package view.customer.contractManagement;

import java.util.Scanner;

import domain.customer.Customer;
import service.customer.CancelApplicationManagementService;
import service.customer.CancelApplicationManagementServiceImpl;
import view.viewUtility.ScannerUtility;

public class CancelApplicationRevocationView {
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
    
    // View
    private CancelApplicationListView cancellationListView;

	// Service
	private CancelApplicationManagementService cancelApplicationService = CancelApplicationManagementServiceImpl.getInstance();

	public void show(CancelApplicationListView cancellationListView, Customer customer) {
		// ��� ���� ��û ��� ȭ��(���� ���̵� �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
		this.cancellationListView = cancellationListView;
		this.customer = customer;
		
		System.out.println("\n[ ��� ���� ��û ��� ]");
		if(cancelApplicationService.getByCustomerId(customer.getId()).size() == 0) {
			System.out.println("��û ������ ���� ���� ��û ��Ұ� �Ұ��մϴ�. ���� ȭ������ ���ư��ϴ�."); return;
		}
		System.out.println("��� ���� ��û ����� ��ȸ�Ϸ��� (list)");
		boolean showList = true;
		String id = "";
		while(showList) {
	        System.out.print("����� ���� ���̵� >> "); id = scanner.nextLine().trim();
	        if(id.equals("list")) {
	        	cancellationListView.show(customer);
	        } else {
	        	showList = false;
	        }
		}
        System.out.print("\n���� ��û ��Ҹ� �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
        boolean blankExist = id.equals("");
        if(input.equals("1")) {
            // A1. ���� ��û ��� ����� �� �Է��� ���
        	if(blankExist) { System.out.println("\n��û ��� ����� ���� �Է����ּ���."); return; }
        	revokeCancellation(id, customer.getId());
        } else {
        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
        	if(!blankExist) {
        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
        		revokeCancellation(id, customer.getId());
        	}
        }
	}

	private void revokeCancellation(String id, String customerId) {
        if(cancelApplicationService.deleteMyCancellation(id, customerId)) {
        	System.out.println("�����Ǿ����ϴ�.");
        	cancellationListView.show(customer);
        }
        else System.out.println("���� �����Ͽ����ϴ�.");
	}

}
