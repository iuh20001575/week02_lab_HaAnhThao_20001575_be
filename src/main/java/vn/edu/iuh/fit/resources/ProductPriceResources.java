package vn.edu.iuh.fit.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.ProductPrice;
import vn.edu.iuh.fit.services.ProductPriceServices;

import java.util.List;
import java.util.Optional;

@Path("/product-prices")
public class ProductPriceResources {
    @Inject
    private ProductPriceServices productPriceServices;

    @GET
    @Produces("application/json")
    public Response getAll(@DefaultValue("1") @QueryParam("page") int page) {
        List<ProductPrice> productPrices = productPriceServices.getAll(page);

        return Response.ok(productPrices).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getProductNewPrice(@PathParam("id") long id) {
        System.out.println("~ Product ID: " + id);
        Optional<ProductPrice> productNewPrice = productPriceServices.getProductNewPrice(id);

        if (productNewPrice.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(productNewPrice.get()).build();
    }

    @POST
    @Consumes("application/json")
    public Response add(ProductPrice productPrice) {
        boolean b = productPriceServices.add(productPrice);

        if (b)
            return Response.status(Response.Status.CREATED).build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes("application/json")
    public Response update(ProductPrice productPrice) {
        Optional<Boolean> update = productPriceServices.update(productPrice);

        if (update.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        if (update.get())
            return Response.ok().build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
