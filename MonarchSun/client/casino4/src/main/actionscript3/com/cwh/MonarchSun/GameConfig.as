/**
 * This class is used to set all game specific proerties.
 * All specifications which are required to load assets, determine column and row,their height-width,
 * win display type like way or line, line count, reel spinning related properties, betting parameters, help screen informations,
 * Symbols with their id and animations, win presentation related specification etc. 
 **/
package com.cwh.MonarchSun
{
	
	
	public class GameConfig
	{
		
		
		/**
		 * Creating a XML which will define the assets to be loaded.
		 **/
		public final function gameAssets () : XML
		{
			
			var gameAssetsXML : XML	=
				<GameAssets>
				
					<NormalLoading>
						<File url = "GameAssets.swf" type = "GameAssets"  />
						<File url = "OtherAssets.swf" type = "GameAssets" />
						<File url = "Information.swf" type = "GameAssets" />
						<File url = "BackgroundCollection.swf" type = "GameAssets" />
						<File url = "ButtonsPanel.swf" type = "GameAssets" />
						<File url = "SymbolCollection.swf" type = "GameAssets" />
						<File url = "Sound.swf" type="Sound" />
						<File url = "language.xml" type="language" />
					</NormalLoading>
				
				</GameAssets>	;
			
			return gameAssetsXML ;	
		}
		
		
		public function amayaConfig () : XML
		{
			var config	: XML	=
				<AmayaConfig>
					<DefaultStopSequence>
						<BaseGameStopSequence>
							<StopSequence>46,20,42,43,76</StopSequence>
							<StopSequence>17,27,55,3,26</StopSequence>
							<StopSequence>32,18,62,45,33</StopSequence>
							<StopSequence>49,1,12,10,34</StopSequence>
							<StopSequence>18,20,16,2,23</StopSequence>
							<StopSequence>14,34,35,14,4</StopSequence>
							<StopSequence>29,41,24,44,26</StopSequence>
							<StopSequence>3,37,14,44,18</StopSequence>
							<StopSequence>23,15,51,17,82</StopSequence>
						</BaseGameStopSequence>
					</DefaultStopSequence>
					<GameConsole>
						<LineBetMultiplierOptions>1,2,3,4,5,6,7,8,9,10,11,12,13,14,15</LineBetMultiplierOptions>
					</GameConsole>
				</AmayaConfig>
				
				return config ;
			}
		
		
		/**
		 * Creating a XML to define all the game properties,
		 * like no of rows & column, features etc.
		 **/
		public final function gameProperties () : XML
		{
			var configXml	: XML	=
				<GameConfig>
					<SlotDetails>
						<NoOfColumns>5</NoOfColumns>
						<NoOfRows>3</NoOfRows>
						<SymbolWidth>136</SymbolWidth>								<!-- SymbolWidth set the mask width to display complete reel with symbols -->
						<SymbolHeight>125.8</SymbolHeight>							<!-- SymbolHeight set the mask height and adjust the symols downwards one after another in a column -->
						<TotalSymbolsCount>13</TotalSymbolsCount>
					</SlotDetails>
					
					<!-- Payout Properties can be of a single type hence other node should be removed eg if your game is of cluster type then remove Paylines & Ways nodes -->
					<PayoutDetails>
						<PayoutType>paylines</PayoutType> <!-- Possible options are ways or paylines -->
						<PayLines>
							<PlayLinesType>variable</PlayLinesType> <!-- Possible options are fixed or variable -->
							<TotalPlayLinesCount>20</TotalPlayLinesCount>
						</PayLines>
					</PayoutDetails>
				
				
					<FreeGameSpinDetails>
						<MinimumSpinDuration>1.7</MinimumSpinDuration>  					<!-- Maximum will be time taken by server to respond -->
						<SpiningSpeed>
							<Start>0.2</Start>
							<Loop>2.8</Loop>
							<End>0.4</End>
						</SpiningSpeed>
						
						<SpinStartProperties>effect:back;easeParams:2</SpinStartProperties> 		<!-- Properties to configure filter details -->
						<SpinStopProperties>effect:back;easeParams:2</SpinStopProperties>
						<SpinFilterProperties></SpinFilterProperties>
				
						<StartIntervals>0.1, 0.1, 0.1, 0.1, 0.1</StartIntervals>
						<StopIntervals>0.1, 0.4, 0.7, 1, 1.3</StopIntervals>
					</FreeGameSpinDetails>
					<BaseGameSpinDetails>
						<MinimumSpinDuration>1.7</MinimumSpinDuration>  					<!-- Maximum will be time taken by server to respond -->
						<SpiningSpeed>
							<Start>0.2</Start>
							<Loop>2.8</Loop>
							<End>0.4</End>
						</SpiningSpeed>
						
						<SpinStartProperties>effect:back;easeParams:2</SpinStartProperties> 		<!-- Properties to configure filter details -->
						<SpinStopProperties>effect:back;easeParams:2</SpinStopProperties>
						<SpinFilterProperties>BlurFilter:1,1,1</SpinFilterProperties>
				
						<StartIntervals>0.1, 0.1, 0.1, 0.1, 0.1</StartIntervals>
						<StopIntervals>0.1, 0.4, 0.7, 1, 1.3</StopIntervals>
					</BaseGameSpinDetails>

				

					<Console>
						<BetIncremenrDecrement>
							<RunInCycle>false</RunInCycle> 
							<IncreaseInAClick>single</IncreaseInAClick> <!--  Possible Option single or continuous   -->
						</BetIncremenrDecrement>
						<LineIncremenrDecrement>
							<RunInCycle>true</RunInCycle> 
							<IncreaseInAClick>single</IncreaseInAClick> <!--  Possible Option single or continuous   -->
						</LineIncremenrDecrement>
				
						<BetMax>
							<SetToMaximumAllowedBet>true</SetToMaximumAllowedBet>
							<SetToMaximumAllowedLine>true</SetToMaximumAllowedLine>
							<PerformPlaceBet>true</PerformPlaceBet>
						</BetMax>
				
						<AutoPlay>
							<Configuration>
								<DefaultValue>5</DefaultValue>
								<MinimumAllowed>5</MinimumAllowed>
								<MaximumAllowed>100</MaximumAllowed>
								<OptionsAllowed>5,10,20,50,100</OptionsAllowed>
								<RunInCycle>false</RunInCycle>
							</Configuration>
						</AutoPlay>
				
					</Console>
				
					
					<HelpScreen>
						<Functionality>
							<StartWithFirstScreenOnGameComplete>false</StartWithFirstScreenOnGameComplete>
							<StartWithFirstScreenOnShow>true</StartWithFirstScreenOnShow>
							<RunInCycle>false</RunInCycle>
						</Functionality>
						<PaginationDisplay>  								</PaginationDisplay> <!--  -->
						<ScreenComponents>									
							<infoBG />		
							<HelpScreenList>			<!-- All info screens in their order to be displayed -->
								<paylinesInfo/>
								<payoutsInfo_1 />
								<payoutsInfo_2 />
								<payoutsInfo_3 />
								<freegameInfo />								
							</HelpScreenList>
							<infobuttonsPanel />
						</ScreenComponents>
					</HelpScreen>
					
				<SymbolCollection>     <!-- All symbols with their id as provided by API -->
				      <BaseGameSymbols>
							<SymbolWild7Red    	id="1" />
					       	<SymbolWild7Blue   	id="2" />
					       	<LogoSymbol      	id="3" />       
					       	<SymbolCoins    	id="4" />
					       	<SymbolCherries    	id="5" />
					       	<SymbolBell     	id="6" />
					       	<SymbolA     		id="7" />
					       	<SymbolK     		id="8" />
					       	<SymbolQ     		id="9"/>
					       	<SymbolJ     		id="10"/>
					       	<Symbol10     		id="11"/>
					       	<Symbol9     		id="12" />
					       	<ScatterSymbol    	id="13"/>
				      </BaseGameSymbols>
				      <FreeGameSymbols>
							<SymbolWild7Red    	id="1" />
							<SymbolWild7Blue   	id="2" />
					       	<LogoSymbol     	id="3" />       
					       	<SymbolCoins    	id="4" />
					       	<SymbolCherries    	id="5" />
					       	<SymbolBell     	id="6" />
					       	<SymbolA     		id="7" />
					       	<SymbolK     		id="8" />
					       	<SymbolQ     		id="9"/>
					       	<SymbolJ     		id="10"/>
					       	<Symbol10     		id="11"/>
					       	<Symbol9     		id="12" />
					       	<ScatterSymbol    	id="13"/>
				      </FreeGameSymbols>
				 	</SymbolCollection>

					<WinPresentationSequence>
						<FreeGameWinPresentation>
							<BigWinCelebration>
								<HugeWinCelebration>20</HugeWinCelebration>				
							</BigWinCelebration>
							<WinPresentation />
						</FreeGameWinPresentation>
						<BaseGameWinPresentation>
							<ShowAllWinPaylinesAndWinFrames duration="100" />
							<BigWinCelebration>
								<HugeWinCelebration>20</HugeWinCelebration>				
							</BigWinCelebration>
							<WinPresentation />
						</BaseGameWinPresentation>
					</WinPresentationSequence>
				
					<LineWinPresentationAnimation>					<!-- winanimation:: Generic win, substitute :: substituted win, trigger :: bunus trigger,  scatter :: scatter win-->
						<BaseGameLineWinAnimation>
							<Generic>
								<SymbolWild7Blue		winanimation="SymbolWild7BlueAnim" 		substitute="SymbolWild7BlueAnim" 	 	 		trigger="SymbolWild7BlueAnim"/>
								<SymbolWild7Red			winanimation="SymbolWild7RedAnim" 		substitute="SymbolWild7RedAnim" 				trigger="SymbolWild7BlueAnim"/>
								<SymbolCoins			winanimation="SymbolCoinsAnim" 		    substitute="SymbolCoinsAnim"				    trigger="SymbolWild7BlueAnim"/>
								<SymbolBell				winanimation="SymbolBellAnimation" 		substitute="SymbolBellAnimation" 			    trigger="SymbolWild7BlueAnim" />
								<SymbolCherries			winanimation="SymbolCherriesAnim" 		substitute="SymbolCherriesAnim" 				trigger="SymbolWild7BlueAnim"/>
								<LogoSymbol     		winanimation="LogoSymbolAnim"		    substitute="LogoSymbolAnim" 				    trigger="SymbolWild7BlueAnim"/>
								<SymbolA				winanimation="SymbolAWinAnim" 			substitute="SymbolAWinAnim" 	 				trigger="SymbolWild7BlueAnim"/>
								<SymbolK	 			winanimation="SymbolKWinAnim" 			substitute="SymbolKWinAnim" 	 				trigger="SymbolWild7BlueAnim"/>
								<Symbol9				winanimation="Symbol9WinAnim" 			substitute="Symbol9WinAnim" 		 			trigger="SymbolWild7BlueAnim"/>
								<SymbolQ				winanimation="SymbolQWinAnim" 			substitute="SymbolQWinAnim" 		 			trigger="SymbolWild7BlueAnim"/>					
								<SymbolJ				winanimation="SymbolJWinAnim" 			substitute="SymbolJWinAnim" 		 			trigger="SymbolWild7BlueAnim"/>
								<Symbol10				winanimation="Symbol10WinAnim" 			substitute="Symbol10WinAnim" 		 			trigger="SymbolWild7BlueAnim"/>
								<ScatterSymbol			winanimation="ScatterWinAnim"			substitute="ScatterWinAnim" 	 				trigger="ScatterWinAnim" 		scatter="ScatterWinAnim"/>								
							</Generic>				
						</BaseGameLineWinAnimation>
						<FreeGameLineWinAnimation>
							<Generic>
								<SymbolWild7Blue		winanimation="SymbolWild7BlueAnim" 		substitute="SymbolWild7BlueAnim" 	 			trigger="SymbolWild7BlueAnim"/>
								<SymbolWild7Red			winanimation="SymbolWild7RedAnim" 		substitute="SymbolWild7RedAnim" 				trigger="SymbolWild7BlueAnim"/>
								<SymbolCoins			winanimation="SymbolCoinsAnim" 		    substitute="SymbolCoinsAnim"					trigger="SymbolWild7BlueAnim"/>
								<SymbolBell				winanimation="SymbolBellAnimation" 		substitute="SymbolBellAnimation" 				trigger="SymbolWild7BlueAnim" />
								<SymbolCherries			winanimation="SymbolCherriesAnim" 		substitute="SymbolCherriesAnim" 				trigger="SymbolWild7BlueAnim"/>
								<LogoSymbol     		winanimation="LogoSymbolAnim"		    substitute="LogoSymbolAnim" 	 			    trigger="SymbolWild7BlueAnim"/>
								<SymbolA				winanimation="SymbolAWinAnim" 			substitute="SymbolAWinAnim" 	 				trigger="SymbolWild7BlueAnim"/>
								<SymbolK	 			winanimation="SymbolKWinAnim" 			substitute="SymbolKWinAnim" 	 				trigger="SymbolWild7BlueAnim"/>
								<Symbol9				winanimation="Symbol9WinAnim" 			substitute="Symbol9WinAnim" 		 			trigger="SymbolWild7BlueAnim"/>
								<SymbolQ				winanimation="SymbolQWinAnim" 			substitute="SymbolQWinAnim" 		 			trigger="SymbolWild7BlueAnim"/>					
								<SymbolJ				winanimation="SymbolJWinAnim" 			substitute="SymbolJWinAnim" 		 			trigger="SymbolWild7BlueAnim"/>
								<Symbol10				winanimation="Symbol10WinAnim" 			substitute="Symbol10WinAnim" 		 			trigger="SymbolWild7BlueAnim"/>
								<ScatterSymbol			winanimation="ScatterWinAnim"			substitute="ScatterWinAnim" 	 				trigger="ScatterWinAnim" 		scatter="ScatterWinAnim"/>								
							</Generic>				
						</FreeGameLineWinAnimation>
					</LineWinPresentationAnimation>
				
					<Anticipation>
						<BaseGameAnticipation/>
						<FreeGameAnticipation/>
					</Anticipation>
				
					<GameSound>
						<BaseGameSound>
							<ReelSpinification></ReelSpinification>
							<ReelSpinSound>MaingameReelSpinLoop</ReelSpinSound>       <!-- there are seven random sounds to be played -->
							<ReelStopSound>
								<Reel1>SndSpin_Stop</Reel1>
								<Reel2>SndSpin_Stop</Reel2>
								<Reel3>SndSpin_Stop</Reel3>
								<Reel4>SndSpin_Stop</Reel4>
								<Reel5>SndSpin_Stop</Reel5>
							</ReelStopSound>
							<Tickup>																					<!-- cout up start and end sound -->
								<Normal>WinStart</Normal>
								<NormalEnd>WinCoin</NormalEnd>
							</Tickup>
						</BaseGameSound>
						
						<FreeGameSound>
							<ReelSpinification>Spinification</ReelSpinification>
							<BackgroundSound>FreegameBG</BackgroundSound>
							<ReelSpinSound order="random">Spin2</ReelSpinSound>
							<ReelStopSound>
								<Reel1>Stop1</Reel1>
								<Reel2>Stop1</Reel2>
								<Reel3>Stop1</Reel3>
								<Reel4>Stop1</Reel4>
								<Reel5>Stop5</Reel5>
							</ReelStopSound>
							<Popup>
								<IntroPopupIn></IntroPopupIn>
								<IntroPopupOut></IntroPopupOut>
								<OutroPopupIn></OutroPopupIn>
								<OutroPopupOut>PopupInOut</OutroPopupOut>
							</Popup>
							<Tickup>
								<Normal>WinStart</Normal>
								<NormalEnd>WinCoin</NormalEnd>
							</Tickup>
						</FreeGameSound>
				
						<HelpScreenSound>
						</HelpScreenSound>
				
					</GameSound>
				
				</GameConfig> ;		
			
			return configXml ;
		}
	}
}