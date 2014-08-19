package com.cwh.WhiteBuffalo.ui.mediator.meter
{
	import flash.external.ExternalInterface;
	
	import ingenuity.casino.slotgame.model.game.key.ProxyKeyGame;
	import ingenuity.casino.slotgame.model.game.property.ModelCurrentBetDetailsProperty;
	import ingenuity.casino.slotgame.ui.mediator.console.MediatorBetLineMeter;
	
	public class MediatorBetLineMeterWhiteBuffalo extends MediatorBetLineMeter
	{
		override protected function updateFieldValue():void
		{
			var value:String;
			if (_view.totalBetPlaced != null)
			{
				value = _model.getDataFromProxy(ProxyKeyGame.CURRENT_BET_DETAILS, ModelCurrentBetDetailsProperty.SINGLE_BET_PLACED);
				_view.totalBetPlaced.text = value;
			}
		}
	}
}