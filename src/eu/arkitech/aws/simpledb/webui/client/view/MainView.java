package eu.arkitech.aws.simpledb.webui.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;

import eu.arkitech.aws.simpledb.webui.client.presenter.MainPresenter;
import com.google.gwt.user.client.ui.SimplePanel;

public class MainView
		extends AbstractCompositeWithPresenter<MainPresenter>
{
	private static MainViewUiBinder uiBinder = GWT.create(MainViewUiBinder.class);
	@UiField DisclosurePanel pnl_detailedStatus;
	@UiField InlineLabel lbl_simpleStatus;
	@UiField DeckPanel pnl_statusDeck;
	@UiField InlineLabel lbl_emptyStatus;
	@UiField SimplePanel pnl_content;
	InlineLabel lbl_statusDetails;

	interface MainViewUiBinder extends UiBinder<Widget, MainView> { }



	public MainView()
	{
		initWidget(uiBinder.createAndBindUi(this));
		lbl_statusDetails = new InlineLabel();
		pnl_detailedStatus.setContent(lbl_statusDetails);
	}



	public void hideStatus()
	{
		pnl_statusDeck.showWidget(0);
	}

	public void showSimpleStatus(String message)
	{
		lbl_simpleStatus.setText(message);
		pnl_statusDeck.showWidget(1);
	}

	public void showDetailedStatus(String message, String details)
	{
		pnl_detailedStatus.getHeaderTextAccessor().setText(message);
		lbl_statusDetails.setText(details);
		pnl_statusDeck.showWidget(2);
	}



	public HasWidgets getContentPanel()
	{
		return pnl_content;
	}
}
