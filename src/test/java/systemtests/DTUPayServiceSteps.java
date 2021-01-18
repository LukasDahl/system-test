/**
 * @author Wassim
*/

package systemtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import bankservice.BankServiceException_Exception;
import clients.AccountServiceClient;
import clients.BankServiceClient;
import clients.PaymentServiceClient;
import clients.ReportServiceClient;
import clients.TokenServiceClient;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import simulators.CustomerApp;
import simulators.MerchantApp;

public class DTUPayServiceSteps {

	String token;
	Boolean paymentResult;
	CustomerApp customerApp;
	MerchantApp merchantApp;

	public DTUPayServiceSteps() {
		var bankServiceClient = new BankServiceClient();
		var tokenServiceClient = new TokenServiceClient();
		var accountServiceClient = new AccountServiceClient();
		var paymentServiceClient = new PaymentServiceClient();
		var reportServiceClient = new ReportServiceClient();

		this.customerApp = new CustomerApp(bankServiceClient, tokenServiceClient,
				accountServiceClient);
		this.merchantApp = new MerchantApp(bankServiceClient, paymentServiceClient,
				accountServiceClient, reportServiceClient);
	}
	
	

	@Given("a customer {string} {string} with CPR {string} is registered in the bank with amount {string}")
	public void aCustomerIsRegisteredInTheBank(String firstName, String lastName, String CPR, String balance)
			throws BankServiceException_Exception {
		
		this.customerApp.createBankAccount(firstName, lastName, CPR, balance);
	}

	@Given("a merchant {string} {string} with CPR {string} is registered in the bank with amount {string}")
	public void aMerchantIsRegisteredInTheBank(String firstName, String lastName, String CPR, String balance)
			throws BankServiceException_Exception {
		this.merchantApp.createBankAccount(firstName, lastName, CPR, balance);
	}

	@And("the customer is registered in DTUPay")
	public void theCustomerIsRegisteredInDTUPay() {
		this.customerApp.registerInDTUPay();
	}

	@And("the merchant is registered in DTUPay")
	public void theMerchantIsRegisteredInDTUPay() {
		this.merchantApp.registerInDTUPay();
	}

	@And("the customer has a least one token available")
	public void theCustomerHasAtLeastOneTokenAvailable() {
		this.token = this.customerApp.getToken();
	}

	@When("the merchant initiates a payment of {double} kr. by the customer with description of {string}")
	public void theMerchantInitiatesPaymentByTheCustomer(Double amount, String description) {
		this.paymentResult = this.merchantApp.initiateMoneyTransfer(this.token, amount, description);
	}

	@Then("the payment is successful")
	public void thePaymentIsSuccessful() {
		assertTrue(this.paymentResult);
	}

	@And("the balance of the customer's account is {string} kr")
	public void theBalanceOfTheCustomerAccountIs(String balance) throws BankServiceException_Exception {
		var customerAccount = this.customerApp.getBankAccount();
		assertEquals(new BigDecimal(balance), customerAccount.getBalance());
	}

	@And("the balance of the merchant's account is {string} kr")
	public void theBalanceOfTheMerchantAccountIs(String balance) throws BankServiceException_Exception {
		var merchantAccount = this.merchantApp.getBankAccount();
		assertEquals(new BigDecimal(balance), merchantAccount.getBalance());
	}
	
	@And("the transaction is registered in the reports database")
	public void theTransactionIsRegisteredInTheReportsDatabase() {
		this.merchantApp.getReport("managers", "20", "0001-01-01", "9999-12-31");
		
	}

	@After()
	public void deleteUsers() throws BankServiceException_Exception {
		customerApp.deleteBankAccount();
		merchantApp.deleteBankAccount();
	}


}
