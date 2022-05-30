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
            System.out.println("����(1), ����(2), ���̵�� ��ȸ(3), ��������� ��ȸ(4), ��ü ��ȸ(5), ���� ���(6), ���(0)");
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
            System.out.print("����� >> ");
            String inputName = scanner.nextLine().trim();
            Insurance insurance = InsuranceListImpl.getInstance().getByName(inputName);
            if(insurance == null) System.out.println("�ش��ϴ� �̸��� ������ ã�� ���߽��ϴ�.");
            else System.out.println(insurance);

            System.out.print("����� ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
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
            System.out.println("�׽�Ʈ�� ���� ������(customer1)�� �����Ͽ����ϴ�.");
            System.out.println(customer1);
            System.out.println("���� ID >> ");
            String insuranceId = scanner.nextLine().trim();
            Insurance insurance = InsuranceListImpl.getInstance().get(insuranceId);
            if(insurance==null) {
                System.out.println("�߸��� ID�Դϴ�.");
                break;
            }
            System.out.println("���� ����: " + insurance.calculateRatio(customer1));

            System.out.print("���� ���� ��� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
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
            System.out.print("���̵� >> ");
            String inputID = scanner.nextLine().trim();
            if(InsuranceListImpl.getInstance().delete(inputID)) System.out.println("���� �����ῴ���ϴ�.");
            else System.out.println("���� �����Ͽ����ϴ�.");

            System.out.print("���� ���� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
    }

    private static void testRetrieveById(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Insurance, Retrieve /////");
            System.out.print("���̵� >> ");
            String inputID = scanner.nextLine().trim();
            Insurance insurance = InsuranceListImpl.getInstance().get(inputID);
            if(insurance == null) System.out.println("�ش��ϴ� ID�� ������ ã�� ���߽��ϴ�.");
            else System.out.println(insurance);

            System.out.print("���� ���̵� ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
    }

    public static void testCreation(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Insurance, Creation /////");
            System.out.print("�̸� >> ");
            String name = scanner.nextLine().trim();
            System.out.print("���� ȭ��(1), ������(2), �ػ�(3), �ڵ���(4) >> ");
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
                    System.out.println("������ ���� �Է��� �ùٸ��� �ʽ��ϴ�. �Է�: " + category);
                    break;
            }
            if(insurance == null) return;
            while(true){
                System.out.print("��� �߰�(1), �Ϸ�(�� ��) >> ");
                if(!scanner.nextLine().trim().equals("1")) break;
                Clause clause = testClauseCreation(scanner);
                insurance.addClause(clause);
            }

            System.out.print("���� ���� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
    }

    private static Clause testClauseCreation(Scanner scanner) {
        System.out.print("����� >> ");
        String name = scanner.nextLine().trim();
        System.out.print("���Աݾ� >> ");
        String insuredAmount = scanner.nextLine().trim();
        System.out.print("�� ����� >> ");
        String premium = scanner.nextLine().trim();
        System.out.print("�Ϲݾ��(1), Ư�����(�� ��) >> ");
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
            System.out.println("�������丮�� �߰��Ǿ����ϴ�.");
            return true;
        }
        else {
            System.out.println("�������丮�� �߰����� �ʾҽ��ϴ�.");
            return false;
        }
    }
}
