package DynammicProgramming;

import java.util.Scanner;

/**
 * See https://www.hackerrank.com/challenges/connected-cell-in-a-grid for a
 * description of the problem
 * 
 * @author christoph
 * 
 */
public class ConnectedCellInAGrid {

	static byte grid[][];
	static boolean cellIsAlreadyInARegion[][];
	static int m;
	static int n;

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		readInput();
		ConnectedCellInAGrid cg = new ConnectedCellInAGrid();
		cg.solve();

	}

	private static void readInput() {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		grid = new byte[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				byte a = sc.nextByte();
				if (a == 1) {
					grid[i][j] = a;
				}
			}
		}
	}

	private int solve() {
		int maxRegion = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0 && !cellIsAlreadyInARegion[i][j]) {
					int region = findLengthOfRegion(i, j);
					if (region > maxRegion) {
						maxRegion = region;
					}
				}
			}
		}
		return maxRegion;
	}

	private int findLengthOfRegion(int i, int j) {
		int region = 1;

		// above cell
		if (checkCell(i + 1, j)) {
			region += findLengthOfRegion(i + 1, j);
		}
		// below cell
		if (checkCell(i - 1, j)) {
			region += findLengthOfRegion(i - 1, j);
		}
		// left cell
		if (checkCell(i, j - 1)) {
			region += findLengthOfRegion(i, j - 1);
		}
		// right cell
		if (checkCell(i, j + 1)) {
			region += findLengthOfRegion(i, j + 1);
		}
		// above left cell
		if (checkCell(i - 1, j - 1)) {
			region += findLengthOfRegion(i - 1, j - 1);
		}

		// above right cell
		if (checkCell(i - 1, j + 1)) {
			region += findLengthOfRegion(i - 1, j + 1);
		}

		// below left cell
		if (checkCell(i + 1, j - 1)) {
			region += findLengthOfRegion(i + 1, j - 1);
		}
		// below right cell
		if (checkCell(i + 1, j + 1)) {
			region += findLengthOfRegion(i + 1, j + 1);
		}

		return region;
	}

	private boolean checkCell(int i, int j) {
		if (i >= 0 && i < m && j >= 0 && j < n) {
			if (grid[i][j] == 1) {
				if (!cellIsAlreadyInARegion[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}