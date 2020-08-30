package sn.chei.gadsleaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sn.chei.gadsleaderboard.data.LearningLeader
import sn.chei.gadsleaderboard.data.remote.LearningLeadersEndPoint
import sn.chei.gadsleaderboard.data.remote.OkHttpProvider

class LearningLeaders : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view  = inflater.inflate(R.layout.fragment_learning_leaders, container, false)
        var recyclerView: RecyclerView = view.findViewById(R.id.learning_leaders_recycler_view)

        var request = OkHttpProvider.buildService(LearningLeadersEndPoint::class.java)
        val call = request.getLearningLeaders()

        call.enqueue(object : Callback<List<LearningLeader>>{
            override fun onResponse(
                call: Call<List<LearningLeader>>,
                response: Response<List<LearningLeader>>
            ) {
                if(response.isSuccessful){
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = LearningLeadersAdapter(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<LearningLeader>>, t: Throwable) {

            }

        })



        return view
    }

}