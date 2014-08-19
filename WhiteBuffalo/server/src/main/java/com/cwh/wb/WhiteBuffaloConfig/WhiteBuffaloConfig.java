package   com.cwh.wb.WhiteBuffaloConfig;

import com.cwh.slotstoolbox2.paylines.paytable.Paytable;

import java.util.*;

/*
Paytable data is converted from paytable CJ.20.SH3.X.13.1111.50000.B.XML
*/

public class WhiteBuffaloConfig  {

    public static final String SYMBOL_WILD = "SYMBOL_WILD";
    public static final String SYMBOL_LOGO = "SYMBOL_LOGO";
    public static final String SYMBOL_WHITEBUFFALO = "SYMBOL_WHITEBUFFALO";
    public static final String SYMBOL_GODDESS = "SYMBOL_GODDESS";
    public static final String SYMBOL_TOMAHAWK = "SYMBOL_TOMAHAWK";
    public static final String SYMBOL_URN = "SYMBOL_URN";
    public static final String SYMBOL_ACE = "SYMBOL_ACE";
    public static final String SYMBOL_KING = "SYMBOL_KING";
    public static final String SYMBOL_QUEEN = "SYMBOL_QUEEN";
    public static final String SYMBOL_JACK = "SYMBOL_JACK";
    public static final String SYMBOL_BONUS = "SYMBOL_BONUS";
    public static final String SYMBOL_RE = "SYMBOL_RE";

    public static final int NUM_REELS = 5;
    public static final int DISPLAY_HEIGHT = 4;
    
    public static String WILD_SYMBOL = SYMBOL_WILD;
    
    public static final int FREE_SPINS_AWARDED = 8;
    public static final int MAX_FREE_SPINS_AWARDED = 128;

    public static final int SCATTER_PAYLINENUMBER = 0;
    
    public static final int COST_TO_COVER = 50;

    public static final Map<String, String> SYMBOL_MAP = createSymbolMap();
    private static Map<String, String> createSymbolMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(SYMBOL_WILD, "1");
        map.put(SYMBOL_LOGO, "2");
        map.put(SYMBOL_WHITEBUFFALO, "3");
        map.put(SYMBOL_GODDESS, "4");
        map.put(SYMBOL_TOMAHAWK, "5");
        map.put(SYMBOL_URN, "6");
        map.put(SYMBOL_ACE, "7");
        map.put(SYMBOL_KING, "8");
        map.put(SYMBOL_QUEEN, "9");
        map.put(SYMBOL_JACK, "10");
        map.put(SYMBOL_BONUS, "11");
        map.put(SYMBOL_RE, "12");

