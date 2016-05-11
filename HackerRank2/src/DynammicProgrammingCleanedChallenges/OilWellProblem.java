package DynammicProgrammingCleanedChallenges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Algorithm for solving the https://www.hackerrank.com/challenges/oil-well
 * 
 * Problem. This Recursive algorithm works as follows: A current border is given
 * and all OilWells in this border are set up. The algorithm chose then a not
 * set up OilWell to open. Note, that not all not set up OilWells have to be
 * checked. We check the region above, below, left, right, left above, left
 * below, right above, right below the current border. For above/below/left/
 * right the following statement proofs: If a OilWell above the current corner
 * is set up, only the OilWells with the minimum vertical distance to the
 * current upperBorder of the Border are interisting. If there are more than one
 * OilWell above the current corner, the minimum cost will be for all the same.
 * So for above/below/left/ right at most one OilWell is interisting to set up.
 * For the other 4 cases see the method searchDiagonal for more Details.
 * 
 * If the Border is updated all OilWells will be set up if not already done. In
 * an optimal solutions this has to be done because the cost for setting up a
 * OilWell increase if the border increase.
 * 
 * @author Christoph
 * 
 */
public class OilWellProblem {

	// grid[i][j] is 1, if there is a OilWell on Position i,j
	boolean grid[][];
	byte r;
	byte c;
	int numberOfOilWells = 0;
	// for a given border, memory stores the minimum cost we need to open the
	// OilWells that are not in the Border where [i][j][k][l] stores the cost
	// for border i,j,k,l
	int[][][][] memoryMinimumCostForSetUpAllOilWellsOutOfBorder;
	int minSolutionValue = Integer.MAX_VALUE;

	public static void main(String[] args) {

		double time1 = System.currentTimeMillis();

		String pathOfTestCaseFile = "/home/christoph/Development/HackerRank/OilWellTest2.txt";
		OilWellProblem oilWell = new OilWellProblem();
		int sol = oilWell.solve(pathOfTestCaseFile);
		System.out.println(sol);

		double time2 = System.currentTimeMillis();
		System.out.println((time2 - time1) / 1000);

	}

	public int solve(String pathOfTestCaseFile) {

		readInput(pathOfTestCaseFile);

		if (this.numberOfOilWells == 0) {
			return 0;
		}

		for (byte i = 0; i < r; i++) {
			for (byte j = 0; j < c; j++) {
				if (grid[i][j]) {
					byte[] border = new byte[4];
					border[0] = i;
					border[1] = i;
					border[2] = j;
					border[3] = j;

					int solution = minimumCostForSetUpAllOilWellsOutOfBorder(border);

					if (solution < minSolutionValue) {
						minSolutionValue = solution;
					}
				}
			}
		}
		return minSolutionValue;
	}

	private int minimumCostForSetUpAllOilWellsOutOfBorder(byte[] border) {

		int minnimumCostForSetUpAllOilWellsOutOfBorder = Integer.MAX_VALUE;

		LinkedList<OilWell> possibleOilWellsForNextSetUp = possibleOilWellsForNextSetUp(border);

		if (possibleOilWellsForNextSetUp.size() == 0) {
			return 0;
		} else {
			for (OilWell oilWell : possibleOilWellsForNextSetUp) {

				int costSetUpNewOilWells = costSetUpOilWell(border, oilWell.getVerticalPos(),
						oilWell.getHorizontalPos());

				byte[] updatedBorder = updateBorder(border, oilWell.getVerticalPos(), oilWell.getHorizontalPos());
				costSetUpNewOilWells += costSetUpAllNewOilWellsInBorder(updatedBorder, border,
						oilWell.getVerticalPos(), oilWell.getHorizontalPos(), oilWell.getPositionToCurrentBorder());

				int memoryCost = memoryCostForBorder(updatedBorder);
				int a = 0;
				if (0 == memoryCost) {
					a = minimumCostForSetUpAllOilWellsOutOfBorder(updatedBorder);
					memoryMinimumCostForSetUpAllOilWellsOutOfBorder[updatedBorder[0]][updatedBorder[1]][updatedBorder[2]][updatedBorder[3]] = a;
				} else {
					a = memoryCost;
				}

				costSetUpNewOilWells += a;
				if (costSetUpNewOilWells < minnimumCostForSetUpAllOilWellsOutOfBorder) {
					minnimumCostForSetUpAllOilWellsOutOfBorder = costSetUpNewOilWells;
				}
			}
		}
		return minnimumCostForSetUpAllOilWellsOutOfBorder;
	}

	private int memoryCostForBorder(byte[] border) {
		return memoryMinimumCostForSetUpAllOilWellsOutOfBorder[border[0]][border[1]][border[2]][border[3]];
	}

