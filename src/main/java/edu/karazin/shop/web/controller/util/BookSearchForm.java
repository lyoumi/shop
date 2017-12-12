package edu.karazin.shop.web.controller.util;

import org.apache.commons.lang3.StringUtils;

public class BookSearchForm {

    String searchText;

    public BookSearchForm(){}

    public BookSearchForm(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookSearchForm that = (BookSearchForm) o;

        return searchText != null ? searchText.equals(that.searchText) : that.searchText == null;
    }

    @Override
    public int hashCode() {
        return searchText != null ? searchText.hashCode() : 0;
    }
}
