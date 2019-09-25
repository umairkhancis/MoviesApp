package com.noorifytech.moviesapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.adapter.MoviesPagedListAdapter
import com.noorifytech.moviesapp.dao.db.entity.MovieEntity
import com.noorifytech.moviesapp.factory.MoviesListFactory
import com.noorifytech.moviesapp.viewmodel.MoviesListViewModel
import com.noorifytech.moviesapp.vo.MovieVO
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : AppCompatActivity() {

    private lateinit var moviesListAdapter: MoviesPagedListAdapter
    private val viewModel: MoviesListViewModel by lazy {
        MoviesListFactory.getViewModel(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_list)

        initRecyclerView()

        init()
    }

    private fun init() {
        viewModel.popularMovies.observe(this, Observer<PagedList<MovieEntity>> {
            println("MoviesListActivity.updateView:listSize: ${it.size}")
            moviesListAdapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        moviesListRV.layoutManager = LinearLayoutManager(this)
        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        moviesListRV.addItemDecoration(decoration)
        moviesListAdapter = MoviesPagedListAdapter(this)
        moviesListRV.adapter = moviesListAdapter
    }
}