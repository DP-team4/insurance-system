package domain.Customer.test;

import java.util.Scanner;

import domain.Customer.Customer;
import repository.customer.CustomerListImpl;

public class CustomerTest {
    private static final CustomerListImpl customerRepository = CustomerListImpl.getInstance();
    
	public static void test(Scanner scanner) {
		System.out.println("///// Test for Customer /////");

	    // 테스트 데이터 생성
		
	    while(true) {
	        System.out.println("생성(1), 삭제(2), 아이디로 조회(3), 전체 조회(4), 보험상품 관리(5), 보험금 청구(6)");
	        System.out.println("가입 상담 신청(7), 사고처리 신청(8), 계약 해지 신청(9), 보험료 납부(10), 뒤로가기(0)");
	        System.out.println("(( # 참고 : 5번 부터는 미구현 기능입니다. ))");
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
	            	testRetrieveAll();
	                break;
	            case "5":
	            	testGetContracts();
	                break;
	            case "6":
	                testApplyClaim(scanner);
	                break;
	            case "7":
	                testApplyConsultation(scanner);
	                break;
	            case "8":
	                testApplyCoverage(scanner);
	                break;
	            case "9":
	                testCancelContract(scanner);
	                break;
	            case "10":
	                testPayPremium();
	                break;
	            default:
	                System.out.println("잘못된 입력입니다.");
	                break;
	        }
//	        System.out.println(customer);
	
	        System.out.println("테스트를 반복합니다. 계속하시겠습니까? 계속(1) 뒤로가기(그 외)");
	        input = scanner.nextLine().trim();
	        if(!input.equals("1")) break;
	    }
	    // 테스트 데이터 삭제
	    
	}

	private static void testCreation(Scanner scanner) {
		String input = "";
        while (true) {
            Customer customer = new Customer();
            System.out.println("///// Test for Customer, Creation /////");
            System.out.print("이름 >> "); customer.setCustomerName(scanner.nextLine().trim());
            System.out.print("나이 >> "); customer.setAge(Integer.parseInt(scanner.nextLine().trim()));
            boolean finished = false;
            while(!finished) {
                System.out.print("성별 남성(1), 여성(2) >> ");
                input = scanner.nextLine().trim();
                switch(input) {
    				case "1": customer.setGender(true); finished = true; break;
    				case "2": customer.setGender(false); finished = true; break;
    				default: System.out.println("성별에 대한 입력이 올바르지 않습니다. 입력: " + input); break;
    			};
            }
            System.out.print("주민등록번호 앞자리 >> "); input = scanner.nextLine().trim();
    		System.out.print("주민등록번호 뒷자리 >> "); customer.setRegistrationNo(input + "-" + scanner.nextLine().trim());
            System.out.print("전화번호 ex.01012345678 >> "); customer.setPhoneNo(scanner.nextLine().trim());System.out.print("메일주소 >> "); customer.setEmail(scanner.nextLine().trim());
            System.out.print("계좌 >> "); customer.setAccountNo(scanner.nextLine().trim());
            System.out.print("집값(억) >> "); customer.setHousePrice(Integer.parseInt(scanner.nextLine().trim()));
            finished = false;
            while(!finished) {
                System.out.print("결혼여부 기혼(1) 미혼(2) >> ");
                input = scanner.nextLine().trim();
                switch(input) {
    				case "1": customer.setMarried(true); finished = true; break;
    				case "2": customer.setMarried(false); finished = true; break;
    				default: System.out.println("결혼여부에 대한 입력이 올바르지 않습니다. 입력: " + input); break;
                };
            }
            System.out.print("비밀번호 >> "); customer.setPassword(scanner.nextLine().trim());
    		
    		if(customerRepository.add(customer))System.out.println("레포지토리에 추가되었습니다.");
            else System.out.println("레포지토리에 추가되지 않았습니다.");
            System.out.print("고객 생성 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testDeletion(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Customer, Deletion /////");
            System.out.print("아이디 >> ");
            String inputID = scanner.nextLine().trim();
            if(customerRepository.delete(inputID)) System.out.println("삭제 성공햐였습니다.");
            else System.out.println("삭제 실패하였습니다.");

            System.out.print("고객 삭제 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieve(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Customer, Retrieve /////");
            System.out.print("아이디 >> ");
            String inputID = scanner.nextLine().trim();
            Customer customer = customerRepository.get(inputID);
            if(customer == null) System.out.println("해당하는 ID의 보험을 찾지 못했습니다.");
            else System.out.println(customer);

            System.out.print("고객 조회 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveAll() {
		System.out.println("Test for Customer, RetrieveAll");
		customerRepository.printAll();
	}

	private static void testGetContracts() {
		System.out.println("Test for Customer, GetContracts");
		System.out.println("미구현 기능입니다.");
//        customer.getContracts();
//        System.out.println("보험상품 관리 완료하였습니다.");
	}

	private static void testApplyClaim(Scanner scanner) {
        System.out.println("Test for Customer, ApplyClaim");
        System.out.println("미구현 기능입니다.");
//        System.out.print("추가 요청 서류 이름 >> ");
//        String documentName = scanner.nextLine().trim();
//        customer.applyClaim();
//        System.out.println("보험금 청구 완료하였습니다.");
	}

	private static void testApplyConsultation(Scanner scanner) {
        System.out.println("Test for Customer, ApplyConsultation");
        System.out.println("미구현 기능입니다.");
//        System.out.print("추가 요청 서류 이름 >> ");
//        String documentName = scanner.nextLine().trim();
//        customer.applyConsultation();
//        System.out.println("가입 상담 신청 완료하였습니다.");
	}

	private static void testApplyCoverage(Scanner scanner) {
        System.out.println("Test for Customer, ApplyCoverage");
        System.out.println("미구현 기능입니다.");
//        System.out.print("추가 요청 서류 이름 >> ");
//        String documentName = scanner.nextLine().trim();
//        customer.applyCoverage();
//        System.out.println("사고처리 신청 완료하였습니다.");
	}

	private static void testCancelContract(Scanner scanner) {
        System.out.println("Test for Customer, CancelContract");
        System.out.println("미구현 기능입니다.");
//        System.out.print("추가 요청 서류 이름 >> ");
//        String documentName = scanner.nextLine().trim();
//        customer.cancelContract();
//        System.out.println("계약 해지 신청 완료하였습니다.");
	}

	private static void testPayPremium() {
        System.out.println("Test for Customer, PayPremium");
        System.out.println("미구현 기능입니다.");
//        customer.payPremium();
//        System.out.println("보험료 납부  완료하였습니다.");
	}
}
