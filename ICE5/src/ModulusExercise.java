public final class ModulusExercise
{
	public static void main(String[] args)
	{
		int a = -6;
		int b = 73;
		int c = 45;
		
		System.out.printf("%d^%d mod %d = %d", a, b, c, modPow(a,b,c));
	}
	
	/**
	 * Calculates modular exponentiation through the "square and multiply" method.
	 * @param a The base
	 * @param b The exponent
	 * @param p The modulus
	 * @return  The solution to the expression (a^b) mod p
	 */
	private static int modPow(int a, int b, int p) throws ArithmeticException
	{

		if(Math.pow(a, b) <0) {
			throw new ArithmeticException("Exponent creates a number beyond the bounds of the variable's inclusive range");
		}
		
		if(b == 0)
		{
			return 1;   // a^0 = 1 for all a
		}
		else if(b == 1)
		{
			return a%p; // a^1 = a for all a
		}
		else if(b%2 == 0)
		{
			return modPow((a*a)%p, (b/2), p);
		}
		else if(b%2 == 1)
		{
			return (a * modPow((a*a)%p, ((b-1)/2), p))%p;
		}
		else
		{
			throw new ArithmeticException("Exponent is an invalid value, for example less than 0.");
		}
	}
}
