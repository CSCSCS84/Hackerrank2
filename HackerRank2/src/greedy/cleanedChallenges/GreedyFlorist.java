/**
 * https://www.hackerrank.com/challenges/greedy-florist/copy-from/20740667
 */

package greedy.cleanedChallenges;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GreedyFlorist {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int K=sc.nextInt();
		Integer cost[]=new Integer[N];
		
		for(int i=0;i<N;i++){
			cost[i]=sc.nextInt();
		}
		sc.close();
		
		int solution = solve(N, K, cost);
		System.out.println(solution);
		
	}

	private static int solve(int N, int K, Integer[] cost) {
		Arrays.sort(cost, Collections.reverseOrder());
       
        int solution=0;
        int numOfPurchased=0;
        int numOfCustomers=1;
        
        while(numOfPurchased<N){
            int a=numOfPurchased%N;
            solution+=cost[a]*numOfCustomers;
            numOfPurchased++;
            if(numOfPurchased%K==0){
                numOfCustomers++;
            }
        }
		return solution;
	}
}