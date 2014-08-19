package com.cwh.WhiteBuffalo.ui.view.infoscreen
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	
	import ingenuity.application.mediator.IView;
	import ingenuity.application.utility.localization.LanguageXML;
	
	public class ViewLocalizeHelpPayout_1 implements IView
	{
		
		private var _clip	: MovieClip ;
		private var _Lang	: LanguageXML = LanguageXML.instance(); 
		
		public function set clip(clip:DisplayObject):void
		{
			_clip = clip as MovieClip;
		}
		
		public function createView():void
		{
			_clip.payout_1.text			= _Lang.getHelpTextForID ( "Payout_1_1" )
			_clip.payout_2.text			= _Lang.getHelpTextForID ( "Payout_1_2" );
			_clip.payout_3.text			= _Lang.getHelpTextForID ( "Payout_1_3" );
			_clip.payout_4.text			= _Lang.getHelpTextForID ( "Payout_1_4" );
			_clip.payout_5.text			= _Lang.getHelpTextForID ( "Payout_1_5" );
			_clip.payout_6.text			= _Lang.getHelpTextForID ( "Payout_1_6" );
			_clip.payout_7.text			= _Lang.getHelpTextForID ( "Payout_1_7" );
			_clip.payout_8.text			= _Lang.getHelpTextForID ( "Payout_1_8" )
			_clip.payout_9.text			= _Lang.getHelpTextForID ( "Payout_1_9" );
			_clip.payout_10.text		= _Lang.getHelpTextForID ( "Payout_1_10" );
			_clip.payout_11.text		= _Lang.getHelpTextForID ( "Payout_1_11" );
			_clip.payout_12.text		= _Lang.getHelpTextForID ( "Payout_1_12" );
			_clip.payout_13.text		= _Lang.getHelpTextForID ( "Payout_1_13" );
			_clip.payout_14.text		= _Lang.getHelpTextForID ( "Payout_1_14" );
			_clip.payout_15.text		= _Lang.getHelpTextForID ( "Payout_1_15" );
		}
	}
}