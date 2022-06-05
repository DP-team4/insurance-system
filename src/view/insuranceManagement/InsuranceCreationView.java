package view.insuranceManagement;

import domain.insurance.*;
import domain.insurance.test.CarInsuranceTest;
import domain.insurance.test.DriverInsuranceTest;
import domain.insurance.test.FireInsuranceTest;
import domain.insurance.test.MarineInsuranceTest;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.util.ArrayList;
import java.util.Scanner;

public class InsuranceCreationView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private final InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();


    @Override
    public void show() {
        System.out.println("///// 새로운 보험을 생성합니다. /////");
        System.out.print("종류 화재(1), 운전자(2), 해상(3), 자동차(4) >> ");
        String category = scanner.nextLine().trim();
        switch (category) {
            case "1":
                showCreation(new FireInsurance());
                break;
            case "2":
                showCreation(new DriverInsurance());
                break;
            case "3":
                showCreation(new MarineInsurance());
                break;
            case "4":
                showCreation(new CarInsurance());
                break;
            default:
                System.out.println("종류에 대한 입력이 올바르지 않습니다. 입력: " + category);
                break;
        }
        return;
    }

    private void showCreation(Insurance insurance) {
        System.out.print("보험명 >> ");
        String name = scanner.nextLine().trim();

        insurance.setName(name);
        while(true) {
            System.out.println("약관을 추가하시겠습니까? 예(1) 아니오(그 외)");
            String input = scanner.nextLine().trim();
            if (!input.equals("1")) break;
            System.out.print("약관명 >> ");
            String clauseName = scanner.nextLine().trim();
            System.out.print("가입금액 >> ");
            String insuredAmount = scanner.nextLine().trim();
            System.out.print("총 보험료 >> ");
            String premium = scanner.nextLine().trim();
            System.out.print("일반약관(1), 특별약관(그 외) >> ");
            String category = scanner.nextLine().trim();
            ClauseCategory clauseCategory = category.equals("1") ? ClauseCategory.NORMAL : ClauseCategory.SPECIAL;
            try {
                Clause clause = new Clause();
                clause.setName(clauseName);
                clause.setInsuredAmount(Long.parseLong(insuredAmount));
                clause.setPremium(Long.parseLong(premium));
                clause.setClauseCategory(clauseCategory);
                insurance.addClause(clause);
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
        insuranceManagementService.addInsurance(insurance);
    }

}
