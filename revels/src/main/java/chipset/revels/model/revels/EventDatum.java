package chipset.revels.model.revels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventDatum {

    @SerializedName("ename")
    @Expose
    private String ename;
    @SerializedName("eid")
    @Expose
    private String eid;
    @SerializedName("edesc")
    @Expose
    private String edesc;
    @SerializedName("emaxteamsize")
    @Expose
    private String emaxteamsize;
    @SerializedName("cname")
    @Expose
    private String cname;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("cntctname")
    @Expose
    private String cntctname;
    @SerializedName("cntctno")
    @Expose
    private String cntctno;
    private String roundno;
    private String evenue;
    private String  strttime;
    private String endtime;
    private String day;
    private String date;
    /**
     *
     * @return
     * The ename
     */
    public String getEname() {
        return ename;
    }

    /**
     *
     * @param ename
     * The ename
     */
    public void setEname(String ename) {
        this.ename = ename;
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
     * The edesc
     */
    public String getEdesc() {
        return edesc;
    }

    /**
     *
     * @param edesc
     * The edesc
     */
    public void setEdesc(String edesc) {
        this.edesc = edesc;
    }

    /**
     *
     * @return
     * The emaxteamsize
     */
    public String getEmaxteamsize() {
        return emaxteamsize;
    }

    /**
     *
     * @param emaxteamsize
     * The emaxteamsize
     */
    public void setEmaxteamsize(String emaxteamsize) {
        this.emaxteamsize = emaxteamsize;
    }

    /**
     *
     * @return
     * The cname
     */
    public String getCname() {
        return cname;
    }

    /**
     *
     * @param cname
     * The cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

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
     * The cntctname
     */
    public String getCntctname() {
        return cntctname;
    }

    /**
     *
     * @param cntctname
     * The cntctname
     */
    public void setCntctname(String cntctname) {
        this.cntctname = cntctname;
    }

    /**
     *
     * @return
     * The cntctno
     */
    public String getCntctno() {
        return cntctno;
    }

    /**
     *
     * @param cntctno
     * The cntctno
     */
    public void setCntctno(String cntctno) {
        this.cntctno = cntctno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoundno() {
        return roundno;
    }

    public void setRoundno(String roundno) {
        this.roundno = roundno;
    }

    public String getEvenue() {
        return evenue;
    }

    public void setEvenue(String evenue) {
        this.evenue = evenue;
    }

    public String getStrttime() {
        return strttime;
    }

    public void setStrttime(String strttime) {
        this.strttime = strttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
