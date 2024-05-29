package io.github.jxch.capital.vaadin.management;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@Theme(themeClass = Material.class, variant = Material.DARK)
class ManagementViewTest implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(ManagementViewTest.class);
    }

}