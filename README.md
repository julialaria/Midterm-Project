# Midterm-Project

Welcome to our banking system. In the following, we will show you how to use our system in the most productive way.

To start with, you can create our classes in tables by putting **“create”** in properties and linking it to a mysql schema. In the example, we use “banking” schema. You must add your username and password.

Ej: jdbc:mysql://localhost:3306/**schema**?serverTimezone=UTC 

![p1](https://github.com/julialaria/Midterm-Project/blob/main/1.JPG?raw=true)

Once your tables have been created, you can fill them with the information that you will find in the folder **resources -> static -> schema.sql**

![p2](https://github.com/julialaria/Midterm-Project/blob/main/2.JPG?raw=true)

**1. ADMINISTRATOR** 

If you are an administrator, you must enter your **username and password in Postman** through **Basic Auth**. Once you are logged in as an administrator, you have the following options:

You can have all the information of your accounts through the url **/accounts / by-username**, of type GET.
 

**1.1 ACCOUNTHOLDER** 

You will be able to have all the information of the accounts of the AccountHolders. To do this, you must enter the url **/accountHolder**, of type GET. In the same way, you can have the information of a specific accountHolder if you know the AccountHolderId by entering the url **/accountHolder/by-id/{id}**

If you want to create an account for an AccountHolder, you must enter the url **/create/accountHolder**, of type POST. Here you can find an example of the data you need to enter into Postman:


***Attention***:

- The mailingAddress is optional.

 ![p3](https://github.com/julialaria/Midterm-Project/blob/main/3.JPG?raw=true)

 
**1.2 THIRDPARTY** 

You can have all the information for ThirdParty type accounts. To do this, you must enter the url **/thirdParty**, of type GET. In the same way, you can have the information of a specific ThirdParty if you know the ThirdPartyId by entering the url **/thirdParty/by-id/{id}**

If you want to create an account for a ThirParty, you must enter the url **/create/thirdParty**, of type POST. Here you can find an example of the data you need to enter into Postman:

***Attention***:

-If you don't enter a hashKey it will be generated automatically.
 
![p4](https://github.com/julialaria/Midterm-Project/blob/main/4.JPG?raw=true)

 **1.3 SAVINGS** 

You can have all the information of the Saving type accounts. To do this, you must enter the url **/savings**, of type GET. In the same way, you can have the information of a specific saving if you know the SavingId by entering the url **/savings/by-id/{id}**

If you want to create an account of type Saving, you must enter the url **/create/savings**, of type POST. You must have previously created the Accountholder that owns the account. Here you can find an example of the data you need to enter into Postman:

***Attention***:

-SecondaryOwner is optional.

-If you do not enter a minimumBalance, the default of 1000 will be applied.

-If you do not enter an interestRate, the default of 0.0025% will be applied.

-The minimumBalance must be greater than or equal to 100.

-The interestRate must be less than or equal to 0.5

-If the minimumBalance falls below 250, a penaltyFee of 40 will be applied.

-The account status will be ACTIVE by default

-For accounts of this type, the chosen interest will be added to your balance annually. The operation will be carried out once you access your account and a year has passed since its creation or since the interest was applied for the last time.
 
![p5](https://github.com/julialaria/Midterm-Project/blob/main/5.JPG?raw=true)
 
**1.4 CHECKINGS** 

You can have all the information of the Checking type accounts. To do this, you must enter the url **/checking**, of type GET. In the same way, you can have the information of a specific checking if you know the CheckingId by entering the url **/checking/by-id/{id}**

If you want to create an account of type Checking, you must enter the url **/create/checking**, of type POST. You must have previously created the Accountholder that owns the account. Here you can find an example of the data you need to enter into Postman:

***Attention***:

-SecondaryOwner is optional

-The minimumBalance will always be of 250

-The monthlyMaintenanceFee will always be of 12

-If the minimumBalance falls below 250, a penaltyFee of 40 will be applied.

-The account status will be ACTIVE by default

-If the AccountHolder for the account is less than 24 years old, a StudenChecking account will automatically be created that will have neither minimumBalance nor montlyMaintenanceFee.

![p6](https://github.com/julialaria/Midterm-Project/blob/main/6.JPG?raw=true)
 

**1.5 CREDITCARD** 

You can have all the information of the CreditCard type accounts. To do this, you must enter the url **/creditCard**, of type GET. In the same way, you can have the information of a specific CreditCard if you know the CreditCardId by entering the url **/creditCard/by-id/{id}**

If you want to create an account of type CreditCard, you must enter the url **/create/creditCard**, of type POST. You must have previously created the Accountholder that owns the account. Here you can find an example of the data you need to enter into Postman:

***Attention***:

-SecondaryOwner is optional.

-If you do not enter a creditLimit, the default of 100 will be applied.

-If you do not enter any interestRate, the default of 0.2% will be applied.

-The creditLimit must be greater than or equal to 100 and less than or equal to 100,000.

-The interestRate must be greater than or equal to 0.1% and less than or equal to 0.2%

-To accounts of this type, the chosen annual interest will be added to your balance every month, divided by 12. The operation will be carried out once you access your account and a month has passed since its creation or since the interest was last applied. 
 
![p7](https://github.com/julialaria/Midterm-Project/blob/main/7.JPG?raw=true)
 

**1.6 STUDENTCHECKING** 

You can have all the information of the StudentChecking type accounts. To do this, you must enter the url **/studentChecking**, of type GET. In the same way, you can have the information of a specific StudentChecking if you know the StudentCheckingId by entering the url **/studentChecking/by-id/{id}**

**1.7 UPDATEBALANCE** 

Any type of account can modify the balance. To do this, you must enter the url **/updateBalance/{id}**, of type PATCH, indicating the id of the account to modify:

 ![p8](https://github.com/julialaria/Midterm-Project/blob/main/8.JPG?raw=true)

**1.8 UPDATESTATUS** 

You can modify the Status of the account that has it. To do this, you must enter the url **/updateStatus/{id}**, of type PATCH, indicating the id of the account to modify:

![p9](https://github.com/julialaria/Midterm-Project/blob/main/9.JPG?raw=true)
 
**1.9 TRANSACTIONS** 

You can get the information of all transactions through the url **/transactions** of type GET.

![p10](https://github.com/julialaria/Midterm-Project/blob/main/10.JPG?raw=true) 

**2.ACCOUNTHOLDER** 

In case you are an AccountHolder, you must enter your **username and password** in Postman through **Basic Auth**. Once you have logged in as an AccountHolder, you have the following options:

You can have all the information about your accounts through the url **/accounts/by-username**, of type GET.

You can make transfers from one of your accounts to any other through the url **/transaction** of POST type. To do this, you must enter the following information in Postman:

![p11](https://github.com/julialaria/Midterm-Project/blob/main/10.JPG?raw=true)


***Attention***:

-You can only make transfers from one of your accounts.

-Fraud Detection - If more than one transaction per second is logged from your account, your status will go to FROZEN for security reasons.

-In the same way, if you exceed 150% in one day your historical maximum of transactions carried out in 24 hours, fraud will be detected and the status of your account will pass to FROZEN until it is supervised.

 
**3.THIRDPARTY** 

In case you are a ThirdParty, you have the following options:

You can receive and send money to other accounts. To do this you must use the url **/update?hashedKey={hashedKey}** of type PATCH. To do this, you must enter the following information in Postman:

***Attention***:

-You can also set the "SEND" option as "transactionType", to send money to the desired account. You must always indicate the id of the account you want to work with and its secretKey.

![p12](https://github.com/julialaria/Midterm-Project/blob/main/12.JPG?raw=true)
 

 

 

 

 

 
