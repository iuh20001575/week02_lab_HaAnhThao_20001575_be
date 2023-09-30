package vn.edu.iuh.fit.backend.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.services.CartDetailServices;

@Path("/cart-detail")
public class CartDetailResources {
    private final CartDetailServices cartDetailServices;

    public CartDetailResources() {
        cartDetailServices = new CartDetailServices();
    }

    @GET
    @Produces("text/plain")
    @Path("/{cus_id}/count")
    public Response countByCustomer(@PathParam("cus_id") long customerId) {
        return Response.ok(cartDetailServices.countByCustomer(customerId)).build();
    }
}
