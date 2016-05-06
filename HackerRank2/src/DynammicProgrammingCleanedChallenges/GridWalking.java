package DynammicProgrammingCleanedChallenges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implementation of an algorithm for the GridWalking Problem
 * https://www.hackerrank.com/challenges/grid-walking The Algorithmus works as
 * follows: 1. Let i be one of dimensions. We calculated the number of ways with
 * j=1,..,M steps where we only make a step in dimension i. 2. We calculate all
 * posibilities in the dimension for making excactly M steps. 3. We multiply
 * somhow the results from 1. and 2.
 * 
 * Explanation for 2. and 3.: Let N=3 and M=10. So we could do x0=3 steps in
 * dimension 0, x1=3 in dimension 1 and x2=4 in dimension 2. In 1. we calculated
 * the number of ways with length k for this dimension. So we multiply these 3
 * numbers. Note, that there is some combinatoric needed since we could do the
 * steps in an arbitrary order. Note, that we have to calculate all posibilities
 * where x0+x1+x2=10 with x0,x1,x2 is the number of steps in each dimension.
 */
public class GridWalking {
	static Scanner scanner;
	static int numberOfTestCases;
	static byte N;
	static short M;
	byte border[];
	// stores for a dimension i the number of ways with length k starting from
	// position j in [j][k]
	long memoryNumOfWaysForPosition[][];
	// stores for dimension i the number of path with length j, 1<=j<=M in
	// [i][j]
	long memoryNumOfWaysForDimensionNumOfSteps[][];
	static long modulo = 1000000007;
	// needed for the calculations in step 2 and 3. stores in [i][j] the
	// numofcombination when we have to make M-i steps in the dimensions
	// i+1,...,N
	static long memoryNumOfWays[][];

	static byte[] startPosition;

	public static void main(String[] args) throws FileNotFoundException {

		String pathOfTestCaseFile = "/home/christoph/Development2/HackerRank2/TestData/GridWalking/GridWalkingTest03.txt";
		File file = new File(pathOfTestCaseFile);
		scanner = new Scanner(file);
		numberOfTestCases = scanner.nextInt();

		for (int t = 1; t <= numberOfTestCases; t++) {
			GridWalking gr = new GridWalking();
			gr.readInputForTestcase();
			long result = gr.solve();
			System.out.println(result);
		}
	}

	private long solve() {

		calcNumOfWaysForEachDimension();
		memoryNumOfWays = new long[N][M + 1];
		long result = calcNumOfWays(M, 0);

		return result;
	}

	/**
	 * Calculates for each dimension i the number of ways of length j=1,...,M
	 * and stores it in memoryNumOfPathForDimensionLengthOfPath[i][j]
	 */
	private void calcNumOfWaysForEachDimension() {

		memoryNumOfWaysForDimensionNumOfSteps = new long[N][M];
		for (int dimension = 0; dimension < N; dimension++) {
			for (int numOfSteps = 1; numOfSteps <= M; numOfSteps++) {
				memoryNumOfWaysForPosition = new long[border[dimension]][numOfSteps];
				long result = numOfWaysForOneDimensional(startPosition[dimension], (short) numOfSteps, dimension);
				memoryNumOfWaysForDimensionNumOfSteps[dimension][numOfSteps - 1] = result;
			}
		}
	}

