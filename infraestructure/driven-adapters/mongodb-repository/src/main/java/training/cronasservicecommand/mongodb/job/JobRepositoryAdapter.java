package training.cronasservicecommand.mongodb.job;

import com.mongodb.MongoClient;
import training.cronasservicecommand.reactive.repository.mongo.AdapterOperations;
import org.bson.Document;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.job.Job;
import training.cronasservicecommand.domain.job.events.JobCreated;
import training.cronasservicecommand.domain.job.gateway.JobRepository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JobRepositoryAdapter extends AdapterOperations<Job, JobData, String, JobDataRepository> implements JobRepository {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    public JobRepositoryAdapter(JobDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Job.class));
    }

    @Override
    public Mono<Job> save(JobCreated event){
//        Map<String, Object> document = new HashMap<>();
//        document.put("_id", event.getJobId());
//        document.put("url", event.getUrl());
//        document.put("httpMethod", event.getHttpMethod());
//        document.put("requestBody", event.getRequestBody());
//        document.put("interval", event.getInterval());
//        document.put("timezone", event.getTimezone());
//
//        mongoClient.getDatabase("casread").getCollection("job").insertOne(new Document(document));
        return Mono.empty();
    }

    @Override
    public Flux<Job> findAll() {
        return doQueryMany(repository.findAll());
    }
}
