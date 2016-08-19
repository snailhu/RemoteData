package DataAn.Util;

public class MathUtil {
//	public static float m;
//	
//	public static float n;
	
	public static float getCommonDivisor(float m, float n){
		
		float k ,y;
		if(m<n){			
			k=m;
			m=n;
			n=k;								
		}
		y=m%n;
		if(y==0){
			return n;
		}
		else{
			m=n;
			n=y;
			return getCommonDivisor(m,n);
		}					
	}
}
