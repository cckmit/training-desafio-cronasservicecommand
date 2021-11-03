package training.cronasservicecommand.domain.job.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import training.cronasservicecommand.domain.common.Event;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobCreated implements Event {

    public static final String EVENT_NAME = "cas.events.job.created";
    private String jobId;
    private String url;
    private String httpMethod;
    private String requestBody;
    private String interval;
    private String timezone;

    @Override
    public String name() {
        return EVENT_NAME;
    }

}
