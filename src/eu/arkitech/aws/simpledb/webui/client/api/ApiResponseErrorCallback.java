package eu.arkitech.aws.simpledb.webui.client.api;

public abstract class ApiResponseErrorCallback
{
	public void onError(String errorMessage, String errorDetails, Throwable e)
	{
		// do nothing
	}
}
