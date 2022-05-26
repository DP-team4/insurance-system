package domain.insurance.test;

import domain.customer.Customer;
import domain.insurance.Insurance;
import repository.insurance.InsuranceListImpl;
import domain.insurance.Clause;
import domain.insurance.ClauseCategory;

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
            System.out.println("����(1), ����(2), ���̵�� ��ȸ(3), ��ü ��ȸ(4), ���� ���(5), ���(0)");
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
                    testRetrieve(scanner);
                    break;
                case "4":
                    testRetrieveAll();
                    break;
                case "5":
                    testCalculateRatio(scanner);
                    break;
                default:
                    break;
            }
        }
    }

    private static void testCalculateRatio(Scanner scanner) {
        Customer customer1 = new Customer();
        customer1.setCustomerName("customer1");
        customer1.setAge(30);
        while(true){
            System.out.println("///// Test for Insurance, CalculateRatio /////");
            System.out.println("�׽�Ʈ�� ���� ������(customer1)�� �����Ͽ����ϴ�.");
            System.out.println("���� ID >> ");
            String insuranceId = scanner.nextLine().trim();
            Insurance insurance = InsuranceListImpl.getInstance().get(insuranceId);
            System.out.println("���� ����: " + insurance.calculateRatio(customer1));

            System.out.print("���� ���� ��� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }

    }

    private static void testRetrieveAll() {
        InsuranceListImpl.getInstance().printAll();
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

    private static void testRetrieve(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Insurance, Retrieve /////");
            System.out.print("���̵� >> ");
            String inputID = scanner.nextLine().trim();
            Insurance insurance = InsuranceListImpl.getInstance().get(inputID);
            if(insurance == null) System.out.println("�ش��ϴ� ID�� ������ ã�� ���߽��ϴ�.");
            else System.out.println(insurance);

            System.out.print("���� ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
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
                    insurance = FireInsuranceTest.testCreation(scanner, name);
                    break;
                case "2":
                    break;
                case "3":
                    insurance = MarineInsuranceTest.testCreation(scanner, name);
                    break;
                case "4":
                    insurance = CarInsuranceTest.testCreation(scanner, name);
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
