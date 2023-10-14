package com.sgnortes.ordermanagement.common.dto;

import lombok.Data;

import java.util.List;

/**
 * Generic class created to return paginated responses in an organized way.
 * @param <T> class of the element we will use
 * @author Sergio
 */
public class PageDto<T> {

    private List<T> data;
    private Integer page;
    private Integer size;
    private Long totalRegisters;
    private Integer totalPages;

    public PageDto(List<T> data, Integer page, Integer size, Long totalRegisters, Integer totalPages) {
        this.data = data;
        this.page = page;
        this.size = size;
        this.totalRegisters = totalRegisters;
        this.totalPages = totalPages;
    }

    // Getters and setters. Using lombok annotations give some problems so I generate them directly.
    // TODO: investigate why using lombok annotations doesn't work correctly in some cases.

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotalRegisters() {
        return totalRegisters;
    }

    public void setTotalRegisters(Long totalRegisters) {
        this.totalRegisters = totalRegisters;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
