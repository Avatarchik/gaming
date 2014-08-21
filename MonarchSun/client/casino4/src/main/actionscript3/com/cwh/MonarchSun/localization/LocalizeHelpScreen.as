package com.cwh.MonarchSun.localization
{
	
	import com.cwh.MonarchSun.ui.key.MediatorKeyLocalization;
	import com.cwh.MonarchSun.ui.mediator.infoscreen.MediatorLocalizeHelpButtonPanel;
	import com.cwh.MonarchSun.ui.mediator.infoscreen.MediatorLocalizeHelpFreeGameInfo;
	import com.cwh.MonarchSun.ui.mediator.infoscreen.MediatorLocalizeHelpPayLineInfo;
	import com.cwh.MonarchSun.ui.mediator.infoscreen.MediatorLocalizeHelpPayout_1;
	import com.cwh.MonarchSun.ui.mediator.infoscreen.MediatorLocalizeHelpPayout_2;
	import com.cwh.MonarchSun.ui.mediator.infoscreen.MediatorLocalizeHelpPayout_3;
	import com.cwh.MonarchSun.ui.view.infoscreen.ViewLocalizeHelpButtonPanel;
	import com.cwh.MonarchSun.ui.view.infoscreen.ViewLocalizeHelpFreeGameInfo;
	import com.cwh.MonarchSun.ui.view.infoscreen.ViewLocalizeHelpPayLineInfo;
	import com.cwh.MonarchSun.ui.view.infoscreen.ViewLocalizeHelpPayout_1;
	import com.cwh.MonarchSun.ui.view.infoscreen.ViewLocalizeHelpPayout_2;
	import com.cwh.MonarchSun.ui.view.infoscreen.ViewLocalizeHelpPayout_3;
	
	import flash.display.DisplayObject;
	
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.media.graphics.ComponentLibrary;

	public class LocalizeHelpScreen
	{
		private var _mediator	: MediatorManager	= MediatorManager.instance() ;
		private var _component	: ComponentLibrary	= ComponentLibrary.instance() ;
		
		public function LocalizeHelpScreen()
		{
			registerMediator();
			setTextForLocalization();
		}
		
		private function registerMediator () : void
		{
			var payout_1 		  	: DisplayObject	= _component.getComponent( "payoutsInfo_1" );
			var payout_2 		  	: DisplayObject	= _component.getComponent( "payoutsInfo_2" );
			var payout_3		  	: DisplayObject	= _component.getComponent( "payoutsInfo_3" );
			var paylineInfo   		: DisplayObject	= _component.getComponent( "paylinesInfo" );
			var freeGameInfo  		: DisplayObject	= _component.getComponent( "freegameInfo" );
			var buttonPanel   		: DisplayObject	= _component.getComponent( "infobuttonsPanel" );
			
			_mediator.registerMediator ( MediatorKeyLocalization.HELPSCREEN_PAYLINE_INFO,   	MediatorLocalizeHelpPayLineInfo,  ViewLocalizeHelpPayLineInfo,  	paylineInfo );
			_mediator.registerMediator ( MediatorKeyLocalization.HELPSCREEN_PAYOUTS_1,        	MediatorLocalizeHelpPayout_1,     ViewLocalizeHelpPayout_1,      	payout_1 );
			_mediator.registerMediator ( MediatorKeyLocalization.HELPSCREEN_PAYOUTS_2,        	MediatorLocalizeHelpPayout_2,     ViewLocalizeHelpPayout_2,      	payout_2 );
			_mediator.registerMediator ( MediatorKeyLocalization.HELPSCREEN_PAYOUTS_3,        	MediatorLocalizeHelpPayout_3,     ViewLocalizeHelpPayout_3,      	payout_3 );			
			_mediator.registerMediator ( MediatorKeyLocalization.HELPSCREEN_FREE_GAME_INFO, 	MediatorLocalizeHelpFreeGameInfo, ViewLocalizeHelpFreeGameInfo, 	freeGameInfo );
			_mediator.registerMediator ( MediatorKeyLocalization.HELPSCREEN_BUTTON_PANEL,   	MediatorLocalizeHelpButtonPanel,  ViewLocalizeHelpButtonPanel, 		buttonPanel);
		}
		
		private function setTextForLocalization () : void
		{
			_mediator.createView( MediatorKeyLocalization.HELPSCREEN_PAYLINE_INFO) ;
			_mediator.createView( MediatorKeyLocalization.HELPSCREEN_PAYOUTS_1 ) ;
			_mediator.createView( MediatorKeyLocalization.HELPSCREEN_PAYOUTS_2 ) ;
			_mediator.createView( MediatorKeyLocalization.HELPSCREEN_PAYOUTS_3 ) ;			
			_mediator.createView( MediatorKeyLocalization.HELPSCREEN_GAME_RULE_INFO ) ;
			_mediator.createView( MediatorKeyLocalization.HELPSCREEN_FREE_GAME_INFO ) ;
			_mediator.createView( MediatorKeyLocalization.HELPSCREEN_BUTTON_PANEL ) ;
			trace ("=LocalizeHelpScreen createView=")

		}
	}
}