package hello.health;

import javax.inject.Inject;
import ru.vyarus.dropwizard.guice.module.installer.feature.health.NamedHealthCheck;

public class TemplateHealthCheck extends NamedHealthCheck {

    private static final String TEST_NAME = "TEST";
    private static final String SERVICE_NAME = "hello-world-service";
    private final String template;

    @Inject
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

    @Override
    public String getName() {
        return SERVICE_NAME;
    }
}
