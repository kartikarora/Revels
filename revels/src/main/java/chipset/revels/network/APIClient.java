package chipset.revels.network;

import chipset.revels.model.instagram.InstaFeed;
import chipset.revels.model.revels.Category;
import chipset.revels.model.revels.Event;
import chipset.revels.model.revels.Result;
import chipset.revels.model.revels.Schedule;
import chipset.revels.resources.Constants;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Developer: chipset
 * Package : chipset.revels.network
 * Project : Revels
 * Date : 5/1/15
 */

public class APIClient {
    private static DataInterface dataInterface = null;
    private static InstaFeedInterface instaFeedInterface = null;

    public static DataInterface getRevels() {
        if (dataInterface == null) {
            String URL_API = "http://mitrevels.in/apidata";
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(URL_API)
                    .build();

            dataInterface = restAdapter.create(DataInterface.class);
        }
        return dataInterface;
    }

    public static InstaFeedInterface getInstagram() {
        if (instaFeedInterface == null) {
            String URL_INSTA = "https://api.instagram.com";
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(URL_INSTA)
                    .build();

            instaFeedInterface = restAdapter.create(InstaFeedInterface.class);
        }
        return instaFeedInterface;
    }

    public interface DataInterface {
        @GET("/categories/")
        void getCategory(Callback<Category> categoryCallback);

        @GET("/events/")
        void getAllEvents(Callback<Event> eventCallback);

        @GET("/events/")
        void getEventFromEndPoint(@Query("category_code") String ep, Callback<Event> eventCallback);

        @GET("/results/")
        void getResults(Callback<Result> resultCallback);

        @GET("/events.php")
        void getEvents(Callback<Event> eventCallback);

        @GET("/categories.php")
        void getCategories(Callback<Category> categoryCallback);

        @GET("/schedule.php")
        void getSchedule(Callback<Schedule> scheduleCallback);
    }

    public interface InstaFeedInterface {
        @GET(Constants.INSTA_API_ENDPOINT)
        void getFeed(Callback<InstaFeed> instaFeedCallback);
    }
}
