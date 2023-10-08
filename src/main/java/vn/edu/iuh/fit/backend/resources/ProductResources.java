package vn.edu.iuh.fit.backend.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.backend.services.ProductImageServices;
import vn.edu.iuh.fit.backend.services.ProductServices;
import vn.edu.iuh.fit.backend.models.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Path("/products")
public class ProductResources {
    @Inject
    private ProductServices productServices;
    @Inject
    private ProductImageServices productImageServices;
    private final ObjectMapper mapper = new ObjectMapper();

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

    @GET
    @Path("/{id}/images")
    @Produces("application/json")
    public Response getImageByProductId(@PathParam("id") long id) {
        return Response.ok(productImageServices.getByProductId(id)).build();
    }

    @GET
    @Path("/product-price")
    @Produces("application/json")
    public Response getProductIdAndNameInProductPrice() throws JsonProcessingException {
        Map<Long, String> map = productServices.getProductIdAndNameInProductPrice();

        return Response.ok(mapper.writeValueAsString(map)).build();
    }

    @POST
    @Consumes("application/json")
    public Response add(Product product) {
        boolean b = productServices.add(product);

        if (b)
            return Response.status(Response.Status.CREATED).build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes("application/json")
    public Response update(Product product) {
        Optional<Boolean> update = productServices.update(product);

        if (update.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        if (update.get())
            return Response.ok().build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        Optional<Boolean> b = productServices.delete(id);

        if (b.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        if (b.get())
            return Response.noContent().build();

        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
