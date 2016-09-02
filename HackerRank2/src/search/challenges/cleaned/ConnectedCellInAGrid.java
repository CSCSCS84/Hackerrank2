/**
 * https://www.hackerrank.com/challenges/connected-cell-in-a-grid for a
 * (Short) Challenge Description: Given a grid with 0 and 1 values we want to find the largest connected region
 * 
 * @author Christoph
 * 
 */
package search.challenges.cleaned;

import java.util.Scanner;

public class ConnectedCellInAGrid {

	static byte grid[][];
	static boolean isAlreadyInRegion[][];
	static int m;
	static int n;

	public static void main(String[] args) {
		readInput();
		int result = ConnectedCellInAGrid.solve();
		System.out.println(result);
	}

	private static int solve() {
		int maxRegionSize = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1 && !isAlreadyInRegion[i][j]) {
					int regionSize = findSizeOfRegion(i, j);
					if (regionSize > maxRegionSize) {
						maxRegionSize = regionSize;
					}
				}
			}
		}

		return maxRegionSize;
	}

	private static int findSizeOfRegion(int i, int j) {
		isAlreadyInRegion[i][j] = true;
		int regionSize = 1;

		//above
		if (checkCell(i + 1, j)) {
			regionSize += findSizeOfRegion(i + 1, j);
		}
		//below
		if (checkCell(i - 1, j)) {
			regionSize += findSizeOfRegion(i - 1, j);
		}
		//left
		if (checkCell(i, j - 1)) {
			regionSize += findSizeOfRegion(i, j - 1);
		}
		//right
		if (checkCell(i, j + 1)) {
			regionSize += findSizeOfRegion(i, j + 1);
		}
		//above left
		if (checkCell(i - 1, j - 1)) {
			regionSize += findSizeOfRegion(i - 1, j - 1);
		}
		//above right
		if (checkCell(i - 1, j + 1)) {
			regionSize += findSizeOfRegion(i - 1, j + 1);
		}
		//below left
		if (checkCell(i + 1, j - 1)) {
			regionSize += findSizeOfRegion(i + 1, j - 1);
		}
		//below right
		if (checkCell(i + 1, j + 1)) {
			regionSize += findSizeOfRegion(i + 1, j + 1);
		}

		return regionSize;
	}

	private static boolean checkCell(int i, int j) {
		if (i >= 0 && i < m && j >= 0 && j < n) {
			if (grid[i][j] == 1 && !isAlreadyInRegion[i][j]) {
				return true;
			}
		}
		return false;
	}

	private static void readInput() {
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		grid = new byte[m][n];
		isAlreadyInRegion = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = sc.nextByte();
			}
		}

		sc.close();
	}
}