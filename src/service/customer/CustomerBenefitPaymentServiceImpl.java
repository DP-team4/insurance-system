package service.customer;

import java.util.ArrayList;

import dao.ContractDao;
import domain.benefitPayment.BenefitPayment;
import domain.contract.Contract;
import repository.benefitPayment.BenefitPaymentRepository;

public class CustomerBenefitPaymentServiceImpl implements CustomerBenefitPaymentService {
	private final static CustomerBenefitPaymentService instance = new CustomerBenefitPaymentServiceImpl();
    private static final BenefitPaymentRepository benefitPaymentRepository = BenefitPaymentRepository.getInstance();
    private static final ContractDao contractDao = new ContractDao();

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
		Contract contract = contractDao.retrieveById(benefitPayment.getContractId());
		if(contract.getCustomerId().equals(customerId)) return benefitPaymentRepository.delete(id);
		return false;
	}
	
	@Override
	public ArrayList<BenefitPayment> getByCustomerId(String customerId) {
		ArrayList<BenefitPayment> benefitPayments = new ArrayList<>();
		for(BenefitPayment b : benefitPaymentRepository.getAll()) {
			Contract contract = contractDao.retrieveById(b.getContractId());
			if(contract.getCustomerId().equals(customerId)) {
				benefitPayments.add(b);
			}
		}
		return benefitPayments;
	}

}