        return map;
    }

    public static Map<String, String> REVERSE_MAP = createReverseMap();
    private static Map<String, String> createReverseMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", SYMBOL_WILD);
        map.put("2", SYMBOL_LOGO);
        map.put("3", SYMBOL_WHITEBUFFALO);
        map.put("4", SYMBOL_GODDESS);
        map.put("5", SYMBOL_TOMAHAWK);
        map.put("6", SYMBOL_URN);
        map.put("7", SYMBOL_ACE);
        map.put("8", SYMBOL_KING);
        map.put("9", SYMBOL_QUEEN);
        map.put("10",SYMBOL_JACK);
        map.put("11",SYMBOL_BONUS);
        map.put("12",SYMBOL_RE);

        return map;
    }
    
    //Payline numbers represent the conbination of the symbol that is winning and the orientation of the win either Left to Right or Right to Left. They are no treated as tradional payline numbers.
    public static Map<String, Integer> PAY_LINES_L2R = createPaylinesL2R();
    private static Map<String, Integer> createPaylinesL2R() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(SYMBOL_LOGO, 1);
        map.put(SYMBOL_WHITEBUFFALO, 2);
        map.put(SYMBOL_GODDESS, 3);
        map.put(SYMBOL_TOMAHAWK, 4);
        map.put(SYMBOL_URN, 5);
        map.put(SYMBOL_ACE, 6);
        map.put(SYMBOL_KING, 7);
        map.put(SYMBOL_QUEEN, 8);
        map.put(SYMBOL_JACK, 9);
        return map;
    }
    
    public static Map<String, Integer> PAY_LINES_R2L = createPaylinesR2L();
    private static Map<String, Integer> createPaylinesR2L() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(SYMBOL_LOGO, 10);
        map.put(SYMBOL_WHITEBUFFALO, 11);
        map.put(SYMBOL_GODDESS, 12);
        map.put(SYMBOL_TOMAHAWK, 13);
        map.put(SYMBOL_URN, 14);
        map.put(SYMBOL_ACE, 15);
        map.put(SYMBOL_KING, 16);
        map.put(SYMBOL_QUEEN, 17);
        map.put(SYMBOL_JACK, 18);
        return map;
    }


    public static Paytable PAY_TABLE_L2R = createPaytableL2R();
    protected static Paytable createPaytableL2R() {
        Paytable paytable = new Paytable(WILD_SYMBOL);
        
        paytable.add(SYMBOL_LOGO, 5, 400);
        paytable.add(SYMBOL_LOGO, 4, 100);
        paytable.add(SYMBOL_LOGO, 3, 25);

        paytable.add(SYMBOL_WHITEBUFFALO, 5, 100);
        paytable.add(SYMBOL_WHITEBUFFALO, 4, 40);
        paytable.add(SYMBOL_WHITEBUFFALO, 3, 20);

        paytable.add(SYMBOL_GODDESS, 5, 60);
        paytable.add(SYMBOL_GODDESS, 4, 30);
        paytable.add(SYMBOL_GODDESS, 3, 15);
        
        paytable.add(SYMBOL_TOMAHAWK, 5, 50);
        paytable.add(SYMBOL_TOMAHAWK, 4, 20);
        paytable.add(SYMBOL_TOMAHAWK, 3, 10);

        paytable.add(SYMBOL_URN, 5, 50);
        paytable.add(SYMBOL_URN, 4, 20);
        paytable.add(SYMBOL_URN, 3, 10);
        
        paytable.add(SYMBOL_ACE, 5, 30);
        paytable.add(SYMBOL_ACE, 4, 15);
        paytable.add(SYMBOL_ACE, 3, 5);

        paytable.add(SYMBOL_KING, 5, 30);
        paytable.add(SYMBOL_KING, 4, 15);
        paytable.add(SYMBOL_KING, 3, 5);

        paytable.add(SYMBOL_QUEEN, 5, 25);
        paytable.add(SYMBOL_QUEEN, 4, 10);
        paytable.add(SYMBOL_QUEEN, 3, 5);

        paytable.add(SYMBOL_JACK, 5, 25);
        paytable.add(SYMBOL_JACK, 4, 10);
        paytable.add(SYMBOL_JACK, 3, 5);

        return paytable;
    }
    
    public static Paytable PAY_TABLE_R2L = createPaytableR2L();
    protected static Paytable createPaytableR2L() {
        Paytable paytable = new Paytable(WILD_SYMBOL);
        
        paytable.add(SYMBOL_LOGO, 4, 100);
        paytable.add(SYMBOL_LOGO, 3, 25);

        paytable.add(SYMBOL_WHITEBUFFALO, 4, 40);
        paytable.add(SYMBOL_WHITEBUFFALO, 3, 20);

        paytable.add(SYMBOL_GODDESS, 4, 30);
        paytable.add(SYMBOL_GODDESS, 3, 15);
        
        paytable.add(SYMBOL_TOMAHAWK, 4, 20);
        paytable.add(SYMBOL_TOMAHAWK, 3, 10);

        paytable.add(SYMBOL_URN, 4, 20);
        paytable.add(SYMBOL_URN, 3, 10);
        
        paytable.add(SYMBOL_ACE, 4, 15);
        paytable.add(SYMBOL_ACE, 3, 5);

        paytable.add(SYMBOL_KING, 4, 15);
        paytable.add(SYMBOL_KING, 3, 5);

        paytable.add(SYMBOL_QUEEN, 4, 10);
        paytable.add(SYMBOL_QUEEN, 3, 5);

        paytable.add(SYMBOL_JACK, 4, 10);
        paytable.add(SYMBOL_JACK, 3, 5);

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
        paytable.add(SYMBOL_LOGO, 5, 125000);
        return paytable;
    }

}
