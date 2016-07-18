package com.irisflowers.interpolation;

import java.text.DecimalFormat;
import java.util.Scanner;

public class LagrangeNewtonThreespline {
	
	static int flag = 0;
	
	public static double lagrange(double x0[],double y0[],int n, double x) {
		int i,j;
		double p[] = new double[100];
		double y = 0;
		for(i=0;i<n;i++){
			p[i] = y0[i];
			for(j=0;j<n;j++) {
				if(i!=j) p[i] *= (x-x0[j])/(x0[i]-x0[j]);
			}
			y += p[i];
		}
		return y;
	}
	
	public static double newton(double x0[],double y0[],int n, double x) {
		int i,j;
		double p[] = new double[100];
		
		for(i=1;i<n;i++){
			p[0] = y0[i];
			for(j=0;j<n;j++) {
				p[j+1] = (p[j] - y0[j])/(x0[i] - x0[j]);			
				y0[i] = p[i];									//存放每一项的系数a
			}
		}
		double y = y0[n-1];
    	for(i=n-2;i>=0;i--){
    		y = y*(x - x0[i]) + y0[i];
    	}
		return y;
	}
	
	/**
	 * ThreeSpine Begin!
	 */
	  static int n, last_interval;
	  static double x[], f[], b[], c[], d[];
	  static boolean uniform, debug;
	  
	  public static double ThreeSpline(double xx[], double ff[], int n, double arg)
	  {
	    // Calculate coefficients defining a smooth cubic interpolatory spline.
	    //
	    // Input parameters:
	    //   xx  = vector of values of the independent variable ordered
	    //         so that  x[i] < x[i+1]  for all i.
	    //   ff  = vector of values of the dependent variable.
	    // class values constructed:
	    //   n   = number of data points.
	    //   b   = vector of S'(x[i]) values.
	    //   c   = vector of S"(x[i])/2 values.
	    //   d   = vector of S'''(x[i]+)/6 values (i < n).
	    //   x = xx
	    //   f = ff
	    // Local variables:   
	    double fp1, fpn, h, p;
	    final double zero = 0.0, two = 2.0, three = 3.0;
	    DecimalFormat f1 = new DecimalFormat("00.00000");
	    
	    uniform = true;
	    debug = false;
	    last_interval = 0;
	    
	    x = new double[n];
	    f = new double[n];
	    b = new double[n];
	    c = new double[n];
	    d = new double[n];
	    for(int i=0; i<n; i++)
	    {
	      x[i] = xx[i];
	      f[i] = ff[i];
	      if(debug) System.out.println("Spline data x["+i+"]="+x[i]+", f[]="+f[i]);
	    }
	    
	    // Calculate coefficients for the tri-diagonal system: store
	    // sub-diagonal in b, diagonal in d, difference quotient in c. 
	    b[0] = x[1]-x[0];
	    c[0] = (f[1]-f[0])/b[0];
	    if(n==2)
	    {
	       b[0] = c[0];
	       c[0] = zero;
	       d[0] = zero;
	       b[1] = b[0];
	       c[1] = zero;
	       return -1;
	    }
	    d[0] = two*b[0];
	    for(int i=1; i<n-1; i++)
	    {
	       b[i] = x[i+1]-x[i];
	       if(Math.abs(b[i]-b[0])/b[0]>1.0E-5) uniform = false;
	       c[i] = (f[i+1]-f[i])/b[i];
	       d[i] = two*(b[i]+b[i-1]);
	    }
	    d[n-1] = two*b[n-2];
	
	    // Calculate estimates for the end slopes.  Use polynomials
	    // interpolating data nearest the end. 
	    fp1 = c[0]-b[0]*(c[1]-c[0])/(b[0]+b[1]);
	    if(n>3) fp1 = fp1+b[0]*((b[0]+b[1])*(c[2]-c[1])/
	                  (b[1]+b[2])-c[1]+c[0])/(x[3]-x[0]);
	    fpn = c[n-2]+b[n-2]*(c[n-2]-c[n-3])/(b[n-3]+b[n-2]);
	    if(n>3) fpn = fpn+b[n-2]*(c[n-2]-c[n-3]-(b[n-3]+
	                  b[n-2])*(c[n-3]-c[n-4])/(b[n-3]+b[n-4]))/(x[n-1]-x[n-4]);
	
	    // Calculate the right-hand-side and store it in c. 
	    c[n-1] = three*(fpn-c[n-2]);
	    for(int i=n-2; i>0; i--)
	       c[i] = three*(c[i]-c[i-1]);
	    c[0] = three*(c[0]-fp1);
	
	    // Solve the tridiagonal system. 
	    for(int k=1; k<n; k++)
	    {
	       p = b[k-1]/d[k-1];
	       d[k] = d[k]-p*b[k-1];
	       c[k] = c[k]-p*c[k-1];
	    }
	    c[n-1] = c[n-1]/d[n-1];
	    for(int k=n-2; k>=0; k--)
	       c[k] = (c[k]-b[k]*c[k+1])/d[k];
	
	    // Calculate the coefficients defining the spline. 
	    h = x[1]-x[0];
	    for(int i=0; i<n-1; i++)
	    {
	       h = x[i+1]-x[i];
	       d[i] = (c[i+1]-c[i])/(three*h);
	       b[i] = (f[i+1]-f[i])/h-h*(c[i]+h*d[i]);
	    }
	    b[n-1] = b[n-2]+h*(two*c[n-2]+h*three*d[n-2]);
	    if(debug) System.out.println("spline coefficients");
	    for(int i=0; i<n; i++)
	    {
	      if(debug) System.out.println("i="+i+", b[i]="+f1.format(b[i])+", c[i]="+
	                         f1.format(c[i])+", d[i]="+f1.format(d[i]));
	    }
	    
	    // Evaluate the spline s at t using coefficients from Spline constructor
	    //
	    // Input parameters
	    //   class variables
	    //   arg = point where spline is to be evaluated.
	    // Output:
	    //   result = value of spline at arg.
	    // Local variables:    
	    double dt, result;
	    int interval; // index such that t>=x[interval] and t<x[interval+1]
	
	    // Search for correct interval for t. 
	    interval = last_interval; // heuristic
	    if(arg<x[0])
	    {
	      System.out.println("requested point below Spline region");
	      return 0.0; // should throw exception
	    }
	    if(arg>x[n-1])
	    {
	      System.out.println("requested point above Spline region");
	      return 0.0; // should throw exception
	    }
	    if(arg>x[n-2])
	       interval = n-2;
	    else if (arg >= x[last_interval])
	       for(int j=last_interval; j<n&&arg>=x[j]; j++) interval = j;
	    else
	       for(int j=last_interval; arg<x[j]; j--) interval = j-1;
	    last_interval = interval; // class variable for next call 
	    if(debug) System.out.println("interval="+interval+", x[interval]="+
	                                 x[interval]+", arg="+arg);
	    // Evaluate cubic polynomial on [x[interval] , x[interval+1]]. 
	    dt = arg-x[interval];
	    result = f[interval]+dt*(b[interval]+dt*(c[interval]+dt*d[interval]));
	    return result;
	  } // end spline_value 
	  
