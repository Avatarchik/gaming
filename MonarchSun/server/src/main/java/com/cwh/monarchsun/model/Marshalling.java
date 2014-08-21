/**
 * Created with IntelliJ IDEA.
 * User: NCraig
 * Date: 8/20/13
 * Time: 9:45 AM
 * To change this template use File | Settings | File Templates.
 */

package   com.cwh.monarchsun.model;

import com.cwh.monarchsun.MonarchSunConfig.*;
import com.cwh.monarchsun.MonarchSunConfig.MonarchSunRTP;
import com.cwh.slotstoolbox2.reels.Reel;
import com.cwh.slotstoolbox2.reels.Reels;

import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Node;
import nu.xom.Nodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Marshalling
{
	public static Node PaylineResultMarshal (PowerXStreamPaylineResult paylineResult)
	{
		Element ePayline = new Element("line");
		ePayline.addAttribute(new Attribute("id",        String.valueOf(paylineResult.getPayline().id())));
		ePayline.addAttribute(new Attribute("won",       String.valueOf(paylineResult.won())));
		ePayline.addAttribute(new Attribute("icon", MonarchSunConfig.SYMBOL_MAP.get(paylineResult.symbol())));

		//Generate Positions String;
		String positions = new String();
		for (int posCounter = 0; posCounter < paylineResult.getPayline().positions().size(); posCounter++ )
		{
			for (int posCounter1 = 0; posCounter1 < paylineResult.getPayline().positions().get(posCounter).size(); posCounter1++ )
			{
				positions += (posCounter+1) + "," + (paylineResult.getPayline().positions().get(posCounter).get(posCounter1) + 1);
				if(posCounter1 + 1 < paylineResult.getPayline().positions().get(posCounter).size()) {
						positions += ";";
				}
			}
			if (paylineResult.getPayline().positions().get(posCounter).size() > 0 && posCounter + 1 < paylineResult.getPayline().positions().size() && paylineResult.getPayline().positions().get(posCounter+1).size() > 0)
	        {
	              positions += ";";
	        }
		}

		ePayline.addAttribute(new Attribute("positions", positions));
		return ePayline;
	}

	public static PowerXStreamPaylineResult PaylineResultUnMarshal (Node ePaylineResults)
	{
		Integer paylineId  = new Integer(((Element)ePaylineResults).getAttributeValue("id"));
		Integer creditsWon = new Integer(((Element)ePaylineResults).getAttributeValue("won"));
		String iconWon    = ((Element)ePaylineResults).getAttributeValue("icon");
		String positions  = ((Element)ePaylineResults).getAttributeValue("positions");
		List<List<String>> posList = new ArrayList<List<String>>();
		List<List<Integer>> posListInteger = new ArrayList<List<Integer>>();
		
		for(int i = 0; i < MonarchSunConfig.NUM_REELS; i++) {
			posList.add(new ArrayList<String>());
			posListInteger.add(new ArrayList<Integer>());
		}

		List<String> listSymbolsToMatch =  Arrays.asList(positions.split(";"));
		for (String pos : listSymbolsToMatch)
		{
			List<String> posStrings =  Arrays.asList(pos.split(","));
			Integer reelId = Integer.valueOf(posStrings.get(0));
			posList.get(reelId-1).add(MonarchSunConfig.REVERSE_MAP.get(posStrings.get(1)));
			posListInteger.get(reelId-1).add(Integer.valueOf(posStrings.get(1)));
		}

		PowerXStreamPayline newPayline = new PowerXStreamPayline(paylineId, posListInteger);
		PowerXStreamPaylineResult rv = PowerXStreamPaylineResult.newWin(newPayline,
				creditsWon,
				MonarchSunConfig.REVERSE_MAP.get(iconWon),
				posListInteger.size(),
				posList);
		return rv;
	}

	public static void ReelsMarshal(Reels eReels, Element eReelsNode)
	{
		List<List<String>> reelDisplay = eReels.getReelsDisplay();
		for (int reelCount = 0; reelCount < reelDisplay.size(); reelCount++)
		{
			Element reel = new Element("reel");
			reel.addAttribute(new Attribute("id",  String.valueOf(reelCount + 1)));
			reel.addAttribute(new Attribute("pos", String.valueOf(eReels.getStopPositions().get(reelCount))));
			int stopIndex = reelDisplay.get(reelCount).size();
            //if(reelCount == 0 || reelCount == MonarchSunConfig.NUM_REELS-1) {
            if(reelCount == 2) {
				stopIndex--;
			}
			for (int symbolCount = 0; symbolCount < stopIndex; symbolCount++)
			{
				reel.appendChild(MonarchSunConfig.SYMBOL_MAP.get(eReels.getReelsDisplay().get(reelCount).get(symbolCount)));
				if (symbolCount + 1 < stopIndex)
				{
					reel.appendChild(",");
				}
			}
			eReelsNode.appendChild(reel);
		}
	}
	
	public static void ReelsMarshal(List<List<String>> reelDisplay, List<Integer> stopPositions, Element eReelsNode)
	{
		for (int reelCount = 0; reelCount < reelDisplay.size(); reelCount++)
		{
			Element reel = new Element("reel");
			reel.addAttribute(new Attribute("id",  String.valueOf(reelCount + 1)));
			reel.addAttribute(new Attribute("pos", String.valueOf(stopPositions.get(reelCount))));
			int stopIndex = reelDisplay.get(reelCount).size();
			//if(reelCount == 0 || reelCount == MonarchSunConfig.NUM_REELS-1) {
            if(reelCount == 2) {
				stopIndex--;
			}
			for (int symbolCount = 0; symbolCount < stopIndex; symbolCount++)
			{
				reel.appendChild(MonarchSunConfig.SYMBOL_MAP.get(reelDisplay.get(reelCount).get(symbolCount)));
				if (symbolCount + 1 < stopIndex)
				{
					reel.appendChild(",");
				}
			}
			eReelsNode.appendChild(reel);
		}
	}
	
	public static List<String> ReReplacementUnMarshal(Nodes nReReplacement) {
		List<String> reReplacement = new ArrayList<String>();
		List<String> stopsStringList =  Arrays.asList(nReReplacement.get(0).getValue().split(","));
		for (int i = 0; i < stopsStringList.size(); i++)
		{
			reReplacement.add(MonarchSunConfig.REVERSE_MAP.get(stopsStringList.get(i)));
		}
		return reReplacement;
	}
	
	public static void ReReplacementMarshal(List<String> rePattern, Element eReelsNode) {
		Element rePatternE = new Element("rePattern");
		for (int i = 0; i < rePattern.size(); i++)
		{
			rePatternE.appendChild(MonarchSunConfig.SYMBOL_MAP.get(rePattern.get(i)));
			if (i + 1 < rePattern.size())
			{
				rePatternE.appendChild(",");
			}
		}
		eReelsNode.appendChild(rePatternE);
	}

	public static Reels ReelsUnMarshal(Nodes nReels)
	{
		List<Reel> reelList = new ArrayList<Reel>();
		for(int i = 0; i < nReels.size(); i++)
		{
			Integer reelNum = new Integer(((Element)nReels.get(i)).getAttribute("id").getValue());
			Integer reelPos = new Integer(((Element)nReels.get(i)).getAttribute("pos").getValue());

			String symbolsString = ((Element)nReels.get(i)).getValue();
			List<String> symbolsStringList =  Arrays.asList(symbolsString.split(","));

			Reel newReel = new Reel(symbolsStringList, symbolsStringList.size(), reelPos);
			reelList.add(newReel);
		}
		return new Reels(reelList);
	}

	public static void ReelsMapMarshal(Map<Object, Map<Integer, List<String>>> map, Element eReels) {
		for (int level = 1; level < MonarchSunRTP.values().length+1; level++) {
			Element rtp = new Element("rtp");
			rtp.addAttribute(new Attribute("level",  String.valueOf(level)));

			for (int reelCount = 0; reelCount < MonarchSunConfig.NUM_REELS; reelCount++)
			{
				Element reel = new Element("reel");
				reel.addAttribute(new Attribute("id",  String.valueOf(reelCount + 1)));
				List<String> reelSymbols = map.get(MonarchSunRTP.rtp(level)).get(reelCount + 1);
				for (int symbolCount = 0; symbolCount < reelSymbols.size(); symbolCount++)
				{
					reel.appendChild(MonarchSunConfig.SYMBOL_MAP.get(reelSymbols.get(symbolCount)));
					if (symbolCount + 1 < reelSymbols.size())
					{
						reel.appendChild(",");
					}
				}
				rtp.appendChild(reel);
			}
			eReels.appendChild(rtp);
		}
	}
	
	public static void ReelsMapMarshal(Map<Object, Map<Integer, List<String>>> map, Element eReels, int rtpLevel) {
		for (int reelCount = 0; reelCount < MonarchSunConfig.NUM_REELS; reelCount++)
		{
			Element reel = new Element("reel");
			reel.addAttribute(new Attribute("id",  String.valueOf(reelCount + 1)));
			List<String> reelSymbols = map.get(MonarchSunRTP.rtp(rtpLevel)).get(reelCount + 1);
			for (int symbolCount = 0; symbolCount < reelSymbols.size(); symbolCount++)
			{
				reel.appendChild(MonarchSunConfig.SYMBOL_MAP.get(reelSymbols.get(symbolCount)));
				if (symbolCount + 1 < reelSymbols.size())
				{
					reel.appendChild(",");
				}
			}
			eReels.appendChild(reel);
		}
	}

	public static void InitStopsMarshal(Element eInitStops, int rtpLevel) {
		int randomNumber = (int) (Math.random()*5);
		for (int i = 0; i < MonarchSunConfig.NUM_REELS; i++)
		{
			eInitStops.appendChild(MonarchSunReelsConfig.getInitStops(MonarchSunRTP.rtp(rtpLevel)).get(randomNumber).get(i).toString());
			if (i + 1 < MonarchSunConfig.NUM_REELS)
			{
				eInitStops.appendChild(",");
			}
		}
	}
	
	public static void ReelsMarshalResume(Reels eReels, List<List<String>> eReelsList, Element eReelsNode)
	{
		List<List<String>> reelDisplay = eReelsList;
		for (int reelCount = 0; reelCount < reelDisplay.size(); reelCount++)
		{
			Element reel = new Element("reel");
			reel.addAttribute(new Attribute("id",  String.valueOf(reelCount + 1)));
			reel.addAttribute(new Attribute("pos", String.valueOf(eReels.getStopPositions().get(reelCount) )));

			for (int symbolCount = 0; symbolCount < reelDisplay.get(reelCount).size(); symbolCount++)
			{
				reel.appendChild(reelDisplay.get(reelCount).get(symbolCount));
				if (symbolCount + 1 < reelDisplay.get(reelCount).size())
				{
					reel.appendChild(",");
				}
			}
			eReelsNode.appendChild(reel);
		}
	}
	
	public static List<List<String>> ReelsUnMarshalAsList(Nodes nReels)
	{
		List<List<String>> reelList = new ArrayList<List<String>>();
		for(int i = 0; i < nReels.size(); i++)
		{
			Integer reelNum = new Integer(((Element)nReels.get(i)).getAttribute("id").getValue());
			Integer reelPos = new Integer(((Element)nReels.get(i)).getAttribute("pos").getValue());

			String symbolsString = ((Element)nReels.get(i)).getValue();
			List<String> symbolsStringList =  Arrays.asList(symbolsString.split(","));

			reelList.add(symbolsStringList);
		}
		return reelList;
	}
	
	public static List<Integer> BaseGameStopsUnMarshal(Nodes nBaseGameStops) {
		List<Integer> baseGameStops = new ArrayList<Integer>();
		List<String> stopsStringList =  Arrays.asList(nBaseGameStops.get(0).getValue().split(","));
		for (int i = 0; i < stopsStringList.size(); i++)
		{
			baseGameStops.add(Integer.parseInt(stopsStringList.get(i)));
		}
		return baseGameStops;
	}

	public static void BaseGameStopsMarshal(List<Integer> baseGameStops,
			Element eBaseGameStops) {
		for (int i = 0; i < MonarchSunConfig.NUM_REELS; i++)
		{
			eBaseGameStops.appendChild(baseGameStops.get(i).toString());
			if (i + 1 < MonarchSunConfig.NUM_REELS)
			{
				eBaseGameStops.appendChild(",");
			}
		}
	}

    public  static void StickyWildsMarshal(List<Integer> stickyWildsPositions,
                                           Element eBaseStickyWilds){
        if(stickyWildsPositions.size()>0){
            for (int i = 0; i < MonarchSunConfig.NUM_REELS-1; i++)
            {
                eBaseStickyWilds.appendChild(stickyWildsPositions.get(i).toString());
                if (i + 1 < MonarchSunConfig.NUM_REELS)
                {
                    eBaseStickyWilds.appendChild(",");
                }
            }
        }

    }
    public static List<Integer> StickyWildsUnMarshal(Nodes nStickyWildsPositions){
        List<Integer> stickyWilds = new ArrayList<Integer>();
        if(nStickyWildsPositions.size() > 0)
        {
            List<String> stickyWildsList =  Arrays.asList(nStickyWildsPositions.get(0).getValue().split(","));
            for (int i = 0; i < stickyWildsList.size(); i++)
            {
                stickyWilds.add(Integer.parseInt(stickyWildsList.get(i)));
            }
        }
        return stickyWilds;
    }
}
