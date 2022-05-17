package domain.UW.test;

import domain.Customer.Customer;
import domain.Insurance.FireInsurance;
import domain.Insurance.Insurance;
import repository.insurance.InsuranceListImpl;
import domain.UW.UW;
import repository.uw.UWListImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class UWTest {
    private static final ArrayList<Customer> customers = new ArrayList<>();
    private static final InsuranceListImpl insuranceRepository = InsuranceListImpl.getInstance();
    private static final ArrayList<Insurance> insurances = new ArrayList<>();
    private static final UWListImpl uwRepository = UWListImpl.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);
        scanner.close();
    }

    public static void test(Scanner scanner) {
        System.out.println("///// Test for UW /////");

        // 테스트 데이터 생성
        // Customer -> 아직 Customer 구현이 완료되지 않은 관계로 ArrayList를 만들어 테스트
        for(int i=0; i<10; i++) {
            customers.add(new Customer("Customer" + i, 30 + i));
        }
        // Insurance
        for (int i = 0; i < 3; i++) {
            FireInsurance insurance = new FireInsurance();
            insurance.setName("insurance"+i);
            insurances.add(insurance);
            InsuranceListImpl.getInstance().add(insurance);
        }
        // UW
        UW uw1 = new UW();
        uw1.setCustomerID(customers.get(0).getId());
        uw1.setInsuranceID(insurances.get(1).getId());
        uwRepository.add(uw1);
        UW uw2 = new UW();
        uw2.setCustomerID(customers.get(3).getId());
        uw2.setInsuranceID(insurances.get(2).getId());
        uwRepository.add(uw2);

        System.out.println("UW 목록:");
        uwRepository.printAll();
        System.out.print("작업할 UW의 ID를 입력하세요 >> ");
        String uwId = scanner.nextLine().trim();

        UW uw = uwRepository.get(uwId);
        if(uw == null){
            System.out.println("잘못된 UW ID입니다.");
            return;
        }

        while(true) {
            System.out.println("////// 해당 UW에 대한 작업을 시작합니다. //////");
            System.out.println("인수(1), 반려(2), 추가서류요구(3), 뒤로가기(0)");
            String input = scanner.nextLine().trim();
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    testAccept(uw);
                    break;
                case "2":
                    testReject(uw);
                    break;
                case "3":
                    testDocumentRequest(scanner, uw);
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    break;
            }
            System.out.println(uw);

            System.out.println("테스트를 반복합니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
            input = scanner.nextLine().trim();
            if(!input.equals("1")) break;
        }
    }

    private static void testDocumentRequest(Scanner scanner, UW uw) {
        System.out.println("Test for UW, DocumentRequest");
        System.out.print("추가 요청 서류 이름 >> ");
        String documentName = scanner.nextLine().trim();
        uw.requestDocument(documentName);
        System.out.println("서류 요청 완료하였습니다.");
    }

    private static void testReject(UW uw) {
        System.out.println("Test for UW, Reject");
        uw.reject();
        System.out.println("반려를 완료하였습니다.");
    }

    private static void testAccept(UW uw) {
        System.out.println("Test for UW, Accept");
        uw.accept();
        System.out.println("인수를 완료하였습니다.");
    }
}
