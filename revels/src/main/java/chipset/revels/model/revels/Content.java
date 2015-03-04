package chipset.revels.model.revels;

/**
 * Developer: chipset
 * Package : chipset.revels.model.revels
 * Project : Revels
 * Date : 19/1/15
 */

import com.google.gson.annotations.Expose;

public class Content {

    @Expose
    private String position;
    @Expose
    private String delegate;

    /**
     * @return The position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position The position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return The delegate
     */
    public String getDelegate() {
        return delegate;
    }

    /**
     * @param delegate The delegate
     */
    public void setDelegate(String delegate) {
        this.delegate = delegate;
    }

}