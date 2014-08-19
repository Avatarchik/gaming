package com.cwh.WhiteBuffalo.ui.view.promomessage
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	
	import ingenuity.application.mediator.IView;
	import ingenuity.application.utility.localization.LanguageXML;
	
	public class ViewLocalizeFreeGamePromoMessage implements IView
	{
		private var _clip	: MovieClip ;
		private var _Lang	: LanguageXML	= LanguageXML.instance() ;
		
		
		public function set clip(clip:DisplayObject):void
		{
			_clip	= clip as MovieClip ;
		}
		
		public function createView():void
		{
			_clip.freepromo1.text1.text			= _Lang.getPromoTestForID("FreeGamePromo1" );
		}
	}
}