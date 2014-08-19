package com.cwh.WhiteBuffalo.ui.view.infoscreen
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	
	import ingenuity.application.mediator.IView;
	import ingenuity.application.utility.localization.LanguageXML;
	
	public class ViewLocalizeHelpFreeGameInfo implements IView
	{
		private var _clip	: MovieClip ;
		private var _Lang	: LanguageXML = LanguageXML.instance(); 
		
		public function set clip(clip:DisplayObject):void
		{
			_clip = clip as MovieClip;
		}
		
		public function createView():void
		{
			_clip.freegameinfotext_1.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text1" )
			_clip.freegameinfotext_2.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text2" );
			_clip.freegameinfotext_3.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text3" );
			_clip.freegameinfotext_4.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text4" );
			_clip.freegameinfotext_5.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text5" );
			_clip.freegameinfotext_6.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text6" );
			_clip.freegameinfotext_7.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text7" );
			_clip.freegameinfotext_8.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text8" )
			_clip.freegameinfotext_9.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text9" );
			_clip.freegameinfotext_10.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text10" );
			_clip.freegameinfotext_11.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text11" );
			_clip.freegameinfotext_12.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text12" );
			_clip.freegameinfotext_13.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text13" );
			_clip.freegameinfotext_14.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text14" );
			_clip.freegameinfotext_15.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text15" );
			_clip.freegameinfotext_16.text			= _Lang.getHelpTextForID ( "Free_Game_Rules_Text16" );
		}
	}
}