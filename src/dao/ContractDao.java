package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import domain.consultApplication.ConsultApplication;
import domain.consultApplication.ConsultApplicationState;
import domain.contract.Contract;
import domain.contract.ContractState;

public class ContractDao extends Dao {

	public ContractDao() {
		super.connect();
	}

	public String createAndGetId(Contract contract) {
		String query = String.format("insert into contract values (0, '%s', '%s', '%s', '%s', '%s')",
				Timestamp.valueOf(contract.getContractDateTime()), Timestamp.valueOf(contract.getExpirationDateTime()),
				contract.getCustomerId(), contract.getInsuranceId(), contract.getState().name());
		return super.createAndGetId(query);
	}

	public boolean create(Contract contract) {
		String query = String.format("insert into contract values (0, '%s', '%s', '%s', '%s', '%s')",
				Timestamp.valueOf(contract.getContractDateTime()), Timestamp.valueOf(contract.getExpirationDateTime()),
				contract.getCustomerId(), contract.getInsuranceId(), contract.getState().name());
		return super.create(query);
	}

	public boolean update(Contract contract) {
		String query = String.format(
				"update contract set contract_datetime='%s', expiration_datetime='%s', customer_id=%s, insurance_id=%s, state='%s' where id=%s",
				Timestamp.valueOf(contract.getContractDateTime()), Timestamp.valueOf(contract.getExpirationDateTime()),
				contract.getCustomerId(), contract.getInsuranceId(), contract.getState().name(), contract.getId()
				);
		return super.update(query);
	}

	public boolean delete(String id) {
		String query = String.format("delete from contract where id=%s", id);
		return super.delete(query);
	}

	public ArrayList<Contract> retrieveAll() {
		try {
			String query = "select * from contract";
			ResultSet resultSet = super.retrieve(query);
			if (resultSet == null)
				return null;
			ArrayList<Contract> contracts = new ArrayList<>();
			while (resultSet.next()) {
				Contract contract = getCurrentContract(resultSet);
				contracts.add(contract);
			}
			return contracts;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public Contract retrieveById(String id) {
		try {
			String query = String.format("select * from contract where id=%s", id);
			ResultSet resultSet = super.retrieve(query);
			if (resultSet == null || !resultSet.next())
				return null;
			Contract contract = getCurrentContract(resultSet);
			return contract;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Contract> retrieveByCustomerId(String customerId) {
		try {
			String query = String.format("select * from contract where customer_id='%s'", customerId);
			ResultSet resultSet = super.retrieve(query);
			if (resultSet == null)
				return null;
			ArrayList<Contract> contracts = new ArrayList<>();
			while (resultSet.next()) {
				Contract contract = getCurrentContract(resultSet);
				contracts.add(contract);
			}
			return contracts;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	private Contract getCurrentContract(ResultSet resultSet) throws SQLException {
		String id = resultSet.getString("id");
		LocalDateTime contractDateTime = resultSet.getTimestamp("contract_datetime").toLocalDateTime();
		LocalDateTime expirationDateTime = resultSet.getTimestamp("expiration_datetime").toLocalDateTime();
		String customerId = resultSet.getString("customer_id");
		String insuranceId = resultSet.getString("insurance_id");
		ContractState state = ContractState.valueOf(resultSet.getString("state"));

		Contract contract = new Contract();
		contract.setId(id);
		contract.setContractDateTime(contractDateTime);
		contract.setExpirationDateTime(expirationDateTime);
		contract.setCustomerId(customerId);
		contract.setInsuranceId(insuranceId);
		contract.setState(state);
		return contract;
	}

}
