package io.github.jxch.capital.vaadin.management.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import io.github.jxch.capital.vaadin.management.AppLayoutBasic;
import io.github.jxch.capital.vaadin.management.ManagementService;
import io.github.jxch.capital.vaadin.management.ManagementUtil;

import java.util.List;

@Route(value = "management", layout = AppLayoutBasic.class)
public class ManagementRedirectView extends Div implements BeforeEnterObserver {

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        List<ManagementService<?>> services = ManagementUtil.getSortedManagementServices();
        if (!services.isEmpty()) {
            event.forwardTo("management/" + ManagementUtil.getServiceName(services.get(0)));
        }
    }

}
