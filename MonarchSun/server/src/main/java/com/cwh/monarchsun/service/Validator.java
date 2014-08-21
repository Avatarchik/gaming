package com.cwh.monarchsun.service;

import java.math.BigDecimal;

import com.cwh.monarchsun.MonarchSunConfig.MonarchSunConfig;
import com.cwh.monarchsun.model.GameState.PLAY_STATE;
import com.cwh.monarchsun.model.GameState;
import com.cwh.monarchsun.model.Profile;
import com.cwh.monarchsun.model.SpinGameRequest;

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
		return (new BigDecimal(profile.getParameter("maxBet").trim()).compareTo(new BigDecimal(spinGameRequest.getCreditWager()).multiply(spinGameRequest.getCoinValue()).multiply(new BigDecimal(MonarchSunConfig.COST_TO_COVER))) == 0)
				&& (!gameState.getPlayState().equals(GameState.PLAY_STATE.FREE));
	}
	
	public static boolean ValidateState(PLAY_STATE actualState, PLAY_STATE sentState) {
		return (actualState == sentState);
	}
}