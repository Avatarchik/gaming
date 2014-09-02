/**
 * Created with IntelliJ IDEA.
 * User: NCraig
 * Date: 8/23/13
 * Time: 8:58 AM
 * To change this template use File | Settings | File Templates.
 */
package   com.cwh.monarchsun.service;

import com.cwh.monarchsun.MonarchSunConfig.MonarchSunConfig;
import com.cwh.monarchsun.MonarchSunConfig.PowerXStreamPayline;
import com.cwh.monarchsun.MonarchSunConfig.PowerXStreamPaylineResult;

import java.util.ArrayList;
import java.util.List;

import com.cwh.monarchsun.model.GameState;
import org.apache.log4j.Logger;

public class MonarchSunBonusEvaluator
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
                    List<Integer> positions = getSymbolPositions(currentSymbol,reelsDisplay.get(reelCounter1), reelCounter1);
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
                        if(i==1)
                        {
                            switch (num)
                            {
                                case 1:
                                    freeSpinsWon = 10;
                                    break;
                                case 2:
                                    freeSpinsWon = 15;
                                    break;
                                case 3:
                                    freeSpinsWon = 20;
                                    break;
                            }
                            //freeSpinsWon *= num;
                        }

                    }

                    if(freeSpinsWon > MonarchSunConfig.MAX_FREE_SPINS_AWARDED) {
                        freeSpinsWon = MonarchSunConfig.MAX_FREE_SPINS_AWARDED;
                    }
                    //Return payline 0
                    matchingPositions.add(0,new ArrayList<java.lang.Integer>());
                    matchingPositions.add(new ArrayList<java.lang.Integer>());
                    PowerXStreamPayline payline = new PowerXStreamPayline(MonarchSunConfig.SCATTER_PAYLINENUMBER, matchingPositions);
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
	public static  PowerXStreamPaylineResult ScatterEvaluate2(java.util.List<java.util.List<java.lang.String>> reelsDisplay, GameState gameState, String bonusSymbol, Logger logger)
	{
        List<Integer> stickyWilds = gameState.getStickyWilds();
		for (int symbolCounter = 0; symbolCounter < reelsDisplay.get(1).size(); symbolCounter++)
		{
			String currentSymbol = reelsDisplay.get(1).get(symbolCounter);
			if(currentSymbol.equals(bonusSymbol)) {
				List<List<java.lang.Integer>> matchingPositions = new ArrayList<List<java.lang.Integer>>();

				//Look for bonus symbols in the center reels
				for (int reelCounter1 = 1; reelCounter1 < reelsDisplay.size()-1; reelCounter1++)
				{

					List<Integer> positions = getSymbolPositions(currentSymbol,reelsDisplay.get(reelCounter1), reelCounter1);
					if(positions.size()>0) {
						matchingPositions.add(positions);
                        if(reelCounter1==2)
                        {
                            //ONLY to calculate the how much free spins is going to get
                            if(!stickyWilds.contains(symbolCounter))
                            {
                                stickyWilds.add(symbolCounter);
                            }
                            gameState.setStickyWilds(stickyWilds);
                        }
					}
                    else {
                        break;
					}
				}

                /*if(matchingPositions.size()==3)
                {*/
                /*int WWin3rdReel = 0;

                for(int i = 0; i < reelsDisplay.get(1).size()-1; i++)
                {
                    if(reelsDisplay.get(2).get(i).equals(MonarchSunConfig.SYMBOL_WW))
                    {
                        WWin3rdReel++;
                    }
                }
                WWin3rdReel += gameState.getStickyWilds();
                if(WWin3rdReel > 3)
                {
                    WWin3rdReel = 3;
                }
                gameState.setStickyWilds(WWin3rdReel);*/
                /*--------------------------------------------------------------*/
                for(int i = 0; i < gameState.getStickyWilds().size(); i++)
                {
                    reelsDisplay.get(2).set(gameState.getStickyWilds().get(i),MonarchSunConfig.SYMBOL_WW);
                }
                /*if(gameState.getStickyWilds().size() > 0)
                {

                    int index = reelsDisplay.get(2).size()-2;//Second to last position
                    int count = gameState.getStickyWilds();
                    while(count > 0)
                    {
                        reelsDisplay.get(2).set(index,MonarchSunConfig.SYMBOL_WW);
                        index--;
                        count--;
                    }
                }*/
                /*--------------------------------------------------------------*/
                /*}*/
				//If there are bonus symbols in the 3 center reels
                if(matchingPositions.size()==3) {
                    int numSymbols = 0;
                    int freeSpinsWon = 0;
                    //Calculation of total free spins
                    for(int i = 0; i < matchingPositions.size(); i++) {
                        int num = matchingPositions.get(i).size();
                        numSymbols += num;
                        if(i==1)
                        {
                            switch (num)
                            {
                                case 1:
                                    freeSpinsWon = 10;
                                    break;
                                case 2:
                                    freeSpinsWon = 15;
                                    break;
                                case 3:
                                    freeSpinsWon = 20;
                                    break;
                            }
                            //freeSpinsWon *= num;
                        }

                    }
                    /*int freeSpinsWon = freeSpins;
					//Calculation of total free spins
					for(int i = 0; i < matchingPositions.size(); i++) {
						int num = matchingPositions.get(i).size();
						numSymbols += num;
						freeSpinsWon *= num;
					}

					if(freeSpinsWon > MonarchSunConfig.MAX_FREE_SPINS_AWARDED) {
						freeSpinsWon = MonarchSunConfig.MAX_FREE_SPINS_AWARDED;
					}*/
					//Return payline 0
					matchingPositions.add(0,new ArrayList<java.lang.Integer>());
					matchingPositions.add(new ArrayList<java.lang.Integer>());
					PowerXStreamPayline payline = new PowerXStreamPayline(MonarchSunConfig.SCATTER_PAYLINENUMBER, matchingPositions);
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
	public static List<Integer> getSymbolPositions(String symbol, List<String> reelDisplay, int reel) {
		List<Integer> positions = new ArrayList<Integer>();
        int size = reelDisplay.size();
        if(reel == 2)
        {
            size--;
        }
		for (int i = 0; i < size; i++)
		{
			if(reelDisplay.get(i).equals(symbol)) {
				positions.add(i);
			}
		}
		return positions;
	}
}
