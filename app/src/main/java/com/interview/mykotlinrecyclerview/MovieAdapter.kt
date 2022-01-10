package com.interview.mykotlinrecyclerview
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.feedbacktree.flow.core.bind
import com.feedbacktree.flow.core.startFlow
import com.feedbacktree.flow.ui.views.core.ViewRegistry
import com.interview.mykotlinrecyclerview.databinding.LayoutproductsBinding
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class MovieAdapter : RecyclerView.Adapter<MainViewHolder>() {

    var productList = mutableListOf<Product>()
    var disposable: Disposable? = null

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
        var context: Context? = null
        val product = productList[position]
        holder.binding.textViewId.text = product.id
        holder.binding.textViewname.text = product.Product
        holder.binding.add.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                val currentNos: Int = holder.binding.textViewId.text.toString().toInt() + 1
                System.out.println("INCREMENT"+currentNos)
//                quantity.setText(++currentNos.toString())
            }

        })
        holder.binding.minus.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
//                val currentNos: Int = quantity.getText().toString().toInt()
//                quantity.setText(--currentNos.toString())
            }

        })
//        bind { screen: Observable<CounterScreen> ->
//            // 4
//            subscriptions = listOf(
//                screen.map { it.counterText }.subscribe { holder.binding.counterTextView.text = it },
//                screen.map { it.isDecrementButtonInvisible }.subscribe { holder.binding.minus.isInvisible = it }
//            )
//            // 5
//            events = listOf(
//                holder.binding.add.clicks().map { Event.Increment },
//                holder.binding.minus.clicks().map { Event.Decrement }
//            )
//        }
//        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)




    }
    fun increment() {

    }

    fun decrement() {

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}

class MainViewHolder(val binding: LayoutproductsBinding) : RecyclerView.ViewHolder(binding.root) {

}