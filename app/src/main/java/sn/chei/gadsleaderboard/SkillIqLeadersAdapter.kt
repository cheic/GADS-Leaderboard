package sn.chei.gadsleaderboard

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import sn.chei.gadsleaderboard.data.SkillIqLeader

class SkillIqLeadersAdapter(private val items: List<SkillIqLeader>): RecyclerView.Adapter<SkillIqLeadersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.txt_skill_iq_leader_name)
        private val scoreIqAndCountry: TextView = itemView.findViewById(R.id.txt_skill_iq_leader_score_and_country)
        private val imageIcon: ImageView = itemView.findViewById(R.id.item_skill_iq_leader_icon)

        fun bind(skillIqLeader: SkillIqLeader){
            val context: Context = name.context //getting the context from the view
            name.text = skillIqLeader.name
            scoreIqAndCountry.text = context.resources.getString(R.string.item_skill_iq_socre_and_coutry_string, skillIqLeader.score.toString(), skillIqLeader.country)
            Picasso.get().load(skillIqLeader.badgeUrl).into(imageIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_skill_iq_leader, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}