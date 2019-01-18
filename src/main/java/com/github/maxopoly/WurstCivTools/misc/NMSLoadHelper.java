package com.github.maxopoly.WurstCivTools.misc;

import java.util.logging.Level;

import org.bukkit.Server;

public class NMSLoadHelper {

	private static NMSLoadHelper instance;
	private static Object nmsObject;
	private static String version;

	public static boolean init(Server server, String className) {
		instance = new NMSLoadHelper();
		String packageName = server.getClass().getPackage().getName();
		version = packageName.substring(packageName.lastIndexOf('.') + 1);
		try {
			nmsObject = Class.forName(String.format(className, packageName)).getConstructors()[0].newInstance();
			return true;
		} catch (Exception e) {
			server.getLogger().log(Level.SEVERE, "Failed to instanciate NMS", e);
			return false;
		}
	}

	public static Object getObject(Class<? extends Object> Class, String name) {
		return nmsObject;
	}
}
