package com.cwh.WhiteBuffalo.localization
{
	
	import com.cwh.WhiteBuffalo.ui.key.MediatorKeyLocalization;
	import com.cwh.WhiteBuffalo.ui.mediator.promomessage.MediatorLocalizationFreeGamePromoMessage;
	import com.cwh.WhiteBuffalo.ui.mediator.promomessage.MediatorLocalizePromoMessage;
	import com.cwh.WhiteBuffalo.ui.view.promomessage.ViewLocalizeFreeGamePromoMessage;
	import com.cwh.WhiteBuffalo.ui.view.promomessage.ViewLocalizePromoMessage;
	
	import flash.display.DisplayObject;
	
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.media.graphics.ComponentLibrary;

	public class LocalizePromoMessage
	{
		private var _mediator	: MediatorManager	= MediatorManager.instance() ;
		private var _component	: ComponentLibrary	= ComponentLibrary.instance() ;
		
		public function LocalizePromoMessage()
		{
			registerMediator();
			setTextForLocalization();
		}
		
		private function registerMediator () : void
		{
			var promoMessage		  	: DisplayObject	= _component.getComponent( "BaseGamPromoMessage" );	
			var freeGamepromoMessage	: DisplayObject	= _component.getComponent( "FreeGamPromoMessage" );
			
			_mediator.registerMediator ( MediatorKeyLocalization.PROMO_MESSAGE, MediatorLocalizePromoMessage, ViewLocalizePromoMessage,  promoMessage );
			_mediator.registerMediator ( MediatorKeyLocalization.FREE_PROMO_MESSAGE, MediatorLocalizationFreeGamePromoMessage, ViewLocalizeFreeGamePromoMessage,  freeGamepromoMessage );
			
		}
		
		private function setTextForLocalization () : void
		{
			_mediator.createView( MediatorKeyLocalization.PROMO_MESSAGE) ;
			_mediator.createView( MediatorKeyLocalization.FREE_PROMO_MESSAGE) ;
			
		}
	}
}