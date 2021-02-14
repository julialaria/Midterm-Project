# Midterm-Project

Bienvenido a nuestro sistema bancario. A continuación, le mostraremos cómo usar nuestro sistema de la forma más productiva. 

Para empezar, podrá crear nuestras clases en tablas poniendo “create” en properties y vinculándolo a un schema de mysql. En el ejemplo, usamos “banking”. Deberá añadir su username y password. 

Ej: jdbc:mysql://localhost:3306/suesquema?serverTimezone=UTC 

![p1](https://github.com/julialaria/Midterm-Project/blob/main/1.JPG?raw=true)



Una vez creada sus tablas, podrá rellenarlas con la información que encontrará en la carpeta resources--> static --> schema.sql 

 

 

ADMINISTRADOR 

 

En caso de que usted sea un administrador, deberá introducir en Postman a través Basic Auth su nombre de usuario y su clave. Una vez hecho el log in como administrador, tiene las siguientes opciones: 

 

Podrá tener toda la información de sus cuentas a través de la url  /accounts/by-username, de tipo GET. 

 

ACCOUNTHOLDER 

 

Podrá tener toda la información de las cuentas de los AccountHolders. Para ello, deberá introducir la url  /accountHolder, de tipo GET. Del mismo modo, podrá tener la información de un accountHolder concreto si conoce el AccountHolderId introduciendo la url /accountHolder/by-id/{id} 

Si quiere crear una cuenta para un AccountHolder, deberá introducir la url /create/accountHolder, de tipo POST. Aquí puede encontrar un ejemplo de los datos que debe introducir en Postman: 

Atención: 

El mailingAddress es opcional. 

 

 

THIRDPARTY 

 

Podrá tener toda la información de las cuentas de tipo ThirdParty. Para ello, deberá introducir la url  /thirdParty , de tipo GET. Del mismo modo, podrá tener la información de un ThirdParty concreto si conoce el ThirdPartyId introduciendo la url /thirdParty/by-id/{id} 

Si quiere crear una cuenta para un ThirParty, deberá introducir la url /create/thirdParty, de tipo POST. Aquí puede encontrar un ejemplo de los datos que debe introducir en Postman: 

Atención: 

Si no introduce una hashKey se generará automáticamente. 

 

 SAVINGS 

Podrá tener toda la información de las cuentas de tipo Saving. Para ello, deberá introducir la url  /savings, de tipo GET. Del mismo modo, podrá tener la información de un saving concreto si conoce el SavingId introduciendo la url /savings/by-id/{id} 

 

Si quiere crear una cuenta del tipo Saving, deberá introducir la url /create/savings, de tipo POST.  Deberá haber creado previamente al Accountholder propietario de la cuenta. Aquí puede encontrar un ejemplo de los datos que debe introducir en Postman: 

 

Atención:  

SecondaryOwner es opcional.  

Si no introduce ningún minimumBalance, se le aplicará el default de 1000.  

Si no introduce ningún interestRate, se le aplicará el default de 0.0025%.   

El minimumBalance deberá ser mayor o igual a 100. 

El interestRate deberá ser menor o igual a 0.5 

Si el minimumBalance baja de 250, se le aplicará un penaltyFee de 40. 

El status de la cuenta será por defecto ACTIVE 

A las cuentas de este tipo se le añadirá anualmente a su balance el interés elegido. La operación se realizará una vez acceda a su cuenta y haya pasado un año desde su creación o desde que se le aplicó el interés por última vez.  

 

 

CHECKINGS 

 

Podrá tener toda la información de las cuentas de tipo Checking. Para ello, deberá introducir la url  /checking , de tipo GET. Del mismo modo, podrá tener la información de un checking concreto si conoce el CheckingId introduciendo la url /checking/by-id/{id} 

 

Si quiere crear una cuenta del tipo Checking, deberá introducir la url /create/checking, de tipo POST.  Deberá haber creado previamente al Accountholder propietario de la cuenta. Aquí puede encontrar un ejemplo de los datos que debe introducir en Postman: 

Atención: 

SecondaryOwner es opcional 

The minimumBalance will always be of 250 

The monthlyMaintenanceFee will always be of 12 

Si el minimumBalance baja de 250, se le aplicará un penaltyFee de 40. 

El status de la cuenta será por defecto ACTIVE 

Si el AccountHolder de la cuenta tiene menos de 24 años de edad, se creará automáticamente una cuneta del tipo StudenChecking que no tendrá ni minimumBalance ni montlyMaintenanceFee. 

 

 

CREDITCARD 

 

Podrá tener toda la información de las cuentas de tipo CreditCard. Para ello, deberá introducir la url  /creditCard , de tipo GET. Del mismo modo, podrá tener la información de un CreditCard concreto si conoce el CreditCardId introduciendo la url /creditCard/by-id/{id} 

Si quiere crear una cuenta del tipo CreditCard, deberá introducir la url /create/creditCard, de tipo POST.  Deberá haber creado previamente al Accountholder propietario de la cuenta. Aquí puede encontrar un ejemplo de los datos que debe introducir en Postman: 

Atención: 

SecondaryOwner es opcional.  

Si no introduce ningún creditLimit, se le aplicará el default de 100.  

Si no introduce ningún interestRate, se le aplicará el default de 0.2%.   

El creditLimit deberá ser mayor o igual a 100 y menor o igual a 100000. 

El interestRate deberá ser mayor o igual a 0.1% y menor o igual a 0.2% 

A las cuentas de este tipo se le añadirá mensualmente a su balance el interés anual elegido, divido por 12. La operación se realizará una vez acceda a su cuenta y haya pasado un mes desde su creación o desde que se le aplicó el interés por última vez. 

 

 

STUDENTCHECKING 

 

Podrá tener toda la información de las cuentas de tipo StudentChecking. Para ello, deberá introducir la url  /studentChecking , de tipo GET. Del mismo modo, podrá tener la información de un StudentChecking concreto si conoce el StudentCheckingId introduciendo la url /studentChecking/by-id/{id} 

 

 

UPDATEBALANCE 

Podrá modificar el balance cualquier tipo de cuenta. Para ello, deberá introducir la url /updateBalance/{id}, de tipo PATCH, indicando el id de la cuenta a modificar: 

 

UPDATESTATUS 

Podrá modificar el Status de la cuenta que cuente con el mismo. Para ello, deberá introducir la url /updateStatus/{id}, de tipo PATCH, indicando el id de la cuenta a modificar: 

 

 

TRANSACTIONS 

Podrá obtener la información de todas las transacciones a través de la url /transactions de tipo GET. 

 

ACCOUNTHOLDER 

 

En caso de que usted sea un AccountHolder, deberá introducir en Postman a través Basic Auth su nombre de usuario y su clave. Una vez hecho el loggin como AccountHolder , tiene las siguientes opciones: 

 

Podrá tener toda la información de sus cuentas a través de la url /accounts/by-username, de tipo GET. 

 

Podrá realizar transferencias de una de sus cuentas a cualquier otra a través de la url /transaction de tipo POST. Para ello, deberá introducir en Postman los siguientes datos: 

Atención: 

Solo podrá hacer transferencias desde una de sus cuentas. 

Detención de fraude: si se registran más de una trasanction por segundo desde su cuenta, su estado pasará a FROZEN por motivos de seguridad.  

Del mismo modo, si usted supera en un día el 150% su máximo histórico de transactions  realizadas en 24h, se detectará fraude y el estado de su cuenta pasará a FROZEN hasta que sea supervisada. 

 

 

THIRDPARTY 

 

En caso de que usted sea un ThirdParty, tiene las siguientes opciones: 

Podrá recibir y enviar dinero a otras cuentas. Para ello deberá usar la url /update?hashedKey={hashedKey} de tipo PATCH. Para ello, deberá introducir en Postman los siguientes datos: 

Atención: 

También podrá poner como “transactionType” la opción de “SEND”, para enviar dinero a la cuenta deseada. Deberá indicar siempre el id de la cuneta con la que quiere trabajar y su secretKey. 

 

 

 

 

 

 
