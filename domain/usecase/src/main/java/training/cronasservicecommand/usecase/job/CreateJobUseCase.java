package training.cronasservicecommand.usecase.job;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.common.Event;
import training.cronasservicecommand.domain.common.UniqueIDGenerator;
import training.cronasservicecommand.domain.job.JobFactory;
import training.cronasservicecommand.domain.job.command.CreateJobCommand;
import training.cronasservicecommand.domain.job.events.JobCreated;
import training.cronasservicecommand.domain.job.gateway.JobMessageGateway;
import training.cronasservicecommand.domain.storedevent.StoredEventFactory;
import training.cronasservicecommand.domain.storedevent.gateway.StoredEventRepository;

@RequiredArgsConstructor
public class CreateJobUseCase {
    private final StoredEventRepository repository;
    private final JobMessageGateway messagePublisher;


    public Mono<Void> createNew(CreateJobCommand jobCommand) {
        Gson gson = new GsonBuilder().create();
        return UniqueIDGenerator.uuid()
                //.map(id -> new CreateJobCommand(id, jobCommand.getUrl(), jobCommand.getHttpMethod(), jobCommand.getRequestBody(), jobCommand.getInterval(), jobCommand.getTimezone()))
                .flatMap(id -> JobFactory.create(id, jobCommand.getUrl(), jobCommand.getHttpMethod(), jobCommand.getRequestBody(), jobCommand.getInterval(), jobCommand.getTimezone()))
                .map(job -> new JobCreated(job.getId(),job.getUrl(),job.getHttpMethod(),job.getRequestBody(),job.getInterval(),job.getTimezone()))
                .flatMap(event -> StoredEventFactory.create(event.getClass().getTypeName(), event.getJobId(), gson.toJson(event)))
                .flatMap(storedEvent -> repository.save(storedEvent).thenReturn(gson.fromJson(storedEvent.getBody(), JobCreated.class)))
                .flatMap(event -> emitCreatedEvent(event));
    }

    private Mono<Void> emitCreatedEvent(Event event) {
        return messagePublisher.send(event);
    }
}
