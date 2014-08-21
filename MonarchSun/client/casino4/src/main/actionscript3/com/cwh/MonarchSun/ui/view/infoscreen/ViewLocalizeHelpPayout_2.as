package com.cwh.MonarchSun.ui.view.infoscreen
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	
	import ingenuity.application.mediator.IView;
	import ingenuity.application.utility.localization.LanguageXML;
	
	public class ViewLocalizeHelpPayout_2 implements IView
	{
		
		private var _clip	: MovieClip ;
		private var _Lang	: LanguageXML = LanguageXML.instance(); 
		
		public function set clip(clip:DisplayObject):void
		{
			_clip = clip as MovieClip;
		}
		
		public function createView():void
		{
			_clip.payout_1.text			= _Lang.getHelpTextForID ( "Payout_2_1" )
		}
	}
}