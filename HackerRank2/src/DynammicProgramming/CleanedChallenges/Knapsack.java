/**
 * https://www.hackerrank.com/challenges/unbounded-knapsack/submissions/code/27059486
 * Challenge Description: Knapsack Problem
 */

package DynammicProgramming.CleanedChallenges;

import java.util.HashMap;
import java.util.Scanner;

public class Knapsack {

	HashMap<Integer, Integer> memory;
	int n;
	int k;
	int numbers[];
	static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int numOfTestcases = sc.nextInt();

		for (int t = 1; t <= numOfTestcases; t++) {
			Knapsack ks = new Knapsack();
			ks.readInputForInstance();
			int sol = ks.solve();
			System.out.println(sol);
		}
	}

	int solve() {
		memory = new HashMap<Integer, Integer>();
		int sol = knapsack(numbers, k);
		return sol;
	}

	int knapsack(int items[], int expectedSum) {
		int maxSum = 0;

		for (int i = 0; i < items.length; i++) {
			int curSum = items[i];
			if (curSum <= expectedSum) {
				int subSolutionSum;
				if (memory.containsKey(expectedSum - curSum)) {
					subSolutionSum = memory.get(expectedSum - curSum);
				} else {
					subSolutionSum = knapsack(items, expectedSum - curSum);
					memory.put(expectedSum - curSum, subSolutionSum);
				}
				if (curSum + subSolutionSum <= expectedSum) {
					curSum += subSolutionSum;
				}
				if (curSum > maxSum) {
					maxSum = curSum;
				}
			}
		}

		return maxSum;
	}

	private void readInputForInstance() {
		n = sc.nextInt();
		k = sc.nextInt();
		numbers = new int[n];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = sc.nextInt();
		}
	}
}
