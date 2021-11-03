package training.cronasservicecommand.usecase.job;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.common.UniqueIDGenerator;
import training.cronasservicecommand.domain.job.events.JobRequestExecution;
import training.cronasservicecommand.domain.storedevent.StoredEventFactory;
import training.cronasservicecommand.domain.storedevent.gateway.StoredEventRepository;

@RequiredArgsConstructor
public class RequestExecutionJobUseCase {
    private final StoredEventRepository repository;

    public Mono<Void> execute(JobRequestExecution event) {
        Gson gson = new GsonBuilder().create();
        return UniqueIDGenerator.uuid()
                .flatMap(e -> StoredEventFactory.create(event.getClass().getTypeName(), event.getJobId(), gson.toJson(event)))
                .flatMap(storedEvent -> repository.save(storedEvent))
                .then();
    }
}
