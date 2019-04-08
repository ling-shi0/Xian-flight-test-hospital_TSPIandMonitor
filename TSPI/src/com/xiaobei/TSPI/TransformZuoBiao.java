package com.xiaobei.TSPI;

import java.util.HashMap;
import java.util.Map;

public class TransformZuoBiao {

	public TransformZuoBiao() {
		
	}
	//xyz转blh（应该用不着）
	public Map<String,String> tranform(double x,double y,double z) {
		double e=0.00669437999014;
		double a=6378137.000;
		double b=Math.sqrt(a*a-e*e);
		Map<String,String> map=new HashMap<String,String>();
		double L=Math.atan(y/x);
		double e2=(a*a-b*b)/(b*b);
		double c=Math.atan((z*a)/(Math.sqrt(x*x+y*y)*b));
		double B=Math.atan((z+e*e*Math.sin(c)*Math.sin(c)*Math.sin(c))/(Math.sqrt(x*x+y*y)-e2*a*Math.cos(c)*Math.cos(c)*Math.cos(c)));
		double N=a/Math.sqrt(1-e*Math.sin(B)*Math.sin(B));
		double H=(Math.sqrt(x*x+y*y)/Math.cos(B))-N;
		map.put("B", String.valueOf(B));
		map.put("L", String.valueOf(L));
		map.put("H", String.valueOf(H));
		return map;
	}
	//blh转xyz blh转aer
	public Map<String,String> transformbTx(double b,double l,double h,double b0,double l0,double h0){
		Map<String,String> map=new HashMap<String,String>();
		double e=0.00669437999014;
		double a=6378137.000;
		
		double p0=180/Math.PI;
		b0=b0/p0;
		l=l/p0;
		l0=l0/p0;
		b=b/p0;
		double n=a/(Math.sqrt(1-e*Math.sin(b)*Math.sin(b)));
		double n0=a/(Math.sqrt(1-e*Math.sin(b0)*Math.sin(b0)));
		double L=l-l0;
		double D=e*(n0*Math.sin(b0)-n*Math.sin(b));
		double x=(n+h)*Math.cos(b)*Math.sin(L);
		double y=(n+h)*(Math.sin(b)*Math.cos(b0)-Math.sin(b0)*Math.cos(b)*Math.cos(L)+D*Math.cos(b0));
		double z=(a+h)*(Math.sin(b)*Math.sin(b0)+Math.cos(b)*Math.cos(b0)*Math.cos(L))-a-h0;
		double DD=Math.sqrt(x*x+y*y+z*z);
		double deg=0;
		if(x>0)
		{
			deg=Math.atan2(x, y)*180/Math.PI;
		}else if(x<0) {
			deg=Math.atan2(x, y)*180/Math.PI+360;
		}else if(x==0&&y>0) {
			deg=360;
		}else if(x==0&&y<=0) {
			deg=180;
		}
		double ddd=Math.sqrt(x*x+y*y);
		double vvv=Math.atan2(z, ddd)*180/Math.PI;
		
			
		map.put("X", String.valueOf(x));
		map.put("Y", String.valueOf(y));
		map.put("Z", String.valueOf(z));
		map.put("A", String.valueOf(deg));
		map.put("E", String.valueOf(vvv));
		map.put("R", String.valueOf(DD));
		return map;
	}
	
	//xyz转aer坐标
	public Map<String,String> transformxTa(double x,double y,double z,double x0,double y0,double z0)
	{
		Map<String,String> map=new HashMap<String,String>();
		double r=Math.sqrt((x-x0)*(x-x0)+(y-y0)*(y-y0)+(z-z0)*(z-z0));
		double e=Math.atan((z-z0)/Math.sqrt((x-x0)*(x-x0)+(y-y0)*(y-y0)));
		double a=Math.atan((x-x0)/(y-y0));
		map.put("R", String.valueOf(r));
		map.put("E", String.valueOf(e));
		map.put("A", String.valueOf(a));		
		return map;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TransformZuoBiao tr=new TransformZuoBiao();
		Map<String,String> map=new HashMap<String,String>();
		Map<String,String> map2=new HashMap<String,String>();
		map=tr.transformbTx(34.582328000, 109.132658, 753.131000000,34.6353506001,109.2260444661,5);
		System.out.println("X:"+map.get("X"));
		System.out.println("Y:"+map.get("Y"));
		System.out.println("Z:"+map.get("Z"));
		map2=tr.transformxTa(Double.parseDouble(map.get("X")), Double.parseDouble(map.get("Y")),Double.parseDouble(map.get("Z")), 0, 0, 0);
		System.out.println("A:"+map2.get("A"));
		System.out.println("E:"+map2.get("E"));
		System.out.println("R:"+map2.get("R"));
	}

}
