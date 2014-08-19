package com.cwh.WhiteBuffalo.ui.mediator.freegame
{
	import ingenuity.casino.slotgame.model.game.key.ProxyKeyGame;
	import ingenuity.casino.slotgame.model.game.property.ModelFreeGameProperty;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorFreeGameCounter;
	
	public class MediatorFreeGameCounterWhiteBuffalo extends MediatorFreeGameCounter
	{
		override public function createView():void
		{
			super.createView();
			var won:Number;
			if (_view.freeGameWonField != null)
			{
				won = _model.getDataFromProxy(ProxyKeyGame.FREE_GAMES_DETAILS, ModelFreeGameProperty.FREE_GAMES_CURRENT_TO_PLAY);
				won = ((won)>0) ? won : 0;
				_view.freeGameWonField.text = String(won-1);
			}
		}
	}
}