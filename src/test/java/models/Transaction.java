package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
	private String id;
    private String token;
    private BigDecimal amount;
    private String merchantId;
    private String customerId;
    private String description;
    private LocalDateTime time;
    
    
    public Transaction () {
    	
    }
    
	public Transaction(String id, String token, BigDecimal amount, String merchantId, String customerId,
			String description, LocalDateTime time) {
		this.id = id;
		this.token = token;
		this.amount = amount;
		this.merchantId = merchantId;
		this.customerId = customerId;
		this.description = description;
		this.time = time;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	

}
