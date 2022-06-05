package view.customer.consultApplicationManagement;

import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;
import domain.customer.Customer;
import service.customer.ConsultApplicationService;
import service.customer.ConsultApplicationServiceImpl;

public class ConsultApplicationListView {
	
	// Service
	private ConsultApplicationService consultApplicationService = ConsultApplicationServiceImpl.getInstance();

	public void show(Customer customer) {
		System.out.println("\n[ 가입 상담 신청 목록 ]");
		ArrayList<ConsultApplication> consultApplications = consultApplicationService.getByCustomerId(customer.getId());
		// A. 가입 상담 신청이 0개인 경우
		if(consultApplications.size() == 0) { System.out.println("가입 상담 신청이 없습니다. 메뉴화면으로 돌아갑니다."); return; }
		for(ConsultApplication c : consultApplications) {
			System.out.println(c.toStringForCustomer());
			System.out.println();
		}
	}
}
