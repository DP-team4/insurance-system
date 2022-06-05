package view.customer.contractManagement;

import java.util.ArrayList;

import domain.cancelApplication.CancelApplication;
import domain.customer.Customer;
import service.customer.CancelApplicationManagementService;
import service.customer.CancelApplicationManagementServiceImpl;

public class CancelApplicationListView {
	
	// Service
	private CancelApplicationManagementService cancelApplicationService = CancelApplicationManagementServiceImpl.getInstance();

	public void show(Customer customer) {
		System.out.println("\n[ 가입 상담 신청 목록 ]");
		ArrayList<CancelApplication> cancelApplications = cancelApplicationService.getByCustomerId(customer.getId());
		// A. 가입 상담 신청이 0개인 경우
		if(cancelApplications.size() == 0) { System.out.println("가입 상담 신청이 없습니다. 메뉴화면으로 돌아갑니다."); return; }
		for(CancelApplication c : cancelApplications) {
			System.out.println(c.toString());
			System.out.println();
		}
	}
}
