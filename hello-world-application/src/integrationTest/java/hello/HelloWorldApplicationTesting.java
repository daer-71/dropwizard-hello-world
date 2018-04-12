package hello;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import hello.health.TemplateHealthCheck;
import hello.resources.HelloWorldResource;

public class HelloWorldApplicationTesting extends Application<AppConfiguration> {


    @Override
    public String getName() {
        return "hello-application hello";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        System.out.println("Initializeing...");
        // nothing to do yet
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        System.out.println("Running...");
    }
}
