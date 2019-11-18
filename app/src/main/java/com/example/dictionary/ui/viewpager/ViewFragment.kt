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
        vocabularys.add(Word(1,"school","<span class=\"title\">school /sku:l/</span><br /><span class=\"type\">danh từ</span><ul><li>đàn cá, bầy cá<ul><li><span class=\"example\"><a href=\"entry://school\" class=\"aexample\">school</a> <a href=\"entry://fish\" class=\"aexample\">fish</a>:</span><span class=\"mexample\"> loại cá thường đi thành bầy</span></li></ul></li></ul><span class=\"type\">nội động từ</span><ul><li>hợp thành đàn, bơi thành bầy (cá...)</li></ul><span class=\"type\">danh từ</span><ul><li>trường học, học đường<ul><li><span class=\"example\"><a href=\"entry://normal\" class=\"aexample\">normal</a> <a href=\"entry://school\" class=\"aexample\">school</a>:</span><span class=\"mexample\"> trường sư phạm</span></li><li><span class=\"example\"><a href=\"entry://primary\" class=\"aexample\">primary</a> <a href=\"entry://school\" class=\"aexample\">school</a>:</span><span class=\"mexample\"> trường sơ cấp</span></li><li><span class=\"example\"><a href=\"entry://private\" class=\"aexample\">private</a> <a href=\"entry://school\" class=\"aexample\">school</a>:</span><span class=\"mexample\"> trường tư</span></li><li><span class=\"example\"><a href=\"entry://public\" class=\"aexample\">public</a> <a href=\"entry://school\" class=\"aexample\">school</a>:</span><span class=\"mexample\"> trường công</span></li><li><span class=\"example\"><a href=\"entry://secondary\" class=\"aexample\">secondary</a> <a href=\"entry://school\" class=\"aexample\">school</a>:</span><span class=\"mexample\"> trường trung học</span></li><li><span class=\"example\"><a href=\"entry://to\" class=\"aexample\">to</a> <a href=\"entry://keep\" class=\"aexample\">keep</a> <a href=\"entry://a\" class=\"aexample\">a</a> <a href=\"entry://school\" class=\"aexample\">school</a>:</span><span class=\"mexample\"> mở trường tư</span></li></ul></li><li>trường sở, phòng học<ul><li><span class=\"example\"><a href=\"entry://chemistry\" class=\"aexample\">chemistry</a> <a href=\"entry://school\" class=\"aexample\">school</a>:</span><span class=\"mexample\"> phòng dạy hoá học</span></li></ul></li><li>trường (toàn thể học sinh một trường)<ul><li><span class=\"example\"><a href=\"entry://the\" class=\"aexample\">the</a> <a href=\"entry://whole\" class=\"aexample\">whole</a> <a href=\"entry://school\" class=\"aexample\">school</a> <a href=\"entry://knows\" class=\"aexample\">knows</a> <a href=\"entry://it\" class=\"aexample\">it</a>:</span><span class=\"mexample\"> toàn trường biết việc đó</span></li></ul></li><li>(nghĩa bóng) trường, hiện trường<ul><li><span class=\"example\"><a href=\"entry://he\" class=\"aexample\">he</a> <a href=\"entry://learnt\" class=\"aexample\">learnt</a> <a href=\"entry://his\" class=\"aexample\">his</a> <a href=\"entry://generalship\" class=\"aexample\">generalship</a> <a href=\"entry://in\" class=\"aexample\">in</a> <a href=\"entry://a\" class=\"aexample\">a</a> <a href=\"entry://serve\" class=\"aexample\">serve</a> <a href=\"entry://school\" class=\"aexample\">school</a>:</span><span class=\"mexample\"> ông ta đã học tập nghệ thuật chỉ huy quân sự trong một hiện trường rất ác liệt</span></li></ul></li><li>giảng đường (thời Trung cổ)</li><li>buổi học, giờ học, giờ lên lớp; sự đi học<ul><li><span class=\"example\"><a href=\"entry://there\" class=\"aexample\">there</a> <a href=\"entry://will\" class=\"aexample\">will</a> <a href=\"entry://be\" class=\"aexample\">be</a> <a href=\"entry://no\" class=\"aexample\">no</a> <a href=\"entry://school\" class=\"aexample\">school</a> <a href=\"entry://today\" class=\"aexample\">today</a>:</span><span class=\"mexample\"> hôm nay không học</span></li></ul></li><li>trường phái<ul><li><span class=\"example\"><a href=\"entry://school\" class=\"aexample\">school</a> <a href=\"entry://of\" class=\"aexample\">of</a> <a href=\"entry://art\" class=\"aexample\">art</a>:</span><span class=\"mexample\"> trường phái nghệ thuật</span></li></ul></li><li>môn học<ul><li><span class=\"example\"><a href=\"entry://the\" class=\"aexample\">the</a> <a href=\"entry://history\" class=\"aexample\">history</a> <a href=\"entry://school\" class=\"aexample\">school</a>:</span><span class=\"mexample\"> môn sử học</span></li></ul></li><li>phòng thi (ở trường đại học); sự thi<ul><li><span class=\"example\"><a href=\"entry://to\" class=\"aexample\">to</a> <a href=\"entry://be\" class=\"aexample\">be</a> <a href=\"entry://in\" class=\"aexample\">in</a> <a href=\"entry://the\" class=\"aexample\">the</a> <a href=\"entry://schools\" class=\"aexample\">schools</a>:</span><span class=\"mexample\"> dự thi, đi thi</span></li></ul></li><li>môn đệ, môn sinh</li><li>(âm nhạc) sách dạy đàn</li></ul><span class=\"type\">Idioms</span><ul><li><a href=\"entry://a\" class=\"aidiom\">a</a> <a href=\"entry://gentleman\" class=\"aidiom\">gentleman</a> <a href=\"entry://of\" class=\"aidiom\">of</a> <a href=\"entry://the\" class=\"aidiom\">the</a> <a href=\"entry://old\" class=\"aidiom\">old</a> <a href=\"entry://school\" class=\"aidiom\">school</a><ul><li>một người quân tử theo kiểu cũ</li></ul><li><a href=\"entry://to\" class=\"aidiom\">to</a> <a href=\"entry://go\" class=\"aidiom\">go</a> <a href=\"entry://to\" class=\"aidiom\">to</a> <a href=\"entry://school\" class=\"aidiom\">school</a> <a href=\"entry://to\" class=\"aidiom\">to</a> <a href=\"entry://somebody\" class=\"aidiom\">somebody</a><ul><li>theo đòi ai, học hỏi ai</li></ul></ul><span class=\"type\">ngoại động từ</span><ul><li>cho đi học; dạy dỗ giáo dục</li><li>rèn luyện cho vào khuôn phép<ul><li><span class=\"example\"><a href=\"entry://to\" class=\"aexample\">to</a> <a href=\"entry://school\" class=\"aexample\">school</a> <a href=\"entry://one\"s\" class=\"aexample\">one\"s</a> <a href=\"entry://temper\" class=\"aexample\">temper</a>:</span><span class=\"mexample\"> rèn luyện tính tình</span></li><li><span class=\"example\"><a href=\"entry://to\" class=\"aexample\">to</a> <a href=\"entry://school\" class=\"aexample\">school</a> <a href=\"entry://onself\" class=\"aexample\">onself</a> <a href=\"entry://to\" class=\"aexample\">to</a> <a href=\"entry://patience\" class=\"aexample\">patience</a>:</span><span class=\"mexample\"> rèn luyện tính kiên nhẫn</span></li></ul></li></ul><span class=\"title\">Chuyên ngành kinh tế</span><br /><ul><li>bầy cá</li><li>đàn</li><li>học phái</li><li>trường phái</li><li>tụ tập thành bầy</li></ul><span class=\"title\">Chuyên ngành kỹ thuật</span><br /><span class=\"title\">Lĩnh vực: xây dựng</span><br /><ul><li>trưởng</li><li>trường dạy nghề</li><li>trường học</li></ul>"))
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