package domain.customer.test;

import java.util.Scanner;

import domain.customer.AdditionalInfo;
import domain.customer.Customer;
import repository.customer.CustomerListImpl;

public class CustomerTest {
    private static final CustomerListImpl customerRepository = CustomerListImpl.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);
        scanner.close();
    }
    
	public static void test(Scanner scanner) {
		System.out.println("///// Test for Customer /////");

	    // 테스트 데이터 생성
		int numTestData = 2; // 테스트 데이터 개수
        // Customer
        for(int i = 0; i < numTestData; i++) {
        	Customer customer = new Customer();
            customer.setEmail(i+"@gmail.com");
            customer.setPassword("i");
        	customer.setCustomerName("Customer" + i+1);
            customer.setAge(24 + (i * 3 + 1));
            if(i / 2 == 0) customer.setGender(true);
            else customer.setGender(false);
            customer.setRegistrationNo("012345" + "-" + "6789012");
            customer.setPhoneNo("01012345678");
            customer.setAccountNo("하나 " + i);
            if(i / 2 == 0) customer.setMarried(true);
            else customer.setMarried(false);
            AdditionalInfo additionalinfo = customer.getAdditionalInfo();
            additionalinfo.setCarPrice((i+1) * 10000000);
            additionalinfo.setHousePrice((i+1) * 200000000);
            additionalinfo.setDrivingCareer((i+1));
            additionalinfo.setShipPrice((i+1) * 150000000);
        	customerRepository.add(customer);
        }
        
        System.out.println("Customer 목록:");
        customerRepository.printAll();
        System.out.print("작업할 Customer ID를 입력하세요 >> ");
        String customerId = scanner.nextLine().trim();

        Customer customer = customerRepository.get(customerId);
        if(customer == null){
            System.out.println("잘못된 Customer ID입니다.");
            return;
        }
        
	    while(true) {
	        System.out.println("생성(1), 삭제(2), 아이디로 조회(3), 전체 조회(4), 뒤로가기(0)");
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
            System.out.print("메일주소 >> "); customer.setEmail(scanner.nextLine().trim());
            System.out.print("비밀번호 >> "); customer.setPassword(scanner.nextLine().trim());
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
            System.out.print("전화번호 ex.01012345678 >> "); customer.setPhoneNo(scanner.nextLine().trim());
            System.out.print("계좌 >> "); customer.setAccountNo(scanner.nextLine().trim());
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
            AdditionalInfo additionalinfo = customer.getAdditionalInfo();
            System.out.print("차값(원) >> "); additionalinfo.setCarPrice(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("집값(원) >> "); additionalinfo.setHousePrice(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("운전경력(년) >> "); additionalinfo.setDrivingCareer(Integer.parseInt(scanner.nextLine().trim()));
            System.out.print("선박가격(원) >> "); additionalinfo.setShipPrice(Long.parseLong(scanner.nextLine().trim()));
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
		System.out.println("///// Test for Customer, RetrieveAll /////");
		customerRepository.printAll();
	}
}
