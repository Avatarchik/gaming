package com.cwh.test.monarchsun.prd;


import static junit.framework.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cwh.monarchsun.MonarchSunConfig.*;
import com.cwh.monarchsun.model.GameState;
import com.cwh.monarchsun.service.MonarchSunBonusEvaluator;
import com.cwh.monarchsun.service.MonarchSunEvaluator;
import org.junit.Ignore;
import org.junit.Test;

import com.cwh.slotstoolbox2.paylines.map.WinPattern;
import com.cwh.monarchsun.MonarchSunConfig.MonarchSunRTP;


public class PermutationTest {

	@Ignore	
	@Test
	public void RunPermutationTest() {
		long startTime = System.currentTimeMillis();
		String[] symbols = new String[46];
		int[] counts = new int[46];
		int[] paylineHitCounters = new int[46];
		int[] paylineHitPayouts = new int[46];

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

			int cores = Runtime.getRuntime().availableProcessors();
			int reel5Size = MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(levelInt)).get(5).size();
			PermutationThread[] threads = new PermutationThread[cores];
			for (int i = 0; i < threads.length; i++) {
				threads[i] = new PermutationThread();
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
		}
	}

}

class PermutationThread extends Thread {
	String[] symbols = new String[46];
	private MonarchSunPayTableConfig mainPayConfig = new MonarchSunPayTableConfig();
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

	int[] counts = new int[46];
	int[] paylineHitCounters = new int[46];
	int[] paylineHitPayouts = new int[46];
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
    @Ignore
	public void run() {

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

		for(int j = 0; j < paylineHitPayouts.length; j++) {
			paylineHitPayouts[j]=0;
		}

		long reel1Size = MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(1).size();
		long reel2Size = MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(2).size();
		long reel3Size = MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(3).size();
		long reel4Size = MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(4).size();
		long totalPermutations = reel1Size*reel2Size*reel3Size*reel4Size*(finish-start);
		long currentTested = 0;
		for(int a = 0; a < reel1Size; a++) {
			for(int b = 0; b < reel2Size; b++) {
				for(int c = 0; c < reel3Size; c++) {
					for(int d = 0; d < reel4Size; d++) {
						for(int e = start; e < finish; e++) {
							List<List<String>> symbolsPattern = new ArrayList<List<String>>();
							symbolsPattern.add(subListCircular(MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(1),a, a+4));
							symbolsPattern.add(subListCircular(MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(2),b, b+4));
							symbolsPattern.add(subListCircular(MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(3),c, c+4));
							symbolsPattern.add(subListCircular(MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(4),d, d+4));
							symbolsPattern.add(subListCircular(MonarchSunReelsConfig.getReels().get(MonarchSunRTP.rtp(rtpLevel)).get(5),e, e+4));

							List<PowerXStreamPaylineResult> paylineResults = MonarchSunEvaluator.EvaluateL2R(mainPayConfig, symbolsPattern, MonarchSunConfig.COST_TO_COVER, false, null);
							paylineResults.addAll(MonarchSunEvaluator.EvaluateR2L(mainPayConfig, symbolsPattern, MonarchSunConfig.COST_TO_COVER, null));

							//Evaluate scatter wins
							PowerXStreamPaylineResult scatterWin = MonarchSunBonusEvaluator.ScatterEvaluate(symbolsPattern, MonarchSunConfig.FREE_SPINS_AWARDED, MonarchSunConfig.SYMBOL_BN, null);
							//Add scatter win, if any, to results
							if (scatterWin != null)
							{
								paylineResults.add(scatterWin);
							}

							for (PowerXStreamPaylineResult result : paylineResults) {
								for(int w = 0; w < 45; w++) {
									if(symbols[w] == result.symbol() && counts[w] == result.numSymbols()) {
										paylineHitCounters[w]++;
										paylineHitPayouts[w]+=result.won();
									}
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
	}
	
	public List<String> subListCircular(List<String> list, int from, int to) {
		if(to > list.size()) {
			List<String> sublist = new ArrayList<String>();
			for(String symbol : list.subList(from, list.size())) {
				sublist.add(symbol);
			}
			for(int i = 0; i < to-list.size(); i++) {
				sublist.add(list.get(i));
			}
			return sublist;
		}
		return list.subList(from, to);
	}
}