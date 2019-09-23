package com.noorifytech.moviesapp.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noorifytech.moviesapp.R
import com.noorifytech.moviesapp.adapter.MoviesListRecyclerViewAdapter
import com.noorifytech.moviesapp.factory.MoviesListFactory
import com.noorifytech.moviesapp.viewmodel.MoviesListViewModel
import com.noorifytech.moviesapp.vo.MoviesVO
import com.noorifytech.moviesapp.vo.Resource
import com.noorifytech.moviesapp.vo.Status
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : AppCompatActivity() {
    private lateinit var viewModel: MoviesListViewModel
    private lateinit var moviesListAdapter: MoviesListRecyclerViewAdapter
    private var nextPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movies_list)

        init()

        initRecyclerView()
    }

    private fun init() {
        viewModel = MoviesListFactory.getViewModel(this)
        viewModel.getMoviesList(nextPage++).observe(this, updateView())
    }

    private fun initRecyclerView() {
        moviesListRV.layoutManager = LinearLayoutManager(this)
        moviesListAdapter = MoviesListRecyclerViewAdapter(this, emptyList())
        moviesListRV.adapter = moviesListAdapter

        moviesListRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                viewModel.getMoviesList(nextPage++).observe(this@MoviesListActivity, updateView())
            }
        })
    }

    private fun updateView(): Observer<Resource<List<MoviesVO>>> =
        Observer { resource ->
            when (resource.status) {
                Status.LOADING -> progressBar.visibility = View.VISIBLE
                Status.ERROR -> progressBar.visibility = View.GONE
                Status.SUCCESS -> progressBar.visibility = View.GONE
            }

            resource.data?.let {
                moviesListAdapter.moviesList = resource.data
                moviesListRV.adapter?.notifyDataSetChanged()
            }
        }
}