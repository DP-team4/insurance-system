package view.customer.consultApplicationManagement;

import java.util.Scanner;

import domain.customer.Customer;
import exception.InvalidInputException;
import service.customer.CustomerConsultApplicationService;
import service.customer.CustomerConsultApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class ConsultApplicationRevocationView {
	private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
	
	// View
	private ConsultApplicationListView consultApplicationListView;

	// Service
	private CustomerConsultApplicationService customerConsultApplicationService = CustomerConsultApplicationServiceImpl.getInstance();

	public void show(ConsultApplicationListView consultationListView, Customer customer) {
		try {
			this.consultApplicationListView = consultationListView;
			this.customer = customer;
			// ��� ��û ��� ȭ��(��� ���̵� �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
			System.out.println("\n[ ���� ��� ��û ��� ]");
			if(customerConsultApplicationService.getByCustomerId(customer.getId()).size() == 0) {
				System.out.println("��û ������ ���� ��� ��û ��Ұ� �Ұ��մϴ�. ���� ȭ������ ���ư��ϴ�."); return;
			}
			System.out.println("���� ��� ��û ����� ��ȸ�Ϸ��� (list)");
			boolean showList = true;
			String id = "";
			while(showList) {
		        System.out.print("����� ��� ���̵� >> "); id = getInputAndCheckInvalid();
		        if(id.equals("list")) {
		        	consultationListView.show(customer);
		        } else {
		        	showList = false;
		        }
			}
	        System.out.print("\n��� ��û ��Ҹ� �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	            // A1. ��� ��û ��� ����� �� �Է��� ���
	        	revokeConsultation(id, customer.getId());
	        } else {
	        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
            	revokeConsultation(id, customer.getId());
	        }
		} catch (InvalidInputException invalidInputException) {
			System.out.println(invalidInputException.getMessage());
		}
	}

	private void revokeConsultation(String id, String customerId) {
        if(customerConsultApplicationService.deleteMyConsultation(id, customerId)) {
        	System.out.println("�����Ǿ����ϴ�.");
        	consultApplicationListView.show(customer);
        }
        else System.out.println("���� �����Ͽ����ϴ�.");
	}

    // �α��� ���� �Է� ���θ� üũ�Ѵ�
	private String getInputAndCheckInvalid() throws InvalidInputException {
		String input = scanner.nextLine().trim();
		// ����
		if(input.equals("")) {
			throw new InvalidInputException("�Է°��� �����ϴ�.");
		}
		return input;
	}
}
