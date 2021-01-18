/**
 * @author Wassim
*/

package simulators;

import java.util.HashMap;

import bankservice.Account;
import bankservice.BankServiceException_Exception;
import clients.AccountServiceClient;
import clients.BankServiceClient;
import clients.PaymentServiceClient;
import clients.ReportServiceClient;
import models.AccountType;
import models.Transaction;

public class MerchantApp {

    private String id;
    private Account bankAccount;

    BankServiceClient bankServiceClient;
    PaymentServiceClient paymentServiceClient;
    AccountServiceClient accountServiceClient;
    ReportServiceClient reportServiceClient;

    public MerchantApp(BankServiceClient bankServiceClient, PaymentServiceClient paymentServiceClient,
            AccountServiceClient accountServiceClient, ReportServiceClient reportServiceClient) {
        this.bankServiceClient = bankServiceClient;
        this.paymentServiceClient = paymentServiceClient;
        this.accountServiceClient = accountServiceClient;
        this.reportServiceClient = reportServiceClient;
    }

    public void createBankAccount(String firstName, String lastName, String CPR, String balance)
            throws BankServiceException_Exception {
        this.bankAccount = this.bankServiceClient.createBankAccount(firstName, lastName, CPR, balance);
    }

    public void deleteBankAccount() throws BankServiceException_Exception {
        this.bankServiceClient.deleteBankAccount(this.bankAccount.getId());
        this.bankAccount = null;
    }

    public void registerInDTUPay() {
        this.id = this.accountServiceClient.registerAccount(this.bankAccount, AccountType.MERCHANT);
    }

    public boolean initiateMoneyTransfer(String token, Double amount, String description) {
        var paymentErrorResult = this.paymentServiceClient.createPayment(amount, token, this.id, description);
        if (paymentErrorResult == null) {
            return true;
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    public Account getBankAccount() throws BankServiceException_Exception {
        // for now always get the latest
        return this.bankAccount = this.bankServiceClient.getBankAccount(this.bankAccount.getId());
    }
    
    public HashMap<String, Transaction> getReport(String clientType, String clientId, String startdate, String enddate) {
    	
    	return this.reportServiceClient.getReport(clientType, clientId, startdate, enddate);
    }

}
