package tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import hello.AppConfiguration;
import hello.HelloWorldApplicationTesting;
import hello.api.Added;
import hello.api.Saying;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import java.lang.invoke.MethodHandles;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldResourceIT {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ClassRule
    public static final DropwizardAppRule<AppConfiguration> RULE =
        new DropwizardAppRule<>(HelloWorldApplicationTesting.class, ResourceHelpers.resourceFilePath("app-configuration.yaml"));

    private Client client;

    @BeforeClass
    public static void beforeClass() {
        LOG.info("Before class");
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
    public void shouldSayHello() {
        AppConfiguration configuration = RULE.getConfiguration();
        final String name = "Dr. IntegrationTest";

        Saying result = client.target("http://localhost:" + RULE.getLocalPort() + "/hello-world")
            .queryParam("name", name)
            .request()
            .accept(MediaType.APPLICATION_JSON)
            .get(Saying.class);

        String expected = String.format(configuration.getTemplate(), name);
        assertThat(result.getContent(), is(expected));
    }

    @Test
    public void shouldAdd() {
        Added result = client.target("http://localhost:" + RULE.getLocalPort() + "/hello-world/add")
            .queryParam("a", "1")
            .queryParam("b", "2")
            .request()
            .accept(MediaType.APPLICATION_JSON)
            .get(Added.class);

        assertThat(result.getResult(), is(4));
    }
}
