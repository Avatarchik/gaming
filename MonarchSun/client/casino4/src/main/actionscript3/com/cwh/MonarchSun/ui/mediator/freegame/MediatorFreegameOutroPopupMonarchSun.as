package com.cwh.MonarchSun.ui.mediator.freegame
{
	import flash.external.ExternalInterface;
	
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.media.sound.SoundType;
	import ingenuity.casino.slotgame.model.game.key.ProxyKeyGame;
	import ingenuity.casino.slotgame.model.game.property.ModelCurrentBetDetailsProperty;
	import ingenuity.casino.slotgame.model.game.property.ModelFreeGameProperty;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyMessage;
	import ingenuity.casino.slotgame.ui.key.MediatorKeyWinPresentation;
	import ingenuity.casino.slotgame.ui.mediator.win.popup.MediatorFreeGameOutroPopup;
	
	public class MediatorFreegameOutroPopupMonarchSun extends MediatorFreeGameOutroPopup
	{
		
		private var _mediator : MediatorManager = MediatorManager.instance();
		
		override protected function animateShowFreegamePopup():void
		{
			super.animateShowFreegamePopup();
			//_sound.stopAndClearAudio('FreegameBG',SoundType.BACKGROUND);
		}
		
		override public function updateView():void
		{
			var freeGameWon:Number = _model.getDataFromProxy(ProxyKeyGame.FREE_GAMES_DETAILS, ModelFreeGameProperty.TOTAL_WON_AMOUNT);
			if(freeGameWon > 0)
			{
				playShowPopupSound();
				animateShowFreegamePopup();
			}
			else
			{
				onFreeGamePopupHidden();
			}
		}
		
		override protected function onFreeGamePopupHidden():void
		{
			super.onFreeGamePopupHidden();
			_mediator.updateView(MediatorKeyWinPresentation.FREE_GAME_END_BIG_WIN_CELEBRATION);
		}

	}
}