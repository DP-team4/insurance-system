package view.customer.consultationManagement;

import java.util.Scanner;

import domain.customer.Customer;
import view.viewUtility.ScannerUtility;

public class ConsultationManagementView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
    // View
    private ConsultationApplicationView consultationApplicationView;
    private ConsultationListView consultationListView;
    private ConsultationRevocationView consultationRevocationView;
    
    public ConsultationManagementView() {
    	consultationApplicationView = new ConsultationApplicationView();
    	consultationListView = new ConsultationListView();
    	consultationRevocationView = new ConsultationRevocationView();
	}
    
	public void show(Customer customer) {
		while(true) {
			System.out.println("\n[ ���� ��� ��û ���� ]");
			System.out.println("���� ��� ��û(1), ��� ��û ��� ��ȸ(2), ��� ��û ���(3) �޴� ȭ������ ���ư���(��Ÿ)");
	        System.out.print(">> ");
	        String input = scanner.nextLine().trim();
	        switch (input) {
	            case "1": consultationApplicationView.show(consultationListView, customer); break;
	            case "2": consultationListView.show(customer); break;
	            case "3": consultationRevocationView.show(consultationListView, customer); break;
	            default: return;
	        }
		}
	}
}
