package com.cwh.test.monarchsun.prd;

import static junit.framework.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cwh.monarchsun.MonarchSunConfig.MonarchSunConfig;
import com.cwh.slotstoolbox2.SlotsRandom;

import nu.xom.Builder;

import com.cwh.monarchsun.model.GameState;
import com.cwh.monarchsun.model.PersistentState;
import com.cwh.monarchsun.model.Profile;
import com.cwh.monarchsun.model.RandomPools;
import com.cwh.monarchsun.model.GameState.PLAY_STATE;
import com.cwh.monarchsun.service.SpinHandler;

import org.junit.Ignore;
import org.junit.Test;

public class ReturnTest {
	final static int RUNS = 2;
	final static int SPINS = 1000000;
	final static int CREDIT_WAGER = 1;

	final String freeSpinReel1Pool = "freeSpinReel1_1_at_0_99";
	final String freeSpinReel2Pool = "freeSpinReel2_1_at_0_99";
	final String freeSpinReel3Pool = "freeSpinReel3_1_at_0_99";
	final String freeSpinReel4Pool = "freeSpinReel4_1_at_0_99";
	final String freeSpinReel5Pool = "freeSpinReel5_1_at_0_99";
	final String freeSpinReelREPool = "freeSpinReelRE_1_at_0_309";

    //Default 92%
    private static String OUTPUT_FILE = "RTP92.txt";
	private String mainSpinReel1Pool = "mainSpinReel921_1_at_0_99";
    private String mainSpinReel2Pool = "mainSpinReel922_1_at_0_98";
    private String mainSpinReel3Pool = "mainSpinReel923_1_at_0_99";
    private String mainSpinReel4Pool = "mainSpinReel924_1_at_0_98";
    private String mainSpinReel5Pool = "mainSpinReel925_1_at_0_99";
    private String mainSpinReelREPool = "mainSpinReel92RE_1_at_0_98";

	private SlotsRandom freeSpinRandomStop1;
	private SlotsRandom freeSpinRandomStop2;
	private SlotsRandom freeSpinRandomStop3;
	private SlotsRandom freeSpinRandomStop4;
	private SlotsRandom freeSpinRandomStop5;
	private SlotsRandom freeSpinRandomStopRE;

	private SlotsRandom mainSpinRandomStop1;
	private SlotsRandom mainSpinRandomStop2;
	private SlotsRandom mainSpinRandomStop3;
	private SlotsRandom mainSpinRandomStop4;
	private SlotsRandom mainSpinRandomStop5;
	private SlotsRandom mainSpinRandomStopRE;

	private void CreateRandomNumberGenerators(String level)  throws Exception
	{
        freeSpinRandomStop1 = new PredRNG(freeSpinReel1Pool, 0, 99);
        freeSpinRandomStop2 = new PredRNG(freeSpinReel2Pool, 0, 99);
		freeSpinRandomStop3 = new PredRNG(freeSpinReel3Pool, 0, 99);
		freeSpinRandomStop4 = new PredRNG(freeSpinReel4Pool, 0, 99);
		freeSpinRandomStop5 = new PredRNG(freeSpinReel5Pool, 0, 99);
		freeSpinRandomStopRE = new PredRNG(freeSpinReelREPool, 0, 309);

        if(level.equals("1"))
        {
            mainSpinRandomStop1 = new PredRNG(mainSpinReel1Pool, 0, 99);
            mainSpinRandomStop2 = new PredRNG(mainSpinReel2Pool, 0, 98);
            mainSpinRandomStop3 = new PredRNG(mainSpinReel3Pool, 0, 99);
            mainSpinRandomStop4 = new PredRNG(mainSpinReel4Pool, 0, 98);
            mainSpinRandomStop5 = new PredRNG(mainSpinReel5Pool, 0, 99);
            mainSpinRandomStopRE = new PredRNG(mainSpinReelREPool, 0, 99);
        }
        if(level.equals("2"))
        {
            mainSpinRandomStop1 = new PredRNG(mainSpinReel1Pool, 0, 99);
            mainSpinRandomStop2 = new PredRNG(mainSpinReel2Pool, 0, 97);
            mainSpinRandomStop3 = new PredRNG(mainSpinReel3Pool, 0, 99);
            mainSpinRandomStop4 = new PredRNG(mainSpinReel4Pool, 0, 97);
            mainSpinRandomStop5 = new PredRNG(mainSpinReel5Pool, 0, 99);
            mainSpinRandomStopRE = new PredRNG(mainSpinReelREPool, 0, 99);
        }
        if(level.equals("3"))
        {
            mainSpinRandomStop1 = new PredRNG(mainSpinReel1Pool, 0, 99);
            mainSpinRandomStop2 = new PredRNG(mainSpinReel2Pool, 0, 97);
            mainSpinRandomStop3 = new PredRNG(mainSpinReel3Pool, 0, 99);
            mainSpinRandomStop4 = new PredRNG(mainSpinReel4Pool, 0, 97);
            mainSpinRandomStop5 = new PredRNG(mainSpinReel5Pool, 0, 99);
            mainSpinRandomStopRE = new PredRNG(mainSpinReelREPool, 0, 99);
        }
	}

