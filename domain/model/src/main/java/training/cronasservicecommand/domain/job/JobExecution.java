package training.cronasservicecommand.domain.job;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder(toBuilder = true)
public class JobExecution {
    private final String id;
    private final Date scheduleDate;
    private final Date startDate;
    private final Date endDate;
    private final Long executionTimeSecond;
    private final String httpCode;
    private final String status;
}
