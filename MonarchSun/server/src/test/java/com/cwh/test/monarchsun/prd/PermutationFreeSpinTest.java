package com.cwh.test.monarchsun.prd;


import static junit.framework.Assert.fail;


import org.junit.Ignore;
import org.junit.Test;


public class PermutationFreeSpinTest {

	@Ignore
	@Test
	public void RunPermutationTest() {
		/*long startTime = System.currentTimeMillis();
		String[] symbols = new String[34];
		int[] counts = new int[35];
		int[] paylineHitCounters = new int[34];
		int[] paylineHitPayouts = new int[34];
		
		try {
			System.out.println("Choose the RTP level for the Permutation Test. Type 1 for 92%, 2 for 94% or 3 for 95%:");
			Scanner terminalInput = new Scanner(System.in);
			String level = terminalInput.nextLine().trim();
			terminalInput.close();
			if(!level.equals("1") && !level.equals("2") && !level.equals("3"))
			{
				fail();
			}
			int levelInt = Integer.parseInt(level);
			
			int index = 0;
			for(WinPattern wp : MonarchSunConfig.PAY_TABLE.getWinPatterns()) {
				symbols[index] = wp.symbol();
				counts[index] = wp.count();
				index++;
			}
			symbols[33] = MonarchSunConfig.SYMBOL_SCATTER;
			counts[33] = 3;

			int cores = Runtime.getRuntime().availableProcessors();
			int reel5Size = MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(levelInt)).get(5).size();
			PermutationFreeSpinThread[] threads = new PermutationFreeSpinThread[cores];
			for (int i = 0; i < threads.length; i++) {
			    threads[i] = new PermutationFreeSpinThread();
			    threads[i].setIdP(i);
			    threads[i].setRtpLevel(levelInt);
			    threads[i].setStart(i*(reel5Size/cores));
			    if(i < threads.length-1) {
			    	threads[i].setFinish((i+1)*(reel5Size/cores));
			    } else {
			    	threads[i].setFinish(reel5Size);
			    }
			    System.out.println("Thread #" + (i+1) + " from: " + threads[i].getStart() + " to: " + threads[i].getFinish());
			    threads[i].start();
			}
			for (int i = 0; i < threads.length; i++) {
			    try {
			       threads[i].join();
			       for(int i1 = 0; i1 < paylineHitCounters.length; i1++) {
						paylineHitCounters[i1]+=threads[i].getPaylineHitCounters()[i1];
					}

					for(int j = 0; j < paylineHitPayouts.length; j++) {
						paylineHitPayouts[j]+=threads[i].getPaylineHitPayouts()[j];
					}
			    } catch (InterruptedException ignore) {}
			}
			
			for(int m = 0; m < paylineHitCounters.length-1; m++) {
				System.out.println("Pattern " + m + " - Symbol: " + symbols[m] + " - Count: " + counts[m] +" - Hits: " + paylineHitCounters[m] + " - TotalPayout: " + paylineHitPayouts[m]);
			}
			
			long estimatedTime = System.currentTimeMillis() - startTime;
			System.out.println("Time: " + estimatedTime/1000 + " seconds");
		} catch(Throwable t) {
			t.printStackTrace();
			fail();
		}*/
	}

}

class PermutationFreeSpinThread extends Thread {
	String[] symbols = new String[34];
	public String[] getSymbols() {
		return symbols;
	}

	public void setSymbols(String[] symbols) {
		this.symbols = symbols;
	}

	public int[] getCounts() {
		return counts;
	}

	public void setCounts(int[] counts) {
		this.counts = counts;
	}

	public int[] getPaylineHitCounters() {
		return paylineHitCounters;
	}

	public void setPaylineHitCounters(int[] paylineHitCounters) {
		this.paylineHitCounters = paylineHitCounters;
	}

	public int[] getPaylineHitPayouts() {
		return paylineHitPayouts;
	}

	public void setPaylineHitPayouts(int[] paylineHitPayouts) {
		this.paylineHitPayouts = paylineHitPayouts;
	}

	public int getRtpLevel() {
		return rtpLevel;
	}

	public void setRtpLevel(int rtpLevel) {
		this.rtpLevel = rtpLevel;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}
	
	public void setStart(int start) {
		this.start = start;
	}

	public int getStart() {
		return start;
	}

	int[] counts = new int[35];
	int[] paylineHitCounters = new int[34];
	int[] paylineHitPayouts = new int[34];
	int rtpLevel = 1;
	
	int start = 0;
	int finish = 0;
	
	int idP = 0;
	
	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

	public void setRTP(int rtpLevel) {
		this.rtpLevel = rtpLevel;
	}
	
	public void run() {
		/*
		int index = 0;
		for(WinPattern wp : MonarchSunConfig.PAY_TABLE.getWinPatterns()) {
			symbols[index] = wp.symbol();
			counts[index] = wp.count();
			index++;
		}
		symbols[33] = MonarchSunConfig.SYMBOL_SCATTER;
		counts[33] = 3;

		for(int i = 0; i < paylineHitCounters.length; i++) {
			paylineHitCounters[i]=0;
		}

		for(int j = 0; j < paylineHitPayouts.length; j++) {
			paylineHitPayouts[j]=0;
		}
		PatternMatcher matcher = DefaultPatternMatcher.createDefaultMatcher(MonarchSunConfig.PAY_TABLE, MonarchSunConfig.WILD_SYMBOL);
		long reel1Size = MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(1).size();
		long reel2Size = MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(2).size();
		long reel3Size = MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(3).size();
		long reel4Size = MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(4).size();
		long totalPermutations = reel1Size*reel2Size*reel3Size*reel4Size*(finish-start);
		long currentTested = 0;
		for(int a = 0; a < reel1Size; a++) {
			for(int b = 0; b < reel2Size; b++) {
				for(int c = 0; c < reel3Size; c++) {
					for(int d = 0; d < reel4Size; d++) {
						for(int e = start; e < finish; e++) {
							List<String> symbolsPattern = new ArrayList<String>();
							symbolsPattern.add(MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(1).get(a));
							symbolsPattern.add(MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(2).get(b));
							symbolsPattern.add(MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(3).get(c));
							symbolsPattern.add(MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(4).get(d));
							symbolsPattern.add(MonarchSunFreeSpinsReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(5).get(e));
							PatternPayout paylineResults = matcher.match(symbolsPattern);

							if(paylineResults.won() > 0) {
								for(int w = 0; w < 34; w++) {
									if(symbols[w] == paylineResults.winPattern().symbol() && counts[w] == paylineResults.winPattern().count()) {
										paylineHitCounters[w]++;
										paylineHitPayouts[w]+=paylineResults.won();
									}
								}
							}
							if((currentTested+1)%(totalPermutations/10)==0)
							{
								System.out.println("Thread #" + (idP+1) + " " + ((currentTested+1)/(totalPermutations/10))*10+"% Done...");
							}
							currentTested++;
						}
					}
				}
			}
		}*/
	}
}
