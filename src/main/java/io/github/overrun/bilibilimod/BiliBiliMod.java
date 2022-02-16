package io.github.overrun.bilibilimod;

import com.alibaba.fastjson.JSON;

import io.github.overrun.bilibilimod.configs.CreateConfig;
import io.github.overrun.bilibilimod.configs.MyProperties;
import net.fabricmc.api.ModInitializer;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class BiliBiliMod extends CreateConfig implements ModInitializer {
	public static final String modid = "bilibilimod";
	public static final Logger message = LoggerFactory.getLogger(modid);
	public static final String version = "1.0.0";
	public static final String author = "overrun";
	public static final String HTTPS_WWW_BILIBILI_COM = "https://www.bilibili.com/";
	MyProperties myProperties = new MyProperties();

	public final Map<String, String> element = new HashMap<>();//将数据注入哈希map内
	@Override
	public void onInitialize() {
		message.info("BiliBili Mod Initializing...");
		message.info("BiliBili Mod Version: " + version);
		message.info("BiliBili Mod Author: " + author);
		message.info("BiliBili Website Url: " + HTTPS_WWW_BILIBILI_COM);
		createConfig();
//		try {
//			getElement(getProperties().getProperty("url"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		String url = "https://api.bilibili.com/x/web-interface/archive/stat?aid=" + getProperties().getProperty("aid");
		String referer = "https://www.bilibili.com/video/" + getProperties().getProperty("BVid");
		try {
			String content = getContent(url, referer);
			var contentMap = JSON.parseObject(content, Map.class);
			@SuppressWarnings("rawtypes") var dataMap = (Map) contentMap.get("data");

			element.put("播放", dataMap.get("view").toString());
			element.put("收藏", dataMap.get("favorite").toString());
			element.put("硬币", dataMap.get("coin").toString());
			element.put("点赞", dataMap.get("like").toString());
			element.put("分享", dataMap.get("share").toString());
			File path = new File(getPath(), "BVids");
			if (!path.exists()) {
				path.mkdirs();
			}
			File file = new File(path, getProperties().getProperty("BVid") + ".save");
			try {
				load(myProperties ,file.getAbsolutePath());
			} catch (FileNotFoundException f) {
				myProperties.put("播放", element.get("播放"));
				myProperties.put("点赞", element.get("点赞"));
				myProperties.put("收藏", element.get("收藏"));
				myProperties.put("分享", element.get("分享"));
				myProperties.put("硬币", element.get("硬币"));
				try {
					myProperties.store(new BufferedOutputStream(new FileOutputStream(file)), "BiliBili Mod Config");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			message.info("播放量 " + element.get("播放") + " 点赞 " + element.get("点赞") + " 收藏 " + element.get("收藏") + " 分享 " + element.get("分享") + " 硬币 " + element.get("硬币"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		message.info("BiliBili Mod Initialized!");
	}

	public String getContent(String url, String referer) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(url)
				.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36")
				.addHeader("Referer", referer)
				.build();
		String result = null;
		try {
			Call call = client.newCall(request);
			Response response = call.execute();
			int code = response.code();
			System.out.println("code: " + code);
			result = Objects.requireNonNull(response.body()).string();
		} catch (IOException e) {
			System.out.println("request" + url + " failed");
			e.printStackTrace();
		}
		return result;
	}

//	public void getElement(String url) throws IOException {
//		message.info("url is..." + url);
//		Document doc = Jsoup.connect(url).get();
//		Elements links = doc.select("a[href]");
//		Elements media = doc.select("[src]");
//		Elements imports = doc.select("link[href]");
//		Elements coin = doc.select("coin");
//		message.info("Media: ", media.size());
//		for (Element src : media) {
//			if (src.tagName().equals("img")) {
//				message.info("src: " + src.tagName() + " " + src.attr("abs:src") + " " + src.attr("width") + " " + src.attr("height") + " " + src.attr("alt"));
//			} else {
//				message.info("src: " + src.tagName() + " " + src.attr("abs:src"));
//			}
//		}
//		message.info("Imports: ", imports.size());
//		for (Element link : imports) {
//			message.info("link: " + link.tagName() + " " + link.attr("abs:href") + " " + link.attr("rel"));
//		}
//		message.info("Links: ", links.size());
//		for (Element link : links) {
//			message.info("link: " + link.tagName() + " " + link.attr("abs:href") + " " + link.text());
//		}
//	}

}
