package sn.chei.gadsleaderboard.data.remote

import retrofit2.Call
import retrofit2.http.GET
import sn.chei.gadsleaderboard.data.SkillIqLeader

interface SkillIdLeadersEndPoint {

    @GET(ApiUrl.SKILL_IQ_LEADERS)
    fun getSkillIqLeaders(): Call<List<SkillIqLeader>>
}