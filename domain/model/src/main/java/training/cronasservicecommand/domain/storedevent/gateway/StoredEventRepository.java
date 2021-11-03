package training.cronasservicecommand.domain.storedevent.gateway;

import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.storedevent.StoredEvent;

public interface StoredEventRepository {
    Mono<StoredEvent> save(StoredEvent event);
}
