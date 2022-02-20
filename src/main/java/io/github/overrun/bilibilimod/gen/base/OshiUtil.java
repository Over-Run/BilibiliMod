package io.github.overrun.bilibilimod.gen.base;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.List;

public class OshiUtil {
	public static List<GraphicsCard> graphicsCard() {
		SystemInfo si = new SystemInfo();

		HardwareAbstractionLayer hal = si.getHardware();
		return hal.getGraphicsCards();
	}
}
