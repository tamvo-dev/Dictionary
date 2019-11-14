package com.example.dictionary.ui.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.dictionary.R
import com.example.dictionary.models.Word

class ViewFragment : Fragment() {

    lateinit var viewPager: ViewPager
    lateinit var adapter: PraticeAdapter
    lateinit var vocabularys: MutableList<Word>
    lateinit var onChangeListener: ViewPager.OnPageChangeListener

    private var mCurrentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vocabularys = mutableListOf()
        vocabularys.add(Word(1,"school","<span class=\"title\">Chuyên ngành kinh tế</span><br /><ul><li>tỉ lệ tích sản-trái vụ</li></ul>"))
        vocabularys.add(Word(2,"student","school"))
        vocabularys.add(Word(3,"mother","school"))
        adapter = PraticeAdapter()
        adapter.addVocabularys(vocabularys)

        onChangeListener = object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                mCurrentPage = position
            }

        }

        val view = inflater.inflate(R.layout.fragment_pager, container, false)
        viewPager = view.findViewById(R.id.view_pager)
        viewPager.adapter = adapter
        viewPager.setCurrentItem(mCurrentPage)
        viewPager.addOnPageChangeListener(onChangeListener)

        return view
    }
}