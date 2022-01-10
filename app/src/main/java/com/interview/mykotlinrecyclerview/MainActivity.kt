package com.interview.mykotlinrecyclerview
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.feedbacktree.flow.core.startFlow
import com.feedbacktree.flow.ui.views.core.ViewRegistry
import com.interview.mykotlinrecyclerview.databinding.ActivityMainBinding
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.MainRepository
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.MyViewModelFactory
import io.reactivex.disposables.Disposable
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val adapter = MovieAdapter()
    lateinit var binding: ActivityMainBinding
    var disposable: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataload()
//        val viewRegistry = ViewRegistry( // 1
//            CounterLayoutBinder // 2
//        )
//        disposable = startFlow(flow = CounterFlow,
//            // 3
//            viewRegistry = viewRegistry)
    }

    private fun dataload() {
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter
        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)
        viewModel.productList.observe(this, {
            adapter.setMovies(it)

        })

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getAllproductList()
    }

    override fun onPause() {
        super.onPause()
        // 3
        if (isFinishing) {
            disposable?.dispose()
            disposable = null
        }
    }
}