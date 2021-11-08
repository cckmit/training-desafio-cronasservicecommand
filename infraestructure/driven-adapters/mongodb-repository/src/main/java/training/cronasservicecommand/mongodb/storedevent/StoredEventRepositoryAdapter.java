package training.cronasservicecommand.mongodb.storedevent;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import training.cronasservicecommand.domain.generic.DomainEvent;
import training.cronasservicecommand.domain.generic.serializer.EventSerializer;
import training.cronasservicecommand.domain.storedevent.StoredEvent;
import training.cronasservicecommand.domain.storedevent.gateway.StoredEventRepository;
import training.cronasservicecommand.reactive.repository.mongo.AdapterOperations;

import java.util.function.Function;

@Repository
public class StoredEventRepositoryAdapter extends AdapterOperations<StoredEvent, StoredEventData, String, StoredEventDataRepository> implements StoredEventRepository {

    @Autowired
    public StoredEventRepositoryAdapter(StoredEventDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, StoredEvent.StoredEventBuilder.class).build());
    }

    @Override
    public Flux<DomainEvent> getById(String aggregateId) {
//        Query query = new Query(Criteria.where("aggregateId").is(aggregateId))
//                .with(new Sort(Sort.DEFAULT_DIRECTION.ASC,"id"));
//
//        return doQueryMany(mongoTemplate.find(query, StoredEventData.class));
//                //.map(getDomainEvent());

        return doQueryMany(repository.findAllByAggregateId(aggregateId))
                .map(getDomainEvent());

    }

    private Function<StoredEvent, DomainEvent> getDomainEvent(){
        return storedEvent -> {
            try {
                return EventSerializer.instance()
                        .deserialize(storedEvent.getEventBody(),Class.forName(storedEvent.getName()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