	/**
	 * Calculates the cost for setting up all OilWells the are in outerBorder
	 * but not in innerBorder and where that are not on position
	 * (oilWellVerticalPos,oilWellHorizontalPos)
	 * 
	 * @param outerBorder
	 * @param innerBorder
	 * @param oilWellVerticalPos
	 * @param oilWellHorizontalPos
	 * @param positionOfOilWellToCurrentBorder
	 * @return
	 */
	private int costSetUpAllNewOilWellsInBorder(byte[] outerBorder, byte[] innerBorder, byte oilWellVerticalPos,
			byte oilWellHorizontalPos, byte positionOfOilWellToCurrentBorder) {

		int costSetUpNewOilWells = 0;
		if (positionOfOilWellToCurrentBorder <= 3) {
			costSetUpNewOilWells = costSetUpOilWellsNonDiagonal(outerBorder, oilWellVerticalPos, oilWellHorizontalPos,
					positionOfOilWellToCurrentBorder);
		} else {
			costSetUpNewOilWells = costSetUpOilWellsDiagonal(outerBorder, innerBorder, oilWellVerticalPos,
					oilWellHorizontalPos, positionOfOilWellToCurrentBorder);
		}

		return costSetUpNewOilWells;

	}

	private int addWellsInRange(byte a, byte b, byte c, byte d, byte cordI, byte cordJ, byte[] border) {
		int sumCostForSetUpOilWells = 0;
		for (byte i = a; i <= b; i++) {
			for (byte j = c; j <= d; j++) {
				if (grid[i][j]) {
					if (!(cordI == i && cordJ == j)) {
						byte cost = costSetUpOilWell(border, i, j);
						sumCostForSetUpOilWells += cost;
					}
				}
			}
		}
		return sumCostForSetUpOilWells;
	}

	private int costSetUpOilWellsDiagonal(byte[] border, byte[] oldBorder, byte cordI, byte cordJ, byte posWellAdded) {
		short sumCostForSetUpOilWells = 0;
		// left above
		if (posWellAdded == 4) {
			// left
			sumCostForSetUpOilWells += addWellsInRange(border[0], border[1], border[2], (byte) (border[3] - 1), cordI,
					cordJ, border);
			// for (byte i = border[0]; i <= border[1]; i++) {
			// for (byte j = border[2]; j < oldBorder[2]; j++) {
			// if (grid[i][j]) {
			// if (!(cordI == i && cordJ == j)) {
			// byte cost = costSetUpOilWell(border, i, j);
			// sumCostForSetUpOilWells += cost;
			// }
			// }
			// }
			// }
			// above
			for (byte i = border[0]; i < oldBorder[0]; i++) {
				for (byte j = oldBorder[2]; j <= border[3]; j++) {
					if (grid[i][j]) {
						if (!(cordI == i && cordJ == j)) {
							byte cost = costSetUpOilWell(border, i, j);
							sumCostForSetUpOilWells += cost;
						}
					}
				}
			}
			return sumCostForSetUpOilWells;
		}

		// left below
		if (posWellAdded == 5) {
			// left
			for (byte i = border[0]; i <= border[1]; i++) {
				for (byte j = border[2]; j < oldBorder[2]; j++) {
					if (grid[i][j]) {
						if (!(cordI == i && cordJ == j)) {
							byte cost = costSetUpOilWell(border, i, j);
							sumCostForSetUpOilWells += cost;
						}
					}
				}
			}
			// below
			for (byte i = (byte) (oldBorder[1] + 1); i <= border[1]; i++) {
				for (byte j = oldBorder[2]; j <= border[3]; j++) {
					if (grid[i][j]) {
						if (!(cordI == i && cordJ == j)) {
							byte cost = costSetUpOilWell(border, i, j);
							sumCostForSetUpOilWells += cost;
						}
					}
				}
			}
			return sumCostForSetUpOilWells;
		}

		// right above
		if (posWellAdded == 6) {
			// above
			for (byte i = border[0]; i < oldBorder[0]; i++) {
				for (byte j = oldBorder[2]; j <= border[3]; j++) {
					if (grid[i][j]) {
						if (!(cordI == i && cordJ == j)) {
							byte cost = costSetUpOilWell(border, i, j);
							sumCostForSetUpOilWells += cost;
						}
					}
				}
			}
			// right
			for (byte i = (byte) (border[0]); i <= border[1]; i++) {
				for (byte j = (byte) (oldBorder[3] + 1); j <= border[3]; j++) {
					if (grid[i][j]) {
						if (!(cordI == i && cordJ == j)) {
							byte cost = costSetUpOilWell(border, i, j);
							sumCostForSetUpOilWells += cost;
						}
					}
				}
			}
			return sumCostForSetUpOilWells;
		}

		// right below
		if (posWellAdded == 7) {
			// below
			for (byte i = (byte) (oldBorder[1] + 1); i <= border[1]; i++) {
				for (byte j = (byte) (border[2]); j <= oldBorder[3]; j++) {
					if (grid[i][j]) {
						if (!(cordI == i && cordJ == j)) {
							byte cost = costSetUpOilWell(border, i, j);
							sumCostForSetUpOilWells += cost;
						}
					}
				}
			}
			// right
			for (byte i = (byte) (border[0]); i <= border[1]; i++) {
				for (byte j = (byte) (oldBorder[3] + 1); j <= border[3]; j++) {
					if (grid[i][j]) {
						if (!(cordI == i && cordJ == j)) {
							byte cost = costSetUpOilWell(border, i, j);
							sumCostForSetUpOilWells += cost;
						}
					}
				}
			}
			return sumCostForSetUpOilWells;
		}
		return sumCostForSetUpOilWells;
	}

