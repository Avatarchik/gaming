package com.cwh.wb.model;

import java.text.DecimalFormat;

import com.cwh.wb.WhiteBuffaloConfig.WhiteBuffaloConfig;

import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;

public class ReplayGameResponse {
	private final int IMAGE_HEIGHT = 128;
	private final int IMAGE_WEIGHT = 96;
	private int totalFreeSpinWinnings = 0;
	private int totalMainGameWinnings = 0;
	private double creditValue = 0.0;
	private double creditWager = 0.0;
	
	private boolean isFreeGame = false;
	private int totalFreeSpins = 0;
	private int usedFreeSpins = 0;

	public String parseReplay(Node replay) {
		String replayHTML = "<table id='game_container' xmlns='http://www.w3.org/1999/xhtml'>";
		replayHTML += addReferences();
		replayHTML += addHeader(replay);
		replayHTML += addGames(replay);
		replayHTML += addResultSummary(replay);
		replayHTML += "</table>";
		return replayHTML;
	}

	private String addReferences() {
		return "<tr style='display: none'> <td><input type='hidden' name='css_path' value='/casino/remote/WhiteBuffalo/replay/styles/wb.css' /> <script language='javascript' src='/casino/remote/WhiteBuffalo/replay/javascript/jquery-1.7.2.min.js' type='text/javascript'> placeholder </script> <script language='javascript' src='/casino/remote/WhiteBuffalo/replay/javascript/utils.js' type='text/javascript'> placeholder </script> <script language='javascript' src='/casino/remote/WhiteBuffalo/replay/javascript/wb.js' type='text/javascript'> placeholder </script></td></tr>";
	}

	private String addHeader(Node replay) {
		String creditValueString = ((Element) replay)
				.getFirstChildElement("replay").getFirstChildElement("request")
				.getFirstChildElement("msgdata")
				.getFirstChildElement("coinValue").getValue();
		creditValue = Double.parseDouble(creditValueString);

		String creditWagerString = ((Element) replay)
				.getFirstChildElement("replay").getFirstChildElement("request")
				.getFirstChildElement("msgdata")
				.getFirstChildElement("creditWager").getValue();
		creditWager = Double.parseDouble(creditWagerString);

		return "<tr>" + "<td><div id='game_info' class='section'>"
		+ "<div class='header'>" + "<p class='label'>Game:</p>"
		+ "<p class='value'>White Buffalo</p>" + "</div>" + "<div>"
		+ "<p class='label'>Server Date/Time:</p>"
		+ "<p class='value'>"
		+ ((Element) replay).getAttributeValue("time")
		+ "</p>"
		+ "</div>"
		+ "<div>"
		+ "<p class='label'>Game Play ID:</p>"
		+ "<p class='value'>"
		+ ((Element) replay).getAttributeValue("nextGameNum")
		+ "</p>"
		+ "</div>"
		+ "<div>"
		+ "<p class='label'>Credit Value:</p>"
		+ "<p class='value'>"
		+ creditValueString
		+ "</p>"
		+ "</div>"
		+ "<div>"
		+ "<p class='label'>Bet:</p>"
		+ "<p class='value'>"
		+ creditWager
		+ "</p>"
		+ "</div>" + "</div></td>" + "</tr>";
	}

