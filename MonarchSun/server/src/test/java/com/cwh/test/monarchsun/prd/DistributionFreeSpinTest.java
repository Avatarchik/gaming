package com.cwh.test.monarchsun.prd;


import static junit.framework.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import com.cwh.slotstoolbox2.SlotsRandom;

public class DistributionFreeSpinTest {
	final static int RUNS = 8;
	final static int SPINS = 10000000;
	
	final String freeSpinReel1Pool = "freeSpinReel1_1_at_0_104";
    final String freeSpinReel2Pool = "freeSpinReel2_1_at_0_93";
    final String freeSpinReel3Pool = "freeSpinReel3_1_at_0_101";
    final String freeSpinReel4Pool = "freeSpinReel4_1_at_0_67";
    final String freeSpinReel5Pool = "freeSpinReel5_1_at_0_90";

    private SlotsRandom freeSpinRandomStop1;
    private SlotsRandom freeSpinRandomStop2;
    private SlotsRandom freeSpinRandomStop3;
    private SlotsRandom freeSpinRandomStop4;
    private SlotsRandom freeSpinRandomStop5;

    private void CreateRandomNumberGenerators()  throws Exception
    {
        freeSpinRandomStop1 = new PredRNG(freeSpinReel1Pool, 0, 104);
        freeSpinRandomStop2 = new PredRNG(freeSpinReel2Pool, 0, 93);
        freeSpinRandomStop3 = new PredRNG(freeSpinReel3Pool, 0, 101);
        freeSpinRandomStop4 = new PredRNG(freeSpinReel4Pool, 0, 67);
        freeSpinRandomStop5 = new PredRNG(freeSpinReel5Pool, 0, 90);
    }

    @Ignore
	@Test
	public void RunDistributionTest() {
		/*try {
			System.out.println("Choose the RTP level for the Distribution Free Spin Test. Type 1 for 92%, 2 for 94% or 3 for 95%:");
			Scanner terminalInput = new Scanner(System.in);
			String level = terminalInput.nextLine().trim();
			terminalInput.close();
			if(!level.equals("1") && !level.equals("2") && !level.equals("3"))
			{
				fail();
			}

			for(int r = 0; r < RUNS; r++) {
				CreateRandomNumberGenerators();
				System.out.println("********** RUN #" + (r+1)+"/"+RUNS + " **********");
				String[] symbols = new String[34];
				int[] counts = new int[34];
				int index = 0;
				for(WinPattern wp : MonarchSunConfig.PAY_TABLE.getWinPatterns()) {
					symbols[index] = wp.symbol();
					counts[index] = wp.count();
					index++;
				}
				symbols[33] = MonarchSunConfig.SYMBOL_SCATTER;
				counts[33] = 3;


				int[] paylineHitCounters = new int[34];
				for(int i = 0; i < paylineHitCounters.length; i++) {
					paylineHitCounters[i]=0;
				}

				final GameState       gameState       = new GameState();
				final PersistentState persistentState = new PersistentState();
				Map<String, String> parameterMap = new HashMap<String, String>();
				parameterMap.put("level", level);
				Profile profile = new Profile(parameterMap);

				gameState.setFreeAwardsTotal(SPINS);
				gameState.setPlayState(GameState.PLAY_STATE.FREE);

				long startTime = System.currentTimeMillis();

				for(int i = 0; i < SPINS; i++) {
					String sRandomPools =
							"<randomPools>\n" +
                                    "    <pool id=\"" + freeSpinReel1Pool + "\">"+ freeSpinRandomStop1.nextSeq() +"</pool>\n" +
                                    "    <pool id=\"" + freeSpinReel2Pool + "\">"+freeSpinRandomStop2.nextSeq() +"</pool>\n" +
                                    "    <pool id=\"" + freeSpinReel3Pool + "\">"+freeSpinRandomStop3.nextSeq() +"</pool>\n" +
                                    "    <pool id=\"" + freeSpinReel4Pool + "\">"+freeSpinRandomStop4.nextSeq() +"</pool>\n" +
                                    "    <pool id=\"" + freeSpinReel5Pool + "\">"+freeSpinRandomStop5.nextSeq() +"</pool>\n" +
                                    "</randomPools>";



					RandomPools     randomPools     = new RandomPools().unmarshal(new Builder().build(new StringReader(sRandomPools)).getRootElement());

					gameState.setCreditWager(1);


					SpinHandler sHandler = new SpinHandler();
					sHandler.setGameState(gameState);
					sHandler.setPersistentState(persistentState);
					sHandler.setRandomPools(randomPools);



					sHandler.processSpin(profile);

					List<PaylineResult> paylineResults = gameState.getSpinResults().getPaylineResults();


					if(paylineResults.size() > 0) {
						PaylineResult payline = null;
						for(int k = 0; k < paylineResults.size(); k++) {
							if(paylineResults.get(k).getPayline().id() == 1) {
								payline = paylineResults.get(k);
							}
							if(paylineResults.get(k).getPayline().id() == 0) {
								paylineHitCounters[33]++;
							}
						}
						if (payline != null) {
							for(int w = 0; w < 34; w++) {
								if(symbols[w] == paylineResults.get(0).winPattern().symbol() && counts[w] == paylineResults.get(0).winPattern().count()) {
									paylineHitCounters[w]++;
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
					System.out.println("Pattern " + m + " - Symbol: " + symbols[m] + " - Count: " + counts[m] +" - Hits: " + paylineHitCounters[m]);
				}
				System.out.println("Retriggers: " + paylineHitCounters[33]);

				long estimatedTime = System.currentTimeMillis() - startTime;
				System.out.println("Time: " + estimatedTime/1000 + " seconds");
			}
		} catch(Throwable t) {
			t.printStackTrace();
			fail();
		}
*/
	}
}