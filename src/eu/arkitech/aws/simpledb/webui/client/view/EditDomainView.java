package eu.arkitech.aws.simpledb.webui.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

import eu.arkitech.aws.simpledb.webui.client.presenter.EditDomainPresenter;

public class EditDomainView
		extends AbstractCompositeWithPresenter<EditDomainPresenter>
		implements EditDomainPresenter.Display
{
	private static EditDomainViewUiBinder uiBinder = GWT.create(EditDomainViewUiBinder.class);

	@UiField InlineLabel lbl_sqlSdbDomain;
	@UiField TextBox input_sqlWhereClause;
	@UiField Button btn_sdbSqlExecute;
	@UiField Tree tree_domain;

	
	interface EditDomainViewUiBinder extends UiBinder<Widget, EditDomainView> { }

	public EditDomainView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	
	@UiHandler("btn_sdbSqlExecute")
	void onBtn_sdbSqlExecuteClick(ClickEvent event)
	{
		getPresenter().updateViewFromSqlQuery(input_sqlWhereClause.getValue());
	}

	

	@Override
	public void setSdbDomainName(String sdbDomainName)
	{
		lbl_sqlSdbDomain.setText(sdbDomainName);
	}

	@Override
	public Tree getDomainTree()
	{
		return tree_domain;
	}


	@Override
	public HasClickHandlers getSqlExecuteButton()
	{
		return btn_sdbSqlExecute;
	}
}
