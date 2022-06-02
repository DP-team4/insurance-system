package domain.insurance;

import domain.customer.Customer;

import java.util.ArrayList;
import java.util.StringJoiner;

public abstract class Insurance {
	private InsuranceCategory insuranceCategory;
	private ArrayList<Clause> clauses = new ArrayList<>();
	private String id;
	private String name;
	private InsuranceState insuranceState = InsuranceState.BEFORE_AUDIT;

	public abstract double calculateRatio(Customer customer);

	public void addClause(Clause clause) { this.clauses.add(clause); }
	public void removeClause(Clause clause) {this.clauses.remove(clause);}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Insurance ) {
			return ((Insurance) obj).id.equals(this.id);
		}
		else return false;
	}
	public boolean equalsAttributes(Object obj) {
		if(obj instanceof Insurance other &&
				other.id.equals(this.id) &&
				other.name.equals(this.name) &&
				other.insuranceState.equals(this.insuranceState) &&
				other.insuranceCategory.equals(this.insuranceCategory)
		) return true;
		else return false;
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

	// getters setters
	public InsuranceCategory getInsuranceCategory() {
		return insuranceCategory;
	}
	public void setInsuranceCategory(InsuranceCategory insuranceCategory) {
		this.insuranceCategory = insuranceCategory;
	}
	public ArrayList<Clause> getClauses() {
		return clauses;
	}
	public void setClauses(ArrayList<Clause> clauses) {
		this.clauses = clauses;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InsuranceState getInsuranceState() {
		return insuranceState;
	}
	public void setInsuranceState(InsuranceState insuranceState) {
		this.insuranceState = insuranceState;
	}
}