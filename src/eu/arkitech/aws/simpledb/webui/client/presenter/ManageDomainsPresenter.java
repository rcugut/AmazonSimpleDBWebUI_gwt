package eu.arkitech.aws.simpledb.webui.client.presenter;

import java.util.List;

import eu.arkitech.aws.simpledb.webui.client.api.ApiListDomainsCallback;
import eu.arkitech.aws.simpledb.webui.client.event.ShowEditDomainEvent;
import eu.arkitech.aws.simpledb.webui.client.view.ManageDomainsView;

public class ManageDomainsPresenter extends AbstractPresenter<ManageDomainsView>
{
	public ManageDomainsPresenter(ManageDomainsView view)
	{
		super(view);
	}

	
	public void reloadDomains()
	{
		//XXX: boilarplate code as the one from LoginPresenter
		getSdbApiClient().listDomains(new ApiListDomainsCallback()
		{
			@Override
			public void onListDomainsCallback(List<String> domains)
			{
				updateDomainsList(domains);
			}
		});
	}

	

	public void showAndEditDomain(String sdbDomainName)
	{
		getEventBus().fireEvent(new ShowEditDomainEvent(sdbDomainName));
	}


	public void updateDomainsList(List<String> sdbDomains)
	{
		this.getView().updateDomainsList(sdbDomains);
	}
}
