package com.harloomdeveloper.harloomcatalogcosumer.ui.main.favorit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.harloomdeveloper.harloomcatalogcosumer.adapter.RcvFavoritMovieAdapter
import com.harloomdeveloper.harloomcatalogcosumer.data.entity.EMovie


import com.harloomdeveloper.harloomcatalogcosumer.R
import kotlinx.android.synthetic.main.fragment_favorit.*

/**
 * A placeholder fragment containing a simple view.
 */
class MyMovieFragment : Fragment(R.layout.fragment_favorit), RcvFavoritMovieAdapter.Interaction {


    override fun onItemSelected(position: Int, item: EMovie) {

    }

    private  var pageViewModel: PageViewModel?= null
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var movieAdapter: RcvFavoritMovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        initVm()
        initRcv()
    }

    private fun initVm() {
        activity?.let {a->
            pageViewModel = ViewModelProvider(a).get(PageViewModel::class.java)
        }


    }
    private fun initRcv() {
        movieAdapter = RcvFavoritMovieAdapter(this@MyMovieFragment)
        mRecyclerView =  view!!.findViewById(R.id.rcv_f_movies)
        mRecyclerView.apply {
            adapter = movieAdapter
        }
        pageViewModel?.getMovies()?.observe(viewLifecycleOwner, Observer {movie->
            movie?.let {
                showLoading(false)
                showIndicatorDataNull(movie.isEmpty())
                movieAdapter.submitList(movie)

            }


        })



    }

    private  fun showIndicatorDataNull(bool : Boolean){
        if(bool){
            bacground_indicator.visibility = View.VISIBLE
        }else{
            bacground_indicator.visibility = View.GONE
        }
    }
    private fun showLoading(st : Boolean){
        if(st){
            loading_indicator.visibility = View.VISIBLE
        }else{
            loading_indicator.visibility = View.GONE
        }
    }



}