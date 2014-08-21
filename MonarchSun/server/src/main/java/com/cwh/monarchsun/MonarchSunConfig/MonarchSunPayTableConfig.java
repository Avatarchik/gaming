package   com.cwh.monarchsun.MonarchSunConfig;

import java.util.Map;

import com.cwh.slotstoolbox2.paylines.paytable.Paytable;

public class MonarchSunPayTableConfig {
    public Paytable paytableL2R() {
        return MonarchSunConfig.PAY_TABLE_L2R;
    }
    
    public Paytable paytableR2L() {
        return MonarchSunConfig.PAY_TABLE_R2L;
    }
    
    public Paytable paytableJackpot() {
        return MonarchSunConfig.JACKPOT_PAYTABLE;
    }
    
    public Map<String, Integer> paylinesL2R() {
        return MonarchSunConfig.PAY_LINES_L2R;
    }
    
    public Map<String, Integer> paylinesR2L() {
        return MonarchSunConfig.PAY_LINES_R2L;
    }
    
}
