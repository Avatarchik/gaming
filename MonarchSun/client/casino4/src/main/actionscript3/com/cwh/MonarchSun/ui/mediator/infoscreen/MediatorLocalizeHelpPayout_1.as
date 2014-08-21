package com.cwh.MonarchSun.ui.mediator.infoscreen
{
	import com.cwh.MonarchSun.ui.view.infoscreen.ViewLocalizeHelpPayout_1;
	
	import ingenuity.application.mediator.IMediator;
	import ingenuity.application.mediator.IView;
	
	public class MediatorLocalizeHelpPayout_1 implements IMediator
	{
		
		private var _view : ViewLocalizeHelpPayout_1 ;
		
		public function createView():void
		{
			_view.createView();
		}
		
		public function deleteView():void
		{
		}
		
		public function updateView():void
		{
		}
		
		public function set view(view:IView):void
		{
			_view	= view as ViewLocalizeHelpPayout_1 ;
		}
	}
}