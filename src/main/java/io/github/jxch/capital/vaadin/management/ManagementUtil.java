package io.github.jxch.capital.vaadin.management;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.jsoup.internal.StringUtil;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagementUtil {

    public static Map<String, ManagementService> getManagementServices() {
        Map<String, ManagementService> services = new HashMap<>();
        SpringUtil.getBeansOfType(ManagementService.class).forEach((beanName, managementService) -> {
            services.put(getServiceName(managementService), managementService);
        });
        return services;
    }

    public static List<ManagementService> getSortedManagementServices() {
        return SpringUtil.getBeansOfType(ManagementService.class).values().stream()
                .sorted(new AnnotationAwareOrderComparator()).toList();
    }

    public static String getServiceName(ManagementService managementService) {
        String name = managementService.getClass().getSimpleName();
        if (AnnotationUtil.hasAnnotation(managementService.getClass(), Management.class)) {
            String theName = AnnotationUtil.getAnnotation(managementService.getClass(), Management.class).name();
            if (!StringUtil.isBlank(theName)) {
                name = theName;
            }
        }
        return name;
    }

}
