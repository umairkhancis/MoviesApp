package com.noorifytech.moviesapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.adapter.MoviesPagedListAdapter
import com.noorifytech.moviesapp.factory.MoviesListFactory
import com.noorifytech.moviesapp.viewmodel.MoviesListViewModel
import com.noorifytech.moviesapp.vo.MovieVO
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : AppCompatActivity() {
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var moviesListAdapter: MoviesPagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_list)

        init()

        initRecyclerView()
    }

    private fun init() {
        viewModel = MoviesListFactory.getViewModel(this)

        viewModel.getPopularMovies().observe(this, updateView())
    }

    private fun initRecyclerView() {
        moviesListRV.layoutManager = LinearLayoutManager(this)
        // add dividers between RecyclerView's row items
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        moviesListRV.addItemDecoration(decoration)
        moviesListAdapter = MoviesPagedListAdapter(this)
        moviesListRV.adapter = moviesListAdapter
    }

    private fun updateView(): Observer<PagedList<MovieVO>> =
        Observer { popularMovies ->
            println("MoviesListActivity.updateView:listSize: ${popularMovies.size}")
            moviesListAdapter.submitList(popularMovies)
        }
}