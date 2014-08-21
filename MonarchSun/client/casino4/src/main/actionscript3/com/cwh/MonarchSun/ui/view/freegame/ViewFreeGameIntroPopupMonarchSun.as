package com.cwh.MonarchSun.ui.view.freegame
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	import flash.text.TextField;
	
	import ingenuity.application.mediator.IView;
	import ingenuity.application.utility.localization.LanguageXML;
	import ingenuity.casino.slotgame.ui.iView.popup.IFreeGameIntro;
	
	public class ViewFreeGameIntroPopupMonarchSun implements IFreeGameIntro
	{
		protected var _clip	: MovieClip
		protected var _lang	: LanguageXML	= LanguageXML.instance() ;
		
		public function set clip ( clip : DisplayObject ) : void
		{
			_clip	= clip as MovieClip ;
		}
		
		public function get freeGameWonField () : TextField 
		{
			return _clip.FreeGameClip.tweenClip.freegamewon.freeGamesLeft ;
		}
			
		public function get freeGameMultiplierField () : TextField
		{
			return null ;
		}
		public function get freegameIntroClip () : DisplayObject 
		{
			return _clip ;
		}
			
		public function get buttonContinue () : MovieClip 	
		{
			return null ;
		}	
		
		public function createView():void
		{
			_clip.FreeGameClip.tweenClip.mcCont.labels.txt1.text = _lang.getLabelForID("FreeGameIntroPopupText4");
			_clip.FreeGameClip.tweenClip.mcCont.labels.txt2.text = _lang.getLabelForID("FreeGameIntroPopupText2");
			_clip.FreeGameClip.tweenClip.mcCont.labels.txt3.text = _lang.getLabelForID("FreeGameIntroPopupText3");
		}
	}
}