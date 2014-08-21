package   com.cwh.monarchsun.MonarchSunConfig;

import com.cwh.slotstoolbox2.scatter.ScatterConfig;

import java.util.Map;

public class MonarchSunScatterConfig implements ScatterConfig
{

    public String symbol() {
        return "SYMBOL_DUMMY";
    }

    public Map<Integer, Integer> pays() {
        return MonarchSunConfig.SCATTER_PAYTABLE;
    }
}