	private String addGames(Node replay) {
		String returnString = "";
		Elements replayGames = ((Element) replay).getChildElements("replay");
		int currentfreeGameWinnings = 0;
		for (int i = 0; i < replayGames.size(); i++) {
			returnString += "<tr><td>";
			// TITLE
			String gameType = "Main Game";
			isFreeGame = false;
			totalFreeSpins = Integer.parseInt(replayGames.get(i)
					.getFirstChildElement("response")
					.getFirstChildElement("msgdata")
					.getFirstChildElement("freeSpins").getAttribute("total")
					.getValue());
			usedFreeSpins = Integer.parseInt(replayGames.get(i)
					.getFirstChildElement("response")
					.getFirstChildElement("msgdata")
					.getFirstChildElement("freeSpins").getAttribute("used")
					.getValue());
			if (replayGames.get(i).getFirstChildElement("request")
					.getFirstChildElement("msgdata").getAttribute("reqName")
					.getValue().equals("FreeReq")) {
				gameType = "Free Game (" + usedFreeSpins + "/" + totalFreeSpins
						+ ")";
				isFreeGame = true;
			}
			returnString += "<div id='spin_" + i
					+ "' class='spin_div section'>" + "<div class='header'>"
					+ gameType + "</div>" + "<div class='panel'>"
					+ "<div class='reels'>";
			// Draw reels and symbols
			int [ ] [ ] reelDisplay = new int [ 4 ] [ 5 ] ;
			Elements reels = replayGames.get(i)
					.getFirstChildElement("response")
					.getFirstChildElement("msgdata")
					.getFirstChildElement("reels").getChildElements("reel");
			for (int j = 0; j < reels.size(); j++) {
				String iconsS = reels.get(j).getValue().toString();
				int k = 0;
				for (String iconID : iconsS.split(",")) {
					returnString += "<img class='row" + (k + 1) + " col"
							+ (j + 1) + " ' style='top: " + (k * IMAGE_WEIGHT + 10)
							+ "px; left: " + (j * IMAGE_HEIGHT + 10) + "px' "
							+ "src='/casino/remote/WhiteBuffalo/replay/images/"
							+ iconID.trim() + "_Symbol.png' " + "alt='"
							+ iconID.trim() + "_Symbol.png' />";
					reelDisplay[k][j]=Integer.parseInt(iconID.trim());
					k++;
				}
			}

			returnString += "</div>";
			returnString += "<div id='canvas_coins_won_spin_" + i
					+ "' class='canvas_coins_won'></div>";
			// Draw payline buttons
			/*for (int m = 0; m < WhiteBuffaloConfig.NUMBER_OF_LINES / 2; m++) {
				returnString += "<div id='payline_" + (m + 1)
						+ "' class='payline_button'" + "style='top: "
						+ (m * 20) + "px; left: 0px'>" + (m + 1) + "</div>";
			}
			for (int n = 0; n < WhiteBuffaloConfig.NUMBER_OF_LINES / 2; n++) {
				returnString += "<div id='payline_"
						+ (n + WhiteBuffaloConfig.NUMBER_OF_LINES / 2 + 1)
						+ "' class='payline_button'" + "style='top: "
						+ (n * 20) + "px; right: 0px'>"
						+ (n + WhiteBuffaloConfig.NUMBER_OF_LINES / 2 + 1)
						+ "</div>";
			}*/
			returnString += "</div>";
			// WINNINGS
			Elements winningLines = replayGames.get(i)
					.getFirstChildElement("response")
					.getFirstChildElement("msgdata")
					.getFirstChildElement("lines").getChildElements("line");
			if (winningLines.size() > 0) {
				String winningsString = "";
				String scatterWinningsString = "";
				int totalWinnings = 0;
				boolean scatterWin = false;
				for (int p = 0; p < winningLines.size(); p++) {
					String lineId = winningLines.get(p).getAttribute("id")
							.getValue().toString();
					int won = Integer.parseInt(winningLines.get(p)
							.getAttribute("won").getValue().toString());
					if (!lineId.equals("0")) {
						String postionsStr = winningLines.get(p).getAttribute("positions").getValue().toString();
						String[] postionsStrSep = postionsStr.split(";");
						String imgsStr = "<div style='float:left;width:auto;height:inherit;margin:0px;padding:0px'>";
						for(int q = 0; q < 5; q++) {
							boolean added = false;
							for(int r = 0; r < postionsStrSep.length && !added; r++) {
								String[] currentPosStr = postionsStrSep[r].split(",");
								int col = Integer.parseInt(currentPosStr[0]);
								if(col == q+1) {
									int row = Integer.parseInt(currentPosStr[1]);
									imgsStr += " <img id='main_winWay_1_r"+row+"c"+col+"' style='float:left;width:50px;height:inherit;margin:0px;padding:0px' src='/casino/remote/WhiteBuffalo/replay/images/" + reelDisplay[row-1][col-1] + "_Symbol.png' alt='"+ reelDisplay[row-1][col-1] + "_Symbol.png' />";
									added = true;
								}
							}
						}
						imgsStr += "</div>";
						winningsString += "<div id='payline_" + lineId
								+ "' class='winning_line_button'>" + imgsStr + "<p>" + "Won <span>" + won
								+ "</span>" + " Credits</p>" 
								+ "<input type='hidden' name='positions' "
								+ "value='"
								+ winningLines.get(p).getAttribute("positions")
								.getValue().toString() + "' />" + "</div>";
					} else {
						// Scatter Wins
						scatterWin = true;
						String positionsStr = winningLines.get(p).getAttribute("positions")
								.getValue().toString();
						String[] positionsSplit = positionsStr.split(";");
						int[] numInReel = new int[]{0,0,0};
						for(String s : positionsSplit) {
							numInReel[Integer.parseInt(s.split(",")[0])-2]++;
						}
						scatterWinningsString = "<div class='winning_lines'>"
								+ "<div class='title'>"
								+ "<p>Bonus:</p>"
								+ "</div>"
								+ "<div class='winning_line_button_fs'>"
								+"<div style='float:left;width:auto;height:inherit;margin:0px;padding:0px'>"
								+ " <img id='main_winWay_1_r2' style='float:left;width:50px;height:inherit;margin:0px;padding:0px' src='/casino/remote/WhiteBuffalo/replay/images/11_Symbol.png' alt='11_Symbol.png' />"
								+ " <img id='main_winWay_1_r3' style='float:left;width:50px;height:inherit;margin:0px;padding:0px' src='/casino/remote/WhiteBuffalo/replay/images/11_Symbol.png' alt='11_Symbol.png' />"
								+ " <img id='main_winWay_1_r4' style='float:left;width:50px;height:inherit;margin:0px;padding:0px' src='/casino/remote/WhiteBuffalo/replay/images/11_Symbol.png' alt='11_Symbol.png' />"
								+ "</div>"
								+ "<p>"
								+ "Won "+ numInReel[0]*numInReel[1]*numInReel[2]*WhiteBuffaloConfig.FREE_SPINS_AWARDED + " Free Games</p>"
								+ "<input type='hidden' name='positions' "
								+ "value='"
								+ positionsStr + "' />"
								+ "</div>" + "<div class='clear'></div>"
								+ "</div>";
					}
					totalWinnings += won;
				}
				if(winningLines.size() != 1 || !scatterWin) {
					returnString += "<div class='winning_lines'>";
					returnString += "<div class='title'>" + "<p>Winning Ways ("
							+ totalWinnings + " Credits Won):</p>" + "</div>";
					returnString += winningsString;
					returnString += "<div class='clear'></div>";
					returnString += "</div>";
				}
				returnString += scatterWinningsString;
				if (isFreeGame) {
					currentfreeGameWinnings += totalWinnings;
				} else {
					totalMainGameWinnings += totalWinnings;
				}
			}
			returnString += "</div>";

			// Free Game Summary
			//returnString += "</div>";
			returnString += "</td></tr>";
			if (isFreeGame && totalFreeSpins == usedFreeSpins) {
				returnString += "<tr>"
						+ "<td><div id='game_summary' class='section'>"
						+ "<div class='header'>" + "<p>Free Game Summary</p>"
						+ "</div>" + "<div>"
						+ "<p class='label'>Credits Won:</p>"
						+ "<p class='value'>" + currentfreeGameWinnings
						+ "</p>" + "</div>" + "<div>"
						+ "<p class='label'>Total Free Games:</p>"
						+ "<p class='value'>" + totalFreeSpins + "</p>"
						+ "</div>" + "</div></td>" + "</tr>";
				this.totalFreeSpinWinnings = currentfreeGameWinnings;
				currentfreeGameWinnings = 0;
			}
		}
		return returnString;
	}

