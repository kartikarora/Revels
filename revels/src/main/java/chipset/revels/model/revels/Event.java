package chipset.revels.model.revels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Event {

    @SerializedName("data")
    @Expose
    private List<EventDatum> data = new ArrayList<EventDatum>();
    private int count;
    /**
     *
     * @return
     * The data
     */
    public List<EventDatum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
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
}
