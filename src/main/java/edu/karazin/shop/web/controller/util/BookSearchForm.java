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
}
