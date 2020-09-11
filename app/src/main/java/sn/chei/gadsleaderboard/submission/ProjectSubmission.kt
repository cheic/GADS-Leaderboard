package sn.chei.gadsleaderboard.submission


import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.submission_state_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sn.chei.gadsleaderboard.R
import sn.chei.gadsleaderboard.data.remote.OkHttpProvider
import sn.chei.gadsleaderboard.data.remote.SubmissionEndPoint


class ProjectSubmission : AppCompatActivity() {

    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var emailAddress: EditText
    private lateinit var githubLink: EditText
    private lateinit var submtButton: Button
    private lateinit var myProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_submission)
        val iconBack: ImageView = findViewById(R.id.icon_back)

        firstName = findViewById(R.id.first_name_edittext)
        lastName = findViewById(R.id.last_name_edittext)
        emailAddress = findViewById(R.id.email_address_edittext)
        githubLink = findViewById(R.id.github_link_name_edittext)
        submtButton = findViewById(R.id.submit_project_button)

        myProgressBar = findViewById(R.id.my_progressBar)
        myProgressBar.isIndeterminate = true

        iconBack.setOnClickListener { onBackPressed() }

        submtButton.setOnClickListener {
            if(!firstName.text.isNullOrBlank() && !lastName.text.isNullOrBlank() && !emailAddress.text.isNullOrBlank() && !githubLink.text.isNullOrBlank()){
                showConfirmationDialog()
            }else{
                Toast.makeText(this, "Check if fields are not empty", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun showConfirmationDialog() {
        val dialog = Dialog(this, R.style.MyDialogStyle)

        dialog.setContentView(R.layout.confirm_dialog_layout)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val cancel: ImageView = dialog.findViewById(R.id.dialog_cancel_icon)
        val confirm: Button = dialog.findViewById(R.id.confirm_dialog_yes_button)

        confirm.setOnClickListener {

            myProgressBar.visibility = View.VISIBLE

            val request =
                OkHttpProvider.buildServiceForProjectSubmission(SubmissionEndPoint::class.java)
            val call = request.submitProject(
                firstName.text.toString(),
                lastName.text.toString(),
                emailAddress.text.toString(),
                githubLink.text.toString()
            )

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    myProgressBar.visibility = View.GONE
                    showSuccessDialog()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    myProgressBar.visibility = View.GONE
                    showFailureDialog()
                }

            })
            dialog.dismiss()
        }
        cancel.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }

    private fun showSuccessDialog() {
        val dialog = Dialog(this, R.style.MyDialogStyle)
        dialog.setContentView(R.layout.submission_state_dialog)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val successIcon: ImageView = dialog.findViewById(R.id.submission_state_dialog_icon)
        val succesText: TextView = dialog.findViewById(R.id.submission_state_dialog_textview)
        Picasso.get().load(R.drawable.ic_check_circle_green_24dp)
            .placeholder(R.drawable.ic_check_circle_green_24dp).into(successIcon)
        succesText.text = getString(R.string.submission_successful_text)
        dialog.show()
    }

    private fun showFailureDialog() {
        val dialog = Dialog(this, R.style.MyDialogStyle)
        dialog.setContentView(R.layout.submission_state_dialog)
        dialog.setCanceledOnTouchOutside(true)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val failureIcon: ImageView = dialog.findViewById(R.id.submission_state_dialog_icon)
        val succesText: TextView = dialog.findViewById(R.id.submission_state_dialog_textview)
        Picasso.get().load(R.drawable.ic_warning_red_24dp)
            .placeholder(R.drawable.ic_warning_red_24dp).into(failureIcon)
        succesText.text = getString(R.string.submission_not_successful_text)
        dialog.show()
    }
}