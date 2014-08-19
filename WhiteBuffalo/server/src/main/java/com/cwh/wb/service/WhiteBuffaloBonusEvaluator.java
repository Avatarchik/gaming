/**
 * Created with IntelliJ IDEA.
 * User: NCraig
 * Date: 8/23/13
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
package   com.cwh.wb.service;

import com.cwh.wb.WhiteBuffaloConfig.PowerXStreamPayline;
import com.cwh.wb.WhiteBuffaloConfig.PowerXStreamPaylineResult;
import   com.cwh.wb.WhiteBuffaloConfig.WhiteBuffaloConfig;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class WhiteBuffaloBonusEvaluator
{
	public static  PowerXStreamPaylineResult ScatterEvaluate(java.util.List<java.util.List<java.lang.String>> reelsDisplay, int freeSpins, String bonusSymbol, Logger logger)
	{
		for (int symbolCounter = 0; symbolCounter < reelsDisplay.get(1).size(); symbolCounter++)
		{
			String currentSymbol = reelsDisplay.get(1).get(symbolCounter);
			if(currentSymbol.equals(bonusSymbol)) {
				List<List<java.lang.Integer>> matchingPositions = new ArrayList<List<java.lang.Integer>>();

				//Look for bonus symbols in the center reels
				for (int reelCounter1 = 1; reelCounter1 < reelsDisplay.size()-1; reelCounter1++)
				{
					List<Integer> positions = getSymbolPositions(currentSymbol,reelsDisplay.get(reelCounter1));
					if(positions.size()>0) {
						matchingPositions.add(positions);
					} else {
						break;
					}
				}
				
				//If there are bonus symbols in the 3 center reels
				if(matchingPositions.size()==3) {
					int numSymbols = 0;
					int freeSpinsWon = freeSpins;
					//Calculation of total free spins
					for(int i = 0; i < matchingPositions.size(); i++) {
						int num = matchingPositions.get(i).size();
						numSymbols += num;
						freeSpinsWon *= num;						
					}
					
					if(freeSpinsWon > WhiteBuffaloConfig.MAX_FREE_SPINS_AWARDED) {
						freeSpinsWon = WhiteBuffaloConfig.MAX_FREE_SPINS_AWARDED;
					}
					//Return payline 0
					matchingPositions.add(0,new ArrayList<java.lang.Integer>());
					matchingPositions.add(new ArrayList<java.lang.Integer>());
					PowerXStreamPayline payline = new PowerXStreamPayline(WhiteBuffaloConfig.SCATTER_PAYLINENUMBER, matchingPositions);
					return PowerXStreamPaylineResult.newBonusWin(payline,
							freeSpinsWon,
							currentSymbol,
							numSymbols,
							payline.symbolsOnLine(reelsDisplay));
				}
			}
		}
		return null;
	}

	//Returns the list of positions where the symbol is on the reel
	public static List<Integer> getSymbolPositions(String symbol, List<String> reelDisplay) {
		List<Integer> positions = new ArrayList<Integer>();
		for (int i = 0; i < reelDisplay.size(); i++)
		{
			if(reelDisplay.get(i).equals(symbol)) {
				positions.add(i);
			}
		}
		return positions;
	}
}
