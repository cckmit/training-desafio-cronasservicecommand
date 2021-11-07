package training.cronasservicecommand.reactive;

import org.reactivecommons.async.api.HandlerRegistry;
import org.reactivecommons.async.impl.config.annotations.EnableMessageListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import training.cronasservicecommand.domain.generic.DomainEvent;
import training.cronasservicecommand.domain.job.command.CreateJobCommand;
import training.cronasservicecommand.usecase.generic.UseCaseHandle;
import training.cronasservicecommand.usecase.job.CreateJobUseCase;

import java.util.List;

import static org.reactivecommons.async.api.HandlerRegistry.register;

@Configuration
@EnableMessageListeners
public class EventsSubscriptionsConfig extends UseCaseHandle {

    private final String CREATE_JOB_COMMAND = "cas.job.createjobcommand";
    private final CreateJobUseCase useCase;

    public EventsSubscriptionsConfig(CreateJobUseCase useCase) {
        this.useCase = useCase;
    }

    @Bean
    public HandlerRegistry eventSubscriptions() {
        return register()
            .listenEvent(CREATE_JOB_COMMAND, command -> {
                List<DomainEvent> events =  useCase.apply(command.getData());
                return saveJob(command.getData().getJobId(), events);
            }, CreateJobCommand.class);
    }

}
