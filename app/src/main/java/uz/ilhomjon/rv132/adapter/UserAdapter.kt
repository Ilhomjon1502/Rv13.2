package uz.ilhomjon.rv132.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.ilhomjon.rv132.databinding.ItemRvBinding
import uz.ilhomjon.rv132.models.User

class UserAdapter(val list:ArrayList<User>, val rvAction: RvAction):RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(val itemRv:ItemRvBinding):ViewHolder(itemRv.root){
        fun onBind(user: User, position:Int){
            itemRv.tvName.text = user.name
            itemRv.tvNumber.text = user.number
            itemRv.btnEdit.setOnClickListener {
                rvAction.editUser(position)
            }
            itemRv.btnDelete.setOnClickListener {
                rvAction.deleteUser(user, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    interface RvAction{
        fun deleteUser(user: User, position: Int)
        fun editUser(position: Int)
    }
}