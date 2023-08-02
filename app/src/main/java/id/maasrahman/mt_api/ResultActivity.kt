package id.maasrahman.mt_api

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.maasrahman.mt_api.databinding.ActivityResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding

    var productModel: Product? = null

    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = "View Data"
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

        val intentData = intent.extras
        if(intentData != null){
            productModel = intentData.getParcelable("product") ?: Product()
            bindData()
        }
    }

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == RESULT_OK){
            val bundle = result.data
            productModel = bundle?.getParcelableExtra("product") ?: Product()
            bindData()
        }
    }

    fun bindData(){
        with(binding){
            txtProductName.text = productModel?.title
            txtProductDesc.text = productModel?.description
            txtProductPrice.text = "$${productModel?.price}"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.result_menu, menu)
        return true
    }

    private fun actionDelete(){
        apiInterface.deleteProduct(productModel?.id.toString()).enqueue(object : Callback<Product>{
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                Toast.makeText(baseContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    finish()
                }, 1500)
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                Toast.makeText(baseContext, t.message.toString(), Toast.LENGTH_SHORT).show();
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_update -> {

            }
            R.id.action_delete -> {
                AlertDialog.Builder(this@ResultActivity)
                    .setTitle("Konfirmasi")
                    .setMessage("Yakin menghapus data ${productModel?.title}?")
                    .setPositiveButton("Ya") { dialog, _ ->
                        actionDelete()
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}