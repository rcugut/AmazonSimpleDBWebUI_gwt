package eu.arkitech.aws.simpledb.webui.client.event;

import com.google.web.bindery.event.shared.Event;

public class ShowEditDomainEvent
		extends Event<ShowEditDomainEvent.EventHandler>
{
	public static Event.Type<ShowEditDomainEvent.EventHandler> TYPE = new Event.Type<ShowEditDomainEvent.EventHandler>();

	public interface EventHandler extends com.google.gwt.event.shared.EventHandler
	{
		void showEditDomainView(String sdbDomainName);
	}

	
	private final String sdbDomainName;

	public ShowEditDomainEvent(String sdbDomainName)
	{
		super();
		this.sdbDomainName = sdbDomainName;
	}

	@Override
	public Event.Type<ShowEditDomainEvent.EventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(ShowEditDomainEvent.EventHandler handler)
	{
		handler.showEditDomainView(sdbDomainName);
	}

}
