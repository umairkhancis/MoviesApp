package com.noorifytech.moviesapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.data.repository.vo.MovieVO
import com.noorifytech.moviesapp.ui.adapter.MoviesPagedListAdapter
import com.noorifytech.moviesapp.ui.viewmodel.MoviesListViewModel
import com.noorifytech.moviesapp.ui.viewmodel.factory.MoviesListViewModelFactory
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : AppCompatActivity() {

    private lateinit var moviesListAdapter: MoviesPagedListAdapter
    private lateinit var viewModelFactory: MoviesListViewModelFactory
    private lateinit var viewModel: MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_list)

        viewModelFactory = MoviesListViewModelFactory.create()
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MoviesListViewModel::class.java)

        initRecyclerView()

        init()
    }

    private fun init() {
        viewModel.getPopularMoviesLiveData()
            .observe(this, Observer<PagedList<MovieVO>> {
            moviesListAdapter.submitList(it)
        })
    }

    private fun initRecyclerView() {
        moviesListRV.layoutManager = LinearLayoutManager(this)
        moviesListRV.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        moviesListAdapter = MoviesPagedListAdapter(this)
        moviesListRV.adapter = moviesListAdapter
    }
}