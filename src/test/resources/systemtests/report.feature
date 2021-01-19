Feature: Reports

Scenario: Successful payment -> transaction added to report database
		Given a customer "Margarete" "White" with CPR "111285-1569" is registered in the bank with amount "2000"
		And a merchant "Ana" "Lewis" with CPR "190144-1324" is registered in the bank with amount "3000"
		And the customer is registered in DTUPay
		And the merchant is registered in DTUPay
		And the customer has a least one token available
		When the merchant initiates a payment of 100 kr. by the customer with description of "system test payment 1"
		Then the payment is successful
		And the balance of the customer's account is "1900" kr
		And the balance of the merchant's account is "3100" kr
		
		#Addition to payments test
		And the transaction is registered in the reports database
		And the "customer" can see the transaction from a get request
		And the "manager" can see the transaction from a get request
		And the "merchant" can see the transaction from a get request
		And the customer is anonymous to the merchant
