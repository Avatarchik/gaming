package com.cwh.MonarchSun {
    
    
    import com.cwh.casino4.api.GameServices;
    import com.cwh.casino4.api.events.GameFinanceUpdateEvent;
    import com.cwh.casino4.api.events.IncomingMessageEvent;
    import com.cwh.casino4.api.events.ModalDialogChangeEvent;
    import com.cwh.casino4.sdk.singleplayer.game.V2Game;
    import com.cwh.casino4.sdk.singleplayer.messaging.v2.constants.MessagingConstants;
    import com.cwh.MonarchSun.register.RegisterKeyboardMediator;
    import com.cwh.MonarchSun.register.RegisterModel;
    import com.cwh.MonarchSun.register.RegisterTasks;
    
    import flash.display.DisplayObject;
    import flash.events.Event;
    import flash.events.KeyboardEvent;
    import flash.external.ExternalInterface;
    import flash.ui.Keyboard;
    
    import ingenuity.amaya.model.key.ProxyKeyAmaya;
    import ingenuity.amaya.model.key.ProxyKeyCJ;
    import ingenuity.amaya.model.property.ModelPropertyCJ;
    import ingenuity.amaya.parser.ParseBaseGame;
    import ingenuity.amaya.parser.ParseBrokenGame;
    import ingenuity.amaya.tasks.macrotask.key.MacrotaskkeyAmaya;
    import ingenuity.amaya.tasks.microtask.keys.MicrotaskKeyCJ;
    import ingenuity.amaya.ui.mediator.key.MediatorKeyCJ;
    import ingenuity.amaya.wrapper.AmayaGameServices;
    import ingenuity.application.debug.log.Log;
    import ingenuity.application.manager.MediatorManager;
    import ingenuity.application.manager.ModelManager;
    import ingenuity.application.manager.TaskManager;
    import ingenuity.application.manager.UIManager;
    import ingenuity.application.media.graphics.AnimationLibrary;
    import ingenuity.application.media.graphics.ComponentLibrary;
    import ingenuity.application.media.graphics.LoaderInfoCollection;
    import ingenuity.application.media.sound.SoundManager;
    import ingenuity.casino.slotgame.model.config.dataform.ModelBetOptionsData;
    import ingenuity.casino.slotgame.model.config.key.ProxyKeyGameConfig;
    import ingenuity.casino.slotgame.model.game.dataform.ModelBrokenGameData;
    import ingenuity.casino.slotgame.model.game.dataform.ModelCurrentReelDetailsData;
    import ingenuity.casino.slotgame.model.game.dataform.ModelCurrentStateData;
    import ingenuity.casino.slotgame.model.game.key.ProxyKeyGame;
    import ingenuity.casino.slotgame.model.other.key.ProxyKeyOther;
    import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyInitializeGame;

    public class Game extends V2Game {

        private var _gameservices 	: AmayaGameServices = AmayaGameServices.instance() ;
		
		private var _task		: TaskManager			= TaskManager.instance() ;
		private var _ui			: UIManager				= UIManager.instance() ;
		private var _model		: ModelManager			= ModelManager.instance();
		private var _mediator	: MediatorManager		= MediatorManager.instance() ;
		private var _sound		: SoundManager			= SoundManager.instance() ;
		private var _animation	: AnimationLibrary		= AnimationLibrary.instance();
		private var _component	: ComponentLibrary		= ComponentLibrary.instance();
		private var _loaderinfo	: LoaderInfoCollection	= LoaderInfoCollection.instance() ;
		
		private var _gameServ	: GameServices  = new GameServices();
		
		public function Game()
		{
			this.addEventListener(Event.ADDED_TO_STAGE,addKeyboardEvent);
		}
		
		private function addKeyboardEvent(e:Event):void
		{
			this.removeEventListener(Event.ADDED_TO_STAGE,addKeyboardEvent);
			new RegisterKeyboardMediator(this.stage as DisplayObject);
		}
		
		
		
        override protected function startMain ( startGameResponseData:XML, openingBalance:Number ) : void 
		{
			Log.showInformation( "startMain" );
			Log.showInformation( startGameResponseData.toXMLString() ) ;
			initializeAmaya ();
			//parseBrokenGame ( startGameResponseData );
        }

        override protected function resumeMain ( resumeGameResponseData:XML, openingBalance:Number ) : void 
		{
			Log.showInformation( "resumeMain .......... " );
			Log.showInformation( resumeGameResponseData.toXMLString() ) ;
			initializeAmaya ();
			parseBrokenGame ( resumeGameResponseData );
        }
		
		private final function parseBrokenGame ( data : XML ) : void
		{
			var parser : ParseBrokenGame = new ParseBrokenGame () ;
			parser.parseResponse( data );
		}
		
		protected function initializeAmaya () : void
		{
			configureLogger () ;
			configerWrapper () ;
			setStageReference () ;
			registerClasses () ;
			populateModel ();
			configureGame ()
		}
		
		private function setStageReference () : void
		{
			UIManager.instance().stage = this;
			this.y =  17.5;
			this.addEventListener ( Event.REMOVED_FROM_STAGE, onGameRemovedFromStage) ;
		}
		
		private function onGameRemovedFromStage ( event : Event ) : void
		{
			this.removeEventListener ( Event.REMOVED_FROM_STAGE, onGameRemovedFromStage) ;
		}
		
		private function configerWrapper () : void
		{
			_gameservices.configureServices() ;
			_gameServ.addEventListener(ModalDialogChangeEvent.MODAL_DIALOG_CHANGE,changedDialogue);
		}
		
		protected function changedDialogue(event:ModalDialogChangeEvent):void
		{
			switch(event.state)
			{
				case true:
				{
					_mediator.deleteView(MediatorKeyCJ.STAGE_KEYBOARD_INTERACTIVITY);
					break;
				}
				case false:
				{
					_mediator.createView(MediatorKeyCJ.STAGE_KEYBOARD_INTERACTIVITY);
					break;
				}
			}
		}		
		
		
		private function configureGame () : void
		{
			_task.executeMacrotask ( MacrotaskkeyAmaya.LOAD_GAME_ASSETS ) ;
		}
		
		private function configureLogger () : void
		{
			_task.enableDebugExecuteMacro	= false ;
			_task.enableDebugExecuteMicro	= false ;
			_task.enableDebugInjectToMacro	= false ;
			_task.enableDebugInjectToMicro	= false ;
			_task.enableDebugRegisterMacro	= false ;
			_task.enableDebugRegisterMicro	= false ;
			_task.enableDebugSuspendMacro	= false ;
			
			_model.enableDebugGetData		= false ;
			_model.enableDebugRegister		= false ;
			_model.enableDebugUpdate		= false ;
			
			_mediator.enableDebugCreate		= false ;
			_mediator.enableDebugDelete		= false ;
			_mediator.enableDebugUpdate		= false ;
			_mediator.enableDebugRegister	= false ;
			
			_ui.enableDebugCreateScreen		= false ;
			_ui.enableDebugRemoveScreen		= false ;
			
			_loaderinfo.enableDebugAddLoaderInfo	= false ;
			_loaderinfo.enableDebugGetClip			= false ; 
			
			_component.enableDebugGetComponent		= false ;
			_component.enableDebugUpdateComponent	= false ;
			
			_animation.enableDebugGetAnimation		= false ;
			_animation.enableDebugPrepareAnimation	= false ;
			_animation.enableDebugUpdateAnimation	= false ;
			
			_sound.enableDebugPlaySound		= false ;
			_sound.enableDebugStopSound		= false ;
			
			Log.applicationInDebugMode		= false ;
		}
		
		private function registerClasses () : void
		{
			new RegisterModel ();
			new RegisterTasks ();
		}
		
		private function populateModel () : void
		{
			var config : GameConfig	= new GameConfig();
			
			_model.updateProxy ( ProxyKeyAmaya.GAME_ASSETS , config.gameAssets() );
			
			_model.updateProxy ( ProxyKeyGameConfig.SLOT_PROPERTIES   , config.gameProperties().SlotDetails[0] ) ;
			_model.updateProxy ( ProxyKeyGameConfig.PAYOUT_PROPERTIES , config.gameProperties().PayoutDetails[0] ) ;
			_model.updateProxy ( ProxyKeyGameConfig.SPIN_PROPERTIES, config.gameProperties().BaseGameSpinDetails[0] ) ;
			_model.updateProxy ( ProxyKeyGameConfig.FREESPIN_PROPERTIES, config.gameProperties().FreeGameSpinDetails[0] ) ;
			_model.updateProxy ( ProxyKeyGameConfig.BET_MAX_PROPERTIES , config.gameProperties().Console.BetMax[0] ) ;
			_model.updateProxy ( ProxyKeyGameConfig.GAME_CONSOLE , config.gameProperties().Console.BetIncremenrDecrement[0] ) ;
			_model.updateProxy ( ProxyKeyGameConfig.GAME_CONSOLE , config.gameProperties().Console.LineIncremenrDecrement[0] );
			
			_model.updateProxy ( ProxyKeyGameConfig.WIN_PRESENTATION , config.gameProperties().WinPresentationSequence[0] ) ;
			_model.updateProxy ( ProxyKeyGameConfig.WIN_PRESENTATION , config.gameProperties().LineWinPresentationAnimation[0] ) ;
			_model.updateProxy ( ProxyKeyGameConfig.SOUND , config.gameProperties().GameSound[0] ); 
			
			_model.updateProxy ( ProxyKeyOther.HELP_SCREEN_DETAILS, config.gameProperties().HelpScreen[0] ) ;
			_model.updateProxy ( ProxyKeyOther.ANTICIPATION, config.gameProperties().Anticipation[0] ) ; 
			
			_model.updateProxy ( ProxyKeyGame.CURRENT_REEL_DETAILS , config.gameProperties().SymbolCollection.BaseGameSymbols[0] ) ;
			_model.updateProxy ( ProxyKeyGame.CURRENT_REEL_DETAILS , config.gameProperties().SymbolCollection.FreeGameSymbols[0] ) ;
			_model.updateProxy ( ProxyKeyGame.CURRENT_REEL_DETAILS , basegameReelStopSequence ( config ) );
			_model.updateProxy ( ProxyKeyOther.AUTOPLAY , config.gameProperties().Console.AutoPlay[0] ) ;
			
			// Added to take BetPerLineMeter Values From GameConfig
			var xml : XML = new XML( "<XML/>") ;
			var lineBetMultiplierOptions 	:	String	=	config.amayaConfig().GameConsole.LineBetMultiplierOptions[0];
			xml [ ModelBetOptionsData.LEGAL_BET_OPTIONS ]			= lineBetMultiplierOptions ;
			_model.updateProxy( ProxyKeyGameConfig.BET_OPTIONS, xml );
			
			
		}
		
		private function basegameReelStopSequence ( config : GameConfig ) : XML
		{
			var xmllist : XMLList	= config.amayaConfig().DefaultStopSequence.BaseGameStopSequence.children() ; 
			var len		: uint		= xmllist.length ();
			var random 	: uint		= Math.floor( Math.random() * len ) ;
			var reelPos	: String	= xmllist[ random ] ; 
			var xml 	: XML		= new XML( "<"+ ModelCurrentReelDetailsData.BASE_GAME_STOP_REEL_SEQUENCE +"/>") ;
			xml.appendChild ( reelPos );
			
			return xml ;
		}

    }

}