package com.cwh.WhiteBuffalo.register
{
	
	
	import com.cwh.WhiteBuffalo.GameScreens;
	import com.cwh.WhiteBuffalo.ui.mediator.freegame.MediatorFreeGameCounterWhiteBuffalo;
	import com.cwh.WhiteBuffalo.ui.mediator.freegame.MediatorFreegameIntroPopupWhiteBuffalo;
	import com.cwh.WhiteBuffalo.ui.mediator.freegame.MediatorFreegameOutroPopupWhiteBuffalo;
	import com.cwh.WhiteBuffalo.ui.mediator.meter.MediatorBetLineMeterWhiteBuffalo;
	import com.cwh.WhiteBuffalo.ui.view.freegame.ViewFreeGameCounterWhiteBuffalo;
	import com.cwh.WhiteBuffalo.ui.view.freegame.ViewFreeGameIntroPopupWhiteBuffalo;
	
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	
	import ingenuity.amaya.ui.mediator.autoplay.MediatorAutoplayGenericControlsCJ;
	import ingenuity.amaya.ui.mediator.console.MediatorGameBalanceCJ;
	import ingenuity.amaya.ui.mediator.key.MediatorKeyCJ;
	import ingenuity.amaya.ui.mediator.lines.MediatorLineIdentifiersCJ;
	import ingenuity.amaya.ui.mediator.lines.MediatorSelectedPaylinesCJ;
	import ingenuity.amaya.ui.mediator.lines.MediatorShowSelectedPaylinesCJ;
	import ingenuity.amaya.ui.mediator.menu.MediatorMainMenuCJ;
	import ingenuity.amaya.ui.mediator.menu.MediatorSettingMenuCJ;
	import ingenuity.amaya.ui.mediator.messages.MediatorBaseGamePlayerMessage;
	import ingenuity.amaya.ui.mediator.messages.MediatorBaseGamePromoMessage;
	import ingenuity.amaya.ui.mediator.messages.MediatorFreeGamePlayerMessage;
	import ingenuity.amaya.ui.mediator.messages.MediatorFreeGamePromoMessage;
	import ingenuity.amaya.ui.mediator.messages.MediatorFreeGameTriggerMessage;
	import ingenuity.amaya.ui.mediator.meter.MediatorDenominationMeterCJ;
	import ingenuity.amaya.ui.mediator.meter.MediatorFreeGameWinMeterCJ;
	import ingenuity.amaya.ui.mediator.meter.MediatorTotalBetMeterCJ;
	import ingenuity.amaya.ui.mediator.meter.MediatorTotalWinMeterCJ;
	import ingenuity.amaya.ui.mediator.win.basegame.others.MediatorBaseGameBigWinCelebrationCJ;
	import ingenuity.amaya.ui.mediator.win.basegame.others.MediatorFreeGameBigWinCelebrationCJ;
	import ingenuity.amaya.ui.mediator.win.basegame.others.MediatorFreeGameEndBigWinCelebrationCJ;
	import ingenuity.amaya.ui.view.autoplay.ViewAutoplayCounterCJ;
	import ingenuity.amaya.ui.view.autoplay.ViewAutoplayGenericControlsCJ;
	import ingenuity.amaya.ui.view.console.ViewGameBalanceCJ;
	import ingenuity.amaya.ui.view.menu.ViewMainMenuCJ;
	import ingenuity.amaya.ui.view.menu.ViewSettingMenuCJ;
	import ingenuity.amaya.ui.view.messages.ViewPlayerMessage;
	import ingenuity.amaya.ui.view.messages.ViewPromoMessage;
	import ingenuity.amaya.ui.view.meter.ViewFreeGameWinMeterCJ;
	import ingenuity.amaya.ui.view.meter.ViewTotalWinMeterCJ;
	import ingenuity.amaya.ui.view.popup.ViewFreeGameOutroPopupCJ;
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.media.graphics.ComponentLibrary;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyAutoplay;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyBetPerLine;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyConsole;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyFeatureUI;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyGrid;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyHelpScreen;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyMessage;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyReels;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyWinPresentation;
	import ingenuity.casino.slotgame.ui.mediator.autoplay.MediatorAutoplayCounter;
	import ingenuity.casino.slotgame.ui.mediator.autoplay.MediatorAutoplayGenericControls;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorBetLineMeter;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorBetMaxButton;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorConsoleMeter;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorDenominationMeter;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorFreeGameCounter;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorFreeGameSpinStopButtons;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorFreeGameWinMeter;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorGameBalance;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorInfoButton;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorPaylinesMeter;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorSpinStopButtons;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorTotalBetMeter;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorTotalWinMeter;
	import ingenuity.casino.slotgame.ui.mediator.grid.MediatorBaseGameGridShowHideMask;
	import ingenuity.casino.slotgame.ui.mediator.grid.MediatorFreeGameGridShowHideMask;
	import ingenuity.casino.slotgame.ui.mediator.helpscreen.MediatorHelpScreenConsole;
	import ingenuity.casino.slotgame.ui.mediator.helpscreen.MediatorHelpScreenPagination;
	import ingenuity.casino.slotgame.ui.mediator.lines.MediatorLineIdentifiers;
	import ingenuity.casino.slotgame.ui.mediator.lines.MediatorSelectedPaylines;
	import ingenuity.casino.slotgame.ui.mediator.reels.vlist.basegame.MediatorBaseGameReelInitialDisplay;
	import ingenuity.casino.slotgame.ui.mediator.reels.vlist.basegame.MediatorBaseGameReelSpinShowHideExtraSymbols;
	import ingenuity.casino.slotgame.ui.mediator.reels.vlist.basegame.MediatorBaseGameReelSpinTopToBottom;
	import ingenuity.casino.slotgame.ui.mediator.reels.vlist.basegame.MediatorBaseGameReelUpdateSymbolFromTop;
	import ingenuity.casino.slotgame.ui.mediator.reels.vlist.basegame.MediatorBaseGameShowReelResponse;
	import ingenuity.casino.slotgame.ui.mediator.reels.vlist.freegame.MediatorFreeGameReelSpinShowHideExtraSymbols;
	import ingenuity.casino.slotgame.ui.mediator.reels.vlist.freegame.MediatorFreeGameReelSpinTopToBottom;
	import ingenuity.casino.slotgame.ui.mediator.reels.vlist.freegame.MediatorFreeGameReelUpdateSymbolFromTop;
	import ingenuity.casino.slotgame.ui.mediator.reels.vlist.freegame.MediatorFreeGameShowReelResponse;
	import ingenuity.casino.slotgame.ui.mediator.win.basegame.general.MediatorBaseGameSelectedLineSymbolWinGeneral;
	import ingenuity.casino.slotgame.ui.mediator.win.basegame.general.MediatorBaseGameSelectedLineSymbolWinGeneralNoSound;
	import ingenuity.casino.slotgame.ui.mediator.win.basegame.others.MediatorBaseGameBigWinCelebration;
	import ingenuity.casino.slotgame.ui.mediator.win.basegame.others.MediatorBaseGameShowAllWiningPayLines;
	import ingenuity.casino.slotgame.ui.mediator.win.basegame.others.MediatorBaseGameShowLineSymbolMask;
	import ingenuity.casino.slotgame.ui.mediator.win.basegame.others.MediatorBaseGameShowPaylines;
	import ingenuity.casino.slotgame.ui.mediator.win.basegame.others.MediatorBaseGameShowWinFrames;
	import ingenuity.casino.slotgame.ui.mediator.win.bonusgame.MediatorBonusGameAnimateTiggeringSymbolAndProceed;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.general.MediatorFreeGameSelectedLineSymbolWinGeneral;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.others.MediatorFreeGameBigWinCelebration;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.others.MediatorFreeGameShowAllWiningPayLines;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.others.MediatorFreeGameShowLineSymbolMask;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.others.MediatorFreeGameShowPaylines;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.others.MediatorFreeGameShowWinFrames;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.trigger.MediatorFreeGameAnimateTiggeringSymbolAndProceed;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.trigger.MediatorFreeGameShowTiggeringFrames;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.trigger.MediatorRetriggerAnimateTiggeringSymbolAndProceed;
	import ingenuity.casino.slotgame.ui.mediator.win.freegame.trigger.MediatorRetriggerShowTiggeringFrames;
	import ingenuity.casino.slotgame.ui.mediator.win.popup.MediatorFreeGameIntroPopup;
	import ingenuity.casino.slotgame.ui.mediator.win.popup.MediatorFreeGameOutroPopup;
	import ingenuity.casino.slotgame.ui.mediator.win.popup.MediatorRetriggerPopup;
	import ingenuity.casino.slotgame.ui.view.autoplay.ViewAutoplayCounter;
	import ingenuity.casino.slotgame.ui.view.autoplay.ViewAutoplayGenericControls;
	import ingenuity.casino.slotgame.ui.view.console.ViewBetLineMeter;
	import ingenuity.casino.slotgame.ui.view.console.ViewBetMaxButton;
	import ingenuity.casino.slotgame.ui.view.console.ViewConsoleMeter;
	import ingenuity.casino.slotgame.ui.view.console.ViewDenominationMeter;
	import ingenuity.casino.slotgame.ui.view.console.ViewFreeGameCounter;
	import ingenuity.casino.slotgame.ui.view.console.ViewFreeGameSpinStopButtons;
	import ingenuity.casino.slotgame.ui.view.console.ViewFreeGameWinMeter;
	import ingenuity.casino.slotgame.ui.view.console.ViewGameBalance;
	import ingenuity.casino.slotgame.ui.view.console.ViewInfoButton;
	import ingenuity.casino.slotgame.ui.view.console.ViewPaylineMeter;
	import ingenuity.casino.slotgame.ui.view.console.ViewSpinStopButton;
	import ingenuity.casino.slotgame.ui.view.console.ViewTotalWinMeter;
	import ingenuity.casino.slotgame.ui.view.console.ViewTotelBetMeter;
	import ingenuity.casino.slotgame.ui.view.helpscreen.ViewHelpScreenConsole;
	import ingenuity.casino.slotgame.ui.view.helpscreen.ViewHelpScreenPagination;
	import ingenuity.casino.slotgame.ui.view.lines.ViewLineIdentifiers;
	import ingenuity.casino.slotgame.ui.view.lines.ViewSelectedPaylines;
	import ingenuity.casino.slotgame.ui.view.popup.ViewFreeGameIntroPopup;
	import ingenuity.casino.slotgame.ui.view.popup.ViewRetriggerPopup;
	import ingenuity.casino.slotgame.ui.view.popup.ViewfreeGameOutroPopup;
	import ingenuity.casino.slotgame.ui.view.reels.grid.ViewSlotgameGrid;
	import ingenuity.casino.slotgame.ui.view.reels.vlist.ViewVReel1;
	import ingenuity.casino.slotgame.ui.view.reels.vlist.ViewVReel2;
	import ingenuity.casino.slotgame.ui.view.reels.vlist.ViewVReel3;
	import ingenuity.casino.slotgame.ui.view.reels.vlist.ViewVReel4;
	import ingenuity.casino.slotgame.ui.view.reels.vlist.ViewVReel5;
	import ingenuity.casino.slotgame.ui.view.win.ViewBigWinCelebration;
	import ingenuity.casino.slotgame.ui.view.win.ViewLineSymbolMask;
	import ingenuity.casino.slotgame.ui.view.win.ViewWinFrames;

	public class RegisterMediator
	{
		private var _mediator	: MediatorManager	= MediatorManager.instance() ;
		private var _component	: ComponentLibrary	= ComponentLibrary.instance() ;
		
		public function RegisterMediator()
		{
			registerConsoleMediator () ;
			registerPaylines (); 
			registerBetPerLine (); 
			registerHelpScreens () ;
			registerGrid ();
			registerReels ();
			registerBaseGameWin () ;
			registerFreeGame () ;
			registerGameMessages ();
			registerFreeGameWin ();			
			registerAutoplay();
			initializeMenuButton();
		}
		
		
		private function registerAutoplay():void
		{
			var clip : DisplayObject = _component.getComponent(GameScreens.SETTING_BUTTONPANNEL);
			_mediator.registerMediator(MediatorKeyCJ.AUTOPLAY_COUNTER, MediatorAutoplayCounter, ViewAutoplayCounter, clip);
			_mediator.registerMediator(MediatorKeyAutoplay.CONTROLS, MediatorAutoplayGenericControlsCJ, ViewAutoplayGenericControlsCJ, clip);			
		}
		
		
		
		
		private function registerConsoleMediator () : void
		{
			var clip : DisplayObject = _component.getComponent( GameScreens.BASE_GAME_BUTTONPANNEL ) ;
			
			//_mediator.registerMediator ( MediatorKeyConsole.CONSOLE_METER  , MediatorConsoleMeter , ViewConsoleMeter , clip );
			_mediator.registerMediator ( MediatorKeyConsole.LINE_BET_METER , MediatorBetLineMeterWhiteBuffalo , ViewBetLineMeter , clip );
			//_mediator.registerMediator ( MediatorKeyConsole.PAYLINES_METER , MediatorPaylinesMeter, ViewPaylineMeter , clip ) ;
			_mediator.registerMediator ( MediatorKeyConsole.TOTAL_BET_METER, MediatorTotalBetMeterCJ, ViewTotelBetMeter , clip ) ;
			_mediator.registerMediator ( MediatorKeyConsole.TOTAL_WIN_METER, MediatorTotalWinMeterCJ, ViewTotalWinMeterCJ , clip ) ;
			
			_mediator.registerMediator ( MediatorKeyConsole.GAME_BALANCE_METER, MediatorGameBalanceCJ, ViewGameBalanceCJ  , clip ) ;
			
			//_mediator.registerMediator ( MediatorKeyConsole.BET_MAX_BUTTON, MediatorBetMaxButton, ViewBetMaxButton , clip ) ;
			_mediator.registerMediator ( MediatorKeyConsole.INFO_BUTTON, MediatorInfoButton, ViewInfoButton , clip ) ;
			_mediator.registerMediator ( MediatorKeyConsole.SPIN_STOP_BUTTON, MediatorSpinStopButtons, ViewSpinStopButton , clip ) ;
			
			_mediator.registerMediator ( MediatorKeyCJ.UPDATE_MAIN_MENU, MediatorMainMenuCJ, ViewMainMenuCJ, clip );
			
			_mediator.registerMediator ( MediatorKeyAutoplay.COUNTER , MediatorAutoplayCounter, ViewAutoplayCounterCJ, clip);
			
			var settingClip :  DisplayObject = _component.getComponent(GameScreens.SETTING_BUTTONPANNEL);
			_mediator.registerMediator ( MediatorKeyCJ.UPDATE_SETTING_MENU, MediatorSettingMenuCJ, ViewSettingMenuCJ, settingClip );
			_mediator.registerMediator ( MediatorKeyCJ.LINE_BET_METER, MediatorBetLineMeterWhiteBuffalo , ViewBetLineMeter , settingClip );
			
			_mediator.registerMediator ( MediatorKeyConsole.DENOMINATION_METER , MediatorDenominationMeterCJ , ViewDenominationMeter , settingClip );
		}
		
		
		private function registerBetPerLine () : void
		{
			var clip : DisplayObject	=	_component.getComponent ( GameScreens.BET_PERLINE_BET_COLLECTION ) ;
			_mediator.registerMediator ( MediatorKeyBetPerLine.LINE_IDENTIFIERS, MediatorLineIdentifiersCJ, ViewLineIdentifiers, clip ) ;
		}
		
		
		private function registerPaylines () : void
		{
			var clip : DisplayObject	=	_component.getComponent ( GameScreens.PAYLINES_COLLECTION ) ;
			_mediator.registerMediator ( MediatorKeyBetPerLine.SELECTED_PAYLINES, MediatorSelectedPaylinesCJ, ViewSelectedPaylines, clip ) ;
			_mediator.registerMediator ( MediatorKeyCJ.SELECTED_PAYLINE, MediatorShowSelectedPaylinesCJ, ViewSelectedPaylines, clip ) ;
		}
		
		private function registerHelpScreens () : void
		{
			var clip	: DisplayObject	=	_component.getComponent ( GameScreens.HELPSCREEN_BUTTON_PANNEL ) ;
			_mediator.registerMediator ( MediatorKeyHelpScreen.HELPSCREEN_CONSOLE, MediatorHelpScreenConsole, ViewHelpScreenConsole, clip ) ;
			_mediator.registerMediator ( MediatorKeyHelpScreen.HELPSCREEN_PAGINATION, MediatorHelpScreenPagination, ViewHelpScreenPagination, clip ) ;
			
		}
		
		private function registerGrid () : void
		{
			var clip : MovieClip = _component.getComponent ( GameScreens.SLOT_GAME_GRID ) as MovieClip ;
			_mediator.registerMediator ( MediatorKeyGrid.GRID_BASEGAME_SHOW_HIDE_MASK , MediatorBaseGameGridShowHideMask , ViewSlotgameGrid , clip ) ;
			_mediator.registerMediator ( MediatorKeyGrid.GRID_FREEGAME_SHOW_HIDE_MASK , MediatorFreeGameGridShowHideMask , ViewSlotgameGrid , clip ) ;
		}
		
		private function registerBaseGameWin () : void
		{
			var bigwin : MovieClip = _component.getComponent ( GameScreens.BIG_WIN_CELEBRATION ) as MovieClip ; 
			_mediator.registerMediator ( MediatorKeyWinPresentation.BASE_GAME_WIN_BIG_WIN_CELEBRATION, MediatorBaseGameBigWinCelebrationCJ , ViewBigWinCelebration , bigwin ) ;	
			
			
			
			
			
			var paylines : DisplayObject	=	_component.getComponent ( GameScreens.PAYLINES_COLLECTION ) ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_PAY_LINES, MediatorBaseGameShowPaylines, ViewSelectedPaylines, paylines ) ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_SYMBOL_LINE_MASK, MediatorBaseGameShowLineSymbolMask, ViewLineSymbolMask, paylines ) ;
			
			var winframes : DisplayObject	=	_component.getComponent ( GameScreens.WIN_FRAME_COLLECTION ) ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_WIN_FRAMES, MediatorBaseGameShowWinFrames , ViewWinFrames, winframes ) ;
			
			var grid : MovieClip = _component.getComponent ( GameScreens.SLOT_GAME_GRID ) as MovieClip ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_LINE_SYMBOLS_FIRST_CYCLE, MediatorBaseGameSelectedLineSymbolWinGeneral, ViewSlotgameGrid, grid ) ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_LINE_SYMBOLS_REPEAT_CYCLES, MediatorBaseGameSelectedLineSymbolWinGeneralNoSound, ViewSlotgameGrid, grid ) ;
			
			_mediator.registerMediator ( MediatorKeyWinPresentation.BASE_GAME_SHOW_ALL_WIN_LINES, MediatorBaseGameShowAllWiningPayLines, ViewSelectedPaylines, paylines ) ;
		}
		
		private function registerFreeGameWin () : void
		{
			var bigwin : MovieClip = _component.getComponent ( GameScreens.BIG_WIN_CELEBRATION ) as MovieClip ; 
			_mediator.registerMediator ( MediatorKeyWinPresentation.FREE_GAME_WIN_BIG_WIN_CELEBRATION, MediatorFreeGameBigWinCelebrationCJ , ViewBigWinCelebration , bigwin ) ;	
			_mediator.registerMediator ( MediatorKeyWinPresentation.FREE_GAME_END_BIG_WIN_CELEBRATION, MediatorFreeGameEndBigWinCelebrationCJ, ViewBigWinCelebration, bigwin);
			
			
			var paylines : DisplayObject	=	_component.getComponent ( GameScreens.PAYLINES_COLLECTION ) ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.FREE_GAME_WIN_SHOW_PAY_LINES, MediatorFreeGameShowPaylines, ViewSelectedPaylines, paylines ) ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.FREE_GAME_WIN_SHOW_SYMBOL_LINE_MASK, MediatorFreeGameShowLineSymbolMask, ViewLineSymbolMask, paylines ) ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.FREE_GAME_SHOW_ALL_WIN_LINES, MediatorFreeGameShowAllWiningPayLines, ViewSelectedPaylines, paylines ) ;
			
			var winframes : DisplayObject	=	_component.getComponent ( GameScreens.WIN_FRAME_COLLECTION ) ;
			_mediator.registerMediator ( MediatorKeyFeatureUI.FREEGAME_TRIGGERING_FRAMES , MediatorFreeGameShowTiggeringFrames , ViewWinFrames, winframes ) ;
			_mediator.registerMediator ( MediatorKeyFeatureUI.RETRIGGER_TRIGGERING_FRAMES , MediatorRetriggerShowTiggeringFrames , ViewWinFrames, winframes ) ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.FREE_GAME_WIN_SHOW_WIN_FRAMES, MediatorFreeGameShowWinFrames , ViewWinFrames, winframes ) ;
			
			var grid : MovieClip = _component.getComponent ( GameScreens.SLOT_GAME_GRID ) as MovieClip ;
			_mediator.registerMediator ( MediatorKeyWinPresentation.FREE_GAME_WIN_SHOW_LINE_SYMBOLS_FIRST_CYCLE, MediatorFreeGameSelectedLineSymbolWinGeneral, ViewSlotgameGrid, grid ) ;
			_mediator.registerMediator ( MediatorKeyFeatureUI.FREEGAME_TRIGGERING_SYMBOL, MediatorFreeGameAnimateTiggeringSymbolAndProceed , ViewSlotgameGrid, grid ) ;
			_mediator.registerMediator ( MediatorKeyFeatureUI.RETRIGGER_TRIGGERING_SYMBOL, MediatorRetriggerAnimateTiggeringSymbolAndProceed , ViewSlotgameGrid, grid ) ;
			
		}
		
		
		private function registerGameMessages () : void
		{
			var clip : MovieClip = _component.getComponent ( GameScreens.GAME_MESSAGES_FIELDS ) as MovieClip ; 
			
			_mediator.registerMediator ( MediatorKeyMessage.BASE_GAME_PLAYER_MESSAGE , MediatorBaseGamePlayerMessage , ViewPlayerMessage , clip ) ;
			_mediator.registerMediator ( MediatorKeyMessage.FREE_GAME_TRIGGER_MESSAGE , MediatorFreeGameTriggerMessage , ViewPlayerMessage , clip ) ;
			_mediator.registerMediator ( MediatorKeyMessage.FREE_GAME_PLAYER_MESSAGE , MediatorFreeGamePlayerMessage , ViewPlayerMessage , clip ) ;
			
			var baseGamePromoMsgClip : MovieClip = _component.getComponent ( GameScreens.GAME_PROMO_MESSAGES ) as MovieClip ; 
			_mediator.registerMediator ( MediatorKeyMessage.BASE_GAME_PROMO_MESSAGE_GRAPHIC , MediatorBaseGamePromoMessage , ViewPromoMessage , baseGamePromoMsgClip ) ;
			
			
			var freeGamePromoMsgClip : MovieClip = _component.getComponent ( GameScreens.FREE_GAME_PROMO_MESSAGES ) as MovieClip ;
			
			_mediator.registerMediator ( MediatorKeyMessage.FREE_GAME_PROMO_MESSAGE_GRAPHIC , MediatorFreeGamePromoMessage , ViewPromoMessage , freeGamePromoMsgClip ) ;
		}
		
		
		private function registerReels () : void
		{
			var grid : MovieClip = _component.getComponent ( GameScreens.SLOT_GAME_GRID ) as MovieClip ;
			var clip : MovieClip = grid.reelSet;
			
			_mediator.registerMediator ( MediatorKeyReels.REEL_1_INITIAL_DISPLAY, MediatorBaseGameReelInitialDisplay , ViewVReel1 , clip.reel1 ) ;
			_mediator.registerMediator ( MediatorKeyReels.REEL_2_INITIAL_DISPLAY, MediatorBaseGameReelInitialDisplay , ViewVReel2 , clip.reel2 ) ;
			_mediator.registerMediator ( MediatorKeyReels.REEL_3_INITIAL_DISPLAY, MediatorBaseGameReelInitialDisplay , ViewVReel3 , clip.reel3 ) ;
			_mediator.registerMediator ( MediatorKeyReels.REEL_4_INITIAL_DISPLAY, MediatorBaseGameReelInitialDisplay , ViewVReel4 , clip.reel4 ) ;
			_mediator.registerMediator ( MediatorKeyReels.REEL_5_INITIAL_DISPLAY, MediatorBaseGameReelInitialDisplay , ViewVReel5 , clip.reel5 ) ;
			
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_1_SPINING, MediatorBaseGameReelSpinTopToBottom , ViewVReel1 , clip.reel1 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_2_SPINING, MediatorBaseGameReelSpinTopToBottom , ViewVReel2 , clip.reel2 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_3_SPINING, MediatorBaseGameReelSpinTopToBottom , ViewVReel3 , clip.reel3 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_4_SPINING, MediatorBaseGameReelSpinTopToBottom , ViewVReel4 , clip.reel4 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_5_SPINING, MediatorBaseGameReelSpinTopToBottom , ViewVReel5 , clip.reel5 ) ;
			
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_1_SHOW_HIDE_EXTRA_SYMBOLS, MediatorBaseGameReelSpinShowHideExtraSymbols , ViewVReel1 , clip.reel1 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_2_SHOW_HIDE_EXTRA_SYMBOLS, MediatorBaseGameReelSpinShowHideExtraSymbols , ViewVReel2 , clip.reel2 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_3_SHOW_HIDE_EXTRA_SYMBOLS, MediatorBaseGameReelSpinShowHideExtraSymbols , ViewVReel3 , clip.reel3 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_4_SHOW_HIDE_EXTRA_SYMBOLS, MediatorBaseGameReelSpinShowHideExtraSymbols , ViewVReel4 , clip.reel4 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_5_SHOW_HIDE_EXTRA_SYMBOLS, MediatorBaseGameReelSpinShowHideExtraSymbols , ViewVReel5 , clip.reel5 ) ;
			
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_1_UPDATE_SYMBOL , MediatorBaseGameReelUpdateSymbolFromTop , ViewVReel1 , clip.reel1 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_2_UPDATE_SYMBOL , MediatorBaseGameReelUpdateSymbolFromTop , ViewVReel2 , clip.reel2 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_3_UPDATE_SYMBOL , MediatorBaseGameReelUpdateSymbolFromTop , ViewVReel3 , clip.reel3 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_4_UPDATE_SYMBOL , MediatorBaseGameReelUpdateSymbolFromTop , ViewVReel4 , clip.reel4 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_5_UPDATE_SYMBOL , MediatorBaseGameReelUpdateSymbolFromTop , ViewVReel5 , clip.reel5 ) ;
			
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_1_SHOW_REEL_RESPONSE , MediatorBaseGameShowReelResponse , ViewVReel1 , clip.reel1 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_2_SHOW_REEL_RESPONSE , MediatorBaseGameShowReelResponse , ViewVReel2 , clip.reel2 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_3_SHOW_REEL_RESPONSE , MediatorBaseGameShowReelResponse , ViewVReel3 , clip.reel3 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_4_SHOW_REEL_RESPONSE , MediatorBaseGameShowReelResponse , ViewVReel4 , clip.reel4 ) ;
			_mediator.registerMediator ( MediatorKeyReels.BASE_GAME_REEL_5_SHOW_REEL_RESPONSE , MediatorBaseGameShowReelResponse , ViewVReel5 , clip.reel5 ) ;
			
			
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_1_SPINING, MediatorFreeGameReelSpinTopToBottom , ViewVReel1 , clip.reel1 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_2_SPINING, MediatorFreeGameReelSpinTopToBottom , ViewVReel2 , clip.reel2 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_3_SPINING, MediatorFreeGameReelSpinTopToBottom , ViewVReel3 , clip.reel3 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_4_SPINING, MediatorFreeGameReelSpinTopToBottom , ViewVReel4 , clip.reel4 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_5_SPINING, MediatorFreeGameReelSpinTopToBottom , ViewVReel5 , clip.reel5 ) ;
			
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_1_SHOW_HIDE_EXTRA_SYMBOLS, MediatorFreeGameReelSpinShowHideExtraSymbols , ViewVReel1 , clip.reel1 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_2_SHOW_HIDE_EXTRA_SYMBOLS, MediatorFreeGameReelSpinShowHideExtraSymbols , ViewVReel2 , clip.reel2 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_3_SHOW_HIDE_EXTRA_SYMBOLS, MediatorFreeGameReelSpinShowHideExtraSymbols , ViewVReel3 , clip.reel3 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_4_SHOW_HIDE_EXTRA_SYMBOLS, MediatorFreeGameReelSpinShowHideExtraSymbols , ViewVReel4 , clip.reel4 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_5_SHOW_HIDE_EXTRA_SYMBOLS, MediatorFreeGameReelSpinShowHideExtraSymbols , ViewVReel5 , clip.reel5 ) ;
			
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_1_UPDATE_SYMBOL , MediatorFreeGameReelUpdateSymbolFromTop , ViewVReel1 , clip.reel1 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_2_UPDATE_SYMBOL , MediatorFreeGameReelUpdateSymbolFromTop , ViewVReel2 , clip.reel2 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_3_UPDATE_SYMBOL , MediatorFreeGameReelUpdateSymbolFromTop , ViewVReel3 , clip.reel3 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_4_UPDATE_SYMBOL , MediatorFreeGameReelUpdateSymbolFromTop , ViewVReel4 , clip.reel4 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_5_UPDATE_SYMBOL , MediatorFreeGameReelUpdateSymbolFromTop , ViewVReel5 , clip.reel5 ) ;
			
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_1_SHOW_REEL_RESPONSE , MediatorFreeGameShowReelResponse , ViewVReel1 , clip.reel1 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_2_SHOW_REEL_RESPONSE , MediatorFreeGameShowReelResponse , ViewVReel2 , clip.reel2 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_3_SHOW_REEL_RESPONSE , MediatorFreeGameShowReelResponse , ViewVReel3 , clip.reel3 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_4_SHOW_REEL_RESPONSE , MediatorFreeGameShowReelResponse , ViewVReel4 , clip.reel4 ) ;
			_mediator.registerMediator ( MediatorKeyReels.FREE_GAME_REEL_5_SHOW_REEL_RESPONSE , MediatorFreeGameShowReelResponse , ViewVReel5 , clip.reel5 ) ;
			
		}	
		
		
		
		private function registerFreeGame () : void
		{
			var intropopup : MovieClip = _component.getComponent ( GameScreens.FREE_GAME_INTRO_POPUP ) as MovieClip ; 
			_mediator.registerMediator ( MediatorKeyFeatureUI.FREEGAME_INTRO_SCREEN , MediatorFreegameIntroPopupWhiteBuffalo, ViewFreeGameIntroPopupWhiteBuffalo , intropopup ) ;
			
			
			var outropopup : MovieClip = _component.getComponent ( GameScreens.FREE_GAME_OUTRO_POPUP ) as MovieClip ; 
			_mediator.registerMediator ( MediatorKeyFeatureUI.FREEGAME_OUTRO_SCREEN , MediatorFreegameOutroPopupWhiteBuffalo , ViewFreeGameOutroPopupCJ , outropopup ) ;
			
			var retrigger : MovieClip = _component.getComponent ( GameScreens.RETRIGGER_POPUP ) as MovieClip ; 
			_mediator.registerMediator ( MediatorKeyFeatureUI.RETRIGGER_SCREEN , MediatorRetriggerPopup , ViewRetriggerPopup , retrigger ) ;
			
			var winmeter : MovieClip = _component.getComponent ( GameScreens.FREE_GAME_CONSOLE ) as MovieClip ; 
			_mediator.registerMediator ( MediatorKeyConsole.FREEGAME_COUNTER , MediatorFreeGameCounterWhiteBuffalo , ViewFreeGameCounterWhiteBuffalo , winmeter ) ;
			_mediator.registerMediator ( MediatorKeyConsole.FREEGAME_WIN_METER , MediatorFreeGameWinMeterCJ , ViewFreeGameWinMeterCJ , winmeter ) ;
		}
		
		private function initializeMenuButton () : void
		{
			_mediator.createView(MediatorKeyCJ.UPDATE_MAIN_MENU);
			_mediator.updateView(MediatorKeyCJ.UPDATE_MAIN_MENU);
			
			_mediator.createView(MediatorKeyCJ.UPDATE_SETTING_MENU);
		}
		
		
	}
}