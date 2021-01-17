package dtupayservice;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class BankServiceSteps {
	BankService service = new BankService();
	
	@Given("a customer {string} {string} with CPR {string} is registered in the bank with amount {int}")
	public void a_customer_with_CPR_is_registered_in_the_bank(String firstName, String lastName, String CPR, int amount) {
		service.createBankAccount(firstName, lastName, CPR, amount);
	}
	
	@And("the customer's balance is {int} kr")
	public void getBalance() {}
	
	
//	@After
//	public void cleanUpByDeleteBankAccounts() {
//		service.deleteBankAccount();
//	}
	

}
