package view.customer.carAccidentHandlingManagement;

import java.util.Scanner;

import domain.customer.Customer;
import exception.InvalidInputException;
import service.customer.CustomerCarAccidentHandlingService;
import service.customer.CustomerCarAccidentHandlingServiceImpl;
import view.viewUtility.ScannerUtility;

public class CustomerCarAccidentHandlingRevokeView {
	private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
	
	// View
	private CustomerCarAccidentHandlingListView customerCarAccidentHandlingListView;

	// Service
	private CustomerCarAccidentHandlingService customerCarAccidentHandlingService = CustomerCarAccidentHandlingServiceImpl.getInstance();

	public void show(CustomerCarAccidentHandlingListView customerCarAccidentHandlingListView, Customer customer) {
		try {
			this.customerCarAccidentHandlingListView = customerCarAccidentHandlingListView;
			this.customer = customer;
			// ���ó�� ��û ��� ȭ��(���ó�� ��û ���̵� �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
			System.out.println("\n[ ���ó�� ��û ��� ]");
			if(customerCarAccidentHandlingService.getByCustomerId(customer.getId()).size() == 0) {
				System.out.println("��û ������ ���� ���ó�� ��û ��Ұ� �Ұ��մϴ�. ���� ȭ������ ���ư��ϴ�."); return;
			}
			System.out.println("���ó�� ��û ����� ��ȸ�Ϸ��� (list)");
			boolean showList = true;
			String id = "";
			while(showList) {
		        System.out.print("����� ���ó�� ��û ���̵� >> "); id = getInputAndCheckInvalid();
		        if(id.equals("list")) {
		        	customerCarAccidentHandlingListView.show(customer);
		        } else {
		        	showList = false;
		        }
			}
	        System.out.print("\n���ó�� ��û ��Ҹ� �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	            // A1. ���ó�� ��û ��� ����� �� �Է��� ���
	        	revokeConsultApplication(id, customer.getId());
	        } else {
	        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
            	revokeConsultApplication(id, customer.getId());
	        }
		} catch (InvalidInputException invalidInputException) {
			System.out.println(invalidInputException.getMessage());
		}
	}

	private void revokeConsultApplication(String id, String customerId) {
        if(customerCarAccidentHandlingService.revokeMyCarAccidentHandling(id, customerId)) {
        	System.out.println("�����Ǿ����ϴ�.");
        	customerCarAccidentHandlingListView.show(customer);
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
