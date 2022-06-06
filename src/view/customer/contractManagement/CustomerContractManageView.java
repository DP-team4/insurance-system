package view.customer.contractManagement;

import java.util.Scanner;

import domain.customer.Customer;
import view.viewUtility.ScannerUtility;

public class CustomerContractManageView {    
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
    
    // View
    private CustomerContractListView customerContractListView = new CustomerContractListView();
	private CustomerCancelApplicationView customerCancelApplicationView = new CustomerCancelApplicationView();
	private CustomerCancelApplicationListView customerCancelApplicationListView = new CustomerCancelApplicationListView();
	private CustomerCancelApplicationRevokeView customerCancelApplicationRevokeView = new CustomerCancelApplicationRevokeView();
    
	public void show(Customer customer) {
		this.customer = customer;
		while(true) {
			System.out.println("\n[ 나의 보험상품 관리 ]");
			System.out.println("계약 목록 조회(1), 계약 해지 신청(2), 해지 신청 목록 조회(3), 해지 신청 삭제(4) 메뉴 화면으로 돌아가기(기타)");
	        System.out.print(">> ");
	        String input = scanner.nextLine().trim();
	        switch (input) {
	            case "1": showContracts(); break;
	            case "2": applyCancellation(); break;
	            case "3": showCancellations(); break;
	            case "4": revokeCancellation(); break;
	            default: return;
	        }
		}
	}
	
	private void showContracts() {
		boolean contractExist = customerContractListView.show(customerCancelApplicationListView, customer);
		if(contractExist) {
			System.out.println("\n해지 신청하기(1) 메뉴 화면으로 돌아가기(기타)");
			System.out.print(">> ");
			switch(scanner.nextLine().trim()) {
				case "1" : applyCancellation(); break;
				default: break;
			}
		}
	}
	
	private void applyCancellation() {
		customerCancelApplicationView.show(customerContractListView, customerCancelApplicationListView, customer);
	}
	
	private void showCancellations() {
		customerCancelApplicationListView.show(customer);
	}

	private void revokeCancellation() {
		customerCancelApplicationRevokeView.show(customerCancelApplicationListView, customer);
	}
}
