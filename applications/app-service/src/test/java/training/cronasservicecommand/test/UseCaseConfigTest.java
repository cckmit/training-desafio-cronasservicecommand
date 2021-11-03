package training.cronasservicecommand.test;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import training.cronasservicecommand.UseCaseConfig;
import training.cronasservicecommand.defaults.DefaultBeansConfig;
import training.cronasservicecommand.usecase.job.CreateJobUseCase;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UseCaseConfigTest {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @Test
    public void createJobUseCase() {
        assertThat(createJobUseCase).isNotNull();
    }


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void objectMapper() {
        assertThat(objectMapper).isNotNull();
    }

    @SpringBootApplication
    @Import({UseCaseConfig.class, DefaultBeansConfig.class})
    static class App {
        public static void main(String[] args) {
            SpringApplication.run(App.class, args);
        }
    }

}