package domain.Insurance;

import domain.Customer.Customer;

import java.util.ArrayList;
import java.util.StringJoiner;

public abstract class Insurance {
	private InsuranceCategory insuranceCategory;
	private ArrayList<Clause> clauses = new ArrayList<>();
	private String id = "Insurance" + System.currentTimeMillis();  // insuranceID, insuranceId
	private String name;
	private InsuranceState insuranceState;

	public abstract double calculateRatio(Customer customer);

	public void setDefault(String name, InsuranceCategory insuranceCategory) {
		this.name = name;
		this.insuranceCategory = insuranceCategory;
		this.insuranceState = InsuranceState.BEFORE_AUDIT;
	}
	public void addClause(Clause clause) { this.clauses.add(clause); }
	public void startSales() {
		this.insuranceState = InsuranceState.ON_SALE;
	}
	public void stopSales() {
		this.insuranceState = InsuranceState.END_SALE;
	}
	public void startAudition() {
		this.insuranceState = InsuranceState.UNDER_AUDIT;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Insurance) {
			return (((Insurance) obj).getId().equals(this.id));
		}
		return false;
	}
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("이름: " + this.name).add("종류: " + this.insuranceCategory).add("상태: " + this.insuranceState);

		for (Clause clause : clauses) {
			sj.add("약관: " + clause.getName());
		}
		return sj.toString();
	}

	// getters
	public ArrayList<Clause> getClauses() { return clauses; }
	public String getId() { return id; }
	public String getName() { return name; }
	public InsuranceCategory getInsuranceCategory() { return insuranceCategory; }
	public InsuranceState getInsuranceState() { return insuranceState; }

}