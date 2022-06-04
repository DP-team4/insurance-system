package view.customer.consultationManagement;

import java.util.Scanner;

import domain.customer.Customer;
import service.customer.MyConsultApplicationService;
import service.customer.MyConsultApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class ConsultationRevocationView {
	private final Scanner scanner = ScannerUtility.getScanner();
	private Customer customer;
	
	// View
	private ConsultationListView consultationListView;

	// Service
	private MyConsultApplicationService consultApplicationService = MyConsultApplicationServiceImpl.getInstance();

	public void show(ConsultationListView consultationListView, Customer customer) {
		this.consultationListView = consultationListView;
		this.customer = customer;
		// ��� ��û ��� ȭ��(��� ���̵� �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
		System.out.println("\n[ ���� ��� ��û ��� ]");
		if(consultApplicationService.getByCustomerId(customer.getId()).size() == 0) {
			System.out.println("��û ������ ���� ��� ��û ��Ұ� �Ұ��մϴ�. ���� ȭ������ ���ư��ϴ�."); return;
		}
		System.out.println("���� ��� ��û ����� ��ȸ�Ϸ��� (list)");
		boolean showList = true;
		String id = "";
		while(showList) {
	        System.out.print("����� ��� ���̵� >> "); id = scanner.nextLine().trim();
	        if(id.equals("list")) {
	        	consultationListView.show(customer);
	        } else {
	        	showList = false;
	        }
		}
        System.out.print("\n��� ��û ��Ҹ� �����Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
        boolean blankExist = id.equals("");
        if(input.equals("1")) {
            // A1. ��� ��û ��� ����� �� �Է��� ���
        	if(blankExist) { System.out.println("\n��û ��� ����� ���� �Է����ּ���."); return; }
        	revokeConsultation(id, customer.getId());
        } else {
        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
        	if(!blankExist) {
        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
            	revokeConsultation(id, customer.getId());
        	}
        }
	}

	private void revokeConsultation(String id, String customerId) {
        if(consultApplicationService.deleteMyConsultation(id, customerId)) {
        	System.out.println("�����Ǿ����ϴ�.");
        	consultationListView.show(customer);
        }
        else System.out.println("���� �����Ͽ����ϴ�.");
	}
}
