package com.irisflowers.math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.irisflowers.domain.EvoGroup;
import com.irisflowers.domain.EvoPath;

public class Convergence {


	//判断一个数据区间内点数是否成上升趋势
	//(1)区间内的最后一个点必须大于第一个点；
	//(2)中间点的波动幅度你得设置一个限制；
	
	public static void judge(List<double[]> numbergroup, EvoGroup egroup){			
		int length = numbergroup.size();
		double[] sds = new double[length];
		Map<Double, EvoPath> map = new HashMap<Double, EvoPath>();
		for(int i=0; i<length; i++){
			sds[i] = SD.getSD(numbergroup.get(i));
			map.put(sds[i], egroup.getList().get(i));
			//System.out.println(sds[i]);
		}
		Arrays.sort(sds);					//所有path的标准差从下到大排序
//		for(int i=0; i<numbergroup.size(); i++){
//			System.out.print(sds[i]+" ");
//		}
//		System.out.println();
		int threshold1 = (int) sds.length/3;
		int threshold2 = (int) 2*sds.length/3;
		
		//开始判断敛散性
		for(int i=0; i<length; i++){
			EvoPath epath = map.get(sds[i]);
			double[] weights = epath.getWeightfull3();
			//1、发散 3、大致不变	4、无法判断
			if(weights[weights.length-1] > weights[(int)weights.length/2]
			       && weights[(int)weights.length/2] > weights[0]
			            && i < threshold2) {
				epath.setConvergence(1);
				System.out.println(1);
			}
			//2、收敛
			else if(weights[weights.length-1] < weights[(int)weights.length/2]
			       && weights[(int)weights.length/2] < weights[0]
			            && i < threshold2) {
				epath.setConvergence(2);
				System.out.println(2);
			}
			//3、基本不变
			else if(i < threshold1) {
				epath.setConvergence(3);
				System.out.println(3);
			}
			//4、无法判断
			else {
				epath.setConvergence(4);
				System.out.println(4);
			}
			
		}
	}
	
	//Test
	public static void main(String[] args) {

	}

}
