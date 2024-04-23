package app.constants;

public interface Constants {
    // http://120.230.12.10(ip address example):8080/cars?id=1 request to BD    <- cars = endpoint
    // jdbc:postgresql://120.230.12.10(ip address example, or localhost):5432/cars?user=postgres&password=postgres  <- cars = database's name
    String DB_DRIVER_PATH = "org.postgresql.Driver";
    String DB_ADDRESS = "jdbc:postgresql://localhost:5432/";
    String DB_NAME = "cars";
    String DB_USERNAME = "postgres";
    String DB_PASSWORD = "postgres";
}
