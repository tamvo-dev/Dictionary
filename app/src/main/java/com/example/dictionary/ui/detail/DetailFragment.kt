package com.example.dictionary.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.dictionary.MainViewModel
import com.example.dictionary.R
import com.example.dictionary.models.Word
import com.example.dictionary.ui.home.HomeFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment: Fragment() {

    lateinit var textViewWord: TextView
    lateinit var textViewContent: TextView

    private val detailViewModel by viewModel<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        textViewContent = view.findViewById(R.id.detail_content)
        textViewWord = view.findViewById(R.id.detail_word)

        detailViewModel.word.observe(this, Observer {
            textViewWord.setText(it.word)
            textViewContent.setText(it.content)
        })

        return view
    }

}