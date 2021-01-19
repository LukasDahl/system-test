/**
 * @author Wassim
*/

package clients;

import java.util.ArrayList;
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

    public String[] getTokenAndId(String clientType, String clientId, String startdate, String enddate, String token) {
    	
        Response response = baseUrl.path(clientType.toLowerCase()).queryParam("id", clientId)
        		.queryParam("start", startdate).queryParam("end", enddate).request().get();
        
        List responseList = response.readEntity(List.class);
        String[] result = new String[3];
        boolean tokenFound = false;
        
        for (Object trans: responseList) {
        	String token_found = trans.toString().split(",")[6].split("=")[1].split("}")[0];
        	String mid_found = trans.toString().split(",")[1].split("=")[1].split("}")[0];
        	String cid_found = trans.toString().split(",")[2].split("=")[1].split("}")[0];
        	if (token_found.equals(token)) {
        		result[0] = token_found;
        		result[1] = mid_found;
        		result[2] = cid_found;
        		tokenFound = true;
        	}
        }
        
        if (!tokenFound) {
        	result[0] = "not found";
        	result[1] = "not found";
        }
        
        return result;
    }
    
    
    
}