	  /**
	   * ThreeSpine End!
	   */

	public static void main(String args[]){
		boolean flag1 = true;
		while(flag1){
			Scanner as = new Scanner(System.in);
			System.out.println("-----------------------------------------------------");
			System.out.println("-------------------插值法求解近似值-------------------");
	        System.out.println("-------------------请选择插值方法---------------------");
	        System.out.println("--------------------1、拉格朗日-----------------------");
	        System.out.println("--------------------2、牛顿插值-----------------------");
	        System.out.println("--------------------3、三次样条插值-----------------------");
	        System.out.println("--------------------退出请按0-------------------------");
	        System.out.println("-----------------------------------------------------");
	        flag=as.nextInt();
	        if(flag == 0) {
	        	flag1=false;
	        	System.out.println("您已退出程序！欢迎下次使用。");
	        } else {
	        	int n = 1;
	        	double x = 0;
	        	System.out.println("请输入n:");
	        	n = as.nextInt();
	        	System.out.println("请输入对应数组x0:");
	        	//System.out.println(n);
	        	double x0[] = new double[n];
	        	double y0[] = new double[n];
	        	for(int i=0;i<n;i++){
	        		x0[i] = as.nextDouble();
	        	}
	        	System.out.println(x0);
	        	System.out.println("请输入对应数组y0:");
	        	for(int i=0;i<n;i++){
	        		y0[i] = as.nextDouble();
	        	}
	        	System.out.println(y0);
	        	System.out.println("请输入值x:");
	        	x = as.nextDouble();
	        	if(flag == 1) {
	        		System.out.println("多项式解为："+lagrange(x0,y0,n,x));
	        	}
	        	if(flag == 2) {
	        		System.out.println("多项式解为："+newton(x0,y0,n,x));
	        	}
	        	if(flag == 3) {
	        		System.out.println("多项式解为："+ThreeSpline(x0,y0,n,x));
	        	}
	        }
		}
	}

}
//5
//x0=0.4 0.55 0.65 0.8 0.9
//y0=0.41075 0.5781 0.69675 0.88811 1.02652
//x=0.596 x=0.895

//n=4
//x0={0.5616 0.5628 0.5640 0.5652}
//y0={0.8274 0.8265 0.8257 0.8249}
//x=0.5635