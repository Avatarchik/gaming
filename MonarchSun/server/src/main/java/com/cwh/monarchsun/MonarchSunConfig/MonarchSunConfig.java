package   com.cwh.monarchsun.MonarchSunConfig;

import com.cwh.slotstoolbox2.paylines.paytable.Paytable;

import java.util.*;

/*
Paytable data is converted from paytable CJ.20.SH3.X.13.1111.50000.B.XML
*/

public class MonarchSunConfig {

    public static final String SYMBOL_WW = "SYMBOL_WW";
    public static final String SYMBOL_H1 = "SYMBOL_H1";
    public static final String SYMBOL_H2 = "SYMBOL_H2";
    public static final String SYMBOL_H3 = "SYMBOL_H3";
    public static final String SYMBOL_M1 = "SYMBOL_M1";
    public static final String SYMBOL_M2 = "SYMBOL_M2";
    public static final String SYMBOL_L1 = "SYMBOL_L1";
    public static final String SYMBOL_L2 = "SYMBOL_L2";
    public static final String SYMBOL_L3 = "SYMBOL_L3";
    public static final String SYMBOL_L4 = "SYMBOL_L4";
    public static final String SYMBOL_BN = "SYMBOL_BN";
    //public static final String SYMBOL_DUMMY = "SYMBOL_DUMMY";
    public static final String SYMBOL_RE = "SYMBOL_RE";

    public static final int NUM_REELS = 5;
    public static final int DISPLAY_HEIGHT = 4;
    
    public static String WILD_SYMBOL = SYMBOL_WW;
    
    public static final int FREE_SPINS_AWARDED = 10;
    public static final int MAX_FREE_SPINS_AWARDED = 20;

    public static final int SCATTER_PAYLINENUMBER = 0;
    
    public static final int COST_TO_COVER = 50;

    public static final Map<String, String> SYMBOL_MAP = createSymbolMap();
    private static Map<String, String> createSymbolMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(SYMBOL_WW, "1");
        map.put(SYMBOL_H1, "2");
        map.put(SYMBOL_H2, "3");
        map.put(SYMBOL_H3, "4");
        map.put(SYMBOL_M1, "5");
        map.put(SYMBOL_M2, "6");
        map.put(SYMBOL_L1, "7");
        map.put(SYMBOL_L2, "8");
        map.put(SYMBOL_L3, "9");
        map.put(SYMBOL_L4, "10");
        map.put(SYMBOL_BN, "11");
        //map.put(SYMBOL_DUMMY, "12");
        map.put(SYMBOL_RE, "12");

