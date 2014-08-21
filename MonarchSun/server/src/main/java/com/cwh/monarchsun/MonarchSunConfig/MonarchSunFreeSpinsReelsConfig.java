package   com.cwh.monarchsun.MonarchSunConfig;

import com.cwh.slotstoolbox2.reels.ReelsConfig;

import java.util.*;

public class MonarchSunFreeSpinsReelsConfig implements ReelsConfig {

    private static final Map<Object, Map<Integer,List<String>>> reels = new HashMap<Object, Map<Integer,List<String>>>();

    static {
        // 92% Paytable
        getReels().put(MonarchSunRTP.RTP_92, new HashMap<Integer, List<String>>());
        getReels().get(MonarchSunRTP.RTP_92).put(1, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3));
        getReels().get(MonarchSunRTP.RTP_92).put(2, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
        getReels().get(MonarchSunRTP.RTP_92).put(3, Arrays.asList(
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4));
        getReels().get(MonarchSunRTP.RTP_92).put(4, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
        getReels().get(MonarchSunRTP.RTP_92).put(5, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3));

        // 94% Paytable
        getReels().put(MonarchSunRTP.RTP_94, new HashMap<Integer, List<String>>());
        getReels().get(MonarchSunRTP.RTP_94).put(1, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3));
        getReels().get(MonarchSunRTP.RTP_94).put(2, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
        getReels().get(MonarchSunRTP.RTP_94).put(3, Arrays.asList(
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4));
        getReels().get(MonarchSunRTP.RTP_94).put(4, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
        getReels().get(MonarchSunRTP.RTP_94).put(5, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3));

        // 95% Paytable
        getReels().put(MonarchSunRTP.RTP_95, new HashMap<Integer, List<String>>());
        getReels().get(MonarchSunRTP.RTP_95).put(1, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3));
        getReels().get(MonarchSunRTP.RTP_95).put(2, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
        getReels().get(MonarchSunRTP.RTP_95).put(3, Arrays.asList(
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4));
        getReels().get(MonarchSunRTP.RTP_95).put(4, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
        getReels().get(MonarchSunRTP.RTP_95).put(5, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3));
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
}
