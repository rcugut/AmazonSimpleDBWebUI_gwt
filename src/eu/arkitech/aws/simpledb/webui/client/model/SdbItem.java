package eu.arkitech.aws.simpledb.webui.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public final class SdbItem extends JavaScriptObject
{
	protected SdbItem()
	{
	}
	
	public final native String getName() /*-{ return this['name']  }-*/;
	public final native JsArray<SdbAttribute> getAttrs() /*-{ return this['attrs']  }-*/;
	

	
	public final String asString()
	{
		return "SdbItem{"+ getName() +"}";
	}
}
