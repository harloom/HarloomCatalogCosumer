package com.harloomdeveloper.harloomcatalogcosumer.ui.main.favorit

import android.app.Application
import androidx.lifecycle.*
import com.harloomdeveloper.harloomcatalogcosumer.data.entity.EMovie
import com.harloomdeveloper.harloomcatalogcosumer.data.entity.ETv
import com.harloomdeveloper.harloomcatalogcosumer.data.repository.ProviderRepository
import kotlinx.coroutines.*

import kotlinx.coroutines.Dispatchers.IO

class PageViewModel(application: Application) : AndroidViewModel(application) {


    private val scope = CoroutineScope(IO);
    private val mContext = application.baseContext
    private val listMovie : MutableLiveData<List<EMovie>> = MutableLiveData()
    private val listTv : MutableLiveData<List<ETv>> = MutableLiveData()
    private val repository = ProviderRepository(mContext)


     fun loadMovie(){
       val mLIst =  repository.getMovieFromProvider()
         listMovie.value = mLIst


    }

    fun loadTv(){
        val tList = repository.getTvFromProvider()
        listTv.value = tList
    }

    fun getMovies() =listMovie
    fun getTvs() = listTv



}