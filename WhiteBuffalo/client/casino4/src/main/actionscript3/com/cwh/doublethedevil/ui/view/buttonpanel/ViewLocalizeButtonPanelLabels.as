package com.cwh.WhiteBuffalo.ui.view.buttonpanel
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	import flash.external.ExternalInterface;
	
	import ingenuity.application.mediator.IView;
	import ingenuity.application.utility.localization.LanguageXML;
	
	public class ViewLocalizeButtonPanelLabels implements IView
	{
		private var _clip	: MovieClip ;
		private var _Lang	: LanguageXML	= LanguageXML.instance() ;
		
		public function set clip(clip:DisplayObject):void
		{
			_clip	= clip as MovieClip ;
		}
		
		public function createView():void
		{
			_clip.mcBasegameButtonPanelText.totalbet.text			= _Lang.getLabelForID("BaseGameTotalBetLabel")
			_clip.mcBasegameButtonPanelText.creditBalance.text		= _Lang.getLabelForID("BaseGameCreditBetLabel");
			_clip.mcBasegameButtonPanelText.totalwin.text			= _Lang.getLabelForID("BaseGameTotalWinLabel")	
			_clip.btnSpin.label.spin.text							= _Lang.getLabelForID("BaseGameSpinButton")
			_clip.btnStopAutoPlay.label.autoplay_stop.text			= _Lang.getLabelForID("BaseGameStopAutoPlayLabel")	
			_clip.btnMenu.label.menu.text							= _Lang.getLabelForID("BaseGameMenuButton")
			_clip.MenuButtonHolder.btnSettings.label.settings.text	= _Lang.getLabelForID("BaseGameSettingsButton")	
			_clip.MenuButtonHolder.btnInfo.label.pays.text			= _Lang.getLabelForID("BaseGameHelpScreenButton")	
			
		}
	}
}