package view.customer.carAccidentHandlingManagement;

import java.util.ArrayList;

import domain.carAccidentHandling.AccidentCar;
import domain.carAccidentHandling.AccidentPerson;
import domain.carAccidentHandling.CarAccidentHandling;
import domain.customer.Customer;
import service.customer.CustomerCarAccidentHandlingService;
import service.customer.CustomerCarAccidentHandlingServiceImpl;

public class CustomerCarAccidentHandlingListView {

	// Service
	private final CustomerCarAccidentHandlingService customerCarAccidentHandlingService = CustomerCarAccidentHandlingServiceImpl.getInstance();
	
	public void show(Customer customer) {
		System.out.println("\n[ 사고처리 신청 목록 ]");
		ArrayList<CarAccidentHandling> carAccidentHandlings = customerCarAccidentHandlingService.getByCustomerId(customer.getId());
		// A. 사고처리 신청이 0개인 경우
		if(carAccidentHandlings.size() == 0) { System.out.println("사고처리 신청이 없습니다. 이전화면으로 돌아갑니다."); return; }
		for(CarAccidentHandling c : carAccidentHandlings) {
			System.out.println("사고 처리 ID: " + c.getId());
			System.out.println("사고 장소: " + c.getAccidentLocation());
			System.out.println("사고 내용: " + c.getAccidentContent());
			System.out.println("사고 일시: " + c.getAccidentDate());
			System.out.println("접수 일시: " + c.getRequestDate());
			System.out.println("신청 상태: " + c.getState());
	        System.out.println("\n< 사고 차량 정보 >");
			for(AccidentCar car : c.getAccidentCars()) {
				System.out.println("차량 번호 : " + car.getCarNo());
	        	System.out.println("차주 이름 : " + car.getOwnerName());
	        	System.out.println("차주 번호 : " + car.getOwnerPhoneNo());
	        	System.out.println("차량 수리점 : " + car.getVisitedShopName());
	        	System.out.println();
			}
	        System.out.println("< 사고 관련자 정보 >");
			for(AccidentPerson person : c.getAccidentPeople()) {
				System.out.print("이름: " + person.getName());
	        	System.out.print("전화번호: " + person.getPhoneNo());
	        	System.out.print("방문병원: " + person.getVisitedHospitalName());
	        	System.out.println();
			}
			System.out.println();
		}
	}

}
