package eu.arkitech.aws.simpledb.webui.client;

import com.google.gwt.core.client.EntryPoint;

public class WebUIEntryPoint
		implements EntryPoint
{
	@Override
	public void onModuleLoad()
	{
		new AppController().start();
	}

}
