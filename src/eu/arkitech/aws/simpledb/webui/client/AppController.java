package eu.arkitech.aws.simpledb.webui.client;

import java.util.List;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;

import eu.arkitech.aws.simpledb.webui.client.api.SdbApiClient;
import eu.arkitech.aws.simpledb.webui.client.event.ShowEditDomainEvent;
import eu.arkitech.aws.simpledb.webui.client.event.ShowManageDomainsEvent;
import eu.arkitech.aws.simpledb.webui.client.presenter.EditDomainPresenter;
import eu.arkitech.aws.simpledb.webui.client.presenter.LoginPresenter;
import eu.arkitech.aws.simpledb.webui.client.presenter.MainPresenter;
import eu.arkitech.aws.simpledb.webui.client.presenter.ManageDomainsPresenter;
import eu.arkitech.aws.simpledb.webui.client.view.EditDomainView;
import eu.arkitech.aws.simpledb.webui.client.view.LoginView;
import eu.arkitech.aws.simpledb.webui.client.view.MainView;
import eu.arkitech.aws.simpledb.webui.client.view.ManageDomainsView;

public class AppController
{
	public static final SimpleEventBus eventBus = new SimpleEventBus();
	public static final SdbApiClient sdbApiClient = new SdbApiClient();
	
	public static RootPanel STATUS_PANEL = RootPanel.get("statusDiv");
	public static RootPanel ROOT_PANEL = RootPanel.get("mainDiv");

	private final MainPresenter mainPresenter;
	private final LoginPresenter loginPresenter;
	

	public AppController()
	{
		this.mainPresenter = new MainPresenter(new MainView());
		this.loginPresenter = new LoginPresenter(new LoginView());

		final ManageDomainsPresenter manageDomainsPresenter = new ManageDomainsPresenter(new ManageDomainsView());
		
		
		eventBus.addHandler(ShowManageDomainsEvent.TYPE, new ShowManageDomainsEvent.EventHandler()
		{
			@Override
			public void showManageDomainsView(List<String> sdbDomains)
			{
				manageDomainsPresenter.updateDomainsList(sdbDomains);
				manageDomainsPresenter.presentViewIn(mainPresenter.getView().getContentPanel());
			}
		});
		
		
		eventBus.addHandler(ShowEditDomainEvent.TYPE, new ShowEditDomainEvent.EventHandler()
		{
			@Override
			public void showEditDomainView(String sdbDomainName)
			{
				new EditDomainPresenter(new EditDomainView(), sdbDomainName).presentViewIn(mainPresenter.getView().getContentPanel());
			}
		});

	}
	
	
	
	
	
	
	
	public void start()
	{
		this.mainPresenter.presentViewIn(ROOT_PANEL);
		this.loginPresenter.presentViewIn(this.mainPresenter.getView().getContentPanel());
	}
}
