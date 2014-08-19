package com.cwh.wb.service;

import java.util.List;

import com.cwh.wb.WhiteBuffaloConfig.REReelsConfig;
import com.cwh.wb.WhiteBuffaloConfig.WhiteBuffaloConfig;

public class WhiteBuffaloREEvaluator {
	//Replaces all the RE symbols on the symbolDisplay with the pattern found using the randomNumber on the reReels
	public static  List<List<String>> EvaluateRE(List<String> rePattern, List<List<String>> symbolDisplay) {
		for(int i = 1; i < symbolDisplay.size()-1; i++) {
			for(int j = 0; j < symbolDisplay.get(i).size(); j++) {
				if(symbolDisplay.get(i).get(j)==WhiteBuffaloConfig.SYMBOL_RE) {
					symbolDisplay.get(i).set(j, rePattern.get(i-1));
				}
			}
		}
		return symbolDisplay;
	}
}
