package hello.resources;

import com.codahale.metrics.annotation.Timed;
import hello.GreeterService;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import hello.api.Saying;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private final String template;
    private final String defaultName;
    private final GreeterService greeterService;
    private final Provider<HttpServletRequest> requestProvider;


    @Inject
    public HelloWorldResource(String template, String defaultName, GreeterService greeterService, Provider<HttpServletRequest> requestProvider) {
        this.template = template;
        this.defaultName = defaultName;
        this.greeterService = greeterService;
        this.requestProvider = requestProvider;
    }

    @GET
    @Timed
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        System.out.println("Context path: " + requestProvider.get().getContextPath());

        return greeterService.sayHello(template, name.orElse(defaultName));
    }
}
