/**
 * @author Wassim
 */

package clients;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import models.CreatePaymentRequest;
import models.ErrorModel;

public class PaymentServiceClient {
    WebTarget baseUrl;

    public PaymentServiceClient() {
        Client client = ClientBuilder.newClient();
        baseUrl = client.target("http://localhost:8010/");
    }

    public ErrorModel createPayment(double amount, String token, String merchantId, String description) {

        //var createPaymentRequest = new CreatePaymentRequest(amount, token, merchantId, description);
        JsonObject json = Json.createObjectBuilder()
                .add("amount", amount)
                .add("token", token)
                .add("merchantId", merchantId)
                .add("description", description)
                .build();

        Response response = baseUrl.path("payments").request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 201) {
            return null;
        } else {
            var error = response.readEntity(ErrorModel.class);
            return error;
        }
    }
}
