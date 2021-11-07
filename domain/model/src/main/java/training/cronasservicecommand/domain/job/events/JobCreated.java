package training.cronasservicecommand.domain.job.events;

import training.cronasservicecommand.domain.generic.DomainEvent;

public class JobCreated extends DomainEvent {

    private final String name;
    private final String url;
    private final String httpMethod;
    private final String requestBody;
    private final String interval;
    private final String timezone;
    private final String email;

    public JobCreated(String name, String url, String httpMethod, String requestBody, String interval, String timezone, String email) {
        super("cas.job.jobcreated");
        this.name = name;
        this.url =  url;
        this.httpMethod = httpMethod;
        this.requestBody = requestBody;
        this.interval = interval;
        this.timezone = timezone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public String getInterval() {
        return interval;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getEmail() {
        return email;
    }
}
