/**
 * https://www.hackerrank.com/challenges/coin-change
 * Challenge Description: How many different ways can you make change for an amount, given a list of coins? 
 * Recursive Algorithm
 */

package DynammicProgramming.CleanedChallenges;

import java.util.Scanner;

public class CoinChangeProblem {

	private int N;
	private int M;
	private int coins[];
	private long memory[][];

	public static void main(String[] args) {
		
		CoinChangeProblem ccp = CoinChangeProblem.createInstance();
		long solution = ccp.solve();
		System.out.println(solution);
		
	}

	private long solve() {
		
		long solution = numOfCombinations(N, 0);
		return solution;
		
	}

	private long numOfCombinations(int amount, int currentCoinIndex) {

		if (amount == 0 || currentCoinIndex == M) {
			return 0;
		}

		long numOfCombinations = 0;
		int valueCurrrentCoin = coins[currentCoinIndex];

		while (amount >= 0) {

			if (amount > 0) {
				long a = 0;
				if (memory[currentCoinIndex][amount] == 0) {
					a = (numOfCombinations(amount, currentCoinIndex + 1));
					memory[currentCoinIndex][amount] = a;
				} else {
					a = memory[currentCoinIndex][amount];
				}
				numOfCombinations += a;

			}
			if (amount == 0) {
				numOfCombinations++;
			}
			amount -= valueCurrrentCoin;
		}

		return numOfCombinations;
	}

	private static CoinChangeProblem createInstance() {

		CoinChangeProblem ccp = new CoinChangeProblem();
		Scanner sc = new Scanner(System.in);
		ccp.setN(sc.nextInt());
		int M = sc.nextInt();
		int coins[] = new int[M];
		for (int i = 0; i < M; i++) {
			coins[i] = sc.nextInt();
		}
		ccp.setM(M);
		ccp.setCoins(coins);
		long memory[][] = new long[ccp.getM()][ccp.getN() + 1];
		ccp.setMemory(memory);
		sc.close();

		return ccp;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}

	public int[] getCoins() {
		return coins;
	}

	public void setCoins(int[] coins) {
		this.coins = coins;
	}

	public long[][] getMemory() {
		return memory;
	}

	public void setMemory(long[][] memory) {
		this.memory = memory;
	}
}
