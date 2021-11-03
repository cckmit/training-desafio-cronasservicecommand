package training.cronasservicecommand.defaults;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultBeansConfigTest {

    private DefaultBeansConfig config = new DefaultBeansConfig();

    @Test
    public void jobRepositoryShouldHaveNoError() {
        assertThat(config.jobRepository()).isNotNull();
        assertThat(config.jobRepository().findAll()).isNotNull();
    }

}