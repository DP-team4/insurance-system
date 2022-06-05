package view;

import view.contractMangement.ContractIntergratedManagementView;
import view.insuranceManagement.InsuranceIntegratedManagementView;
import view.salesManagement.SalesMainView;
import view.uwManagement.UwIntegratedManagementView;
import view.viewUtility.View;

public class EmployeeView extends View {


    @Override
    public void show() {
        System.out.println("////////////////////// DP 2022-1 보험사조 //////////////////////");
        while (true) {
            System.out.println("///// 업무 선택 /////");
            System.out.println("보험(1), 계약(2), 보상(3), UW(4), 영업(5) 뒤로가기(exit)");
            String input = mScanner.getString();
            switch (input) {
                case "1":
                    new InsuranceIntegratedManagementView().show();
                    break;
                case "2":
                    new ContractIntergratedManagementView().show();
                    break;
                case "3":
                    System.out.println("추가 요망");
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
                    System.out.println("잘못된 입력입니다. 입력: " + input);
            }
        }
    }
}