        return map;
    }

    public static Map<String, String> REVERSE_MAP = createReverseMap();
    private static Map<String, String> createReverseMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", SYMBOL_WW);
        map.put("2", SYMBOL_H1);
        map.put("3", SYMBOL_H2);
        map.put("4", SYMBOL_H3);
        map.put("5", SYMBOL_M1);
        map.put("6", SYMBOL_M2);
        map.put("7", SYMBOL_L1);
        map.put("8", SYMBOL_L2);
        map.put("9", SYMBOL_L3);
        map.put("10", SYMBOL_L4);
        map.put("11", SYMBOL_BN);
        //map.put("12", SYMBOL_DUMMY);
        map.put("12",SYMBOL_RE);

        return map;
    }
    
    //Payline numbers represent the conbination of the symbol that is winning and the orientation of the win either Left to Right or Right to Left. They are no treated as tradional payline numbers.
    public static Map<String, Integer> PAY_LINES_L2R = createPaylinesL2R();
    private static Map<String, Integer> createPaylinesL2R() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(SYMBOL_H1, 1);
        map.put(SYMBOL_H2, 2);
        map.put(SYMBOL_H3, 3);
        map.put(SYMBOL_M1, 4);
        map.put(SYMBOL_M2, 5);
        map.put(SYMBOL_L1, 6);
        map.put(SYMBOL_L2, 7);
        map.put(SYMBOL_L3, 8);
        map.put(SYMBOL_L4, 9);
        return map;
    }
    
    public static Map<String, Integer> PAY_LINES_R2L = createPaylinesR2L();
    private static Map<String, Integer> createPaylinesR2L() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(SYMBOL_H1, 10);
        map.put(SYMBOL_H2, 11);
        map.put(SYMBOL_H3, 12);
        map.put(SYMBOL_M1, 13);
        map.put(SYMBOL_M2, 14);
        map.put(SYMBOL_L1, 15);
        map.put(SYMBOL_L2, 16);
        map.put(SYMBOL_L3, 17);
        map.put(SYMBOL_L4, 18);
        return map;
    }


    public static Paytable PAY_TABLE_L2R = createPaytableL2R();
    protected static Paytable createPaytableL2R() {
        Paytable paytable = new Paytable(WILD_SYMBOL);

        paytable.add(SYMBOL_H1, 5, 100);
        paytable.add(SYMBOL_H1, 4, 40);
        paytable.add(SYMBOL_H1, 3, 20);

        paytable.add(SYMBOL_H2, 5, 50);
        paytable.add(SYMBOL_H2, 4, 20);
        paytable.add(SYMBOL_H2, 3, 10);
        
        paytable.add(SYMBOL_H3, 5, 25);
        paytable.add(SYMBOL_H3, 4, 10);
        paytable.add(SYMBOL_H3, 3, 5);

        paytable.add(SYMBOL_M1, 5, 20);
        paytable.add(SYMBOL_M1, 4, 10);
        paytable.add(SYMBOL_M1, 3, 5);
        
        paytable.add(SYMBOL_M2, 5, 20);
        paytable.add(SYMBOL_M2, 4, 10);
        paytable.add(SYMBOL_M2, 3, 5);

        paytable.add(SYMBOL_L1, 5, 16);
        paytable.add(SYMBOL_L1, 4, 8);
        paytable.add(SYMBOL_L1, 3, 4);

        paytable.add(SYMBOL_L2, 5, 16);
        paytable.add(SYMBOL_L2, 4, 8);
        paytable.add(SYMBOL_L2, 3, 4);

        paytable.add(SYMBOL_L3, 5, 16);
        paytable.add(SYMBOL_L3, 4, 8);
        paytable.add(SYMBOL_L3, 3, 4);

        paytable.add(SYMBOL_L4, 5, 16);
        paytable.add(SYMBOL_L4, 4, 8);
        paytable.add(SYMBOL_L4, 3, 4);

        return paytable;
    }
    
    public static Paytable PAY_TABLE_R2L = createPaytableR2L();
    protected static Paytable createPaytableR2L() {
        Paytable paytable = new Paytable(WILD_SYMBOL);

        paytable.add(SYMBOL_H1, 4, 40);
        paytable.add(SYMBOL_H1, 3, 20);

        paytable.add(SYMBOL_H2, 4, 20);
        paytable.add(SYMBOL_H2, 3, 10);
        
        paytable.add(SYMBOL_H3, 4, 10);
        paytable.add(SYMBOL_H3, 3, 5);

        paytable.add(SYMBOL_M1, 4, 10);
        paytable.add(SYMBOL_M1, 3, 5);
        
        paytable.add(SYMBOL_M2, 4, 10);
        paytable.add(SYMBOL_M2, 3, 5);

        paytable.add(SYMBOL_L1, 4, 8);
        paytable.add(SYMBOL_L1, 3, 4);

        paytable.add(SYMBOL_L2, 4, 8);
        paytable.add(SYMBOL_L2, 3, 4);

        paytable.add(SYMBOL_L3, 4, 8);
        paytable.add(SYMBOL_L3, 3, 4);

        paytable.add(SYMBOL_L4, 4, 8);
        paytable.add(SYMBOL_L4, 3, 4);

        return paytable;
    }

    public static final Map<Integer, Integer> SCATTER_PAYTABLE = createScatterPaytable();
    private static Map<Integer, Integer> createScatterPaytable()
    {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(3, 0);
        return map;
    }
    
    public static final Paytable JACKPOT_PAYTABLE = createJackpotPaytable();
    private static Paytable createJackpotPaytable()
    {
    	Paytable paytable = new Paytable(WILD_SYMBOL);
        paytable.add(SYMBOL_WW, 5, 0);
        return paytable;
    }

    public static Boolean isWild(String symbol)
    {
        if(symbol.equals(SYMBOL_WW) || symbol.equals(SYMBOL_WW))
        {
            return true;
        }
        return false;
    }
}
