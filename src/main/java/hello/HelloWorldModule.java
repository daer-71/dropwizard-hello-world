package hello;

import hello.modules.Some3rdPartyModule;
import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

public class HelloWorldModule extends DropwizardAwareModule<AppConfiguration> {

    @Override
    protected void configure() {
        // 3rd party guice modules installation
        install(new Some3rdPartyModule());

        // example access to Dropwizard objects from module
        configuration();
        environment();
        bootstrap();
    }
}
