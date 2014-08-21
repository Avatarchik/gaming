package com.cwh.MonarchSun.localization
{
	import com.cwh.MonarchSun.ui.key.MediatorKeyLocalization;
	import com.cwh.MonarchSun.ui.mediator.buttonpanel.MediatorLocalizeButtonPanelLabels;
	import com.cwh.MonarchSun.ui.mediator.buttonpanel.MediatorLocalizeSettingsPopupLabels;
	import com.cwh.MonarchSun.ui.view.buttonpanel.ViewLocalizeButtonPanelLabels;
	import com.cwh.MonarchSun.ui.view.buttonpanel.ViewLocalizeSettingsPopupLabels;
	
	import flash.display.DisplayObject;
	
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.media.graphics.ComponentLibrary;

	public class LocalizeGameLabels
	{
		private var _mediator	: MediatorManager	= MediatorManager.instance() ;
		private var _component	: ComponentLibrary	= ComponentLibrary.instance() ;
		
		public function LocalizeGameLabels()
		{
			registerMediator();
			setTextForLocalization();
		}
		
		private function registerMediator () : void
		{
			var buttonsPanel   		: DisplayObject	= _component.getComponent( "buttonsPanel" );
			var settingsPopUp		: DisplayObject = _component.getComponent( "settingsPopUp" );
			
			
			_mediator.registerMediator ( MediatorKeyLocalization.BASEGAME_BUTTON_PANEL, MediatorLocalizeButtonPanelLabels, ViewLocalizeButtonPanelLabels, buttonsPanel );
			_mediator.registerMediator ( MediatorKeyLocalization.BASEGAME_SETTINGS_POPUP, MediatorLocalizeSettingsPopupLabels, ViewLocalizeSettingsPopupLabels, settingsPopUp );
		}
		
		private function setTextForLocalization () : void
		{
			_mediator.createView( MediatorKeyLocalization.BASEGAME_BUTTON_PANEL) ;
			_mediator.createView( MediatorKeyLocalization.BASEGAME_SETTINGS_POPUP) ;
		}
	}
}