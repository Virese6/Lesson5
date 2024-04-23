package app.model;



import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static app.constants.Constants.*;

public class CarRepositoryDB implements CarRepository{

    private Connection getConnection(){
        try {
            Class.forName(DB_DRIVER_PATH);
            // jdbc:postgresql://120.230.12.10(ip address example, or localhost):5432/
            // cars?user=postgres&password=postgres  <- cars = database's name
            String dbURL = String.format("%s%s?user=%s&password=%s",
                    DB_ADDRESS, DB_NAME, DB_USERNAME, DB_PASSWORD);
            return DriverManager.getConnection(dbURL);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car save(Car car) {
        try (Connection connection = getConnection()){

            String query = String.format("INSERT INTO car (brand, price, year) VALUES ('%s', %d, %d)", car.getBrand(),
                    car.getPrice(), car.getYear());
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            return car;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car getById(Long id) {
        try (Connection connection = getConnection()){

            String query = String.format("SELECT * FROM car WHERE id=%d", id);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            String brand = resultSet.getString("brand");
            BigDecimal price = resultSet.getBigDecimal("price");
            int year = resultSet.getInt("year");

            return new Car(id,brand,price,year);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car car) {
        try (Connection connection = getConnection()){
            String query = String.format("UPDATE car SET brand =%s, price =%d, year =%d WHERE id =%d",
                    car.getBrand(),car.getPrice(),car.getYear(),car.getId());
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,car.getBrand());
            statement.setBigDecimal(2,car.getPrice());
            statement.setInt(3,car.getYear());
            statement.executeUpdate();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> getAll() {
        //while(ResultSet.next)
        List<Car> cars = new ArrayList<>();
        try (Connection connection = getConnection()){

            String query = "SELECT * FROM car";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String brand = resultSet.getString("brand");
                BigDecimal price = resultSet.getBigDecimal("price");
                int year = resultSet.getInt("year");

                cars.add(new Car(resultSet.getLong("id"),brand,price,year));
            }
            return cars;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection()){

            String query = String.format("DELETE FROM car WHERE id=%d", id);
            Statement statement = connection.createStatement();
            int rowsDeleted = statement.executeUpdate(query);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
