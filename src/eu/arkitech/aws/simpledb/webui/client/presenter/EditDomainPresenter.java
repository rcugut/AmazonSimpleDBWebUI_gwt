package eu.arkitech.aws.simpledb.webui.client.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

import eu.arkitech.aws.simpledb.webui.client.api.ApiSelectCallback;
import eu.arkitech.aws.simpledb.webui.client.model.SdbAttribute;
import eu.arkitech.aws.simpledb.webui.client.model.SdbDomain;
import eu.arkitech.aws.simpledb.webui.client.model.SdbItem;
import eu.arkitech.aws.simpledb.webui.client.view.EditDomainView;

public class EditDomainPresenter
		extends AbstractPresenter<EditDomainView>
{
	public interface Display
	{
		void setSdbDomainName(String sdbDomainName);
		Tree getDomainTree();
		HasClickHandlers getSqlExecuteButton();
	}
	
	
	
	private String sdbDomainName = null;
	
	
	public EditDomainPresenter(EditDomainView view, final String sdbDomainName)
	{
		super(view);
		this.sdbDomainName = sdbDomainName;
		getView().setSdbDomainName(sdbDomainName);
		this.updateViewFromSqlQuery(null);
	}
	
	
	
	public void updateViewFromSqlQuery(String sqlWhereClause)
	{
		getSdbApiClient().select(this.sdbDomainName, sqlWhereClause, new ApiSelectCallback()
		{
			@Override
			public void onSelectCallback(SdbDomain domain)
			{
				getView().setSdbDomainName(domain.getName());
				setDomainForEdit(domain);
			}
		});
	}
	
	
	
	
	
	
	private void setDomainForEdit(SdbDomain domain)
	{
		getView().getDomainTree().clear();
		
		for(int i=0; i<domain.getItems().size(); i++)
		{
			final SdbItem sdbItem = domain.getItems().get(i); 
			
			final TreeItem ti_item = new TreeItem(this.createSdbItemWidget(sdbItem));
			
			for(int j=0; j<sdbItem.getAttrs().length(); j++)
			{
				final SdbAttribute sdbAttr = sdbItem.getAttrs().get(j);
				
				final TreeItem ti_attr = new TreeItem(this.createEditItemAttributeValuesWidget(sdbItem, sdbAttr));
				
				for(int k=0; k<sdbAttr.getValues().length(); k++)
				{
					final String sdbAttrValue = sdbAttr.getValues().get(k); 
					ti_attr.addItem(this.createWidgetForAttributeValue(domain.getName(), sdbItem.getName(), sdbAttr.getName(), sdbAttrValue));
				}

				ti_attr.setState(true);
				ti_item.addItem(ti_attr);
			}
			
			ti_item.setState(true);
			getView().getDomainTree().addItem(ti_item);
		}
	}
	
	
	

	private Widget createSdbItemWidget(SdbItem sdbItem)
	{
		final FlowPanel panel = new FlowPanel();
		panel.addStyleName("arki-itemPanel");
		final InlineLabel lbl_itemName = new InlineLabel(sdbItem.getName());
		lbl_itemName.setStyleName("arki-itemLabel");
		panel.add(lbl_itemName);
		
		final Anchor lnk_addAttribute = new Anchor("Add attribute");
		lnk_addAttribute.addStyleName("arki-treeItemActionLink");
		panel.add(lnk_addAttribute);

		final Anchor lnk_delItem = new Anchor("Delete item");
		lnk_delItem.addStyleName("arki-treeItemActionLink");
		panel.add(lnk_delItem);
		
		return panel;
	}
	
	
	
	private Widget createEditItemAttributeValuesWidget(SdbItem sdbItem, SdbAttribute sdbAttr)
	{
		final FlowPanel panel = new FlowPanel();
		panel.addStyleName("arki-attrPanel");
		panel.add(new InlineLabel(sdbAttr.getName()));

		final Anchor lnk_addValue = new Anchor("Add value");
		lnk_addValue.addStyleName("arki-treeItemActionLink");
		panel.add(lnk_addValue);

		final Anchor lnk_delAttr = new Anchor("Delete attribute");
		lnk_delAttr.addStyleName("arki-treeItemActionLink");
		panel.add(lnk_delAttr);

		return panel;
	}


	private Widget createWidgetForAttributeValue(String sdbDomainName, String sdbItemName, String sdbAttrName, String sdbAttrValue)
	{
		final FlowPanel panel = new FlowPanel();
		panel.addStyleName("arki-attrValuePanel");
		panel.add(new InlineLabel(sdbAttrValue));
		
		final Anchor lnk_delAttrValue = new Anchor("Delete value");
		lnk_delAttrValue.addStyleName("arki-treeItemActionLink");
		panel.add(lnk_delAttrValue);
		
		return panel;
	}

}
