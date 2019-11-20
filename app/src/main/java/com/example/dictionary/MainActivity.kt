package com.example.dictionary

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dictionary.data.DATABASE_ANH_VIET
import com.example.dictionary.data.DATABASE_VIET_ANH
import com.example.dictionary.data.DatabaseAccess
import com.example.dictionary.models.Word
import com.example.dictionary.ui.detail.DetailFragment
import com.example.dictionary.ui.detail.DetailViewModel
import com.example.dictionary.ui.flashcard.FlashCardFragment
import com.example.dictionary.ui.home.HomeFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    companion object{
        val TAG = "MainActivity"
    }

    lateinit var drawerToggle: ActionBarDrawerToggle

    private lateinit var databaseEngVie: DatabaseAccess
    private lateinit var databaseVieEng: DatabaseAccess

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseEngVie = DatabaseAccess(this@MainActivity, DATABASE_ANH_VIET)
        databaseVieEng = DatabaseAccess(this@MainActivity, DATABASE_VIET_ANH)

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

        val homeFragment = HomeFragment(databaseEngVie)
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
                val homeFragment = HomeFragment(databaseEngVie)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit()
            }

            R.id.nav_viet_eng -> {
                val homeFragment = HomeFragment(databaseVieEng)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit()
            }

            R.id.nav_your_words -> {
                val flashCardFragment = FlashCardFragment(databaseEngVie)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, flashCardFragment)
                    .commit()
            }
        }

        main_drawerLayout.closeDrawers()
        return true
    }

    fun showDetailWord(word: Word) {

        val detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)

        detailViewModel.passWord(word)
        Log.e("TAG: Main", detailViewModel.toString())
        val detailFragment = DetailFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .commit()
    }

}
