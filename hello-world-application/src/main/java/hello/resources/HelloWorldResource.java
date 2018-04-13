package hello.resources;

import adder.Adder;
import com.codahale.metrics.annotation.Timed;
import hello.AppConfiguration;
import hello.GreeterService;
import hello.api.Added;
import java.lang.invoke.MethodHandles;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final AppConfiguration configuration;
    private final GreeterService greeterService;
    private final Adder adder;
    private final Provider<HttpServletRequest> requestProvider;


    @Inject
    public HelloWorldResource(AppConfiguration configuration, GreeterService greeterService, Adder adder, Provider<HttpServletRequest> requestProvider) {
        this.configuration = configuration;
        this.greeterService = greeterService;
        this.adder = adder;
        this.requestProvider = requestProvider;
    }

    @GET
    @Timed
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        LOG.info("Path info: " + requestProvider.get().getPathInfo());
        return greeterService.sayHello(configuration.getTemplate(), name.orElse(configuration.getDefaultName()));
    }

    @GET
    @Path("/add")
    @Timed
    public Added add(@QueryParam("a") String a, @QueryParam("b") String b) {
        int result = adder.add(Integer.parseInt(a), Integer.parseInt(b));
        return new Added(result);
    }
}
