package chipset.revels.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import io.fabric.sdk.android.Fabric;

/**
 * Developer: chipset
 * Package : chipset.revels.application
 * Project : Revels
 * Date : 17/2/15
 */

@ReportsCrashes(
        mailTo = "chipset95@gmail.com",
        mode = ReportingInteractionMode.SILENT)
public class AcraInitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        ACRA.init(this);
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(getApplicationContext(), "b4ySAS7cIdzpkfG78S61gsgXGnmejk7wC3VO4nOz", "o0U4wRrAgquQXj96F4fTx1C2LxUfyM3IobMJzyA0");
    }
}
