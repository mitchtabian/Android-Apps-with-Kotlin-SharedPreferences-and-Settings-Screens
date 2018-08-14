package com.jwhh.notekeeper

import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class CourseRecyclerAdapter(val context: Context) : RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder>()
{
    private val layoutInflater = LayoutInflater.from(context)
    private val courses = DataManager.courses.values.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_course, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return DataManager.courses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = courses[position]
        holder.textCourse.text = course?.title
        holder.currentPosition = position
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textCourse: TextView = itemView.findViewById<TextView>(R.id.textCourse)
        var currentPosition = 0;

        init {
            itemView.setOnClickListener {v ->
                Snackbar.make(v, courses[currentPosition].title,
                        Snackbar.LENGTH_LONG).show()
            }
        }
    }
}