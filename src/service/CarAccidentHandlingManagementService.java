package service;

import domain.carAccidentHandling.AccidentCar;
import domain.carAccidentHandling.AccidentPerson;
import domain.carAccidentHandling.CarAccidentHandling;

import java.util.ArrayList;

public interface CarAccidentHandlingManagementService {
    // 사고처리 신청 목록 요청
    // 특정 사고처리 신청 건 조회
    // 특정 사고처리 신청 건 상태 변경 - 처리됨
    // 특정 사고처리 신청 건 상태 변경 - 반려됨
    public ArrayList<CarAccidentHandling> getAll();
    public CarAccidentHandling getById(String id);
    public boolean createCarAccidentHandling(CarAccidentHandling carAccidentHandling);
    public boolean updateCarAccidentHandling(CarAccidentHandling carAccidentHandling);
    public boolean deleteCarAccidentHandling(String id);
}
