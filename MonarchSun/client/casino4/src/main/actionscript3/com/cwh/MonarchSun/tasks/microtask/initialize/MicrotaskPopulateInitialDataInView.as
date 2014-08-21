package com.cwh.MonarchSun.tasks.microtask.initialize
{
	import ingenuity.amaya.ui.mediator.key.MediatorKeyCJ;
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.taskmanager.base.IMicrotask;
	import ingenuity.casino.slotgame.model.feature.key.ProxyKeyBonus;
	import ingenuity.casino.slotgame.model.feature.proxy.ModelBonusGamePlay;
	import ingenuity.casino.slotgame.model.game.property.ModelBonusGameProperty;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyAutoplay;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyBetPerLine;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyConsole;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyFeatureUI;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyGrid;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyMessage;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyReels;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyWinPresentation;
	
	public class MicrotaskPopulateInitialDataInView implements IMicrotask
	{
		private var _mediator : MediatorManager = MediatorManager.instance() ;
		
		public function executeTask():void
		{
			_mediator.createView ( MediatorKeyConsole.CONSOLE_METER ) ;
			_mediator.createView ( MediatorKeyConsole.BET_MAX_BUTTON ) ;
			_mediator.createView ( MediatorKeyConsole.INFO_BUTTON ) ;
			_mediator.createView ( MediatorKeyConsole.LINE_BET_METER ) ;
			_mediator.createView ( MediatorKeyConsole.DENOMINATION_METER ) ;
			
			_mediator.createView ( MediatorKeyConsole.PAYLINES_METER ) ;		
			_mediator.createView ( MediatorKeyConsole.SPIN_STOP_BUTTON ) ;
			_mediator.createView ( MediatorKeyConsole.TOTAL_BET_METER ) ;
			_mediator.createView ( MediatorKeyConsole.TOTAL_WIN_METER ) ;
			_mediator.createView ( MediatorKeyConsole.GAME_BALANCE_METER ) ;
			
			_mediator.createView ( MediatorKeyBetPerLine.LINE_IDENTIFIERS ) ;
			_mediator.createView ( MediatorKeyBetPerLine.SELECTED_PAYLINES ) ;
			
			_mediator.createView ( MediatorKeyReels.REEL_1_INITIAL_DISPLAY ) ;
			_mediator.createView ( MediatorKeyReels.REEL_2_INITIAL_DISPLAY ) ;
			_mediator.createView ( MediatorKeyReels.REEL_3_INITIAL_DISPLAY ) ;
			_mediator.createView ( MediatorKeyReels.REEL_4_INITIAL_DISPLAY ) ;
			_mediator.createView ( MediatorKeyReels.REEL_5_INITIAL_DISPLAY ) ;
			
			_mediator.createView ( MediatorKeyGrid.GRID_BASEGAME_SHOW_HIDE_MASK) ;
			
			_mediator.createView ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_WIN_FRAMES ) ;
			_mediator.createView ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_PAY_LINES ) ;
			_mediator.createView ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_SYMBOL_LINE_MASK ) ;
			_mediator.createView ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_LINE_SYMBOLS_FIRST_CYCLE ) ;
			_mediator.createView ( MediatorKeyWinPresentation.BASE_GAME_WIN_SHOW_LINE_SYMBOLS_REPEAT_CYCLES ) ;
			
			_mediator.createView ( MediatorKeyMessage.BASE_GAME_PLAYER_MESSAGE ) ;
			
			_mediator.createView ( MediatorKeyFeatureUI.FREEGAME_TRIGGERING_FRAMES ) ;
			_mediator.createView ( MediatorKeyFeatureUI.FREEGAME_TRIGGERING_SYMBOL ) ;
			
			_mediator.createView ( MediatorKeyAutoplay.CONTROLS ) ;
			_mediator.createView ( MediatorKeyAutoplay.COUNTER ) ;
			
			_mediator.createView (	MediatorKeyMessage.BASE_GAME_PROMO_MESSAGE_GRAPHIC);
			_mediator.createView (	MediatorKeyMessage.FREE_GAME_PROMO_MESSAGE_GRAPHIC);
			
			_mediator.createView ( MediatorKeyCJ.LINE_BET_METER ) ;
			_mediator.createView ( MediatorKeyCJ.AUTOPLAY_COUNTER ) ;
			
			_mediator.createView ( MediatorKeyCJ.AUTOPLAY_COUNTER ) ;
			
			
			_mediator.createView ( MediatorKeyWinPresentation.BASE_GAME_SHOW_ALL_WIN_LINES);
			
		}
	}
}