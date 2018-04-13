package hello.resources;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import adder.Adder;
import hello.AppConfiguration;
import hello.GreeterService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import hello.api.Saying;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HelloWorldResourceTest {

    private static final String TEMPLATE_STRING = "Hello, %s!";
    private static final String DEFAULT_NAME = "Default Name";
    private static final String A_NAME = "A Name";
    private static final String CONTEXT_PATH = "context path";
    private Adder adder;
    @Mock
    private Provider<HttpServletRequest> requestProviderMock;
    @Mock
    private HttpServletRequest httpServletRequestMock;
    @Mock
    private GreeterService greeterServiceMock;
    private AppConfiguration configuration;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(requestProviderMock.get()).thenReturn(httpServletRequestMock);
        when(httpServletRequestMock.getContextPath()).thenReturn(CONTEXT_PATH);
        adder = new Adder();
        configuration = new AppConfiguration();
        configuration.setDefaultName(DEFAULT_NAME);
        configuration.setTemplate(TEMPLATE_STRING);
    }

    @Test
    public void shouldReturnSayingWithDefaultNameWhenEmptyNameAsParameter() {
        when(greeterServiceMock.sayHello(anyString(), eq(DEFAULT_NAME))).thenReturn(new Saying(1, "Greetings, " + DEFAULT_NAME));
        HelloWorldResource target = new HelloWorldResource(configuration, greeterServiceMock, adder, requestProviderMock);
        Saying result = target.sayHello(Optional.empty());
        assertThat(result, is(notNullValue()));
        assertThat(result.getContent(), containsString(DEFAULT_NAME));
    }

    @Test
    public void shouldReturnSayingWithSuppliedName() {
        when(greeterServiceMock.sayHello(anyString(), eq(A_NAME))).thenReturn(new Saying(1, "Greetings, " + A_NAME));
        HelloWorldResource target = new HelloWorldResource(configuration, greeterServiceMock, adder, requestProviderMock);

        Saying result = target.sayHello(Optional.of(A_NAME));

        assertThat(result, is(notNullValue()));
        assertThat(result.getContent(), containsString(A_NAME));
    }
}
