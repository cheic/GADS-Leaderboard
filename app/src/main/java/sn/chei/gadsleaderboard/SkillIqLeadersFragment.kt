package sn.chei.gadsleaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sn.chei.gadsleaderboard.data.SkillIqLeader
import sn.chei.gadsleaderboard.data.remote.OkHttpProvider
import sn.chei.gadsleaderboard.data.remote.SkillIdLeadersEndPoint

class SkillIqLeadersFragment : Fragment() {

    private lateinit var myProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_skill_iq_leaders, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.skill_iq_leaders_recycler_view)
        myProgressBar = view.findViewById(R.id.my_progressBar)
        myProgressBar.isIndeterminate = true
        myProgressBar.visibility = View.VISIBLE

        val request = OkHttpProvider.buildService(SkillIdLeadersEndPoint::class.java)
        val call = request.getSkillIqLeaders()

        call.enqueue(object : Callback<List<SkillIqLeader>> {
            override fun onResponse(
                call: Call<List<SkillIqLeader>>,
                response: Response<List<SkillIqLeader>>
            ) {
                if (response.isSuccessful) {
                    myProgressBar.visibility = View.GONE
                    recyclerView.layoutManager = LinearLayoutManager(activity)
                    recyclerView.adapter = SkillIqLeadersAdapter(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<SkillIqLeader>>, t: Throwable) {
                myProgressBar.visibility = View.GONE
                Toast.makeText(
                    activity?.applicationContext,
                    getString(R.string.somethieng_went_wrong_text),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        return view
    }
}