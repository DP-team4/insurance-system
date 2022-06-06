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
			System.out.println("\n[ ���� ��� ��û ���� ]");
			System.out.println("���� ��� ��û(1), ��� ��û ��� ��ȸ(2), ��� ��û ���(3) �޴� ȭ������ ���ư���(��Ÿ)");
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
