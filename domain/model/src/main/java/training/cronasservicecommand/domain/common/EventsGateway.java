package training.cronasservicecommand.domain.common;

import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.generic.Command;

/**
 * Interfaz propia del dominio para emitir eventos completamente definidos por las necesidades del dominio
 * El tipo Event se puede enriquecer en la medida que sea necesario.
 */
public interface EventsGateway {
    Mono<Void> emit(Command command);
}
