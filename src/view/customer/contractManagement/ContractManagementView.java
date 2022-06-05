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
		boolean contractExist = contractListView.show(cancellationListView, customer);
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
		cancelApplicationView.show(contractListView, cancellationListView, customer);
	}
	
	private void showCancellations() {
		cancellationListView.show(customer);
	}

	private void revokeCancellation() {
		cancellationRevocationView.show(cancellationListView, customer);
	}
}
