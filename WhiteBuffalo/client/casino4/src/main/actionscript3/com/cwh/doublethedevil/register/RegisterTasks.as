package com.cwh.WhiteBuffalo.register
{
	import com.cwh.WhiteBuffalo.tasks.microtask.freegame.MicrotaskBaseGameScreenWithFreeGameIntro;
	import com.cwh.WhiteBuffalo.tasks.microtask.freegame.MicrotaskBaseGameScreenWithFreeGameOutro;
	import com.cwh.WhiteBuffalo.tasks.microtask.freegame.MicrotaskFreeGamePlayScreen;
	import com.cwh.WhiteBuffalo.tasks.microtask.freegame.MicrotaskFreeGameScreenWithFreeGameIntro;
	import com.cwh.WhiteBuffalo.tasks.microtask.freegame.MicrotaskFreeGameScreenWithFreeGameOutro;
	import com.cwh.WhiteBuffalo.tasks.microtask.freegame.MicrotaskRetriggerScreen;
	import com.cwh.WhiteBuffalo.tasks.microtask.initialize.MicrotaskDefaultGameScreen;
	import com.cwh.WhiteBuffalo.tasks.microtask.initialize.MicrotaskPopulateInitialDataInView;
	import com.cwh.WhiteBuffalo.tasks.microtask.register.MicroTaskRegisterMediator;
	import com.cwh.WhiteBuffalo.tasks.microtask.task.anticipation.MicrotaskBaseGameAnticipationScatterWhiteBuffalo;
	import com.cwh.WhiteBuffalo.tasks.microtask.task.anticipation.MicrotaskFreeGameAnticipationScatterWhiteBuffalo;
	
	import ingenuity.amaya.tasks.macrotask.bet.MacrotaskProcessBetRequest;
	import ingenuity.amaya.tasks.macrotask.bet.MacrotaskProcessFreegameRequest;
	import ingenuity.amaya.tasks.macrotask.gamecycle.MacrotaskNewGameCycleOpenGameAmaya;
	import ingenuity.amaya.tasks.macrotask.gamecycle.MacrotaskStartGameCycleForBaseBameAmaya;
	import ingenuity.amaya.tasks.macrotask.gamecycle.MacrotaskStartGameCycleForFreeGameWinPresentationAmaya;
	import ingenuity.amaya.tasks.macrotask.initialize.MacrotaskLoadGameAssets;
	import ingenuity.amaya.tasks.macrotask.initialize.MacrotaskLoadGameConfig;
	import ingenuity.amaya.tasks.macrotask.initialize.MacrotaskLoadGameInitResponse;
	import ingenuity.amaya.tasks.macrotask.key.MacrotaskkeyAmaya;
	import ingenuity.amaya.tasks.macrotask.task.gamecycle.MacrotaskNewGameCycleOpenGameAmayaCJ;
	import ingenuity.amaya.tasks.macrotask.task.gamecycle.base.MacrotaskStopGameCycleForBaseGameCJ;
	import ingenuity.amaya.tasks.macrotask.task.win.basegame.MacrotaskBasegameShowWinPaylinesWinframesCJ;
	import ingenuity.amaya.tasks.microtask.autoplay.MicrotaskDecrementAutoplayCountCJ;
	import ingenuity.amaya.tasks.microtask.autoplay.MicrotaskIncrementAutoplayCountCJ;
	import ingenuity.amaya.tasks.microtask.autoplay.MicrotaskSetDefaultAutoplayCJ;
	import ingenuity.amaya.tasks.microtask.autoplay.MicrotaskStopAutoplayCJ;
	import ingenuity.amaya.tasks.microtask.bigwin.MicrotaskBigwinTickupEnd;
	import ingenuity.amaya.tasks.microtask.keys.MicrotaskKeyCJ;
	import ingenuity.amaya.tasks.microtask.paylines.MicrotaskLineIdentifierHidePaylineCJ;
	import ingenuity.amaya.tasks.microtask.paylines.MicrotaskLineIdentifierShowPaylineCJ;
	import ingenuity.amaya.tasks.microtask.task.bet.MicrotaskBetUpdateWrapper;
	import ingenuity.amaya.tasks.microtask.task.bet.MicrotaskBetValidateRequest;
	import ingenuity.amaya.tasks.microtask.task.bet.MicrotaskBetValidateRequestCJ;
	import ingenuity.amaya.tasks.microtask.task.brokengame.MicrotaskShowBrokenGamePopup;
	import ingenuity.amaya.tasks.microtask.task.console.MicrotaskColsoleDecrementBetCJ;
	import ingenuity.amaya.tasks.microtask.task.console.MicrotaskColsoleIncrementBetCJ;
	import ingenuity.amaya.tasks.microtask.task.console.MicrotaskDecrementDenominationCJ;
	import ingenuity.amaya.tasks.microtask.task.console.MicrotaskIncrementDenominationCJ;
	import ingenuity.amaya.tasks.microtask.task.console.MicrotaskUpdateBaseGameCJ;
	import ingenuity.amaya.tasks.microtask.task.helpscreen.MicrotaskHideHelpScreenCJ;
	import ingenuity.amaya.tasks.microtask.task.helpscreen.MicrotaskShowHelpScreenCJ;
	import ingenuity.amaya.tasks.microtask.task.initialize.MicrotaskGameReadyToDisplayAmaya;
	import ingenuity.amaya.tasks.microtask.task.keyevent.MicrotaskBaseGameKeyBoardEventCJ;
	import ingenuity.amaya.tasks.microtask.task.kill.MicrotaskKillFreeGameCJ;
	import ingenuity.amaya.tasks.microtask.task.popup.MicrotaskHideSettingsPopup;
	import ingenuity.amaya.tasks.microtask.task.popup.MicrotaskShowSettingsPopup;
	import ingenuity.amaya.tasks.microtask.task.win.ui.MicrotaskAnimateRetriggerTriggeringCJ;
	import ingenuity.amaya.tasks.microtask.task.win.ui.MicrotaskFreeGameOutroScreenCJ;
	import ingenuity.amaya.tasks.microtask.winpresentation.MicrotaskBaseGameWinPresentationNextStepCJ;
	import ingenuity.application.manager.TaskManager;
	import ingenuity.casino.slotgame.tasks.macrotask.dataform.MacrotaskReelSpin;
	import ingenuity.casino.slotgame.tasks.macrotask.key.MacrotaskKeyBet;
	import ingenuity.casino.slotgame.tasks.macrotask.key.MacrotaskKeyGameCycle;
	import ingenuity.casino.slotgame.tasks.macrotask.key.MacrotaskKeyReelSpin;
	import ingenuity.casino.slotgame.tasks.macrotask.key.MacrotaskKeyWinPresentation;
	import ingenuity.casino.slotgame.tasks.macrotask.task.gamecycle.basegame.MacrotaskNewGameCycleOpenGame;
	import ingenuity.casino.slotgame.tasks.macrotask.task.gamecycle.basegame.MacrotaskStartGameCycleForBaseBame;
	import ingenuity.casino.slotgame.tasks.macrotask.task.gamecycle.basegame.MacrotaskStopGameCycleForBaseGame;
	import ingenuity.casino.slotgame.tasks.macrotask.task.gamecycle.freegame.MacrotaskIntermediateCycleForFreeGame;
	import ingenuity.casino.slotgame.tasks.macrotask.task.gamecycle.freegame.MacrotaskStartGameCycleForFreeGame;
	import ingenuity.casino.slotgame.tasks.macrotask.task.gamecycle.freegame.MacrotaskStartGameCycleForFreeGameWinPresentation;
	import ingenuity.casino.slotgame.tasks.macrotask.task.gamecycle.freegame.MacrotaskStopGameCycleForFreeGame;
	import ingenuity.casino.slotgame.tasks.macrotask.task.gamecycle.freegame.MacrotaskStopGameCycleForFreeGameWinPresentation;
	import ingenuity.casino.slotgame.tasks.macrotask.task.reel.MacrotaskBaseGameReelSpinTimer;
	import ingenuity.casino.slotgame.tasks.macrotask.task.reel.MacrotaskFreeGameReelSpinTimer;
	import ingenuity.casino.slotgame.tasks.macrotask.task.win.basegame.MacrotaskBasegameBigWinCelebration;
	import ingenuity.casino.slotgame.tasks.macrotask.task.win.basegame.MacrotaskBasegameShowWinPaylinesWinframes;
	import ingenuity.casino.slotgame.tasks.macrotask.task.win.basegame.MacrotaskBasegameWinPresentation;
	import ingenuity.casino.slotgame.tasks.macrotask.task.win.freegame.MacrotaskFreegameBigWinCelebration;
	import ingenuity.casino.slotgame.tasks.macrotask.task.win.freegame.MacrotaskFreegameShowWinPaylinesWinframes;
	import ingenuity.casino.slotgame.tasks.macrotask.task.win.freegame.MacrotaskFreegameWinPresentation;
	import ingenuity.casino.slotgame.tasks.microtask.dataform.MicrotaskGameSymbolCollection;
	import ingenuity.casino.slotgame.tasks.microtask.dataform.MicrotaskReelData;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyAnticipation;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyAutoplay;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyBet;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyBrokenGame;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyConsolePanel;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyFreatureUI;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyHelpScreen;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyInitializeGame;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyKill;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyLineIdentifiers;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyReels;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyShorting;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeySymbols;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyWinPresentation;
	import ingenuity.casino.slotgame.tasks.microtask.task.anticipation.MicrotaskBaseGameAnticipationScatter;
	import ingenuity.casino.slotgame.tasks.microtask.task.anticipation.MicrotaskCreateBaseGameAnticipation;
	import ingenuity.casino.slotgame.tasks.microtask.task.anticipation.MicrotaskCreateFreeGameAnticipation;
	import ingenuity.casino.slotgame.tasks.microtask.task.anticipation.MicrotaskFreeGameAnticipationScatter;
	import ingenuity.casino.slotgame.tasks.microtask.task.autoplay.MicrotaskDecrementAutoplayCount;
	import ingenuity.casino.slotgame.tasks.microtask.task.autoplay.MicrotaskIncrementAutoplayCount;
	import ingenuity.casino.slotgame.tasks.microtask.task.autoplay.MicrotaskProcessNextAutoplayRequest;
	import ingenuity.casino.slotgame.tasks.microtask.task.autoplay.MicrotaskStartAutoplay;
	import ingenuity.casino.slotgame.tasks.microtask.task.autoplay.MicrotaskStopAutoplay;
	import ingenuity.casino.slotgame.tasks.microtask.task.bet.MicrotaskBetRequestComplete;
	import ingenuity.casino.slotgame.tasks.microtask.task.bet.MicrotaskFreeBetRequestComplete;
	import ingenuity.casino.slotgame.tasks.microtask.task.brokengame.MicrotaskPlayBrokenGame;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskColsoleDecrementBet;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskColsoleDecrementSelectedLine;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskColsoleIncrementBet;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskColsoleIncrementSelectedLine;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskConsoleBetMax;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskConsoleSpinBaseGame;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskConsoleSpinFreeGame;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskConsoleStopSpiningBaseGame;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskConsoleStopSpiningFreeGame;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskDecrementDenomination;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskIncrementDenomination;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskUpdateBaseGame;
	import ingenuity.casino.slotgame.tasks.microtask.task.console.MicrotaskUpdateFreeGame;
	import ingenuity.casino.slotgame.tasks.microtask.task.helpscreen.MicrotaskHideHelpScreen;
	import ingenuity.casino.slotgame.tasks.microtask.task.helpscreen.MicrotaskShowHelpScreen;
	import ingenuity.casino.slotgame.tasks.microtask.task.helpscreen.MicrotaskShowNextHelpScreen;
	import ingenuity.casino.slotgame.tasks.microtask.task.helpscreen.MicrotaskShowPrevHelpScreen;
	import ingenuity.casino.slotgame.tasks.microtask.task.initialise.MicrotaskGameReadyToDisplay;
	import ingenuity.casino.slotgame.tasks.microtask.task.kill.MicrotaskKillBaseGame;
	import ingenuity.casino.slotgame.tasks.microtask.task.kill.MicrotaskKillFreeGame;
	import ingenuity.casino.slotgame.tasks.microtask.task.kill.MicrotaskKillFreeGameWinPresentation;
	import ingenuity.casino.slotgame.tasks.microtask.task.kill.MicrotaskKillbaseGameWinPresentation;
	import ingenuity.casino.slotgame.tasks.microtask.task.lineIdentifiers.MicrotaskLineIdentifierSetPayLine;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.basegame.MicrotaskBaseGameReelStoppingSound;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.basegame.MicrotaskBasegameNextSymbolToDisplayAtTop;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.basegame.MicrotaskBasegameReelLoopComplete;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.basegame.MicrotaskBasegameReelSpinStopped;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.basegame.MicrotaskBasegameReelSpinificationSound;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.basegame.MicrotaskBasegameReelStopping;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.basegame.MicrotaskBasegameUpdateReelSpining;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.freegame.MicrotaskFreeGameReelStoppingSound;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.freegame.MicrotaskFreegameNextSymbolToDisplayAtTop;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.freegame.MicrotaskFreegameReelLoopComplete;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.freegame.MicrotaskFreegameReelSpinStopped;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.freegame.MicrotaskFreegameReelSpinificationSound;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.freegame.MicrotaskFreegameReelStopping;
	import ingenuity.casino.slotgame.tasks.microtask.task.reel.freegame.MicrotaskFreegameUpdateReelSpining;
	import ingenuity.casino.slotgame.tasks.microtask.task.sort.MicrotaskSortBaseGameLinesDescending;
	import ingenuity.casino.slotgame.tasks.microtask.task.sort.MicrotaskSortFreeGameLinesDescending;
	import ingenuity.casino.slotgame.tasks.microtask.task.symbols.MicrotaskGenerateBaseGameSymbolCollectionVertical;
	import ingenuity.casino.slotgame.tasks.microtask.task.symbols.MicrotaskGenerateFreeGameSymbolCollectionVertical;
	import ingenuity.casino.slotgame.tasks.microtask.task.symbols.MicrotaskPrepareSymbolAnimationGeneric;
	import ingenuity.casino.slotgame.tasks.microtask.task.symbols.MicrotaskPrepareSymbols;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.basegame.MicrotaskBaseGameShowCurrentWinPresentationFirstCycle;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.basegame.MicrotaskBaseGameShowCurrentWinPresentationRepeatCycle;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.basegame.MicrotaskBaseGameShowWinPresentationLoopElements;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.basegame.MicrotaskBaseGameStopWinPresentationFirstCycle;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.basegame.MicrotaskBaseGameStopWinPresentationLoopElements;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.basegame.MicrotaskBaseGameStopWinPresentationRepeatCycle;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.basegame.MicrotaskBaseGameWinPresentationNextStep;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.freegame.MicrotaskFreeGameShowCurrentWinPresentationFirstCycle;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.freegame.MicrotaskFreeGameShowWinPresentationLoopElements;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.freegame.MicrotaskFreeGameStopWinPresentationFirstCycle;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.freegame.MicrotaskFreeGameStopWinPresentationLoopElements;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.freegame.MicrotaskFreeGameWinPresentationNextStep;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.sequence.MicrotaskBaseGameWinSequenceFactory;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.sequence.MicrotaskFreeGameWinSequenceFactory;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.sequence.MicrotaskGameWinSequenceFactory;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.ui.MicrotaskAnimateFreegameTriggering;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.ui.MicrotaskAnimateRetriggerTriggering;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.ui.MicrotaskFreeGameIntroScreen;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.ui.MicrotaskFreeGameOutroScreen;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.ui.MicrotaskStopFreegameTriggeringAndProceed;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.ui.MicrotaskStopRetriggerTriggeringAndProceed;
	
	
	public class RegisterTasks
	{
		private var _task : TaskManager	=	TaskManager.instance() ;
		
		public function RegisterTasks()
		{
			registerWinPresentations ();
			registerBaseGameReels();
			registerFreeGameReels();
			registerInitialiseTasks () ;
			registerMediatorTasks (); 
			registerConsoleTask () ;
			registerSpinTasks () ;
			registerHelpScreenTasks () ;
			registerLineIdentifierTasks () ;
			registerGameKillTasks () ;
			registerGameCycleTasks() ;
			registerBrokenGames ();
			registerFreeGameXtras();
			registerGameAnticipation ();
			registerAutoPlay();
			registerPopUp();
			
		}
		
		private function registerAutoPlay () : void
		{
			_task.registerMicroTask ( MicrotaskKeyAutoplay.DECREMENT_COUNT, MicrotaskDecrementAutoplayCountCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyAutoplay.INCREMENT_COUNT, MicrotaskIncrementAutoplayCountCJ ) ;
			_task.registerMicroTask	( MicrotaskKeyCJ.SET_TO_DEFAULT_AUTOPLAY, MicrotaskSetDefaultAutoplayCJ);
			_task.registerMicroTask ( MicrotaskKeyAutoplay.PROCESS_NEXT_CYCLE , MicrotaskProcessNextAutoplayRequest ) ;
			_task.registerMicroTask ( MicrotaskKeyAutoplay.START_FEATURE, MicrotaskStartAutoplay ) ;			
			_task.registerMicroTask ( MicrotaskKeyAutoplay.STOP_FEATURE , MicrotaskStopAutoplayCJ ) ;
		}
		
		private function registerPopUp () : void
		{
			_task.registerMicroTask ( MicrotaskKeyCJ.SHOW_SETTINGS_POPUP, MicrotaskShowSettingsPopup );
			_task.registerMicroTask ( MicrotaskKeyCJ.HIDE_SETTINGS_POPUP, MicrotaskHideSettingsPopup );
		}
		
		private function registerGameAnticipation () : void
		{
			_task.registerMicroTask ( MicrotaskKeyAnticipation.BASE_GAME_ANTICIPATION, MicrotaskBaseGameAnticipationScatterWhiteBuffalo ) ;
			_task.registerMicroTask ( MicrotaskKeyAnticipation.FREE_GAME_ANTICIPATION, MicrotaskFreeGameAnticipationScatterWhiteBuffalo ) ;
			_task.registerMicroTask ( MicrotaskKeyAnticipation.CREATE_BASE_GAME_ANTICIPATION, MicrotaskCreateBaseGameAnticipation ) ; 
			_task.registerMicroTask ( MicrotaskKeyAnticipation.CREATE_FREE_GAME_ANTICIPATION, MicrotaskCreateFreeGameAnticipation ) ; 
			
			_task.registerMicroTask ( MicrotaskKeyReels.BASE_GAME_REEL_SPINIFICATION_SOUND, MicrotaskBasegameReelSpinificationSound ) ;
			_task.registerMicroTask ( MicrotaskKeyReels.FREE_GAME_REEL_SPINIFICATION_SOUND, MicrotaskFreegameReelSpinificationSound ) ;
		}
		
		private function registerInitialiseTasks () : void
		{
			_task.registerMacroTask ( MacrotaskkeyAmaya.LOAD_GAME_ASSETS , MacrotaskLoadGameAssets ) ;
			_task.registerMacroTask ( MacrotaskkeyAmaya.LOAD_GAME_CONFIG , MacrotaskLoadGameConfig ) ;
			_task.registerMacroTask ( MacrotaskkeyAmaya.LOAD_GAME_INIT , MacrotaskLoadGameInitResponse ) ;
			
			_task.registerMicroTask ( MicrotaskKeyInitializeGame.SET_DEFAULT_GAME_SCREEN , MicrotaskDefaultGameScreen ) ;
			_task.registerMicroTask ( MicrotaskKeyInitializeGame.POPULATE_INITIAL_DATA_IN_VIEW, MicrotaskPopulateInitialDataInView ) ;
			
			_task.registerMicroTask ( MicrotaskKeyInitializeGame.GAME_READY_TO_DISPLAY, MicrotaskGameReadyToDisplayAmaya ) ;
			
			_task.registerMicroTask ( MicrotaskKeySymbols.PREPARE_SYMBOLS , MicrotaskPrepareSymbols ) ;
			_task.registerMicroTask ( MicrotaskKeySymbols.PREPARE_SYMBOLS_ANIMATION, MicrotaskPrepareSymbolAnimationGeneric ) ;
			_task.registerMicroTask ( MicrotaskKeySymbols.GENERATE_SYMBOL_SEQUENCE_FOR_BASE_GAME , MicrotaskGenerateBaseGameSymbolCollectionVertical ) ;
			_task.registerMicroTask ( MicrotaskKeySymbols.GENERATE_SYMBOL_SEQUENCE_FOR_FREE_GAME , MicrotaskGenerateFreeGameSymbolCollectionVertical ) ;
		}
		
		private function registerMediatorTasks () : void
		{
			_task.registerMicroTask ( MicrotaskKeyInitializeGame.REGISTER_INITIAL_MEDIATORS, MicroTaskRegisterMediator ) ;
		}
		
		private function registerConsoleTask () : void
		{
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.DECREMENT_SELECTED_BET, MicrotaskColsoleDecrementBetCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.INCREMENT_SELECTED_BET, MicrotaskColsoleIncrementBetCJ ) ;
			
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.DECREMENT_DENOMINATION , MicrotaskDecrementDenominationCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.INCREMENT_DENOMINATION , MicrotaskIncrementDenominationCJ ) ;
			
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.BET_MAX , MicrotaskConsoleBetMax ) ;
			//_task.registerMicroTask ( MicrotaskKeyConsolePanel.UPDATE_GAME_BALANCE , MicrotaskUpdateGameBalanc ) ;
			
		}
		
		private function registerSpinTasks () : void
		{
			_task.registerMicroTask ( MicrotaskKeyCJ.KEYBOARD_SPIN_EVENT_FIRED , MicrotaskBaseGameKeyBoardEventCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.SPIN_BASEGAME , MicrotaskConsoleSpinBaseGame ) ;
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.UPDATE_BASEGAME , MicrotaskUpdateBaseGameCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.STOP_SPINING_BASEGAME, MicrotaskConsoleStopSpiningBaseGame ) ;
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.STOP_SPINING_FREEGAME, MicrotaskConsoleStopSpiningFreeGame ) ;
			
			_task.registerMicroTask ( MicrotaskKeyBet.VALIDATE , MicrotaskBetValidateRequestCJ) ;			
			_task.registerMicroTask ( MicrotaskKeyBet.REQUEST_COMPLETE , MicrotaskBetRequestComplete ) ;
			_task.registerMicroTask ( MicrotaskKeyBet.FREE_BET_REQUEST_COMPLETE, MicrotaskFreeBetRequestComplete )
			_task.registerMacroTask ( MacrotaskKeyBet.PROCESS , MacrotaskProcessBetRequest ) ;
			_task.registerMicroTask ( MicrotaskKeyReels.UPDATE_BASE_GAME_REEL_SPINING, MicrotaskBasegameUpdateReelSpining ) ;
			
			_task.registerMicroTask ( MicrotaskKeyBet.UPDATE_WRAPPER , MicrotaskBetUpdateWrapper ) ;
			
			_task.registerMacroTask ( MacrotaskKeyBet.FREE_GAME_BET, MacrotaskProcessFreegameRequest ) ;
			
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.SPIN_FREEGAME , MicrotaskConsoleSpinFreeGame ) ;
			_task.registerMicroTask ( MicrotaskKeyConsolePanel.UPDATE_FREEGAME , MicrotaskUpdateFreeGame ) ;
			_task.registerMicroTask ( MicrotaskKeyReels.UPDATE_FREE_GAME_REEL_SPINING, MicrotaskFreegameUpdateReelSpining ) ;
			
		}
		
		private function registerBaseGameReels () : void
		{
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_1_LOOP_COMPLETE, MicrotaskBasegameReelLoopComplete );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_2_LOOP_COMPLETE, MicrotaskBasegameReelLoopComplete );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_3_LOOP_COMPLETE, MicrotaskBasegameReelLoopComplete );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_4_LOOP_COMPLETE, MicrotaskBasegameReelLoopComplete );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_5_LOOP_COMPLETE, MicrotaskBasegameReelLoopComplete );
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_1_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "0");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_2_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "1");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_3_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "2");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_4_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "3");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_5_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "4");
			
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_1_SET_NEXT_SYMBOL , MicrotaskBasegameNextSymbolToDisplayAtTop );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_2_SET_NEXT_SYMBOL , MicrotaskBasegameNextSymbolToDisplayAtTop );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_3_SET_NEXT_SYMBOL , MicrotaskBasegameNextSymbolToDisplayAtTop );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_4_SET_NEXT_SYMBOL , MicrotaskBasegameNextSymbolToDisplayAtTop );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_5_SET_NEXT_SYMBOL , MicrotaskBasegameNextSymbolToDisplayAtTop );
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_1_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "0");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_2_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "1");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_3_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "2");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_4_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "3");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_5_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "4");
			
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_1_STOPPING , MicrotaskBasegameReelStopping  );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_2_STOPPING , MicrotaskBasegameReelStopping  );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_3_STOPPING , MicrotaskBasegameReelStopping  );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_4_STOPPING , MicrotaskBasegameReelStopping  );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_5_STOPPING , MicrotaskBasegameReelStopping  );
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_1_STOPPING , MicrotaskReelData.REEL_ID, "0");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_2_STOPPING , MicrotaskReelData.REEL_ID, "1");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_3_STOPPING , MicrotaskReelData.REEL_ID, "2");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_4_STOPPING , MicrotaskReelData.REEL_ID, "3");
			_task.injectToMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_5_STOPPING , MicrotaskReelData.REEL_ID, "4");
			
			_task.registerMacroTask (MacrotaskKeyReelSpin.BASE_GAME_1_REEL_SPIN_TIMER , MacrotaskBaseGameReelSpinTimer  );
			_task.registerMacroTask (MacrotaskKeyReelSpin.BASE_GAME_2_REEL_SPIN_TIMER , MacrotaskBaseGameReelSpinTimer  );
			_task.registerMacroTask (MacrotaskKeyReelSpin.BASE_GAME_3_REEL_SPIN_TIMER , MacrotaskBaseGameReelSpinTimer  );
			_task.registerMacroTask (MacrotaskKeyReelSpin.BASE_GAME_4_REEL_SPIN_TIMER , MacrotaskBaseGameReelSpinTimer  );
			_task.registerMacroTask (MacrotaskKeyReelSpin.BASE_GAME_5_REEL_SPIN_TIMER , MacrotaskBaseGameReelSpinTimer  );
			_task.injectToMacroTask (MacrotaskKeyReelSpin.BASE_GAME_1_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "0");
			_task.injectToMacroTask (MacrotaskKeyReelSpin.BASE_GAME_2_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "1");
			_task.injectToMacroTask (MacrotaskKeyReelSpin.BASE_GAME_3_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "2");
			_task.injectToMacroTask (MacrotaskKeyReelSpin.BASE_GAME_4_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "3");
			_task.injectToMacroTask (MacrotaskKeyReelSpin.BASE_GAME_5_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "4");
			
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_STOPPED , MicrotaskBasegameReelSpinStopped );
			_task.registerMicroTask (MicrotaskKeyReels.BASE_GAME_REEL_STOPPING_SOUND, MicrotaskBaseGameReelStoppingSound ) ;
			
		}
		
		private function registerFreeGameReels () : void
		{
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_1_LOOP_COMPLETE, MicrotaskFreegameReelLoopComplete );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_2_LOOP_COMPLETE, MicrotaskFreegameReelLoopComplete );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_3_LOOP_COMPLETE, MicrotaskFreegameReelLoopComplete );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_4_LOOP_COMPLETE, MicrotaskFreegameReelLoopComplete );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_5_LOOP_COMPLETE, MicrotaskFreegameReelLoopComplete );
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_1_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "0");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_2_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "1");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_3_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "2");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_4_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "3");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_5_LOOP_COMPLETE, MicrotaskReelData.REEL_ID, "4");
			
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_1_SET_NEXT_SYMBOL , MicrotaskFreegameNextSymbolToDisplayAtTop );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_2_SET_NEXT_SYMBOL , MicrotaskFreegameNextSymbolToDisplayAtTop );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_3_SET_NEXT_SYMBOL , MicrotaskFreegameNextSymbolToDisplayAtTop );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_4_SET_NEXT_SYMBOL , MicrotaskFreegameNextSymbolToDisplayAtTop );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_5_SET_NEXT_SYMBOL , MicrotaskFreegameNextSymbolToDisplayAtTop );
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_1_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "0");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_2_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "1");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_3_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "2");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_4_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "3");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_5_SET_NEXT_SYMBOL, MicrotaskReelData.REEL_ID, "4");
			
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_1_STOPPING , MicrotaskFreegameReelStopping  );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_2_STOPPING , MicrotaskFreegameReelStopping  );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_3_STOPPING , MicrotaskFreegameReelStopping  );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_4_STOPPING , MicrotaskFreegameReelStopping  );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_5_STOPPING , MicrotaskFreegameReelStopping  );
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_1_STOPPING , MicrotaskReelData.REEL_ID, "0");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_2_STOPPING , MicrotaskReelData.REEL_ID, "1");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_3_STOPPING , MicrotaskReelData.REEL_ID, "2");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_4_STOPPING , MicrotaskReelData.REEL_ID, "3");
			_task.injectToMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_5_STOPPING , MicrotaskReelData.REEL_ID, "4");
			
			_task.registerMacroTask (MacrotaskKeyReelSpin.FREE_GAME_1_REEL_SPIN_TIMER , MacrotaskFreeGameReelSpinTimer  );
			_task.registerMacroTask (MacrotaskKeyReelSpin.FREE_GAME_2_REEL_SPIN_TIMER , MacrotaskFreeGameReelSpinTimer  );
			_task.registerMacroTask (MacrotaskKeyReelSpin.FREE_GAME_3_REEL_SPIN_TIMER , MacrotaskFreeGameReelSpinTimer  );
			_task.registerMacroTask (MacrotaskKeyReelSpin.FREE_GAME_4_REEL_SPIN_TIMER , MacrotaskFreeGameReelSpinTimer  );
			_task.registerMacroTask (MacrotaskKeyReelSpin.FREE_GAME_5_REEL_SPIN_TIMER , MacrotaskFreeGameReelSpinTimer  );
			_task.injectToMacroTask (MacrotaskKeyReelSpin.FREE_GAME_1_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "0");
			_task.injectToMacroTask (MacrotaskKeyReelSpin.FREE_GAME_2_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "1");
			_task.injectToMacroTask (MacrotaskKeyReelSpin.FREE_GAME_3_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "2");
			_task.injectToMacroTask (MacrotaskKeyReelSpin.FREE_GAME_4_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "3");
			_task.injectToMacroTask (MacrotaskKeyReelSpin.FREE_GAME_5_REEL_SPIN_TIMER , MacrotaskReelSpin.REEL_ID, "4");
			
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_STOPPED , MicrotaskFreegameReelSpinStopped );
			_task.registerMicroTask (MicrotaskKeyReels.FREE_GAME_REEL_STOPPING_SOUND, MicrotaskFreeGameReelStoppingSound ) ;
			
		}
		
		private function registerGameCycleTasks () : void
		{
			_task.registerMacroTask ( MacrotaskKeyGameCycle.START_GAME_CYCLE_FOR_NEW_GAME , MacrotaskNewGameCycleOpenGameAmayaCJ);
			_task.registerMacroTask ( MacrotaskKeyGameCycle.START_GAME_CYCLE_FOR_BASE_GAME , MacrotaskStartGameCycleForBaseBameAmaya ) ;
			_task.registerMacroTask ( MacrotaskKeyGameCycle.STOP_GAME_CYCLE_FOR_BASE_GAME , MacrotaskStopGameCycleForBaseGameCJ ) ;
			
			_task.registerMacroTask ( MacrotaskKeyGameCycle.START_GAME_CYCLE_FOR_FREE_GAME , MacrotaskStartGameCycleForFreeGame ) ;
			_task.registerMacroTask ( MacrotaskKeyGameCycle.INTERMEDIATE_GAME_CYCLE_FOR_FREE_GAME , MacrotaskIntermediateCycleForFreeGame ) ;
			_task.registerMacroTask ( MacrotaskKeyGameCycle.STOP_GAME_CYCLE_FOR_FREE_GAME , MacrotaskStopGameCycleForFreeGame ) ;
			_task.registerMacroTask ( MacrotaskKeyGameCycle.START_GAME_CYCLE_FOR_FREE_WIN_ANIMATION , MacrotaskStartGameCycleForFreeGameWinPresentationAmaya ) ;
			_task.registerMacroTask ( MacrotaskKeyGameCycle.STOP_GAME_CYCLE_FOR_FREE_WIN_ANIMATION , MacrotaskStopGameCycleForFreeGameWinPresentation ) ;
		}
		
		private function registerWinPresentations () : void
		{
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.GAME_WIN_SEQUENCE_FACTORY, MicrotaskGameWinSequenceFactory );
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.BASE_GAME_WIN_SEQUENCE_FACTORY , MicrotaskBaseGameWinSequenceFactory );
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.FREE_GAME_WIN_SEQUENCE_FACTORY , MicrotaskFreeGameWinSequenceFactory );
			
			_task.registerMacroTask ( MacrotaskKeyWinPresentation.BASE_GAME_BIG_WIN_CELEBRATION , MacrotaskBasegameBigWinCelebration );
			_task.registerMacroTask ( MacrotaskKeyWinPresentation.BASE_GAME_WIN_PRESENTATION , MacrotaskBasegameWinPresentation ) ;
			_task.registerMacroTask ( MacrotaskKeyWinPresentation.FREE_GAME_BIG_WIN_CELEBRATION , MacrotaskFreegameBigWinCelebration );
			_task.registerMacroTask ( MacrotaskKeyWinPresentation.FREE_GAME_WIN_PRESENTATION , MacrotaskFreegameWinPresentation ) ;
			
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.PLAY_BASE_GAME_CURRENT_WIN_PRESENTATION_FIRST_CYCLE, MicrotaskBaseGameShowCurrentWinPresentationFirstCycle ) ;
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.STOP_BASE_GAME_CURRENT_WIN_PRESENTATION_FIRST_CYCLE, MicrotaskBaseGameStopWinPresentationFirstCycle ) ;
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.PLAY_BASE_GAME_CURRENT_WIN_PRESENTATION_REPEAT_CYCLES, MicrotaskBaseGameShowCurrentWinPresentationRepeatCycle ) ;
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.STOP_BASE_GAME_CURRENT_WIN_PRESENTATION_REPEAT_CYCLES, MicrotaskBaseGameStopWinPresentationRepeatCycle ) ;
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.PLAY_FREE_GAME_CURRENT_WIN_PRESENTATION_FIRST_CYCLE, MicrotaskFreeGameShowCurrentWinPresentationFirstCycle ) ;
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.STOP_FREE_GAME_CURRENT_WIN_PRESENTATION_FIRST_CYCLE, MicrotaskFreeGameStopWinPresentationFirstCycle ) ;
			
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.BASE_GAME_CURRENT_WIN_PRESENTATION_NEXT_STEP, MicrotaskBaseGameWinPresentationNextStepCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyWinPresentation.FREE_GAME_CURRENT_WIN_PRESENTATION_NEXT_STEP, MicrotaskFreeGameWinPresentationNextStep ) ;
			
			_task.registerMacroTask ( MacrotaskKeyWinPresentation.BASE_GAME_SHOW_WIN_PAYLINES_WINFRAMES, MacrotaskBasegameShowWinPaylinesWinframesCJ ) ;
			_task.registerMacroTask ( MacrotaskKeyWinPresentation.FREE_GAME_SHOW_WIN_PAYLINES_WINFRAMES, MacrotaskFreegameShowWinPaylinesWinframes ) ;
			
			_task.registerMicroTask ( MicrotaskKeyShorting.BASE_GAME_SHORT_WIN, MicrotaskSortBaseGameLinesDescending ) ; 
			_task.registerMicroTask ( MicrotaskKeyShorting.FREE_GAME_SHORT_WIN, MicrotaskSortFreeGameLinesDescending ) ;
			
			_task.registerMicroTask ( MicrotaskKeyCJ.BIGWIN_TICKUP_END, MicrotaskBigwinTickupEnd ) ;
			
		}
		
		private function registerHelpScreenTasks () : void
		{
			_task.registerMicroTask ( MicrotaskKeyHelpScreen.SHOW_GAME_HELP_SCREEN , MicrotaskShowHelpScreenCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyHelpScreen.HIDE_GAME_HELP_SCREEN , MicrotaskHideHelpScreenCJ ) ;
			
			_task.registerMicroTask ( MicrotaskKeyHelpScreen.SHOW_NEXT_GAME_HELP_SCREEN , MicrotaskShowNextHelpScreen ) ;
			_task.registerMicroTask ( MicrotaskKeyHelpScreen.SHOW_PREV_GAME_HELP_SCREEN , MicrotaskShowPrevHelpScreen ) ;
		}
		
		private function registerLineIdentifierTasks () : void
		{
			_task.registerMicroTask ( MicrotaskKeyCJ.LINE_IDENTIFIER_SHOW_PAYLINE, MicrotaskLineIdentifierShowPaylineCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyCJ.LINE_IDENTIFIER_HIDE_PAYLINE, MicrotaskLineIdentifierHidePaylineCJ ) ;
		}
		
		
		private function registerGameKillTasks () : void
		{
			_task.registerMicroTask ( MicrotaskKeyKill.KILL_BASEGAME, MicrotaskKillBaseGame ) ;
			_task.registerMicroTask ( MicrotaskKeyKill.KILL_BASEGAME_WIN_PRESENTATION, MicrotaskKillbaseGameWinPresentation ) ;
			_task.registerMicroTask ( MicrotaskKeyKill.KILL_FREEGAME, MicrotaskKillFreeGameCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyKill.KILL_FREEGAME_WIN_PRESENTATION, MicrotaskKillFreeGameWinPresentation ) ;
		}
		
		private function registerBrokenGames () : void
		{
			_task.registerMicroTask ( MicrotaskKeyBrokenGame.BROKEN_GAME_SHOW_POPUP , MicrotaskShowBrokenGamePopup ) ;
			_task.registerMicroTask ( MicrotaskKeyBrokenGame.BROKEN_GAME_PLAY , MicrotaskPlayBrokenGame ) ;
		}
		
		private function registerFreeGameXtras() : void
		{
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.ANIMATE_FREEGAME_TRIGGERING , MicrotaskAnimateFreegameTriggering ) ;
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.STOP_FREEGAME_TRIGGERING , MicrotaskStopFreegameTriggeringAndProceed ) ;
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.SHOW_FREEGAME_INTRO_SCREEN , MicrotaskFreeGameIntroScreen ) ;
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.SHOW_FREEGAME_OUTRO_SCREEN , MicrotaskFreeGameOutroScreenCJ ) ;
			
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.ANIMATE_RETRIGGER_TRIGGERING , MicrotaskAnimateRetriggerTriggeringCJ ) ;
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.STOP_RETRIGGER_TRIGGERING , MicrotaskStopRetriggerTriggeringAndProceed ) ;
			
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.SET_BASEGAME_SCREEN_WITH_FREEGAME_INTRO , MicrotaskBaseGameScreenWithFreeGameIntro ) ;
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.SET_FREEGAME_SCREEN_WITH_FREEGAME_INTRO , MicrotaskFreeGameScreenWithFreeGameIntro ) ;
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.SET_FREEGAME_SCREEN_FOR_PLAY, MicrotaskFreeGamePlayScreen ) ; 
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.SET_BASEGAME_SCREEN_WITH_FREEGAME_OUTRO , MicrotaskBaseGameScreenWithFreeGameOutro ) ;
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.SET_FREEGAME_SCREEN_WITH_FREEGAME_OUTRO , MicrotaskFreeGameScreenWithFreeGameOutro ) ;
			_task.registerMicroTask ( MicrotaskKeyFreatureUI.SHOW_RETRIGGER_SCREEN , MicrotaskRetriggerScreen ) ;
			
		}
	}
}