package repositories

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dyma_chap8.network.RetrofitClient
import com.example.dyma_chap8.network.dto.UserDto
import com.example.dyma_chap8.network.response.ApiResponse
import com.example.dyma_chap8.network.services.UserServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoRepository() {
    private val todoService = RetrofitClient.instance.create(UserServices::class.java)
    val todosLiveData = MutableLiveData<ApiResponse<List<UserDto>>>()

    fun fetchUsers() {
        val call = todoService.getAllUsers()

        call.enqueue(object : Callback<List<UserDto>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<UserDto>>, response: Response<List<UserDto>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let { users ->
                        val apiResponse = ApiResponse(listOf<UserDto>(), "Erreur client")
                        this@TodoRepository.todosLiveData.value = apiResponse
                    }
                    Log.d("Récupération succés", "Succès")
                } else {
                    Log.d("Récupération echec", "Echec")
                    val apiResponse = ApiResponse(listOf<UserDto>(), "Erreur client")
                    this@TodoRepository.todosLiveData.value = apiResponse
                }
            }

            override fun onFailure(call: Call<List<UserDto>>, t: Throwable) {
                Log.d("Erreur réseau", t.message ?: "Erreur")
                val apiResponse = ApiResponse(listOf<UserDto>(), "Erreur serveur")
                this@TodoRepository.todosLiveData.value = apiResponse
            }
        })
    }
}