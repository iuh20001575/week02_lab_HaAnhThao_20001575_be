package vn.edu.iuh.fit.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.OrderDetail;
import vn.edu.iuh.fit.services.OrderDetailServices;

import java.util.List;
import java.util.Optional;

@Path("/order-details")
public class OrderDetailResources {
    @Inject
    private OrderDetailServices orderDetailServices;

    @GET
    @Produces("application/json")
    public Response getAll(@DefaultValue("1") @QueryParam("page") int page) {
        List<OrderDetail> orderDetails = orderDetailServices.getAll(page);

        return Response.ok(orderDetails).build();
    }

    @GET
    @Path("/{order-id}/{product-id}")
    @Produces("application/json")
    public Response findById(@PathParam("order-id") long orderId, @PathParam("product-id") long productId) {
        Optional<OrderDetail> orderDetail = orderDetailServices.findById(orderId, productId);

        if (orderDetail.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(orderDetail.get()).build();
    }

    @POST
    @Consumes("application/json")
    public Response add(OrderDetail orderDetail) {
        boolean b = orderDetailServices.add(orderDetail);

        if (b)
            return Response.status(Response.Status.CREATED).build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes("application/json")
    public Response update(OrderDetail orderDetail) {
        Optional<Boolean> update = orderDetailServices.update(orderDetail);

        if (update.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        if (update.get())
            return Response.ok().build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
