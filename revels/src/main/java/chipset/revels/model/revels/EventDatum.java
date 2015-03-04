package chipset.revels.model.revels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventDatum {

    @Expose
    private String event;
    @Expose
    private String category;
    @Expose
    private String description;
    @Expose
    private String location;
    @Expose
    private String start;
    @Expose
    private String stop;
    @Expose
    private String date;
    @Expose
    private String contact;
    @SerializedName("event_code")
    @Expose
    private String eventCode;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @Expose
    private int day;

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
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return The start
     */
    public String getStart() {
        return start;
    }

    /**
     * @param start The start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * @return The stop
     */
    public String getStop() {
        return stop;
    }

    /**
     * @param stop The stop
     */
    public void setStop(String stop) {
        this.stop = stop;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact The contact
     */
    public void setContact(String contact) {
        this.contact = contact;
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
     * @return The day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day The day
     */
    public void setDay(int day) {
        this.day = day;
    }

}
