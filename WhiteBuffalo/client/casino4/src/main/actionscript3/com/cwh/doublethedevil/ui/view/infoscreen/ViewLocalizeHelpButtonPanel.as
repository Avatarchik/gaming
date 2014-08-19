package com.cwh.WhiteBuffalo.ui.view.infoscreen
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	
	import ingenuity.application.mediator.IView;
	import ingenuity.application.utility.localization.LanguageXML;
	
	public class ViewLocalizeHelpButtonPanel implements IView
	{
		private var _clip	: MovieClip ;
		private var _Lang	: LanguageXML	= LanguageXML.instance() ;
		
		public function set clip(clip:DisplayObject):void
		{
			_clip	= clip as MovieClip ;
		}
		
		public function createView():void
		{
			_clip.btnShowPrevInfoScreen.level.backBtnText.text	= _Lang.getHelpTextForID ( "HelpScreenBackButton" );
			_clip.btnInfoHide.level.exitBtnText.text			= _Lang.getHelpTextForID ( "HelpScreenExitButton" );
			_clip.btnShowNextInfoScreen.level.nextBtnText.text	= _Lang.getHelpTextForID ( "HelpScreenNextButton" );
		}
	}
}