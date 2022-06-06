package view.CarAccidentHandlingManagement;

import service.CarAccidentHandlingManagementService;
import service.CarAccidentHandlingManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.util.Scanner;

public class CarAccidentHandlingManagementView extends View {

    private final Scanner scanner = ScannerUtility.getScanner();

    @Override
    public void show() {
        while(true) {
            System.out.println();
            System.out.println("================사고처리 요청 관리 화면================");
            System.out.println("사고처리 요청 접수(1), 사고처리 요청 전체 현황 확인(2), 뒤로가기(0)");
            System.out.print(">> ");
            String input = this.scanner.nextLine().trim();
            if(input.equals("0")) break;
            switch(input) {
                case "1": new CarAccidentHandlingProcessView().show();		break;
                case "2": new CarAccidentHandlingListView().show();		break;
                case "0": break;
                default:  System.out.println("잘못된 입력입니다.");		break;
            }
            System.out.println("반복됩니다. 계속하시겠습니가? 계속(1) 뒤로가기(그 외)");
            input = scanner.nextLine().trim();
            if(!input.equals("1")) break;
        }
    }

}
