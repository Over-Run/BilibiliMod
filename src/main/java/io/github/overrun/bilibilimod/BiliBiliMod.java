package io.github.overrun.bilibilimod;

import com.alibaba.fastjson.JSON;

import io.github.overrun.bilibilimod.configs.CreateConfig;
import net.fabricmc.api.ModInitializer;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BiliBiliMod extends CreateConfig implements ModInitializer {
	public static final String modid = "bilibilimod";
	public static final Logger message = LoggerFactory.getLogger(modid);
	public static final String version = "1.0.0";
	@Override
	public void onInitialize() {
		message.info("BiliBili Mod Initializing...");
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
			Map contentMap = JSON.parseObject(content, Map.class);
			Map dataMap = (Map) contentMap.get("data");

			String like = dataMap.get("like").toString();
			String coin = dataMap.get("coin").toString();
			String view = dataMap.get("view").toString();
			String share = dataMap.get("share").toString();
			String favorite = dataMap.get("favorite").toString();
			message.info("播放量 " + view + " 点赞 " + like + " 收藏 " + favorite + " 分享 " + share + " 硬币 " + coin);

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
			result = response.body().string();
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
