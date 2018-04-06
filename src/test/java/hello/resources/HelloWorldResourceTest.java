package hello.resources;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import hello.api.Saying;

public class HelloWorldResourceTest {

    private static final String TEMPLATE_STRING = "Hello, %s!";
    private static final String DEFAULT_NAME = "Default Name";
    private static final String A_NAME = "A Name";

    @Test
    public void shouldReturnSayingWithDefaultNameWhenEmptyNameAsParameter() {
        HelloWorldResource target = new HelloWorldResource(TEMPLATE_STRING, DEFAULT_NAME);
        Saying result = target.sayHello(Optional.empty());
        assertThat(result, is(notNullValue()));
        assertThat(result.getContent(), containsString(DEFAULT_NAME));
    }

    @Test
    public void shouldReturnSayingWithSuppliedName() {
        HelloWorldResource target = new HelloWorldResource(TEMPLATE_STRING, DEFAULT_NAME);
        Saying result = target.sayHello(Optional.of(A_NAME));
        assertThat(result, is(notNullValue()));
        assertThat(result.getContent(), containsString(A_NAME));
    }

}
