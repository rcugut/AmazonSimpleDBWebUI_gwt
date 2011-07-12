package eu.arkitech.aws.simpledb.webui.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import eu.arkitech.aws.simpledb.webui.client.presenter.ManageDomainsPresenter;

public class ManageDomainsView
		extends AbstractCompositeWithPresenter<ManageDomainsPresenter>
{
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	
	@UiField InlineLabel lbl_sdbDomainsNumber;
	@UiField ListBox lst_sdbDomains;
	@UiField Button btn_deleteDomain;
	@UiField Button btn_editDomain;
	@UiField Button btn_addDomain;
	@UiField Button btn_reloadDomains;
	@UiField HTMLPanel pnl_domainActions;
	@UiField InlineLabel lbl_selectedDomain;
	

	
	interface MainViewUiBinder extends UiBinder<Widget, ManageDomainsView> { }
	
	

	public ManageDomainsView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	public void updateDomainsList(List<String> domains)
	{
		lst_sdbDomains.clear();
		
		for(String d : domains)
		{
			lst_sdbDomains.addItem(d);
		}
		lbl_sdbDomainsNumber.setText(String.valueOf(domains.size()));
	}
	
	
	
	@UiHandler("btn_reloadDomains")
	void onBtn_reloadDomainsClick(ClickEvent event)
	{
		getPresenter().reloadDomains();
	}
	
	
	@UiHandler("btn_addDomain")
	void onBtn_addDomainClick(ClickEvent event)
	{
		
	}


	@UiHandler("lst_sdbDomains")
	void onLst_sdbDomainsChange(ChangeEvent event)
	{
		if(lst_sdbDomains.getSelectedIndex() >= 0)
		{
			lbl_selectedDomain.setText(lst_sdbDomains.getValue(lst_sdbDomains.getSelectedIndex()));
			pnl_domainActions.setVisible(true);
		}
		else
		{
			pnl_domainActions.setVisible(false);
		}
	}
	
	
	@UiHandler("btn_editDomain")
	void onBtn_editDomainClick(ClickEvent event)
	{
		getPresenter().showAndEditDomain(lst_sdbDomains.getValue(lst_sdbDomains.getSelectedIndex()));
	}
}
