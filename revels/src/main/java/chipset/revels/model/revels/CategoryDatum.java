package chipset.revels.model.revels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryDatum {

    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("cname")
    @Expose
    private String cname;
    @SerializedName("cdesc")
    @Expose
    private String cdesc;

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
     * The cdesc
     */
    public String getCdesc() {
        return cdesc;
    }

    /**
     *
     * @param cdesc
     * The cdesc
     */
    public void setCdesc(String cdesc) {
        this.cdesc = cdesc;
    }
}
