package com.vaadin.samples.flow;



import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;


/**
 * Responsive navigation menu presenting a list of available views to the user.
 */
public class Menu extends VerticalLayout {

    private Tabs tabs;


    public Menu() {
        setSizeUndefined();
        // header of the menu
        final HorizontalLayout top = new HorizontalLayout();
        Span title = new Span("My CRUD");
        title.setSizeUndefined();

        Image image = new Image("img/table-logo.png", "");
        image.setClassName("logo");
        top.add(image, title);
        add(top);

        tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        Div menuItems = new Div(tabs);
        add(menuItems);
        expand(menuItems);


        Button logout = new Button("Logout", VaadinIcon.SIGN_OUT.create(), event -> {
            VaadinSession.getCurrent().getSession().invalidate();
            UI.getCurrent().getPage().reload();
        });
        logout.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        add(logout);
    }

    /**
     * Add a view to the navigation menu
     *
     * @param viewClass
     *         that has a {@code Route} annotation
     * @param caption
     *         view caption in the menu
     * @param icon
     *         view icon in the menu
     */
    public void addView(Class<? extends Component> viewClass, String caption,
                        Icon icon) {
        Tab tab = new Tab();
        RouterLink routerLink = new RouterLink(null, viewClass);
        routerLink.setClassName("menu-link");
        routerLink.add(icon, new Span(caption));
        tab.add(routerLink);
        tabs.add(tab);
    }

}
