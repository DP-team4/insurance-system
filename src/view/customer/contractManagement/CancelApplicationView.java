package view.customer.contractManagement;

import java.util.Scanner;

import domain.cancelApplication.CancelApplication;
import domain.customer.Customer;
import service.customer.MyCancelApplicationService;
import service.customer.MyCancelApplicationServiceImpl;
import service.customer.MyContractManagementService;
import service.customer.MyContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;

public class CancelApplicationView {
    private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
    
    // View
	private CancellationListView cancellationListView;

	// Service
	private MyCancelApplicationService cancelApplicationService = MyCancelApplicationServiceImpl.getInstance();
    private MyContractManagementService myContractManagementService = MyContractManagementServiceImpl.getInstance();
	
	public void show(ContractListView contractListView, CancellationListView cancellationListView, Customer customer) {
		this.cancellationListView = cancellationListView;
		
		System.out.println("\n[ ��� ���� ��û ]");
		if(myContractManagementService.getCustomerContracts(customer.getId()).size() == 0) {
			System.out.println("������ ������ ���� ���� ��û�� �Ұ��մϴ�. ���� ȭ������ ���ư��ϴ�."); return;
		}
		System.out.println("��� ����� ��ȸ�Ϸ��� (list)");
		boolean showList = true;
		String contractId = "";
		while(showList) {
			System.out.print("������ ��� ID >> "); contractId = scanner.nextLine().trim();
	        if(contractId.equals("list")) {
	        	contractListView.show(cancellationListView, customer);
	        } else {
	        	showList = false;
	        }
		}
		// ���� ��û ȭ��(������ ���̵� �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
        System.out.print("\n��� ������ �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
		if(input.equals("1")) {
	        // A1. ���� ��û ����� �� �Է��� ���
			if(contractId.equals("")) { System.out.println("��û ����� �Է����ּ���."); return; }
        	applyCancellation(contractId);
        } else {
        	// A2. ������ �Է��� �� ��� ��ư�� Ŭ���� ���
        	if(!contractId.equals("")) {
        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? Ȯ��(1) ���(��Ÿ) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
        		applyCancellation(contractId);
        	}
        }
		
	}

	// ���� ��û ������ ������ ��û�Ѵ�
	private void applyCancellation(String contractId) {
		CancelApplication cancelApplication = new CancelApplication();
		cancelApplication.setContractId(contractId);
		if(cancelApplicationService.applyCancellation(cancelApplication)) {
			System.out.println("\n������ �Ϸ� �Ǿ����ϴ�.");
			cancellationListView.show(customer);
		}
        else System.out.println("\n������û ����. ���� ȭ������ ���ư��ϴ�.");
	}
}
