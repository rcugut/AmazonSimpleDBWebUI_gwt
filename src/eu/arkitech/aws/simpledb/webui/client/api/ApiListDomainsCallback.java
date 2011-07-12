package eu.arkitech.aws.simpledb.webui.client.api;

import java.util.List;

public abstract class ApiListDomainsCallback extends ApiResponseErrorCallback
{
	public abstract void onListDomainsCallback(List<String> domains);
}
