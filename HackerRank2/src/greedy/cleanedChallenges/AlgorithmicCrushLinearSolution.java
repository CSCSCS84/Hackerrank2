package greedy.cleanedChallenges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Solution for the https://www.hackerrank.com/challenges/crush problem.
 * Algorithm works as follows:
 * 1. Calculate the diffences between between i and i+1 for all i=1,...,N
 * 2. Calculate sum(1,...i) of the differences for all i=1,....,N
 * @author Christoph
 * 
 */

public class AlgorithmicCrushLinearSolution {

	static Scanner sc;

	public static void main(String[] args) {

		String testCase = "/home/christoph/Development2/HackerRank2/TestData/AlgorithmicCrush/AlgorithmicCrushTest07.txt";

		long solution = 0;
		try {
			solution = solve(testCase);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(solution);
	}

	private static long solve(String testCase) throws FileNotFoundException {

		File file = new File(testCase);
		sc = new Scanner(file);
		int N = sc.nextInt();
		int M = sc.nextInt();
		long[] differences = calcDifferencesBetweenIndices(N, M);
		long max = calcMax(N, differences);
		sc.close();
		return max;
	}

	private static long[] calcDifferencesBetweenIndices(int N, int M) {
		
		long differences[] = new long[N + 1];

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			long k = sc.nextLong();
			differences[a] += k;
			differences[b + 1] -= k;
		}
		return differences;
	}

	private static long calcMax(int N, long[] differences) {
		
		long max = 0;
		long sumForIndexI = 0;
		for (int i = 0; i < N; i++) {
			sumForIndexI += differences[i];
			if (sumForIndexI > max) {
				max = sumForIndexI;
			}
		}
		return max;
	}
}
