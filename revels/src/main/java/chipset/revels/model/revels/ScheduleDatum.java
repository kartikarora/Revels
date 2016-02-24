package chipset.revels.model.revels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saketh on 18/2/16.
 */
public class ScheduleDatum {
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("eid")
    @Expose
    private String eid;
    @SerializedName("roundno")
    @Expose
    private String roundno;
    @SerializedName("evenue")
    @Expose
    private String evenue;
    @SerializedName("strttime")
    @Expose
    private String strttime;
    @SerializedName("endtime")
    @Expose
    private String endtime;
    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("date")
    @Expose
    private String date;

    /**
     *
     * @return
     * The cid
     */
    public String getCid() {
        return cid;
    }

    /**
     *
     * @param cid
     * The cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }

    /**
     *
     * @return
     * The eid
     */
    public String getEid() {
        return eid;
    }

    /**
     *
     * @param eid
     * The eid
     */
    public void setEid(String eid) {
        this.eid = eid;
    }

    /**
     *
     * @return
     * The roundno
     */
    public String getRoundno() {
        return roundno;
    }

    /**
     *
     * @param roundno
     * The roundno
     */
    public void setRoundno(String roundno) {
        this.roundno = roundno;
    }

    /**
     *
     * @return
     * The evenue
     */
    public String getEvenue() {
        return evenue;
    }

    /**
     *
     * @param evenue
     * The evenue
     */
    public void setEvenue(String evenue) {
        this.evenue = evenue;
    }

    /**
     *
     * @return
     * The strttime
     */
    public String getStrttime() {
        return strttime;
    }

    /**
     *
     * @param strttime
     * The strttime
     */
    public void setStrttime(String strttime) {
        this.strttime = strttime;
    }

    /**
     *
     * @return
     * The endtime
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     *
     * @param endtime
     * The endtime
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     *
     * @return
     * The day
     */
    public String getDay() {
        return day;
    }

    /**
     *
     * @param day
     * The day
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }
}
