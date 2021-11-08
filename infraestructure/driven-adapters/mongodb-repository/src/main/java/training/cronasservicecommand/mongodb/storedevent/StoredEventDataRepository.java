package training.cronasservicecommand.mongodb.storedevent;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import training.cronasservicecommand.domain.generic.DomainEvent;
import training.cronasservicecommand.domain.generic.StoredEvent;

import java.util.List;

public interface StoredEventDataRepository  extends ReactiveCrudRepository<StoredEventData, String>, ReactiveQueryByExampleExecutor<StoredEventData> {
    Flux<StoredEventData> findAllByAggregateId(String aggregateId);
}
