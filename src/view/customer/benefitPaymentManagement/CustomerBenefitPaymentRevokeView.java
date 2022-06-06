package view.customer.benefitPaymentManagement;

import java.util.Scanner;

import domain.customer.Customer;
import exception.InvalidInputException;
import service.customer.CustomerBenefitPaymentService;
import service.customer.CustomerBenefitPaymentServiceImpl;
import view.viewUtility.ScannerUtility;

public class CustomerBenefitPaymentRevokeView {
	private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
	
	// View
	private CustomerBenefitPaymentListView customerBenefitPaymentListView;

	// Service
	private CustomerBenefitPaymentService customerBenefitPaymentService = CustomerBenefitPaymentServiceImpl.getInstance();

	public void show(CustomerBenefitPaymentListView customerBenefitPaymentListView, Customer customer) {
		try {
			this.customerBenefitPaymentListView = customerBenefitPaymentListView;
			this.customer = customer;
			// ����� û�� ��� ȭ��(����� û�� ���̵� �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
			System.out.println("\n[ ����� û�� ��� ]");
			if(customerBenefitPaymentService.getByCustomerId(customer.getId()).size() == 0) {
				System.out.println("��û ������ ���� ����� û�� ��Ұ� �Ұ��մϴ�. ���� ȭ������ ���ư��ϴ�."); return;
			}
			System.out.println("����� û�� ����� ��ȸ�Ϸ��� (list)");
			boolean showList = true;
			String id = "";
			while(showList) {
		        System.out.print("����� ����� û�� ���̵� >> "); id = getInputAndCheckInvalid();
		        if(id.equals("list")) {
		        	customerBenefitPaymentListView.show(customer);
		        } else {
		        	showList = false;
		        }
			}
	        System.out.print("\n����� û�� ��Ҹ� �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	            // A1. ����� û�� ��� ����� �� �Է��� ���
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
        if(customerBenefitPaymentService.revokeMyBenefitPayment(id, customerId)) {
        	System.out.println("�����Ǿ����ϴ�.");
        	customerBenefitPaymentListView.show(customer);
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
