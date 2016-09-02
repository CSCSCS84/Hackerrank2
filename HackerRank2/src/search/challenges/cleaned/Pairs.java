/**
 * https://www.hackerrank.com/challenges/pairs/copy-from/20502119
 * @author Christoph
 * Challenge Description: Given integers, count the number of pairs of integers whose difference is K. 
 * 
 */
package search.challenges.cleaned;

import java.util.Arrays;
import java.util.Scanner;

public class Pairs {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//Read input
		int n = sc.nextInt();
		int K = sc.nextInt();
		int numbers[] = new int[n];
		
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = sc.nextInt();
		}

		int sol = solve(numbers, K);
		System.out.println(sol);
		sc.close();

	}

	private static int solve(int numbers[], int K) {
		
		Arrays.sort(numbers);
		int solution = 0;
		int i = 0;
		int j = 1;
		while (j < numbers.length) {
			
			int difference = numbers[j] - numbers[i];

			if (difference == K) {
				solution++;
				j++;
				i++;
			}
			if (difference > K) {
				i++;
			}
			if (difference < K) {
				j++;
			}

		}
		return solution;
	}
}
