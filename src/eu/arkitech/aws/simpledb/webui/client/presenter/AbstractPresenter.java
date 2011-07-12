package eu.arkitech.aws.simpledb.webui.client.presenter;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HasWidgets;

import eu.arkitech.aws.simpledb.webui.client.AppController;
import eu.arkitech.aws.simpledb.webui.client.api.SdbApiClient;
import eu.arkitech.aws.simpledb.webui.client.view.AbstractCompositeWithPresenter;

public abstract class AbstractPresenter<V extends AbstractCompositeWithPresenter<? extends AbstractPresenter<V>>>
{
	private final V view;
	private final SimpleEventBus eventBus;
	private final SdbApiClient sdbApiClient;
	
	public AbstractPresenter(V view)
	{
		this.view = view;
		this.view.setPresenter(this);
		this.eventBus = AppController.eventBus;
		this.sdbApiClient = AppController.sdbApiClient;
	}
	

	public void presentViewIn(HasWidgets container)
	{
		container.clear();
		container.add(view);
	}

	

	public SimpleEventBus getEventBus()
	{
		return this.eventBus;
	}


	public V getView()
	{
		return this.view;
	}


	public SdbApiClient getSdbApiClient()
	{
		return this.sdbApiClient;
	}
}
