Feature: DTUPay

	Scenario: A payment is succesful in DTUPay
		Given a customer "Margarete" "White" with CPR "111285-0569" is registered in the bank with amount "2000"
		And a merchant "Ana" "Lewis" with CPR "190144-0324" is registered in the bank with amount "3000"
		And the customer is registered in DTUPay
		And the merchant is registered in DTUPay
		And the customer has a least one token available
		When the merchant initiates a payment of 100 kr. by the customer with description of "system test payment 1"
		Then the payment is successful
		And the balance of the customer's account is "1900" kr
		And the balance of the merchant's account is "3100" kr
