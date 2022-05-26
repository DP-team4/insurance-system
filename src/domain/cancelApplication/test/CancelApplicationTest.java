package domain.cancelApplication.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.customer.Customer;
import domain.insurance.FireInsurance;
import domain.insurance.Insurance;
import domain.cancelApplication.CancelApplication;
import domain.cancelApplication.CancelApplicationState;
import domain.contract.Contract;
import repository.cancelApplication.CancelApplicationListImpl;

public class CancelApplicationTest {
	private static final ArrayList<Customer> customers = new ArrayList<>();
    private static final ArrayList<Insurance> insurances = new ArrayList<>();
    private static final ArrayList<Contract> contracts = new ArrayList<>();
	private static final CancelApplicationListImpl cancelRepository = CancelApplicationListImpl.getInstance();
	
	public static void test(Scanner scanner) {
		System.out.println("///// Test for CancelApplication /////");

        // 테스트 데이터 생성
		int numTestData = 2; // 테스트 데이터 개수
        // Customer
        for(int i = 0; i < numTestData; i++) {
        	Customer customer = new Customer();
        	customer.setCustomerName("Customer" + i+1);
            customer.setAge(24 + (i * 3 + 1));
            customers.add(customer);
        }
        // Insurance
        for (int i = 0; i < numTestData; i++) {
            FireInsurance insurance = new FireInsurance();
            insurance.setName("Insurance" + i);
            insurances.add(insurance);
        }
        // Contract
        for(int i = 0; i < numTestData; i++) {
        	Contract contract = new Contract(customers.get(i), insurances.get(i), LocalDateTime.now(), LocalDateTime.now().plusDays(i * 3));
        	contracts.add(contract);
        }
        // CancelApplication
        for(int i = 0; i < numTestData; i++) {
        	CancelApplication cancelApplication = new CancelApplication();
        	cancelApplication.setContract(contracts.get(i));
            cancelRepository.add(cancelApplication);
        }
        System.out.println("CancelApplication 목록:");
        cancelRepository.printAll();
        System.out.print("작업할 CancelApplication ID를 입력하세요 >> ");
        String cancelApplicationId = scanner.nextLine().trim();

        CancelApplication cancelApplication = cancelRepository.get(cancelApplicationId);
        if(cancelApplication == null){
            System.out.println("잘못된 CancelApplication ID입니다.");
            return;
        }

        while(true) {
            System.out.println("////// 해당 CancelApplication에 대한 작업을 시작합니다. //////");
            System.out.println("승인(1), 거절(2), 뒤로가기(0)");
            String input = scanner.nextLine().trim();
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    testAccept(cancelApplication);
                    break;
                case "2":
                    testReject(cancelApplication);
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
            System.out.println(cancelApplication);

            System.out.println("테스트를 반복합니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
            input = scanner.nextLine().trim();
            if(!input.equals("1")) break;
        }
	}

	private static void testAccept(CancelApplication cancelApplication) {
        System.out.println("///// Test for CancelApplication, Accept /////");
        cancelApplication.setState(CancelApplicationState.ACCEPTED);
        System.out.println("해지 승인을 완료하였습니다.");
	}

	private static void testReject(CancelApplication cancelApplication) {
        System.out.println("///// Test for CancelApplication, Reject /////");
        cancelApplication.setState(CancelApplicationState.REJECTED);
        System.out.println("해지 신청 거절을 완료하였습니다.");
	}
}
