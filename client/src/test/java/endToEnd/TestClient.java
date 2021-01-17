package endToEnd;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;

public class TestClient {
    WebTarget paymentUrl, tokenUrl, accountUrl, reportUrl;
    LinkedList<String> tokens = new LinkedList<>();
    String id = "";
    String error = "";

    public TestClient() {

        paymentUrl = ClientBuilder.newClient().target("http://g-15.compute.dtu.dk:8010/");
        tokenUrl = ClientBuilder.newClient().target("http://g-15.compute.dtu.dk:8020/");
        accountUrl = ClientBuilder.newClient().target("http://g-15.compute.dtu.dk:8030/");
        reportUrl = ClientBuilder.newClient().target("http://g-15.compute.dtu.dk:8040/");

    }

    public void createAccount(String cpr, String first, String last, String type, String bank) {
        JsonObject user = Json.createObjectBuilder()
                .add("cprNumber", cpr)
                .add("firstName", first)
                .add("lastName", last)
                .build();

        JsonObject account = Json.createObjectBuilder()
                .add("type", type)
                .add("bankAccountId", bank)
                .add("user", user)
                .build();

        Response response = accountUrl
                .path("accounts")
                .request()
                .post(Entity.entity(account, MediaType.APPLICATION_JSON));

        if (response.getStatus() != 201) {
            System.out.println(response.getStatus());
            error = response.readEntity(JsonObject.class).getString("errorMessage");

            return;
        }
        id = response.readEntity(JsonObject.class).getString("id");
        System.out.println(id);
    }

    public void requestTokens(int count) {

        JsonObject json = Json.createObjectBuilder()
                .add("count", count)
                .add("id", id)
                .build();

        Response response = tokenUrl.
                path("tokens")
                .request()
                .post(Entity.entity(json, MediaType.APPLICATION_JSON_TYPE));

        if (response.getStatus() != 200){
            error = response.readEntity(String.class);
            System.out.println(error);
            return;
        }

        JsonArray jsonArray = response.readEntity(JsonArray.class);
        System.out.println(jsonArray.toString());
        for (int i = 0; i < jsonArray.size(); i++)
            tokens.add(jsonArray.getString(i));

    }

    public void deleteAccount(String id){
        Response response = accountUrl.path("accounts/" + id).request().delete();
        if (response.getStatus() != 200){
            error = response.readEntity(String.class);
            System.out.println(error);
        }
    }


}
