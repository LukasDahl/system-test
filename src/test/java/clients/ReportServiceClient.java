/**
 * @author Wassim
*/

package clients;

import java.util.List;

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

    public boolean checkToken(String clientType, String clientId, String startdate, String enddate, String token) {
    	
        Response response = baseUrl.path(clientType.toLowerCase()).queryParam("id", clientId)
        		.queryParam("start", startdate).queryParam("end", enddate).request().get();
        
        List responseList = response.readEntity(List.class);
        
        for (Object trans: responseList) {
        	String token_found = trans.toString().split(",")[6].split("=")[1].split("}")[0];
        	System.out.println("token found: " + token_found + "       token to match: " + token);
        	if (token_found.equals(token)) {
        		return true;
        	}
        }
        
        return false;
    }
}
