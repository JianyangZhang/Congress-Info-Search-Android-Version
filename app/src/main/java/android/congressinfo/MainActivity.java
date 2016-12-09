package android.congressinfo;



import android.congressinfo.TabFragments.*;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private ViewPager viewPager1;
    private ViewPager viewPager2;
    private ViewPager viewPager3;
    private ViewPager viewPager4;
    private TabLayout tabLayout;
    protected NavigationView navigationView;

    private static int currentDrawer = 0;
    public static int currentPage_l = 0;
    public static int currentPage_b = 0;
    public static int currentPage_c = 0;
    public static int currentPage_f = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager1 = (ViewPager) findViewById(R.id.viewpager1);
        viewPager2 = (ViewPager) findViewById(R.id.viewpager2);
        viewPager3 = (ViewPager) findViewById(R.id.viewpager3);
        viewPager4 = (ViewPager) findViewById(R.id.viewpager4);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.onNavigationItemSelected(navigationView.getMenu().getItem(currentDrawer));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*----------------------------------------------------------------------------*/

    }

    private void setupViewPager_l(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentOne(), "BY STATES");
        adapter.addFragment(new FragmentTwo(), "HOUSE");
        adapter.addFragment(new FragmentThree(), "SENATE");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    private void setupViewPager_b(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentFour(), "ACTIVE BILLS");
        adapter.addFragment(new FragmentFive(), "NEW BILLS");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    private void setupViewPager_c(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentSix(), "HOUSE");
        adapter.addFragment(new FragmentSeven(), "SENATE");
        adapter.addFragment(new FragmentEight(), "JOINT");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    private void setupViewPager_f(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentNine(), "LEGISLATORS");
        adapter.addFragment(new FragmentTen(), "BILLS");
        adapter.addFragment(new FragmentEleven(), "COMMITTEES");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_legislator) {
            setTitle("Legislators");
            currentDrawer = 0;
            currentPage_b = 0;
            currentPage_c = 0;
            currentPage_f = 0;
            viewPager2.setAdapter(null);
            viewPager3.setAdapter(null);
            viewPager4.setAdapter(null);
            setupViewPager_l(viewPager1);
            tabLayout.setupWithViewPager(viewPager1);
            viewPager1.setCurrentItem(currentPage_l);

        } else if (id == R.id.nav_bill) {
            setTitle("Bills");
            currentDrawer = 1;
            currentPage_l = 0;
            currentPage_c = 0;
            currentPage_f = 0;
            viewPager1.setAdapter(null);
            viewPager3.setAdapter(null);
            viewPager4.setAdapter(null);
            setupViewPager_b(viewPager2);
            tabLayout.setupWithViewPager(viewPager2);
            viewPager2.setCurrentItem(currentPage_b);

        } else if (id == R.id.nav_committee) {
            setTitle("Committees");
            currentDrawer = 2;
            currentPage_l = 0;
            currentPage_b = 0;
            currentPage_f = 0;
            viewPager1.setAdapter(null);
            viewPager2.setAdapter(null);
            viewPager4.setAdapter(null);
            setupViewPager_c(viewPager3);
            tabLayout.setupWithViewPager(viewPager3);
            viewPager3.setCurrentItem(currentPage_c);

        } else if (id == R.id.nav_favorite) {
            setTitle("Favorites");
            currentDrawer = 3;
            currentPage_l = 0;
            currentPage_b = 0;
            currentPage_c = 0;
            viewPager1.setAdapter(null);
            viewPager2.setAdapter(null);
            viewPager3.setAdapter(null);
            setupViewPager_f(viewPager4);
            tabLayout.setupWithViewPager(viewPager4);
            viewPager4.setCurrentItem(currentPage_f);

        } else if (id == R.id.nav_aboutMe) {
            setTitle("About Me");
            currentPage_l = 0;
            currentPage_b = 0;
            currentPage_c = 0;
            currentPage_f = 0;
            viewPager1.setAdapter(null);
            viewPager2.setAdapter(null);
            viewPager3.setAdapter(null);
            viewPager4.setAdapter(null);
            Intent intent = new Intent(this, AboutMe.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
