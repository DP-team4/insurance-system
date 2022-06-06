package service;

import domain.carAccidentHandling.CarAccidentHandling;

import java.util.ArrayList;

public interface CarAccidentHandlingManagementService {
    public ArrayList<CarAccidentHandling> getAll();
    public CarAccidentHandling getById(String id);
    public boolean createCarAccidentHandling(CarAccidentHandling carAccidentHandling);
    public boolean updateCarAccidentHandling(CarAccidentHandling carAccidentHandling);
    public boolean deleteCarAccidentHandling(String id);
}
