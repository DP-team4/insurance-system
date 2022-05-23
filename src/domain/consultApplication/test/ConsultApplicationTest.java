package domain.consultApplication.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Customer.Customer;
import domain.consultApplication.ConsultApplication;
import repository.consultApplication.ConsultApplicationListImpl;

public class ConsultApplicationTest {
	private static final ArrayList<Customer> customers = new ArrayList<>();
	private static final ConsultApplicationListImpl consultRepository = ConsultApplicationListImpl.getInstance();

	public static void test(Scanner scanner) {
		System.out.println("///// Test for ConsultApplication /////");

        // 테스트 데이터 생성
		int numTestData = 2; // 테스트 데이터 개수
        // Customer
        for(int i = 0; i < numTestData; i++) {
        	customers.add(new Customer("Customer" + i, 30 + i));
        }
        // ConsultApplicationTest
        for(int i = 0; i < numTestData; i++) {
        	ConsultApplication consultApplication = new ConsultApplication(customers.get(i).getId(), "상담내용" + i+1, LocalDateTime.now().plusDays(i * 3 + 1));
        	consultRepository.add(consultApplication);
        }
        System.out.println("ConsultApplication 목록:");
        consultRepository.printAll();
        System.out.print("작업할 ConsultApplication ID를 입력하세요 >> ");
        String consultApplicationId = scanner.nextLine().trim();

        ConsultApplication consultApplication = consultRepository.get(consultApplicationId);
        if(consultApplication == null){
            System.out.println("잘못된 ConsultApplication ID입니다.");
            return;
        }

        while(true) {
            System.out.println("////// 해당 ConsultApplication에 대한 작업을 시작합니다. //////");
            System.out.println("승인(1), 거절(2), 상담완료(3), 뒤로가기(0)");
            String input = scanner.nextLine().trim();
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    testAccept(consultApplication);
                    break;
                case "2":
                    testReject(consultApplication);
                    break;
                case "3":
                    testComplete(consultApplication);
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
            System.out.println(consultApplication);

            System.out.println("테스트를 반복합니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
            input = scanner.nextLine().trim();
            if(!input.equals("1")) break;
        }
	}

	private static void testAccept(ConsultApplication consultApplication) {
        System.out.println("///// Test for CancelApplication, Accept /////");
		consultApplication.accept();
        System.out.println("상담 신청 승인을 완료하였습니다.");
	}

	private static void testReject(ConsultApplication consultApplication) {
        System.out.println("///// Test for CancelApplication, Reject /////");
		consultApplication.reject();
        System.out.println("상담 신청 거절을 완료하였습니다.");
	}

	private static void testComplete(ConsultApplication consultApplication) {
        System.out.println("///// Test for CancelApplication, Complete /////");
		consultApplication.complete();
        System.out.println("상담 신청이 완료되었습니다.");
	}

}
