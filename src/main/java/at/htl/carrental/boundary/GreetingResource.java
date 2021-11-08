package at.htl.carrental.boundary;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "greeting", defaultValue = "hi")
    String greeting;

    @GET
        @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greeting + " 4AHIF!";
    }
}