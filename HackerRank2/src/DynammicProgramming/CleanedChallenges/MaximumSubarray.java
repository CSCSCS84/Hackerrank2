/**
 * https://www.hackerrank.com/challenges/maxsubarray
 * Challenge Description: Find the maximal contiguous subarray of an array and the maximal non-contiguous subarray
 */

package DynammicProgramming.CleanedChallenges;

import java.util.Scanner;

public class MaximumSubarray {

	private static Scanner scanner;
	private static int N;
	private static int[] arr;

	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		int numberOfTestCases = scanner.nextInt();
		solve(numberOfTestCases);
		scanner.close();
	}

	private static void solve(int numberOfTestCases) {

		for (int t = 0; t < numberOfTestCases; t++) {
			MaximumSubarray.readInputForTestcase();
			int solution[] = calcMaxContiguousAndMaxNontContiguous();
			System.out.println(solution[0] + " " + solution[1]);
		}
	}

	private static int[] calcMaxContiguousAndMaxNontContiguous() {

		int maxSumContiguous = 0;
		int maxSumNonContiguous = 0;
		int maxNonPositiveNumber = Integer.MIN_VALUE;
		int sumOfPositiveNumbers = 0;

		int maxLastIndex = 0;

		// calculate MaxNonPositiveNumber, SumOfPositiveNumbers and
		// maxSumContiguous (if there is at least one non-negative value )
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= 0 && arr[i] > maxNonPositiveNumber) {
				maxNonPositiveNumber = arr[i];
			}

			if (arr[i] > 0) {
				sumOfPositiveNumbers += arr[i];
			}

			int maxCurrentIndex = Math.max(maxLastIndex + arr[i], 0);

			if (maxCurrentIndex > maxSumContiguous) {
				maxSumContiguous = maxCurrentIndex;
			}
			maxLastIndex = maxCurrentIndex;
		}

		// if the arrays contains non negative number
		if (sumOfPositiveNumbers == 0 && maxSumNonContiguous == 0) {
			maxSumNonContiguous = maxNonPositiveNumber;
			maxSumContiguous = maxNonPositiveNumber;
		} else {
			maxSumNonContiguous = sumOfPositiveNumbers;
		}

		int[] solution = { maxSumContiguous, maxSumNonContiguous };
		return solution;
	}

	private static void readInputForTestcase() {
		N = scanner.nextInt();
		arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = scanner.nextInt();
		}

	}
}