package domain.customer.test;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AdditionalInfoDao;
import dao.CustomerDao;
import domain.customer.AdditionalInfo;
import domain.customer.Customer;
import repository.CustomerRepository;

public class CustomerTest {
	private static final CustomerRepository customerRepository = CustomerRepository.getInstance();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);
        scanner.close();
    }
    
	public static void test(Scanner scanner) {
		System.out.println("///// Test for Customer /////"); 
	    while(true) {
	        System.out.println("����(1), ����(2), ���̵�� ��ȸ(3), �̸��Ϸ� ��ȸ(4), �̸����� ��ȸ(5), ��ü ��ȸ(6), �ڷΰ���(0)");
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
	            	testRetrieveByEmail(scanner);
	                break;
	            case "5":
	            	testRetrieveByName(scanner);
	                break;
	            case "6":
	            	testRetrieveAll();
	                break;
	            default:
	                System.out.println("�߸��� �Է��Դϴ�.");
	                break;
	        }
	        System.out.println("�׽�Ʈ�� �ݺ��մϴ�. ����Ͻðڽ��ϱ�? ���(1) �ڷΰ���(�� ��)");
	        input = scanner.nextLine().trim();
	        if(!input.equals("1")) break;
	    }
	}

	private static void testCreation(Scanner scanner) {
		String input = "";
        while (true) {
            Customer customer = new Customer();
            System.out.println("///// Test for Customer, Creation /////");
            System.out.print("�̸� >> "); customer.setName(scanner.nextLine().trim());
            System.out.print("�̸��� >> "); customer.setEmail(scanner.nextLine().trim());
            System.out.print("��й�ȣ >> "); customer.setPassword(scanner.nextLine().trim());
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
            System.out.print("��ȭ��ȣ ex.01012345678 >> "); customer.setPhoneNo(scanner.nextLine().trim());
            System.out.print("���� >> "); customer.setAccountNo(scanner.nextLine().trim());
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
            AdditionalInfo additionalinfo = customer.getAdditionalInfo();
            System.out.print("����(��) >> "); additionalinfo.setCarPrice(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("����(��) >> "); additionalinfo.setHousePrice(Long.parseLong(scanner.nextLine().trim()));
            System.out.print("�������(��) >> "); additionalinfo.setDrivingCareer(Integer.parseInt(scanner.nextLine().trim()));
            System.out.print("���ڰ���(��) >> "); additionalinfo.setShipPrice(Long.parseLong(scanner.nextLine().trim()));
    		if(customerRepository.add(customer)) System.out.println("DB�� �߰��Ǿ����ϴ�.");
            else System.out.println("DB�� �߰����� �ʾҽ��ϴ�.");
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
            String id = scanner.nextLine().trim();
            if(customerRepository.delete(id)) System.out.println("���� �����ῴ���ϴ�.");
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
            String id = scanner.nextLine().trim();
            Customer customer = customerRepository.get(id);
            if(customer == null) System.out.println("�ش��ϴ� ID�� ���� ã�� ���߽��ϴ�.");
            else System.out.println(customer);
            System.out.print("�� ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveByEmail(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Customer, Retrieve /////");
            System.out.print("�̸��� >> ");
            String email = scanner.nextLine().trim();
            Customer customer = customerRepository.getByEmail(email);
            if(customer == null) System.out.println("�ش��ϴ� Email�� ���� ã�� ���߽��ϴ�.");
            else System.out.println(customer);
            System.out.print("�� ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveByName(Scanner scanner) {
        while (true) {
            System.out.println("///// Test for Customer, Retrieve By Name/////");
            System.out.print("�̸� >> ");
            String name = scanner.nextLine().trim();
            ArrayList<Customer> customers = customerRepository.getByName(name);
            if(customers.size() == 0) System.out.println("�ش��ϴ� �̸��� ���� ã�� ���߽��ϴ�.");
            else {
            	System.out.println();
            	System.out.println(customers.size() + "���� ���� ã�ҽ��ϴ�!!");
            	System.out.println();
            	customers.forEach(c -> {
                    System.out.println(c);
                    System.out.println();
                });
            }
            System.out.print("�� ��ȸ �׽�Ʈ�� ��� �Ͻðڽ��ϱ�? ��(1), �ƴϿ�(�� ��) >> ");
            String continueInput = scanner.nextLine().trim();
            if(continueInput.equals("1")) continue;
            else break;
        }
	}

	private static void testRetrieveAll() {
		System.out.println("///// Test for Customer, RetrieveAll /////");
		customerRepository.getAll().forEach(c -> {
            System.out.println(c);
            System.out.println();
        });
	}
}
