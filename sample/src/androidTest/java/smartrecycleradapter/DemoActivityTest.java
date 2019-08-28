package smartrecycleradapter;

/*
 * Created by Manne Öhlund on 31/05/17.
 * Copyright © 2017 All rights reserved.
 */

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewAssertion;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import smartadapter.SmartRecyclerAdapter;
import smartrecycleradapter.models.ActionMoviesModel;
import smartrecycleradapter.models.AdventureMoviesModel;
import smartrecycleradapter.models.AnimatedMoviesModel;
import smartrecycleradapter.models.ComingSoonMoviesModel;
import smartrecycleradapter.models.CopyrightModel;
import smartrecycleradapter.models.MovieBannerModel;
import smartrecycleradapter.models.MoviePosterModel;
import smartrecycleradapter.models.MyWatchListModel;
import smartrecycleradapter.models.RecentlyPlayedMoviesModel;
import smartrecycleradapter.models.SciFiMoviesModel;
import smartrecycleradapter.viewholder.ActionMoviesViewHolder;
import smartrecycleradapter.viewholder.AdventureMoviesViewHolder;
import smartrecycleradapter.viewholder.AnimatedMoviesViewHolder;
import smartrecycleradapter.viewholder.BannerViewHolder;
import smartrecycleradapter.viewholder.ComingSoonMoviesViewHolder;
import smartrecycleradapter.viewholder.CopyrightViewHolder;
import smartrecycleradapter.viewholder.MyWatchListViewHolder;
import smartrecycleradapter.viewholder.PosterViewHolder;
import smartrecycleradapter.viewholder.RecentlyPlayedMoviesViewHolder;
import smartrecycleradapter.viewholder.SciFiMoviesViewHolder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DemoActivityTest {

    @Rule
    public ActivityTestRule<DemoActivity> rule  = new ActivityTestRule<>(DemoActivity.class);

    @Test
    public void testSmartRecyclerAdapter_getItems() {
        SmartRecyclerAdapter adapter = (SmartRecyclerAdapter) rule.getActivity().recyclerView.getAdapter();
        assertNotNull("Check SmartRecyclerAdapter is not null", adapter);
        assertEquals("MoviePosterModel count", 1, adapter.getItems(MoviePosterModel.class).size());
        assertEquals("MovieBannerModel count", 3, adapter.getItems(MovieBannerModel.class).size());
        assertEquals("ComingSoonMoviesModel count", 1, adapter.getItems(ComingSoonMoviesModel.class).size());
        assertEquals("MyWatchListModel count", 1, adapter.getItems(MyWatchListModel.class).size());
        assertEquals("ActionMoviesModel count", 1, adapter.getItems(ActionMoviesModel.class).size());
        assertEquals("AdventureMoviesModel count", 1, adapter.getItems(AdventureMoviesModel.class).size());
        assertEquals("AnimatedMoviesModel count", 1, adapter.getItems(AnimatedMoviesModel.class).size());
        assertEquals("SciFiMoviesModel count", 1, adapter.getItems(SciFiMoviesModel.class).size());
        assertEquals("RecentlyPlayedMoviesModel count", 1, adapter.getItems(RecentlyPlayedMoviesModel.class).size());
        assertEquals("CopyrightModel count", 1, adapter.getItems(CopyrightModel.class).size());
    }

    @Test
    public void testSmartRecyclerAdapter_matchesViewHolderAtPosition() {
        onView(withId(R.id.recycler_view))
                .check(matchesViewHolderAtPosition(0, PosterViewHolder.class))
                .perform(scrollToPosition(1))
                .check(matchesViewHolderAtPosition(1, ComingSoonMoviesViewHolder.class))
                .perform(scrollToPosition(2))
                .check(matchesViewHolderAtPosition(2, MyWatchListViewHolder.class))
                .perform(scrollToPosition(3))
                .check(matchesViewHolderAtPosition(3, BannerViewHolder.class))
                .perform(scrollToPosition(4))
                .check(matchesViewHolderAtPosition(4, ActionMoviesViewHolder.class))
                .perform(scrollToPosition(5))
                .check(matchesViewHolderAtPosition(5, AdventureMoviesViewHolder.class))
                .perform(scrollToPosition(6))
                .check(matchesViewHolderAtPosition(6, BannerViewHolder.class))
                .perform(scrollToPosition(7))
                .check(matchesViewHolderAtPosition(7, AnimatedMoviesViewHolder.class))
                .perform(scrollToPosition(8))
                .check(matchesViewHolderAtPosition(8, SciFiMoviesViewHolder.class))
                .perform(scrollToPosition(9))
                .check(matchesViewHolderAtPosition(9, BannerViewHolder.class))
                .perform(scrollToPosition(10))
                .check(matchesViewHolderAtPosition(10, RecentlyPlayedMoviesViewHolder.class))
                .perform(scrollToPosition(11))
                .check(matchesViewHolderAtPosition(11, CopyrightViewHolder.class));
    }

    private static ViewAssertion matchesViewHolderAtPosition(int position, Class<?> target) {
        return (view, noViewFoundException) -> {
            RecyclerView recyclerView = (RecyclerView) view;
            Class<? extends RecyclerView.ViewHolder> source = recyclerView.findViewHolderForAdapterPosition(position).getClass();
            assertTrue(String.format("Is <%s> assignable from <%s>", source, target), source.isAssignableFrom(target));
        };
    }
}