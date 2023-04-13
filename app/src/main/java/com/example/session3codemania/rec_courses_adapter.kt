package com.example.session3codemania

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session3codemania.databinding.CourseItemBinding

class rec_courses_adapter(val list: List<CoursesData>, val tags:List<TagsData>): RecyclerView.Adapter<rec_courses_adapter.VH>() {
    class VH(binding: CourseItemBinding):RecyclerView.ViewHolder(binding.root) {
        val image = binding.imageView3
        val course = binding.textView2
        val recycler = binding.recCourseTags
        val cost = binding.priceCourse

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.cost.text = "₽${list[position].price}"
        holder.course.text = list[position].title
        holder.recycler.adapter = rec_course_tags(list[position].tags,tags)
    }
}