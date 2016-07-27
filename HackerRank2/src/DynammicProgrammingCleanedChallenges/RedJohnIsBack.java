/**Solution for https://www.hackerrank.com/challenges/red-john-is-back **/
package DynammicProgrammingCleanedChallenges;

import java.util.Scanner;

public class RedJohnIsBack {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int numOfTestcases = sc.nextInt();
		for (int t = 1; t <= numOfTestcases; t++) {
			int N = sc.nextInt();
			long solution = RedJohnIsBack.solve(N);
			System.out.println(solution);

		}
		sc.close();
	}

	private static long solve(int N) {
		long numOfConfiguration = 0;
		numOfConfiguration = numOfConfigurations(N);
		long numOfPrime = numOfPrim(numOfConfiguration);
		return numOfPrime;
	}



	private static long numOfConfigurations(int rowsNotCovered) {
		
		if (rowsNotCovered == 0) {
			return 1;
		}

		long numOfConfigurations = 0;

		if (rowsNotCovered >= 4) {
			numOfConfigurations += numOfConfigurations(rowsNotCovered - 4);
		}
		numOfConfigurations += numOfConfigurations(rowsNotCovered - 1);

		return numOfConfigurations;

	}
	
	//calculates number of Primes smaller or equal to number
	private static long numOfPrim(long number) {
		
		long numOfPrime = 0;
		for (int i = 2; i <= number; i++) {
			if (isPrim(i)) {
				numOfPrime++;
			}
		}
		return numOfPrime;
		
	}

	private static boolean isPrim(int number) {
		if (number == 1 || number == 2) {
			return true;
		}
		
		boolean isPrim = true;
		long divisor = 2;

		while (divisor <= Math.sqrt(number)) {
			if (number % divisor == 0) {
				return false;
			}
			divisor += 1;
		}
		return isPrim;
	}

}
