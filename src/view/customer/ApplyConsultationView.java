package view.customer;

import java.time.LocalDateTime;
import java.util.Scanner;

import domain.consultApplication.ConsultApplication;
import domain.customer.Customer;
import service.ConsultApplicationService;
import service.ConsultApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class ApplyConsultationView {
    private final Scanner scanner = ScannerUtility.getScanner();
	
	// Service
	private ConsultApplicationService consultApplicationService = ConsultApplicationServiceImpl.getInstance();
	
	public void show(Customer customer) {
		if(customer == null) {
			System.out.println("�α����� �ʿ��� ����Դϴ�."); return;
		}
		System.out.println("\n[ ���� ��� ��û ]");
		ConsultApplication consultApplication = new ConsultApplication();
        consultApplication.setCustomerId(customer.getId());
        try {
	        System.out.print("��� ���� >> "); consultApplication.setContent(scanner.nextLine().trim());
	        System.out.println("��� ��¥ >> ");
            System.out.print("��(year) : "); int year = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("��(month) : "); int month = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("��(day) : "); int dayOfMonth = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("��(hour) : "); int hour = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("��(minute) : "); int minute = Integer.parseInt(scanner.nextLine().trim());
            LocalDateTime consultDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
            consultApplication.setConsultationDate(consultDateTime);
    		if(consultApplicationService.applyConsultation(consultApplication)) System.out.println("����û ����. �޴��� ���ư��ϴ�.");
            else System.out.println("����û ����. �޴��� ���ư��ϴ�.");
        } catch (NumberFormatException e) {
            System.out.println("�߸��� �Է��Դϴ�.");
        }
	}
}
