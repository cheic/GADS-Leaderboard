package sn.chei.gadsleaderboard

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import sn.chei.gadsleaderboard.data.LearningLeader

class LearningLeadersAdapter(private val items: List<LearningLeader>): RecyclerView.Adapter<LearningLeadersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.txt_learning_leader_name)
        val hours_and_country: TextView = itemView.findViewById(R.id.txt_learning_leader_hours_and_country)
        val imageIcon: ImageView = itemView.findViewById(R.id.item_learning_leader_icon)

        fun bind(learningLeader: LearningLeader){
            name.text = learningLeader.name
            hours_and_country.text = ""+learningLeader.hours + " learning hours, " + learningLeader.country
            Picasso.get().load(learningLeader.badgeUrl).into(imageIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_learning_leader, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}