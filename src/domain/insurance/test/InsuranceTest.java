package domain.insurance.test;

import domain.customer.Customer;
import domain.insurance.Insurance;
import repository.customer.CustomerListImpl;
import repository.insurance.InsuranceListImpl;
import domain.insurance.Clause;
import domain.insurance.ClauseCategory;

import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);
        scanner.close();
    }

    public static void test(Scanner scanner) {
        System.out.println("///// Test for Insurance /////");
        while(true){
            System.out.println("생성(1), 삭제(2), 아이디로 조회(3), 보험명으로 조회(4), 전체 조회(5), 요율 계산(6), 취소(0)");
            System.out.print(">> ");
            String input = scanner.nextLine().trim();
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    testCreation(scanner);
                    break;
                case "2":
                    testDeletion(scanner);
                    break;
                case "3":
                    testRetrieveById(scanner);
                    break;
                case "4":
                    testRetrieveByName(scanner);
                    break;
                case "5":
                    testRetrieveAll();
                    break;
                case "6":
                    testCalculateRatio(scanner);
                    break;
                default:
                    break;
            }
        }
    }

    private static void testRetrieveByName(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Insurance, Retrieve By Name /////");
            System.out.print("보험명 >> ");
            String inputName = scanner.nextLine().trim();
            Insurance insurance = InsuranceListImpl.getInstance().getByName(inputName);
            if(insurance == null) System.out.println("해당하는 이름의 보험을 찾지 못했습니다.");
            else System.out.println(insurance);

            System.out.print("보험명 조회 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
    }

    private static void testCalculateRatio(Scanner scanner) {
        Customer customer1 = new Customer();
        customer1.setCustomerName("customer1");
        customer1.setAge(30);
        customer1.getAdditionalInfo().setHousePrice(2000000000L);
        customer1.getAdditionalInfo().setShipPrice(1300000000L);
        customer1.getAdditionalInfo().setDrivingCareer(12);
        customer1.getAdditionalInfo().setCarPrice(2000000000L);
        CustomerListImpl.getInstance().add(customer1);
        while(true){
            System.out.println("///// Test for Insurance, CalculateRatio /////");
            System.out.println("테스트용 더미 데이터(customer1)를 생성하였습니다.");
            System.out.println(customer1);
            System.out.println("보험 ID >> ");
            String insuranceId = scanner.nextLine().trim();
            Insurance insurance = InsuranceListImpl.getInstance().get(insuranceId);
            if(insurance==null) {
                System.out.println("잘못된 ID입니다.");
                break;
            }
            System.out.println("보험 요율: " + insurance.calculateRatio(customer1));

            System.out.print("보험 요율 계산 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }

    }

    private static void testRetrieveAll() {
        ArrayList<Insurance> insurances = InsuranceListImpl.getInstance().getAll();
        insurances.forEach(i -> {
            System.out.println(i);
            System.out.println();
        });
    }

    private static void testDeletion(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Insurance, Deletion /////");
            System.out.print("아이디 >> ");
            String inputID = scanner.nextLine().trim();
            if(InsuranceListImpl.getInstance().delete(inputID)) System.out.println("삭제 성공햐였습니다.");
            else System.out.println("삭제 실패하였습니다.");

            System.out.print("보험 삭제 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
    }

    private static void testRetrieveById(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Insurance, Retrieve /////");
            System.out.print("아이디 >> ");
            String inputID = scanner.nextLine().trim();
            Insurance insurance = InsuranceListImpl.getInstance().get(inputID);
            if(insurance == null) System.out.println("해당하는 ID의 보험을 찾지 못했습니다.");
            else System.out.println(insurance);

            System.out.print("보험 아이디 조회 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
    }

    public static void testCreation(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Insurance, Creation /////");
            System.out.print("이름 >> ");
            String name = scanner.nextLine().trim();
            System.out.print("종류 화재(1), 운전자(2), 해상(3), 자동차(4) >> ");
            String category = scanner.nextLine().trim();
            Insurance insurance = null;
            switch (category) {
                case "1":
                    insurance = FireInsuranceTest.testCreation(name);
                    break;
                case "2":
                    insurance = DriverInsuranceTest.testCreation(name);
                    break;
                case "3":
                    insurance = MarineInsuranceTest.testCreation(name);
                    break;
                case "4":
                    insurance = CarInsuranceTest.testCreation(name);
                    break;
                default:
                    System.out.println("종류에 대한 입력이 올바르지 않습니다. 입력: " + category);
                    break;
            }
            if(insurance == null) return;
            while(true){
                System.out.print("약관 추가(1), 완료(그 외) >> ");
                if(!scanner.nextLine().trim().equals("1")) break;
                Clause clause = testClauseCreation(scanner);
                insurance.addClause(clause);
            }

            System.out.print("보험 생성 테스트를 계속 하시겠습니까? 예(1), 아니오(그 외) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
    }

    private static Clause testClauseCreation(Scanner scanner) {
        System.out.print("약관명 >> ");
        String name = scanner.nextLine().trim();
        System.out.print("가입금액 >> ");
        String insuredAmount = scanner.nextLine().trim();
        System.out.print("총 보험료 >> ");
        String premium = scanner.nextLine().trim();
        System.out.print("일반약관(1), 특별약관(그 외) >> ");
        String category = scanner.nextLine().trim();
        ClauseCategory clauseCategory = category.equals("1") ? ClauseCategory.NORMAL : ClauseCategory.SPECIAL;
        try {
            Clause clause = new Clause();
            clause.setName(name);
            clause.setInsuredAmount(Long.parseLong(insuredAmount));
            clause.setPremium(Long.parseLong(premium));
            clause.setClauseCategory(clauseCategory);
            return clause;
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException, testClauseAddition");
        }
        return null;
    }

    public static boolean testAddition(Insurance insurance) {
        if(InsuranceListImpl.getInstance().add(insurance)) {
            System.out.println("레포지토리에 추가되었습니다.");
            return true;
        }
        else {
            System.out.println("레포지토리에 추가되지 않았습니다.");
            return false;
        }
    }
}
