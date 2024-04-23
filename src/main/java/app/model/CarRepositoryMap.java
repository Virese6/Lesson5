package app.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRepositoryMap implements CarRepository {
    private Map<Long,Car> database = new HashMap<>();
    private long currentId = 0;

    public CarRepositoryMap() {
        save(new Car("Volkswagen", new BigDecimal(10_000), 2010));
        save(new Car("Mazda", new BigDecimal(30_000), 2015));
        save(new Car("Honda", new BigDecimal(50_000), 2020));
    }

    @Override
    public Car save(Car car) {
        car.setId(++currentId);
        database.put(currentId, car);
        return car;
    }

    @Override
    public Car getById(Long id) {
        return database.get(id);
    }

    @Override
    public List<Car> getAll() {
        return database.values().stream().toList();
    }

    @Override
    public void update(Car car) {
        Car foundCar = database.get(car.getId());

        if(foundCar !=null){
            foundCar.setPrice(car.getPrice());
        }

    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }
}
