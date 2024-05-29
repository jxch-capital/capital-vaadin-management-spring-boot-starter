package io.github.jxch.capital.vaadin.management.service;

import io.github.jxch.capital.vaadin.management.Management;
import io.github.jxch.capital.vaadin.management.ManagementService;
import io.github.jxch.capital.vaadin.management.page.MPage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Management(name = "测试")
public class TestManagementService implements ManagementService<TestDto> {


    @Override
    public MPage<TestDto> findAll(int page, int size) {

        MPage<TestDto> mPage = new MPage<>();
        mPage.setItems(List.of(TestDto.builder().a("a1").b("a2").build(), TestDto.builder().a("b1").b("b2").build()));
        mPage.setPage(page);
        mPage.setSize(size);
        mPage.setTotal(100);
        mPage.setClazz(TestDto.class);
        if (page == 2) {
            mPage.setItems(List.of(TestDto.builder().a("2333").b("34r23fd").build()));
        }
        return mPage;
    }
}
