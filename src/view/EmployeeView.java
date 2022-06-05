package view;

import view.contractMangement.ContractIntergratedManagementView;
import view.insuranceManagement.InsuranceIntegratedManagementView;
import view.salesManagement.SalesMainView;
import view.uwManagement.UwIntegratedManagementView;
import view.viewUtility.View;

public class EmployeeView extends View {


    @Override
    public void show() {
        System.out.println("////////////////////// DP 2022-1 ������� //////////////////////");
        while (true) {
            System.out.println("///// ���� ���� /////");
            System.out.println("����(1), ���(2), ����(3), UW(4), ����(5) �ڷΰ���(exit)");
            String input = mScanner.getString();
            switch (input) {
                case "1":
                    new InsuranceIntegratedManagementView().show();
                    break;
                case "2":
                    new ContractIntergratedManagementView().show();
                    break;
                case "3":
                    System.out.println("�߰� ���");
                    break;
                case "4":
                    new UwIntegratedManagementView().show();
                    break;
                case "5":
                    new SalesMainView().show();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("�߸��� �Է��Դϴ�. �Է�: " + input);
            }
        }
    }
}
