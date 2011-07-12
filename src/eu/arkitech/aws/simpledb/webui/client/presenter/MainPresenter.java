package eu.arkitech.aws.simpledb.webui.client.presenter;

import com.google.gwt.core.client.GWT;

import eu.arkitech.aws.simpledb.webui.client.event.UpdateStatusMessageEvent;
import eu.arkitech.aws.simpledb.webui.client.view.MainView;

public class MainPresenter extends AbstractPresenter<MainView>
{
	public MainPresenter(MainView view)
	{
		super(view);
		
		getEventBus().addHandler(UpdateStatusMessageEvent.TYPE, new UpdateStatusMessageEvent.EventHandler()
		{
			@Override
			public void updateStatus(String message, String details)
			{
				if(message == null)
				{
					getView().hideStatus();
				}
				else if(details == null)
				{
					GWT.log("simple status: " + message);
					getView().showSimpleStatus(message);
				}
				else
				{
					getView().showDetailedStatus(message, details);
				}
			}
		});
	}


}
