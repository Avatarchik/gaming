package com.cwh.MonarchSun.ui.mediator.infoscreen
{
	import com.cwh.MonarchSun.ui.view.infoscreen.ViewLocalizeHelpPayout_3;
	
	import ingenuity.application.mediator.IMediator;
	import ingenuity.application.mediator.IView;
	
	public class MediatorLocalizeHelpPayout_3 implements IMediator
	{
		
		private var _view : ViewLocalizeHelpPayout_3 ;
		
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
			_view	= view as ViewLocalizeHelpPayout_3 ;
		}
	}
}