package eu.arkitech.aws.simpledb.webui.client.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public final class SdbAttribute extends JavaScriptObject
{
	protected SdbAttribute()
	{
	}
	
	
	public final native String getName() /*-{ return this['name']  }-*/;
	public final native JsArrayString getValues() /*-{ return this['values']  }-*/;
	
}
