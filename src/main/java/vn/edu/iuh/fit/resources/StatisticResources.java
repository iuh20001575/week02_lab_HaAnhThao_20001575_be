package vn.edu.iuh.fit.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.services.StatisticDateServices;

import java.time.LocalDateTime;
import java.util.Map;

@Path("/statistics")
public class StatisticResources {
    @Inject
    private StatisticDateServices statisticDateServices;

    @GET
    @Path("/by-date")
    public Response statisticByDate() throws JsonProcessingException {
        Map<LocalDateTime, Integer> localDateTimeIntegerMap = statisticDateServices.statisticsByDate();

        ObjectMapper mapper = new ObjectMapper();
        return Response.ok(mapper.writeValueAsString(localDateTimeIntegerMap)).build();
    }

    @GET
    @Path("/by-time-range/{start-date}/{end-date}")
    public Response statisticsByPeriod(@PathParam("start-date") LocalDateTime startDate, @PathParam("end-date") LocalDateTime endDate) throws JsonProcessingException {
        Map<LocalDateTime, Integer> map = statisticDateServices.statisticsByPeriod(startDate, endDate);

        ObjectMapper mapper = new ObjectMapper();
        return Response.ok(mapper.writeValueAsString(map)).build();
    }
}
