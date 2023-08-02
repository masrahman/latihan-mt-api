package id.maasrahman.mt_api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.maasrahman.mt_api.databinding.ItemProductBinding
import kotlin.properties.Delegates

class ProductAdapter(private val listener: (Product) -> Unit): RecyclerView.Adapter<ProductAdapter.BiodataHolder>() {

    private var listData: List<Product> by Delegates.observable(emptyList()){_, _, _ ->
        notifyDataSetChanged()
    }

    fun updateData(list: List<Product>){
        listData = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiodataHolder {
        val itemBind = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BiodataHolder(itemBind, listener)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: BiodataHolder, position: Int) {
        holder.bindData(listData[position])
    }

    inner class BiodataHolder(private val itemBind: ItemProductBinding,
                              private val listener: (Product) -> Unit)
        : RecyclerView.ViewHolder(itemBind.root){

        fun bindData(product: Product){
            with(itemBind){
                txtProductName.text = product.title
                txtProductDesc.text = product.description
                txtProductPrice.text = "$${product.price}"
                Picasso.get().load(product.thumbnail).into(imgProduct)
                cardItem.setOnClickListener {
                    listener(product)
                }
            }
        }

    }
}