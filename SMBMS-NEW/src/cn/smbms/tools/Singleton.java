package cn.smbms.tools;

public class Singleton {
	private static Singleton singleton;
	private Singleton() {
		//整个应用运行期间，只执行一次的业务代码操作（如读取配置文件的操作）
	}
	public static class SingletonHelper{
		private static final Singleton INSTANCE = new Singleton();
	}
	
	public static Singleton getInstance() {
		singleton = SingletonHelper.INSTANCE;
		return singleton;
	}
	public static Singleton test() {
		return singleton;
	}
}
