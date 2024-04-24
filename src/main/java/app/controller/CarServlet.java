package app.controller;


import app.model.Car;
import app.model.CarRepository;
import app.model.CarRepositoryHibernate;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CarServlet extends HttpServlet {
    private CarRepository repository = new CarRepositoryHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,String[]> parameters = req.getParameterMap();
        if(parameters.containsKey("id")) {
            Long id = Long.parseLong(parameters.get("id")[0]);
            resp.getWriter().write(repository.getById(id).toString());
        } else {
            resp.getWriter().write(repository.getAll().toString());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id =Long.parseLong(req.getParameterMap().get("id")[0]);
        repository.delete(id);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        Car car = mapper.readValue(req.getReader(), Car.class);
        repository.save(car);
        PrintWriter writer = resp.getWriter();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        Car car = mapper.readValue(req.getReader(), Car.class);
        repository.update(car);
        PrintWriter writer = resp.getWriter();
    }
}
