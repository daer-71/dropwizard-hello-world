package hello.health;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.codahale.metrics.health.HealthCheck.Result;
import org.junit.jupiter.api.Test;

public class TemplateHealthCheckTest {

    private static final String TEMPLATE_STRING = "Hello, %s!";

    @Test
    public void shouldReturnHealthy() {
        TemplateHealthCheck target = new TemplateHealthCheck(TEMPLATE_STRING);
        Result result = target.execute();
        assertThat(result.isHealthy(), is(true));
    }

    @Test
    public void shouldReturnUnHealthyOnFaultyTemplate() {
        TemplateHealthCheck target = new TemplateHealthCheck("Template...");
        Result result = target.execute();
        assertThat(result.isHealthy(), is(false));
    }
}
