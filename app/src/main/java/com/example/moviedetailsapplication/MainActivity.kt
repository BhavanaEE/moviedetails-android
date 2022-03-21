package com.example.moviedetailsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.example.moviedetailsapplication.retrofitwithrecyclerview.MoviesAdapter
import com.example.moviedetailsapplication.ui.Movie
import com.google.android.material.tabs.TabLayout
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var moviesList =  ArrayList<Movie>()
    private var adapter : MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tabLayout= findViewById<TabLayout>(R.id.home_screen_tab_layout)
        val viewPager =  findViewById<ViewPager>(R.id.home_screen_pager)
        val pagerAdapter = ScreenAdapter(supportFragmentManager,tabLayout.tabCount)
        viewPager.adapter=pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        adapter= MoviesAdapter(moviesList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val getItemId=item.itemId
        if(getItemId==R.id.searchMovie) {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}