	private String addResultSummary(Node replay) {
		int totalWon = totalMainGameWinnings + totalFreeSpinWinnings;
		DecimalFormat numberFormat = new DecimalFormat("#0.00");
		if (totalFreeSpins == usedFreeSpins) {
		return "<tr><td><div id='game_summary' class='section'>"
		+ "<div class='header'>" + "<p>Game Result Summary</p>"
		+ "</div>" + "<div class='space_after'>"
		+ "<p class='label'>Credits Wagered:</p>" + "<p class='value'>"
		+ (int) (creditWager * WhiteBuffaloConfig.COST_TO_COVER)
		+ "</p>"
		+ "</div>"
		+ "<div>"
		+ "<p class='label sub_item'>Main Game Won:</p>"
		+ "<p class='value sub_item'>"
		+ totalMainGameWinnings
		+ "</p>"
		+ "</div>"
		+ "<div>"
		+ "<p class='label sub_item'>Free Game Won:</p>"
		+ "<p class='value sub_item underline'>"
		+ totalFreeSpinWinnings
		+ "</p>"
		+ "</div>"
		+ "<div class='space_after'>"
		+ "<p class='label'>Credits Won:</p>"
		+ "<p class='value'>"
		+ totalWon
		+ "</p>"
		+ "</div>"
		+ "<div>"
		+ "<p class='label'>Total Wagered:</p>"
		+ "<p class='value'>"
		+ numberFormat.format(creditWager
		* creditValue
		* WhiteBuffaloConfig.COST_TO_COVER)
		+ "</p>"
		+ "</div>"
		+ "<div>"
		+ "<p class='label'>Total Won:</p>"
		+ "<p class='value'>"
		+ numberFormat.format(totalWon
		* creditValue)
		+ "</p>"
		+ "</div>"
		+ "<div>"
		+ "<p class='label'>Total Net:</p>"
		+ "<p class='value'>"
		+ numberFormat.format(((totalWon * creditValue) - (creditWager * creditValue * WhiteBuffaloConfig.COST_TO_COVER)))
				+ "</p>"
				+ "</div>"
				+ "</div></td></tr>";
		} else {
			return "<tr><td><div id='game_summary' class='section'>"
					+"<p>Game is still in progress...</p>"
					+ "</div></td></tr>";
		}
	}
}