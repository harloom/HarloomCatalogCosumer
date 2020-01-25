package com.harloomdeveloper.harloomcatalogcosumer.data.repository

import android.content.Context
import android.database.Cursor
import android.net.Uri

import com.harloomdeveloper.harloomcatalogcosumer.data.entity.EMovie
import com.harloomdeveloper.harloomcatalogcosumer.data.entity.ETv

class ProviderRepository(private val mContext: Context) {
    companion object{
        private const val AUTHORITY = "com.harloomDeveloper.moviecatalogharloom"
        private const val BASE_PATH = "favorite"
        private const val SCHEME = "content"
        const val MOVIE = 1
        const val TV =2


        const val ID ="id"
        const val TITLE = "title"
        const val POSTERPATH = "posterPath"

        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(BASE_PATH)
            .build()
    }
    fun getMovieFromProvider(): MutableList<EMovie> {
       val movie : MutableList<EMovie> = mutableListOf()
        val uriMovie = Uri.parse("$CONTENT_URI/$MOVIE")
        println("debug uriMOvie: $uriMovie" )
        val cursor : Cursor? = mContext.contentResolver.query(uriMovie,null,null,null,null)
        cursor?.let {cr->
                while (cr.moveToNext()){
                    val id = cr.getInt(cr.getColumnIndexOrThrow(ID))
                    val title = cr.getString(cr.getColumnIndexOrThrow(TITLE))
                    val poster = cr.getString(cr.getColumnIndexOrThrow(POSTERPATH))
                    val obj = EMovie(posterPath = poster,title = title,id = id)
                    println("debug : ${obj.toString()}")
                    movie.add(obj)
                }
        }
        cursor?.close()
        return movie
    }

    fun getTvFromProvider() : MutableList<ETv>{
        val tv : MutableList<ETv> = mutableListOf()
        val uriTv = Uri.parse("$CONTENT_URI/$TV")
        println("debug  uriTV: $uriTv")
        val cursor : Cursor? = mContext.contentResolver.query(uriTv,null,null,null,null)
        cursor?.let {cr->
            while (cr.moveToNext()){
                val id = cr.getInt(cr.getColumnIndexOrThrow(ID))
                val title = cr.getString(cr.getColumnIndexOrThrow(TITLE))
                val poster = cr.getString(cr.getColumnIndexOrThrow(POSTERPATH))
                val obj = ETv(posterPath = poster,title = title,id = id)
                println("debug Tv: ${obj.toString()}")
                tv.add(obj)
            }

        }

        cursor?.close()
        return  tv
    }
}