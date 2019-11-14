package com.example.dictionary

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import com.example.dictionary.models.Word
import com.example.dictionary.ui.detail.DetailFragment
import com.example.dictionary.ui.detail.DetailViewModel
import com.example.dictionary.ui.home.HomeFragment
import com.example.dictionary.ui.home.HomeViewModel
import com.example.dictionary.ui.viewpager.ViewFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    companion object{
        val TAG = "MainActivity"
    }

    lateinit var drawerToggle: ActionBarDrawerToggle

    private val homeFragment: HomeFragment by inject()
    private val detailFragment: DetailFragment by inject()
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        drawerToggle = ActionBarDrawerToggle(
            this,
            main_drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        main_drawerLayout.addDrawerListener(drawerToggle)

        main_navigation.setNavigationItemSelectedListener(this)
        main_navigation.setCheckedItem(0)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, homeFragment).commit()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when(p0.itemId){
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .addToBackStack(null)
                    .commit()
            }

           R.id.nav_detail -> {
               supportFragmentManager.beginTransaction()
                   .replace(R.id.fragment_container, ViewFragment())
                   .addToBackStack(null)
                   .commit()
           }
        }

        main_drawerLayout.closeDrawers()
        return true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStack()
        }else{
            super.onBackPressed()
        }
    }

}
