/**
 * In the following code two or more threads are attempting to 
 * modify shared data at the same time. The main method below
 * will attempt to deposit and withdraw amounts 100 times. 
 *          - deposit $100 100 times
 *          - withdraw $50 100 times
 * At the end of the program, there should be $5000 in the account.
 */
 
public class RaceConditionICE {
 
	public static void main(String[] args) {
		BankAccount Account = new BankAccount("AccountNumber");
 
		// Total Expected Deposit: 10000 (100 x 100)
		for (int i = 0; i < 100; i++) {
			Transaction t = new Transaction(Account, Transaction.TransactionType.DEPOSIT_MONEY, 100);
			t.start();
		}
 
		// Total Expected Withdrawal: 5000 (100 x 50)
		for (int i = 0; i < 100; i++) {
			Transaction t = new Transaction(Account, Transaction.TransactionType.WITHDRAW_MONEY, 50);
			t.start();
 
		}
 
		// Let's just wait for a second to make sure all thread execution completes.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
 
		// Expected account balance (without interest) is 5000
		System.out.printf("Final Account Balance: %,.2f", Account.getAccountBalance());
	}
}
 
class Transaction extends Thread {
 
	public static enum TransactionType {
		DEPOSIT_MONEY(1), WITHDRAW_MONEY(2);
 
		private TransactionType(int value) {
		}
	};
 
	private TransactionType transactionType;
	private BankAccount Account;
	private double Amount;
 
	/*
	 * If transactionType == 1, depositAmount() else if transactionType == 2 withdrawAmount()
	 */
	public Transaction(BankAccount Account, TransactionType transactionType, double Amount) {
		this.transactionType = transactionType;
		this.Account = Account;
		this.Amount = Amount;
	}
 
	public void run() {
		switch (this.transactionType) {
		case DEPOSIT_MONEY:
			depositAmount();
			printBalance();
			break;
		case WITHDRAW_MONEY:
			withdrawAmount();
			printBalance();
			break;
		default:
			System.out.println("NOT A VALID TRANSACTION");
		}
	}
 
	public void depositAmount() {
		this.Account.depositAmount(this.Amount);
	}
 
	public void withdrawAmount() {
		this.Account.withdrawAmount(Amount);
	}
 
	public void printBalance() {
		System.out.println(Thread.currentThread().getName() + " : TransactionType: " + this.transactionType + ", Amount: " + this.Amount);
		System.out.printf("New Account Balance: %,.2f\n", this.Account.getAccountBalance());
	}
}

class BankAccount {
	private String AccountNumber;
	private double AccountBalance;
 
	public String getAccountNumber() {
		return AccountNumber;
	}
 
	public double getAccountBalance() {
		return AccountBalance;
	}
 
	public BankAccount(String AccountNumber) {
		this.AccountNumber = AccountNumber;
	}
 
	public boolean depositAmount(double amount) {
		if (amount < 0) {
			return false;
		} else {
			AccountBalance = AccountBalance + amount;
			return true;
		}
	}
 
	public boolean withdrawAmount(double amount) {
		if (amount > AccountBalance) {
			return false;
		} else {
			AccountBalance = AccountBalance - amount;
			return true;
		}
	}
}