package com.cwh.MonarchSun.ui.view.buttonpanel
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	
	import ingenuity.application.mediator.IView;
	import ingenuity.application.utility.localization.LanguageXML;
	
	public class ViewLocalizeSettingsPopupLabels implements IView
	{
		private var _clip	: MovieClip ;
		private var _Lang	: LanguageXML	= LanguageXML.instance() ;
		
		public function set clip(clip:DisplayObject):void
		{
			_clip	= clip as MovieClip ;
		}
		
		public function createView():void
		{
			_clip.mcLables.credit_value.text							= _Lang.getLabelForID("BaseGameDenominationLabel")
			_clip.mcLables.credit_perLine.text							= _Lang.getLabelForID("BaseGameLineBetLabel")
			_clip.AutoplayStartButton.label.autoplay_start.text			= _Lang.getLabelForID("BaseGameStartAutoPlayLabel")
			_clip.exit.label.exit_popup.text							= _Lang.getLabelForID("BaseGameExitSettingsPopup")
		}
	}
}