import java.util.Scanner;

public final class OverflowExercise
{
	public static void main(String[] args)
	{
		short acctBal = Short.MAX_VALUE - 3000; // Take note of the data type for acctBal!
		
		
		String input = "0";
		Scanner scanner = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("You currently have an account balance of $" + acctBal + ".");
			System.out.print("Deposit how much? ");
			
			input = scanner.nextLine().trim();
			
			try
			{
	
				if(tryDeposit(acctBal, input) == false) {
					throw new ArithmeticException("Sorry, I can't quite add that to your account.");
				} else {
					acctBal += Integer.parseInt(input);
				}
				
			}
			catch(NumberFormatException nfe)
			{
				if(input.toLowerCase().equals("quit"))
				{
					break;
				}
				else
				{
					System.out.println("Sorry, I don't recognize that number.");
				}
			}
			catch(ArithmeticException nfe) {
				System.out.println("Sorry, I can't quite add that to your account.");
			}
		}
		
		System.out.println("Thank you, and goodbye.");
		scanner.close();
	}	
	
	private static boolean tryDeposit(short balance, String input) {
		
		short acctBalTest;
		acctBalTest = balance;
		balance += Integer.parseInt(input);
		
		if(balance < acctBalTest) {
			return false;
		}
		return true;
	}
}
