package domain.customer.test;

import java.util.Scanner;

import domain.customer.Customer;
import repository.customer.CustomerListImpl;

public class CustomerTest {
    private static final CustomerListImpl customerRepository = CustomerListImpl.getInstance();
    
	public static void test(Scanner scanner) {
		System.out.println("///// Test for Customer /////");

	    // �׽�Ʈ ������ ����
		int numTestData = 2; // �׽�Ʈ ������ ����
        // Customer
        for(int i = 0; i < numTestData; i++) {
        	customerRepository.add(new Customer("Customer" + i, 30 + i));
        }
        
        System.out.println("Customer ���:");
        customerRepository.printAll();
        System.out.print("�۾��� Customer ID�� �Է��ϼ��� >> ");
        String customerId = scanner.nextLine().trim();

        Customer customer = customerRepository.get(customerId);
        if(customer == null){
            System.out.println("�߸��� Customer ID�Դϴ�.");
            return;
        }
        
	    while(true) {
	        System.out.println("����(1), ����(2), ���̵�� ��ȸ(3), ��ü ��ȸ(4), �ڷΰ���(0)");
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
	                System.out.println("�߸��� �Է��Դϴ�.");
	                break;
	        }
//	        System.out.println(customer);
	
	        System.out.println("�׽�Ʈ�� �ݺ��մϴ�. ����Ͻðڽ��ϱ�? ���(1) �ڷΰ���(�� ��)");
	        input = scanner.nextLine().trim();
	        if(!input.equals("1")) break;
	    }
	    // �׽�Ʈ ������ ����
	    
	}

	private static void testCreation(Scanner scanner) {
		String input = "";
        while (true) {
            Customer customer = new Customer();
            System.out.println("///// Test for Customer, Creation /////");
            System.out.print("�̸� >> "); customer.setCustomerName(scanner.nextLine().trim());
            System.out.print("���� >> "); customer.setAge(Integer.parseInt(scanner.nextLine().trim()));
            boolean finished = false;
            while(!finished) {
                System.out.print("���� ����(1), ����(2) >> ");
                input = scanner.nextLine().trim();
                switch(input) {
    				case "1": customer.setGender(true); finished = true; break;
    				case "2": customer.setGender(false); finished = true; break;
    				default: System.out.println("������ ���� �Է��� �ùٸ��� �ʽ��ϴ�. �Է�: " + input); break;
    			};
            }
            System.out.print("�ֹε�Ϲ�ȣ ���ڸ� >> "); input = scanner.nextLine().trim();
    		System.out.print("�ֹε�Ϲ�ȣ ���ڸ� >> "); customer.setRegistrationNo(input + "-" + scanner.nextLine().trim());
            System.out.print("��ȭ��ȣ ex.01012345678 >> "); customer.setPhoneNo(scanner.nextLine().trim());System.out.print("�����ּ� >> "); customer.setEmail(scanner.nextLine().trim());
            System.out.print("���� >> "); customer.setAccountNo(scanner.nextLine().trim());
            System.out.print("���� >> "); customer.setCarPrice(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("���� >> "); customer.setHousePrice(Long.parseLong(scanner.nextLine().trim()));
            finished = false;
            while(!finished) {
                System.out.print("��ȥ���� ��ȥ(1) ��ȥ(2) >> ");
                input = scanner.nextLine().trim();
                switch(input) {
    				case "1": customer.setMarried(true); finished = true; break;
    				case "2": customer.setMarried(false); finished = true; break;
    				default: System.out.println("��ȥ���ο� ���� �Է��� �ùٸ��� �ʽ��ϴ�. �Է�: " + input); break;
                };
            }
            System.out.print("��й�ȣ >> "); customer.setPassword(scanner.nextLine().trim());
    		
    		if(customerRepository.add(customer))System.out.println("�������丮�� �߰��Ǿ����ϴ�.");
            else System.out.println("�������丮�� �߰����� �ʾҽ��ϴ�.");
            System.out.print("�� ���� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testDeletion(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Customer, Deletion /////");
            System.out.print("���̵� >> ");
            String inputID = scanner.nextLine().trim();
            if(customerRepository.delete(inputID)) System.out.println("���� �����ῴ���ϴ�.");
            else System.out.println("���� �����Ͽ����ϴ�.");

            System.out.print("�� ���� �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieve(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Customer, Retrieve /////");
            System.out.print("���̵� >> ");
            String inputID = scanner.nextLine().trim();
            Customer customer = customerRepository.get(inputID);
            if(customer == null) System.out.println("�ش��ϴ� ID�� ������ ã�� ���߽��ϴ�.");
            else System.out.println(customer);

            System.out.print("�� ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
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
