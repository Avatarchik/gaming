package com.cwh.WhiteBuffalo.register
{
	import flash.external.ExternalInterface;
	
	import ingenuity.amaya.model.key.ProxyKeyAmaya;
	import ingenuity.amaya.model.key.ProxyKeyCJ;
	import ingenuity.amaya.model.property.ModelPropertyCJ;
	import ingenuity.amaya.model.proxy.ModelCJ;
	import ingenuity.amaya.model.proxy.ModelGameAsets;
	import ingenuity.application.manager.ModelManager;
	import ingenuity.casino.slotgame.model.config.key.ProxyKeyGameConfig;
	import ingenuity.casino.slotgame.model.config.proxy.ModelBasegameSpinDetails;
	import ingenuity.casino.slotgame.model.config.proxy.ModelBetMaxDetails;
	import ingenuity.casino.slotgame.model.config.proxy.ModelBetOption;
	import ingenuity.casino.slotgame.model.config.proxy.ModelFreegameSpinDetails;
	import ingenuity.casino.slotgame.model.config.proxy.ModelGameConsole;
	import ingenuity.casino.slotgame.model.config.proxy.ModelPayoutDetails;
	import ingenuity.casino.slotgame.model.config.proxy.ModelSlotgameDetails;
	import ingenuity.casino.slotgame.model.config.proxy.ModelSound;
	import ingenuity.casino.slotgame.model.config.proxy.ModelWinPresentation;
	import ingenuity.casino.slotgame.model.game.key.ProxyKeyGame;
	import ingenuity.casino.slotgame.model.game.proxy.ModelBrokenGame;
	import ingenuity.casino.slotgame.model.game.proxy.ModelCurrentBetDetails;
	import ingenuity.casino.slotgame.model.game.proxy.ModelCurrentReelDetails;
	import ingenuity.casino.slotgame.model.game.proxy.ModelCurrentState;
	import ingenuity.casino.slotgame.model.game.proxy.ModelFreeGame;
	import ingenuity.casino.slotgame.model.game.proxy.ModelWinCollectionDetails;
	import ingenuity.casino.slotgame.model.other.key.ProxyKeyOther;
	import ingenuity.casino.slotgame.model.other.proxy.ModelAnticipation;
	import ingenuity.casino.slotgame.model.other.proxy.ModelAutoplay;
	import ingenuity.casino.slotgame.model.other.proxy.ModelHelpScreenDetails;

	public class RegisterModel
	{
		private var _model : ModelManager	=	ModelManager.instance() ;
		
		function RegisterModel ()
		{
			registerGameConfig ();
			registerAmayaModel();
			registerGameModel();
			registerHelpScreenModel () ;
			registerWinCollectionModel () ; 
			registerCJModel();
			
		}
		
		private function registerCJModel():void
		{
			_model.registerModel ( ProxyKeyCJ.CJ_MODEL , ModelCJ ) ;
			var xml : XML = new XML( "<XML/>") ;
			xml.autoplaystatus = 'false';
			xml.bigwintickupend = 'false';
			_model.updateProxy( ProxyKeyCJ.CJ_MODEL, xml );
		}
		
		
		private function registerGameConfig () : void
		{
			_model.registerModel ( ProxyKeyGameConfig.SLOT_PROPERTIES, ModelSlotgameDetails ) ;
			_model.registerModel ( ProxyKeyGameConfig.PAYOUT_PROPERTIES , ModelPayoutDetails ) ;
			_model.registerModel ( ProxyKeyGameConfig.BET_OPTIONS, ModelBetOption ) ;
			_model.registerModel ( ProxyKeyGameConfig.SPIN_PROPERTIES, ModelBasegameSpinDetails ) ;
			_model.registerModel ( ProxyKeyGameConfig.FREESPIN_PROPERTIES, ModelFreegameSpinDetails ) ;
			_model.registerModel ( ProxyKeyGameConfig.BET_MAX_PROPERTIES , ModelBetMaxDetails ) ;
			_model.registerModel ( ProxyKeyGameConfig.WIN_PRESENTATION, ModelWinPresentation ) ;
			_model.registerModel ( ProxyKeyGameConfig.SOUND, ModelSound ) ;
			_model.registerModel ( ProxyKeyGameConfig.GAME_CONSOLE, ModelGameConsole ) ;
		}
		
		private function registerAmayaModel () : void
		{
			_model.registerModel ( ProxyKeyAmaya.GAME_ASSETS , ModelGameAsets ) ;
		}
		
		private function registerGameModel () : void
		{
			_model.registerModel ( ProxyKeyGame.CURRENT_BET_DETAILS, ModelCurrentBetDetails ) ;
			_model.registerModel ( ProxyKeyGame.CURRENT_REEL_DETAILS , ModelCurrentReelDetails );
			_model.registerModel ( ProxyKeyGame.CURRENT_STATE , ModelCurrentState );
		}
		
		private function registerHelpScreenModel () : void
		{
			_model.registerModel ( ProxyKeyOther.HELP_SCREEN_DETAILS, ModelHelpScreenDetails ) ;
			_model.registerModel ( ProxyKeyOther.AUTOPLAY, ModelAutoplay ) ;
			_model.registerModel ( ProxyKeyOther.ANTICIPATION , ModelAnticipation ) ;
		}
		
		private function registerWinCollectionModel () : void
		{
			_model.registerModel ( ProxyKeyGame.WIN_COLLECTION_DETAILS , ModelWinCollectionDetails ) ;
			_model.registerModel ( ProxyKeyGame.BROKEN_GAMES , ModelBrokenGame ) ;
			_model.registerModel ( ProxyKeyGame.FREE_GAMES_DETAILS , ModelFreeGame ) ;
		}
		
	}
}