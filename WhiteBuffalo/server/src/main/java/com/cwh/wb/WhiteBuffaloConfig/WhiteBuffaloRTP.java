package   com.cwh.wb.WhiteBuffaloConfig;

public enum WhiteBuffaloRTP {
    RTP_92, RTP_94, RTP_95;
    
    private static String[] rtpStrings = {"91.75", "93.72", "94.69"};

    public static WhiteBuffaloRTP rtp(int level){
        WhiteBuffaloRTP rtp = null;
        for(WhiteBuffaloRTP r : WhiteBuffaloRTP.values()){
            if(r.ordinal() == level-1){
                rtp = r;
            }
        }
        return rtp;
    }

    public int getLevel() {
        return this.ordinal()+1;
    }
    
    public static String getRTPString(int level)
    {
        return  rtpStrings[level - 1];
    }

}
