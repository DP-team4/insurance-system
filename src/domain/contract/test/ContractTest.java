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
	
	        // 테스트 데이터 생성
	        // Customer -> 아직 Customer 구현이 완료되지 않은 관계로 ArrayList를 만들어 테스트
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
	            if (!input.equals("4")) System.out.println(contract);
	
	            System.out.println("테스트를 반복합니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
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
	        System.out.println("거절을 완료하였습니다. " + contract.getContractState().name());
	    }
	
	    private static void testAccept(Contract contract) {
	        System.out.println("Test for UW, Accept");
	        contract.setContractState(ContractState.ACCEPTED);
	        System.out.println("승인를 완료하였습니다." + contract.getContractState().name());
	    }
	    private static void testRequestUW(Contract contract) {
	        System.out.println("Test for UW, Accept");
	        contract.setContractState(ContractState.UNDER_UW);
	        System.out.println("인수심사 요청을 완료하였습니다." + contract.getContractState().name());
	    }
}

