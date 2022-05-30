package domain.contract.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.customer.Customer;
import domain.insurance.FireInsurance;
import domain.insurance.Insurance;
import domain.contract.Contract;
import domain.contract.ContractState;
import repository.contract.ContractListImpl;
import service.ContractService;

public class ContractTest {
	private static final ArrayList<Customer> customers = new ArrayList<>();
	    private static final ContractListImpl contractRepository = ContractListImpl.getInstance();
	    private static final ArrayList<Insurance> insurances = new ArrayList<>();
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        test(scanner);
	        scanner.close();
	    }
	
	    public static void test(Scanner scanner) {
	        System.out.println("///// Test for Contract /////");
	
	        // �׽�Ʈ ������ ����
	        // Customer -> ���� Customer ������ �Ϸ���� ���� ����� ArrayList�� ����� �׽�Ʈ
	        for(int i=0; i<10; i++) {
	            customers.add(new Customer());
	        }
	        // Insurance
	        for (int i = 0; i < 4; i++) {
	            FireInsurance insurance = new FireInsurance();
	            insurances.add(insurance);
	        }
	        // Contract
	        for(int i = 0; i < 4; i++) {
	        	Contract contract = new Contract();
	        	contract.setId("Contract"+i);
	        	contract.setCustomerId(customers.get(i).getId());
	        	contract.setInsuranceId(insurances.get(i).getId());
	        	contract.setContractDateTime(LocalDateTime.now());
	        	contract.setExpirationDateTime(LocalDateTime.now().plusDays(i * 6));
	        	contractRepository.add(contract);
	        }
	
	        System.out.println("��� ���:");
	        contractRepository.printAll();
	        System.out.print("�۾��� ����� ID�� �Է��ϼ��� >> ");
	        String contractId = scanner.nextLine().trim();
	
	        Contract contract = contractRepository.get(contractId);
	        if(contract == null){
	            System.out.println("�߸��� ��� ID�Դϴ�.");
	            return;
	        }
	
	        while(true) {
	            System.out.println("////// �ش� ��࿡ ���� �۾��� �����մϴ�. //////");
	            System.out.println("����(1), ����(2), �μ��ɻ� ��û(3), ������ (4), �ڷΰ��� (0)");
	            String input = scanner.nextLine().trim();
	            if(input.equals("0")) break;
	            switch (input) {
	                case "1":
	                    testAccept(contract);
	                    break;
	                case "2":
	                    testReject(contract);
	                    break;
	                case "3":
	                	testRequestUW(contract);
	                    break;
	                case "4":
	                	testMature();
	                	break;
	                case "0":
	                	break;
	                default:
	                    System.out.println("�߸��� �Է��Դϴ�.");
	                    break;
	            }
	            if (!input.equals("4")) System.out.println(contract);
	
	            System.out.println("�׽�Ʈ�� �ݺ��մϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
	            input = scanner.nextLine().trim();
	            if(!input.equals("1")) break;
	        }
	    }
	
	    private static void testMature() {
	    	System.out.println("Test for Contract, Mature:");
	        ContractService contractService = new ContractService();
	        contractService.getAllMatureContracts();
		}

		private static void testReject(Contract contract) {
	        System.out.println("Test for Contract, Reject");
	        contract.setContractState(ContractState.REJECTED);
	        System.out.println("������ �Ϸ��Ͽ����ϴ�. " + contract.getContractState().name());
	    }
	
	    private static void testAccept(Contract contract) {
	        System.out.println("Test for UW, Accept");
	        contract.setContractState(ContractState.ACCEPTED);
	        System.out.println("���θ� �Ϸ��Ͽ����ϴ�." + contract.getContractState().name());
	    }
	    private static void testRequestUW(Contract contract) {
	        System.out.println("Test for UW, Accept");
	        contract.setContractState(ContractState.UNDER_UW);
	        System.out.println("�μ��ɻ� ��û�� �Ϸ��Ͽ����ϴ�." + contract.getContractState().name());
	    }
}

