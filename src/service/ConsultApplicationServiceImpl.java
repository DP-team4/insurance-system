package service;

import repository.consultApplication.ConsultApplicationRepository;

public class ConsultApplicationServiceImpl implements ConsultApplicationService {
	private final static ConsultApplicationService consultApplicationService = new ConsultApplicationServiceImpl();
    private static final ConsultApplicationRepository consultApplicationRepository = ConsultApplicationRepository.getInstance();

	// Singleton
	private ConsultApplicationServiceImpl(){}
	public static ConsultApplicationService getInstance() { return consultApplicationService; }
	
	@Override
	public boolean applyConsultation(ConsultApplication consultApplication) {
		return consultApplicationRepository.add(consultApplication);
	}
}
