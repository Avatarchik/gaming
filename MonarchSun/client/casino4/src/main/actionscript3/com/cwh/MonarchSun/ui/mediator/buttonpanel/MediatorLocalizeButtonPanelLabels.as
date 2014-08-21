package com.cwh.MonarchSun.ui.mediator.buttonpanel
{
	import com.cwh.MonarchSun.ui.view.buttonpanel.ViewLocalizeButtonPanelLabels;
	
	import ingenuity.application.mediator.IMediator;
	import ingenuity.application.mediator.IView;
	
	public class MediatorLocalizeButtonPanelLabels implements IMediator
	{
		private var _view : ViewLocalizeButtonPanelLabels;
		
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
			_view = view as ViewLocalizeButtonPanelLabels;
		}
	}
}