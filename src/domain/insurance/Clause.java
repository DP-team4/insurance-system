package domain.insurance;

import java.util.StringJoiner;

/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 오후 4:48:25
 */
public class Clause {
	private ClauseCategory clauseCategory;
	private String id;
	private long insuredAmount;
	private long premium;
	private String name;
	private String insuranceId;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Clause) {
			return ((Clause) obj).id.equals(this.id);
		}
		return false;

	}

	public boolean equalsAttributes(Object obj) {
		if (obj instanceof Clause ) {
			Clause other = (Clause) obj;
			if(other.id.equals(this.id) &&
					other.name.equals(this.name) &&
					other.clauseCategory.equals(this.clauseCategory) &&
					other.insuredAmount == this.insuredAmount &&
					other.premium == this.premium) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		sj.add("ID: " + this.id).add("약관 이름: " + this.name).add("종류: " + this.clauseCategory).add("가입금액 " + this.insuredAmount).add("보험료: " + this.premium);
		return sj.toString();
	}

	// getters setters
	public ClauseCategory getClauseCategory() {
		return clauseCategory;
	}
	public void setClauseCategory(ClauseCategory clauseCategory) {
		this.clauseCategory = clauseCategory;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getInsuredAmount() {
		return insuredAmount;
	}
	public void setInsuredAmount(long insuredAmount) {
		this.insuredAmount = insuredAmount;
	}
	public long getPremium() {
		return premium;
	}
	public void setPremium(long premium) {
		this.premium = premium;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}
}