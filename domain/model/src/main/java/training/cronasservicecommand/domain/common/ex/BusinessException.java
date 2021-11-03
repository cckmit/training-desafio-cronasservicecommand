package training.cronasservicecommand.domain.common.ex;

import java.util.function.Supplier;

public class BusinessException extends ApplicationException {

    public enum Type {
        INVALID_INITIAL_DATA("Invalid initial data");

        private final String message;

        public String getMessage() {
            return message;
        }

        public BusinessException build() {
            return new BusinessException(this);
        }

        public Supplier<Throwable> defer() {
            return () -> new BusinessException(this);
        }

        Type(String message) {
            this.message = message;
        }

    }

    private final Type type;

    public BusinessException(Type type){
        super(type.message);
        this.type = type;
    }

    @Override
    public String getCode(){
        return type.name();
    }


}
