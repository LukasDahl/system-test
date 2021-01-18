package endToEnd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class TokenStepdefs {

    TestClient client = new TestClient();

    @Given("a new customer")
    public void newCustomer() {
        client.createAccount("", "Test", "Person", "Costumer", "");
    }

    @Given("a new merchant")
    public void newMerchant() {
        client.createAccount("", "Test", "Person", "Merchant", "");
    }

    @When("the customer requests {int} tokens")
    public void theCustomerRequestsTokens(int count) {
        client.requestTokens(count);
    }

    @Then("he has {int} tokens")
    public void heHasTokens(int count) {
        assertEquals(count, client.tokens.size());
    }

    @Then("he has not gotten an error")
    public void heHasNotGottenAnError() {
        assertEquals("", client.error);
    }

    @Then("he has gotten an error")
    public void heHasGottenAnError() {
        assertNotEquals("", client.error);
    }

    @Then("the account is deleted")
    public void theAccountIsDeleted() {
        client.deleteAccount();
    }
}
