package view.sales;

import java.time.LocalDateTime;
import java.util.Scanner;

import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;
import service.SalesService;

public class SalesActivityView {
	
	Scanner scanner;
	SalesService salesService;

	public SalesActivityView(Scanner scanner) {
		this.scanner = scanner;
		this.salesService = new SalesService();
	}

	public void showView() {
		while(true) {
			System.out.println("\n================영업활동 화면================");
			String insuranceId = this.showInsurance();
			String customerId = this.showCustomer();
			// 보험 가입 여부 및 금액 계산
			
			System.out.println("가입하시겠습니까? 예 (1) 아니오 (2)");
			String input = this.scanner.nextLine().trim();
			if(input.equals("1")) {
				this.showRequestUW(insuranceId, customerId);
			} else if(input.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력값입니다."); break;
			}
		}
	}

	private void showRequestUW(String insuranceId, String customerId) {
		Contract contract = new Contract();
		contract.setInsuranceId(insuranceId);
		contract.setCustomerId(customerId);
		contract.setContractDateTime(LocalDateTime.now());
		contract.setExpirationDateTime(LocalDateTime.now().plusDays(10));
		
		// 관련 서류 첨부
		String name;
		String file;
		System.out.println("인수 심사 관련 서류를 첨부하시오.");
		System.out.println("이름: ");
		name = scanner.nextLine().trim();
		System.out.println("파일 경로: ");
		file = scanner.nextLine().trim();
		// 인수심사 요청 ?
		this.salesService.requestUW(contract);
		
	}

	private String showInsurance() {
		System.out.println("보험 이름을 입력하시오.");
		System.out.print(">> ");
		String input = this.scanner.nextLine().trim();
		Insurance insurance = this.salesService.requestInsurance(input);
		System.out.println("[보험 정보]");
		System.out.println(insurance);
		return insurance.getId();
	}

	private String showCustomer() {
		String customerId;
		// 이름, 나이, 성별로 고객 찾기 => ?
		while(true) {
			System.out.println("고객 정보를 입력하시오.");
			String name;
			boolean gender;
			String age;
			
			System.out.println("이름: ");
			name = (this.scanner.nextLine().trim());
			System.out.println("성별(W=0, M=1): ");
			String input = this.scanner.nextLine().trim();
			if (input.equals("0")) { gender = false;} 
			else if(input.equals("1")) { gender = true; }
			else { System.out.println("잘못된 입력값입니다."); continue; }
			System.out.println("나이: ");
			age = this.scanner.nextLine().trim();	
			
			Customer customer = this.salesService.requestCustomer();
			System.out.println("[고객 정보]");
			System.out.println(customer);
			customerId = customer.getId();
			break;
		}
		return customerId;
	}

}
