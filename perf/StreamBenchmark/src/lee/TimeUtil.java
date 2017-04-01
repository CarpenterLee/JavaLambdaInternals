package lee;

public class TimeUtil {
	public static void outTimeMs(long startTime, String msg){
		long ms = System.currentTimeMillis()-startTime;
		System.out.println(msg + " " + ms + " ms");
		
	}
	public static void outTimeUs(long startTime, String msg){
		long us = (System.nanoTime()-startTime+500)/1000;
		System.out.println(msg + " " + us + " us");
	}
	public static void outTimeUs(long startTime, String msg, int times){
		long ns_all = System.nanoTime()-startTime;
		double us_avg = (ns_all+500.0)/1000/times;
		System.out.println(
				String.format("%s avg of %d = %.2f us", msg, times, us_avg));
	}
}
