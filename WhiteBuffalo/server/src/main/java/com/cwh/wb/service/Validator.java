package com.cwh.wb.service;

import java.math.BigDecimal;

import com.cwh.wb.WhiteBuffaloConfig.WhiteBuffaloConfig;
import com.cwh.wb.model.GameState.PLAY_STATE;
import com.cwh.wb.model.GameState;
import com.cwh.wb.model.Profile;
import com.cwh.wb.model.SpinGameRequest;

public class Validator {
	public static boolean ValidateBet(Profile profile, SpinGameRequest spinGameRequest, GameState gameState) {
		boolean coinValueExists = false;
		for(String coinValue : profile.getParameter("coinValue").split(",")) {
			BigDecimal cv = new BigDecimal(coinValue.trim());
			BigDecimal cvparameter = new BigDecimal(spinGameRequest.getCoinValue().toString().trim());
			if(cv.compareTo(cvparameter) == 0) {
				coinValueExists = true;
				break;
			}
		}
		return coinValueExists;
	}
	
	public static boolean ValidateMaxBet(Profile profile, SpinGameRequest spinGameRequest, GameState gameState) {
		return (new BigDecimal(profile.getParameter("maxBet").trim()).compareTo(new BigDecimal(spinGameRequest.getCreditWager()).multiply(new BigDecimal(WhiteBuffaloConfig.COST_TO_COVER))) == 0) 
				&& (!gameState.getPlayState().equals(GameState.PLAY_STATE.FREE));
	}
	
	public static boolean ValidateState(PLAY_STATE actualState, PLAY_STATE sentState) {
		return (actualState == sentState);
	}
}