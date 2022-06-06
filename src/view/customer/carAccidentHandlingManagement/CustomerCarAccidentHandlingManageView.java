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
			System.out.println("\n[ ���ó�� ��û ���� ]");
			System.out.println("���ó�� ��û(1), ���ó�� ��û ��� ��ȸ(2), ���ó�� ��û ���(3) �޴� ȭ������ ���ư���(��Ÿ)");
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
