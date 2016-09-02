/**
 * https://www.hackerrank.com/challenges/count-luck
 * Solve by using Depth-First-Search
 */
package DynammicProgramming.CleanedChallenges;

import java.util.LinkedList;
import java.util.Scanner;

public class CountLuck {

	static byte[][] forest;
	static int N;
	static int M;
	static int K;
	static int startPosX;
	static int startPosY;
	static int keyPosX;
	static int keyPosY;
	static Scanner sc;

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		int T = sc.nextInt();
		// solve for each Instance
		for (int t = 1; t <= T; t++) {
			readInputForInstance();
			CountLuck.solve();
		}
	}

	private static void solve() {
		boolean solution = countLuck(K, startPosX, startPosY, -1);
		if (solution) {
			System.out.println("Impressed");
		} else {
			System.out.println("Oops!");
		}
	}

	private static boolean countLuck(int numberOfWaves, int currentPosX, int currentPosY, int notAllowedMove) {

		if (currentPosX == keyPosX && currentPosY == keyPosY) {
			if (numberOfWaves == 0) {
				return true;
			} else {
				return false;
			}
		}

		LinkedList<Integer> posDirections = calcPossibleDirections(currentPosX, currentPosY, notAllowedMove);
		if (posDirections.size() > 1) {
			numberOfWaves--;
		}
		if (numberOfWaves < 0) {
			return false;
		}

		for (Integer direction : posDirections) {
			if (direction == 0) {
				boolean pathFound = countLuck(numberOfWaves, currentPosX, currentPosY - 1, 2);
				if (pathFound) {
					return true;
				}
			}
			if (direction == 1) {
				boolean pathFound = countLuck(numberOfWaves, currentPosX + 1, currentPosY, 3);
				if (pathFound) {
					return true;
				}
			}
			if (direction == 2) {
				boolean pathFound = countLuck(numberOfWaves, currentPosX, currentPosY + 1, 0);
				if (pathFound) {
					return true;
				}
			}
			if (direction == 3) {
				boolean pathFound = countLuck(numberOfWaves, currentPosX - 1, currentPosY, 1);
				if (pathFound) {
					return true;
				}
			}
		}

		return false;
	}

	// 0 means move above, 1 move right, 2 move below, 3 move right
	private static LinkedList<Integer> calcPossibleDirections(int curposX, int curposY, int notAllowedMove) {

		LinkedList<Integer> directions = new LinkedList<Integer>();

		if (notAllowedMove != 0) {
			if (isValidPosition(curposX, curposY - 1)) {
				directions.add(0);
			}
		}
		if (notAllowedMove != 1) {
			if (isValidPosition(curposX + 1, curposY)) {
				directions.add(1);
			}
		}
		if (notAllowedMove != 2) {
			if (isValidPosition(curposX, curposY + 1)) {
				directions.add(2);
			}
		}
		if (notAllowedMove != 3) {
			if (isValidPosition(curposX - 1, curposY)) {
				directions.add(3);
			}
		}

		return directions;
	}

	static boolean isValidPosition(int posX, int posY) {
		if (posX < 0 || posX >= N || posY < 0 || posY >= M) {
			return false;
		}
		if (forest[posX][posY] == 1) {
			return false;
		}
		return true;
	}

	private static void readInputForInstance() {
		N = sc.nextInt();
		M = sc.nextInt();
		forest = new byte[N][M];
		sc.nextLine();

		for (int i = 0; i < N; i++) {
			String str = sc.nextLine();
			readLine(i, str);
		}

		K = sc.nextInt();
	}

	private static void readLine(int i, String str) {
		for (int j = 0; j < M; j++) {
			switch (str.charAt(j)) {
			case '.':
				forest[i][j] = 0;
				break;
			case 'X':
				forest[i][j] = 1;
				break;
			case 'M':
				forest[i][j] = 2;
				startPosX = i;
				startPosY = j;
				break;
			case '*':
				forest[i][j] = 3;
				keyPosX = i;
				keyPosY = j;
				break;
			}
		}
	}
}