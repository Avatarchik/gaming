package   com.cwh.monarchsun.MonarchSunConfig;

public enum MonarchSunRTP {
    RTP_92, RTP_94, RTP_95;
    
    private static String[] rtpStrings = {"91.75", "93.72", "94.69"};

    public static MonarchSunRTP rtp(int level){
        MonarchSunRTP rtp = null;
        for(MonarchSunRTP r : MonarchSunRTP.values()){
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
