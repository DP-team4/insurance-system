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
			System.out.println("로그인이 필요한 기능입니다."); return;
		}
		System.out.println("\n[ 가입 상담 신청 ]");
		ConsultApplication consultApplication = new ConsultApplication();
        consultApplication.setCustomerId(customer.getId());
        try {
	        System.out.print("상담 내용 >> "); consultApplication.setContent(scanner.nextLine().trim());
	        System.out.println("상담 날짜 >> ");
            System.out.print("년(year) : "); int year = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("월(month) : "); int month = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("일(day) : "); int dayOfMonth = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("시(hour) : "); int hour = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("분(minute) : "); int minute = Integer.parseInt(scanner.nextLine().trim());
            LocalDateTime consultDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
            consultApplication.setConsultationDate(consultDateTime);
    		if(consultApplicationService.applyConsultation(consultApplication)) System.out.println("상담신청 성공. 메뉴로 돌아갑니다.");
            else System.out.println("상담신청 실패. 메뉴로 돌아갑니다.");
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
        }
	}
}
