package domain.benefitPayment;

import java.time.LocalDateTime;

public class BenefitPayment {
    private String id;
    private String contractId;
    private LocalDateTime requestDate;
    private LocalDateTime accidentDate;
    private String accidentContent;
    private int totalPropertyLoss;
    private int totalPersonLoss;
    private int totalPropertyBenefit;
    private int totalPersonBenefit;
    private EBenefitPaymentState state;

    //GTRSTR
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContractId() {
        return contractId;
    }
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public LocalDateTime getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
    public LocalDateTime getAccidentDate() {
        return accidentDate;
    }
    public void setAccidentDate(LocalDateTime accidentDate) {
        this.accidentDate = accidentDate;
    }
    public String getAccidentContent() {
        return accidentContent;
    }
    public void setAccidentContent(String accidentContent) {
        this.accidentContent = accidentContent;
    }
    public int getTotalPropertyBenefit() {
        return totalPropertyBenefit;
    }
    public void setTotalPropertyBenefit(int totalPropertyBenefit) {
        this.totalPropertyBenefit = totalPropertyBenefit;
    }
    public int getTotalPersonBenefit() {
        return totalPersonBenefit;
    }
    public void setTotalPersonBenefit(int totalPersonBenefit) {
        this.totalPersonBenefit = totalPersonBenefit;
    }
    public int getTotalPropertyLoss() {
        return totalPropertyLoss;
    }
    public void setTotalPropertyLoss(int totalPropertyLoss) {
        this.totalPropertyLoss = totalPropertyLoss;
    }
    public int getTotalPersonLoss() {
        return totalPersonLoss;
    }
    public void setTotalPersonLoss(int totalPersonLoss) {
        this.totalPersonLoss = totalPersonLoss;
    }
    public EBenefitPaymentState getState() {
        return state;
    }
    public void setState(EBenefitPaymentState state) {
        this.state = state;
    }
}
