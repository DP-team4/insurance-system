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
				System.out.println("\n================����û ��� ȭ��================");
				this.showConsultApplicationList();
				this.showConsultApplicationManage();
				System.out.println("����û ��� ȭ���Դϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
				String input = scanner.nextLine().trim();
				if(!input.equals("1")) break;
			} catch (InvalidInputException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void showConsultApplicationList() {
		ArrayList<ConsultApplication> consultApplications = salesService.getAllConsultApplication();
		if(consultApplications.size() < 1) System.out.println("���� ����� ���Ի���û�� �����ϴ�.");
		else consultApplications.forEach(i -> {
			System.out.println(i);
            System.out.println();
		});
	}
	
	private void showConsultApplicationManage() throws InvalidInputException {
		System.out.print("�۾��� ����û�� ID�� �Է��ϼ��� >> ");
		System.out.print(">> ");
        String input = scanner.nextLine().trim();
		ConsultApplication consultApplication = salesService.getConsultApplication(input);
		if(consultApplication == null) {
			throw new InvalidInputException("�������� ���� ����û ID �Դϴ�.");
		}
		
		while(true) {
		    System.out.println("////// �ش� ����û�� ���� �۾��� �����մϴ�. //////");
		    System.out.println("����(1), ����(2), �Ϸ�(3), �� ���� ����(4), �ڷΰ��� (0)");
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
	                System.out.println("�߸��� �Է��Դϴ�.");
	                break;
	        }
		}
	}

	private void showCustomerInfoView(ConsultApplication consultApplication) {
		System.out.println("[�� ����]");
		String customerId = consultApplication.getCustomerId();
		Customer customer = salesService.getCustomer(customerId);
		System.out.println(customer.toStringBySecurity());
	}

	private void showChangeStateView(ConsultApplication consultApplication, ConsultApplicationState state) {
		System.out.println(consultApplication.getId() + "�� ���¸� �����մϴ�.");
		consultApplication.setState(state);
		salesService.updateConsultApplication(consultApplication);
		System.out.println(consultApplication);
	}

}
