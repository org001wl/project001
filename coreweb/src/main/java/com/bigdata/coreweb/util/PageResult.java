package com.bigdata.coreweb.util;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private Integer totalCount;
    private Integer currentIndex;
    private Integer pageSize;
    private List<T> items;

    public static <T> PageResult<T> create(int totalCount, int currentIndex, int pageSize, List<T> itemList) {
        PageResult<T> tPageResult = new PageResult<>();
        tPageResult.setCurrentIndex(currentIndex);
        tPageResult.setPageSize(pageSize);
        tPageResult.setTotalCount(totalCount);
        tPageResult.setItems(itemList);
        return tPageResult;
    }

}
