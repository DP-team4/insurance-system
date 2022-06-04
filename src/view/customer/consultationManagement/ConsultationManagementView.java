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
			System.out.println("\n[ 가입 상담 신청 관리 ]");
			System.out.println("가입 상담 신청(1), 상담 신청 목록 조회(2), 상담 신청 취소(3) 메뉴 화면으로 돌아가기(기타)");
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
