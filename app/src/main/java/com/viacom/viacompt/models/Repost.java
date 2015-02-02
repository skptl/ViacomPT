package com.viacom.viacompt.models;

import java.util.List;

/**
 * Created by Shilpan Patel on 2/2/15.
 */
public class Repost {

    private Number nextPage;
    private Number count;
    private String backAnchor;
    private String anchorStr;
    private List<Record> records;
    private Number previousPage;
    private Number size;
    private Number anchor;

    public Number getNextPage() {
        return nextPage;
    }

    public void setNextPage(Number nextPage) {
        this.nextPage = nextPage;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }

    public String getBackAnchor() {
        return backAnchor;
    }

    public void setBackAnchor(String backAnchor) {
        this.backAnchor = backAnchor;
    }

    public String getAnchorStr() {
        return anchorStr;
    }

    public void setAnchorStr(String anchorStr) {
        this.anchorStr = anchorStr;
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public Number getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Number previousPage) {
        this.previousPage = previousPage;
    }

    public Number getSize() {
        return size;
    }

    public void setSize(Number size) {
        this.size = size;
    }

    public Number getAnchor() {
        return anchor;
    }

    public void setAnchor(Number anchor) {
        this.anchor = anchor;
    }
}
