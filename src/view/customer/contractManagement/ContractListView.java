package view.customer.contractManagement;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import domain.contract.Contract;
import domain.customer.Customer;
import domain.insurance.Clause;
import domain.insurance.Insurance;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import service.customer.MyContractManagementService;
import service.customer.MyContractManagementServiceImpl;

public class ContractListView {    
	// Service
    private MyContractManagementService myContractManagementService = MyContractManagementServiceImpl.getInstance();
    private InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();
	
	public boolean show(CancellationListView cancellationListView, Customer customer) {
		System.out.println("\n[ 나의 보험상품 목록 ]");
		// 로그인된 회원의 보험상품 정보를 요청한다
		ArrayList<Contract> contracts = myContractManagementService.getCustomerContracts(customer.getId());
		// A2. 가입한 보험이 0개인 경우
		if(contracts.size() == 0) { System.out.println("\n가입한 보험이 없습니다. 이전 화면으로 돌아갑니다."); return false; }
		for(Contract contract : contracts) {
			Insurance insurance = insuranceManagementService.getById(contract.getInsuranceId());
			if(insurance == null) {
				System.out.println("Exception : 계약의 보험 정보가 삭제되었습니다."); return false;// Exception 3
			}
			// 내 보험 관리 화면(보험상품명, 보험종류, 계약일, 만기일, 보험료, 해지 신청 버튼)을 출력한다.
			System.out.println();
			System.out.println("보험상품명 : " + insurance.getName());
			System.out.println("보험종류 : " + insurance.getInsuranceCategory().name());
			System.out.println("계약일 : " + contract.getContractDateTime());
			System.out.println("만기일 : " + contract.getExpirationDateTime());
			System.out.println("월 보험료 : " + calculatePremium(contract, insurance));
		}
		return true;
	}
	
	public double calculatePremium(Contract contract, Insurance insurance) {
		// 각 약관들 premium 합하기 = sum
		ArrayList<Clause> clauses = insurance.getClauses();
		long sum = 0;
		for(Clause clause: clauses) sum += clause.getPremium();
		
		// 납입 월 수
		LocalDateTime contractDateTime = contract.getContractDateTime();
		LocalDateTime expirationDateTime = contract.getExpirationDateTime();
		long month = ChronoUnit.MONTHS.between(contractDateTime, expirationDateTime);
		
		// sum / (납입 월 수)
		return (double) sum / month;
	}
}
