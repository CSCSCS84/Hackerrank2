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
	long memoryNumOfPathForDimensionNumOfSteps[][];
	static long modulo = 1000000007;
	// needed for the calculations in step 2 and 3. stores in [i][j] the
	// numofcombination when we have to make M-i steps in the dimensions
	// i+1,...,N
	static long memoryNumOfCombination[][];

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
		memoryNumOfCombination = new long[N][M + 1];
		long result = calcNumOfWays(M, 0);

		return result;
	}

	/**
	 * Calculates for each dimension i the number of ways of length j=1,...,M
	 * and stores it in memoryNumOfPathForDimensionLengthOfPath[i][j]
	 */
	private void calcNumOfWaysForEachDimension() {

		memoryNumOfPathForDimensionNumOfSteps = new long[N][M];
		for (int dimension = 0; dimension < N; dimension++) {
			for (int numOfSteps = 1; numOfSteps <= M; numOfSteps++) {
				memoryNumOfWaysForPosition = new long[border[dimension]][numOfSteps];
				long result = solveForCurrentPos(startPosition[dimension], (short) numOfSteps, dimension);
				memoryNumOfPathForDimensionNumOfSteps[dimension][numOfSteps - 1] = result;
			}
		}
	}

	private long calcNumOfWays(short numberOfOfElementsForSubset, int dimension) {
		// TODO dimension==N needed?
		if (numberOfOfElementsForSubset == 0 || dimension == N) {
			return 1;
		}

		short minNumOfStepForDimension = 0;
		if (dimension == N - 1) {
			minNumOfStepForDimension = numberOfOfElementsForSubset;
		}

		long numOfWays = 0;
		long bio = 1;
		for (short i = numberOfOfElementsForSubset; i >= minNumOfStepForDimension; i--) {
			bio = updateBio(numberOfOfElementsForSubset, bio, i);

			long waysForElement  = waysForElement(i, dimension, bio);
			long waysForNextSubset = 0;
			
			if (memoryNumOfCombination[dimension][numberOfOfElementsForSubset - i] != 0) {
				waysForNextSubset = memoryNumOfCombination[dimension][numberOfOfElementsForSubset - i];

			} else {
				waysForNextSubset = calcNumOfWays((short) (numberOfOfElementsForSubset - i), dimension + 1) % modulo;
				memoryNumOfCombination[dimension][numberOfOfElementsForSubset - i] = waysForNextSubset;
			}
			waysForElement = (waysForElement * waysForNextSubset) % modulo;
			numOfWays = (numOfWays + waysForElement) % modulo;
		}
		return numOfWays;
	}
	private long waysForElement(int i,int dimension,long bio){
		long numOfPathForEl = 1;
		if (i != 0) {
			numOfPathForEl = memoryNumOfPathForDimensionNumOfSteps[dimension][i - 1];
		}
		return (numOfPathForEl * bio) % modulo;
	}

	private long updateBio(short numberOfOfElementsForSubset, long bio, short i) {
		if (i != numberOfOfElementsForSubset) {

			bio = moduloDivision((bio * (i + 1)) % modulo, numberOfOfElementsForSubset - i, modulo);
		} else {
			bio = 1;
		}
		return bio;
	}

	private long solveForCurrentPos(byte currentPosition, short numOfSteps, int dimension) {
		if (numOfSteps == 0) {
			return 1;
		}
		long numOfWays = 0;
		if (currentPosition + 1 <= border[dimension]) {
			if (memoryNumOfWaysForPosition[currentPosition + 1 - 1][numOfSteps - 1] == 0) {
				long a = solveForCurrentPos((byte) (currentPosition + 1), (short) (numOfSteps - 1), dimension);
				numOfWays = (numOfWays + a) % modulo;
				memoryNumOfWaysForPosition[currentPosition + 1 - 1][numOfSteps - 1] = a;
			} else {
				numOfWays = (numOfWays + memoryNumOfWaysForPosition[currentPosition + 1 - 1][numOfSteps - 1]) % modulo;
			}
		}

		if (currentPosition - 1 >= 1) {

			if (memoryNumOfWaysForPosition[currentPosition - 1 - 1][numOfSteps - 1] == 0) {
				long a = solveForCurrentPos((byte) (currentPosition - 1), (short) (numOfSteps - 1), dimension);
				numOfWays = (numOfWays + a) % modulo;
				memoryNumOfWaysForPosition[currentPosition - 1 - 1][numOfSteps - 1] = a;
			} else {
				numOfWays = (numOfWays + memoryNumOfWaysForPosition[currentPosition - 1 - 1][numOfSteps - 1]) % modulo;
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
