package dtupayservice;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class BankService {
	WebTarget baseUrl;

	public BankService() {
		Client client = ClientBuilder.newClient();
		baseUrl = client.target("http://g-00.compute.dtu.dk");
		//BankService bank = new BankService().getBankServicePort();
	}
	
	public void createBankAccount(String firstName, String lastName, String CPR, int amount) {
		//bank.createAccount(firstName, lastName, CPR, amount);
	}	
	
	public void deleteBankAccount() {
		//bank.deleteAccount()
	}

}
