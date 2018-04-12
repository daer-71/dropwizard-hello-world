package hello;

import hello.api.Saying;
import java.util.concurrent.atomic.AtomicLong;

public class GreeterService {

    private final AtomicLong counter;

    public GreeterService() {
        this.counter = new AtomicLong();
    }

    public Saying sayHello(String template, String name) {
        final String greeting = String.format(template, name);
        return new Saying(counter.incrementAndGet(), greeting);
    }
}
