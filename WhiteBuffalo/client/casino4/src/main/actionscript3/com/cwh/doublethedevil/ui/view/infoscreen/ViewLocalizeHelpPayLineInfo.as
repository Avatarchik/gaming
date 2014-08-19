package com.cwh.WhiteBuffalo.ui.view.infoscreen
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	
	import ingenuity.application.mediator.IView;
	import ingenuity.application.utility.localization.LanguageXML;
	
	public class ViewLocalizeHelpPayLineInfo implements IView
	{
		
		private var _clip	: MovieClip ;
		private var _Lang	: LanguageXML = LanguageXML.instance();
		
		public function set clip(clip:DisplayObject):void
		{
			_clip	= clip as MovieClip ;
		}
		
		public function createView():void
		{
			_clip.paylines_1.text	= _Lang.getHelpTextForID ( "Paylines_1" );
			_clip.paylines_2.text	= _Lang.getHelpTextForID ( "Paylines_2" );
		}
	}
}