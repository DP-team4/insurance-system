package view;

import view.BenefitPaymentManagement.BenefitPaymentManagementView;
import view.CarAccidentHandlingManagement.CarAccidentHandlingManagementView;
import view.contractMangement.ContractIntergratedManagementView;
import view.insuranceManagement.InsuranceIntegratedManagementView;
import view.salesManagement.SalesMainView;
import view.uwManagement.UwIntegratedManagementView;
import view.viewUtility.View;

public class EmployeeView extends View {


    @Override
    public void show() {
        while (true) {
            System.out.println("///// ���� ���� /////");
            System.out.println("����(1), ���(2), UW(3), ����(4), ����(5), ���ó��(6), �ڷΰ���(exit)");
            String input = mScanner.getString();
            switch (input) {
                case "1":
                    new InsuranceIntegratedManagementView().show();
                    break;
                case "2":
                    new ContractIntergratedManagementView().show();
                    break;
                case "3":
                    new UwIntegratedManagementView().show();
                    break;
                case "4":
                    new SalesMainView().show();
                    break;
                case "5":
                    new BenefitPaymentManagementView().show();
                    break;
                case "6":
                    new CarAccidentHandlingManagementView().show();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("�߸��� �Է��Դϴ�. �Է�: " + input);
            }
        }
    }
}
