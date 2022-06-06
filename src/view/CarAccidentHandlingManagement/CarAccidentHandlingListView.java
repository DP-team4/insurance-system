package view.CarAccidentHandlingManagement;

import domain.carAccidentHandling.CarAccidentHandling;
import domain.carAccidentHandling.ECarAccidentHandlingState;
import service.CarAccidentHandlingManagementService;
import service.CarAccidentHandlingManagementServiceImpl;
import service.contractManagement.ContractManagementService;
import service.contractManagement.ContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CarAccidentHandlingListView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private CarAccidentHandlingManagementService carAccidentHandlingManagementService = CarAccidentHandlingManagementServiceImpl.getInstance();
    private ContractManagementService contractManagementService = ContractManagementServiceImpl.getInstance();

    @Override
    public void show() {
        while (true) {
            System.out.println("\n================���ó�� ��û ���� ��ü ��Ȳ ��ȸ ȭ��================");
            if (!showOnReviewCarAccidentHandlingList())
                break;
            System.out.println("�ٽ� ��ȸ�Ϸ��� Y Ȥ�� y, ���ư��÷��� �� �� �ƹ�Ű�� �Է��ϼ���.");
            String input = scanner.nextLine().trim();
            if (!(input.equals("Y") && input.equals("y"))) break;
        }
    }

    private boolean showOnReviewCarAccidentHandlingList() {
        System.out.printf(" %s %s %s %s %s %s %s", "���ó�� ��ȣ", "����", "��û �ð�","��� �߻� �ð�","��� �߻� ��ġ", "��� ����", "���� ��Ȳ");
        ArrayList<CarAccidentHandling> carAccidentHandlings = this.carAccidentHandlingManagementService.getAll();
        if(carAccidentHandlings.isEmpty()) return false;
        for(CarAccidentHandling e : carAccidentHandlings){
            String id = e.getId();
            String customerName = this.contractManagementService.getCustomer(this.contractManagementService.getContract(e.getContractId()).getCustomerId()).getName();
            LocalDateTime requestDate = e.getRequestDate();
            LocalDateTime accidentDate = e.getAccidentDate();
            String accidentLocation = e.getAccidentLocation();
            String accidentContent = e.getAccidentContent();
            String state = e.getState().getTitle();
            System.out.printf("%s %s %t %t %s %s %s", id, customerName, requestDate, accidentDate, accidentLocation, accidentContent, state);
            System.out.println();
        }
        return true;
    }
}
