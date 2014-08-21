package com.cwh.MonarchSun.tasks.microtask.freegame
{
	import com.cwh.MonarchSun.GameScreens;
	
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.manager.ModelManager;
	import ingenuity.application.manager.UIManager;
	import ingenuity.application.media.sound.SoundManager;
	import ingenuity.application.taskmanager.base.IMicrotask;
	import ingenuity.casino.slotgame.model.config.key.ProxyKeyGameConfig;
	import ingenuity.casino.slotgame.model.config.property.ModelSoundProperty;
	import ingenuity.casino.slotgame.model.game.dataform.ModelBrokenGameData;
	import ingenuity.casino.slotgame.model.game.key.ProxyKeyGame;
	import ingenuity.casino.slotgame.model.game.property.ModelBrokenGameProperty;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyConsole;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyFeatureUI;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyGrid;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyMessage;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyWinPresentation;
	
	public class MicrotaskFreeGamePlayScreen implements IMicrotask
	{
		private var _mediator	: MediatorManager	=	MediatorManager.instance() ;
		private var _model		: ModelManager		=	ModelManager.instance() ;
		private var _sound		: SoundManager		=	SoundManager.instance() ;
		
		public function executeTask():void
		{
			trace ( " ******** MicrotaskFreeGamePlayScreen ****** " )
			createScreen () ;
			initializeComponents() ;
			playFreegameBackgroundSound () ;
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
			arrScene.push (GameScreens.FREE_GAME_BACKGROUND);
			arrScene.push (GameScreens.SLOT_GAME_GRID);
			arrScene.push (GameScreens.PAYLINES_COLLECTION ) ;
			arrScene.push (GameScreens.WIN_FRAME_COLLECTION ) ;
			arrScene.push (GameScreens.BET_PERLINE_BET_COLLECTION);
			arrScene.push (GameScreens.FREE_GAME_CONSOLE);			
			arrScene.push (GameScreens.GAME_MESSAGES_FIELDS ) ;
			arrScene.push (GameScreens.FREE_GAME_PROMO_MESSAGES ) ;
			arrScene.push (GameScreens.BIG_WIN_CELEBRATION ) ;
			
			UIManager.instance().createNewScreen ( arrScene ) ;
		}
		
		private function initializeComponents () : void
		{
			_mediator.createView ( MediatorKeyGrid.GRID_FREEGAME_SHOW_HIDE_MASK) ;
			
			_mediator.createView ( MediatorKeyWinPresentation.FREE_GAME_WIN_SHOW_PAY_LINES ) ;
			_mediator.createView ( MediatorKeyWinPresentation.FREE_GAME_WIN_SHOW_WIN_FRAMES ) ;
			
			_mediator.createView ( MediatorKeyWinPresentation.FREE_GAME_WIN_SHOW_SYMBOL_LINE_MASK ) ;
			_mediator.createView ( MediatorKeyWinPresentation.FREE_GAME_WIN_SHOW_LINE_SYMBOLS_FIRST_CYCLE ) ;
			
			_mediator.createView ( MediatorKeyMessage.FREE_GAME_PLAYER_MESSAGE ) ;
			_mediator.createView ( MediatorKeyMessage.FREE_GAME_PROMO_MESSAGE_GRAPHIC ) ;
			
			_mediator.createView ( MediatorKeyConsole.FREEGAME_SPIN_STOP_BUTTON ) ;
			_mediator.createView ( MediatorKeyConsole.FREEGAME_WIN_METER ) ;
			_mediator.createView ( MediatorKeyConsole.FREEGAME_COUNTER ) ;
			
			_mediator.createView ( MediatorKeyFeatureUI.RETRIGGER_TRIGGERING_FRAMES ) ;
			_mediator.createView ( MediatorKeyFeatureUI.RETRIGGER_TRIGGERING_SYMBOL ) ;
			
			_mediator.createView ( MediatorKeyWinPresentation.FREE_GAME_SHOW_ALL_WIN_LINES ) ;
			
		}
	}
}