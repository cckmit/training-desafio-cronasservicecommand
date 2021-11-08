package training.cronasservicecommand.domain.generic;

import reactor.core.publisher.Flux;

public abstract class AggregateRoot {

    private final String id;
    private final ChangeEventSubscriber changeEventSubscriber;

    protected AggregateRoot(String id) {
        this.id = id;
        this.changeEventSubscriber = new ChangeEventSubscriber();
    }

    protected final void subscribe(EventChange eventChange) {
        changeEventSubscriber.subscribe(eventChange);
    }

    protected ChangeEventSubscriber.ChangeApply appendChange(DomainEvent event) {
        event.setAggregateId(id);
        return changeEventSubscriber.appendChange(event);
    }

    protected void applyEvent(DomainEvent domainEvent) {
        changeEventSubscriber.applyEvent(domainEvent);
    }

    public Flux<DomainEvent> getUncommittedChanges() {
        return Flux.fromIterable(changeEventSubscriber.getChanges());
    }
}
