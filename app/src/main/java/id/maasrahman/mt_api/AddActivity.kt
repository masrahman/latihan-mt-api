package id.maasrahman.mt_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import id.maasrahman.mt_api.databinding.ActivityAddBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding

    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        title = "Input Product"

        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

        with(binding){
            btnSubmit.setOnClickListener {
                if(etProductName.text.toString().isEmpty()){
                    etProductName.error = "Wajib diisi"
                    return@setOnClickListener
                }
                if(etProductDesc.text.toString().isEmpty()){
                    etProductDesc.error = "Wajib diisi"
                    return@setOnClickListener
                }

                val prod = Product(
                    title = etProductName.text.toString(),
                    description = etProductDesc.text.toString()
                )

                apiInterface.addProduct(prod).enqueue(object: Callback<Product>{
                    override fun onResponse(call: Call<Product>, response: Response<Product>) {
                        Toast.makeText(baseContext, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                        finish()
                    }

                    override fun onFailure(call: Call<Product>, t: Throwable) {
                        Toast.makeText(baseContext, t.message.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
            }
        }

    }
}