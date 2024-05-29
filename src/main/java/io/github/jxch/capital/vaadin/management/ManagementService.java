package io.github.jxch.capital.vaadin.management;

import io.github.jxch.capital.vaadin.management.page.MPage;

public interface ManagementService<T> {

    MPage<T> findAll(int page, int size);

}
