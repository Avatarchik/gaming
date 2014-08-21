package com.cwh.monarchsun.MonarchSunConfig;

import java.util.ArrayList;

public class PowerXStreamPayline {
	private int id;
	private java.util.List<java.util.List<java.lang.Integer>> positions;
	
	public PowerXStreamPayline(int id, java.util.List<java.util.List<java.lang.Integer>> positions) {
		this.id = id;
		this.positions = positions;
	}
	
	public int id() {
		return id;
	}
	
	public java.util.List<java.util.List<java.lang.Integer>> positions() {
		return positions;
	}
	
	public java.util.List<java.util.List<java.lang.String>> symbolsOnLine(java.util.List<java.util.List<java.lang.String>> reelsDisplay) {
		java.util.List<java.util.List<java.lang.String>> symbolsOnLine = new ArrayList<java.util.List<java.lang.String>>();
		for (int k = 0; k < positions.size(); k++)
		{
			symbolsOnLine.add(new ArrayList<java.lang.String>());
		}
		for (int i = 0; i < positions.size(); i++)
		{
			for (int j = 0; j < positions.get(i).size(); j++)
			{
				symbolsOnLine.get(i).add(reelsDisplay.get(i).get(positions.get(i).get(j)));
			}
		}
		return symbolsOnLine;
	}
}
