package hello;

import hello.modules.AutoscanModule;
import hello.modules.HelloWorldModule;
import hello.modules.HelloWorldTestModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class HelloWorldApplicationTesting extends Application<AppConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public String getName() {
        return "hello-application-testing";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        LOG.info("Initializing testing app...");
        bootstrap.addBundle(GuiceBundle.builder()
            .enableAutoConfig(getClass().getPackage().getName())
            .useWebInstallers()
            .modules(new AutoscanModule(), new HelloWorldModule(), new HelloWorldTestModule())
            .build());
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        LOG.info("Running testing app...");
    }
}
