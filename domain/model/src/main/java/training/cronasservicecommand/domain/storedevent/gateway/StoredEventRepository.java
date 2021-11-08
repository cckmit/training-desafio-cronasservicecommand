package training.cronasservicecommand.domain.storedevent.gateway;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.generic.DomainEvent;
import training.cronasservicecommand.domain.storedevent.StoredEvent;

import java.util.List;

public interface StoredEventRepository {
    Mono<StoredEvent> save(StoredEvent event);
    Flux<DomainEvent> getById(String aggregateId);
}
