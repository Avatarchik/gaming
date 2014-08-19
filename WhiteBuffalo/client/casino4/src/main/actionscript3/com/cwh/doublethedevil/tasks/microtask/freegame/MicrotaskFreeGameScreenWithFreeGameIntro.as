package com.cwh.WhiteBuffalo.tasks.microtask.freegame
{
	import com.cwh.WhiteBuffalo.GameScreens;
	
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.manager.UIManager;
	import ingenuity.application.taskmanager.base.IMicrotask;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyBetPerLine;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyConsole;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyFeatureUI;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyMessage;
	
	public class MicrotaskFreeGameScreenWithFreeGameIntro implements IMicrotask
	{
		private var _mediator	: MediatorManager	=	MediatorManager.instance() ;
		
		public function executeTask():void
		{
			createScreen ();
			updateButtonPanel () ;
		}
		
		private function createScreen () : void
		{
			var arrScene : Vector.<String>	= new Vector.<String>();
			arrScene.push (GameScreens.FREE_GAME_BACKGROUND);
			arrScene.push (GameScreens.BASE_GAME_BUTTONPANNEL);
			arrScene.push (GameScreens.BET_PERLINE_BET_COLLECTION);
			arrScene.push (GameScreens.SLOT_GAME_GRID);
			arrScene.push (GameScreens.GAME_MESSAGES_FIELDS ) ;
			arrScene.push (GameScreens.FREE_GAME_INTRO_POPUP ) ;
			
			UIManager.instance().createNewScreen ( arrScene ) ;
		}
		
		private function updateButtonPanel () : void
		{
			_mediator.createView ( MediatorKeyFeatureUI.FREEGAME_INTRO_SCREEN ) ;
			_mediator.createView ( MediatorKeyConsole.FREEGAME_SPIN_STOP_BUTTON ) ;
			_mediator.createView ( MediatorKeyConsole.FREEGAME_WIN_METER ) ;
			_mediator.createView ( MediatorKeyConsole.FREEGAME_COUNTER ) ;
			_mediator.createView ( MediatorKeyMessage.FREE_GAME_PROMO_MESSAGE_GRAPHIC ) ;
			
		}
	}
}