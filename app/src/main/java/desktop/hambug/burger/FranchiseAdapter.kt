package desktop.hambug.burger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import desktop.hambug.databinding.ItemFranchiseBinding

class FranchiseAdapter(private val dataset: Franchise) : RecyclerView.Adapter<FranchiseAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemFranchiseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FranchiseItem) {
            with(binding) {
                nameTextView.text = data.shopName
                numTextView.text = data.shopNumber
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFranchiseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}