package view.customer.carAccidentHandlingManagement;

import java.util.ArrayList;

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
			System.out.println(c.toString());
			System.out.println();
		}
	}

}
