package com.example.dictionary.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.dictionary.R


class DetailFragment: Fragment() {

    lateinit var webView: WebView

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        webView = view.findViewById(R.id.detail_web)

        detailViewModel = ViewModelProviders.of(activity!!).get(DetailViewModel::class.java)

        Log.e("TAG: Detail", detailViewModel.toString())

        detailViewModel.word.observe(this, Observer {
           
            webView.loadData(it.content, "text/html", "UTF-8")
        })

        return view
    }

}