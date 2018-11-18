
package com.example.mk0730.alkolmetre.lcbo;

import com.squareup.moshi.Json;

public class LcboApiResponsePager {

    @Json(name = "records_per_page")
    private Integer recordsPerPage;
    @Json(name = "total_record_count")
    private Integer totalRecordCount;
    @Json(name = "current_page_record_count")
    private Integer currentPageRecordCount;
    @Json(name = "is_first_page")
    private Boolean isFirstPage;
    @Json(name = "is_final_page")
    private Boolean isFinalPage;
    @Json(name = "current_page")
    private Integer currentPage;
    @Json(name = "current_page_path")
    private String currentPagePath;
    @Json(name = "next_page")
    private Object nextPage;
    @Json(name = "next_page_path")
    private Object nextPagePath;
    @Json(name = "previous_page")
    private Object previousPage;
    @Json(name = "previous_page_path")
    private Object previousPagePath;
    @Json(name = "total_pages")
    private Integer totalPages;
    @Json(name = "total_pages_path")
    private String totalPagesPath;

    public Integer getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(Integer recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public Integer getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(Integer totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }

    public Integer getCurrentPageRecordCount() {
        return currentPageRecordCount;
    }

    public void setCurrentPageRecordCount(Integer currentPageRecordCount) {
        this.currentPageRecordCount = currentPageRecordCount;
    }

    public Boolean getIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(Boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public Boolean getIsFinalPage() {
        return isFinalPage;
    }

    public void setIsFinalPage(Boolean isFinalPage) {
        this.isFinalPage = isFinalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getCurrentPagePath() {
        return currentPagePath;
    }

    public void setCurrentPagePath(String currentPagePath) {
        this.currentPagePath = currentPagePath;
    }

    public Object getNextPage() {
        return nextPage;
    }

    public void setNextPage(Object nextPage) {
        this.nextPage = nextPage;
    }

    public Object getNextPagePath() {
        return nextPagePath;
    }

    public void setNextPagePath(Object nextPagePath) {
        this.nextPagePath = nextPagePath;
    }

    public Object getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Object previousPage) {
        this.previousPage = previousPage;
    }

    public Object getPreviousPagePath() {
        return previousPagePath;
    }

    public void setPreviousPagePath(Object previousPagePath) {
        this.previousPagePath = previousPagePath;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotalPagesPath() {
        return totalPagesPath;
    }

    public void setTotalPagesPath(String totalPagesPath) {
        this.totalPagesPath = totalPagesPath;
    }

}
