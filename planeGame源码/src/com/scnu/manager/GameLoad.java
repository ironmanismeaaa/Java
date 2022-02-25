package com.scnu.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.swing.ImageIcon;

import com.scnu.element.BOSS;
import com.scnu.element.ElementBase;
import com.scnu.element.Enemy;
import com.scnu.element.Player;
import com.scnu.element.Prop;
import com.scnu.manager.GameElement;
import com.scnu.manager.GameLoad;

/**
 * 说明 加载器（工具：用于读取配置文件的工具）工具类，大多提供的是static方法
 * 
 * @author asus
 *
 */
public class GameLoad {
	// 得到资源管理器
	private static ElementManager em = ElementManager.getManager();

	// 图片集合 使用map来进行存储 枚举类型配合移动（扩展）
	public static Map<String, ImageIcon> imgMap = new HashMap<>();
	public static Map<String, List<ImageIcon>> imgMaps;

	private static Properties pro = new Properties();

	/**
	 * @说明 传入地图id由加载方法依据文件规则自动产生地图文件名称，加载文件
	 * @param mapId
	 *            文件编号 文件id
	 */
	// public static void MapLoad(int mapId){
	// loadObj();
	// String bgStr="0,0,bg1";
	// ElementBase obj=getObj("bg");
	// ElementBase bg=obj.createElement(bgStr);
	// em.addElement(bg, GameElement.MAPS);
	// }
	/**
	 * @说明 加载图片代码 加载图片 图片和代码之间差一个路径问题
	 */
	public static void loadImg() {// 可以带参数，因为不同的关也可能需要不同的图片资源
		String texturl = "com/scnu/text/GameData.pro";// 文件的命名可以更加有规律
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
		// imgMap用于存放数据
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();// 是一个set集合
			for (Object o : set) {
				String url = pro.getProperty(o.toString());
				imgMap.put(o.toString(), new ImageIcon(url));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 加载玩家
	 */
	public static void loadPlay() {
		loadObj();
		String playStr = "360,900,10,0,0,images/play/11.PNG,Bullet_1";
		// ImageIcon icon2=GameLoad.imgMap.get(split[2]);this.setIcon(icon2);
		// String playStr="360,900,5,5,5,play2,file1";
		ElementBase obj = getObj("play");
		ElementBase play = obj.createElement(playStr);
		// ElementObj play = new Play().createElement(playStr);
		// 解耦，降低代码和代码之间的耦合度 可以直接通过接口或者是抽象父类就可以获取到实体对象
		em.addElement(play, GameElement.PLAY);
	}

	public static ElementBase getObj(String str) {
		try {
			Class<?> class1 = objMap.get(str);
			Object newInstance = class1.newInstance();
			if (newInstance instanceof ElementBase) {
				return (ElementBase) newInstance;
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加载敌人
	 */
	public static void loadEnemy() {
		String enemyName = "com/scnu/text/EnemyData.pro";
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream enemys = classLoader.getResourceAsStream(enemyName);
		if (enemys == null) {
			System.out.println("配置文件读取异常，请重新安装");
			return;
		}
		try {
			pro.clear();
			pro.load(enemys);
			Enumeration<?> names = pro.propertyNames();
			while (names.hasMoreElements()) {// 获取是无序的
				String key = names.nextElement().toString();
				String[] arrs = pro.getProperty(key).split(";");
				for (int i = 0; i < arrs.length; i++) {
					ElementBase element = new Enemy().createElement(arrs[i]);
					System.out.println(element.getImageIcon());
					em.addElement(element, GameElement.ENEMY);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @说明 加载图片代码 加载图片 图片和代码之间差一个路径问题
	 */

	/**
	 * 加载boss
	 */
	public static void loadBoss() {
		String bossName="com/scnu/text/BossData.pro";
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream boss=classLoader.getResourceAsStream(bossName);
		if(boss==null){
			System.out.println("配置文件读取异常，请重新安装");
			return;
		}
		try {
			pro.clear();
			pro.load(boss);
			Enumeration<?> names = pro.propertyNames();
			while(names.hasMoreElements()){//获取是无序的
				String key=names.nextElement().toString();
				String [] arrs=pro.getProperty(key).split(";");
				for(int i=0;i<arrs.length;i++){
					ElementBase element = new BOSS().createElement(arrs[i]);
					System.out.println(element.getImageIcon());
					em.addElement(element, GameElement.BOSS);
				}	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 加载道具
	 */
	public static void loadProp() {
		String propName = "com/scnu/text/PropData.pro";
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream props = classLoader.getResourceAsStream(propName);
		if (props == null) {
			System.out.println("配置文件读取异常，请重新安装");
			return;
		}
		try {
			pro.clear();
			pro.load(props);
			Enumeration<?> names = pro.propertyNames();
			while (names.hasMoreElements()) {// 获取是无序的
				String key = names.nextElement().toString();
				String[] arrs = pro.getProperty(key).split(";");
				for (int i = 0; i < arrs.length; i++) {
					ElementBase propE = new Prop().createElement(arrs[i]);
					em.addElement(propE, GameElement.PROP);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 扩展：使用配置文件，来实例化对象 通过固定的key（字符串来实例化）
	 * 
	 * @param args
	 */
	private static Map<String, Class<?>> objMap = new HashMap<>();

	public static void loadObj() {
		String texturl = "com/scnu/text/obj.pro";// 文件的命名可以更加有规律
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();// 是一个set集合
			for (Object o : set) {
				String classUrl = pro.getProperty(o.toString());
				// 使用反射的方式直接将类进行获取
				Class<?> forName = Class.forName(classUrl);
				objMap.put(o.toString(), forName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
