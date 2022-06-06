package view.CarAccidentHandlingManagement;

import domain.carAccidentHandling.CarAccidentHandling;
import domain.carAccidentHandling.ECarAccidentHandlingState;
import exception.InvalidInputException;
import service.CarAccidentHandlingManagementService;
import service.CarAccidentHandlingManagementServiceImpl;
import service.contractManagement.ContractManagementService;
import service.contractManagement.ContractManagementServiceImpl;
import view.viewUtility.ScannerUtility;
import view.viewUtility.View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class CarAccidentHandlingProcessView extends View {
    private final Scanner scanner = ScannerUtility.getScanner();
    private CarAccidentHandlingManagementService carAccidentHandlingManagementService = CarAccidentHandlingManagementServiceImpl.getInstance();
    private ContractManagementService contractManagementService = ContractManagementServiceImpl.getInstance();

    @Override
    public void show() {
        while(true) {
            try {
                System.out.println("\n================���ó�� ��û ���� ��� ȭ��================");
                if(!showOnReviewCarAccidentHandlingList())
                    break;
                showProcessRequestedCarAccidentHandling();
                System.out.println("���ó�� ��û ���� ��� ȭ���Դϴ�. ����Ͻ÷��� Y/y, ���ư��÷��� N/n�� �Է����ּ���.");
                String input = scanner.nextLine().trim();
                if(!(input.equals("Y")&&input.equals("y"))) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showProcessRequestedCarAccidentHandling() throws InvalidInputException {
        CarAccidentHandling carAccidentHandling = null;

        System.out.print("������ ���ó���� ID�� �Է��ϼ��� >> ");
        String input = scanner.nextLine().trim();
        carAccidentHandling = carAccidentHandlingManagementService.getById(input);
        if(carAccidentHandling == null)
            throw new InvalidInputException("�������� ���� ���ó�� ID �Դϴ�.");

        while(true) {
            System.out.println("////// �ش� ���ó���� �����մϴ�. //////");
            System.out.println("'����'ó��(1), '����'ó��(2), �ڷΰ��� (0)");
            input = scanner.nextLine().trim();
            boolean escape = false;
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    carAccidentHandling.setState(ECarAccidentHandlingState.HANDLED);
                    break;
                case "2":
                    carAccidentHandling.setState(ECarAccidentHandlingState.REFUSED);
                    //����� û�� �� �ڵ� ����
                    break;
                case "0":
                    break;
                default:
                    System.out.println("1, 2, 0 �߿��� �Է����ֽñ� �ٶ��ϴ�.");
                    break;
            }
            if(escape) break;
        }
    }

    private boolean showOnReviewCarAccidentHandlingList() {
        System.out.printf(" %s %s %s %s %s %s %s", "���ó�� ��ȣ", "����", "��û �ð�","��� �߻� �ð�","��� �߻� ��ġ", "��� ����");
        ArrayList<CarAccidentHandling> carAccidentHandlings = this.carAccidentHandlingManagementService.getAll();
        if(carAccidentHandlings.isEmpty()) return false;
        for(CarAccidentHandling e : carAccidentHandlings){
            if(!e.getState().equals(ECarAccidentHandlingState.ONREVIEW)) continue;
            String id = e.getId();
            String customerName = this.contractManagementService.getCustomer(this.contractManagementService.getContract(e.getContractId()).getCustomerId()).getName();
            LocalDateTime requestDate = e.getRequestDate();
            LocalDateTime accidentDate = e.getAccidentDate();
            String accidentLocation = e.getAccidentLocation();
            String accidentContent = e.getAccidentContent();

            System.out.printf("%s %s %s %s %s %s", id, customerName, requestDate, accidentDate, accidentLocation, accidentContent);
            System.out.println();
        }
        return true;
    }
}
