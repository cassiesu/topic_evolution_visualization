package com.irisflowers.math;

import java.text.DecimalFormat;

public class SD {

	private static DecimalFormat f1 = new DecimalFormat("0.000000");
	
	private static double getAverage(double[] numbers, int n){//returns average
		double total = 0;
		for(double num :numbers ){
			total += num;
		}
		return total/n;
	}//end get average

	public static double getSD(double[] numbers){		//returns the standard deviation
		int n = numbers.length;
		double total = 0;
		double average = getAverage(numbers, n);
		
		for(double num :numbers){
			total+=(num-average)*(num-average);
		}
		
		return Double.parseDouble(f1.format(Math.sqrt(total/n)));			// returns the square root of the average2
	
	}//end getSD
		
}

