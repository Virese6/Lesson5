package app.controller;


import app.model.Car;
import app.model.CarRepository;
import app.model.CarRepositoryDB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;

public class CarServlet extends HttpServlet {
    private CarRepository repository = new CarRepositoryDB();

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

        String brand = req.getParameter("brand");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        int year = Integer.parseInt(req.getParameter("year"));

        Car car = new Car(brand, price, year);

        Car savedCar = repository.save(car);

        PrintWriter out = resp.getWriter();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        int year = Integer.parseInt(req.getParameter("year"));

        Car car = new Car(brand, price, year);

        repository.update(car);

        PrintWriter out = resp.getWriter();
    }
}
