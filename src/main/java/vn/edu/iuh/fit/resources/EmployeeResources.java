package vn.edu.iuh.fit.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.models.Employee;
import vn.edu.iuh.fit.services.EmployeeServices;

import java.util.List;
import java.util.Optional;

@Path("/employees")
public class EmployeeResources {
    @Inject
    private EmployeeServices employeeServices;

    @GET
    @Produces("application/json")
    public Response getAll(@DefaultValue("1") @QueryParam("page") int page) {
        List<Employee> employees = employeeServices.getAll(page);

        return Response.ok(employees).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findById(@PathParam("id") long id) {
        Optional<Employee> employee = employeeServices.findById(id);

        if (employee.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(employee.get()).build();
    }

    @POST
    @Consumes("application/json")
    public Response add(Employee employee) {
        boolean b = employeeServices.add(employee);

        if (b)
            return Response.status(Response.Status.CREATED).build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Consumes("application/json")
    public Response update(Employee employee) {
        Optional<Boolean> update = employeeServices.update(employee);

        if (update.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        if (update.get())
            return Response.ok().build();

        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        Optional<Boolean> b = employeeServices.delete(id);

        if (b.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        if (b.get())
            return Response.noContent().build();

        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
