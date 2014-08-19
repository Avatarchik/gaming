package com.cwh.test.wb.prd;

import static junit.framework.Assert.fail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.cwh.slotstoolbox2.SlotsRandom;

import nu.xom.Builder;

import com.cwh.wb.WhiteBuffaloConfig.WhiteBuffaloConfig;
import com.cwh.wb.model.GameState;
import com.cwh.wb.model.PersistentState;
import com.cwh.wb.model.Profile;
import com.cwh.wb.model.RandomPools;
import com.cwh.wb.model.GameState.PLAY_STATE;
import com.cwh.wb.service.SpinHandler;

import org.junit.Ignore;
import org.junit.Test;

public class ReturnTest {
	final static int RUNS = 10;
	final static int SPINS = 10000000;
	final static int CREDIT_WAGER = 1;
	final static String OUTPUT_FILE = "RTP95.txt";

	final String freeSpinReel1Pool = "freeSpinReel1_1_at_0_99";
	final String freeSpinReel2Pool = "freeSpinReel2_1_at_0_199";
	final String freeSpinReel3Pool = "freeSpinReel3_1_at_0_199";
	final String freeSpinReel4Pool = "freeSpinReel4_1_at_0_199";
	final String freeSpinReel5Pool = "freeSpinReel5_1_at_0_99";
	final String freeSpinReelREPool = "freeSpinReelRE_1_at_0_399";

	final String mainSpinReel1Pool = "mainSpinReel1_1_at_0_129";
	final String mainSpinReel2Pool = "mainSpinReel2_1_at_0_199";
	final String mainSpinReel3Pool = "mainSpinReel3_1_at_0_199";
	final String mainSpinReel4Pool = "mainSpinReel4_1_at_0_199";
	final String mainSpinReel5Pool = "mainSpinReel5_1_at_0_149";
	final String mainSpinReelREPool = "mainSpinReelRE_1_at_0_499";

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

	private void CreateRandomNumberGenerators()  throws Exception
	{
		freeSpinRandomStop1 = new PredRNG(freeSpinReel1Pool, 0, 99);
		freeSpinRandomStop2 = new PredRNG(freeSpinReel2Pool, 0, 199);
		freeSpinRandomStop3 = new PredRNG(freeSpinReel3Pool, 0, 199);
		freeSpinRandomStop4 = new PredRNG(freeSpinReel4Pool, 0, 199);
		freeSpinRandomStop5 = new PredRNG(freeSpinReel5Pool, 0, 99);
		freeSpinRandomStopRE = new PredRNG(freeSpinReelREPool, 0, 399);

		mainSpinRandomStop1 = new PredRNG(mainSpinReel1Pool, 0, 129);
		mainSpinRandomStop2 = new PredRNG(mainSpinReel2Pool, 0, 199);
		mainSpinRandomStop3 = new PredRNG(mainSpinReel3Pool, 0, 199);
		mainSpinRandomStop4 = new PredRNG(mainSpinReel4Pool, 0, 199);
		mainSpinRandomStop5 = new PredRNG(mainSpinReel5Pool, 0, 149);
		mainSpinRandomStopRE = new PredRNG(mainSpinReelREPool, 0, 499);
	}

	@Ignore
	@Test
	public void RunRTPTest()
	{
		try {
			System.out.println("Choose the RTP level for the Return to Player Test. Type 1 for 92%, 2 for 94% or 3 for 95%:");
			Scanner terminalInput = new Scanner(System.in);
			String level = terminalInput.nextLine().trim();

			terminalInput.close();
			if(!level.equals("1") && !level.equals("2") && !level.equals("3"))
			{
				fail();
			}


			for(int r = 0; r < RUNS; r++)
			{
				CreateRandomNumberGenerators();

				int paylineHits = 0;
				int freeSpinPaylineHits = 0;
				int triggerHit = 0;
				int retriggerHit = 0;
				int freeSpinHit = 0;
				int coinOut = 0;
				int freeSpinWon = 0;

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
							System.out.println(((i+1)/(SPINS/10))*10+"% Done...");
						}
					}
				}
				coinOut = gameState.getCreditWonTotal();
				freeSpinWon = gameState.getCreditFreeWonTotal();
				freeSpinHit = gameState.getFreeAwardsTotal();

				writeToFile(SPINS + " Spins");
				writeToFile("Coin In: " + CREDIT_WAGER*WhiteBuffaloConfig.COST_TO_COVER*SPINS);
				writeToFile("Payline Hits: " + paylineHits);
				writeToFile("Free Spin Payline Hits: " + freeSpinPaylineHits);
				writeToFile("Trigger Hits: " + triggerHit);
				writeToFile("Retrigger Hits: " + retriggerHit);
				writeToFile("Free Spin Hits: " + freeSpinHit);
				writeToFile("Coin Out: " + coinOut);
				writeToFile("Free Spin Won: " + freeSpinWon);
				writeToFile("RTP: " + (((double) coinOut) / (CREDIT_WAGER*WhiteBuffaloConfig.COST_TO_COVER*SPINS)) * 100);
				long estimatedTime = System.currentTimeMillis() - startTime;
				writeToFile("Time: " + estimatedTime/1000 + " seconds");
			}
		} catch(Throwable t) {
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