package view.customer.benefitPaymentManagement;

import java.util.Scanner;

import domain.customer.Customer;
import view.viewUtility.ScannerUtility;

public class CustomerBenefitPaymentManageView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
    // View
	private CustomerBenefitPaymentApplyView customerBenefitPaymentApplyView;
	private CustomerBenefitPaymentListView customerBenefitPaymentListView;
	private CustomerBenefitPaymentRevokeView customerBenefitPaymentRevokeView;
    
    public CustomerBenefitPaymentManageView() {
    	customerBenefitPaymentApplyView = new CustomerBenefitPaymentApplyView();
    	customerBenefitPaymentListView = new CustomerBenefitPaymentListView();
    	customerBenefitPaymentRevokeView = new CustomerBenefitPaymentRevokeView();
	}
    
	public void show(Customer customer) {
		while(true) {
			System.out.println("\n[ 보험금 청구 관리 ]");
			System.out.println("보험금 청구(1), 보험금 청구 목록 조회(2), 보험금 청구 취소(3) 메뉴 화면으로 돌아가기(기타)");
	        System.out.print(">> ");
	        String input = scanner.nextLine().trim();
	        switch (input) {
	            case "1": customerBenefitPaymentApplyView.show(customerBenefitPaymentListView, customer); break;
	            case "2": customerBenefitPaymentListView.show(customer); break;
	            case "3": customerBenefitPaymentRevokeView.show(customerBenefitPaymentListView, customer); break;
	            default: return;
	        }
		}
	}
}