	private long calcNumOfWays(short numberOfOfElementsForSubset, int dimension) {

		if (numberOfOfElementsForSubset == 0) {
			return 1;
		}

		short minNumOfStepForDimension = 0;
		if (dimension == N - 1) {
			minNumOfStepForDimension = numberOfOfElementsForSubset;
		}

		long numOfWays = 0;
		// i.e. a let (1,4,2) denote the num of steps we make in each iteration.
		// Then we have to calculate how many combinations of ways exist that do
		// 1 step in dimension 0, 4 in 1 and 2 in 2. Note: If we add to a way of
		// length M x_i steps in dimension i, we have to multiply our
		// combination
		// by C(M,x_i)
		long numOfCombinations = 1;

		for (short numOfStepsForThisDimension = numberOfOfElementsForSubset; numOfStepsForThisDimension >= minNumOfStepForDimension; numOfStepsForThisDimension--) {
			numOfCombinations = updateNumOfCombinations(numberOfOfElementsForSubset, numOfCombinations,
					numOfStepsForThisDimension);

			// calculate how many ways exists if we only move in dimension
			// dimension...N-1
			long numOfWaysThisDimensionToN = numOfWaysForDimension(numOfStepsForThisDimension, dimension,
					numOfCombinations);
			long numOfWaysForNextDimensions = 0;

			if (memoryNumOfWays[dimension][numberOfOfElementsForSubset - numOfStepsForThisDimension] != 0) {
				numOfWaysForNextDimensions = memoryNumOfWays[dimension][numberOfOfElementsForSubset
						- numOfStepsForThisDimension];
			} else {
				numOfWaysForNextDimensions = calcNumOfWays(
						(short) (numberOfOfElementsForSubset - numOfStepsForThisDimension), dimension + 1) % modulo;
				memoryNumOfWays[dimension][numberOfOfElementsForSubset - numOfStepsForThisDimension] = numOfWaysForNextDimensions;
			}

			numOfWaysThisDimensionToN = (numOfWaysThisDimensionToN * numOfWaysForNextDimensions) % modulo;
			numOfWays = (numOfWays + numOfWaysThisDimensionToN) % modulo;
		}
		return numOfWays;
	}

	private long numOfWaysForDimension(int lengthOfWay, int dimension, long mult) {
		long numOfWaysForDimension = 1;
		if (lengthOfWay != 0) {
			numOfWaysForDimension = memoryNumOfWaysForDimensionNumOfSteps[dimension][lengthOfWay - 1];
		}
		return (numOfWaysForDimension * mult) % modulo;
	}

	private long updateNumOfCombinations(short numberOfOfElementsForSubset, long mult, short numOfSteps) {
		if (numOfSteps != numberOfOfElementsForSubset) {

			mult = moduloDivision((mult * (numOfSteps + 1)) % modulo, numberOfOfElementsForSubset - numOfSteps, modulo);
		} else {
			mult = 1;
		}
		return mult;
	}

	/**
	 * Calculates the number of ways if we have only one dimension where we can make steps. 
	 * 
	 * @param position
	 * @param lengthOfWay
	 * @param dimension
	 * @return
	 */
	private long numOfWaysForOneDimensional(byte position, short lengthOfWay, int dimension) {
		if (lengthOfWay == 0) {
			return 1;
		}
		long numOfWays = 0;
		if (position + 1 <= border[dimension]) {
			if (memoryNumOfWaysForPosition[position + 1 - 1][lengthOfWay - 1] == 0) {
				long a = numOfWaysForOneDimensional((byte) (position + 1), (short) (lengthOfWay - 1), dimension);
				numOfWays = (numOfWays + a) % modulo;
				memoryNumOfWaysForPosition[position + 1 - 1][lengthOfWay - 1] = a;
			} else {
				numOfWays = (numOfWays + memoryNumOfWaysForPosition[position + 1 - 1][lengthOfWay - 1]) % modulo;
			}
		}

		if (position - 1 >= 1) {

			if (memoryNumOfWaysForPosition[position - 1 - 1][lengthOfWay - 1] == 0) {
				long a = numOfWaysForOneDimensional((byte) (position - 1), (short) (lengthOfWay - 1), dimension);
				numOfWays = (numOfWays + a) % modulo;
				memoryNumOfWaysForPosition[position - 1 - 1][lengthOfWay - 1] = a;
			} else {
				numOfWays = (numOfWays + memoryNumOfWaysForPosition[position - 1 - 1][lengthOfWay - 1]) % modulo;
			}
		}

		return numOfWays;
	}

	private void readInputForTestcase() {
		N = scanner.nextByte();
		M = scanner.nextShort();

		startPosition = new byte[N];
		for (int i = 0; i < N; i++) {
			startPosition[i] = scanner.nextByte();
		}
		border = new byte[N];
		for (int i = 0; i < N; i++) {
			border[i] = scanner.nextByte();
		}

	}

	private long moduloDivision(long numerator, long denominator, long modulo) {

		long moduloInverse = moduloInverse(denominator, modulo);
		return (numerator * moduloInverse) % modulo;

	}

	private long moduloInverse(long number, long modulo) {
		return moduloPow(number, modulo - 2);
	}

	public long moduloPow(long x, long n) {
		long result = 1;

		while (n > 0) {
			if (n % 2 != 0) {
				result = (result * x) % modulo;
			}
			x = (x * x) % modulo;
			n = n / 2;
		}
		return result;
	}
}
