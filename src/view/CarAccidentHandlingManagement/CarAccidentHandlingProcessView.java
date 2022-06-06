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
                System.out.println("\n================사고처리 요청 접수 목록 화면================");
                if(!showOnReviewCarAccidentHandlingList())
                    break;
                showProcessRequestedCarAccidentHandling();
                System.out.println("사고처리 요청 접수 목록 화면입니다. 계속하시려면 Y/y, 돌아가시려면 N/n을 입력해주세요.");
                String input = scanner.nextLine().trim();
                if(!(input.equals("Y")&&input.equals("y"))) break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showProcessRequestedCarAccidentHandling() throws InvalidInputException {
        CarAccidentHandling carAccidentHandling = null;

        System.out.print("접수할 사고처리의 ID를 입력하세요 >> ");
        String input = scanner.nextLine().trim();
        carAccidentHandling = carAccidentHandlingManagementService.getById(input);
        if(carAccidentHandling == null)
            throw new InvalidInputException("유요하지 않은 사고처리 ID 입니다.");

        while(true) {
            System.out.println("////// 해당 사고처리를 접수합니다. //////");
            System.out.println("'접수'처리(1), '거절'처리(2), 뒤로가기 (0)");
            input = scanner.nextLine().trim();
            boolean escape = false;
            if(input.equals("0")) break;
            switch (input) {
                case "1":
                    carAccidentHandling.setState(ECarAccidentHandlingState.HANDLED);
                    break;
                case "2":
                    carAccidentHandling.setState(ECarAccidentHandlingState.REFUSED);
                    //보험금 청구 건 자동 생성
                    break;
                case "0":
                    break;
                default:
                    System.out.println("1, 2, 0 중에서 입력해주시기 바랍니다.");
                    break;
            }
            if(escape) break;
        }
    }

    private boolean showOnReviewCarAccidentHandlingList() {
        System.out.printf(" %s %s %s %s %s %s %s", "사고처리 번호", "고객명", "요청 시각","사고 발생 시각","사고 발생 위치", "사고 내용");
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
