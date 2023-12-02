<br/>
<p align="center">
  <h1 align="center">Java Bank CLI App</h1>
</p>

### About The Project

This is a Java application that simulates a bank system with a command-line interface. It demonstrates the use of object-oriented programming concepts such as inheritance, polymorphism, association, aggregation, and composition.
The application has two types of users: `admin` and `customer`. Each user has a unique card number, password, and PIN. The card number are randomly generated each time customer are registered. The password are hashed using SHA-256 algorithm for security purposes.

The application comes with two default accounts for testing purposes:
Admin account: username `admin`, password `admin`.
Customer account: username `customer`, password `customer`.

You can use these accounts to login and explore the features of the application. You can also create your own accounts by registering as a customer.

### Admin features
* Admin can suspend or unsuspend a customer account by entering the customer’s username.
* Admin can list all the customer accounts with their details.
* Admin can see a customer’s transaction history by entering the customer’s card number.
* Admin can see its own account information.
* Admin cannot be suspended by other admins.

### Customer features
* Customer can register a new account by entering their name, username, password, and PIN. A card number will be generated automatically for them.
* Customer can deposit money to their account by entering the amount. There is no limit for each deposit.
* Customer can withdraw money from their account by entering the amount. There is no limit for each withdrawal, as long as the balance is not negative.
* Customer can transfer money to another customer account by entering the recipient’s card number and the amount. The recipient will also have the transaction recorded in their history.
* Customer can see their own transaction history.
* Customer have 10 tries for entering the wrong PIN for each transaction, otherwise their account will be suspended.
* Customer have 10 tries for entering the wrong password for each login, otherwise their account will be suspended.

### Getting Started
#### Prerequisites

To run the application, you need to have Java installed on your system. You can download Java based on your device. Then, follow these steps:

#### Installation

1. Clone this repository to your local machine using git clone

```sh
git clone https://github.com/bagasdisini/java-bank-app.git
```

2. Navigate to the project folder using cd

```sh
cd java-bank-app
```

3. Compile the source code using javac

```sh
javac Main.java
```

4. Run the application using java

```sh
java Main
```

You will see a welcome message and a menu with options to choose from. You can enter the option number to perform the corresponding action.