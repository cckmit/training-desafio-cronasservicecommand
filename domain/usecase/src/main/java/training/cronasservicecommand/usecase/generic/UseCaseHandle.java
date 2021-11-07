package training.cronasservicecommand.usecase.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.generic.DomainEvent;
import training.cronasservicecommand.domain.generic.serializer.EventSerializer;
import training.cronasservicecommand.domain.job.gateway.JobMessageGateway;
import training.cronasservicecommand.domain.storedevent.StoredEventFactory;
import training.cronasservicecommand.domain.storedevent.gateway.StoredEventRepository;

import java.util.List;

import static reactor.core.publisher.Mono.when;

@Component
public abstract class UseCaseHandle {

    @Autowired
    private StoredEventRepository repository;
    @Autowired
    private JobMessageGateway messageService;

    public Mono<Void> saveJob(String jobId, List<DomainEvent> events) {
        return Flux.fromIterable(events)
                .flatMap(event -> {
                    final Mono<Void> saveStoredEvent = save(jobId,event);
                    final Mono<Void> emitDomainEvent = emit(event);
                    return when(saveStoredEvent, emitDomainEvent);
                }).then();
    }

    private Mono<Void> save(String jobId, DomainEvent event){
        return StoredEventFactory.create(jobId,event.getClass().getTypeName(), EventSerializer.instance().serialize(event))
                .flatMap(storedEvent ->  repository.save(storedEvent)).then();
    }

    private Mono<Void> emit(DomainEvent event) {
        return messageService.send(event);
    }
}
