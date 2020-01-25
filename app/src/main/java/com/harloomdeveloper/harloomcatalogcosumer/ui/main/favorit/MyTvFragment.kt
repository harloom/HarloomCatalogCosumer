package com.harloomdeveloper.harloomcatalogcosumer.ui.main.favorit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


import androidx.recyclerview.widget.RecyclerView
import com.harloomdeveloper.harloomcatalogcosumer.adapter.RcvFavoritTvAdapter
import com.harloomdeveloper.harloomcatalogcosumer.data.entity.ETv
import com.harloomdeveloper.harloomcatalogcosumer.R
import kotlinx.android.synthetic.main.fragment_favorit.*


class MyTvFragment : Fragment(R.layout.fragment_favorit), RcvFavoritTvAdapter.Interaction {
    override fun onItemSelected(position: Int, item: ETv) {

    }


    private  var pageViewModel: PageViewModel? =null
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var movieAdapter: RcvFavoritTvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            pageViewModel =ViewModelProvider(activity!!).get(PageViewModel::class.java)
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        initVm()
        initRcv()
    }

    private fun initVm() {
        pageViewModel?.load()

    }
    private fun initRcv() {
        movieAdapter = RcvFavoritTvAdapter(this@MyTvFragment)
        mRecyclerView =  view!!.findViewById(R.id.rcv_f_movies)
        mRecyclerView.apply {
            adapter = movieAdapter
        }
        pageViewModel?.getTvs()?.observe(viewLifecycleOwner, Observer {tv->
                showLoading(false)
                 showIndicatorDataNull(tv.isEmpty())
                movieAdapter.submitList(tv)

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