package com.ssau.esalab.controller;

import com.ssau.esalab.dao.CarDao;
import com.ssau.esalab.dao.ModelDao;
import com.ssau.esalab.model.Car;
import com.ssau.esalab.model.Model;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

@WebServlet
@Path("/car")
public class CarController {

    @EJB
    private CarDao carDao;

    @EJB
    private ModelDao modelDao;


    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/")
    public Response getCar() throws JsonProcessingException {
        List<Car> cars = carDao.getAll();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(cars))
                .build();
    }

    @GET
    @Path("/{carId}")
    public Response getCarById(@PathParam("carId") String carId) throws JsonProcessingException {
        Car car = carDao.get(Integer.valueOf(carId));

        if (car == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Car with id %s not found", carId)).build();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(car))
                .build();
    }

    @POST
    @Path("/")
    public Response addNewCar(
            @FormParam("modelId") String model_id,
            @FormParam("cost") String cost,
            @FormParam("numberD") String numberD,
            @FormParam("color") String color) {

        Car car = new Car();
        car.setCost(Integer.parseInt(cost));
        car.setColor(color);
        car.setNumberD(Integer.parseInt(numberD));

        Model model = modelDao.get(Integer.valueOf(model_id));
        if (model == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("car with id %s not found", model_id)).build();
        car.setModel(model);

        carDao.save(car);
        return Response.ok().build();
    }

    @PUT
    @Path("/{carId}")
    public Response updateCar(
            @PathParam("carId") String carId,
            @DefaultValue("") @FormParam("cost") String cost,
            @DefaultValue("") @FormParam("numberD") String numberD,
            @DefaultValue("") @FormParam("color") String color) {

        Car car = carDao.get(Integer.valueOf(carId));
        if (car == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Car with id %s not found", carId)).build();
        }


        if (!cost.isEmpty()) {
            car.setCost(Integer.parseInt(cost));
        }
        if (!numberD.isEmpty()) {
            car.setNumberD(Integer.parseInt(numberD));
        }
        if (!color.isEmpty()) {
            car.setColor(color);
        }
        carDao.update(car);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{carId}")
    public Response deleteCar(@PathParam("carId") String carId) {
        Car car = carDao.get(Integer.valueOf(carId));
        if (car == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Car with id %s not found", carId)).build();
        }

        carDao.delete(car);
        return Response.ok().build();
    }

}
