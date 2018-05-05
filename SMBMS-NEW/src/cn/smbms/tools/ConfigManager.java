package cn.smbms.tools;

import java.io.InputStream;
import java.util.Properties;

//读取配置文件的工具类——单例模式
public class ConfigManager {
	private static ConfigManager configManager;
	private static Properties properties;

	// 私有构造器——读取数据库配置文件
	private ConfigManager() {
		String configFile = "database.properties";
		properties = new Properties();
		InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class ConfigManagerHelper {
		private final static ConfigManager CONFIGMANAGER = new ConfigManager();
	}

	// 全局访问点 延迟加载模式（懒汉模式）
	public static ConfigManager getInstance() {
		configManager = ConfigManagerHelper.CONFIGMANAGER;
		return configManager;
	}

	// 饿汉模式
	// public static ConfigManager getInstance() {
	// return configManager;
	// }

	public static String getValue(String key) {
		return properties.getProperty(key);
	}
}
