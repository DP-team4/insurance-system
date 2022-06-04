package view.customer.consultationManagement;

import java.time.LocalDateTime;
import java.util.Scanner;

import domain.consultApplication.ConsultApplication;
import domain.customer.Customer;
import service.customer.MyConsultApplicationService;
import service.customer.MyConsultApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class ConsultationApplicationView {
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
	
    // View
	private ConsultationListView consultationListView;
	
	// Service
	private MyConsultApplicationService consultApplicationService = MyConsultApplicationServiceImpl.getInstance();
	
	public void show(ConsultationListView consultationListView, Customer customer) {
		this.consultationListView = consultationListView;
		this.customer = customer;
		// 상담 신청 화면(상담 내용, 상담 날짜(년, 월, 일, 시, 분) 입력창  + '확인', '취소' 버튼)을 보여준다
		System.out.println("\n[ 가입 상담 신청 ]");
        System.out.print("상담 내용 >> "); String content = scanner.nextLine().trim();
        System.out.println("상담 날짜 >> ");
        System.out.print("년(year) : "); String year = scanner.nextLine().trim();
        System.out.print("월(month) : "); String month = scanner.nextLine().trim();
        System.out.print("일(day) : "); String dayOfMonth = scanner.nextLine().trim();
        System.out.print("시(hour) : "); String hour = scanner.nextLine().trim();
        System.out.print("분(minute) : "); String minute = scanner.nextLine().trim();
        System.out.print("\n상담을 신청하시겠습니까? 예(1) 아니오(기타) >> "); String input = scanner.nextLine().trim();
        // 상담 신청 내용 입력 여부를 체크한다
        boolean blankExist = content.equals("") || year.equals("") || month.equals("") || dayOfMonth.equals("") || hour.equals("");
        if(input.equals("1")) {
            // A1. 상담 신청 양식을 덜 입력한 경우
        	if(blankExist) { System.out.println("\n신청 양식을 전부 입력해주세요."); return; }
        	applyConsultation(content, year, month, dayOfMonth, hour, minute);
        } else {
        	// A2. 내용을 모두 입력한 후 취소 버튼을 클릭할 경우
        	if(!blankExist) {
        		System.out.println("\n작성중인 내용이 있습니다. 화면을 나가시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
        		if(input.equals("1")) return;
            	applyConsultation(content, year, month, dayOfMonth, hour, minute);
        	}
        }
	}

	// 상담 신청 정보를 저장을 요청한다
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
            // "접수가 완료 되었습니다" 메시지를 보여주고 가입 상담 신청 현황 화면을 보여준다
    		if(consultApplicationService.applyConsultation(consultApplication)) {
    			System.out.println("\n접수가 완료 되었습니다.");
    			consultationListView.show(customer);
    		}
            else System.out.println("\n상담신청 실패. 이전 화면으로 돌아갑니다.");
        } catch (NumberFormatException e) {
        	// A3. 상담신청내용에 유효하지 않은 값을 입력한 경우
        	System.out.println("\n입력값이 유효하지 않습니다."); return;
        }
	}
}
