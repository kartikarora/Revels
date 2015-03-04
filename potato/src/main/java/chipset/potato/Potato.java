package chipset.potato;

import chipset.potato.intents.Intents;
import chipset.potato.notifications.Notifications;
import chipset.potato.prefrences.Preferences;
import chipset.potato.utils.Utils;

/*
 * Developer: chipset
 * Package : chipset.potato
 * Project : PotatoLibrary
 * Date : 22/10/14
 */
public class Potato {

    public static Potato potate() {
        return new Potato();
    }

    public Utils getUtils() {
        return new Utils();
    }

    public Notifications getNotifications() {
        return new Notifications();
    }

    public Preferences getPreferences() {
        return new Preferences();
    }

    public Intents getIntents() {
        return new Intents();
    }


}
