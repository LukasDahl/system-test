/**
 * @author Wassim
*/

package clients;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import bankservice.Account;
import models.AccountType;

import javax.json.*;

public class AccountServiceClient {
    WebTarget baseUrl;

    public AccountServiceClient() {
        Client client = ClientBuilder.newClient();
        baseUrl = client.target("http://localhost:8030/");
    }

    public String registerAccount(Account bankAccount, AccountType accountType) {

        JsonObjectBuilder userBuild = Json.createObjectBuilder().add("cprNumber", bankAccount.getUser().getCprNumber())
                .add("firstName", bankAccount.getUser().getFirstName())
                .add("lastName", bankAccount.getUser().getLastName());
        var user = userBuild.build();

        var type = accountType == AccountType.CUSTOMER ? "Costumer" : "Merchant";

        JsonObjectBuilder accountBuild = Json.createObjectBuilder().add("type", type)
                .add("bankAccountId", bankAccount.getId()).add("user", user);
        var account = accountBuild.build();

        String response = baseUrl.path("accounts").request().post(Entity.entity(account, MediaType.APPLICATION_JSON))
                .readEntity(String.class);

        // better change this @Jonatan and also you can return the account already so we
        // don't need to fetch it again
        if (response.equals("user created")) {
            // return "some-account-id";
        }

        return "some-account-id";
    }
}
