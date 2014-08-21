package com.cwh.MonarchSun.tasks.microtask.task.anticipation
{
	import ingenuity.casino.slotgame.tasks.microtask.task.anticipation.MicrotaskFreeGameAnticipationScatter;
	
	public class MicrotaskFreeGameAnticipationScatterMonarchSun extends MicrotaskFreeGameAnticipationScatter
	{
		override protected function setSpinnificationAvailablityAndDuration (symbolprop : Object,  reel : uint ) : void 
		{
			var preBool : Boolean;
			if(reel != 0)
			{
				preBool = scatterAvilableInPrevious(reel);
			}
			if ( symbolprop.mapagainst == symbolprop.appears && preBool)
			{
				_arrSpinificationArray [ reel ] = { duration : _spinificationDuration , available : true };
			}
		}
		
		private function scatterAvilableInPrevious(reel : uint):Boolean
		{
			var i : int = reel - 1;
			while (i >= 0)
			{
				if(!_arrAnticipationArray[i].available)
				{
					return false;
				}	
				i--;
			}
			return true;	
		}
	}
}