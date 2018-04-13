package hello.modules;

import adder.Adder;
import com.google.inject.AbstractModule;
import hello.AdderTestImpl;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldTestModule extends AbstractModule {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    protected void configure() {
        LOG.info("Configure in HelloWorldTestModule");
        bind(Adder.class).to(AdderTestImpl.class);
    }
}
