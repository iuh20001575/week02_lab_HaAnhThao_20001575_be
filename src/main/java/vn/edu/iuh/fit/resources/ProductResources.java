package vn.edu.iuh.fit.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.services.ProductServices;

import java.util.List;

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
}
