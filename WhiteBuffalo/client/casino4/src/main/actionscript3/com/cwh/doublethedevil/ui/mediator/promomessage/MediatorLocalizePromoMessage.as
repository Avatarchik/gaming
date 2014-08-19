package com.cwh.WhiteBuffalo.ui.mediator.promomessage
{
	
	import com.cwh.WhiteBuffalo.ui.view.promomessage.ViewLocalizePromoMessage;
	
	import ingenuity.application.mediator.IMediator;
	import ingenuity.application.mediator.IView;
	
	public class MediatorLocalizePromoMessage implements IMediator
	{
		private var _view : ViewLocalizePromoMessage ;
		
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
			_view	= view as ViewLocalizePromoMessage ;
		}
	}
}