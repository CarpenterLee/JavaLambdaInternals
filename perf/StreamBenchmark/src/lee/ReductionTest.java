package lee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * java -server -Xms10G -Xmx10G -XX:+PrintGCDetails 
 * -XX:+UseConcMarkSweepGC -XX:CompileThreshold=1000 lee/ReductionTest
 * taskset -c 0-[0,1,3,7] java ...
 * @author CarpenterLee
 */
public class ReductionTest {

	public static void main(String[] args) {
		new ReductionTest().doTest();
	}
	public void doTest(){
		warmUp();
		int[] lengths = {
				10000, 
				100000, 
				1000000, 
				10000000, 
				20000000, 
				40000000
			};
		for(int length : lengths){
			System.out.println(String.format("---orders length: %d---", length));
			List<Order> orders = Order.genOrders(length);
			int times = 4;
			Map<String, Double> map1 = null;
			Map<String, Double> map2 = null;
			Map<String, Double> map3 = null;
			
			long startTime;
			
			startTime = System.nanoTime();
			for(int i=0; i<times; i++){
				map1 = sumOrderForLoop(orders);
			}
			TimeUtil.outTimeUs(startTime, "sumOrderForLoop time:", times);
			
			startTime = System.nanoTime();
			for(int i=0; i<times; i++){
				map2 = sumOrderStream(orders);
			}
			TimeUtil.outTimeUs(startTime, "sumOrderStream time:", times);

			startTime = System.nanoTime();
			for(int i=0; i<times; i++){
				map3 = sumOrderParallelStream(orders);	
			}
			TimeUtil.outTimeUs(startTime, "sumOrderParallelStream time:", times);
			
			System.out.println("users=" + map3.size());
		
		}
	}
	private void warmUp(){
		List<Order> orders = Order.genOrders(10);
		for(int i=0; i<20000; i++){
			sumOrderForLoop(orders);
			sumOrderStream(orders);
			sumOrderParallelStream(orders);
			
		}
	}
	private Map<String, Double> sumOrderForLoop(List<Order> orders){
		Map<String, Double> map = new HashMap<>();
		for(Order od : orders){
			String userName = od.getUserName();
			Double v; 
			if((v=map.get(userName)) != null){
				map.put(userName, v+od.getPrice());
			}else{
				map.put(userName, od.getPrice());
			}
		}
		return map;
	}
	private Map<String, Double> sumOrderStream(List<Order> orders){
		return orders.stream().collect(
				Collectors.groupingBy(Order::getUserName, 
						Collectors.summingDouble(Order::getPrice)));
	}
	private Map<String, Double> sumOrderParallelStream(List<Order> orders){
		return orders.parallelStream().collect(
				Collectors.groupingBy(Order::getUserName, 
						Collectors.summingDouble(Order::getPrice)));
	}
}
class Order{
	private String userName;
	private double price;
	private long timestamp;
	public Order(String userName, double price, long timestamp) {
		this.userName = userName;
		this.price = price;
		this.timestamp = timestamp;
	}
	public String getUserName() {
		return userName;
	}
	public double getPrice() {
		return price;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public static List<Order> genOrders(int listLength){
		ArrayList<Order> list = new ArrayList<>(listLength);
		Random rand = new Random();
		int users = listLength/200;// 200 orders per user
		users = users==0 ? listLength : users;
		ArrayList<String> userNames = new ArrayList<>(users);
		for(int i=0; i<users; i++){
			userNames.add(UUID.randomUUID().toString());
		}
		for(int i=0; i<listLength; i++){
			double price = rand.nextInt(1000);
			String userName = userNames.get(rand.nextInt(users));
			list.add(new Order(userName, price, System.nanoTime()));
		}
		return list;
	}
	@Override
	public String toString(){
		return userName + "::" + price;
	}
}
