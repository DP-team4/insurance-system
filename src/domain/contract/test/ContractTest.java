package domain.contract.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Customer.Customer;
import domain.Insurance.FireInsurance;
import domain.Insurance.Insurance;
import domain.contract.Contract;
import repository.contract.ContractListImpl;
import repository.insurance.InsuranceListImpl;
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
	
	        // 테스트 데이터 생성
	        // Customer -> 아직 Customer 구현이 완료되지 않은 관계로 ArrayList를 만들어 테스트
	        for(int i=0; i<10; i++) {
	            customers.add(Customer.create("Customer" + i, 30 + i));
	        }
	        // Insurance
	        for (int i = 0; i < 3; i++) {
	            FireInsurance insurance = FireInsurance.create("Insurance" + i);
	            insurances.add(insurance);
	        }
	        // Contract
	        contractRepository.add(new Contract(customers.get(0), insurances.get(0), LocalDateTime.now(), LocalDateTime.now().plusDays(12)));
	        contractRepository.add(new Contract(customers.get(0), insurances.get(1), LocalDateTime.now(), LocalDateTime.now().plusDays(20)));
	
	        System.out.println("계약 목록:");
	        contractRepository.printAll();
	        System.out.print("작업할 계약의 ID를 입력하세요 >> ");
	        String contractId = scanner.nextLine().trim();
	
	        Contract contract = contractRepository.get(contractId);
	        if(contract == null){
	            System.out.println("잘못된 계약 ID입니다.");
	            return;
	        }
	
	        while(true) {
	            System.out.println("////// 해당 계약에 대한 작업을 시작합니다. //////");
	            System.out.println("승인(1), 거절(2), 인수심사 요청(3), 만기대상 (4), 뒤로가기 (0)");
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
	                    System.out.println("잘못된 입력입니다.");
	                    break;
	            }
	            System.out.println(contract);
	
	            System.out.println("테스트를 반복합니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
	            input = scanner.nextLine().trim();
	            if(!input.equals("1")) break;
	        }
	    }
	
	    private static void testMature() {
	    	System.out.println("만기대상 계약 목록:");
	        ContractService contractService = new ContractService();
	        contractService.getAllMatureContracts();
		}

		private static void testReject(Contract contract) {
	        System.out.println("Test for Contract, Reject");
	        contract.reject();
	        System.out.println("거절을 완료하였습니다. " + contract.getContractState().name());
	    }
	
	    private static void testAccept(Contract contract) {
	        System.out.println("Test for UW, Accept");
	        contract.accept();
	        System.out.println("승인를 완료하였습니다." + contract.getContractState().name());
	    }
	    private static void testRequestUW(Contract contract) {
	        System.out.println("Test for UW, Accept");
	        contract.requestUW();
	        System.out.println("인수심사 요청을 완료하였습니다." + contract.getContractState().name());
	    }
}

