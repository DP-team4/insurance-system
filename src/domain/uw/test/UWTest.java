package domain.uw.test;

import domain.customer.Customer;
import domain.insurance.FireInsurance;
import domain.insurance.Insurance;
import domain.uw.UWDocument;
import domain.uw.UWDocumentState;
import domain.uw.UWState;
import repository.insurance.InsuranceListImpl;
import domain.uw.UW;
import repository.uw.UWListImpl;

import java.util.ArrayList;
import java.util.Scanner;

public class UWTest {
    private static final ArrayList<Customer> customers = new ArrayList<>();
    private static final ArrayList<Insurance> insurances = new ArrayList<>();
    private static final UWListImpl uwRepository = UWListImpl.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);
        scanner.close();
    }

    public static void test(Scanner scanner) {
        System.out.println("///// Test for UW /////");

        // �׽�Ʈ ������ ����
        // Customer -> ���� Customer ������ �Ϸ���� ���� ����� ArrayList�� ����� �׽�Ʈ
        for(int i=0; i<10; i++) {
            Customer customer = new Customer();
            customer.setCustomerName("Customer" + i);
            customer.setAge(30 + i);
            customers.add(customer);
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
        uw1.setCustomerId(customers.get(0).getId());
        uw1.setInsuranceId(insurances.get(1).getId());
        uwRepository.add(uw1);
        UW uw2 = new UW();
        uw2.setCustomerId(customers.get(3).getId());
        uw2.setInsuranceId(insurances.get(2).getId());
        uwRepository.add(uw2);

        System.out.println("UW ���:");
        uwRepository.printAll();
        System.out.print("�۾��� UW�� ID�� �Է��ϼ��� >> ");
        String uwId = scanner.nextLine().trim();

        UW uw = uwRepository.get(uwId);
        if(uw == null){
            System.out.println("�߸��� UW ID�Դϴ�.");
            return;
        }

        while(true) {
            System.out.println("////// �ش� UW�� ���� �۾��� �����մϴ�. //////");
            System.out.println("�μ�(1), �ݷ�(2), �߰������䱸(3), �ڷΰ���(0)");
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
                    System.out.println("�߸��� �Է��Դϴ�.");
                    break;
            }
            System.out.println(uw);

            System.out.println("�׽�Ʈ�� �ݺ��մϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
            input = scanner.nextLine().trim();
            if(!input.equals("1")) break;
        }
    }

    private static void testDocumentRequest(Scanner scanner, UW uw) {
        System.out.println("Test for UW, DocumentRequest");
        System.out.print("�߰� ��û ���� �̸� >> ");
        String documentName = scanner.nextLine().trim();
        UWDocument uwDocument = new UWDocument();
        uwDocument.setName(documentName);
        uwDocument.setUwDocumentState(UWDocumentState.REQUESTED);
        uw.addDocument(uwDocument);
        System.out.println("���� ��û �Ϸ��Ͽ����ϴ�.");
    }

    private static void testReject(UW uw) {
        System.out.println("Test for UW, Reject");
        uw.setUwState(UWState.REJECTED);
        System.out.println("�ݷ��� �Ϸ��Ͽ����ϴ�.");
    }

    private static void testAccept(UW uw) {
        System.out.println("Test for UW, Accept");
        uw.setUwState(UWState.ACCEPTED);
        System.out.println("�μ��� �Ϸ��Ͽ����ϴ�.");
    }
}
