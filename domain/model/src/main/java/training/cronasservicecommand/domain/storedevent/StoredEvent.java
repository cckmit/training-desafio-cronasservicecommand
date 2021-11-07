package training.cronasservicecommand.domain.storedevent;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class StoredEvent {
    public final String id;
    public final String aggregateId;
    public final String name;
    private final String eventBody;
    private final Date createDate;
}
