package com.example.session3codemania

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.session3codemania.databinding.TagsItemBinding

class rec_course_tags(val list: List<Int>,val tags : List<TagsData>): RecyclerView.Adapter<rec_course_tags.CourseTagHolder>() {
    class CourseTagHolder(binding: TagsItemBinding):RecyclerView.ViewHolder(binding.root) {
        val tag = binding.textView3
        val cardView = binding.background

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseTagHolder {
        return CourseTagHolder(TagsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CourseTagHolder, position: Int) {
        val cur = list[position]
        for (i in tags){
            if(i.id == cur){
                holder.tag.text = i.name
                break
            }
        }
    }
}