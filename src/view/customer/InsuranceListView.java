package view.customer;

import java.util.ArrayList;
import java.util.Scanner;

import domain.insurance.Clause;
import domain.insurance.Insurance;
import domain.insurance.InsuranceCategory;
import service.customer.CustomerInsuranceRetrieveService;
import service.customer.CustomerInsuranceRetrieveServiceImpl;
import view.viewUtility.ScannerUtility;

public class InsuranceListView {
    private final Scanner scanner = ScannerUtility.getScanner();
    
    // Service
    private final CustomerInsuranceRetrieveService customerInsuranceRetrieveService = CustomerInsuranceRetrieveServiceImpl.getInstance();
	
	public void show() {
		System.out.println("\n[ 보험상품 조회 ]");
		// 가입할 보험상품(해상보험, 자동차보험, 운전자보험, 화재보험)을 선택하고 검색버튼을 클릭한다(A1, A2, E1, E2)
		System.out.println("조회할 보험상품 종류를 선택해주세요 -");
		System.out.print("해상보험(1), 자동차보험(2), 운전자보험(3), 화재보험(4), 전체조회(5) >> ");
		String input = scanner.nextLine().trim();
        // 검색 조건 입력 여부를 체크한다
		// A1. 검색 조건을 덜 입력한 경우 -> '검색 조건을 입력해주세요' 메시지를 보여준다
		if(input.equals("")) System.out.println("\n검색 조건을 입력해주세요");
		else {
	        // 검색 조건에 맞는 판매중인 보험상품 정보를 요청한다
	        ArrayList<Insurance> insurances = customerInsuranceRetrieveService.getInsurancesOnSale();
	        InsuranceCategory insuranceCategory = null;
			switch(input) {
				case "1": insuranceCategory = InsuranceCategory.MARINE; break;
				case "2": insuranceCategory = InsuranceCategory.CAR; break;
				case "3": insuranceCategory = InsuranceCategory.DRIVER; break;
				case "4": insuranceCategory = InsuranceCategory.FIRE; break;
				case "5": insuranceCategory = null; break;
				// A3. 올바르지 않은 검색 조건을 입력한 경우 -> "입력값이 유효하지 않습니다" 메시지를 보여준다
				default : System.out.println("\n입력값이 유효하지 않습니다."); return;
			}
			insurances = customerInsuranceRetrieveService.filterInsuranceByCategory(insurances, insuranceCategory);
	        // 보험목록(보험상품명, 보험종류, 약관명, 가입금액, 보험료)을 보여준다
	        // A2. 검색 조건에 해당하는 보험상품이 0개인 경우 -> 메시지 '고객님의 조건에 맞는 상품이 없습니다'를 보여준다
	        if(insurances.size() == 0) { System.out.println("고객님의 조건에 맞는 상품이 없습니다. 메뉴화면으로 돌아갑니다."); return; }
            insurances.forEach(i -> {
            	ArrayList<Clause> clauses = i.getClauses();
                System.out.println("\n보험상품명: " + i.getName());
            	System.out.println("<약관 목록>");
                for(Clause clause : clauses) {
                	System.out.print("약관명: " + clause.getName() + ", ");
	                System.out.print("가입금액: " + clause.getInsuredAmount() + ", ");
	                System.out.println("총보험료: " + clause.getPremium());
                }
            });
		}
	}
}
