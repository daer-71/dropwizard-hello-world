package hello.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.util.Optional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import hello.HelloWorldApplication;
import hello.AppConfiguration;
import hello.api.Saying;

public class HelloWorldResourceIT {

    @ClassRule
    public static final DropwizardAppRule<AppConfiguration> RULE =
        new DropwizardAppRule<>(HelloWorldApplication.class, ResourceHelpers.resourceFilePath("app-configuration.yaml"));

    private Client client;

    @BeforeClass
    public static void migrateDb() {
        System.out.println("Before class");
    }

    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void shouldTest() {
        AppConfiguration configuration = RULE.getConfiguration();
        final Optional<String> name = Optional.of("Dr. IntegrationTest");
        final Saying result = client.target("http://localhost:" + RULE.getLocalPort() + "/hello-world")
            .queryParam("name", name.get())
            .request()
            .get(Saying.class);
        assertThat(result.getContent(), is(notNullValue()));
        String expected = String.format(configuration.getTemplate(), name.get());
        assertThat(result.getContent(), is(expected));
    }
}
