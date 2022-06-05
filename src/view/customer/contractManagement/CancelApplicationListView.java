package view.customer.contractManagement;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;
import domain.customer.Customer;
import service.customer.CustomerCancelApplicationService;
import service.customer.CustomerCancelApplicationServiceImpl;

public class CancelApplicationListView {
	
	// Service
	private CustomerCancelApplicationService customerCancelApplicationService = CustomerCancelApplicationServiceImpl.getInstance();

	public void show(Customer customer) {
		System.out.println("\n[ 계약 해지 신청 목록 ]");
		ArrayList<CancelApplication> cancelApplications = customerCancelApplicationService.getByCustomerId(customer.getId());
		// A. 계약 해지 신청이 0개인 경우
		if(cancelApplications.size() == 0) { System.out.println("계약 해지 신청이 없습니다. 메뉴화면으로 돌아갑니다."); return; }
		for(CancelApplication c : cancelApplications) {
			System.out.println();
			System.out.println(c.toString());
		}
	}
}
