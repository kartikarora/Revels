package chipset.revels.model.revels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Developer: chipset
 * Package : chipset.revels.model.revels
 * Project : Revels
 * Date : 5/1/15
 */

public class Category {

    @SerializedName("data")
    @Expose
    private List<CategoryDatum> data = new ArrayList<CategoryDatum>();
    private int count;
    /**
     *
     * @return
     * The data
     */
    public List<CategoryDatum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<CategoryDatum> data) {
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
