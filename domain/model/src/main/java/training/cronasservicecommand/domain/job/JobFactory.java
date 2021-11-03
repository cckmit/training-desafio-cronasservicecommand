package training.cronasservicecommand.domain.job;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.common.StringUtils;
import training.cronasservicecommand.domain.common.ex.BusinessException;

import static reactor.core.publisher.Mono.just;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JobFactory {
    public static Mono<Job> create(String id, String url, String httpMethod, String requestBody,String interval, String timezone){
        return StringUtils.isEmpty(id, url, interval, timezone)
                ? Mono.error(BusinessException.Type.INVALID_INITIAL_DATA.build())
                : just(Job.builder()
                            .id(id)
                            .url(url)
                            .httpMethod(httpMethod)
                            .requestBody(requestBody)
                            .interval(interval)
                            .timezone(timezone)
                            .build());
    }

}
