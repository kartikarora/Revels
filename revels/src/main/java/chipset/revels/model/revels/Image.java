package chipset.revels.model.revels;

import chipset.revels.R;

/**
 * Developer: chipset
 * Package : chipset.revels.model.revels
 * Project : Revels
 * Date : 21/1/15
 */

public class Image {

    private int[] images;

    public Image setData(int count) {
        images = new int[count + 1];
        images[0] = R.drawable.app_icon;
        images[1] = R.drawable.app_icon;
        images[2] = R.drawable.anubhuti;
        images[3] = R.drawable.congizance;
        images[4] = R.drawable.crescendo;
        images[5] = R.drawable.dramebaaz;
        images[6] = R.drawable.eqiq;
        images[7] = R.drawable.footloose;
        images[8] = R.drawable.gaming;
        images[9] = R.drawable.hautecouture;
        images[10] = R.drawable.kalakriti;
        images[11] = R.drawable.kodachrome;
        images[12] = R.drawable.paradigmshift;
        images[13] = R.drawable.psychus;
        images[14] = R.drawable.cup;
        images[15] = R.drawable.xventure;
        return this;
    }

    public int getImage(int i) {
        return images[i];
    }
}
