package hello.health;

import com.codahale.metrics.health.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

    public static final String TEST_NAME = "TEST";
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() {
        final String saying = String.format(template, TEST_NAME);
        if (!saying.contains(TEST_NAME)) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
