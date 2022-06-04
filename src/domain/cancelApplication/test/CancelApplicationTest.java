package domain.cancelApplication.test;

import java.util.Scanner;

import dao.CancelApplicationDao;
import domain.cancelApplication.CancelApplication;

public class CancelApplicationTest {
    private static final CancelApplicationDao cancelApplicationDao = new CancelApplicationDao();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);
        scanner.close();
    }
    
	public static void test(Scanner scanner) {
		System.out.println("///// Test for CancelApplication /////"); 
	    while(true) {
	        System.out.println("생성(1), 삭제(2), 해지 신청 신청 아이디로 조회(3), 고객 아이디로 조회(4), 전체 조회(5), 뒤로가기(0)");
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
	            	testRetrieveByContractId(scanner);
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
        	CancelApplication cancelApplication = new CancelApplication();
            System.out.println("///// Test for CancelApplication, Creation /////");
            /////////////////////////// 수정 필요 >> 현재 로그인된 고객의 계약 목록을 가져와야 함
            System.out.print("해지할 계약 아이디 >> "); cancelApplication.setContractId(scanner.nextLine().trim());
    		if(cancelApplicationDao.create(cancelApplication))System.out.println("레포지토리에 추가되었습니다.");
            else System.out.println("레포지토리에 추가되지 않았습니다.");
            System.out.print("해지 신청 생성 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testDeletion(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for CancelApplication, Deletion /////");
            System.out.print("아이디 >> ");
            String id = scanner.nextLine().trim();
            if(cancelApplicationDao.delete(id)) System.out.println("삭제 성공햐였습니다.");
            else System.out.println("삭제 실패하였습니다.");

            System.out.print("해지 신청 삭제 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieve(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for CancelApplication, Retrieve /////");
            System.out.print("아이디 >> ");
            String id = scanner.nextLine().trim();
            CancelApplication cancelApplication = cancelApplicationDao.retrieveById(id);
            if(cancelApplication == null) System.out.println("해당하는 ID의 해지 신청을 찾지 못했습니다.");
            else System.out.println(cancelApplication);

            System.out.print("해지 신청 조회 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveByContractId(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for CancelApplication, Retrieve By Customer Id/////");
            System.out.print("계약 아이디 >> ");
            String contractId = scanner.nextLine().trim();
            CancelApplication cancelApplication = cancelApplicationDao.retrieveByContractId(contractId);
            if(cancelApplication == null) System.out.println("해당하는 계약 ID의 해지 신청을 찾지 못했습니다.");
            else System.out.println(cancelApplication);
            System.out.print("해지 신청 조회 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveAll() {
		System.out.println("///// Test for CancelApplication, RetrieveAll /////");
        cancelApplicationDao.retrieveAll().forEach(c -> {
            System.out.println(c);
            System.out.println();
        });
	}
}
