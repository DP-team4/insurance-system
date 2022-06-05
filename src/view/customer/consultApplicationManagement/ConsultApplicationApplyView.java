package view.customer.consultApplicationManagement;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Scanner;

import domain.consultApplication.ConsultApplication;
import domain.customer.Customer;
import exception.InvalidConsultDateTimeException;
import exception.InvalidInputException;
import service.customer.ConsultApplicationService;
import service.customer.ConsultApplicationServiceImpl;
import view.viewUtility.ScannerUtility;

public class ConsultApplicationApplyView {
    private final Scanner scanner = ScannerUtility.getScanner();
    private Customer customer;
	
    // View
	private ConsultApplicationListView consultApplicationListView;
	
	// Service
	private ConsultApplicationService consultApplicationService = ConsultApplicationServiceImpl.getInstance();
	
	public void show(ConsultApplicationListView consultationListView, Customer customer) {
		try {
			this.consultApplicationListView = consultationListView;
			this.customer = customer;
			// 상담 신청 화면(상담 내용, 상담 날짜(년, 월, 일, 시, 분) 입력창  + '확인', '취소' 버튼)을 보여준다
			System.out.println("\n[ 가입 상담 신청 ]");
	        System.out.print("상담 내용 >> "); String content = getInputAndCheckInvalid();
	        System.out.println("상담 날짜 (신청은 최소 하루 후부터 가능합니다) >> ");
	        System.out.print("년(year) : "); String year = getInputAndCheckInvalid();
	        System.out.print("월(month) : "); String month = getInputAndCheckInvalid()
	        System.out.print("일(day) : "); String dayOfMonth = getInputAndCheckInvalid()
	        System.out.print("시(hour) : "); String hour = getInputAndCheckInvalid()
	        System.out.print("분(minute) : "); String minute = getInputAndCheckInvalid()
	        System.out.print("\n상담을 신청하시겠습니까? 예(1) 아니오(기타) >> "); String input = scanner.nextLine().trim();
	        if(input.equals("1")) {
	            // A1. 상담 신청 양식을 덜 입력한 경우
	        	applyConsultation(content, year, month, dayOfMonth, hour, minute);
	        } else {
	        	// A2. 내용을 모두 입력한 후 취소 버튼을 클릭할 경우
	    		System.out.println("\n작성중인 내용이 있습니다. 화면을 나가시겠습니까? 예(1) 아니오(기타) >> "); input = scanner.nextLine().trim();
	    		if(input.equals("1")) return;
	        	applyConsultation(content, year, month, dayOfMonth, hour, minute);
	        }
		} catch (InvalidInputException invalidInputException) {
        	System.out.println(invalidInputException.getMessage());
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
            checkInvalidDate(consultDateTime);
            // "접수가 완료 되었습니다" 메시지를 보여주고 가입 상담 신청 현황 화면을 보여준다
    		if(consultApplicationService.applyConsultation(consultApplication)) {
    			System.out.println("\n접수가 완료 되었습니다.");
    			consultApplicationListView.show(customer);
    		}
            else System.out.println("\n상담신청 실패. 이전 화면으로 돌아갑니다.");
        } catch (NumberFormatException e) {
        	// A3. 상담신청내용에 유효하지 않은 값을 입력한 경우
        	System.out.println("입력값이 유효하지 않습니다.");
        } catch (DateTimeException dateTimeException) {
        	System.out.println("날짜가 올바르지 않습니다.");
        } catch (InvalidConsultDateTimeException invalidConsultDateTimeException) {
        	System.out.println(invalidConsultDateTimeException.getMessage());
        }
	}
	
	private void checkInvalidDate(LocalDateTime consultDateTime) throws InvalidConsultDateTimeException {
		// 신청날짜가 최소 신청 가능 날짜(현재 + 1일)보다 이전일 때
		if(consultDateTime.isBefore(LocalDateTime.now().plusDays(1))) {
			throw new InvalidConsultDateTimeException("신청할 수 없는 날짜입니다.");
		}
	}

    // 로그인 내용 입력 여부를 체크한다
	private String getInputAndCheckInvalid() throws InvalidInputException {
		String input = scanner.nextLine().trim();
		// 공백
		if(input.equals("")) {
			throw new InvalidInputException("입력값이 없습니다.");
		}
		return input;
	}
}
