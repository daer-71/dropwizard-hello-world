package hello;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class ServiceTest {

    @Test
    public void shouldAdd() {
        Service target = new Service();

        int result = target.add(1, 2);

        assertThat(result, is(3));
    }
}
