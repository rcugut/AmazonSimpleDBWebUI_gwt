package eu.arkitech.aws.simpledb.webui.client.view;

import com.google.gwt.user.client.ui.Composite;

import eu.arkitech.aws.simpledb.webui.client.presenter.AbstractPresenter;

public class AbstractCompositeWithPresenter<P extends AbstractPresenter<? extends AbstractCompositeWithPresenter<P>>>
		extends Composite
{
	private P presenter;
	

	
	
	@SuppressWarnings("unchecked")
	public void setPresenter(AbstractPresenter<?> presenter)
	{
		this.presenter = (P) presenter;
	}
	public P getPresenter()
	{
		return this.presenter;
	}
}
