package com.cwh.wb.WhiteBuffaloConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WhiteBuffaloREFreeSpinsReelsConfig implements REReelsConfig {

	private static final Map<Object, Map<Integer,List<String>>> reels = new HashMap<Object, Map<Integer,List<String>>>();
	
	private static final List<Integer> weights = new ArrayList<Integer>();

	static {
		// 92% Paytable
		getReels().put(WhiteBuffaloRTP.RTP_92, new HashMap<Integer, List<String>>());
		getReels().get(WhiteBuffaloRTP.RTP_92).put(2, Arrays.asList(
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, 
				WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, 
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING,
				WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_QUEEN,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK));
		getReels().get(WhiteBuffaloRTP.RTP_92).put(3, Arrays.asList(
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, 
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK));
		getReels().get(WhiteBuffaloRTP.RTP_92).put(4, Arrays.asList(
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO,
				WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_GODDESS, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_GODDESS, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN,
				WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, 
				WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_KING,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_KING,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK));

		// 94% Paytable
		getReels().put(WhiteBuffaloRTP.RTP_94, new HashMap<Integer, List<String>>());
		getReels().get(WhiteBuffaloRTP.RTP_94).put(2, Arrays.asList(
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, 
				WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, 
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING,
				WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_QUEEN,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK));
		getReels().get(WhiteBuffaloRTP.RTP_94).put(3, Arrays.asList(
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, 
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK));
		getReels().get(WhiteBuffaloRTP.RTP_94).put(4, Arrays.asList(
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO,
				WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_GODDESS, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_GODDESS, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN,
				WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, 
				WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_KING,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_KING,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK));

		// 95% Paytable
		getReels().put(WhiteBuffaloRTP.RTP_95, new HashMap<Integer, List<String>>());
		getReels().get(WhiteBuffaloRTP.RTP_95).put(2, Arrays.asList(
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, 
				WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, 
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING,
				WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_QUEEN,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK));
		getReels().get(WhiteBuffaloRTP.RTP_95).put(3, Arrays.asList(
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, 
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK));
		getReels().get(WhiteBuffaloRTP.RTP_95).put(4, Arrays.asList(
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_LOGO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_LOGO,
				WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO,
				WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WHITEBUFFALO, WhiteBuffaloConfig.SYMBOL_GODDESS, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_GODDESS, 
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_GODDESS, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_TOMAHAWK, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_URN, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_URN,
				WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, 
				WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_ACE, WhiteBuffaloConfig.SYMBOL_KING,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_KING,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_KING, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_WILD,
				WhiteBuffaloConfig.SYMBOL_QUEEN, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_WILD, 
				WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_JACK, WhiteBuffaloConfig.SYMBOL_WILD, WhiteBuffaloConfig.SYMBOL_JACK));
		
		weights.addAll(Arrays.asList(1, 4, 4, 4, 4, 4, 4, 5, 23, 23, 23, 10, 10, 10, 5, 23, 23, 23, 10, 10, 10, 5, 5, 5, 5, 3, 3, 3, 3, 5, 5, 5, 3, 3, 3, 3, 5, 5, 5, 3, 3, 3, 3, 5, 5, 5, 3, 3, 3, 3, 5, 5, 5, 3, 3, 3, 3, 5, 5, 5, 3, 3, 3, 3));
	}

	public int displayHeight() {
		return WhiteBuffaloConfig.DISPLAY_HEIGHT;
	}

	public Map<String, String> symbolMap() {
		return WhiteBuffaloConfig.SYMBOL_MAP;
	}

	public List<String> reel(Object level, int reel) {
		return getReels().get(level).get(reel);
	}

	public int numReels() {
		return WhiteBuffaloConfig.NUM_REELS;
	}

	public Map<String,String> reverseMap(){
		return WhiteBuffaloConfig.REVERSE_MAP;
	}

	public static Map<Object, Map<Integer,List<String>>> getReels() {
		return reels;
	}

	public List<String> getPattern(Object level, int pos) {
		List<String> pattern = new ArrayList<String>();
		int countWeight = 0;
		int realPos = 0;
        for (int i = 0; i < weights.size(); i++) {
            countWeight += weights.get(i);
            if (countWeight > pos) {
            	realPos = i;
            	break;
            }
        }
        pattern.add(reel(level, 2).get(realPos));
        pattern.add(reel(level, 3).get(realPos));
        pattern.add(reel(level, 4).get(realPos));
		return pattern;
	}

}
