/**
 * https://www.hackerrank.com/challenges/stockmax
 */

package DynammicProgramming.CleanedChallenges;

import java.util.Scanner;

public class StockMaximize {
	static Scanner sc;
	static int days;
	static long prices[];
	static int memory[][];

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		int numberOfTestcases = sc.nextInt();
		for (int t = 1; t <= numberOfTestcases; t++) {
			StockMaximize.readInputForInstance();
			long solution = StockMaximize.solve();
			System.out.println(solution);
		}
	}

	private static long solve() {

		int day = findMaxOfSubarry(prices, 0);
		int dayOfLastSell = -1;
		long sum = 0;
		boolean dayOfBuy[] = new boolean[days];
		while (day != days) {
			long sell = (day - dayOfLastSell - 1) * prices[day];
			dayOfBuy[day] = true;
			sum += sell;
			dayOfLastSell = day;
			day = findMaxOfSubarry(prices, day + 1);

		}

		for (int i = 0; i < prices.length; i++) {
			if (!dayOfBuy[i]) {
				sum -= prices[i];
			}
		}

		return sum;
	}


	private static int findMaxOfSubarry(long price[], int startIndex) {

		long maxPrice = 0;
		int maxIndex = days;
		for (int i = startIndex; i < price.length; i++) {
			if (price[i] >= maxPrice) {
				maxPrice = price[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	private static void readInputForInstance() {
		days = sc.nextInt();
		prices = new long[days];
		for (int i = 0; i < days; i++) {
			prices[i] = sc.nextLong();
		}
	}

}