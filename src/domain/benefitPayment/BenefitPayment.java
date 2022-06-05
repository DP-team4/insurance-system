package domain.benefitPayment;

import java.time.LocalDateTime;

public class BenefitPayment {
    private String id;
    private String contractId;
    private LocalDateTime requestDate;
    private LocalDateTime accidentDate;
    private String accidentContent;
    private long totalPropertyLoss;
    private long totalPersonLoss;
    private long totalPropertyBenefit;
    private long totalPersonBenefit;
    private EBenefitPaymentState state;

    public boolean equalsAttributes(Object obj) {
        if(obj instanceof BenefitPayment) {
            BenefitPayment other = (BenefitPayment) obj;
            if (other.id.equals(this.id) && other.contractId.equals(this.contractId) &&
                    other.accidentDate.equals(this.accidentDate) &&
                    other.requestDate.equals(this.requestDate) &&
                    other.accidentContent.equals(this.accidentContent) &&
                    other.totalPropertyLoss==this.totalPropertyLoss &&
                    other.totalPersonLoss==this.totalPersonLoss &&
                    other.totalPropertyBenefit==this.totalPropertyBenefit &&
                    other.totalPersonBenefit==this.totalPersonBenefit)
                return true;
        }
        return false;
    }

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
    public long getTotalPropertyBenefit() {
        return totalPropertyBenefit;
    }
    public void setTotalPropertyBenefit(long totalPropertyBenefit) {
        this.totalPropertyBenefit = totalPropertyBenefit;
    }
    public long getTotalPersonBenefit() {
        return totalPersonBenefit;
    }
    public void setTotalPersonBenefit(long totalPersonBenefit) {
        this.totalPersonBenefit = totalPersonBenefit;
    }
    public long getTotalPropertyLoss() {
        return totalPropertyLoss;
    }
    public void setTotalPropertyLoss(long totalPropertyLoss) {
        this.totalPropertyLoss = totalPropertyLoss;
    }
    public long getTotalPersonLoss() {
        return totalPersonLoss;
    }
    public void setTotalPersonLoss(long totalPersonLoss) {
        this.totalPersonLoss = totalPersonLoss;
    }
    public EBenefitPaymentState getState() {
        return state;
    }
    public void setState(EBenefitPaymentState state) {
        this.state = state;
    }
}
