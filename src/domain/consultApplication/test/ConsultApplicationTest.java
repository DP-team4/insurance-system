package domain.consultApplication.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import dao.ConsultApplicationDao;
import dao.CustomerDao;
import domain.consultApplication.ConsultApplication;

public class ConsultApplicationTest {
    private static final ConsultApplicationDao consultApplicationDao = new ConsultApplicationDao();
    private static final CustomerDao customerDao = new CustomerDao();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);
        scanner.close();
    }
    
	public static void test(Scanner scanner) {
		System.out.println("///// Test for ConsultApplication /////"); 
	    while(true) {
	        System.out.println("생성(1), 삭제(2), 상담 신청 아이디로 조회(3), 고객 아이디로 조회(4), 전체 조회(5), 뒤로가기(0)");
	        System.out.print(">> "); String input = scanner.nextLine().trim();
	        if(input.equals("0")) break;
	        switch (input) {
	        	case "1":
		        	testCreation(scanner);
	                break;
	            case "2":
	            	testDeletion(scanner);
	                break;
	            case "3":
	            	testRetrieve(scanner);
	                break;
	            case "4":
	            	testRetrieveByCustomerId(scanner);
	                break;
	            case "5":
	            	testRetrieveAll();
	                break;
	            default:
	                System.out.println("잘못된 입력입니다.");
	                break;
	        }
	        System.out.println("테스트를 반복합니다. 계속하시겠습니까? 계속(1) 뒤로가기(그 외)");
	        input = scanner.nextLine().trim();
	        if(!input.equals("1")) break;
	    }
	}

	private static void testCreation(Scanner scanner) {
        while (true) {
        	ConsultApplication consultApplication = new ConsultApplication();
            System.out.println("///// Test for ConsultApplication, Creation /////");
            /////////////////////////// 수정 필요 >> 현재 로그인된 고객의 ID를 가져와야 함
            customerDao.retrieveAll().forEach(c -> {
                System.out.println(c);
                System.out.println();
            });
            System.out.print("고객 아이디 >> ");
            consultApplication.setCustomerId(scanner.nextLine().trim());
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.print("상담 내용 >> "); consultApplication.setContent(scanner.nextLine().trim());
            System.out.println("상담 날짜 >> ");
            System.out.println("년 : "); int year = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("월 : "); int month = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("일 : "); int dayOfMonth = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("시 : "); int hour = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("분 : "); int minute = Integer.parseInt(scanner.nextLine().trim());
            LocalDateTime consultDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
            consultApplication.setConsultationDate(consultDateTime);
    		if(consultApplicationDao.create(consultApplication))System.out.println("레포지토리에 추가되었습니다.");
            else System.out.println("레포지토리에 추가되지 않았습니다.");
            System.out.print("상담 신청 생성 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testDeletion(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for ConsultApplication, Deletion /////");
            System.out.print("아이디 >> ");
            String id = scanner.nextLine().trim();
            if(consultApplicationDao.delete(id)) System.out.println("삭제 성공햐였습니다.");
            else System.out.println("삭제 실패하였습니다.");

            System.out.print("상담 신청 삭제 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieve(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for ConsultApplication, Retrieve /////");
            System.out.print("아이디 >> ");
            String id = scanner.nextLine().trim();
            ConsultApplication consultApplication = consultApplicationDao.retrieveById(id);
            if(consultApplication == null) System.out.println("해당하는 ID의 상담 신청을 찾지 못했습니다.");
            else System.out.println(consultApplication);

            System.out.print("상담 신청 조회 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveByCustomerId(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for ConsultApplication, Retrieve By Customer Id/////");
            System.out.print("고객 아이디 >> ");
            String customerId = scanner.nextLine().trim();
            ArrayList<ConsultApplication> consultApplications = consultApplicationDao.retrieveByCustomerId(customerId);
            if(consultApplications.size() == 0) System.out.println("해당하는 이름의 상담 신청을 찾지 못했습니다.");
            else {
            	System.out.println();
            	System.out.println(consultApplications.size() + "명의 상담 신청을 찾았습니다!!");
            	System.out.println();
            	consultApplications.forEach(c -> {
                    System.out.println(c);
                    System.out.println();
                });
            }
            System.out.print("상담 신청 조회 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveAll() {
		System.out.println("///// Test for ConsultApplication, RetrieveAll /////");
        consultApplicationDao.retrieveAll().forEach(c -> {
            System.out.println(c);
            System.out.println();
        });
	}
}
