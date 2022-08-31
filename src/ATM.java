import java.util.*;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class ATM {
private HashMap<Integer, Double> fullATM;

NumberFormat rounder = new DecimalFormat("0.00");
	public ATM() {
		
		fullATM = new HashMap<Integer, Double>();
	}
	
	public void openAccount(int accountNumber)
	{
		fullATM.put(accountNumber, 0.0);
	}
	
	public void openAccount (int accountNumber, double deposit)
	{
		fullATM.put(accountNumber, deposit);
	}
	public void closeAccount (int accountNumber)
	{
		fullATM.remove(accountNumber, 0.0);
	}
	public double checkBalance (int accountNumber)
	{
		Double current = fullATM.get(accountNumber);
		if (fullATM.containsKey(accountNumber))
		{
			current = Double.parseDouble(rounder.format(current));
			return current;
		}
		return 0.0;
	}
	public boolean depositMoney (int accountNumber, double deposit)
	{
		if (fullATM.containsKey(accountNumber) && deposit >= 0)
		{
			fullATM.replace(accountNumber, fullATM.get(accountNumber)+deposit);
			return true;
		}
		return false;
	}
	public boolean withdrawMoney (int accountNumber, double withdraw)
	{
		if (fullATM.containsKey(accountNumber) && fullATM.get(accountNumber)-withdraw >= 0 && withdraw >= 0)
		{
			fullATM.replace(accountNumber, fullATM.get(accountNumber)-withdraw);
			return true;
		}
		return false;
	}
	
	public static void main (String [] args)
	{
		ATM atm1 = new ATM();
		//checking check balance
		atm1.openAccount(1, 11);
		System.out.println (atm1.checkBalance(1));
		
		System.out.println(atm1.depositMoney(1, 20));
		System.out.println (atm1.checkBalance(1));
		
		System.out.println(atm1.withdrawMoney(1, 9));
		System.out.println (atm1.checkBalance(1));
		
		System.out.println(atm1.withdrawMoney(1, 30));
		System.out.println (atm1.checkBalance(1));
		
		System.out.println(atm1.withdrawMoney(1, 22));
		System.out.println (atm1.checkBalance(1));
		
		atm1.closeAccount(1);
		System.out.println(atm1.checkBalance(1));
		System.out.println(atm1.depositMoney(1, 20));
		
		System.out.println ("\n\n");
		
		ATM chase = new ATM();
			// Open accounts
		chase.openAccount(00001);
		chase.openAccount(00002, 2500.0);
			// Test Basic Functionality
		chase.depositMoney(00001, 433.45);
		chase.depositMoney(00001, 434.77);
		chase.depositMoney(00001, 424.91);
		chase.depositMoney(00001, 474.10);
		chase.depositMoney(00002, 154.30);
		System.out.println(chase.checkBalance(00001)); // Should be 1767.23
		System.out.println(chase.checkBalance(00002)); // Should be 2654.30
		chase.withdrawMoney(00001, 100.00);
		chase.withdrawMoney(00001, 1000.00);
		System.out.println(chase.checkBalance(00001)); // Should be 667.23
			// Test Invalid Deposits
		chase.depositMoney(00003, -433.45);
		chase.depositMoney(00004, 32434.77);
		chase.depositMoney(01337, 128537424.91);
		chase.depositMoney(21504, 2343474.10);
		System.out.println(chase.checkBalance(00003)); // Should be 0.0
		System.out.println(chase.checkBalance(00004)); // Should be 0.0
		System.out.println(chase.checkBalance(01337)); // Should be 0.0
		System.out.println(chase.checkBalance(21504)); // Should be 0.0
			// Test Invalid Withdrawals
		chase.withdrawMoney(00001, -433.45);
		chase.withdrawMoney(00001, 32434.77);
		chase.withdrawMoney(01337, 128537424.91);
		chase.withdrawMoney(21504, -2343474.10);
		System.out.println(chase.checkBalance(00001)); // Should be 667.23
		System.out.println(chase.checkBalance(00001)); // Should be 667.23
		System.out.println(chase.checkBalance(01337)); // Should be 0.0
		System.out.println(chase.checkBalance(21504)); // Should be 0.0
			// Test other issues
		chase.withdrawMoney(00002, 2020.2);
		System.out.println(chase.checkBalance(00002)); // Should be 634.1 and not a fraction more!
	}
}
