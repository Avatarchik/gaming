/**
 * Created with IntelliJ IDEA.
 * User: NCraig
 * Date: 8/16/13
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */

package   com.cwh.monarchsun.MonarchSunConfig;

import com.cwh.slotstoolbox2.reels.ReelsConfig;
import java.util.*;

public class MonarchSunReelsConfig implements ReelsConfig {

	private static final Map<Object, Map<Integer,List<String>>> reels = new HashMap<Object, Map<Integer,List<String>>>();
	
	private static final Map<Object, Map<Integer,List<Integer>>> initStops = new HashMap<Object, Map<Integer,List<Integer>>>();

	static {
		// 92% Paytable
		getReels().put(MonarchSunRTP.RTP_92, new HashMap<Integer, List<String>>());
		getReels().get(MonarchSunRTP.RTP_92).put(1, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3));
		getReels().get(MonarchSunRTP.RTP_92).put(2, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
		getReels().get(MonarchSunRTP.RTP_92).put(3, Arrays.asList(
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2));
		getReels().get(MonarchSunRTP.RTP_92).put(4, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
		getReels().get(MonarchSunRTP.RTP_92).put(5, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3));

		// 94% Paytable
		getReels().put(MonarchSunRTP.RTP_94, new HashMap<Integer, List<String>>());
		getReels().get(MonarchSunRTP.RTP_94).put(1, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3));
		getReels().get(MonarchSunRTP.RTP_94).put(2, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
		getReels().get(MonarchSunRTP.RTP_94).put(3, Arrays.asList(
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2));
		getReels().get(MonarchSunRTP.RTP_94).put(4, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
		getReels().get(MonarchSunRTP.RTP_94).put(5, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3));

		// 95% Paytable
		getReels().put(MonarchSunRTP.RTP_95, new HashMap<Integer, List<String>>());
		getReels().get(MonarchSunRTP.RTP_95).put(1, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3));
		getReels().get(MonarchSunRTP.RTP_95).put(2, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M2,MonarchSunConfig.SYMBOL_M2));
		getReels().get(MonarchSunRTP.RTP_95).put(3, Arrays.asList(
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_WW, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M2));
		getReels().get(MonarchSunRTP.RTP_95).put(4, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_BN, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L3,
                MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2));
		getReels().get(MonarchSunRTP.RTP_95).put(5, Arrays.asList(
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE,
                MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_RE, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2,
                MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H1,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H3,
                MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_L4, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1, MonarchSunConfig.SYMBOL_L1,
                MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_M2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_H2,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_H3, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2,
                MonarchSunConfig.SYMBOL_H1, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_L2, MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_M1, MonarchSunConfig.SYMBOL_M1,
                MonarchSunConfig.SYMBOL_H2, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_L3, MonarchSunConfig.SYMBOL_H3));

		initStops.put(MonarchSunRTP.RTP_92, new HashMap<Integer, List<Integer>>());
        initStops.get(MonarchSunRTP.RTP_92).put(0, Arrays.asList(4,7,0,4,6));
        initStops.get(MonarchSunRTP.RTP_92).put(1, Arrays.asList(18,24,20,25,46));
        initStops.get(MonarchSunRTP.RTP_92).put(2, Arrays.asList(50,54,54,42,44));
        initStops.get(MonarchSunRTP.RTP_92).put(3, Arrays.asList(54,54,45,62,60));
        initStops.get(MonarchSunRTP.RTP_92).put(4, Arrays.asList(79,76,81,76,79));
        
        initStops.put(MonarchSunRTP.RTP_94, new HashMap<Integer, List<Integer>>());
        initStops.get(MonarchSunRTP.RTP_94).put(0, Arrays.asList(6,15,13,16,18));
        initStops.get(MonarchSunRTP.RTP_94).put(1, Arrays.asList(26,27,14,7,18));
        initStops.get(MonarchSunRTP.RTP_94).put(2, Arrays.asList(46,4,45,41,44));
        initStops.get(MonarchSunRTP.RTP_94).put(3, Arrays.asList(50,51,62,54,54));
        initStops.get(MonarchSunRTP.RTP_94).put(4, Arrays.asList(86,85,82,86,87));
        
        initStops.put(MonarchSunRTP.RTP_95, new HashMap<Integer, List<Integer>>());
        initStops.get(MonarchSunRTP.RTP_95).put(0, Arrays.asList(18,16,22,31,33));
        initStops.get(MonarchSunRTP.RTP_95).put(1, Arrays.asList(36,54,45,41,44));
        initStops.get(MonarchSunRTP.RTP_95).put(2, Arrays.asList(50,51,50,50,56));
        initStops.get(MonarchSunRTP.RTP_95).put(3, Arrays.asList(62,62,62,62,62));
        initStops.get(MonarchSunRTP.RTP_95).put(4, Arrays.asList(74,80,81,80,81));
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
	
	public static Map<Integer, List<Integer>> getInitStops(Object level) {
    	return initStops.get(level);
    }
}
