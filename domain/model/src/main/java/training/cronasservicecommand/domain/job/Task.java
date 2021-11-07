package training.cronasservicecommand.domain.job;

import java.util.Date;
import java.util.Objects;

public class Task {
    private final String id;
    private final Date scheduleDate;
    private final Date startDate;
    private final Date endDate;
    private final Long executionTimeSecond;
    private final String httpCode;
    private final String status;

    public Task(String id, Date scheduleDate, Date startDate, Date endDate, Long executionTimeSecond, String httpCode, String status){
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.executionTimeSecond = executionTimeSecond;
        this.httpCode = httpCode;
        this.status = status;
    }

    public String id() {
        return id;
    }

    public Date scheduleDate() {
        return scheduleDate;
    }

    public Date startDate() {
        return startDate;
    }

    public Date endDate() {
        return endDate;
    }

    public Long executionTimeSecond() {
        return executionTimeSecond;
    }

    public String httpCode() {
        return httpCode;
    }

    public String status() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
