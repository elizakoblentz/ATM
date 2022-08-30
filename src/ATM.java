import java.util.*;
public class ATM {
private HashMap<Integer, Double> fullATM;

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
		if (fullATM.containsKey(accountNumber))
		{
			return fullATM.get(accountNumber);
		}
		return (Double)null;
	}
	public boolean depositMoney (int accountNumber, double deposit)
	{
		double current = fullATM.get(accountNumber);
		if (fullATM.containsKey(accountNumber))
		{
			fullATM.replace(accountNumber, current+deposit);
			return true;
		}
		return false;
	}
	public boolean withdrawMoney (int accountNumber, double withdraw)
	{
		double current = fullATM.get(accountNumber);
		if (fullATM.containsKey(accountNumber) && current-withdraw >= 0)
		{
			fullATM.replace(accountNumber, current-withdraw);
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
	}
}
