package view.customer.contractManagement;

import java.util.Scanner;

import domain.customer.Customer;
import view.viewUtility.ScannerUtility;

public class ContractManagementView {    
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
    
    // View
    private ContractListView contractListView = new ContractListView();
	private CancelApplicationView cancelApplicationView = new CancelApplicationView();
	private CancelApplicationListView cancellationListView = new CancelApplicationListView();
	private CancelApplicationRevocationView cancellationRevocationView = new CancelApplicationRevocationView();
    
	public void show(Customer customer) {
		this.customer = customer;
		while(true) {
			System.out.println("\n[ ���� �����ǰ ���� ]");
			System.out.println("��� ��� ��ȸ(1), ��� ���� ��û(2), ���� ��û ��� ��ȸ(3), ���� ��û ����(4) �޴� ȭ������ ���ư���(��Ÿ)");
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
		boolean contractExist = contractListView.show(cancellationListView, customer);
		if(contractExist) {
			System.out.println("\n���� ��û�ϱ�(1) �޴� ȭ������ ���ư���(��Ÿ)");
			System.out.print(">> ");
			switch(scanner.nextLine().trim()) {
				case "1" : applyCancellation(); break;
				default: break;
			}
		}
	}
	
	private void applyCancellation() {
		cancelApplicationView.show(contractListView, cancellationListView, customer);
	}
	
	private void showCancellations() {
		cancellationListView.show(customer);
	}

	private void revokeCancellation() {
		cancellationRevocationView.show(cancellationListView, customer);
	}
}
