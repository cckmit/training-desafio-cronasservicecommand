package training.cronasservicecommand.mongodb.storedevent;

import training.cronasservicecommand.reactive.repository.mongo.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import training.cronasservicecommand.domain.storedevent.StoredEvent;
import training.cronasservicecommand.domain.storedevent.gateway.StoredEventRepository;

@Repository
public class StoredEventRepositoryAdapter extends AdapterOperations<StoredEvent, StoredEventData, String, StoredEventDataRepository> implements StoredEventRepository {

    @Autowired
    public StoredEventRepositoryAdapter(StoredEventDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, StoredEvent.StoredEventBuilder.class).build());
    }

}
