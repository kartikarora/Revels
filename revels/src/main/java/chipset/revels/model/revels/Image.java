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
        images = new int[count];
        images[0] = R.mipmap.ic_launcher;
        images[1] = R.drawable.gaming;
        images[2] = R.drawable.crescendo;
        images[3] = R.drawable.footloose;
        images[4] = R.drawable.dramebaaz;
        images[5] = R.drawable.omniscience;
        images[6] = R.drawable.kalakriti;
        images[7] = R.drawable.hautecouture;
        images[8] = R.drawable.anubhuti;
        images[9] = R.drawable.lensation;
        images[10] = R.drawable.xventure;
        images[11] = R.drawable.paradigmshift;
        images[12] = R.drawable.eqiq;
        images[13] = R.drawable.psychus;
        return this;
    }

    public int getImage(int i) {
        return images[i];
    }
}
