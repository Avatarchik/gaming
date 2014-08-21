package com.cwh.MonarchSun.localization
{
	
	import com.cwh.MonarchSun.GameScreens;
	
	import flash.display.MovieClip;
	
	import ingenuity.amaya.ui.mediator.bigwintext.MediatorBigWinText;
	import ingenuity.amaya.ui.mediator.key.MediatorKeyCJ;
	import ingenuity.amaya.ui.view.bigwintext.ViewBigWinText;
	import ingenuity.application.manager.MediatorManager;
	import ingenuity.application.media.graphics.ComponentLibrary;

	public class LocalizeBigWin
	{
		private var _mediator	: MediatorManager	= MediatorManager.instance() ;
		private var _component	: ComponentLibrary	= ComponentLibrary.instance() ;
		
		public function LocalizeBigWin()
		{
			registerMediator();
			setTextForLocalization();
		}
		
		protected function setTextForLocalization():void
		{
			_mediator.createView ( MediatorKeyCJ.BIG_WIN_TEXT );
		}
		
		protected function registerMediator():void
		{
			var bigwin : MovieClip = _component.getComponent ( GameScreens.BIG_WIN_CELEBRATION ) as MovieClip ;
			//var bigwintext : MovieClip = bigwin.mcBigWinText;
			//var bigwintext : MovieClip = bigwin;
			_mediator.registerMediator ( MediatorKeyCJ.BIG_WIN_TEXT, MediatorBigWinText, ViewBigWinText, bigwin);
		}
	}
}