package eu.arkitech.aws.simpledb.webui.client.event;

import java.util.List;

import com.google.web.bindery.event.shared.Event;

public class ShowManageDomainsEvent
		extends Event<ShowManageDomainsEvent.EventHandler>
{
	public static Event.Type<ShowManageDomainsEvent.EventHandler> TYPE = new Event.Type<ShowManageDomainsEvent.EventHandler>();

	public interface EventHandler extends com.google.gwt.event.shared.EventHandler
	{
		void showManageDomainsView(List<String> sdbDomains);
	}

	
	private final List<String> sdbDomains;
	
	public ShowManageDomainsEvent(List<String> sdbDomains)
	{
		super();
		this.sdbDomains = sdbDomains;
	}
	
	

	@Override
	public Event.Type<ShowManageDomainsEvent.EventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(ShowManageDomainsEvent.EventHandler handler)
	{
		handler.showManageDomainsView(this.sdbDomains);
	}

}
