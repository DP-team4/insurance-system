package view.uwManagement;

import domain.uw.UW;
import service.UwManagementService;
import service.UwManagementServiceImpl;
import view.viewUtility.View;

import java.util.ArrayList;

public class UwIntegratedManagementView extends View {
    private final UwManagementService uwManagementService = UwManagementServiceImpl.getInstance();
    ArrayList<UW> uws;
    UwListFilter condition;

    public UwIntegratedManagementView() {
        condition = UwListFilter.ONLY_UNDER_AUDIT;
        this.loadUwList();
    }

    @Override
    public void show() {
        System.out.println("///// UW ���հ��� /////");
        while(true){
            loadUwList();
            showUwListView();
            switch (this.condition) {
                case ALL:
                    System.out.println("�۾��� UW�� ID�� �Է��ϼ���. ����� UW�� ����(f), �ڷΰ���(exit)");
                    break;
                case ONLY_UNDER_AUDIT:
                    System.out.println("�۾��� UW�� ID�� �Է��ϼ���. ��ü ����(all), �ڷΰ���(exit)");
                    break;
            }
            System.out.print(">> ");
            String input = mScanner.getString();
            switch (input) {
                case "exit":
                    return;
                case "all":
                    this.condition = UwListFilter.ALL;
                    continue;
                case "f":
                    this.condition = UwListFilter.ONLY_UNDER_AUDIT;
                    continue;
                default:
                    for (UW uw : uws) {
                        if (uw.getId().equals(input)) {
                            new UwManagementView(uw.getId()).show();
                            break;
                        }
                    }
                    System.out.println("�ش��ϴ� ���̵��� UW�� ã�� ���߽��ϴ�. �Է��� Ȯ�����ּ���.");
                    break;
            }
        }
    }

    private void showUwListView() {
        if(uws.size() < 1) System.out.println("���� ������ UW�� �����ϴ�.");
        else uws.forEach(uw -> {
            System.out.println(uw);
            System.out.println();
        });
    }

    private void loadUwList() {
        switch (this.condition) {
            case ALL:
                this.uws = uwManagementService.getAll();
                break;
            case ONLY_UNDER_AUDIT:
                this.uws = uwManagementService.getAllUnderAudit();
                break;
        }
    }

    private enum UwListFilter{
        ALL, ONLY_UNDER_AUDIT
    }

    public static void main(String[] args) {
        new UwIntegratedManagementView().show();
    }
}
