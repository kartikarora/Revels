package chipset.revels.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nirhart.parallaxscroll.views.ParallaxListView;
import com.nispok.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import chipset.potato.Potato;
import chipset.revels.R;
import chipset.revels.activities.DetailDialogActivity;
import chipset.revels.adapters.CategoryListAdapter;
import chipset.revels.model.revels.Category;
import chipset.revels.model.revels.Event;
import chipset.revels.model.revels.EventDatum;
import chipset.revels.model.revels.Image;
import chipset.revels.model.revels.Schedule;
import chipset.revels.model.revels.ScheduleDatum;
import chipset.revels.network.APIClient;
import chipset.revels.resources.Constants;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Developer: chipset
 * Package : chipset.revels.fragments
 * Project : Revels
 * Date : 30/12/14
 */

public class EventsInCategoryFragment extends Fragment {
    private Category category;
    private ParallaxListView eventListView;
    private ProgressBar eventProgressBar;
    private int position;
    LinearLayout mButtonLinearLayout;
    private Animation animUp;
    private Animation animDown;
    float startY;
    boolean isUp = false;
    int day = 1;
    Button dayOneButton, dayTwoButton, dayThreeButton, dayFourButton;
    List<EventDatum> eventOneList = new ArrayList<>();
    List<EventDatum> eventTwoList = new ArrayList<>();
    List<EventDatum> eventThreeList = new ArrayList<>();
    List<EventDatum> eventFourList = new ArrayList<>();
    Event eventOne = new Event();
    Event eventTwo = new Event();
    Event eventThree = new Event();
    Event eventFour = new Event();
    LayoutInflater inflater;
    ViewGroup container;
    ImageView imageView;
    TextView textView;
    ActionBar mActionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        category = new Gson().fromJson(getArguments().getString("category"), Category.class);
        position = getArguments().getInt("position");
        this.inflater = inflater;
        this.container = container;
        View view = inflater.inflate(R.layout.fragment_events_in_category, container,
                false);
        eventListView = (ParallaxListView) view.findViewById(R.id.event_list_view);
        eventProgressBar = (ProgressBar) view.findViewById(R.id.event_progress_bar);
        mButtonLinearLayout = (LinearLayout) view.findViewById(R.id.button_linear_layout);
        dayOneButton = (Button) view.findViewById(R.id.day_one_button);
        dayTwoButton = (Button) view.findViewById(R.id.day_two_button);
        dayThreeButton = (Button) view.findViewById(R.id.day_three_button);
        dayFourButton = (Button) view.findViewById(R.id.day_four_button);
        textView = (TextView) view.findViewById(R.id.no_events_today_text_view);
        imageView = new ImageView(view.getContext());

        animUp = AnimationUtils.loadAnimation(view.getContext(), R.anim.animation_up);
        animDown = AnimationUtils.loadAnimation(view.getContext(), R.anim.animation_down);

