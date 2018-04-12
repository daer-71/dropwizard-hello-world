package hello;

import hello.modules.AutoscanModule;
import hello.modules.HelloWorldModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
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
        // nothing to do here
    }
}
