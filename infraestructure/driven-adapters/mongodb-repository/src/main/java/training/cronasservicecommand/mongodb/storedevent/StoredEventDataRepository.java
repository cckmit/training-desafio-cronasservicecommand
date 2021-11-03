package training.cronasservicecommand.mongodb.storedevent;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface StoredEventDataRepository  extends ReactiveCrudRepository<StoredEventData, String>, ReactiveQueryByExampleExecutor<StoredEventData> {
}
