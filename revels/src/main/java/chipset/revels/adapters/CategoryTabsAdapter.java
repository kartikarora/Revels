package chipset.revels.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.gson.Gson;

import chipset.revels.fragments.EventsInCategoryFragment;
import chipset.revels.model.revels.Category;

/**
 * Developer: chipset
 * Package : chipset.revels.adapters
 * Project : Revels
 * Date : 29/12/14
 */
public class CategoryTabsAdapter extends FragmentPagerAdapter {
    Category category;
    Context context;
    String[] categories;

    public CategoryTabsAdapter(FragmentManager fm, Context context, Category category) {
        super(fm);
        this.context = context;
        this.category = category;
        this.categories = new String[category.getCount() + 1];
        this.categories[0] = "All Events";
        for (int i = 1; i < this.categories.length; i++) {
            this.categories[i] = category.getData().get(i-1).getCategory();
        }
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("category", new Gson().toJson(category));
        bundle.putInt("position", position);
        Fragment fragment = new EventsInCategoryFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
        return categories[position];
    }
}
