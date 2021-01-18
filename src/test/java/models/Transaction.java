package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
	private String Id;
    private String Token;
    private BigDecimal Amount;
    private String MerchantId;
    private String CustomerId;
    private String Description;
    private LocalDateTime Time;
    
    
    public Transaction () {
    	
    }
    
	public Transaction(String id, String token, BigDecimal amount, String merchantId, String customerId,
			String description, LocalDateTime time) {
		Id = id;
		Token = token;
		Amount = amount;
		MerchantId = merchantId;
		CustomerId = customerId;
		Description = description;
		Time = time;
	}
	
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public BigDecimal getAmount() {
		return Amount;
	}
	public void setAmount(BigDecimal amount) {
		Amount = amount;
	}
	public String getMerchantId() {
		return MerchantId;
	}
	public void setMerchantId(String merchantId) {
		MerchantId = merchantId;
	}
	public String getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public LocalDateTime getTime() {
		return Time;
	}
	public void setTime(LocalDateTime time) {
		Time = time;
	}
	
	

}
