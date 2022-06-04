package view.customer.consultApplicationManagement;

import java.time.LocalDateTime;
import java.util.Scanner;

import domain.consultApplication.ConsultApplication;
import domain.customer.Customer;
import service.customer.ConsultApplicationService;
import service.customer.ConsultApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class ConsultApplicationApplyView {
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
	
    // View
	private ConsultApplicationListView consultationListView;
	
	// Service
	private ConsultApplicationService consultApplicationService = ConsultApplicationServiceImpl.getInstance();
	
	public void show(ConsultApplicationListView consultationListView, Customer customer) {
		this.consultationListView = consultationListView;
		this.customer = customer;
		// ��� ��û ȭ��(��� ����, ��� ��¥(��, ��, ��, ��, ��) �Է�â  + 'Ȯ��', '���' ��ư)�� �����ش�
		System.out.println("\n[ ���� ��� ��û ]");
        System.out.print("��� ���� >> "); String content = scanner.nextLine().trim();
        System.out.println("��� ��¥ >> ");
        System.out.print("��(year) : "); String year = scanner.nextLine().trim();
        System.out.print("��(month) : "); String month = scanner.nextLine().trim();
        System.out.print("��(day) : "); String dayOfMonth = scanner.nextLine().trim();
        System.out.print("��(hour) : "); String hour = scanner.nextLine().trim();
        System.out.print("��(minute) : "); String minute = scanner.nextLine().trim();
        System.out.print("\n����� ��û�Ͻðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); String input = scanner.nextLine().trim();
        // ��� ��û ���� �Է� ���θ� üũ�Ѵ�
        boolean blankExist = content.equals("") || year.equals("") || month.equals("") || dayOfMonth.equals("") || hour.equals("");
        if(input.equals("1")) {
            // A1. ��� ��û ����� �� �Է��� ���
        	if(blankExist) { System.out.println("\n��û ����� ���� �Է����ּ���."); return; }
        	applyConsultation(content, year, month, dayOfMonth, hour, minute);
        } else {
        	// A2. ������ ��� �Է��� �� ��� ��ư�� Ŭ���� ���
        	if(!blankExist) {
        		System.out.println("\n�ۼ����� ������ �ֽ��ϴ�. ȭ���� �����ðڽ��ϱ�? ��(1) �ƴϿ�(��Ÿ) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
            	applyConsultation(content, year, month, dayOfMonth, hour, minute);
        	}
        }
	}

	// ��� ��û ������ ������ ��û�Ѵ�
	private void applyConsultation(String content, String year, String month, String dayOfMonth, String hour, String minute) {
		ConsultApplication consultApplication = new ConsultApplication();
        consultApplication.setCustomerId(customer.getId());
        consultApplication.setContent(content);
        try {
            LocalDateTime consultDateTime = LocalDateTime.of(
            		Integer.parseInt(year),
            		Integer.parseInt(month),
            		Integer.parseInt(dayOfMonth),
            		Integer.parseInt(hour),
            		Integer.parseInt(minute)
            );
            consultApplication.setConsultationDate(consultDateTime);
            // "������ �Ϸ� �Ǿ����ϴ�" �޽����� �����ְ� ���� ��� ��û ��Ȳ ȭ���� �����ش�
    		if(consultApplicationService.applyConsultation(consultApplication)) {
    			System.out.println("\n������ �Ϸ� �Ǿ����ϴ�.");
    			consultationListView.show(customer);
    		}
            else System.out.println("\n����û ����. ���� ȭ������ ���ư��ϴ�.");
        } catch (NumberFormatException e) {
        	// A3. ����û���뿡 ��ȿ���� ���� ���� �Է��� ���
        	System.out.println("\n�Է°��� ��ȿ���� �ʽ��ϴ�."); return;
        }
	}
}
