package view;

import domain.insurance.Clause;
import domain.insurance.ClauseCategory;
import domain.insurance.Insurance;
import service.InsuranceManagementService;
import service.InsuranceManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.util.Scanner;

public class ClauseManagementView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private final InsuranceManagementService insuranceManagementService = InsuranceManagementServiceImpl.getInstance();
    private Clause clause;

    public ClauseManagementView(String clauseId) {
        this.clause = insuranceManagementService.getClauseById(clauseId);
    }

    @Override
    public void show() {
        while (true) {
            System.out.println("///// 약관 관리 /////");
            refreshClause();
            System.out.println(clause);

            System.out.println("이름 변경(1), 카테고리 변경(2), 보험가입금 변경(3), 보험료 변경(4), 삭제(delete) 뒤로가기(exit)");
            System.out.print(">>");
            String input = scanner.nextLine().trim();
            if(input.equals("exit")) break;
            switch (input) {
                case "1":
                    showRenameView();
                    break;
                case "2":
                    showChangeCategoryView();
                    break;
                case "3":
                    showChangeInsuredAmountView();
                    break;
                case "4":
                    showChangePremiumView();
                    break;
                case"delete":
                    showDeletionView();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 입력: " + input);
                    break;
            }
        }
    }

    private void showDeletionView() {
        System.out.println("정말로 삭제하시겠습니까? yes/no");
        String input = scanner.nextLine().trim();
        switch (input) {
            case "yes":
                Insurance insurance = insuranceManagementService.getById(clause.getInsuranceId());
                insurance.removeClause(clause);
                insuranceManagementService.updateInsurance(insurance);
                break;
            case "no":
                return;
            default:
                System.out.println("잘못된 입력입니다.");
                return;
        }
    }

    private void showChangePremiumView() {
        while (true) {
            refreshClause();
            System.out.println(clause.getName() + "의 보험료를 변경합니다. 기존: " + clause.getPremium());
            System.out.print("보험료 >> ");
            try {
                long premium = Long.parseLong(scanner.nextLine().trim());
                clause.setPremium(premium);
                insuranceManagementService.updateClause(clause);
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void showChangeInsuredAmountView() {
        while (true) {
            refreshClause();
            System.out.println(clause.getName() + "의 보험가입금을 변경합니다. 기존: " + clause.getInsuredAmount());
            System.out.print("보험가입금 >> ");
            try {
                long insuredAmount = Long.parseLong(scanner.nextLine().trim());
                clause.setInsuredAmount(insuredAmount);
                insuranceManagementService.updateClause(clause);
                break;
            } catch (NumberFormatException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }

    private void showChangeCategoryView() {
        while (true) {
            refreshClause();
            System.out.println(clause.getName() + "의 카테고리을 변경합니다.");
            System.out.print("카테고리 >> ");
            System.out.print("일반약관(1), 특별약관(2) >> ");
            String input = scanner.nextLine().trim();
            ClauseCategory category = null;
            switch (input) {
                case "1" : category = ClauseCategory.NORMAL; break;
                case "2" : category = ClauseCategory.SPECIAL; break;
                default:
                    System.out.println("잘못된 입력입니다. 입력: " + input);
                    continue;
            }
            clause.setClauseCategory(category);
            insuranceManagementService.updateClause(clause);
            break;
        }
    }

    private void showRenameView() {
        while (true) {
            refreshClause();
            System.out.println(clause.getName() + "의 이름을 변경합니다.");
            System.out.print("이름 >> ");
            String name = scanner.nextLine().trim();
            if(name.length() < 1) {
                System.out.println("공백은 이름이 될 수 없습니다.");
                continue;
            } else {
                clause.setName(name);
                insuranceManagementService.updateClause(clause);
                break;
            }
        }
    }

    private void refreshClause() {
        this.clause = insuranceManagementService.getClauseById(clause.getId());
    }
}
