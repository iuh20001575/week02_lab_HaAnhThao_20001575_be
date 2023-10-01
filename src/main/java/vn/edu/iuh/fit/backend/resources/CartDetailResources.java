package vn.edu.iuh.fit.backend.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.models.CartDetail;
import vn.edu.iuh.fit.backend.services.CartDetailServices;

import java.util.List;

@Path("/cart-details")
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

    @GET
    @Produces("application/json")
    @Path("/{cus_id}")
    public Response getCartDetailsByCustomerId(@PathParam("cus_id") long customerId) {
        List<CartDetail> cartDetails = cartDetailServices.getCartDetailsByCustomerId(customerId);

        return Response.ok(cartDetails).build();
    }
}
