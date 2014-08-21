package com.cwh.MonarchSun.tasks.microtask.register
{
	
	import com.cwh.MonarchSun.localization.LocalizeBigWin;
	import com.cwh.MonarchSun.localization.LocalizeGameLabels;
	import com.cwh.MonarchSun.localization.LocalizeHelpScreen;
	import com.cwh.MonarchSun.localization.LocalizePromoMessage;
	import com.cwh.MonarchSun.register.RegisterMediator;
	
	import ingenuity.application.taskmanager.base.IMicrotask;
	
	public class MicroTaskRegisterMediator implements IMicrotask
	{
		
		public function executeTask():void
		{
			new RegisterMediator () ;
			new LocalizeHelpScreen();
			new LocalizePromoMessage();
			new LocalizeGameLabels();
			new LocalizeBigWin();
		}
	}
}