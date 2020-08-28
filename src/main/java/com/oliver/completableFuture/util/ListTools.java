package com.oliver.completableFuture.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Oliver Wang
 * @description
 * @created by IntelliJ IDEA 2018.3.3
 * @date Create at 2020/8/28
 * @since
 */
public class ListTools {
    /**
     * 根据给定的pageSize分页
     *
     * @param <T>      集合类型
     * @param list     需要分页的集合
     * @param pageSize 你懂的
     * @return 一个分页的二维数组 list
     */
    public static <T> List<List<T>> pageByNum(List<T> list, int pageSize) {
        if (Objects.isNull(list)){
            return null;
        }
        return IntStream.range(0, list.size()).boxed().
                filter(t -> t % pageSize == 0).
                map(t -> list.stream().skip(t).limit(pageSize).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
