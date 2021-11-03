package training.cronasservicecommand.domain.storedevent;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;
import training.cronasservicecommand.domain.common.StringUtils;
import training.cronasservicecommand.domain.common.UniqueIDGenerator;
import training.cronasservicecommand.domain.common.ex.BusinessException;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoredEventFactory {
    public static Mono<StoredEvent> create(String name,String jobId, String body){
        return StringUtils.isEmpty(name) && null != body
                ? Mono.error(BusinessException.Type.INVALID_INITIAL_DATA.build())
                : UniqueIDGenerator.uuid().map(id -> StoredEvent.builder().id(id).name(name).jobId(jobId).body(body).createDate(new Date()).build());
    }
}
