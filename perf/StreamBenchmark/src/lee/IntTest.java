package lee;

import java.util.Arrays;
import java.util.Random;
/**
 * java -server -Xms10G -Xmx10G -XX:+PrintGCDetails 
 * -XX:+UseConcMarkSweepGC -XX:CompileThreshold=1000 lee/IntTest
 * taskset -c 0-[0,1,3,7] java ...
 * @author CarpenterLee
 */
public class IntTest {

	public static void main(String[] args) {
		new IntTest().doTest();
	}
	public void doTest(){
		warmUp();
		int[] lengths = {
				10000, 
				100000, 
				1000000, 
				10000000, 
				100000000, 
				1000000000
			};
		for(int length : lengths){
			System.out.println(String.format("---array length: %d---", length));
			int[] arr = new int[length];
			randomInt(arr);
			
			int times = 4;
			int min1 = 1;
			int min2 = 2;
			int min3 = 3;
			long startTime;
			
			startTime = System.nanoTime();
			for(int i=0; i<times; i++){
				min1 = minIntFor(arr);
			}
			TimeUtil.outTimeUs(startTime, "minIntFor time:", times);
			
			startTime = System.nanoTime();
			for(int i=0; i<times; i++){
				min2 = minIntStream(arr);
			}
			TimeUtil.outTimeUs(startTime, "minIntStream time:", times);
			
			startTime = System.nanoTime();
			for(int i=0; i<times; i++){
				min3 = minIntParallelStream(arr);
			}
			TimeUtil.outTimeUs(startTime, "minIntParallelStream time:", times);
			
			
			System.out.println(min1==min2 && min2==min3);
		}
	}
	private void warmUp(){
		int[] arr = new int[100];
		randomInt(arr);
		for(int i=0; i<20000; i++){
//			minIntFor(arr);
			minIntStream(arr);
			minIntParallelStream(arr);
			
		}
	}
	private int minIntFor(int[] arr){
		int min = Integer.MAX_VALUE;
		for(int i=0; i<arr.length; i++){
			if(arr[i]<min)
				min = arr[i];
		}
		return min;
	}
	private int minIntStream(int[] arr){
		return Arrays.stream(arr).min().getAsInt();
	}
	private int minIntParallelStream(int[] arr){
		return Arrays.stream(arr).parallel().min().getAsInt();
	}
	private void randomInt(int[] arr){
		Random r = new Random();
		for(int i=0; i<arr.length; i++){
			arr[i] = r.nextInt();
		}
	}
}
