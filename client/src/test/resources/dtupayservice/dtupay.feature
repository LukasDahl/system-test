Feature: DTUPay

Scenario: A transaction is succesful in DTUPay
	Given a customer "Jane" "Austin" with CPR "010001-0100" is registered in the bank with amount "2000"
	And a merchant "Jack" "Jackman" with CPR "000100-0000" is registered in the bank with amount "10000"
	And the customer's balance is "2000" kr
	And the merchant's balance is "10000" kr 
#	And the customer with id "cid" is registered in DTUPay
#	And the merchant with id "mid" is registered in DTUPay
#	And the customer has a least one token available	
#	When the merchant transfers an amount of "1000" from account "cid" to acount "mid"
#	Then the balance of the customer's account "cid" is "1000" kr
#	And the balance of the merchant's account "mid" is "11000" kr
