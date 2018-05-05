package cn.smbms.tools;

import java.util.HashMap;
import java.util.Map;


public class TestSingleton {
	public static void main(String[] args) {
//		System.out.println("Singleton.getInstance()---->"+Singleton.getInstance());
//		System.out.println("Singleton.test()---->" + Singleton.test());
		int[] arry = {1,2,1,3,2};
		Map<Integer,String> map = new HashMap<Integer,String>();
		for(int i = 0;i<arry.length;i++) {
			if(map.containsKey(arry[i])) {
				map.put(new Integer(arry[i]), map.get(arry[i])+","+i);
			}else {
				map.put(new Integer(arry[i]), i+"");
			}
		}
		for(int i:map.keySet()) {
			System.out.println("值：" + i + ",位于：" + map.get(i));
		}
	}
}
