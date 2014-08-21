package com.cwh.MonarchSun.tasks.microtask.freegame
{
	import com.cwh.MonarchSun.GameScreens;
	
	import flash.external.ExternalInterface;
	
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.manager.ModelManager;
	import ingenuity.application.manager.TaskManager;
	import ingenuity.application.manager.UIManager;
	import ingenuity.application.media.sound.SoundManager;
	import ingenuity.application.taskmanager.base.IMicrotask;
	import ingenuity.casino.slotgame.model.config.key.ProxyKeyGameConfig;
	import ingenuity.casino.slotgame.model.config.property.ModelSoundProperty;
	import ingenuity.casino.slotgame.model.game.key.ProxyKeyGame;
	import ingenuity.casino.slotgame.model.game.property.ModelBrokenGameProperty;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyConsolePanel;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyAutoplay;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyBetPerLine;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyConsole;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyFeatureUI;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyMessage;
	
	public class MicrotaskBaseGameScreenWithFreeGameIntro implements IMicrotask
	{
		private var _mediator	: MediatorManager	=	MediatorManager.instance() ;
		private var _task		: TaskManager		=	TaskManager.instance() ;
		private var _model		: ModelManager		=	ModelManager.instance() ;
		private var _sound		: SoundManager		=	SoundManager.instance() ;
		
		public function executeTask():void
		{
			createScreen ();			
		}
		
		private function playFreegameBackgroundSound () : void
		{
			var sound : String	= _model.getDataFromProxy ( ProxyKeyGameConfig.SOUND, ModelSoundProperty.FREE_GAME_BACKGROUND ) ;
			if ( sound.length > 0 ) 
			{
				_sound.playBackgroundAudio ( sound ) ;
			}
		}
		
		private function createScreen () : void
		{
			var arrScene : Vector.<String>	= new Vector.<String>();
			arrScene.push (GameScreens.BASE_GAME_BACKGROUND);
			arrScene.push (GameScreens.BASE_GAME_BUTTONPANNEL);
			arrScene.push (GameScreens.SLOT_GAME_GRID);
			arrScene.push (GameScreens.GAME_MESSAGES_FIELDS ) ;
			arrScene.push (GameScreens.FREE_GAME_INTRO_POPUP ) ;
			
			UIManager.instance().createNewScreen ( arrScene ) ;
		}
		
		

	}
}