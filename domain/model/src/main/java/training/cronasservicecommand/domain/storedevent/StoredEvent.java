package training.cronasservicecommand.domain.storedevent;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class StoredEvent {
    public final String id;
    public final String jobId;
    public final String name;
    private final String body;
    private final Date createDate;
}
