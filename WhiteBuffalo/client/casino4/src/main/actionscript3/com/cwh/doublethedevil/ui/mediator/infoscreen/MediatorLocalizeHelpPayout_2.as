package com.cwh.WhiteBuffalo.ui.mediator.infoscreen
{
	import com.cwh.WhiteBuffalo.ui.view.infoscreen.ViewLocalizeHelpPayout_2;
	
	import ingenuity.application.mediator.IMediator;
	import ingenuity.application.mediator.IView;
	
	public class MediatorLocalizeHelpPayout_2 implements IMediator
	{
		
		private var _view : ViewLocalizeHelpPayout_2 ;
		
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
			_view	= view as ViewLocalizeHelpPayout_2 ;
		}
	}
}