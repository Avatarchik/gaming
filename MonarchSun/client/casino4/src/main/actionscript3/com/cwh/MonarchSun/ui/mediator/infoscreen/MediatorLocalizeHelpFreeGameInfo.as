package com.cwh.MonarchSun.ui.mediator.infoscreen
{
	
	import com.cwh.MonarchSun.ui.view.infoscreen.ViewLocalizeHelpFreeGameInfo;
	
	import ingenuity.application.mediator.IMediator;
	import ingenuity.application.mediator.IView;
	
	public class MediatorLocalizeHelpFreeGameInfo implements IMediator
	{
		private var _view : ViewLocalizeHelpFreeGameInfo;
		
		
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
			_view = view as ViewLocalizeHelpFreeGameInfo;
		}
	}
}