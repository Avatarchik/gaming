package com.cwh.WhiteBuffalo.tasks.microtask.initialize
{
	import com.cwh.WhiteBuffalo.GameScreens;
	
	import ingenuity.application.manager.UIManager;
	import ingenuity.application.taskmanager.base.IMicrotask;
	
	public class MicrotaskDefaultGameScreen implements IMicrotask
	{
		
		public function executeTask():void
		{
			var vtr : Vector.<String> = new Vector.<String> ();
			vtr.push ( GameScreens.BASE_GAME_BACKGROUND );
			
			vtr.push ( GameScreens.SLOT_GAME_GRID );
			
			vtr.push ( GameScreens.PAYLINES_COLLECTION );
			vtr.push ( GameScreens.WIN_FRAME_COLLECTION ) ;
			vtr.push ( GameScreens.BET_PERLINE_BET_COLLECTION );
			vtr.push ( GameScreens.BASE_GAME_BUTTONPANNEL );
			vtr.push ( GameScreens.GAME_MESSAGES_FIELDS ) ;
			vtr.push ( GameScreens.GAME_PROMO_MESSAGES );
			vtr.push ( GameScreens.BIG_WIN_CELEBRATION	);
			vtr.push ( GameScreens.SETTING_BUTTONPANNEL );
			
			UIManager.instance().createNewScreen ( vtr );
		}
	}
}