package view.customer.contractManagement;

import java.util.Scanner;

import domain.customer.Customer;
import exception.InvalidInputException;
import service.customer.CustomerCancelApplicationService;
import service.customer.CustomerCancelApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class CustomerCancelApplicationRevokeView {
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
    
    // View
    private CustomerCancelApplicationListView customerCancelApplicationListView;

	// Service
	private CustomerCancelApplicationService customerCancelApplicationService = CustomerCancelApplicationServiceImpl.getInstance();

	public void show(CustomerCancelApplicationListView cancellationListView, Customer customer) {
		try {
			// ��� ���� ��û ��� ȭ��(���� ���̵� �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
			this.customerCancelApplicationListView = cancellationListView;
			this.customer = customer;
			
			System.out.println("\n[ ��� ���� ��û ��� ]");
			if(customerCancelApplicationService.getByCustomerId(customer.getId()).size() == 0) {
				System.out.println("��û ������ ���� ���� ��û ��Ұ� �Ұ��մϴ�. ���� ȭ������ ���ư��ϴ�."); return;
			}
			System.out.println("��� ���� ��û ����� ��ȸ�Ϸ��� (list)");
			boolean showList = true;
			String id = "";
			while(showList) {
		        System.out.print("����� ���� ���̵� >> "); id = getInputAndCheckInvalid();
		        if(id.equals("list")) {
		        	cancellationListView.show(customer);
		        } else {
		        	showList = false;
		        }
			}
	        System.out.print("\n���� ��û ��Ҹ� �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	        	revokeCancellation(id, customer.getId());
	        } else {
	        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
        		revokeCancellation(id, customer.getId());
	        }
		} catch (InvalidInputException invalidInputException) {
        	// ��ȿ���� ���� ���� �Է��� ���
            System.out.println(invalidInputException.getMessage());
		}
	}

	private void revokeCancellation(String id, String customerId) {
        if(customerCancelApplicationService.deleteMyCancellation(id, customerId)) {
        	System.out.println("�����Ǿ����ϴ�.");
        	customerCancelApplicationListView.show(customer);
        }
        else System.out.println("���� �����Ͽ����ϴ�.");
	}

    // ���� ��û ��� ��� �Է� ���θ� üũ�Ѵ�
	private String getInputAndCheckInvalid() throws InvalidInputException {
		String input = scanner.nextLine().trim();
		// ����
		if(input.equals("")) {
			throw new InvalidInputException("�Է°��� �����ϴ�.");
		}
		return input;
	}
}
