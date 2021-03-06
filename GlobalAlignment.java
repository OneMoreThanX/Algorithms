import java.util.*;
import java.math.*;

public class GlobalAlignment {
	
	public int m;
	public int n;
	//the score for gap, matching and mismatching
	public int gap = -1;
	public int mi = -2;
	public int ma = 1;
	
	public GlobalAlignment(char[] s, char[] t){
		this.m = s.length+1;
		this.n = t.length+1;
	}
	
	public void score(char[] s, char[] t){
		//Initialization
		int[][] DP = new int[m][n];
		if(m>1 && n>1){
			for(int i=0;i<m;i++){
				DP[i][0] = i*gap;
			}
			for(int j=0;j<n;j++){
				DP[0][j] = j*gap;
			}
		}
		else{
			System.out.println("Empty input strings");
			return;
		}
		
		//Recursive calculation
		int match;
		for(int i=1;i<m;i++){
			for(int j=1;j<n;j++){
				if(s[i-1]==t[j-1]){
					match = ma;
				}
				else
					match = mi;
				int max = Math.max(DP[i-1][j-1]+match, Math.max(DP[i-1][j]+gap, DP[i][j-1]+gap));
				DP[i][j] = max;
			}
		}
		System.out.println("Final score is "+DP[m-1][n-1]);
		
		//Trace back to find the alignment
		ArrayList<Character> sa = new ArrayList<Character>();
		ArrayList<Character> ta = new ArrayList<Character>();
		int i = m-1;
		int j = n-1;
		int mark1 = 0;
		int mark2 = 0;
		while(i>0 && j>0){
			if(DP[i][j] == DP[i][j-1] + gap){
				sa.add('-');
				ta.add(t[j-1]);
				j = j-1;
				mark2 = mark2 + 1;
			}
			else if(DP[i][j] == DP[i-1][j] + gap){
				sa.add(s[i-1]);
				ta.add('-');
				i = i-1;
				mark1 = mark1 + 1;
			}
			else if((DP[i][j] == DP[i-1][j-1] + ma) || (DP[i][j] == DP[i-1][j-1] + mi)){
				sa.add(s[i-1]);
				ta.add(t[j-1]);
				i = i-1;
				j = j-1;
				mark1 = mark1 + 1;
				mark2 = mark2 + 1;
			}
			else{
				System.out.println("ERROR: trace back error!");
				break;
			}
		}
		while(i > 0){
			int index = s.length - mark1 - 1;
			sa.add(s[index]);
			ta.add('-');
			i = i-1;
		}
		while(j > 0){
			int index = t.length - mark2 - 1;
			sa.add('-');
			ta.add(t[index]);
			j = j-1;
		}
		Collections.reverse(sa);
		Collections.reverse(ta);
		System.out.println(sa);
		System.out.println(ta);
	}
	
	public static void main(String args[]){
		char[] s = {'t','c','a','c','g','t','a'};
		char[] t = {'a','g','t','c','a','c','g','a'};
		GlobalAlignment test = new GlobalAlignment(s,t);
		System.out.println("Table is "+test.m+" * "+test.n);
		test.score(s, t);
	}
	
	
}
