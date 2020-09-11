package sn.chei.gadsleaderboard.data.remote

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SubmissionEndPoint {

    @POST(SubmissionDetails.SUBMISSION_ID)
    @FormUrlEncoded
    fun submitProject(
        @Field(SubmissionDetails.NAME_ENTRY) firstName: String,
        @Field(SubmissionDetails.LAST_NAME_ENTRY) lastName: String,
        @Field(SubmissionDetails.EMAIL_ADDRESS_ENTRY) emailAddress: String,
        @Field(SubmissionDetails.PROJECT_LINK_ENTRY) githubUrl: String
    ):Call<Void>

}