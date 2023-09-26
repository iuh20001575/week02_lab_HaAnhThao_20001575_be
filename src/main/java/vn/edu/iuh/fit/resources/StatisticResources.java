package vn.edu.iuh.fit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.services.StatisticServices;

import java.time.LocalDateTime;
import java.util.Map;

@Path("/statistics")
public class StatisticResources {
    @Inject
    private StatisticServices statisticServices;
    private final ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("/by-date")
    public Response statisticByDate() throws JsonProcessingException {
        Map<LocalDateTime, Integer> localDateTimeIntegerMap = statisticServices.statisticsByDate();

        ObjectMapper mapper = new ObjectMapper();
        return Response.ok(mapper.writeValueAsString(localDateTimeIntegerMap)).build();
    }

    @GET
    @Path("/by-time-range/{start-date}/{end-date}")
    public Response statisticsByPeriod(@PathParam("start-date") LocalDateTime startDate, @PathParam("end-date") LocalDateTime endDate) throws JsonProcessingException {
        Map<LocalDateTime, Integer> map = statisticServices.statisticsByPeriod(startDate, endDate);

        return Response.ok(mapper.writeValueAsString(map)).build();
    }

    @GET
    @Path("/by-employee/{start-date}/{end-date}")
    public Response statisticsByEmployee(@PathParam("start-date") LocalDateTime startDate, @PathParam("end-date") LocalDateTime endDate) throws JsonProcessingException {
        Map<Long, Integer> map = statisticServices.statisticsByEmployee(startDate, endDate);

        return Response.ok(mapper.writeValueAsString(map)).build();
    }
}
