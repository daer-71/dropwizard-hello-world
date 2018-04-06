package hello;

import hello.modules.AutoscanModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import hello.health.TemplateHealthCheck;
import hello.resources.HelloWorldResource;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class HelloWorldApplication extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-application";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
            .enableAutoConfig(getClass().getPackage().getName())
            .useWebInstallers()
            .modules(new AutoscanModule(), new HelloWorldModule())
            .build());
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
//        final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
//        environment.jersey().register(resource);
    }
}
