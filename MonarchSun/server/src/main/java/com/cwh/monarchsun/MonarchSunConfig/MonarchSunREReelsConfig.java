package com.cwh.monarchsun.MonarchSunConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonarchSunREReelsConfig implements REReelsConfig {

	private static final Map<Object, Map<Integer,List<String>>> reels = new HashMap<Object, Map<Integer,List<String>>>();
	
	private static final List<Integer> weights = new ArrayList<Integer>();

	static {
		// 92% Paytable
		getReels().put(MonarchSunRTP.RTP_92, new HashMap<Integer, List<String>>());
        getReels().get(MonarchSunRTP.RTP_92).put(1, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1));
        getReels().get(MonarchSunRTP.RTP_92).put(2, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L2));
		/*REEL 3 NOT REQUIRED*/
		getReels().get(MonarchSunRTP.RTP_92).put(4, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L2));
        getReels().get(MonarchSunRTP.RTP_92).put(5, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1));

		// 94% Paytable
		getReels().put(MonarchSunRTP.RTP_94, new HashMap<Integer, List<String>>());
        getReels().get(MonarchSunRTP.RTP_94).put(1, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1));
        getReels().get(MonarchSunRTP.RTP_94).put(2, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L2));
		/*REEL 3 NOT REQUIRED*/
        getReels().get(MonarchSunRTP.RTP_94).put(4, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L2));
        getReels().get(MonarchSunRTP.RTP_94).put(5, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1));

		// 95% Paytable
		getReels().put(MonarchSunRTP.RTP_95, new HashMap<Integer, List<String>>());
        getReels().get(MonarchSunRTP.RTP_95).put(1, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1));
        getReels().get(MonarchSunRTP.RTP_95).put(2, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L2));
		/*REEL 3 NOT REQUIRED*/
        getReels().get(MonarchSunRTP.RTP_95).put(4, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L2));
        getReels().get(MonarchSunRTP.RTP_95).put(5, Arrays.asList(
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1));
		
		weights.addAll(Arrays.asList(7, 5, 10, 10, 10, 4, 4, 10, 10, 5, 5, 5, 5, 5, 5));
	}

	public int displayHeight() {
		return MonarchSunConfig.DISPLAY_HEIGHT;
	}

	public Map<String, String> symbolMap() {
		return MonarchSunConfig.SYMBOL_MAP;
	}

	public List<String> reel(Object level, int reel) {
		return getReels().get(level).get(reel);
	}

	public int numReels() {
		return MonarchSunConfig.NUM_REELS;
	}

	public Map<String,String> reverseMap(){
		return MonarchSunConfig.REVERSE_MAP;
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
        pattern.add(reel(level, 1).get(realPos));
        pattern.add(reel(level, 2).get(realPos));
        //pattern.add(reel(level, 3).get(realPos));
        pattern.add(reel(level, 4).get(realPos));
        pattern.add(reel(level, 5).get(realPos));

        return pattern;
	}

}
