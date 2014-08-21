package com.cwh.MonarchSun.ui.mediator.promomessage
{
	
	import com.cwh.MonarchSun.ui.view.promomessage.ViewLocalizeFreeGamePromoMessage;
	
	import ingenuity.application.mediator.IMediator;
	import ingenuity.application.mediator.IView;
	
	public class MediatorLocalizationFreeGamePromoMessage implements IMediator
	{
		private var _view : ViewLocalizeFreeGamePromoMessage ;
		
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
			_view	= view as ViewLocalizeFreeGamePromoMessage ;
		}
	}
}