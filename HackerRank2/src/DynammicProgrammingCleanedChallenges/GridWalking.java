package DynammicProgrammingCleanedChallenges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GridWalking {
	static Scanner sc;
	static int numberOfTestCases;
	static byte N;
	static short M;
	byte border[];
	long memoryCurPos[][];
	long memoryNumOfPathForDimensionLengthOfPath[][];
	static long sol = 0;
	static long modulo = 1000000007;
	static long memoryWays[][];
	
	static byte[] position;

	public static void main(String[] args) throws FileNotFoundException {
		String pathOfTestCaseFile = "/home/christoph/Development2/HackerRank2/TestData/GridWalking/GridWalkingTest03.txt";
		File file = new File(pathOfTestCaseFile);
		sc = new Scanner(file);
		numberOfTestCases = sc.nextInt();
		GridWalking gr = new GridWalking();
		for (int t = 1; t <= numberOfTestCases; t++) {

			long result = gr.solve();
			System.out.println(result);
			sol = 0;

		}

	}

	private long solve() {
		readInput();
		long result = 0;
		memoryNumOfPathForDimensionLengthOfPath = new long[N][M];
		for (byte dimension = 0; dimension < N; dimension++) {
			for (int numOfSteps = 1; numOfSteps <= M; numOfSteps++) {
				memoryCurPos = new long[border[dimension]][numOfSteps];
				result = solveForCurrentPos(position[dimension], (short) numOfSteps, dimension);

				memoryNumOfPathForDimensionLengthOfPath[dimension][numOfSteps - 1] = result;
			}

		}
		memoryWays = new long[N][M + 1];
		result = calcSubsetsLengthK((short) M, 0, 1);

		return result;
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

	private long calcSubsetsLengthK(short numberOfOfElementsForSubset, int dimension, long mult) {

		if (numberOfOfElementsForSubset == 0 || dimension == N) {

			return 1;
		}
		long ways = 0;
		short a = 0;

		if (dimension == N - 1) {
			a = (short) (numberOfOfElementsForSubset);
		}
		long bio = 1;
		for (Short i = (short) (numberOfOfElementsForSubset); i >= a; i--) {
			long waysForElement = 1;
			long numOfPathForEl = 1;
			long multCopy = mult;
			long multi = 1;
			if (i != 0) {
				numOfPathForEl = memoryNumOfPathForDimensionLengthOfPath[dimension][i - 1];
				if (i != M) {

					if (i != numberOfOfElementsForSubset) {

						bio = moduloDivision((bio * (i + 1)) % modulo, numberOfOfElementsForSubset - i, modulo);
					}
					multi = bio;
				}

			}
			waysForElement = (numOfPathForEl * multi) % modulo;
			long waysForNextSubset = 0;
			if (memoryWays[dimension][numberOfOfElementsForSubset - i] != 0) {
				waysForNextSubset = memoryWays[dimension][numberOfOfElementsForSubset - i];

			} else {
				waysForNextSubset = calcSubsetsLengthK((short) (numberOfOfElementsForSubset - i), dimension + 1,
						multCopy) % modulo;
				memoryWays[dimension][numberOfOfElementsForSubset - i] = waysForNextSubset;
			}
			waysForElement = (waysForElement * waysForNextSubset) % modulo;
			ways = (ways + waysForElement) % modulo;
		}
		return ways;
	}

	private long solveForCurrentPos(byte currentPosition, short numOfSteps, byte dimension) {
		if (numOfSteps == 0) {
			return 1;
		}
		long numOfWays = 0;
		if (currentPosition + 1 <= border[dimension]) {
			if (memoryCurPos[currentPosition + 1 - 1][numOfSteps - 1] == 0) {
				long a = solveForCurrentPos((byte) (currentPosition + 1), (short) (numOfSteps - 1), dimension);
				numOfWays = (numOfWays + a) % modulo;
				memoryCurPos[currentPosition + 1 - 1][numOfSteps - 1] = a;
			} else {
				numOfWays = (numOfWays + memoryCurPos[currentPosition + 1 - 1][numOfSteps - 1]) % modulo;
			}
		}

		if (currentPosition - 1 >= 1) {

			if (memoryCurPos[currentPosition - 1 - 1][numOfSteps - 1] == 0) {
				long a = solveForCurrentPos((byte) (currentPosition - 1), (short) (numOfSteps - 1), dimension);
				numOfWays = (numOfWays + a) % modulo;
				memoryCurPos[currentPosition - 1 - 1][numOfSteps - 1] = a;
			} else {
				numOfWays = (numOfWays + memoryCurPos[currentPosition - 1 - 1][numOfSteps - 1]) % modulo;
			}
		}

		return numOfWays;
	}

	private void readInput() {
		N = sc.nextByte();
		M = sc.nextShort();

		 position = new byte[N];
		for (int i = 0; i < N; i++) {
			position[i] = sc.nextByte();
		}
		border = new byte[N];
		for (int i = 0; i < N; i++) {
			border[i] = sc.nextByte();
		}

	}
}
