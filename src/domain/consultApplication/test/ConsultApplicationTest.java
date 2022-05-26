package domain.consultApplication.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.customer.Customer;
import domain.consultApplication.ConsultApplication;
import domain.consultApplication.ConsultApplicationState;
import repository.consultApplication.ConsultApplicationListImpl;

public class ConsultApplicationTest {
	private static final ArrayList<Customer> customers = new ArrayList<>();
	private static final ConsultApplicationListImpl consultRepository = ConsultApplicationListImpl.getInstance();

	public static void test(Scanner scanner) {
		System.out.println("///// Test for ConsultApplication /////");

        // �׽�Ʈ ������ ����
		int numTestData = 2; // �׽�Ʈ ������ ����
        // Customer
        for(int i = 0; i < numTestData; i++) {
        	Customer customer = new Customer();
        	customer.setCustomerName("Customer" + i+1);
            customer.setAge(24 + (i * 3 + 1));
            customers.add(customer);
        }
        // ConsultApplicationTest
        for(int i = 0; i < numTestData; i++) {
        	ConsultApplication consultApplication = new ConsultApplication(customers.get(i), "��㳻��" + i+1, LocalDateTime.now().plusDays(i * 3 + 1));
        	consultRepository.add(consultApplication);
        }
        System.out.println("ConsultApplication ���:");
        consultRepository.printAll();
        System.out.print("�۾��� ConsultApplication ID�� �Է��ϼ��� >> ");
        String consultApplicationId = scanner.nextLine().trim();

        ConsultApplication consultApplication = consultRepository.get(consultApplicationId);
        if(consultApplication == null){
            System.out.println("�߸��� ConsultApplication ID�Դϴ�.");
            return;
        }

        while(true) {
            System.out.println("////// �ش� ConsultApplication�� ���� �۾��� �����մϴ�. //////");
            System.out.println("����(1), ����(2), ���Ϸ�(3), �ڷΰ���(0)");
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
                    System.out.println("�߸��� �Է��Դϴ�.");
                    break;
            }
            System.out.println(consultApplication);

            System.out.println("�׽�Ʈ�� �ݺ��մϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
            input = scanner.nextLine().trim();
            if(!input.equals("1")) break;
        }
	}

	private static void testAccept(ConsultApplication consultApplication) {
        System.out.println("///// Test for CancelApplication, Accept /////");
        consultApplication.setState(ConsultApplicationState.ACCEPTED);
        System.out.println("��� ��û ������ �Ϸ��Ͽ����ϴ�.");
	}

	private static void testReject(ConsultApplication consultApplication) {
        System.out.println("///// Test for CancelApplication, Reject /////");
        consultApplication.setState(ConsultApplicationState.REJECTED);
        System.out.println("��� ��û ������ �Ϸ��Ͽ����ϴ�.");
	}

	private static void testComplete(ConsultApplication consultApplication) {
        System.out.println("///// Test for CancelApplication, Complete /////");
		consultApplication.setState(ConsultApplicationState.COMPLETED);
        System.out.println("��� ��û�� �Ϸ�Ǿ����ϴ�.");
	}

}
