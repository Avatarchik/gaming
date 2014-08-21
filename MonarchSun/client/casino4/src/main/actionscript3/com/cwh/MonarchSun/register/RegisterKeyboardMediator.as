package com.cwh.MonarchSun.register
{
	import flash.display.DisplayObject;
	
	import ingenuity.amaya.ui.mediator.key.MediatorKeyCJ;
	import ingenuity.amaya.ui.mediator.stagekeyboardinteractivity.MediatorStageKeyBoardInteractivity;
	import ingenuity.amaya.ui.view.stagekeyboardinteractivity.ViewStageKeyBoardInteractivity;
	import ingenuity.application.manager.MediatorManager;
	/**
	 * This class registers mediator and creates view of keyboardstageinteractivity.
	 */	
	public class RegisterKeyboardMediator
	{
		protected var _mediator : MediatorManager = MediatorManager.instance();
		
		public function RegisterKeyboardMediator(clip:DisplayObject)
		{
			_mediator.registerMediator(MediatorKeyCJ.STAGE_KEYBOARD_INTERACTIVITY,MediatorStageKeyBoardInteractivity,ViewStageKeyBoardInteractivity,clip);
			createStageView();
		}
		
		protected function createStageView():void
		{
			_mediator.createView(MediatorKeyCJ.STAGE_KEYBOARD_INTERACTIVITY);
		}
	}
}