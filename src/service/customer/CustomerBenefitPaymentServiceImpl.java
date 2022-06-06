package service.customer;

import java.util.ArrayList;

import domain.benefitPayment.BenefitPayment;
import domain.contract.Contract;
import repository.benefitPayment.BenefitPaymentRepository;
import repository.contract.ContractRepository;

public class CustomerBenefitPaymentServiceImpl implements CustomerBenefitPaymentService {
	private final static CustomerBenefitPaymentService instance = new CustomerBenefitPaymentServiceImpl();
    private static final BenefitPaymentRepository benefitPaymentRepository = BenefitPaymentRepository.getInstance();
    private static final ContractRepository contractRepository = ContractRepository.getInstance();

	// Singleton
	private CustomerBenefitPaymentServiceImpl(){}
	public static CustomerBenefitPaymentService getInstance() { return instance; }
	
	@Override
	public boolean applyBenefitPayment(BenefitPayment benefitPayment) {
		return benefitPaymentRepository.create(benefitPayment);
	}

	@Override
	public boolean revokeMyBenefitPayment(String id, String customerId) {
		BenefitPayment benefitPayment = benefitPaymentRepository.getById(id);
		if(benefitPayment == null) return false;
		Contract contract = contractRepository.get(benefitPayment.getContractId());
		if(contract.getCustomerId().equals(customerId)) return benefitPaymentRepository.delete(id);
		return false;
	}
	
	@Override
	public ArrayList<BenefitPayment> getByCustomerId(String customerId) {
		ArrayList<BenefitPayment> benefitPayments = new ArrayList<>();
		for(BenefitPayment b : benefitPaymentRepository.getAll()) {
			Contract contract = contractRepository.get(b.getContractId());
			if(contract.getCustomerId().equals(customerId)) {
				benefitPayments.add(b);
			}
		}
		return benefitPayments;
	}

}
