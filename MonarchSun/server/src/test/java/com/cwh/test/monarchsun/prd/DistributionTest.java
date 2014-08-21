package com.cwh.test.monarchsun.prd;


import static junit.framework.Assert.fail;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.cwh.monarchsun.MonarchSunConfig.MonarchSunConfig;
import org.junit.Ignore;
import org.junit.Test;

import nu.xom.Builder;

import com.cwh.slotstoolbox2.SlotsRandom;
import com.cwh.slotstoolbox2.paylines.map.WinPattern;
import com.cwh.monarchsun.MonarchSunConfig.PowerXStreamPaylineResult;
import com.cwh.monarchsun.model.GameState;
import com.cwh.monarchsun.model.PersistentState;
import com.cwh.monarchsun.model.Profile;
import com.cwh.monarchsun.model.RandomPools;
import com.cwh.monarchsun.model.GameState.PLAY_STATE;
import com.cwh.monarchsun.service.SpinHandler;


public class DistributionTest {
	final static int RUNS = 1;
	final static int SPINS = 1000000;

    //Default 92%
    private String mainSpinReel1Pool = "mainSpinReel921_1_at_0_99";
    private String mainSpinReel2Pool = "mainSpinReel922_1_at_0_98";
    private String mainSpinReel3Pool = "mainSpinReel923_1_at_0_99";
    private String mainSpinReel4Pool = "mainSpinReel924_1_at_0_98";
    private String mainSpinReel5Pool = "mainSpinReel925_1_at_0_99";
    private String mainSpinReelREPool = "mainSpinReel92RE_1_at_0_98";

    private SlotsRandom mainSpinRandomStop1;
    private SlotsRandom mainSpinRandomStop2;
    private SlotsRandom mainSpinRandomStop3;
    private SlotsRandom mainSpinRandomStop4;
    private SlotsRandom mainSpinRandomStop5;
    private SlotsRandom mainSpinRandomStopRE;

    private void CreateRandomNumberGenerators()  throws Exception
    {
        mainSpinRandomStop1 = new PredRNG(mainSpinReel1Pool, 0, 129);
        mainSpinRandomStop2 = new PredRNG(mainSpinReel2Pool, 0, 199);
        mainSpinRandomStop3 = new PredRNG(mainSpinReel3Pool, 0, 199);
        mainSpinRandomStop4 = new PredRNG(mainSpinReel4Pool, 0, 199);
        mainSpinRandomStop5 = new PredRNG(mainSpinReel5Pool, 0, 149);
        mainSpinRandomStopRE = new PredRNG(mainSpinReelREPool, 0, 499);
    }
	
