package chipset.revels.model.revels;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Event {

    @Expose
    private List<EventDatum> data = new ArrayList<>();
    @Expose
    private int count;
    @Expose
    private String timestamp;

    /**
     * @return The data
     */
    public List<EventDatum> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<EventDatum> data) {
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
