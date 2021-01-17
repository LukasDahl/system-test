/**
 * @author Wassim
*/

package clients;

import java.util.LinkedList;

import javax.json.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TokenServiceClient {
    WebTarget baseUrl;

    public TokenServiceClient() {
        Client client = ClientBuilder.newClient();
        baseUrl = client.target("http://localhost:8020/");
    }

    public LinkedList<String> requestTokens(String customerId, int count) {

        JsonObject json = Json.createObjectBuilder().add("count", count).add("id", customerId).build();

        Response response = baseUrl.path("tokens").request().post(Entity.entity(json, MediaType.APPLICATION_JSON_TYPE));

        LinkedList<String> tokens = new LinkedList<>();

        // consider happy paths for now
        if (response.getStatus() == 200) {
            JsonArray jsonArray = response.readEntity(JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++)
                tokens.add(jsonArray.getString(i));
        }

        return tokens;
    }
}
