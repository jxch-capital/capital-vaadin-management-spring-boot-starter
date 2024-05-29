package io.github.jxch.capital.vaadin.management.service;

import io.github.jxch.capital.vaadin.management.MField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TestDto {
    @MField(name = "第一个字段")
    private String a;
    private String b;
}
