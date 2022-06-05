package view.salesManagement;

import java.util.ArrayList;
import java.util.Scanner;

import domain.consultApplication.ConsultApplication;
import domain.consultApplication.ConsultApplicationState;
import domain.customer.Customer;
import exception.InvalidInputException;
import service.SalesService;
import service.SalesServiceImpl;
import view.viewUtility.ScannerUtility;

public class ConsultApplicationListView {
	
	private final Scanner scanner = ScannerUtility.getScanner();
	private final SalesService salesService = SalesServiceImpl.getInstance();
	
	public ConsultApplicationListView() {}

	public void show() {
		while(true) {
			try {
				System.out.println("\n================상담신청 목록 화면================");
				this.showConsultApplicationList();
				this.showConsultApplicationManage();
				System.out.println("상담신청 목록 화면입니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
				String input = scanner.nextLine().trim();
				if(!input.equals("1")) break;
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void showConsultApplicationList() {
		ArrayList<ConsultApplication> consultApplications = salesService.getAllConsultApplication();
		if(consultApplications.size() < 1) System.out.println("현재 저장된 가입상담신청이 없습니다.");
		else consultApplications.forEach(i -> {
			System.out.println(i);
            System.out.println();
		});
	}
	
	private void showConsultApplicationManage() throws InvalidInputException {
		System.out.print("작업할 상담신청의 ID를 입력하세요 >> ");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
		ConsultApplication consultApplication = salesService.getConsultApplication(input);
		if(consultApplication == null) {
			throw new InvalidInputException("유요하지 않은 상담신청 ID 입니다.");
		}
		
		while(true) {
		    System.out.println("////// 해당 상담신청에 대한 작업을 시작합니다. //////");
		    System.out.println("승인(1), 거절(2), 완료(3), 고객 정보 보기(4), 뒤로가기 (0)");
		    input = scanner.nextLine().trim();
	        if(input.equals("0")) break;
	        switch (input) {
	            case "1":
	                showChangeStateView(consultApplication, ConsultApplicationState.ACCEPTED);
	                break;
	            case "2":
	            	showChangeStateView(consultApplication, ConsultApplicationState.REJECTED);
	                break;
	            case "3":
	            	showChangeStateView(consultApplication, ConsultApplicationState.COMPLETED);
	                break;
	            case "4":
	            	showCustomerInfoView(consultApplication);
	            	break;
	            case "0":
	            	break;
	            default:
	                System.out.println("잘못된 입력입니다.");
	                break;
	        }
		}
	}

	private void showCustomerInfoView(ConsultApplication consultApplication) {
		System.out.println("[고객 정보]");
		String customerId = consultApplication.getCustomerId();
		Customer customer = salesService.getCustomer(customerId);
		System.out.println(customer.toStringBySecurity());
	}

	private void showChangeStateView(ConsultApplication consultApplication, ConsultApplicationState state) {
		System.out.println(consultApplication.getId() + "의 상태를 변경합니다.");
		consultApplication.setState(state);
		salesService.updateConsultApplication(consultApplication);
		System.out.println(consultApplication);
	}

}
