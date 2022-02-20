package io.github.overrun.bilibilimod.gen.base;

import java.io.*;

public class GetIp {
	public static String[] getIp() {
		String cmd = "curl -L ip.tool.lu";
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			InputStream fis = p.getInputStream();

			InputStreamReader isr = new InputStreamReader(fis);

			BufferedReader br = new BufferedReader(isr);
			StringBuilder line = new StringBuilder();
			while (br.readLine() != null) {
				line.append(br.readLine());
			}
			return line.toString().split(" ");


		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
