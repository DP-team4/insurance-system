package view.customer.contractManagement;

import java.util.Scanner;

import domain.cancelApplication.CancelApplication;
import domain.customer.Customer;
import exception.InvalidInputException;
import service.customer.CustomerCancelApplicationService;
import service.customer.CustomerCancelApplicationServiceImpl;
import service.customer.CustomerContractService;
import service.customer.CustomerContractServiceImpl;
import view.viewUtility.ScannerUtility;

public class CustomerCancelApplicationView {
    private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
    
    // View
	private CustomerCancelApplicationListView customerCancelApplicationListView;

	// Service
	private CustomerCancelApplicationService customerCancelApplicationService = CustomerCancelApplicationServiceImpl.getInstance();
    private CustomerContractService customerContractService = CustomerContractServiceImpl.getInstance();
	
	public void show(CustomerContractListView customerContractListView, CustomerCancelApplicationListView cancellationListView, Customer customer) {
		try {
			this.customerCancelApplicationListView = cancellationListView;
			
			System.out.println("\n[ ��� ���� ��û ]");
			if(customerContractService.getCustomerContracts(customer.getId()).size() == 0) {
				System.out.println("������ ������ ���� ���� ��û�� �Ұ��մϴ�. ���� ȭ������ ���ư��ϴ�."); return;
			}
			System.out.println("��� ����� ��ȸ�Ϸ��� (list)");
			boolean showList = true;
			String contractId = "";
			while(showList) {
				System.out.print("������ ��� ID >> "); contractId = getInputAndCheckInvalid();
		        if(contractId.equals("list")) {
		        	customerContractListView.show(cancellationListView, customer);
		        } else {
		        	showList = false;
		        }
			}
			// ���� ��û ȭ��(������ ���̵� �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
	        System.out.print("\n��� ������ �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
			if(input.equals("1")) {
	        	applyCancellation(contractId);
	        } else {
	        	// A2. ������ �Է��� �� ��� ��ư�� Ŭ���� ���
        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? Ȯ��(1) ���(��Ÿ) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
        		applyCancellation(contractId);
	        }
		} catch (InvalidInputException invalidInputException) {
			System.out.println(invalidInputException.getMessage());
		}
	}

	// ���� ��û ������ ������ ��û�Ѵ�
	private void applyCancellation(String contractId) {
		CancelApplication cancelApplication = new CancelApplication();
		cancelApplication.setContractId(contractId);
		if(customerCancelApplicationService.applyCancellation(cancelApplication)) {
			System.out.println("\n������ �Ϸ� �Ǿ����ϴ�.");
			customerCancelApplicationListView.show(customer);
		}
        else System.out.println("\n������û ����. ���� ȭ������ ���ư��ϴ�.");
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