	public int costSetUpOilWellsNonDiagonal(byte[] border, byte cordI, byte cordJ, byte posWellAdded) {
		// above
		short sumOfCost = 0;
		if (posWellAdded == 0) {
			byte i = border[0];
			byte maxVer = (byte) (border[1] - border[0]);
			byte hor1 = 0;
			byte hor2 = (byte) (border[3] - border[2]);
			for (byte j = border[2]; j <= border[3]; j++) {
				if (grid[i][j]) {

					if (cordJ != j) {
						byte maxHor = max(hor1, hor2);
						byte cost = max(maxVer, maxHor);
						sumOfCost += cost;

					}

				}
				hor1++;
				hor2--;
			}
			return sumOfCost;
		}
		// below
		if (posWellAdded == 1) {
			byte i = border[1];
			byte maxVer = (byte) (border[1] - border[0]);
			byte hor1 = 0;
			byte hor2 = (byte) (border[3] - border[2]);
			for (byte j = border[2]; j <= border[3]; j++) {
				if (grid[i][j]) {
					if (cordJ != j) {
						byte maxHor = max(hor1, hor2);
						// int cost = costForAddingWell(border, i, j);
						byte cost = max(maxVer, maxHor);
						sumOfCost += cost;
					}
				}
				hor1++;
				hor2--;
			}
			return sumOfCost;
		}

		// left
		if (posWellAdded == 2) {
			byte j = border[2];
			byte maxHor = (byte) (border[3] - border[2]);
			byte vert1 = 0;
			byte vert2 = (byte) (border[1] - border[0]);
			for (byte i = border[0]; i <= border[1]; i++) {
				if (grid[i][j]) {
					if (cordI != i) {
						byte maxVer = max(vert1, vert2);
						// int cost = costForAddingWell(border, i, j);
						byte cost = max(maxVer, maxHor);
						sumOfCost += cost;
					}
				}
				vert1++;
				vert2--;
			}
			return sumOfCost;
		}

		// right
		if (posWellAdded == 3) {

			byte j = border[3];
			byte maxHor = (byte) (border[3] - border[2]);
			byte vert1 = 0;
			byte vert2 = (byte) (border[1] - border[0]);
			for (byte i = border[0]; i <= border[1]; i++) {
				if (grid[i][j]) {
					if (cordI != i) {
						byte maxVer = max(vert1, vert2);
						// int cost = costForAddingWell(border, i, j);
						byte cost = max(maxVer, maxHor);
						sumOfCost += cost;
					}
				}
				vert1++;
				vert2--;
			}
			return sumOfCost;
		}
		return sumOfCost;
	}

	// ca. 2.6 s
	private byte costSetUpOilWell(byte[] border, byte cordI, byte cordJ) {

		byte maxHor = max(absolute((byte) (cordI - (border[0]))), absolute((byte) (cordI - (border[1]))));
		byte maxVer = max(absolute((byte) (cordJ - (border[2]))), absolute((byte) (cordJ - (border[3]))));

		return max(maxHor, maxVer);
	}

	private byte absolute(byte i) {
		if (i > 0) {
			return i;
		} else {
			return (byte) (i * (-1));
		}
	}

