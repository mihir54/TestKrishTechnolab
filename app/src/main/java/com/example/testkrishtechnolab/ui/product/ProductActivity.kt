package com.example.testkrishtechnolab.ui.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testkrishtechnolab.R
import com.example.testkrishtechnolab.databinding.ActivityProductBinding
import com.example.testkrishtechnolab.ui.SecondActivity
import com.example.testkrishtechnolab.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_product.*

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {

    private val productViewModel : ProductViewModel by viewModels()
    private var layoutManager: GridLayoutManager? = null
    private lateinit var productAdapter : ProductAdapter

    private val binding  by lazy {
        DataBindingUtil.setContentView<ActivityProductBinding>(this, R.layout.activity_product).apply {
            lifecycleOwner = this@ProductActivity
            viewModel = productViewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productViewModel.getProducts()

        layoutManager = GridLayoutManager(this, 2)

        productAdapter = ProductAdapter(layoutManager!!)
        binding.adapter = productAdapter
        rvProducts.layoutManager = layoutManager

        productViewModel.liveDataProducts.observe(this, Observer {
           productAdapter.submitList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_layout -> {
                if (layoutManager?.spanCount == 1) {
                    layoutManager?.spanCount = 2
                    item.title = "list"
                } else {
                    layoutManager?.spanCount = 1
                    item.title = "grid"
                }
                productAdapter.notifyItemRangeChanged(0, productAdapter.itemCount ?: 0)
            }
            R.id.logout -> {
//                productViewModel.logout()
                startActivity(Intent(this,SecondActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        //super.onBackPressed()
    }
}