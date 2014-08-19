package com.cwh.WhiteBuffalo.tasks.microtask.register
{
	
	import com.cwh.WhiteBuffalo.localization.LocalizeBigWin;
	import com.cwh.WhiteBuffalo.localization.LocalizeGameLabels;
	import com.cwh.WhiteBuffalo.localization.LocalizeHelpScreen;
	import com.cwh.WhiteBuffalo.localization.LocalizePromoMessage;
	import com.cwh.WhiteBuffalo.register.RegisterMediator;
	
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