package com.interview.mykotlinrecyclerview
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.interview.mykotlinrecyclerview.databinding.LayoutproductsBinding
class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var productList = mutableListOf<Product>()

    fun setMovies(movies: List<Product>) {
        this.productList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutproductsBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val product = productList[position]
        holder.binding.textViewId.text = product.id
        holder.binding.textViewname.text = product.Product

//        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}

class MainViewHolder(val binding: LayoutproductsBinding) : RecyclerView.ViewHolder(binding.root) {

}