package view.salesManagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Insurance;
import domain.uw.UWDocument;
import service.SalesService;
import service.SalesServiceImpl;
import view.viewUtility.ScannerUtility;

public class SalesActivityView {
	
	private final Scanner scanner = ScannerUtility.getScanner();
	private final SalesService salesService = SalesServiceImpl.getInstance();
	private Insurance insurance;
	private Customer customer;
	
	public SalesActivityView() {}

	public void show() {
		while(true) {
			System.out.println("\n================영업활동 화면================");
			this.showInsurance();
			this.showCustomer();
			// 월 납입료, 보상금액
			this.showInsurancePremiumAndInsuredAmount();
			
			System.out.println("가입하시겠습니까? 예 (1) 아니오 (2)");
			String input = this.scanner.nextLine().trim();
			if(input.equals("1")) {
				this.showRequestUW(this.insurance.getId(), this.customer.getId());
			} else if(input.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력값입니다."); break;
			}
		}
	}

	private void showInsurancePremiumAndInsuredAmount() {
		System.out.println("[보험 가입 정보]");
		long insurancePremium = this.salesService.getInsurancePremium(this.insurance, this.customer);
		long insuredAmount = this.salesService.getInsuredAmount(insurance, customer);
		System.out.println("월 납입료: " + insurancePremium);
		System.out.println("보상 금액: " + insuredAmount);
	}

	private void showRequestUW(String insuranceId, String customerId) {
		System.out.println("계약기간을 입력하시오. ex)60, 70, 100,,,");
		System.out.print(">> ");
		int days = Integer.parseInt(this.scanner.nextLine().trim());
		
		System.out.println("[인수심사 요청]");
		Contract contract = new Contract();
		contract.setInsuranceId(insuranceId);
		contract.setCustomerId(customerId);
		contract.setContractDateTime(LocalDateTime.now());
		contract.setExpirationDateTime(LocalDateTime.now().plusDays(days));
		// 관련 서류 첨부
		ArrayList<UWDocument> documents = new ArrayList<>();
		while(true) {
			System.out.println("인수 심사 관련 서류를 첨부하시오.");
			UWDocument uwDocument = new UWDocument();
			System.out.println("이름: ");
			String name = scanner.nextLine().trim();
			uwDocument.setName(name);
			System.out.println("파일 경로: ");
			String filePath = scanner.nextLine().trim();
			uwDocument.setPath(filePath);	
			documents.add(uwDocument);
			
			System.out.println("서류를 더 첨부하시겠습니까? 예 (1) 아니오(나머지)");
			String input = this.scanner.nextLine().trim();
			if(!input.equals(1)) {
				break;
			}
		}
		// 인수심사 요청
		boolean result = this.salesService.requestUW(contract, documents);
		if(result) {
			System.out.println("인수 심사 접수가 완료되었습니다.");
		} else {}
		
	}

	private void showInsurance() {
		System.out.println("보험 이름을 입력하시오.");
		System.out.print(">> ");
		String input = this.scanner.nextLine().trim();
		Insurance insurance = this.salesService.getInsuranceByName(input);
		System.out.println("[보험 정보]");
		System.out.println(insurance);
		this.insurance = insurance;
	}

	private void showCustomer() {
		String email;
		// 이름, 나이, 성별로 고객 찾기 => 이메일
		System.out.println("고객 이메일을 입력하시오.");
		System.out.println("이메일: ");
		email = (this.scanner.nextLine().trim());

		Customer customer = this.salesService.getCustomerByEmail(email);
		System.out.println("[고객 정보]");
		System.out.println(customer);
		this.customer = customer;
	}

}
