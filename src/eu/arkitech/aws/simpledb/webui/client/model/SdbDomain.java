package eu.arkitech.aws.simpledb.webui.client.model;

import java.util.ArrayList;
import java.util.List;

public class SdbDomain
{
	private String name;
	private final List<SdbItem> items = new ArrayList<SdbItem>();
	
	
	public SdbDomain()
	{
	}

	public SdbDomain(String name)
	{
		super();
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public List<SdbItem> getItems()
	{
		return this.items;
	}
}
