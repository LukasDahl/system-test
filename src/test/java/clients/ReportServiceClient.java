/**
 * @author Wassim
*/

package clients;

import java.util.HashMap;
import java.util.Map;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import io.cucumber.messages.internal.com.google.gson.Gson;
import models.Transaction;

public class ReportServiceClient {
    WebTarget baseUrl;

    public ReportServiceClient() {
        Client client = ClientBuilder.newClient();
        baseUrl = client.target("http://localhost:8040/reports/");
    }

    public HashMap<String, Transaction> getReport(String clientType, String clientId, String startdate, String enddate) {
    	
    	
        Response response = baseUrl.path(clientType.toLowerCase()).queryParam("id", clientId)
        		.queryParam("start", startdate).queryParam("end", enddate).request().get();
        
        
        String response_string = response.readEntity(JsonObject.class).toString();
        HashMap<String, Transaction> map = new Gson().fromJson(response_string, HashMap.class);
  
        Object[] mapArray = map.values().toArray();
        
        for (int i=0; i<mapArray.length; i++) {
        	String tokenId = mapArray[i].toString().split(",")[6].substring(7).split("}")[0];
        	System.out.println(tokenId);
        }
        
        

        return map;
    }
}
