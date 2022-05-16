package domain.Insurance;

/**
 * @author wls43
 * @version 1.0
 * @created 09-5-2022 ¿ÀÈÄ 4:48:25
 */
public class Clause {
	private ClauseCategory clauseCategory;
	private String id = "Clause" + System.currentTimeMillis();
	private int insuredAmount;
	private int premium;
	private String name;

	private Clause(){}
	public Clause(String name, int insuredAmount, int premium, ClauseCategory clauseCategory) {
		this.name=name; this.insuredAmount=insuredAmount;
		this.premium = premium;
	}

	public static Clause create(String name, int insuredAmount, int premium, ClauseCategory clauseCategory) {
		return new Clause(name, insuredAmount, premium, clauseCategory);
	}

	// getters
	public ClauseCategory getClauseCategory() {
		return clauseCategory;
	}
	public String getId() {
		return id;
	}
	public int getInsuredAmount() {
		return insuredAmount;
	}
	public String getName() {
		return name;
	}
	public int getPremium() {
		return premium;
	}
}