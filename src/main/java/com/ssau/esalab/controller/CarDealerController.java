package com.ssau.esalab.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssau.esalab.dao.ManagerDao;
import com.ssau.esalab.dao.ModelDao;
import com.ssau.esalab.dao.СarDealerDao;
import com.ssau.esalab.model.CarDealer;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@WebServlet
@Path("/carDealer")
public class CarDealerController {
    @EJB
    private СarDealerDao carDealerDao;

    @EJB
    private ModelDao modelDao;

    @EJB
    private ManagerDao managerDao;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/")
    public Response getCarDealer() throws JsonProcessingException {
        List<CarDealer> carDealers = carDealerDao.getAll();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(carDealers))
                .build();
    }

    @GET
    @Path("/{carDealerId}")
    public Response getCarDealerById(@PathParam("carDealerId") String carDealerId) throws JsonProcessingException {
        CarDealer carDealer = carDealerDao.get(Integer.valueOf(carDealerId));

        if (carDealer == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Car Dealer with id %s not found", carDealerId)).build();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(carDealer))
                .build();
    }

    @POST
    @Path("/")
    public Response addNewCarDealerId(
            @FormParam("name") String name,
            @FormParam("address") String address,
            @FormParam("phone") String phone
    ) {
        CarDealer carDealer = new CarDealer();

        carDealer.setName(name);
        carDealer.setAddress(address);
        carDealer.setPhone(phone);

        carDealerDao.save(carDealer);
        return Response.ok().build();
    }

    @PUT
    @Path("/{carDealerId}")
    public Response updateModel(
            @PathParam("carDealerId") String carDealerId,
            @DefaultValue("") @FormParam("name") String name,
            @DefaultValue("") @FormParam("address") String address,
            @DefaultValue("") @FormParam("phone") String phone) {
        CarDealer carDealer = carDealerDao.get(Integer.valueOf(carDealerId));
        if (carDealer == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Model with id %s not found", carDealerId)).build();
        }


        if (!name.isEmpty()) {
            carDealer.setName(name);
        }
        if (!address.isEmpty()) {
            carDealer.setAddress(address);
        }
        if (!phone.isEmpty()) {
            carDealer.setPhone(phone);
        }
        carDealerDao.update(carDealer);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{carDealerId}")
    public Response deleteModel(@PathParam("carDealerId") String carDealerId) {
        CarDealer carDealer = carDealerDao.get(Integer.valueOf(carDealerId));
        if (carDealer == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Car Dealer with id %s not found", carDealerId)).build();
        }
        carDealerDao.delete(carDealer);
        return Response.ok().build();

    }
}
