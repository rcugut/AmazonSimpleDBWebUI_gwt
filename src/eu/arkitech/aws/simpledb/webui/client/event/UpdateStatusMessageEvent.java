package eu.arkitech.aws.simpledb.webui.client.event;

import com.google.web.bindery.event.shared.Event;

public class UpdateStatusMessageEvent
		extends Event<UpdateStatusMessageEvent.EventHandler>
{
	public static Event.Type<UpdateStatusMessageEvent.EventHandler> TYPE = new Event.Type<UpdateStatusMessageEvent.EventHandler>();

	public interface EventHandler extends com.google.gwt.event.shared.EventHandler
	{
		void updateStatus(String message, String details);
	}
	
	
	private final String message;
	private final String details;


	public UpdateStatusMessageEvent()
	{
		this(null, null);
	}
	public UpdateStatusMessageEvent(String message)
	{
		this(message, null);
	}
	public UpdateStatusMessageEvent(String message, String details)
	{
		this.message = message;
		this.details = details;
	}
	
	

	@Override
	public Event.Type<UpdateStatusMessageEvent.EventHandler> getAssociatedType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(UpdateStatusMessageEvent.EventHandler handler)
	{
		handler.updateStatus(this.message, this.details);
	}

}
