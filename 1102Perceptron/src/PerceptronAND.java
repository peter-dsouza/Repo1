//Author: 1102
//Date: 12 August 2013

import java.util.ArrayList;


public class PerceptronAND {

	static int[][] Gate = new int[][]{
			  { 0, 0, 0 },
			  { 0, 1, 0 },
			  { 1, 0, 0 },
			  { 1, 1, 1 }
			};
	
	public static void main(String[] args) {
		ArrayList<Integer> input = new ArrayList<Integer>();
		input.add(0); input.add(0); input.add(1);
		ArrayList<Integer> weight = new ArrayList<Integer>();
		weight.add(0); weight.add(0); weight.add(0);
		
		int sum;
		int pass;
		int i=0;
		int flag=0;
		
		while((i!=0)||(flag==0)){
			if(i==0){
				flag=1;
				input.set(0,0); input.set(1,0); input.set(2,1);
			}
			if(i==1){
				input.set(0,0); input.set(1,1); input.set(2,1);
			}
			if(i==2){
				input.set(0,1); input.set(1,0); input.set(2,1);
			}
			if(i==3){
				input.set(0,1); input.set(1,1); input.set(2,1);
			}
			
			System.out.println("Input: "+input.toString()+" Weights: "+weight.toString());
			
			sum=sum(input,weight);			//Summation Processor
			pass=checkThreshold(sum);		//Threshold Processor (Part of Summation Processor)
			
			System.out.println("\tActual Output:"+pass+" Expected Output:"+Gate[i][2]);
			
			if(pass>Gate[i][2]){			//Weight Modification
				subWeights(input,weight);
				if(flag>0)flag=0;
			}else if(pass<Gate[i][2]){
				addWeights(input,weight);
				if(flag>0)flag=0;
			}

			i=(i+1)%4;
			
		}
		
		}

	private static void subWeights(ArrayList<Integer> input, ArrayList<Integer> weight) {
		for(int i=2; i>=0; i--){
			weight.set(i, weight.get(i)-input.get(i));			
		}
	}

	private static void addWeights(ArrayList<Integer> input, ArrayList<Integer> weight) {
		for(int i=2; i>=0; i--){
			weight.set(i, weight.get(i)+input.get(i));			
		}
		//return weight;
	}

	private static int checkThreshold(int input) {
		if(input>0)return 1;
		return 0;
	}

	private static int sum(ArrayList<Integer> input, ArrayList<Integer> weight) {
		return input.get(0)*weight.get(0)+input.get(1)*weight.get(1)+input.get(2)*weight.get(2);
	}

}
