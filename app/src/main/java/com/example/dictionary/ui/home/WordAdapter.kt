package com.example.dictionary.ui.home

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.dictionary.R
import com.example.dictionary.models.Word

class WordAdapter : BaseAdapter() {

    private val words = mutableListOf<Word>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)

        val itemWord = view.findViewById<TextView>(R.id.item_word)
        val itemContent = view.findViewById<TextView>(R.id.item_content)

        itemWord.setText(words.get(position).word)
        // code...
        itemContent.setText(Html.fromHtml(words.get(position)?.content, Html.FROM_HTML_MODE_COMPACT))

        return view
    }

    override fun getItem(position: Int): Word = words.get(position)

    override fun getItemId(position: Int): Long = 0

    override fun getCount(): Int = words.size

    fun addWords(list: List<Word>){
        words.clear()
        words.addAll(list)
        notifyDataSetChanged()
    }
}