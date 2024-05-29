package io.github.jxch.capital.vaadin.management.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import io.github.jxch.capital.vaadin.management.AppLayoutBasic;
import io.github.jxch.capital.vaadin.management.ManagementService;
import io.github.jxch.capital.vaadin.management.ManagementUtil;

import java.util.HashMap;
import java.util.Map;

@Route(value = "management/:serviceName", layout = AppLayoutBasic.class)
public class ManagementView extends Div implements BeforeEnterObserver {
    private static final Map<String, ManagementService> SERVICES = new HashMap<>();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (SERVICES.isEmpty()) {
            SERVICES.putAll(ManagementUtil.getManagementServices());
        }

        String serviceName = event.getRouteParameters().get("serviceName").orElse("");

        if (SERVICES.containsKey(serviceName)) {
            removeAll();
            add(serviceName);
        } else {
            event.forwardTo("management");
        }
    }

}