	@Ignore
	@Test
	public void RunRTPTest()
	{
		try {
			System.out.println("Choose the RTP level for the Return to Player Test. Type 1 for 92%, 2 for 94% or 3 for 95%:");
			//Scanner terminalInput = new Scanner(System.in);
			String level = "2";//terminalInput.nextLine().trim();

			//terminalInput.close();
			if(!level.equals("1") && !level.equals("2") && !level.equals("3"))
			{
				fail();
			}
            if(level.equals("1"))
            {
                OUTPUT_FILE = "RTP92.txt";
                mainSpinReel1Pool = "mainSpinReel921_1_at_0_99";
                mainSpinReel2Pool = "mainSpinReel922_1_at_0_98";
                mainSpinReel3Pool = "mainSpinReel923_1_at_0_99";
                mainSpinReel4Pool = "mainSpinReel924_1_at_0_98";
                mainSpinReel5Pool = "mainSpinReel925_1_at_0_99";
                mainSpinReelREPool = "mainSpinReel92RE_1_at_0_98";
                System.out.println("********** RTP - 92% **********");
                writeToFile("********** RTP - 92% **********");
            }
            if(level.equals("2"))
            {
                OUTPUT_FILE = "RTP94.txt";
                mainSpinReel1Pool = "mainSpinReel941_1_at_0_99";
                mainSpinReel2Pool = "mainSpinReel942_1_at_0_97";
                mainSpinReel3Pool = "mainSpinReel943_1_at_0_99";
                mainSpinReel4Pool = "mainSpinReel944_1_at_0_97";
                mainSpinReel5Pool = "mainSpinReel945_1_at_0_99";
                mainSpinReelREPool = "mainSpinReel94RE_1_at_0_98";
                System.out.println("********** RTP - 94% **********");
                writeToFile("********** RTP - 94% **********");
            }
            if(level.equals("3"))
            {
                OUTPUT_FILE = "RTP95.txt";
                mainSpinReel1Pool = "mainSpinReel951_1_at_0_99";
                mainSpinReel2Pool = "mainSpinReel952_1_at_0_97";
                mainSpinReel3Pool = "mainSpinReel953_1_at_0_99";
                mainSpinReel4Pool = "mainSpinReel954_1_at_0_97";
                mainSpinReel5Pool = "mainSpinReel955_1_at_0_99";
                mainSpinReelREPool = "mainSpinReel95RE_1_at_0_98";
                System.out.println("********** RTP - 95% **********");
                writeToFile("********** RTP - 95% **********");
            }


			for(int r = 0; r < RUNS; r++)
			{
				CreateRandomNumberGenerators(level);

				long paylineHits = 0;
                long freeSpinPaylineHits = 0;
                long triggerHit = 0;
                long retriggerHit = 0;
                long freeSpinHit = 0;
                long coinOut = 0;
                long freeSpinWon = 0;


				System.out.println("********** RUN #" + (r+1) +"/"+RUNS + " **********");
				writeToFile("********** RUN #" + (r+1) +"/"+RUNS + " **********");
				final GameState       gameState       = new GameState();
				final PersistentState persistentState = new PersistentState();
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("level", level);
				Profile profile = new Profile(parameterMap);

				long startTime = System.currentTimeMillis();
				for(int i = 0; i < SPINS; i++) {
					String sRandomPools = "";

					gameState.setPlayState(PLAY_STATE.NEW);
					while (gameState.getPlayState() != PLAY_STATE.FINISHED)
					{
						PLAY_STATE beforeState = gameState.getPlayState();
						int beforeFreeSpins = gameState.getFreeAwardsTotal() - gameState.getFreeAwardsUsed();
						if(beforeState == PLAY_STATE.FREE) {
                            int i1 = freeSpinRandomStop1.nextSeq();
                            int i2 = freeSpinRandomStop1.nextSeq();
                            int i3 = freeSpinRandomStop1.nextSeq();
                            int i4 = freeSpinRandomStop1.nextSeq();
                            int i5 = freeSpinRandomStop1.nextSeq();

							sRandomPools =
									"<randomPools>\n" +
											"<pool id=\"" + freeSpinReel1Pool + "\">"+freeSpinRandomStop1.nextSeq() +"</pool>"+
											"<pool id=\"" + freeSpinReel2Pool + "\">"+freeSpinRandomStop2.nextSeq() +"</pool>"+
											"<pool id=\"" + freeSpinReel3Pool + "\">"+freeSpinRandomStop3.nextSeq() +"</pool>"+
											"<pool id=\"" + freeSpinReel4Pool + "\">"+freeSpinRandomStop4.nextSeq() +"</pool>"+
											"<pool id=\"" + freeSpinReel5Pool + "\">"+freeSpinRandomStop5.nextSeq() +"</pool>" +
											"<pool id=\"" + freeSpinReelREPool + "\">"+freeSpinRandomStopRE.nextSeq() +"</pool>" +
											"</randomPools>";
						} else {
							sRandomPools =
									"<randomPools>\n" +
											"    <pool id=\"" + mainSpinReel1Pool + "\">"+mainSpinRandomStop1.nextSeq() +"</pool>\n" +
											"    <pool id=\"" + mainSpinReel2Pool + "\">"+mainSpinRandomStop2.nextSeq() +"</pool>\n" +
											"    <pool id=\"" + mainSpinReel3Pool + "\">"+mainSpinRandomStop3.nextSeq() +"</pool>\n" +
											"    <pool id=\"" + mainSpinReel4Pool + "\">"+mainSpinRandomStop4.nextSeq() +"</pool>\n" +
											"    <pool id=\"" + mainSpinReel5Pool + "\">"+mainSpinRandomStop5.nextSeq() +"</pool>\n" +
											"    <pool id=\"" + mainSpinReelREPool + "\">"+mainSpinRandomStopRE.nextSeq() +"</pool>\n" +
											"</randomPools>";
						}

						final RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());


						gameState.setCreditWager(CREDIT_WAGER);


						final SpinHandler sHandler = new SpinHandler();
						sHandler.setGameState(gameState);
						sHandler.setPersistentState(persistentState);
						sHandler.setRandomPools(randomPools);
						sHandler.processSpin(profile);

                        //System.out.println("Spin; Symbol; Quantity; Payout; Reels Display");
                        /*for(int payline = 0; payline < sHandler.getGameState().getSpinResults().getPaylineResults().size(); payline++)
                        {
                            String symbol = sHandler.getGameState().getSpinResults().getPaylineResults().get(payline).symbol();
                            int qty = sHandler.getGameState().getSpinResults().getPaylineResults().get(payline).numSymbols();
                            int payout = sHandler.getGameState().getSpinResults().getPaylineResults().get(payline).won();
                            String reels = sHandler.getGameState().getReelDisplay().toString();
                            System.out.println(i + "; " + symbol + "; " + qty + "; " + payout + "; " + reels);
                        }*/


						if(beforeState == PLAY_STATE.FREE) {
							freeSpinPaylineHits += gameState.getSpinResults().getPaylineResults().size();
							int currentFreeSpins = gameState.getFreeAwardsTotal() - gameState.getFreeAwardsUsed();
							if(currentFreeSpins > beforeFreeSpins) {
								retriggerHit++;
							}
						} else {
							paylineHits += gameState.getSpinResults().getPaylineResults().size();
							if(gameState.getPlayState() == PLAY_STATE.FREE) {
								triggerHit++;
							}
						}

						if((i+1)%(SPINS/10)==0)
						{
							//System.out.println(((i+1)/(SPINS/10))*10+"% Done...");
						}
					}
				}
				coinOut = gameState.getCreditWonTotal();
				freeSpinWon = gameState.getCreditFreeWonTotal();
				freeSpinHit = gameState.getFreeAwardsTotal();

				writeToFile(SPINS + " Spins");
				writeToFile("Coin In: " + CREDIT_WAGER* MonarchSunConfig.COST_TO_COVER*SPINS);
				writeToFile("Payline Hits: " + paylineHits);
				writeToFile("Free Spin Payline Hits: " + freeSpinPaylineHits);
				writeToFile("Trigger Hits: " + triggerHit);
				writeToFile("Retrigger Hits: " + retriggerHit);
				writeToFile("Free Spin Hits: " + freeSpinHit);
				writeToFile("Coin Out: " + coinOut);
				writeToFile("Free Spin Won: " + freeSpinWon);
				writeToFile("RTP: " + (((double) coinOut) / (CREDIT_WAGER* MonarchSunConfig.COST_TO_COVER*SPINS)) * 100);
				long estimatedTime = System.currentTimeMillis() - startTime;
				writeToFile("Time: " + estimatedTime/1000 + " seconds");

                System.out.println(SPINS + " Spins");
                System.out.println("Coin In: " + CREDIT_WAGER* MonarchSunConfig.COST_TO_COVER*SPINS);
                System.out.println("Payline Hits: " + paylineHits);
                System.out.println("Free Spin Payline Hits: " + freeSpinPaylineHits);
                System.out.println("Trigger Hits: " + triggerHit);
                System.out.println("Retrigger Hits: " + retriggerHit);
                System.out.println("Free Spin Hits: " + freeSpinHit);
                System.out.println("Coin Out: " + coinOut);
                System.out.println("Free Spin Won: " + freeSpinWon);
                System.out.println("RTP: " + (((double) coinOut) / (CREDIT_WAGER* MonarchSunConfig.COST_TO_COVER*SPINS)) * 100);
                System.out.println("Time: " + estimatedTime/1000 + " seconds");
			}
		} catch(Throwable t) {
            t.getCause();
			t.printStackTrace();
			fail();
		}
	}

	public static void writeToFile(String text) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(OUTPUT_FILE), true));
			bw.write(text);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
		}
	}
}