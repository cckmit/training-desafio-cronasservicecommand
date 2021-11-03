package training.cronasservicecommand.defaults;

import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.common.Event;
import training.cronasservicecommand.domain.common.EventsGateway;
import training.cronasservicecommand.domain.job.Job;
import training.cronasservicecommand.domain.job.events.JobCreated;
import training.cronasservicecommand.domain.job.gateway.JobMessageGateway;
import training.cronasservicecommand.domain.job.gateway.JobRepository;
import training.cronasservicecommand.domain.storedevent.StoredEvent;
import training.cronasservicecommand.domain.storedevent.gateway.StoredEventRepository;

import java.util.logging.Level;


/*
 * Contiene definiciones de beans usados por defecto cuando no se han encontrado alternativas de implementación.
 * Se usa para facilitar la demostración de capacidades agregadas gradualmente mediante los diferentes driven-adapters
 */
@Log
@Configuration
public class DefaultBeansConfig {

    @Bean
    @ConditionalOnMissingBean
    public JobRepository jobRepository() {
        alertFakeBean("JobRepository");
        return jobRepository;
    }

    @Bean
    @ConditionalOnMissingBean
    public StoredEventRepository storedEventRepository() {
        alertFakeBean("StoredEventRepository");
        return storedEventRepository;
    }

    @Bean
    @ConditionalOnMissingBean()
    public EventsGateway eventsGateway() {
        alertFakeBean("EventsGateway");
        return eventsGateway;
    }

    @Bean
    @ConditionalOnMissingBean
    public JobMessageGateway jobMessageGateway() {
        alertFakeBean("JobCommandGateway");
        return jobMessageGateway;
    }

    private void alertFakeBean(String beanName) {
        log.log(Level.WARNING, "CONFIGURACION FAKE: " + beanName, beanName);
    }

    private final JobMessageGateway jobMessageGateway = (Event event) -> {
        log.info("Usando JobMessageGateway sin implementación: " + event.name());
        return Mono.empty();
    };

    private final EventsGateway eventsGateway = event -> {
        log.info("Evento de dominio emitido sólo a sysout:");
        log.info(event.toString());
        return Mono.empty();
    };

    private final JobRepository jobRepository = new JobRepository() {
        @Override
        public Mono<Job> save(JobCreated event) {
            log.info("Guardado a repo sin implementación: ");
            log.info(event.toString());
            return Mono.empty();
        }

        @Override
        public Mono<Job> findById(String id) {
            log.info("Usando JobRepository.findById sin implementación");
            return Mono.just(Job.builder().id(id).build());
        }

        @Override
        public Flux<Job> findAll() {
            log.info("Usando JobRepository.findAll sin implementación");
            return Flux.empty();
        }
    };

    private final StoredEventRepository storedEventRepository = new StoredEventRepository() {

        @Override
        public Mono<StoredEvent> save(StoredEvent event) {
            log.info("Usando StoredEventRepository.save sin implementación");
            return Mono.just(event);
        }
    };

}

