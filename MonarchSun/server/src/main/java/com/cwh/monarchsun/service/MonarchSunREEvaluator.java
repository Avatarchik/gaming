package com.cwh.monarchsun.service;

import java.util.List;

import com.cwh.monarchsun.MonarchSunConfig.MonarchSunConfig;

public class MonarchSunREEvaluator {
	//Replaces all the RE symbols on the symbolDisplay with the pattern found using the randomNumber on the reReels
	public static  List<List<String>> EvaluateRE(List<String> rePattern, List<List<String>> symbolDisplay) {
		for(int i = 0; i < symbolDisplay.size(); i++) {
			for(int j = 0; j < symbolDisplay.get(i).size(); j++) {
				if(symbolDisplay.get(i).get(j)== MonarchSunConfig.SYMBOL_RE) {
                    switch(i)
                    {
                        case 0:
                            symbolDisplay.get(i).set(j, rePattern.get(0));
                            break;
                        case 1:
                            symbolDisplay.get(i).set(j, rePattern.get(1));
                            break;
                        case 3:
                            symbolDisplay.get(i).set(j, rePattern.get(2));
                            break;
                        case 4:
                            symbolDisplay.get(i).set(j, rePattern.get(3));
                            break;
                    }
				}
			}
		}
		return symbolDisplay;
	}
}
