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
            System.out.println("///// ��� ���� /////");
            refreshClause();
            System.out.println(clause);

            System.out.println("�̸� ����(1), ī�װ� ����(2), ���谡�Ա� ����(3), ����� ����(4), ����(delete) �ڷΰ���(exit)");
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
                    System.out.println("�߸��� �Է��Դϴ�. �Է�: " + input);
                    break;
            }
        }
    }

    private void showDeletionView() {
        System.out.println("������ �����Ͻðڽ��ϱ�? yes/no");
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
                System.out.println("�߸��� �Է��Դϴ�.");
                return;
        }
    }

    private void showChangePremiumView() {
        while (true) {
            refreshClause();
            System.out.println(clause.getName() + "�� ����Ḧ �����մϴ�. ����: " + clause.getPremium());
            System.out.print("����� >> ");
            try {
                long premium = Long.parseLong(scanner.nextLine().trim());
                clause.setPremium(premium);
                insuranceManagementService.updateClause(clause);
                break;
            } catch (NumberFormatException e) {
                System.out.println("�߸��� �Է��Դϴ�.");
            }
        }
    }

    private void showChangeInsuredAmountView() {
        while (true) {
            refreshClause();
            System.out.println(clause.getName() + "�� ���谡�Ա��� �����մϴ�. ����: " + clause.getInsuredAmount());
            System.out.print("���谡�Ա� >> ");
            try {
                long insuredAmount = Long.parseLong(scanner.nextLine().trim());
                clause.setInsuredAmount(insuredAmount);
                insuranceManagementService.updateClause(clause);
                break;
            } catch (NumberFormatException e) {
                System.out.println("�߸��� �Է��Դϴ�.");
            }
        }
    }

    private void showChangeCategoryView() {
        while (true) {
            refreshClause();
            System.out.println(clause.getName() + "�� ī�װ��� �����մϴ�.");
            System.out.print("ī�װ� >> ");
            System.out.print("�Ϲݾ��(1), Ư�����(2) >> ");
            String input = scanner.nextLine().trim();
            ClauseCategory category = null;
            switch (input) {
                case "1" : category = ClauseCategory.NORMAL; break;
                case "2" : category = ClauseCategory.SPECIAL; break;
                default:
                    System.out.println("�߸��� �Է��Դϴ�. �Է�: " + input);
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
            System.out.println(clause.getName() + "�� �̸��� �����մϴ�.");
            System.out.print("�̸� >> ");
            String name = scanner.nextLine().trim();
            if(name.length() < 1) {
                System.out.println("������ �̸��� �� �� �����ϴ�.");
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
