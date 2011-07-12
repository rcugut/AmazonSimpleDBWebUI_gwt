package eu.arkitech.aws.simpledb.webui.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import eu.arkitech.aws.simpledb.webui.client.presenter.LoginPresenter;

public class LoginView
		extends AbstractCompositeWithPresenter<LoginPresenter>
{
	private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

	@UiField Button btn_awsLogin;
	@UiField TextBox input_awsAccessKeyId;
	@UiField TextBox input_awsSecretKey;

	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> { }

	
	public LoginView()
	{
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	
	
	
	@UiHandler("btn_awsLogin")
	void onBtn_awsLoginClick(ClickEvent event)
	{
		this.getPresenter().onLoginClick(input_awsAccessKeyId.getValue(), input_awsSecretKey.getValue());
	}
}
