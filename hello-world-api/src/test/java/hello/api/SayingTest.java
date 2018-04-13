package hello.api;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class SayingTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final int ID = 1;
    private static final String CONTENT = "Some content";

    @Test
    public void shouldSerializeToJson() throws IOException {
        final Saying saying = new Saying(ID, CONTENT);
        final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/saying.json"), Saying.class));

        String result = MAPPER.writeValueAsString(saying);

        assertThat(result, is(expected));
    }

    @Test
    public void shouldDeserializesFromJSON() throws IOException {
        final Saying saying = new Saying(ID, CONTENT);

        Saying result = MAPPER.readValue(fixture("fixtures/saying.json"), Saying.class);

        assertThat(result, is(saying));
    }
}
