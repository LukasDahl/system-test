package dtupayservice;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class DTUPayService {

	WebTarget baseUrl;

	public DTUPayService() {
		Client client = ClientBuilder.newClient();
		baseUrl = client.target("http://localhost:8080/");
	}
	
}
