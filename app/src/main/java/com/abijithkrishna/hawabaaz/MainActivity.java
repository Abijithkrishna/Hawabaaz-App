package com.abijithkrishna.hawabaaz;

import android.app.Activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,HomeFragment.OnFragmentInteractionListener,LocationFragment.OnFragmentInteractionListener,FoodMenuFragment.OnFragmentInteractionListener,JuiceFragment.OnFragmentInteractionListener,SandwichFragment.OnFragmentInteractionListener,MainCourseFragment.OnFragmentInteractionListener,SpecialFragment.OnFragmentInteractionListener,OrderHistoryFragment.OnFragmentInteractionListener,NotificationsFragment.OnFragmentInteractionListener,MyLocationsFragment.OnFragmentInteractionListener,RegisterFragment.OnFragmentInteractionListener,LoginFragment.OnFragmentInteractionListener,TrackOrderFragment.OnFragmentInteractionListener,CancelOrderFragment.OnFragmentInteractionListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Stack<CharSequence> titileStack = new Stack<CharSequence>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment mCurr = null;
        switch (position){
            case 0:
                mCurr = new HomeFragment();
                break;
            case 1:
                mCurr = new LocationFragment();
                break;
            case 2:
                mCurr = new FoodMenuFragment();
                break;
            case 3:
                mCurr = new OrderHistoryFragment();
                break;
            case 4:
                mCurr = new NotificationsFragment();
                break;
            case 5:
                mCurr = new MyLocationsFragment();
                break;
            case 6:
                mCurr = new RegisterFragment();
                break;
            case 7:
                mCurr = new LoginFragment();
                break;
        }
        onSectionAttached(position);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.container, mCurr)
                .commit();
        //mTitle = null;
        //onSectionAttached(position);
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 0:
                mTitle = "Home";
                break;
            case 1:
                mTitle = "Location";
                break;
            case 2:
                mTitle = "Food Menu";
                break;
            case 3:
                mTitle = "Order History";
                break;
            case 4:
                mTitle = "Notifications";
                break;
            case 5:
                mTitle = "My Locations";
                break;
            case 6:
                mTitle = "Register";
                break;
            case 7:
                mTitle = "Login";
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        titileStack.push(mTitle);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.trackorder:
            {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TrackOrderFragment fragment = new TrackOrderFragment();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                mTitle="Track Order";
                //titileStack.push(mTitle);
                restoreActionBar();
                fragmentTransaction.commit();
                return true;
            }
            case R.id.cancelorder:
            {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CancelOrderFragment fragment = new CancelOrderFragment();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                mTitle="Cancel Order";
                //titileStack.push(mTitle);
                restoreActionBar();
                fragmentTransaction.commit();
                return true;
            }
            case R.id.callcare:{
                Toast.makeText(getApplicationContext(), "YET TO COME",
                        Toast.LENGTH_LONG).show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
        //return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        //restoreActionBar();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);

        try{
            titileStack.pop();
            actionBar.setTitle(titileStack.peek());
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "ThankYou ComeAgain",
                    Toast.LENGTH_LONG).show();
            System.out.println(e);
            finish();
        }
        //actionBar.setTitle("Hawabazz");
        super.onBackPressed();
    }
}
