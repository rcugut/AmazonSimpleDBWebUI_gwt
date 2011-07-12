package eu.arkitech.aws.simpledb.webui.client.api;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

import eu.arkitech.aws.simpledb.webui.client.AppController;
import eu.arkitech.aws.simpledb.webui.client.AwsCredentials;
import eu.arkitech.aws.simpledb.webui.client.event.UpdateStatusMessageEvent;
import eu.arkitech.aws.simpledb.webui.client.model.SdbDomain;
import eu.arkitech.aws.simpledb.webui.client.model.SdbItem;

public class SdbApiClient
{
	private static final String BASE_URL = GWT.getHostPageBaseURL();
	public static final String API_URL = BASE_URL.substring(0, BASE_URL.substring(0, BASE_URL.length()-1).lastIndexOf("/")) + "/api/"; 

	private final SimpleEventBus eventBus = AppController.eventBus;
	
	
	private abstract class HttpApiResponseCallback extends ApiResponseErrorCallback
	{
		public abstract void onResponseData(String jsonResponse);
		
		@Override
		public void onError(String errorMessage, String errorDetails, Throwable e)
		{
			eventBus.fireEvent(new UpdateStatusMessageEvent("Oups, an error occured: " + errorMessage, errorDetails));
			GWT.log("ResponseCallback error: " + errorMessage, e);
		}
	}
	
	
	
	
	private void makeHttpCall(String apiRequest, final HttpApiResponseCallback responseCallback)
	{
	    final RequestBuilder reqBuilder = new RequestBuilder(RequestBuilder.POST, URL.encode(API_URL + apiRequest));
	    try
	    {
	    	reqBuilder.setHeader("Content-Type", "application/x-www-form-urlencoded");
			reqBuilder.sendRequest(AwsCredentials.asUrlData(), new RequestCallback()
			{
				@Override
				public void onResponseReceived(Request request, Response response)
				{
				      if (200 == response.getStatusCode())
				      {
				    	  try
				    	  {
				    		  responseCallback.onResponseData(response.getText());
				    	  }
				    	  catch(Exception e)
				    	  {
				    		  responseCallback.onError("HTTP/200 received, but an exception raised", e.getMessage(), e);
				    	  }
				      }
				      else
				      {
				    	  responseCallback.onError("HTTP/" + response.getStatusCode() + " received", response.getText(), null);
				      }
				}
			
				@Override
				public void onError(Request request, Throwable e)
				{
					responseCallback.onError("Server request did not complete normally", e.getMessage(), e);
				}
			});
		}
		catch (RequestException e)
		{
			responseCallback.onError("Unexpected RequestException raised", e.getMessage(), e);
		}
	}
	
	
	private final native JsArrayString asArrayOfStrings(String json) /*-{
		return eval(json);
	}-*/;
	
	
	private final native <T extends JavaScriptObject> JsArray<T> asArrayOfT(String json, Class<T> clazz) /*-{
		return eval(json);
	}-*/;




	public void listDomains(final ApiListDomainsCallback callback)
	{
		this.makeHttpCall("list_domains", new HttpApiResponseCallback()
		{
			@Override
			public void onResponseData(String jsonResponse)
			{
				JsArrayString jsArrayString = asArrayOfStrings(jsonResponse);
				List<String> sdbDomains = new LinkedList<String>();
				
				for(int i=0; i<jsArrayString.length(); i++)
				{
					sdbDomains.add(jsArrayString.get(i));
				}
				
				callback.onListDomainsCallback(sdbDomains);
			}
			

			@Override
			public void onError(String errorMessage, String errorDetails, Throwable e)
			{
				super.onError(errorMessage, errorDetails, e);
				callback.onError(errorMessage, errorDetails, e);
			}
		});
	}
	
	
	
	
	
	public void select(final String sdbDomain, String whereClause, final ApiSelectCallback callback)
	{
		this.makeHttpCall("select?domain=" + URL.encode(sdbDomain) + (whereClause!=null && whereClause.trim().length() > 0  ? "&where_clause=" + URL.encode(whereClause) : ""), new HttpApiResponseCallback()
		{
			@Override
			public void onResponseData(String jsonResponse)
			{
				SdbDomain domain = new SdbDomain(sdbDomain);
				JsArray<SdbItem> jsSdbItems = asArrayOfT(jsonResponse, SdbItem.class);

				for(int i=0; i<jsSdbItems.length(); i++)
				{
					SdbItem itm = jsSdbItems.get(i);
					domain.getItems().add(itm);
				}
				
				callback.onSelectCallback(domain);
			}
			
			@Override
			public void onError(String errorMessage, String errorDetails, Throwable e)
			{
				super.onError(errorMessage, errorDetails, e);
				callback.onError(errorMessage, errorDetails, e);
			}
		});
	}

}
