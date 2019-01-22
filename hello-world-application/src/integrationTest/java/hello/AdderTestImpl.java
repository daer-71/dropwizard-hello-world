package hello;

import adder.Adder;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdderTestImpl extends Adder {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public int add(int a, int b) {
        LOG.info("Adding in test adder implementation");
        return super.add(b, b);
    }
}
