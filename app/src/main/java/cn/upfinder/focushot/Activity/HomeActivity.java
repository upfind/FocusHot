package cn.upfinder.focushot.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import cn.upfinder.focushot.Fragment.AboutFragment;
import cn.upfinder.focushot.Fragment.BeautyFragment;
import cn.upfinder.focushot.Fragment.QiuBaiFragment;
import cn.upfinder.focushot.Fragment.TabPagerFragment;
import cn.upfinder.focushot.Fragment.SwipeFragment;
import cn.upfinder.focushot.Fragment.ZhihuDailyFragment;
import cn.upfinder.focushot.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RelativeLayout rlContent;
    //切换的Fragment
    private TabPagerFragment newsTabFragment; //聚合新闻
    private FragmentManager fragmentManager;
    private TabPagerFragment itTabFragment; //IT资讯
    private QiuBaiFragment qiuBaiFragment; //测试
    private TabPagerFragment mediaTabFragment; //多媒体
    private AboutFragment aboutFragment; //关于我

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        rlContent = (RelativeLayout) findViewById(R.id.content_home);
        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_home);
        newsTabFragment = TabPagerFragment.newInstance(TabPagerFragment.ARG_NEWS);
        if (fragment == null) {
            fragmentManager.beginTransaction().add(R.id.content_home, newsTabFragment).commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.content_home, newsTabFragment).commit();
        }
        toolbar.setTitle(getString(R.string.title_fragment_news));

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
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

        if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else {
            selectFragmentShow(item.getItemId());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void selectFragmentShow(int menuId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (menuId) {
            case R.id.nav_news:
                if (newsTabFragment != null) {
                    transaction.show(newsTabFragment);
                } else {
                    newsTabFragment = TabPagerFragment.newInstance(TabPagerFragment.ARG_NEWS);
                    transaction.add(R.id.content_home, newsTabFragment);
                }
                toolbar.setTitle(getString(R.string.title_fragment_news));
                break;
            case R.id.nav_it:
                if (itTabFragment != null) {
                    transaction.show(itTabFragment);
                } else {
                    itTabFragment = TabPagerFragment.newInstance(TabPagerFragment.ARG_IT);
                    transaction.add(R.id.content_home, itTabFragment);
                }
                toolbar.setTitle(getString(R.string.title_fragment_it));
                break;
            case R.id.nav_joke:
                if (qiuBaiFragment != null) {
                    transaction.show(qiuBaiFragment);
                } else {
                    qiuBaiFragment = QiuBaiFragment.newInstance("", "");
                    transaction.add(R.id.content_home, qiuBaiFragment);
                }
                toolbar.setTitle(getString(R.string.title_fragment_jock));
                break;
            case R.id.nav_media:
                if (mediaTabFragment != null) {
                    transaction.show(mediaTabFragment);
                } else {
                    mediaTabFragment = TabPagerFragment.newInstance(TabPagerFragment.ARG_MEDIA);
                    transaction.add(R.id.content_home, mediaTabFragment);
                }
                toolbar.setTitle(getString(R.string.title_fragment_media));
                break;
            case R.id.nav_about:

                if (aboutFragment != null) {
                    transaction.show(aboutFragment);
                } else {
                    aboutFragment = AboutFragment.newInstance("", "");
                    transaction.add(R.id.content_home, aboutFragment);
                }
                break;
        }
        transaction.commit();
    }

    //隐藏所有Fragment
    private void hideFragments(FragmentTransaction fragmentTransaction) {

        if (newsTabFragment != null) {
            fragmentTransaction.hide(newsTabFragment);
        }
        if (itTabFragment != null) {
            fragmentTransaction.hide(itTabFragment);
        }
        if (qiuBaiFragment != null) {
            fragmentTransaction.hide(qiuBaiFragment);
        }
        if (mediaTabFragment != null) {
            fragmentTransaction.hide(mediaTabFragment);
        }

        if (aboutFragment != null) {
            fragmentTransaction.hide(aboutFragment);
        }
    }

}
