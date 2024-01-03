package desktop.hambug.burger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import desktop.hambug.databinding.ItemShopBinding

class ShopAdapter(private val dataset: Shop) : RecyclerView.Adapter<ShopAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemShopBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ShopItem) {
            with(binding) {
                nameTextView.text = data.shopName
                addressTextView.text = data.shopAddress
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}