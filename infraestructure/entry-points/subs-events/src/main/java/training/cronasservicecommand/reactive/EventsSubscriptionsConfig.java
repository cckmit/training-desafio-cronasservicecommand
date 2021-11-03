package training.cronasservicecommand.reactive;

import lombok.RequiredArgsConstructor;
import org.reactivecommons.async.api.HandlerRegistry;
import org.reactivecommons.async.impl.config.annotations.EnableMessageListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import training.cronasservicecommand.domain.job.command.CreateJobCommand;
import training.cronasservicecommand.usecase.job.CreateJobUseCase;

import static org.reactivecommons.async.api.HandlerRegistry.register;

@Configuration
@EnableMessageListeners
@RequiredArgsConstructor
public class EventsSubscriptionsConfig {

    private final CreateJobUseCase useCase;

    @Bean
    public HandlerRegistry eventSubscriptions() {
        return register()
            .listenEvent(CreateJobCommand.COMMAND_NAME, event -> useCase.createNew(event.getData()), CreateJobCommand.class);
    }

}
