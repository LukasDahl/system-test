/**
 * @author Wassim
*/

package simulators;

import java.util.LinkedList;

import bankservice.Account;
import bankservice.BankServiceException_Exception;
import clients.AccountServiceClient;
import clients.BankServiceClient;
import clients.ReportServiceClient;
import clients.TokenServiceClient;
import models.AccountType;

public class CustomerApp {

    String id;
    Account bankAccount;
    LinkedList<String> tokens = new LinkedList<>();

    BankServiceClient bankServiceClient;
    TokenServiceClient tokenServiceClient;
    AccountServiceClient accountServiceClient;
    ReportServiceClient reportServiceClient;

    public CustomerApp(BankServiceClient bankServiceClient, TokenServiceClient tokenServiceClient,
            AccountServiceClient accountServiceClient, ReportServiceClient reportServiceClient) {
        this.bankServiceClient = bankServiceClient;
        this.tokenServiceClient = tokenServiceClient;
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

    public void requestTokens(int count) {
        this.tokens = this.tokenServiceClient.requestTokens(this.id, count);
    }

    public void registerInDTUPay() {
        this.id = this.accountServiceClient.registerAccount(this.bankAccount, AccountType.CUSTOMER);
    }

    public String getId() {
        return this.id;
    }

    public Account getBankAccount() throws BankServiceException_Exception {
        // for now always get the latest
        return this.bankAccount = this.bankServiceClient.getBankAccount(this.bankAccount.getId());
    }

    public String getToken() {
        if (this.tokens.size() == 0) {
            this.requestTokens(3);
        }
        return this.tokens.removeFirst();
    }
    
    public String[] getTokenAndId(String clientType, String clientId, String startdate, String enddate, String token) {

        return this.reportServiceClient.getTokenAndId(clientType, clientId, startdate, enddate, token);
    }

}
