/**
 * @author Wassim
*/

package models;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class CreatePaymentRequest {
    private String token;
    private Double amount;
    private String merchantId;
    private String description;

    public CreatePaymentRequest() {
    }

    @JsonbCreator
    public CreatePaymentRequest(@JsonbProperty("amount") Double amount, @JsonbProperty("token") String token,
            @JsonbProperty("merchantId") String merchantId, @JsonbProperty("description") String description) {
        this.amount = amount;
        this.token = token;
        this.merchantId = merchantId;
        this.description = description;
    }
}
