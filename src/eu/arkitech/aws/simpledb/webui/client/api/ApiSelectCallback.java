package eu.arkitech.aws.simpledb.webui.client.api;

import eu.arkitech.aws.simpledb.webui.client.model.SdbDomain;

public abstract class ApiSelectCallback extends ApiResponseErrorCallback
{
	public abstract void onSelectCallback(SdbDomain domain);
}
