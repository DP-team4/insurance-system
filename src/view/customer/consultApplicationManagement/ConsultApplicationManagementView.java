package view.customer.consultApplicationManagement;

import java.util.Scanner;

import domain.customer.Customer;
import view.viewUtility.ScannerUtility;

public class ConsultApplicationManagementView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
    // View
    private ConsultApplicationApplyView consultApplicationApplyView;
    private ConsultApplicationListView consultApplicationListView;
    private ConsultApplicationRevocationView consultApplicationRevocationView;
    
    public ConsultApplicationManagementView() {
    	consultApplicationApplyView = new ConsultApplicationApplyView();
    	consultApplicationListView = new ConsultApplicationListView();
    	consultApplicationRevocationView = new ConsultApplicationRevocationView();
	}
    
	public void show(Customer customer) {
		while(true) {
			System.out.println("\n[ ���� ��� ��û ���� ]");
			System.out.println("���� ��� ��û(1), ��� ��û ��� ��ȸ(2), ��� ��û ���(3) �޴� ȭ������ ���ư���(��Ÿ)");
	        System.out.print(">> ");
	        String input = scanner.nextLine().trim();
	        switch (input) {
	            case "1": consultApplicationApplyView.show(consultApplicationListView, customer); break;
	            case "2": consultApplicationListView.show(customer); break;
	            case "3": consultApplicationRevocationView.show(consultApplicationListView, customer); break;
	            default: return;
	        }
		}
	}
}
