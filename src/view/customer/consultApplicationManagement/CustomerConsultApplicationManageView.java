package view.customer.consultApplicationManagement;

import java.util.Scanner;

import domain.customer.Customer;
import view.viewUtility.ScannerUtility;

public class CustomerConsultApplicationManageView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
    // View
    private CustomerConsultApplicationApplyView customerConsultApplicationApplyView;
    private CustomerConsultApplicationListView customerConsultApplicationListView;
    private CustomerConsultApplicationRevokeView customerConsultApplicationRevokeView;
    
    public CustomerConsultApplicationManageView() {
    	customerConsultApplicationApplyView = new CustomerConsultApplicationApplyView();
    	customerConsultApplicationListView = new CustomerConsultApplicationListView();
    	customerConsultApplicationRevokeView = new CustomerConsultApplicationRevokeView();
	}
    
	public void show(Customer customer) {
		while(true) {
			System.out.println("\n[ 가입 상담 신청 관리 ]");
			System.out.println("가입 상담 신청(1), 상담 신청 목록 조회(2), 상담 신청 취소(3) 메뉴 화면으로 돌아가기(기타)");
	        System.out.print(">> ");
	        String input = scanner.nextLine().trim();
	        switch (input) {
	            case "1": customerConsultApplicationApplyView.show(customerConsultApplicationListView, customer); break;
	            case "2": customerConsultApplicationListView.show(customer); break;
	            case "3": customerConsultApplicationRevokeView.show(customerConsultApplicationListView, customer); break;
	            default: return;
	        }
		}
	}
}
