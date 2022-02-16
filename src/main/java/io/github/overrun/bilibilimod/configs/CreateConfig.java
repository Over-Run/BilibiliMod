package io.github.overrun.bilibilimod.configs;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class CreateConfig {
	public final MyProperties properties = new MyProperties();
	public final File
			path = new File("config" + File.separatorChar + "bilibilimod"),
			file = new File(path, "config.properties");
	public void createConfig() {
		if (!path.exists()) path.mkdirs();
			try {
				load(properties ,file.getAbsolutePath());
			} catch (FileNotFoundException f) {
				properties.put("BVid", "BV1zQ4y1N79L");
				properties.put("aid", "710598316");
				try {
					properties.store(new BufferedOutputStream(new FileOutputStream(file)), "BiliBili Mod Config");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public MyProperties getProperties() {
		return properties;
	}
	public void load(MyProperties myProperties,String a) throws IOException { myProperties.load(new BufferedInputStream(new FileInputStream(a))); }

	public File getPath() {
		return path;
	}

	public File getFile() {
		return file;
	}
}
