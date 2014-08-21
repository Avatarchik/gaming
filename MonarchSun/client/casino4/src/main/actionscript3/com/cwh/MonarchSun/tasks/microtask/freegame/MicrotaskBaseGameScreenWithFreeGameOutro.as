package com.cwh.MonarchSun.tasks.microtask.freegame
{
	import com.cwh.MonarchSun.GameScreens;
	
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.manager.TaskManager;
	import ingenuity.application.manager.UIManager;
	import ingenuity.application.taskmanager.base.IMicrotask;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyConsolePanel;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyAutoplay;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyBetPerLine;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyConsole;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyFeatureUI;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyMessage;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyReels;
	
	public class MicrotaskBaseGameScreenWithFreeGameOutro implements IMicrotask
	{
		private var _mediator	: MediatorManager	=	MediatorManager.instance() ;
		private var _task		: TaskManager		=	TaskManager.instance() ;
		
		public function executeTask():void
		{
			createScreen ();
			updateToBaseGameReelResponse() ;
		}
		
		private function createScreen () : void
		{
			var arrScene : Vector.<String>	= new Vector.<String>();
			arrScene.push (GameScreens.BASE_GAME_BACKGROUND);
			arrScene.push (GameScreens.BASE_GAME_BUTTONPANNEL);
			arrScene.push (GameScreens.BET_PERLINE_BET_COLLECTION);
			arrScene.push (GameScreens.SLOT_GAME_GRID);
			arrScene.push (GameScreens.GAME_MESSAGES_FIELDS ) ;
			arrScene.push (GameScreens.FREE_GAME_OUTRO_POPUP ) ;			
			UIManager.instance().createNewScreen ( arrScene ) ;
		}
		private function updateToBaseGameReelResponse() : void
		{
			_mediator.updateView ( MediatorKeyReels.BASE_GAME_REEL_1_SHOW_REEL_RESPONSE ) ;
			_mediator.updateView ( MediatorKeyReels.BASE_GAME_REEL_2_SHOW_REEL_RESPONSE ) ;
			_mediator.updateView ( MediatorKeyReels.BASE_GAME_REEL_3_SHOW_REEL_RESPONSE ) ;
			_mediator.updateView ( MediatorKeyReels.BASE_GAME_REEL_4_SHOW_REEL_RESPONSE ) ;
			_mediator.updateView ( MediatorKeyReels.BASE_GAME_REEL_5_SHOW_REEL_RESPONSE ) ;
			
		}
	}
}