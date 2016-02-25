package chipset.revels.model.revels;

/**
 * Developer: chipset
 * Package : chipset.revels.model.revels
 * Project : Revels
 * Date : 19/1/15
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultDatum {

    @SerializedName("eventName")
    @Expose
    private String eventName;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("result")
    @Expose
    private String result;

    /**
     *
     * @return
     * The eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     *
     * @param eventName
     * The eventName
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     *
     * @return
     * The categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     *
     * @param categoryName
     * The categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     *
     * @return
     * The result
     */
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(String result) {
        this.result = result;
    }
}