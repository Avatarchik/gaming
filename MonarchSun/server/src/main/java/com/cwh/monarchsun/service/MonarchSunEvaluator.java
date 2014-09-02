/**
 * Created with IntelliJ IDEA.
 * User: NCraig
 * Date: 8/23/13
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
package   com.cwh.monarchsun.service;

import com.cwh.monarchsun.MonarchSunConfig.MonarchSunConfig;
import com.cwh.monarchsun.MonarchSunConfig.MonarchSunPayTableConfig;
import com.cwh.monarchsun.model.GameState;
import com.cwh.slotstoolbox2.paylines.paytable.Paytable;
import com.cwh.monarchsun.MonarchSunConfig.PowerXStreamPayline;
import com.cwh.monarchsun.MonarchSunConfig.PowerXStreamPaylineResult;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class MonarchSunEvaluator
{
    public static List<List<String>> AddNReplaceStickyWilds(List<List<String>> reelsDisplay, GameState gameState)
    {
        //Add wilds on GameState if is not added on reel 2
        for(int i = 0; i < MonarchSunConfig.DISPLAY_HEIGHT-1; i++)
        {
            String currentSymbol = reelsDisplay.get(2).get(i);
            if(currentSymbol.equals(MonarchSunConfig.SYMBOL_WW))
            {
                if(!gameState.getStickyWilds().contains(i))
                {
                    gameState.getStickyWilds().add(i);
                }
            }
        }
        //Replace any symbol with WILD if is in StickYWilds on GameState
        for(int i = 0; i < gameState.getStickyWilds().size(); i++)
        {
            reelsDisplay.get(2).set(gameState.getStickyWilds().get(i),MonarchSunConfig.SYMBOL_WW);
        }
        return reelsDisplay;
    }
	public static  List<PowerXStreamPaylineResult> EvaluateL2R(MonarchSunPayTableConfig config,
			java.util.List<java.util.List<java.lang.String>> reelsDisplay,
			int bet,
			boolean isMaxBet,
			Logger logger) {
		List<PowerXStreamPaylineResult> wins = new ArrayList<PowerXStreamPaylineResult>();
		//For each symbol on the leftmost reel
		for (int symbolCounter = 0; symbolCounter < reelsDisplay.get(0).size(); symbolCounter++)
		{
			String currentSymbol = reelsDisplay.get(0).get(symbolCounter);
			int numSymbols = 0;
			if(!currentSymbol.equals(MonarchSunConfig.SYMBOL_WW)) {
				//Find the positions of the symbol on all the reels starting by the leftmost
				List<List<java.lang.Integer>> matchingPositions = new ArrayList<List<java.lang.Integer>>();

				for (int reelCounter1 = 0; reelCounter1 < reelsDisplay.size(); reelCounter1++)
				{
					List<Integer> positions = getSymbolPositions(currentSymbol,reelsDisplay.get(reelCounter1),reelCounter1);
					if(positions.size()>0) {
						matchingPositions.add(positions);
						numSymbols++;
					} else {
						break;
					}
				}

				//If there are instances of the symbol on at least 3 consecutive reels starting from the leftmost reel
				if(matchingPositions.size()>2) {
					int won = bet/ MonarchSunConfig.COST_TO_COVER;
					//Calculate the payout
					for(int i = 0; i < matchingPositions.size(); i++) {
						int num = matchingPositions.get(i).size();
						won *= num;
					}
					for(int j = 0; j < 5-matchingPositions.size(); j++) {
						matchingPositions.add(new ArrayList<java.lang.Integer>());
					}
					//Return the winning line
					if(!checkIfAdded(wins,config.paylinesL2R().get(currentSymbol))) {
						won*= MonarchSunConfig.PAY_TABLE_L2R.getPayout(currentSymbol, numSymbols).payout();
						PowerXStreamPayline payline = new PowerXStreamPayline(config.paylinesL2R().get(currentSymbol), matchingPositions);

						//Check if it is a jackpot win and change the pay accordingly
						/*if(isMaxBet && currentSymbol == MonarchSunConfig.SYMBOL_WW) {
							won += bet/ MonarchSunConfig.COST_TO_COVER*checkJackpot(won, numSymbols, MonarchSunConfig.JACKPOT_PAYTABLE, payline.symbolsOnLine(reelsDisplay));
						}*/
						wins.add(PowerXStreamPaylineResult.newWin(payline,
								won,
								currentSymbol,
								numSymbols,
								payline.symbolsOnLine(reelsDisplay)));
					}
				}
			}
		}
		return wins;
	}

	//If there is a jackpot win, returns either the jackpot pay, else returns the current 0	
	private static int checkJackpot(int won, int numSymbols, Paytable jackpotPaytable, List<List<java.lang.String>> symbols) {
		//Check that there are symbols on all reels
		if(numSymbols != MonarchSunConfig.NUM_REELS) {
			return 0;
		}
		//Check there are no wild symbols, just LOGOs
		for(List<java.lang.String> reel : symbols) {
			if (!reel.contains(MonarchSunConfig.SYMBOL_WW)) {
				return 0;
			}
		}
		int win = jackpotPaytable.getPayoutAmount(MonarchSunConfig.SYMBOL_WW, numSymbols) - MonarchSunConfig.PAY_TABLE_L2R.getPayout(MonarchSunConfig.SYMBOL_WW, 5).payout();
		return win;
	}

	//Checks if the payline is already added to the wins array
	private static boolean checkIfAdded(List<PowerXStreamPaylineResult> wins,
			Integer id) {
		for(PowerXStreamPaylineResult result : wins) {
			if(result.getPayline().id() == id) {
				return true;
			}
		}
		return false;
	}

	public static  List<PowerXStreamPaylineResult> EvaluateR2L(MonarchSunPayTableConfig config,
			java.util.List<java.util.List<java.lang.String>> reelsDisplay,
			int bet,
			Logger logger) {
		List<PowerXStreamPaylineResult> wins = new ArrayList<PowerXStreamPaylineResult>();
		//For each symbol on the rightmost reel
		for (int symbolCounter = 0; symbolCounter < reelsDisplay.get(reelsDisplay.size()-1).size(); symbolCounter++)
		{
			String currentSymbol = reelsDisplay.get(reelsDisplay.size()-1).get(symbolCounter);
			int numSymbols = 0;
			if(!currentSymbol.equals(MonarchSunConfig.SYMBOL_WW)) {
				//Find the positions of the symbol on all the reels starting by the rightmost
				List<List<java.lang.Integer>> matchingPositions = new ArrayList<List<java.lang.Integer>>();

				for (int reelCounter1 = reelsDisplay.size()-1; reelCounter1 > 0; reelCounter1--)
				{
					List<Integer> positions = getSymbolPositions(currentSymbol,reelsDisplay.get(reelCounter1),reelCounter1);
					if(positions.size()>0) {
						matchingPositions.add(positions);
						numSymbols++;
					} else {
						break;
					}
				}

				//If there are instances of the symbol on at least 3 consecutive reels starting from the rightmost reel
				if(matchingPositions.size()>2) {
					//Calculate the payout
					int won = bet/ MonarchSunConfig.COST_TO_COVER;
					for(int i = 0; i < matchingPositions.size(); i++) {
						int num = matchingPositions.get(i).size();
						won *= num;						
					}
					for(int j = 0; j <= 5-matchingPositions.size(); j++) {
						matchingPositions.add(new ArrayList<java.lang.Integer>());
					}
					//Return the winning line
					if(!checkIfAdded(wins,config.paylinesR2L().get(currentSymbol))) {
						//Check that the winning line doesn't have symbols on the 5 reels (because the winning payline of 5 symbols is just payed left to right)
						if(!(numSymbols==4 && getSymbolPositions(currentSymbol, reelsDisplay.get(0), 0).size()>0)) {
							won*= MonarchSunConfig.PAY_TABLE_R2L.getPayout(currentSymbol, numSymbols).payout();
							matchingPositions = revert(matchingPositions);
							PowerXStreamPayline payline = new PowerXStreamPayline(config.paylinesR2L().get(currentSymbol), matchingPositions);
							wins.add(PowerXStreamPaylineResult.newWin(payline,
									won,
									currentSymbol,
									numSymbols,
									payline.symbolsOnLine(reelsDisplay)));
						}
					}
				}
			}
		}
		return wins;
	}

	//Returns the list of positions where the symbol is on the reel
	public static List<Integer> getSymbolPositions(String symbol, List<String> reelDisplay, int reelIndex) {
		List<Integer> positions = new ArrayList<Integer>();
		int stopIndex = reelDisplay.size();
        if(reelIndex == 2)
        {
            stopIndex--;
        }
		for (int i = 0; i < stopIndex; i++)
		{
			if(reelDisplay.get(i).equals(symbol) || reelDisplay.get(i).equals(MonarchSunConfig.SYMBOL_WW) || reelDisplay.get(i).equals(MonarchSunConfig.SYMBOL_WW)) {
				positions.add(i);
			}
		}
		return positions;
	}

	//Reverts array of arrays of integers
	public static List<List<java.lang.Integer>> revert(List<List<java.lang.Integer>> array) {
		List<List<java.lang.Integer>> newArray = new ArrayList<List<java.lang.Integer>>();
		for(int i = array.size()-1; i >= 0; i--) {
			newArray.add(array.get(i));
		}
		return newArray;
	}
}
