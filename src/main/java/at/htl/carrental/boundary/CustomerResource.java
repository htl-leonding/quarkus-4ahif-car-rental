package at.htl.carrental.boundary;

import at.htl.carrental.entity.Customer;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("customer")
public class CustomerResource {

    @Inject
    Logger logger;

    @Path("/id/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") String id) {
        return Response.ok(
                new Customer(id, LocalDate.now())
        ).build();
    }

    @GET
    public Response getObjectPerJsonp(
            @QueryParam("fn") @DefaultValue("") String firstName,
            @QueryParam("ln") @DefaultValue("") String lastName
    ) {
        return Response.ok(
                new Customer(
                        firstName + " " + lastName,
                        LocalDate.now())
        ).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addCustomer(
            @FormParam("name") String name,
            @FormParam("dob") String dob,
            @Context UriInfo uriInfo
    ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Customer newCustomer = new Customer(
                name,
                LocalDate.parse(dob, dtf));
        logger.info(newCustomer);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path("42")
                .build();

        return Response.created(uri).build();
    }

    @Path("name")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomerWithJsonb(
            Customer customer,
            @Context UriInfo uriInfo
    ) {

        logger.info(customer);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path("42")
                .build();

        return Response.created(uri).build();
    }
}
