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
            System.out.println("================���ó�� ��û ���� ȭ��================");
            System.out.println("���ó�� ��û ����(1), ���ó�� ��û ��ü ��Ȳ Ȯ��(2), �ڷΰ���(0)");
            System.out.print(">> ");
            String input = this.scanner.nextLine().trim();
            if(input.equals("0")) break;
            switch(input) {
                case "1": new CarAccidentHandlingProcessView().show();		break;
                case "2": new CarAccidentHandlingListView().show();		break;
                case "0": break;
                default:  System.out.println("�߸��� �Է��Դϴ�.");		break;
            }
            System.out.println("�ݺ��˴ϴ�. ����Ͻðڽ��ϰ�? ���(1) �ڷΰ���(�� ��)");
            input = scanner.nextLine().trim();
            if(!input.equals("1")) break;
        }
    }

}
