package chipset.revels.model.revels;

/**
 * Developer: chipset
 * Package : chipset.revels.model.revels
 * Project : Revels
 * Date : 19/1/15
 */

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Result {

    @Expose
    private List<ResultDatum> data = new ArrayList<>();
    @Expose
    private Integer count;
    @Expose
    private String timestamp;

    /**
     * @return The data
     */
    public List<ResultDatum> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<ResultDatum> data) {
        this.data = data;
    }

    /**
     * @return The count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}