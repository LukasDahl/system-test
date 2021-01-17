/**
 * @author Wassim
*/

package clients;

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

        var createPaymentRequest = new CreatePaymentRequest(amount, token, merchantId, description);

        Response response = baseUrl.path("payments").request()
                .post(Entity.entity(createPaymentRequest, MediaType.APPLICATION_JSON));

        if (response.getStatus() == 201) {
            return null;
        } else {
            var error = response.readEntity(ErrorModel.class);
            return error;
        }
    }
}
