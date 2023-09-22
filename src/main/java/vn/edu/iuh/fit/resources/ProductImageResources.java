package vn.edu.iuh.fit.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.ProductImage;
import vn.edu.iuh.fit.services.ProductImageServices;

import java.util.List;
import java.util.Optional;

@Path("/product-images")
public class ProductImageResources {
    @Inject
    private ProductImageServices productImageServices;

    @GET
    @Produces("application/json")
    public Response getAll(@DefaultValue("1") @QueryParam("page") int page) {
        List<ProductImage> productImages = productImageServices.getAll(page);

        return Response.ok(productImages).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") long id) {
        Optional<ProductImage> productImage = productImageServices.findById(id);

        if (productImage.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(productImage.get()).build();
    }

    @POST
    @Consumes("application/json")
    public Response add(ProductImage productImage) {
        boolean b = productImageServices.add(productImage);

        if (b)
            return Response.status(Response.Status.CREATED).build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes("application/json")
    public Response update(ProductImage productImage) {
        System.out.println("~ productImage: " + productImage);
        Optional<Boolean> update = productImageServices.update(productImage);

        if (update.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        if (update.get())
            return Response.ok().build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
