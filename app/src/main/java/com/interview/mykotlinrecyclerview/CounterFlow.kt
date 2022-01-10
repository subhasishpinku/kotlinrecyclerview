package com.interview.mykotlinrecyclerview
import com.feedbacktree.flow.core.Flow
import com.feedbacktree.flow.core.advance
val CounterFlow = Flow<Unit, State, Event, Nothing, CounterScreen>( // 1
    initialState = { State(counter = 0) }, // 2
    stepper = { state, event -> // 3
        when (event) {
            Event.Increment -> state.copy(
                counter = state.counter + 1
            ).advance()
            Event.Decrement -> state.copy(
                counter = kotlin.math.max(0, state.counter - 1) // prevents the state going negative
            ).advance()
        }
    },
    feedbacks = listOf(), // 4
    render = { state, context -> // 5
        CounterScreen(state, context.sink)
    }
)

data class State(
    val counter: Int
)

sealed class Event {
    object Increment : Event()
    object Decrement : Event()
}

data class CounterScreen(
    private val state: State,
    val sink: (Event) -> Unit // 6
) {
    val counterText: String = state.counter.toString() // 7
    val isDecrementButtonInvisible: Boolean = state.counter == 0
}