        Image image = new Gson().fromJson(Potato.potate().getPreferences().getSharedPreferenceString(view.getContext(), Constants.IMAGE), Image.class);
        Picasso.with(view.getContext()).load(image.getImage(position)).into(imageView);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 250);
        imageView.setBackground(getResources().getDrawable(R.drawable.card));
        imageView.setLayoutParams(params);
        eventListView.addParallaxedHeaderView(imageView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        eventListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        startY = motionEvent.getY();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        float endY = motionEvent.getY();
                        if (endY < startY) {
                            if (!isUp) {
                                mButtonLinearLayout.setVisibility(View.VISIBLE);
                                mButtonLinearLayout.startAnimation(animUp);
                                isUp = true;
                                mActionBar.hide();
                            }
                        } else {
                            if (isUp) {
                                mButtonLinearLayout.setVisibility(View.GONE);
                                mButtonLinearLayout.startAnimation(animDown);
                                isUp = false;
                                mActionBar.show();
                            }
                        }
                    }
                }
                return false;
            }
        });

        if (position == 0) {
            APIClient.getRevels().getAllEvents(new Callback<Event>() {
                @Override
                public void success(Event event, Response response) {
                    if (event.getData().size() == 0) {
                        textView.setVisibility(View.VISIBLE);
                        eventProgressBar.setVisibility(View.GONE);
                        mButtonLinearLayout.setVisibility(View.VISIBLE);
                        mButtonLinearLayout.startAnimation(animUp);
                    } else {
                        segregateEvents(event);
                        updateList(view, event);
                        Potato.potate().getPreferences().putSharedPreference(view.getContext(), Constants.EVENT, new Gson().toJson(event));
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    if (!Potato.potate().getPreferences().getSharedPreferenceBoolean(view.getContext(), Constants.FIRST_RUN)) {
                        Event event = new Gson().fromJson(Potato.potate().getPreferences().getSharedPreferenceString(view.getContext(), Constants.EVENT), Event.class);
                        try {
                            segregateAndUpdate(view, event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        error.printStackTrace();
                        Snackbar.with(view.getContext()).text("Oops! Something went wrong!").show(getActivity());
                    }
                }
            });
        } else {
            /*
            APIClient.getRevels().getEventFromEndPoint(category.getData().get(position - 1).getCid(), new Callback<Event>() {
                @Override
                public void success(Event event, Response response) {
                    if (event.getCount() == 0) {
                        textView.setVisibility(View.VISIBLE);
                        eventProgressBar.setVisibility(View.GONE);
                        mButtonLinearLayout.setVisibility(View.VISIBLE);
                        mButtonLinearLayout.startAnimation(animUp);
                    } else {
                        try {
                            segregateAndUpdate(view, event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Potato.potate().getPreferences().putSharedPreference(view.getContext(), Constants.EVENT + category.getData().get(position - 1).getCategoryCode(), new Gson().toJson(event));
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    if (!Potato.potate().getPreferences().getSharedPreferenceBoolean(view.getContext(), Constants.FIRST_RUN)) {
                        Event event = new Gson().fromJson(Potato.potate().getPreferences().getSharedPreferenceString(view.getContext(), Constants.EVENT + category.getData().get(position - 1).getCategoryCode()), Event.class);
                        try {
                            segregateAndUpdate(view, event);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        error.printStackTrace();
                        Snackbar.with(view.getContext()).text("Oops! Something went wrong!").show(getActivity());
                    }
                }
            });*/
            Event event = new Gson().fromJson(Potato.potate().getPreferences().getSharedPreferenceString(getActivity(), Constants.EVENT), Event.class);
            Event event1 = new Event();
            ArrayList<EventDatum> eventData = new ArrayList<>();
            for (EventDatum eventDatum : event.getData()) {
                if (eventDatum.getCid().equals(category.getData().get(position - 1).getCid()))
                    eventData.add(eventDatum);
            }
            event1.setCount(eventData.size());
            event1.setData(eventData);
            try {
                segregateAndUpdate(view, event1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        dayOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = 1;
                updateList(view, eventOne);
            }
        });
        dayTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = 2;
                updateList(view, eventTwo);
            }
        });
        dayThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = 3;
                updateList(view, eventThree);
            }
        });
        dayFourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day = 4;
                updateList(view, eventFour);
            }
        });
    }

    private void updateList(View view, Event event) {
        final CategoryListAdapter adapter = new CategoryListAdapter(view.getContext(), event, position);
        adapter.notifyDataSetChanged();
        eventListView.setAdapter(adapter);
        eventListView.setVisibility(View.VISIBLE);
        eventProgressBar.setVisibility(View.GONE);
        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    view.getContext().startActivity(new Intent(view.getContext(), DetailDialogActivity.class).putExtra(Constants.EVENT_DATA, new Gson().toJson(adapter.getItem(position - 1))).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void segregateEvents(Event event) {
        List<EventDatum> eventDatumList = event.getData();
        Schedule schedule = new Gson().fromJson(Potato.potate().getPreferences().getSharedPreferenceString(getActivity(), Constants.SCHEDULE), Schedule.class);
        Log.d("schedule", schedule.toString());
        for (int i = 0; i < eventDatumList.size(); i++) {
            EventDatum eventDatum = eventDatumList.get(i);
            eventDatum.setDay("1");
            eventDatum.setStrttime("NA");
            eventDatum.setEvenue("NA");
            eventDatum.setEndtime("NA");
            eventDatum.setRoundno("NA");
            eventDatum.setDate("NA");
            eventDatumList.remove(eventDatum);
            eventDatumList.add(i, eventDatum);
        }
        for (int i = 0; i < eventDatumList.size(); i++) {
            EventDatum eventDatum = eventDatumList.get(i);
            for (ScheduleDatum scheduleDatum : schedule.getData()) {
                if (eventDatum.getEid().equals(scheduleDatum.getEid())) {
                    eventDatum.setDay(scheduleDatum.getDay());
                    eventDatum.setStrttime(scheduleDatum.getStrttime());
                    eventDatum.setEvenue(scheduleDatum.getEvenue());
                    eventDatum.setEndtime(scheduleDatum.getEndtime());
                    eventDatum.setRoundno(scheduleDatum.getRoundno());
                    eventDatum.setDate(scheduleDatum.getDate());
                    eventDatumList.remove(eventDatum);
                    eventDatumList.add(i, eventDatum);
                }
            }
        }
        for (EventDatum eventDatum : eventDatumList) {
            Log.d("event name", eventDatum.getEname());
        }
        for (int i = 0; i < eventDatumList.size(); i++) {
            int day = Integer.parseInt(eventDatumList.get(i).getDay());
            switch (day) {
                case 1: {
                    eventOneList.add(eventDatumList.get(i));
                    break;
                }
                case 2: {
                    eventTwoList.add(eventDatumList.get(i));
                    break;
                }
                case 3: {
                    eventThreeList.add(eventDatumList.get(i));
                    break;
                }
                case 4: {
                    eventFourList.add(eventDatumList.get(i));
                    break;
                }
            }
        }
        eventOne.setData(eventOneList);
        eventOne.setCount(eventOneList.size());
        eventTwo.setData(eventTwoList);
        eventTwo.setCount(eventTwoList.size());
        eventThree.setData(eventThreeList);
        eventThree.setCount(eventThreeList.size());
        eventFour.setData(eventFourList);
        eventFour.setCount(eventFourList.size());
    }

    public void segregateAndUpdate(View view, Event event) throws Exception {
        if (event == null) {
            textView.setVisibility(View.VISIBLE);
            eventProgressBar.setVisibility(View.GONE);
            mButtonLinearLayout.setVisibility(View.VISIBLE);
            mButtonLinearLayout.startAnimation(animUp);
        } else {
            textView.setVisibility(View.GONE);
            mButtonLinearLayout.setVisibility(View.GONE);
        }
        segregateEvents(event);
        updateList(view, event);
    }
}