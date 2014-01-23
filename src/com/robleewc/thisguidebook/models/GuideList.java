
package com.robleewc.thisguidebook.models;

import java.util.List;

/**
 * @author Robert Lee
 */
public class GuideList {

    protected int total;
    protected List<Guide> data;

    public GuideList() {
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the data
     */
    public List<Guide> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<Guide> data) {
        this.data = data;
    }

}