	private byte max(byte a, byte b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	// wahrscheinlich ziemlich optimal
	// 0.3 s für Test11
	private byte[] updateBorder(byte[] border, byte pos1, byte pos2) {

		byte[] borderUpdate = new byte[4];

		if (pos1 < border[0]) {
			borderUpdate[0] = pos1;
		} else {
			borderUpdate[0] = border[0];
		}
		if (pos1 > border[1]) {
			borderUpdate[1] = pos1;
		} else {
			borderUpdate[1] = border[1];
		}
		if (pos2 < border[2]) {
			borderUpdate[2] = pos2;
		} else {
			borderUpdate[2] = border[2];
		}
		if (pos2 > border[3]) {
			borderUpdate[3] = pos2;
		} else {
			borderUpdate[3] = border[3];
		}

		return borderUpdate;
	}

	// für oben, unten, rechts links wird nur höchstens eine well geaddet
	// ca. 0.3 s für Test11
	private LinkedList<OilWell> possibleOilWellsForNextSetUp(byte[] border) {
		LinkedList<OilWell> wellsNotInSol = new LinkedList<OilWell>();

		byte upperBorder = -1;
		byte lowerBorder = r;
		byte leftBorder = -1;
		byte rightBorder = (byte) c;
		boolean end = false;
		// look above the current border
		for (byte i = (byte) (border[0] - 1); i >= 0 && !end; i--) {
			for (byte j = border[2]; j <= border[3] && !end; j++) {
				if (grid[i][j]) {
					OilWell well = new OilWell(i, j, (byte) 0);
					end = true;
					upperBorder = i;
					wellsNotInSol.add(well);
				}
			}
		}
		end = false;
		// look below the current border
		for (byte i = (byte) (border[1] + 1); i < r && !end; i++) {
			for (byte j = border[2]; j <= border[3] && !end; j++) {
				if (grid[i][j]) {
					OilWell well = new OilWell(i, j, (byte) 1);
					end = true;
					lowerBorder = i;
					wellsNotInSol.add(well);

				}
			}
		}

		end = false;
		// look left the current border
		for (byte j = (byte) (border[2] - 1); j >= 0 && !end; j--) {
			for (byte i = border[0]; i <= border[1] && !end; i++) {
				if (grid[i][j]) {
					OilWell well = new OilWell(i, j, (byte) 2);
					end = true;
					leftBorder = j;
					wellsNotInSol.add(well);

				}
			}
		}

		end = false;
		// look right the current border
		for (byte j = (byte) (border[3] + 1); j < c && !end; j++) {
			for (byte i = border[0]; i <= border[1] && !end; i++) {
				if (grid[i][j]) {
					OilWell well = new OilWell(i, j, (byte) 3);
					end = true;
					rightBorder = j;
					wellsNotInSol.add(well);

				}
			}
		}
		searchDiagonal2(border, wellsNotInSol, upperBorder, lowerBorder, leftBorder, rightBorder);
		return wellsNotInSol;
	}

	// 0.06 für Test11
	public void searchDiagonal2(byte[] border, LinkedList<OilWell> wellsNotInSol, byte upperBorder, byte lowerBorder,
			byte leftBorder, byte rightBorder) {
		byte startPos1 = (byte) (border[0] - 1);
		byte startPos2 = (byte) (border[2] - 1);

		// upper left corner
		byte upperBorderCopy = upperBorder;
		byte leftBorderCopy = leftBorder;
		while (startPos1 > upperBorderCopy && startPos2 > leftBorderCopy) {
			boolean wellFound = false;
			for (byte i = startPos1; i > upperBorderCopy && !wellFound; i--) {
				if (grid[i][startPos2]) {
					OilWell well = new OilWell(i, startPos2, (byte) 4);
					wellsNotInSol.add(well);
					wellFound = true;

					upperBorderCopy = (byte) (i + 1);

				}
			}

			wellFound = false;
			for (byte j = startPos2; j > leftBorderCopy && !wellFound; j--) {

				if (grid[startPos1][j]) {
					OilWell well = new OilWell(startPos1, j, (byte) 4);
					wellsNotInSol.add(well);
					wellFound = true;
					leftBorderCopy = (byte) (j + 1);

				}

			}
			startPos1--;
			startPos2--;
		}

		// lower left corner
		startPos1 = (byte) (border[1] + 1);
		startPos2 = (byte) (border[2] - 1);
		byte lowerBorderCopy = lowerBorder;
		leftBorderCopy = leftBorder;

		while (startPos1 < lowerBorderCopy && startPos2 > leftBorderCopy) {
			boolean wellFound = false;
			for (byte i = startPos1; i < lowerBorderCopy && !wellFound; i++) {
				if (grid[i][startPos2]) {
					OilWell well = new OilWell(i, startPos2, (byte) 5);
					wellsNotInSol.add(well);
					wellFound = true;
					lowerBorderCopy = (byte) (i - 1);
				}
			}
			wellFound = false;
			for (byte j = startPos2; j > leftBorderCopy && !wellFound; j--) {
				if (grid[startPos1][j]) {
					OilWell well = new OilWell(startPos1, j, (byte) 5);
					wellsNotInSol.add(well);
					wellFound = true;
					leftBorderCopy = (byte) (j + 1);
				}
			}
			startPos1++;
			startPos2--;
		}
		//
		// // upper right corner
		startPos1 = (byte) (border[0] - 1);
		startPos2 = (byte) (border[3] + 1);
		upperBorderCopy = upperBorder;
		byte rightBorderCopy = rightBorder;
		while (startPos1 > upperBorderCopy && startPos2 < rightBorderCopy) {
			boolean wellFound = false;
			for (byte i = startPos1; i > upperBorderCopy && !wellFound; i--) {
				if (grid[i][startPos2]) {
					OilWell well = new OilWell(i, startPos2, (byte) 6);
					wellsNotInSol.add(well);
					wellFound = true;
					upperBorderCopy = (byte) (i + 1);
				}
			}
			wellFound = false;
			for (byte j = startPos2; j < rightBorderCopy && !wellFound; j++) {
				if (grid[startPos1][j]) {
					OilWell well = new OilWell(startPos1, j, (byte) 6);
					wellsNotInSol.add(well);
					wellFound = true;
					rightBorderCopy = (byte) (j - 1);
				}
			}
			startPos1--;
			startPos2++;
		}
		//
		// // lower right corner
		startPos1 = (byte) (border[1] + 1);
		startPos2 = (byte) (border[3] + 1);
		lowerBorderCopy = lowerBorder;
		rightBorderCopy = rightBorder;
		while (startPos1 < lowerBorderCopy && startPos2 < rightBorder) {
			boolean wellFound = false;
			for (byte i = startPos1; i < lowerBorderCopy && !wellFound; i++) {
				if (grid[i][startPos2]) {
					OilWell well = new OilWell(i, startPos2, (byte) 7);
					wellsNotInSol.add(well);
					wellFound = true;
					lowerBorderCopy = (byte) (i - 1);
				}
			}
			wellFound = false;
			for (byte j = startPos2; j < rightBorderCopy && !wellFound; j++) {
				if (grid[startPos1][j]) {
					OilWell well = new OilWell(startPos1, j, (byte) 7);
					wellsNotInSol.add(well);
					wellFound = true;
					rightBorderCopy = (byte) (j - 1);
				}
			}
			startPos1++;
			startPos2++;
		}

	}

	private void readInput(String pathOfTestCaseFile) {

		File file = new File(pathOfTestCaseFile);
		Scanner sc;

		try {
			sc = new Scanner(file);

			r = sc.nextByte();
			c = sc.nextByte();
			memoryMinimumCostForSetUpAllOilWellsOutOfBorder = new int[r][r][c][c];

			grid = new boolean[r][c];

			for (int i = 0; i < r; i++) {
				for (byte j = 0; j < c; j++) {
					int a = sc.nextByte();
					if (a == 1) {
						grid[i][j] = true;
						numberOfOilWells++;
					}
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private class OilWell {

		byte verticalPos;
		byte horizontalPos;
		// 0 above, 1 below, 2 left, 3 right, 4 left above, 5 left below
		// 6 right above, 7 right below
		byte positionToCurrentBorder;

		public OilWell(byte verticalPos, byte horizontalPos, byte positionToCurrentBorder) {
			super();
			this.verticalPos = verticalPos;
			this.horizontalPos = horizontalPos;
			this.positionToCurrentBorder = positionToCurrentBorder;
		}

		public byte getVerticalPos() {
			return verticalPos;
		}

		public byte getHorizontalPos() {
			return horizontalPos;
		}

		public byte getPositionToCurrentBorder() {
			return positionToCurrentBorder;
		}

	}

	/**
	 * Border of a (part) of a Grid field
	 * 
	 * @author Christoph
	 * 
	 */
	private class Border {

		byte upperBorder;
		byte lowerBorder;
		byte leftBorder;
		byte rightBorder;

		public Border(byte upperBorder, byte lowerBorder, byte leftBorder, byte rightBorder) {
			super();
			this.upperBorder = upperBorder;
			this.lowerBorder = lowerBorder;
			this.leftBorder = leftBorder;
			this.rightBorder = rightBorder;
		}

		public byte getUpperBorder() {
			return upperBorder;
		}

		public byte getLowerBorder() {
			return lowerBorder;
		}

		public byte getLeftBorder() {
			return leftBorder;
		}

		public byte getRightBorder() {
			return rightBorder;
		}

	}
}
