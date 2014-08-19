package   com.cwh.wb.WhiteBuffaloConfig;

import com.cwh.slotstoolbox2.scatter.ScatterConfig;

import java.util.Map;

public class WhiteBuffaloScatterConfig implements ScatterConfig
{

    public String symbol() {
        return "SYMBOL_BONUS";
    }

    public Map<Integer, Integer> pays() {
        return WhiteBuffaloConfig.SCATTER_PAYTABLE;
    }
}
