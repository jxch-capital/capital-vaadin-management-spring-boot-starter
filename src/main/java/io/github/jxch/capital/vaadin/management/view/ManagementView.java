package io.github.jxch.capital.vaadin.management.view;

import cn.hutool.core.annotation.AnnotationUtil;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import io.github.jxch.capital.vaadin.management.AppLayoutBasic;
import io.github.jxch.capital.vaadin.management.MField;
import io.github.jxch.capital.vaadin.management.ManagementService;
import io.github.jxch.capital.vaadin.management.ManagementUtil;
import io.github.jxch.capital.vaadin.management.page.MPage;
import lombok.SneakyThrows;
import org.jsoup.internal.StringUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Route(value = "management/:serviceName", layout = AppLayoutBasic.class)
public class ManagementView extends Div implements BeforeEnterObserver {
    private static final Map<String, ManagementService<?>> SERVICES = new HashMap<>();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (SERVICES.isEmpty()) {
            SERVICES.putAll(ManagementUtil.getManagementServices());
        }

        String serviceName = event.getRouteParameters().get("serviceName").orElse("");
        if (SERVICES.containsKey(serviceName)) {
            update(serviceName, 1, 10);
        } else {
            event.forwardTo("management");
        }
    }

    private void update(String serviceName, int page, int size) {
        removeAll();
        ManagementService<?> service = SERVICES.get(serviceName);
        MPage<?> mPage = service.findAll(page, size);

        IntegerField current = new IntegerField();
        current.setMin(1);
        current.setMax(mPage.getTotal());
        current.setValue(mPage.getPage());
        current.setStepButtonsVisible(true);
        IntegerField pageSize = new IntegerField();
        pageSize.setValue(size);

        Paragraph info = new Paragraph(" / " + mPage.getTotal());
        Button search = new Button("GO");
        search.addClickListener(event -> {
            update(serviceName, current.getValue(), pageSize.getValue());
        });
        HorizontalLayout horizontalLayout = new HorizontalLayout(search, current, info);

        add(getGrid(mPage), horizontalLayout);
    }

    private Grid<Object> getGrid(MPage<?> mPage) {
        Grid<Object> grid = new Grid<>();
        for (Field field : mPage.getClazz().getDeclaredFields()) {
            String fieldName = field.getName();
            if (AnnotationUtil.hasAnnotation(field, MField.class)) {
                String mName = AnnotationUtil.getAnnotation(field, MField.class).name();
                if (!StringUtil.isBlank(mName)) {
                    fieldName = mName;
                }
            }
            grid.addColumn(obj -> getFieldValue(field, obj)).setHeader(fieldName);
        }
        grid.setItems(mPage.getItems().toArray());
        return grid;
    }

    @SneakyThrows
    private Object getFieldValue(Field field, Object obj) {
        field.setAccessible(true);
        return field.get(obj);
    }

}
