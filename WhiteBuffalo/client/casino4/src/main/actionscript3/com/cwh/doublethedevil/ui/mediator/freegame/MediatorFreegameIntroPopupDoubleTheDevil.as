package com.cwh.WhiteBuffalo.ui.mediator.freegame
{
	import com.greensock.TweenLite;
	
	import flash.display.MovieClip;
	
	import ingenuity.application.media.sound.SoundManager;
	import ingenuity.casino.slotgame.model.game.key.ProxyKeyGame;
	import ingenuity.casino.slotgame.model.game.property.ModelFreeGameProperty;
	import ingenuity.casino.slotgame.tasks.macrotask.key.MacrotaskKeyWinPresentation;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyAutoplay;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyFreatureUI;
	import ingenuity.casino.slotgame.tasks.microtask.key.MicrotaskKeyWinPresentation;
	import ingenuity.casino.slotgame.tasks.microtask.task.win.sequence.MicrotaskGameWinSequenceFactory;
	import ingenuity.casino.slotgame.ui.mediator.win.popup.MediatorFreeGameIntroPopup;
	
	public class MediatorFreegameIntroPopupWhiteBuffalo extends MediatorFreeGameIntroPopup
	{
		override protected function setFreegameWonAmount():void
		{
			var won:String;
			if (_view.freeGameWonField != null){
				won = _model.getDataFromProxy(ProxyKeyGame.FREE_GAMES_DETAILS, ModelFreeGameProperty.FREE_GAMES_TOTAL_WON_COUNT);
				won = String(Number(won)-1);
				_view.freeGameWonField.text = won;
			};
		}
		
		override protected function animateShowFreegamePopup () : void
		{
			_view.freegameIntroClip.visible	= true;
			_task.executeMicrotask ( MicrotaskKeyFreatureUI.SET_FREEGAME_SCREEN_WITH_FREEGAME_INTRO );
			showTransition();
		}
		
		protected function showTransition():void
		{
			var introClip	: MovieClip	=	_view.freegameIntroClip as MovieClip;
			introClip.mcFireAnim.gotoAndPlay('startAnim');
			SoundManager.instance().playEffectAudio('SndFire',1);
			TweenLite.delayedCall(3,showFreeGameConsole );
		}
		
		protected function showFreeGameConsole():void
		{
			var introClip	: MovieClip	=	_view.freegameIntroClip as MovieClip;
			introClip.mcFireAnim.gotoAndStop(1);
			introClip.FreeGameClip.gotoAndPlay('startAnim');
			TweenLite.delayedCall(1.1,showFreegameScreen );
			
		}
		
		protected function showFreegameScreen():void
		{
			var introClip	: MovieClip	=	_view.freegameIntroClip as MovieClip;
			introClip.FreeGameClip.gotoAndStop(1);
			_view.freegameIntroClip.visible	= false;
			//_sound.playBackgroundAudio("FreegameBG");
			_task.executeMicrotask ( MicrotaskKeyAutoplay.STOP_FEATURE);
			_task.executeMicrotask ( MicrotaskKeyFreatureUI.SET_FREEGAME_SCREEN_FOR_PLAY );
			_task.executeMicrotask ( MicrotaskKeyWinPresentation.GAME_WIN_SEQUENCE_FACTORY ) ;
		}
		
		
		override protected function hideFreeGamePopup () : void
		{
		}
		
		override protected function onFreeGamePopupHidden():void
		{
		}
		
	}
}