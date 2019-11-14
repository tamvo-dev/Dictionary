package com.example.dictionary.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.dictionary.R
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {

    companion object{
        val TAG = "HomeFragment"
    }

    lateinit var listView: ListView
    lateinit var searchView: SearchView
    lateinit var adapter: WordAdapter
    lateinit var progressBar: ProgressBar

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        listView = view.findViewById(R.id.home_listView)
        searchView = view.findViewById(R.id.home_searchView)
        progressBar = view.findViewById(R.id.home_progressBar)

        adapter = WordAdapter()
        listView.adapter = adapter

        homeViewModel.isLoading.observe(this, Observer {
            progressBar.visibility = it
        })

        homeViewModel.words.observe(this, Observer {
            adapter.addWords(it)
        })

        listView.setOnItemClickListener { parent, view, position, id ->
            val wordId = adapter.getItem(position).id
            val word = homeViewModel.selectWord(wordId)
        }

        searchView.setOnQueryTextListener(this)

        return view
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.run {
            homeViewModel.findWords(newText)
        }
        return true
    }

}


