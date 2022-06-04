package view.customer.contractManagement;

import java.util.Scanner;

import domain.cancelApplication.CancelApplication;
import domain.customer.Customer;
import exception.InvalidInputException;
import service.customer.CancelApplicationManagementService;
import service.customer.CancelApplicationManagementServiceImpl;
import service.customer.ContractManagementService;
import service.customer.ContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;

public class CancelApplicationView {
    private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
    
    // View
	private CancelApplicationListView cancelApplicationListView;

	// Service
	private CancelApplicationManagementService cancelApplicationManagementService = CancelApplicationManagementServiceImpl.getInstance();
    private ContractManagementService contractManagementService = ContractManagementServiceImpl.getInstance();
	
	public void show(ContractListView contractListView, CancelApplicationListView cancellationListView, Customer customer) {
		try {
			this.cancelApplicationListView = cancellationListView;
			
			System.out.println("\n[ ��� ���� ��û ]");
			if(contractManagementService.getCustomerContracts(customer.getId()).size() == 0) {
				System.out.println("������ ������ ���� ���� ��û�� �Ұ��մϴ�. ���� ȭ������ ���ư��ϴ�."); return;
			}
			System.out.println("��� ����� ��ȸ�Ϸ��� (list)");
			boolean showList = true;
			String contractId = "";
			while(showList) {
				System.out.print("������ ��� ID >> "); contractId = getInputAndCheckInvalid();
		        if(contractId.equals("list")) {
		        	contractListView.show(cancellationListView, customer);
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
		if(cancelApplicationManagementService.applyCancellation(cancelApplication)) {
			System.out.println("\n������ �Ϸ� �Ǿ����ϴ�.");
			cancelApplicationListView.show(customer);
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
