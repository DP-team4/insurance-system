package view.customer.carAccidentHandlingManagement;

import java.util.Scanner;

import domain.customer.Customer;
import view.viewUtility.ScannerUtility;

public class CustomerCarAccidentHandlingManageView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
    // View
	private CustomerCarAccidentHandlingApplyView customerCarAccidentHandlingApplyView;
	private CustomerCarAccidentHandlingListView customerCarAccidentHandlingListView;
	private CustomerCarAccidentHandlingRevokeView customerCarAccidentHandlingRevokeView;
    
    public CustomerCarAccidentHandlingManageView() {
    	customerCarAccidentHandlingApplyView = new CustomerCarAccidentHandlingApplyView();
    	customerCarAccidentHandlingListView = new CustomerCarAccidentHandlingListView();
    	customerCarAccidentHandlingRevokeView = new CustomerCarAccidentHandlingRevokeView();
	}
    
	public void show(Customer customer) {
		while(true) {
			System.out.println("\n[ 사고처리 신청 관리 ]");
			System.out.println("사고처리 신청(1), 사고처리 신청 목록 조회(2), 사고처리 신청 취소(3) 메뉴 화면으로 돌아가기(기타)");
	        System.out.print(">> ");
	        String input = scanner.nextLine().trim();
	        switch (input) {
	            case "1": customerCarAccidentHandlingApplyView.show(customerCarAccidentHandlingListView, customer); break;
	            case "2": customerCarAccidentHandlingListView.show(customer); break;
	            case "3": customerCarAccidentHandlingRevokeView.show(customerCarAccidentHandlingListView, customer); break;
	            default: return;
	        }
		}
	}
}
