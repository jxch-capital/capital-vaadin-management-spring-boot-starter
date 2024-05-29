package io.github.jxch.capital.vaadin.management.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MPage<T> {
    private List<T> items;
    private int page;
    private int size;
    private int total;
    private Class<T> clazz;
}
