package com.cwh.monarchsun.MonarchSunConfig;

import java.util.List;

public class PowerXStreamPaylineResult {
	private PowerXStreamPayline payline;
	private int won;
	private String symbol;
	private int numSymbols;
	private List<List<String>> symbolsOnPayline;
	
	private int freeSpinsWon;
	
	PowerXStreamPaylineResult(PowerXStreamPayline payline, int won, java.lang.String symbol, int numSymbols, java.util.List<java.util.List<java.lang.String>> symbolsOnPayline, int freeSpinsWon) {
		this.payline = payline;
		this.won = won;
		this.symbol = symbol;
		this.numSymbols = numSymbols;
		this.symbolsOnPayline = symbolsOnPayline;
		this.freeSpinsWon = freeSpinsWon;
	}
	
	public static PowerXStreamPaylineResult newWin(PowerXStreamPayline payline, int won, java.lang.String symbol, int numSymbols, java.util.List<java.util.List<java.lang.String>> symbolsOnPayline) {
		return new PowerXStreamPaylineResult(payline, won, symbol, numSymbols, symbolsOnPayline,0);
	}
	
	public static PowerXStreamPaylineResult newBonusWin(PowerXStreamPayline payline, int freeSpinsWon, java.lang.String symbol, int numSymbols, java.util.List<java.util.List<java.lang.String>> symbolsOnPayline) {
		return new PowerXStreamPaylineResult(payline, 0, symbol, numSymbols, symbolsOnPayline, freeSpinsWon);
	}
	
	public PowerXStreamPayline getPayline() {
		return payline;
	}
	
	public int won() {
		return won;
	}
	
	public int freeSpinsWon() {
		return freeSpinsWon;
	}
	
	public String symbol() {
		return symbol;
	}
	
	public int numSymbols() {
		return numSymbols;
	}
	
	public List<List<String>> symbolsOnPayline() {
		return symbolsOnPayline;
	}
}
