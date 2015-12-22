package com.redhat.coolstore.web.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.vaadin.teemu.VaadinIcons;

import com.redhat.coolstore.model.ShoppingCart;
import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@UIScoped
public abstract class AbstractView extends Panel implements ClickListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2404594287924999036L;

	@Inject
	private ShoppingCart shoppingCart;

	private VerticalLayout layout;
	private HorizontalLayout controllerLayout;

	private static final String BUTTON_WIDTH = "10em";

	public AbstractView() {
		super();

		layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);

		setContent(layout);
	}

	@PostConstruct
	private void init() {
		createLayout(layout);

		controllerLayout = new HorizontalLayout();
		controllerLayout.addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		controllerLayout.setSpacing(true);

		createControllerButtons();

		layout.addComponent(controllerLayout);
	}

	protected ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	protected abstract void createLayout(VerticalLayout layout);

	protected abstract void createControllerButtons();

	protected void createButton(Button button,
			String caption, VaadinIcons icon, boolean isPrimary) {

		button.addClickListener(this);
		button.setCaption(caption);
		button.setIcon(icon);
		button.setWidth(BUTTON_WIDTH);

		if (isPrimary) {
			button.addStyleName(ValoTheme.BUTTON_PRIMARY);
		}

		controllerLayout.addComponent(button);
	}
}
