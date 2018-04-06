package hello.resources;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

import hello.api.Saying;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HelloWorldResourceTest {

    private static final String TEMPLATE_STRING = "Hello, %s!";
    private static final String DEFAULT_NAME = "Default Name";
    private static final String A_NAME = "A Name";
    private static final String CONTEXT_PATH = "context path";
    @Mock
    private Provider<HttpServletRequest> requestProviderMock;
    @Mock
    private HttpServletRequest httpServletRequestMock;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(requestProviderMock.get()).thenReturn(httpServletRequestMock);
        when(httpServletRequestMock.getContextPath()).thenReturn(CONTEXT_PATH);
    }

    @Test
    public void shouldReturnSayingWithDefaultNameWhenEmptyNameAsParameter() {
        HelloWorldResource target = new HelloWorldResource(TEMPLATE_STRING, DEFAULT_NAME, requestProviderMock);
        Saying result = target.sayHello(Optional.empty());
        assertThat(result, is(notNullValue()));
        assertThat(result.getContent(), containsString(DEFAULT_NAME));
    }

    @Test
    public void shouldReturnSayingWithSuppliedName() {
        HelloWorldResource target = new HelloWorldResource(TEMPLATE_STRING, DEFAULT_NAME, requestProviderMock);
        Saying result = target.sayHello(Optional.of(A_NAME));
        assertThat(result, is(notNullValue()));
        assertThat(result.getContent(), containsString(A_NAME));
    }

}
