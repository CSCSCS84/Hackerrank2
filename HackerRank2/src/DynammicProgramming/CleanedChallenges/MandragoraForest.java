/**
 * https://www.hackerrank.com/contests/master/challenges/mandragora
 */

package DynammicProgramming.CleanedChallenges;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class MandragoraForest {

	static Scanner sc;
	static int N;
	static Mandragora mandragoras[];

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		int numberOfTestCases = sc.nextInt();
		for (int t = 1; t <= numberOfTestCases; t++) {
			readInputForInstance();
			long solution = solve(N, mandragoras);
			System.out.println(solution);
		}
	}

	private static void readInputForInstance() {
		N = sc.nextInt();
		mandragoras = new Mandragora[N];
		for (int i = 0; i < N; i++) {
			Mandragora m = new Mandragora(i, sc.nextInt());
			mandragoras[i] = m;
		}
	}

	private static long solve(int N, Mandragora[] mandragoras) {
		Arrays.sort(mandragoras, new Mandragoraomperator());
		long maxSol = 0;
		long[] healthPoints = calcHealthPoints(N, mandragoras);

		for (int i = 0; i < N; i++) {
			long curSol = 0;
			long experiencePoints = 1 + i;
			curSol = experiencePoints * healthPoints[i];

			if (curSol > maxSol) {
				maxSol = curSol;
			}
		}
		return maxSol;
	}

	private static long[] calcHealthPoints(int N, Mandragora[] mandragoras) {
		long[] healthPoints = new long[N];
		long sumHealthPoints = 0;
		for (int i = N - 1; i >= 0; i--) {
			sumHealthPoints += mandragoras[i].getHealtPoints();
			healthPoints[i] = sumHealthPoints;
		}
		return healthPoints;
	}

	static class Mandragoraomperator implements Comparator<Mandragora> {

		@Override
		public int compare(Mandragora m1, Mandragora m2) {
			if (m1.getHealtPoints() < m2.getHealtPoints()) {
				return -1;
			} else if (m1.getHealtPoints() > m2.getHealtPoints()) {
				return 1;
			} else {
				return 0;
			}
		}

	}

	static class Mandragora {
		int index;
		int healtPoints;

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public int getHealtPoints() {
			return healtPoints;
		}

		public void setHealtPoints(int healtPoints) {
			this.healtPoints = healtPoints;
		}

		public Mandragora(int index, int healtPoints) {
			super();
			this.index = index;
			this.healtPoints = healtPoints;
		}

	}
}
