package eu.arkitech.aws.simpledb.webui.client.presenter;

import java.util.List;

import eu.arkitech.aws.simpledb.webui.client.AwsCredentials;
import eu.arkitech.aws.simpledb.webui.client.api.ApiListDomainsCallback;
import eu.arkitech.aws.simpledb.webui.client.event.ShowManageDomainsEvent;
import eu.arkitech.aws.simpledb.webui.client.event.UpdateStatusMessageEvent;
import eu.arkitech.aws.simpledb.webui.client.view.LoginView;


public class LoginPresenter
		extends AbstractPresenter<LoginView>
{
	public LoginPresenter(LoginView view)
	{
		super(view);
	}
	
	
	public void onLoginClick(String awsAccessKeyId, String awsSecretKey)
	{
		AwsCredentials.awsAccessKeyId = awsAccessKeyId;
		AwsCredentials.awsSecretKey = awsSecretKey;
		
		getSdbApiClient().listDomains(new ApiListDomainsCallback()
		{
			@Override
			public void onListDomainsCallback(List<String> domains)
			{
				getEventBus().fireEvent(new UpdateStatusMessageEvent());
				getEventBus().fireEvent(new ShowManageDomainsEvent(domains));
			}
		});
		
		
		getEventBus().fireEvent(new UpdateStatusMessageEvent("Please wait ..."));
	}
}
