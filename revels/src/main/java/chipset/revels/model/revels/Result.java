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

public class Result {

    @SerializedName("data")
    @Expose
    private List<ResultDatum> data = new ArrayList<ResultDatum>();

    /**
     *
     * @return
     * The data
     */
    public List<ResultDatum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<ResultDatum> data) {
        this.data = data;
    }
}