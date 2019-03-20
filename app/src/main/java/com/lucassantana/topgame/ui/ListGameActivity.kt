package com.lucassantana.topgame.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.lucassantana.topgame.R
import com.lucassantana.topgame.bean.GameInfo
import com.lucassantana.topgame.bo.GameBO
import com.lucassantana.topgame.prefs.Prefs
import com.lucassantana.topgame.rest.RestCallback
import com.lucassantana.topgame.ui.adapter.ListGameAdapter
import kotlinx.android.synthetic.main.activity_list_game.*
import java.util.*


class ListGameActivity : AppCompatActivity(), RestCallback<ArrayList<GameInfo>?> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_game)

        Prefs.instance.init(this)

        requestInfos()
    }

    private fun requestInfos() {
        swipe_container.setOnRefreshListener {
            GameBO.instance.getListGames(this,this)
        }
        swipe_container.isRefreshing = true
        recyclerView.layoutManager = GridLayoutManager(this,2)
        GameBO.instance.getListGames(this,this)
    }


    override fun result(result: ArrayList<GameInfo>?) {
        swipe_container.isRefreshing = false
        var games : ArrayList<GameInfo>
        if (result != null){
            games = result
        }else{
            games = ArrayList<GameInfo>()

        }

        if (recyclerView.adapter == null){
            recyclerView.adapter = ListGameAdapter(games,this)
        }else{
            (recyclerView.adapter as ListGameAdapter).setGames(games)
        }
    }




}
