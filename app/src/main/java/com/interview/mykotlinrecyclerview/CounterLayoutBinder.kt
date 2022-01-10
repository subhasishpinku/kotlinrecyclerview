package com.interview.mykotlinrecyclerview
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import com.feedbacktree.flow.ui.views.LayoutBinder
import com.jakewharton.rxbinding3.view.clicks
import io.reactivex.Observable

// 1
val CounterLayoutBinder = LayoutBinder.create(
    layoutId = R.layout.layoutproducts,
    sink = CounterScreen::sink
) { view ->

    // 2
    val counterTextView = view.findViewById<TextView>(R.id.counterTextView)
    val incrementButton = view.findViewById<ImageView>(R.id.add)
    val decrementButton = view.findViewById<ImageView>(R.id.minus)

    // 3
    bind { screen: Observable<CounterScreen> ->
        // 4
        subscriptions = listOf(
            screen.map { it.counterText }.subscribe { counterTextView.text = it },
            screen.map { it.isDecrementButtonInvisible }.subscribe { decrementButton.isInvisible = it }
        )
        // 5
        events = listOf(
            incrementButton.clicks().map { Event.Increment },
            decrementButton.clicks().map { Event.Decrement }
        )
    }
}
