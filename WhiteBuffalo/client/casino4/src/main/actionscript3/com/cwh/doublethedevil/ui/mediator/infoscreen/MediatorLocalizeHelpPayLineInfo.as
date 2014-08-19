package com.cwh.WhiteBuffalo.ui.mediator.infoscreen
{
	import com.cwh.WhiteBuffalo.ui.view.infoscreen.ViewLocalizeHelpPayLineInfo;
	
	import ingenuity.application.mediator.IMediator;
	import ingenuity.application.mediator.IView;
	
	public class MediatorLocalizeHelpPayLineInfo implements IMediator
	{
		private var _view : ViewLocalizeHelpPayLineInfo ;
		
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
			_view	= view as ViewLocalizeHelpPayLineInfo;
		}
	}
}