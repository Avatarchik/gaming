package com.cwh.MonarchSun.ui.mediator.buttonpanel
{
	
	import com.cwh.MonarchSun.ui.view.buttonpanel.ViewLocalizeSettingsPopupLabels;
	
	import ingenuity.application.mediator.IMediator;
	import ingenuity.application.mediator.IView;
	
	public class MediatorLocalizeSettingsPopupLabels implements IMediator
	{
		
		private var _view : ViewLocalizeSettingsPopupLabels;
		
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
			_view = view as ViewLocalizeSettingsPopupLabels;
		}
	}
}