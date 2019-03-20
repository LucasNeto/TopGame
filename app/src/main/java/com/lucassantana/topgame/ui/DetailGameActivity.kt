package com.lucassantana.topgame.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lucassantana.topgame.R
import com.lucassantana.topgame.bean.GameInfo
import com.lucassantana.topgame.bo.GameBO
import com.lucassantana.topgame.rest.Volley.VolleySingleton
import kotlinx.android.synthetic.main.activity_detail_game.*

import kotlinx.android.synthetic.main.recycleview_row_game.*

class DetailGameActivity : AppCompatActivity() {


    val PARAMETER_EXTRA = "idGameDetail"
    var gameSelected : GameInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_game)
    }

    override fun onResume() {
        super.onResume()

        getDetail()
    }
    private fun getDetail(){

        val _id = intent.getIntExtra(PARAMETER_EXTRA,0)
        if (_id != 0){
            gameSelected = GameBO.instance.getDetailFromID(_id)
            layoutGame(gameSelected)
        }

    }

    private fun layoutGame(game: GameInfo?) {

        if (game == null ) { return }


        image_box_game.setImageUrl(game!!.game.box.large,
                VolleySingleton.instance.imageLoader)
        image_logo_game.setImageUrl(game!!.game.logo.large,
                VolleySingleton.instance.imageLoader)


        tv_mainname_game?.setText(game.game.name)
        tv_localname_game?.setText(game.game.localized_name)


        tv_views_game?.setText(game.viewers.toString())
        tv_channel_game?.setText(game.channels.toString())
        tv_people_game?.setText(game.game.popularity.toString())

    }
}
