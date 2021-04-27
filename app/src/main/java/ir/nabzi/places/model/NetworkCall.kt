package ir.nabzi.places.model

import okhttp3.Headers
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class NetworkCall<ResultType> {
    suspend fun fetch(): Resource<ResultType> {
        return try {
            val response = createCall()
            if (response.isSuccessful) {
                onSuccess(response.body())
                Resource(Status.SUCCESS, response.body(),"success")

            } else{
                val errorMsg = response.errorBody()?.let {
                    JSONObject(it.charStream().readText()).getString("message")
                }

                Resource.error(errorMsg?:"" ,null, response.code())
            }

        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.error("", null, e.code())

        } catch (e: ConnectException) {
            e.printStackTrace()
            Resource.error("",null, ConnectException)

        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            Resource.error("",null, SocketTimeoutException)

        }catch (e : UnknownHostException){
            e.printStackTrace()
            Resource.error("",null, UnknownHostException)

        }catch (e : JSONException){
            e.printStackTrace()
            Resource.error("",null, JSONException)

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error("",null, Exception)
        }
    }

    abstract suspend fun createCall(): Response<ResultType>
    open suspend fun onSuccess(result: ResultType?){}
    companion object{
        final const val ConnectException=600
        final const val SocketTimeoutException=601
        final const val UnknownHostException=602
        final const val Exception=603
        final const val JSONException=604
    }
}