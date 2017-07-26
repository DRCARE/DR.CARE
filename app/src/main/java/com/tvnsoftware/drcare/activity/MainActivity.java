package com.tvnsoftware.drcare.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

import com.tvnsoftware.drcare.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tvnsoftware.drcare.R.dimen.abc_action_button_min_width_material;
import static com.tvnsoftware.drcare.R.dimen.abc_action_button_min_width_overflow_material;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search_toolbar)
    Toolbar mSearchToolbar;
    @BindView(R.id.fab_message)
    FloatingActionButton fab;
    @BindView(R.id.doctor_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rvDoctor)
    RecyclerView rvDoctor;
    private MenuItem mMenuItem;
    private Menu mSearchMenu;
    private SearchViewQueryCallback searchViewQueryCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpSearchToolbar();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //TODO : add chat function
                Intent i = new Intent(MainActivity.this, DiagnosisActivity.class);
                startActivity(i);
            }
        });
    }

    protected interface SearchViewQueryCallback {
        void onQuerySubmit(String queryString);
    }

    public void setSearchViewQueryCallback(SearchViewQueryCallback searchViewQueryCallback) {
        this.searchViewQueryCallback = searchViewQueryCallback;
    }

    protected void setUpSearchToolbar() {
        //this.mSearchToolbar = searchToolbar;
        if (mSearchToolbar != null) {
            mSearchToolbar.inflateMenu(R.menu.menu_search);
            mSearchMenu = mSearchToolbar.getMenu();

            mSearchToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(R.id.search_toolbar, 1, true, false);
                    } else {
                        mSearchToolbar.setVisibility(View.GONE);
                    }
                }
            });
            mMenuItem = mSearchMenu.findItem(R.id.action_search);

            MenuItemCompat.setOnActionExpandListener(mMenuItem,
                    new MenuItemCompat.OnActionExpandListener() {
                        @Override public boolean onMenuItemActionCollapse(MenuItem item) {
                            // Do something when collapsed
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                circleReveal(R.id.search_toolbar, 1, true, false);
                            } else {
                                mSearchToolbar.setVisibility(View.GONE);
                            }
                            return true;
                        }

                        @Override public boolean onMenuItemActionExpand(MenuItem item) {
                            // Do something when expanded
                            return true;
                        }
                    });

            initSearchView();
        } else {
            Log.d("toolbar", "setSearchToolbar: NULL");
        }
    }

    private void initSearchView() {
        final SearchView searchView =
                (SearchView) mSearchMenu.findItem(R.id.action_search).getActionView();

        // Enable/Disable Submit button in the keyboard
        searchView.setSubmitButtonEnabled(false);

        // Change search close button image
        ImageView closeButton = (ImageView) searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageResource(R.drawable.ic_close);

        ImageView backButton = (ImageView) searchView.findViewById(R.id.search_button);
        backButton.setImageResource(R.drawable.ic_arrow_back);

        // set hint and the text colors
        EditText txtSearch =
                ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        txtSearch.setHint("Search..");
        txtSearch.setHintTextColor(Color.DKGRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.primary));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewQueryCallback.onQuerySubmit(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void circleReveal(int viewID, int posFromRight, boolean containsOverflow, final boolean isShow)  {
        final View myView = findViewById(viewID);

        int width = myView.getWidth();

        if (posFromRight > 0) {
            width -= (posFromRight * getResources().getDimensionPixelSize(
                    abc_action_button_min_width_material)) - (getResources().getDimensionPixelSize(
                    abc_action_button_min_width_material) / 2);
        }
        if (containsOverflow) {
            width -= getResources().getDimensionPixelSize(abc_action_button_min_width_overflow_material);
        }

        int cx = width;
        int cy = myView.getHeight() / 2;

        Animator anim;
        if (isShow) {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, (float) width);
        } else {
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float) width, 0);
        }

        anim.setDuration((long) 400);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                if (!isShow) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        // make the view visible and start the animation
        if (isShow) myView.setVisibility(View.VISIBLE);

        // start the animation
        anim.start();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_search:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    circleReveal(R.id.search_toolbar, 1, true, true);
                } else {
                    mSearchToolbar.setVisibility(View.VISIBLE);
                }
                mMenuItem.expandActionView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
