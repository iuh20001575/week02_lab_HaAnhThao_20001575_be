package vn.edu.iuh.fit.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.services.OrderServices;
import vn.edu.iuh.fit.backend.models.Order;

import java.util.List;
import java.util.Optional;

@Path("/orders")
public class OrderResources {
    @Inject
    private OrderServices orderServices;

    @GET
    @Produces("application/json")
    public Response getAll(@DefaultValue("1") @QueryParam("page") int page) {
        List<Order> orders = orderServices.getAll(page);

        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") long id) {
        Optional<Order> order = orderServices.findById(id);

        if (order.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(order.get()).build();
    }

    @POST
    @Consumes("application/json")
    public Response add(Order order) {
        boolean b = orderServices.add(order);

        if (b)
            return Response.status(Response.Status.CREATED).build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes("application/json")
    public Response update(Order order) {
        Optional<Boolean> b = orderServices.update(order);

        if (b.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        if (b.get())
            return Response.ok().build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
