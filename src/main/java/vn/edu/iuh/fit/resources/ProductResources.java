package vn.edu.iuh.fit.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.services.ProductServices;

import java.util.List;
import java.util.Optional;

@Path("/products")
public class ProductResources {
    @Inject
    private ProductServices productServices;

    @GET
    @Produces("application/json")
    public Response getAll(@DefaultValue("1") @QueryParam("page") int page) {
        List<Product> products = productServices.getAll(page);

        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") long id) {
        Optional<Product> product = productServices.findById(id);

        if (product.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(product.get()).build();
    }
}
