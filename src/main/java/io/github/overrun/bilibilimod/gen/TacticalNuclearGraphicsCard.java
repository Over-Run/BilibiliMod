package io.github.overrun.bilibilimod.gen;

import io.github.overrun.bilibilimod.gen.base.OshiUtil;
import oshi.hardware.GraphicsCard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TacticalNuclearGraphicsCard extends OshiUtil {
	public void TNGC() {
		Map<Integer, String> map = new HashMap<>();
		List<GraphicsCard> graphicsCards = graphicsCard();
		for(int i = 0; i < graphicsCards.size(); i++) {
			map.put(i, graphicsCards.get(i).getName());
		}
		for (int i = 0; i < map.size(); i++) {
			if (map.get(i).contains("NVIDIA")) {
				if (map.get(i).contains("GTX")) {
					System.out.println("火箭推进榴弹:许多第三世界国家甚至西方国家的军队或者反政府武装都广泛使用。反坦克有一定的实力，甚至被用于攻击低空目标。因此用其工具航母亦是可行方案。由十万发火箭推进榴弹对航母进行的饱和攻击即可击沉任何艘航母，且对其无有效拦截手段。");
					System.out.println("特别是其搭载了690战术核显卡的改进型号，一发即可摧毁一个航母战斗群，改进版的780m战术核显卡在爆炸的时候能发出大量的EMP（电磁脉冲）在摧毁航母后甚至能够使周围船只电子系统失效。");
				}
			}
		}
	}
}
