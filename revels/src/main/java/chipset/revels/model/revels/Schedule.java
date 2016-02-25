package chipset.revels.model.revels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saketh on 18/2/16.
 */
public class Schedule {
    @SerializedName("data")
    @Expose
    private List<ScheduleDatum> data = new ArrayList<ScheduleDatum>();

    /**
     * @return The data
     */
    public List<ScheduleDatum> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<ScheduleDatum> data) {
        this.data = data;
    }
}
