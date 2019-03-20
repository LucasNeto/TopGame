package com.lucassantana.topgame.ui.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucassantana.topgame.R
import com.lucassantana.topgame.bean.GameInfo
import android.widget.TextView
import com.android.volley.toolbox.NetworkImageView
import java.util.ArrayList
import com.lucassantana.topgame.rest.Volley.VolleySingleton
import com.lucassantana.topgame.ui.DetailGameActivity


/**
 * Created by capgemini on 16/03/2019.
 */


class ListGameAdapter(private var games: ArrayList<GameInfo>,
                      private var context: Context?) : RecyclerView.Adapter<ListGameAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        games[position].let {
            holder?.bind(it)

        }
        holder?.itemView?.setOnClickListener {
            var i = Intent(context, DetailGameActivity::class.java)
            i.putExtra(DetailGameActivity().PARAMETER_EXTRA,games[position].game._id)
            context?.startActivity(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.recycleview_row_game, parent, false))
    }

    override fun getItemCount(): Int {
        return games.size
    }

    fun setGames(games: ArrayList<GameInfo>) {
        this.games = games
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var imageGame: NetworkImageView? = null
        private var tvGameName: TextView? = null

        private var gameInfo: GameInfo? = null

        init {
            imageGame = view.findViewById(R.id.imageview_game)
            tvGameName = view.findViewById(R.id.tv_game_name)
        }

        fun bind(game: GameInfo) {
            gameInfo = game
            try {
                tvGameName?.setText(gameInfo!!.game.name)
                //Imagem NotFound
//                imageGame?.setImageUrl("https://www.unesale.com/ProductImages/Large/notfound.png",
//                        VolleySingleton.instance.imageLoader)
                imageGame?.setImageUrl(gameInfo!!.game.box.large,
                        VolleySingleton.instance.imageLoader)

            } catch (e: Exception) {
                e.printStackTrace()
            }


        }
    }


}