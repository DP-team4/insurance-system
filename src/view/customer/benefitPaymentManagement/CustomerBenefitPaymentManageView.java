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
			System.out.println("\n[ ����� û�� ���� ]");
			System.out.println("����� û��(1), ����� û�� ��� ��ȸ(2), ����� û�� ���(3) �޴� ȭ������ ���ư���(��Ÿ)");
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
