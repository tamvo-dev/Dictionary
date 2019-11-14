package com.example.dictionary.ui.viewpager

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.dictionary.R
import com.example.dictionary.models.Word

class PraticeAdapter : PagerAdapter(){

    private val vocabularys = mutableListOf<Word>()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(container.context)
        val view = layoutInflater.inflate(R.layout.payger_item, container, false)

        //code...
        val word = view.findViewById<TextView>(R.id.pager_word)
        val content = view.findViewById<TextView>(R.id.pager_content)

        word.setText(vocabularys[position].word)
        content.setText(Html.fromHtml(vocabularys[position].content, Html.FROM_HTML_MODE_COMPACT))
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)

    }

    override fun getCount(): Int = vocabularys.size

    fun addVocabularys(vob: List<Word>){
        vocabularys.clear()
        vocabularys.addAll(vob)
    }

}