package io.github.jxch.capital.vaadin.management;

import cn.hutool.extra.spring.SpringUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.theme.lumo.LumoUtility;
import io.github.jxch.capital.vaadin.management.view.ManagementView;

import java.util.ArrayList;
import java.util.List;

public class AppLayoutBasic extends AppLayout implements VaadinServiceInitListener {
    private static final List<ManagementService<?>> SERVICES = new ArrayList<>();

    public AppLayoutBasic() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1(SpringUtil.getApplicationName());
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");

        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);
        addToNavbar(toggle, title);
    }

    private SideNav getSideNav() {
        SideNav sideNav = new SideNav();

        if (SERVICES.isEmpty()) {
            SERVICES.addAll(ManagementUtil.getSortedManagementServices());
        }

        SERVICES.forEach(service -> {
            String serviceName = ManagementUtil.getServiceName(service);
            sideNav.addItem(new SideNavItem(serviceName, "management/" + serviceName, VaadinIcon.DATABASE.create()));
        });

        return sideNav;
    }

    @Override
    public void serviceInit(ServiceInitEvent event) {
        SERVICES.forEach(service -> RouteConfiguration.forSessionScope()
                .setRoute("management/" + ManagementUtil.getServiceName(service), ManagementView.class));

    }

}