    @Ignore
	@Test
	public void RunDistributionTest() {
		try {
			System.out.println("Choose the RTP level for the Distribution Test. Type 1 for 92%, 2 for 94% or 3 for 95%:");
			Scanner terminalInput = new Scanner(System.in);
			String level = terminalInput.nextLine().trim();
			terminalInput.close();
			if(!level.equals("1") && !level.equals("2") && !level.equals("3"))
			{
				fail();
			}
            if(level.equals("1"))
            {
                mainSpinReel1Pool = "mainSpinReel921_1_at_0_99";
                mainSpinReel2Pool = "mainSpinReel922_1_at_0_98";
                mainSpinReel3Pool = "mainSpinReel923_1_at_0_99";
                mainSpinReel4Pool = "mainSpinReel924_1_at_0_98";
                mainSpinReel5Pool = "mainSpinReel925_1_at_0_99";
                mainSpinReelREPool = "mainSpinReel92RE_1_at_0_98";
                System.out.println("********** RTP - 92% **********");
            }
            if(level.equals("2"))
            {
                mainSpinReel1Pool = "mainSpinReel941_1_at_0_99";
                mainSpinReel2Pool = "mainSpinReel942_1_at_0_97";
                mainSpinReel3Pool = "mainSpinReel943_1_at_0_99";
                mainSpinReel4Pool = "mainSpinReel944_1_at_0_97";
                mainSpinReel5Pool = "mainSpinReel945_1_at_0_99";
                mainSpinReelREPool = "mainSpinReel94RE_1_at_0_98";
                System.out.println("********** RTP - 94% **********");
            }
            if(level.equals("3"))
            {
                mainSpinReel1Pool = "mainSpinReel951_1_at_0_99";
                mainSpinReel2Pool = "mainSpinReel952_1_at_0_97";
                mainSpinReel3Pool = "mainSpinReel953_1_at_0_99";
                mainSpinReel4Pool = "mainSpinReel954_1_at_0_97";
                mainSpinReel5Pool = "mainSpinReel955_1_at_0_99";
                mainSpinReelREPool = "mainSpinReel95RE_1_at_0_98";
                System.out.println("********** RTP - 95% **********");
            }

			for(int r = 0; r < RUNS; r++) {
				System.out.println("********** RUN #" + (r+1) +"/"+RUNS + " **********");
				String[] symbols = new String[46];
				int[] counts = new int[46];
				int[] paylineHitCounters = new int[46];
				int[] paylineHitPayouts = new int[46];
				int index = 0;
				for(WinPattern wp : MonarchSunConfig.PAY_TABLE_L2R.getWinPatterns()) {
					symbols[index] = wp.symbol();
					counts[index] = wp.count();
					index++;
				}
				for(WinPattern wp : MonarchSunConfig.PAY_TABLE_R2L.getWinPatterns()) {
					symbols[index] = wp.symbol();
					counts[index] = wp.count();
					index++;
				}
				//symbols[45] = MonarchSunConfig.SYMBOL_DUMMY;
				counts[45] = 3;

				for(int i = 0; i < paylineHitCounters.length; i++) {
					paylineHitCounters[i]=0;
				}

				final GameState       gameState       = new GameState();
				final PersistentState persistentState = new PersistentState();
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("level", level);
				Profile profile = new Profile(parameterMap);

				long startTime = System.currentTimeMillis();
				for(int i = 0; i < SPINS; i++) {
					CreateRandomNumberGenerators();
					String sRandomPools =
							"<randomPools>\n" +
                                    "<pool id=\"" + mainSpinReel1Pool + "\">"+mainSpinRandomStop1.nextSeq() +"</pool>"+
                                    "<pool id=\"" + mainSpinReel2Pool + "\">"+mainSpinRandomStop2.nextSeq() +"</pool>"+
                                    "<pool id=\"" + mainSpinReel3Pool + "\">"+mainSpinRandomStop3.nextSeq() +"</pool>"+
                                    "<pool id=\"" + mainSpinReel4Pool + "\">"+mainSpinRandomStop4.nextSeq() +"</pool>"+
                                    "<pool id=\"" + mainSpinReel5Pool + "\">"+mainSpinRandomStop5.nextSeq() +"</pool>" +
                                    "<pool id=\"" + mainSpinReelREPool + "\">"+mainSpinRandomStopRE.nextSeq() +"</pool>" +
                                    "</randomPools>";



					gameState.setPlayState(PLAY_STATE.NEW);

					RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

					gameState.setCreditWager(1);


					SpinHandler sHandler = new SpinHandler();
					sHandler.setGameState(gameState);
					sHandler.setPersistentState(persistentState);
					sHandler.setRandomPools(randomPools);



					sHandler.processSpin(profile);

					List<PowerXStreamPaylineResult> paylineResults = gameState.getSpinResults().getPaylineResults();


					if(paylineResults.size() > 0) {
						for (PowerXStreamPaylineResult result : paylineResults) {
							for(int w = 0; w < 45; w++) {
								if(symbols[w] == result.symbol() && counts[w] == result.numSymbols()) {
									paylineHitCounters[w]++;
									paylineHitPayouts[w]+=result.won();
								}
							}
						}
					}

					if((i+1)%(SPINS/10)==0)
					{
						System.out.println(((i+1)/(SPINS/10))*10+"% Done...");
					}
				}

				for(int m = 0; m < paylineHitCounters.length-1; m++) {
					System.out.println("Pattern " + m + " - Symbol: " + symbols[m] + " - Count: " + counts[m] +" - Hits: " + paylineHitCounters[m] +" - Payout: " + paylineHitPayouts[m]);
				}
				System.out.println("Triggers: " + paylineHitCounters[33]);

				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("Time: " + estimatedTime/1000 + " seconds");
			}
		} catch(Throwable t) {
			t.printStackTrace();
			fail();
		}
	}

}