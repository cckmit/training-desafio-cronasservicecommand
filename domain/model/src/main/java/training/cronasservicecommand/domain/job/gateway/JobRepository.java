package training.cronasservicecommand.domain.job.gateway;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.job.Job;
import training.cronasservicecommand.domain.job.events.JobCreated;

public interface JobRepository {
    Mono<Job> save(JobCreated event);
    Mono<Job> findById(String id);
    Flux<Job> findAll();
}
