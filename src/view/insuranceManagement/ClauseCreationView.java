package view.insuranceManagement;

import domain.insurance.Clause;
import domain.insurance.ClauseCategory;
import domain.insurance.Insurance;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.util.Scanner;

public class ClauseCreationView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private final InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();
    private String insuranceId;

    public ClauseCreationView(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    @Override
    public void show() {
        System.out.print("약관명 >> ");
        String name = scanner.nextLine().trim();
        System.out.print("가입금액 >> ");
        String insuredAmount = scanner.nextLine().trim();
        System.out.print("총 보험료 >> ");
        String premium = scanner.nextLine().trim();
        System.out.print("일반약관(1), 특별약관(그 외) >> ");
        String category = scanner.nextLine().trim();
        ClauseCategory clauseCategory = category.equals("1") ? ClauseCategory.NORMAL : ClauseCategory.SPECIAL;
        try {
            Clause clause = new Clause();
            clause.setName(name);
            clause.setInsuredAmount(Long.parseLong(insuredAmount));
            clause.setPremium(Long.parseLong(premium));
            clause.setClauseCategory(clauseCategory);
            clause.setInsuranceId(insuranceId);
            Insurance insurance = insuranceManagementService.getById(insuranceId);
            insurance.addClause(clause);
            insuranceManagementService.updateInsurance(insurance);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
        }
    }
}
