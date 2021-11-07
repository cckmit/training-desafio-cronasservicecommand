package training.cronasservicecommand.web.job;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.common.EventsGateway;
import training.cronasservicecommand.domain.job.command.CreateJobCommand;

@RestController
@RequiredArgsConstructor
public class CommandController {
    private final EventsGateway eventBus;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/job", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity> createNew(@RequestBody CreateJobCommand command) {
        eventBus.emit(command).subscribe();
        return Mono.just(ResponseEntity.ok().build());
    }
}
