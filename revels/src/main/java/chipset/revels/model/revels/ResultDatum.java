package chipset.revels.model.revels;

/**
 * Developer: chipset
 * Package : chipset.revels.model.revels
 * Project : Revels
 * Date : 19/1/15
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultDatum {

    @Expose
    private String event;
    @Expose
    private String category;
    @Expose
    private List<Content> content = new ArrayList<>();
    @SerializedName("event_code")
    @Expose
    private String eventCode;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @Expose
    private int count;

    /**
     * @return The event
     */
    public String getEvent() {
        return event;
    }

    /**
     * @param event The event
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The content
     */
    public List<Content> getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent(List<Content> content) {
        this.content = content;
    }

    /**
     * @return The eventCode
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * @param eventCode The event_code
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * @return The lastUpdated
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated The last_updated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
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

}