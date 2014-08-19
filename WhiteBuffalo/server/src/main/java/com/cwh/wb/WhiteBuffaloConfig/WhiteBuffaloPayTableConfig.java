package   com.cwh.wb.WhiteBuffaloConfig;

import java.util.Map;

import com.cwh.slotstoolbox2.paylines.paytable.Paytable;

public class WhiteBuffaloPayTableConfig {
    public Paytable paytableL2R() {
        return WhiteBuffaloConfig.PAY_TABLE_L2R;
    }
    
    public Paytable paytableR2L() {
        return WhiteBuffaloConfig.PAY_TABLE_R2L;
    }
    
    public Paytable paytableJackpot() {
        return WhiteBuffaloConfig.JACKPOT_PAYTABLE;
    }
    
    public Map<String, Integer> paylinesL2R() {
        return WhiteBuffaloConfig.PAY_LINES_L2R;
    }
    
    public Map<String, Integer> paylinesR2L() {
        return WhiteBuffaloConfig.PAY_LINES_R2L;
    }
    
}
