package com.example.chess


import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.json.JSONObject

class UploadWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    companion object {
        const val TAG = "UploadWorker"
    }

    override fun doWork(): Result {
        // Do the work here--in this case, upload user interaction history.

        val json = JSONObject()
        json.accumulate("username", inputData.getString("username"))
        json.accumulate("date", inputData.getString("date"))
        json.accumulate("userID", inputData.getString("userID"))
        json.accumulate("event", inputData.getString("event"))
         // Log.d(MainActivity.TAG, "params:" + json.toString() + " url " + MainActivity.URL)
        return uploadLog(json, MainActivity.URL)
    }

    private fun uploadLog(json: JSONObject, url: String): Result {

        try {
            //  Log.d(MainActivity.TAG, "uploadLog() $url")
            val call = TrackerRetrofitService.create(url).postLog(json)
            val response = call.execute()

            // Handle the response here
            return if (response.isSuccessful) {
                Result.success()
            } else {
                Result.failure()
            }
        }
        catch (exc: java.lang.Exception)
        {
            Log.d("Inside Upload Worker: ", exc.message.toString())
        }

        return Result.failure()
    }
}