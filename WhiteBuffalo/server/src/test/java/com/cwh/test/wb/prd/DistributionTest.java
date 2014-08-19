package com.cwh.test.wb.prd;


import static junit.framework.Assert.fail;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Ignore;
import org.junit.Test;

import nu.xom.Builder;

import com.cwh.slotstoolbox2.SlotsRandom;
import com.cwh.slotstoolbox2.paylines.PaylineResult;
import com.cwh.slotstoolbox2.paylines.map.WinPattern;
import com.cwh.wb.WhiteBuffaloConfig.PowerXStreamPaylineResult;
import com.cwh.wb.WhiteBuffaloConfig.WhiteBuffaloConfig;
import com.cwh.wb.model.GameState;
import com.cwh.wb.model.PersistentState;
import com.cwh.wb.model.Profile;
import com.cwh.wb.model.RandomPools;
import com.cwh.wb.model.GameState.PLAY_STATE;
import com.cwh.wb.service.SpinHandler;


public class DistributionTest {
	final static int RUNS = 1;
	final static int SPINS = 1000000;

	final String mainSpinReel1Pool = "mainSpinReel1_1_at_0_129";
	final String mainSpinReel2Pool = "mainSpinReel2_1_at_0_199";
	final String mainSpinReel3Pool = "mainSpinReel3_1_at_0_199";
	final String mainSpinReel4Pool = "mainSpinReel4_1_at_0_199";
	final String mainSpinReel5Pool = "mainSpinReel5_1_at_0_149";
	final String mainSpinReelREPool = "mainSpinReelRE_1_at_0_499";

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

			for(int r = 0; r < RUNS; r++) {
				System.out.println("********** RUN #" + (r+1) +"/"+RUNS + " **********");
				String[] symbols = new String[46];
				int[] counts = new int[46];
				int[] paylineHitCounters = new int[46];
				int[] paylineHitPayouts = new int[46];
				int index = 0;
				for(WinPattern wp : WhiteBuffaloConfig.PAY_TABLE_L2R.getWinPatterns()) {
					symbols[index] = wp.symbol();
					counts[index] = wp.count();
					index++;
				}
				for(WinPattern wp : WhiteBuffaloConfig.PAY_TABLE_R2L.getWinPatterns()) {
					symbols[index] = wp.symbol();
					counts[index] = wp.count();
					index++;
				}
				symbols[45] = WhiteBuffaloConfig.SYMBOL_BONUS;
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