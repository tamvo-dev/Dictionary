package com.example.dictionary.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.*
import androidx.lifecycle.ViewModelProviders
import com.example.dictionary.MainActivity
import com.example.dictionary.R
import com.example.dictionary.data.DATABASE_ANH_VIET
import com.example.dictionary.data.DatabaseAccess


class HomeFragment(private val database: DatabaseAccess) : Fragment(), SearchView.OnQueryTextListener {

    companion object{
        val TAG = "HomeFragment"
    }

    lateinit var listView: ListView
    lateinit var searchView: SearchView
    lateinit var adapter: WordAdapter
    lateinit var progressBar: ProgressBar

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        listView = view.findViewById(R.id.home_listView)
        searchView = view.findViewById(R.id.home_searchView)
        progressBar = view.findViewById(R.id.home_progressBar)

        val factory = object : Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeViewModel(database) as T
            }
        }
        homeViewModel = factory.create(HomeViewModel::class.java)

        adapter = WordAdapter()
        listView.adapter = adapter

        homeViewModel.isLoading.observe(this, Observer {
            progressBar.visibility = it
        })

        homeViewModel.words.observe(this, Observer {
            adapter.addWords(it)
        })

        listView.setOnItemClickListener { parent, view, position, id ->
            val word = adapter.getItem(position)
            (activity as MainActivity).showDetailWord(word)
        }

        searchView.setOnQueryTextListener(this)
        listView.setOnScrollListener(object : AbsListView.OnScrollListener {

            var isScroll = false

            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                if(isScroll && totalItemCount == firstVisibleItem + visibleItemCount){
                    homeViewModel.loadData()
                    isScroll = false
                }
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
                isScroll = true
            }

        })

        return view
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.run {
            homeViewModel.findWords(newText)
        }
        return true
    }

}


