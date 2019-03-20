package com.lucassantana.topgame.bo


import android.content.Context
import android.content.SharedPreferences
import android.os.AsyncTask
import android.util.JsonReader
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.lucassantana.topgame.bean.Box
import com.lucassantana.topgame.bean.Game
import com.lucassantana.topgame.bean.GameInfo
import com.lucassantana.topgame.bean.Logo
import com.lucassantana.topgame.rest.*
import org.json.JSONObject
import java.net.URL
import com.google.gson.GsonBuilder
import com.lucassantana.topgame.R
import com.lucassantana.topgame.prefs.Prefs
import org.json.JSONArray


class GameBO : RestService {


    private val KEY_GAMES = "KEY_GAMES"

    companion object {
        private var mInstance: GameBO? = null

        val instance: GameBO
            get() {
                if (mInstance == null) {
                    mInstance = GameBO()
                }
                return mInstance as GameBO
            }
    }

    private var arrGames: ArrayList<GameInfo>? = null

    private var gson: Gson = GsonBuilder().create()

    fun getListGames(context: Context, callback: RestCallback<ArrayList<GameInfo>?>) {
        try {

            val queue = Volley.newRequestQueue(context)
            val url = "https://api.twitch.tv/kraken/games/top"

            val stringRequest = StringRequest(Request.Method.GET, url,
                    Response.Listener<String> { response ->
                        val jsonObj = JSONObject(
                                response
                                        .substring(response.indexOf("{"),
                                                response.lastIndexOf("}") + 1))
                        val topJson = jsonObj.getJSONArray("top")
                        arrGames = convertArrayJsonInObject(topJson)

                        saveInCache(arrGames!!)
                        callback.result(arrGames)
                    },
                    Response.ErrorListener {
                        Toast.makeText(context,
                                context.getString(R.string.erro_request),
                                Toast.LENGTH_SHORT).show()
                        callback.result(getInCache())
                    })

            queue.add(stringRequest)
        } catch (e: Exception) {
            callback.result(null)
        }
    }

    fun getDetailFromID(id: Int): GameInfo? {
        if (arrGames == null) arrGames = getInCache()
        if (arrGames == null || arrGames?.size == 0) {
            return null
        }
        for (g: GameInfo in arrGames!!) {
            if (g.game._id == id) {
                return g
            }
        }
        return null
    }


    private fun convertJsonInGameInfo(jsonObject: JSONObject): GameInfo? {
        try {
            return gson.fromJson<GameInfo>(jsonObject.toString(), GameInfo::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun saveInCache(arr: ArrayList<GameInfo>) {
        arrGames = arr
        Prefs.instance.set(KEY_GAMES, gson.toJson(arr))
    }

    private fun getInCache(): ArrayList<GameInfo>? {
        if (arrGames == null) {

            val jsonArray = JSONArray(Prefs.instance.get(KEY_GAMES))
            if (jsonArray != null) {
                arrGames = convertArrayJsonInObject(jsonArray)
            }
        }

        return arrGames
    }

    fun convertArrayJsonInObject(json: JSONArray): ArrayList<GameInfo> {
        val games = ArrayList<GameInfo>()
        for (i in 0..json!!.length() - 1) {
            val game = convertJsonInGameInfo(json.getJSONObject(i))
            if (game != null) {
                games.add(game)
            }
        }
        return games
    }

}