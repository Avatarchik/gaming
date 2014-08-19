package com.cwh.WhiteBuffalo.ui.view.freegame
{
	import flash.display.DisplayObject;
	import flash.display.MovieClip;
	import flash.external.ExternalInterface;
	import flash.text.TextField;
	
	import ingenuity.application.utility.localization.LanguageXML;
	import ingenuity.casino.slotgame.ui.iView.console.IFreeGameCounter;
	import ingenuity.casino.slotgame.ui.view.console.ViewFreeGameCounter;
	
	public class ViewFreeGameCounterWhiteBuffalo extends ViewFreeGameCounter
	{
		
		private var _clip:MovieClip;
		private var _lang:LanguageXML = LanguageXML.instance();
		
		override public function get freeGameWonField():TextField
		{
			return _clip.FreeSpinsLeft;
		}
		
		override public function get freeGameMultiplierField():TextField
		{
			return _clip.FreeSpinsMultiplier;
		}
		
		override public function set clip(clip:DisplayObject):void
		{
			_clip = clip as MovieClip;
		}
		
		override public function createView():void
		{
			_clip.mcTween.labels.txt1.text 	= 	_lang.getLabelForID("FreeGameIntroPopupText1");
			_clip.mcTween.labels.txt2.text 	= 	_lang.getLabelForID("FreeGameIntroPopupText2");
			_clip.mcTween.labels.txt3.text 	= 	_lang.getLabelForID("FreeGameIntroPopupText3");
		}
		
		
	}
}