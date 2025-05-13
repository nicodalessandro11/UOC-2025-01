package com.uoc.pr1.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.uoc.pr1.R
import com.uoc.pr1.data.model.Seminary
import com.uoc.pr1.data.model.Item
import com.uoc.pr1.data.model.ItemType
import com.uoc.pr1.data.model.User
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class DataSourceFirebase :  DataSource {

    private val seminarItemList = mutableListOf<Item>()
    private val userSeminaryList = mutableListOf<Seminary>()


    private lateinit  var context: Context
    private var _user_id = 0

    constructor(context: Context?) : super() {
        seminarsLiveData = MutableLiveData(userSeminaryList)
        ItemsLiveData = MutableLiveData(seminarItemList)
        this.context =  context!!




    }



    override fun loginAsync(username: String, password: String,listener:ListenerData){

        lateinit var result:Result<User>



        val db = FirebaseFirestore.getInstance()




        db.collection("user")
            .whereEqualTo("user_username", username)
            .whereEqualTo("user_pwd", password)
            .get(Source.SERVER)
            .addOnSuccessListener { querySnapshot ->
                if(!querySnapshot.isEmpty()){
                    val doc_user = querySnapshot.documents.get(0)
                    val user_id = doc_user.data?.get("user_id") as Long
                    var user_display_name = doc_user.data?.get("user_display_name") as String
                    var user:User = User(user_id.toInt(),user_display_name)
                    result =  Result.Success(user)
                    _user_id = user_id.toInt()
                    listener.onLogin(result);
                }
                else{

                    _user_id = 0;
                    listener.onLogin(Result.Error(DataSourceException("User not exists")));
                }
            }
            .addOnFailureListener { exception ->
                _user_id = 0;
                listener.onLogin(Result.Error(DataSourceException("User not exists")));
                Log.w("Firestore", "Error getting documents $exception")
            }





    }

    override fun logout() {
        _user_id = 0
    }


    fun readSeminarsUserIdsAsync(listener:ListenerData){

        val result = mutableListOf<Long>()

        //BEGIN-CODE-UOC-3.1
        val db = FirebaseFirestore.getInstance()

        db.collection("user_seminar")
            .whereEqualTo("user_id", _user_id)
            .get(Source.SERVER)
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val sem_id = document.data?.get("sem_id") as Long
                    result.add(sem_id)
                }
                listener.onSeminarsUserIds(result)
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting seminar IDs: $exception")
                listener.onSeminarsUserIds(result)
            }
        //END-CODE-UOC-3.1
    }

    //**********************************************************************
    override fun selectSeminarsUserAsync(user_id:Int, listener:ListenerData) {
        userSeminaryList.clear()


        listener.onSeminarsUserIds = { list_ids ->
            if (!list_ids.isEmpty()){
            //BEGIN-CODE-UOC-3.2
                val db = FirebaseFirestore.getInstance()

                db.collection("seminar")
                    .whereIn("sem_id", list_ids)
                    .orderBy("sem_id", Query.Direction.ASCENDING)
                    .get(Source.SERVER)
                    .addOnSuccessListener { querySnapshot ->
                        for (document in querySnapshot.documents) {
                            val sem_id = (document.data?.get("sem_id") as Long).toInt()
                            val sem_name = document.data?.get("sem_name")?.toString() ?: ""
                            val sem_duration = (document.data?.get("sem_duration") as Long).toInt()
                            val sem_level = document.data?.get("sem_level")?.toString() ?: ""
                            val sem_image_url = document.data?.get("sem_image_url")?.toString() ?: ""

                            val seminar = Seminary(
                                id = sem_id,
                                name = sem_name,
                                duration = sem_duration,
                                level = sem_level,
                                image_path = sem_image_url
                            )

                            userSeminaryList.add(seminar)
                        }
                        seminarsLiveData.postValue(userSeminaryList)
                        listener.onSeminarsUser()
                    }
                    .addOnFailureListener { exception ->
                        Log.w("Firestore", "Error getting seminars: ", exception)
                        listener.onSeminarsUser()
                    }
            //END-CODE-UOC-3.2

            }
        }

        readSeminarsUserIdsAsync(listener)


    }

    // ************************************************************


    override fun selectItemsSeminary(id:Int,listener:ListenerData){
        seminarItemList.clear()


        //BEGIN-CODE-UOC-4.3
        val db = FirebaseFirestore.getInstance()

        db.collection("item")
            .whereEqualTo("item_sem_id", id)
            .orderBy("item_id")
            .get(Source.SERVER)
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    val item_id = (document.data?.get("item_id") as Long).toInt()
                    val item_type = (document.data?.get("item_type") as Long).toInt()
                    val item_question = document.data?.get("item_question") as String
                    val item_link = document.data?.get("item_link") as? String
                    val item_correct_answer = document.data?.get("item_correct_answer") as? Long
                    val item_answer1 = document.data?.get("item_answer1") as? String
                    val item_answer2 = document.data?.get("item_answer2") as? String
                    val item_answer3 = document.data?.get("item_answer3") as? String
                    val item_answer4 = document.data?.get("item_answer4") as? String

                    val item = Item(
                        id = item_id,
                        type = ItemType.values().first { it.v1 == item_type },
                        question = item_question,
                        link = item_link,
                        correct_answer = item_correct_answer,
                        answer1 = item_answer1,
                        answer2 = item_answer2,
                        answer3 = item_answer3,
                        answer4 = item_answer4
                    )
                    seminarItemList.add(item)
                }
                listener.onItemsSeminar()
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting items: ", exception)
                listener.onItemsSeminar()
            }
         //END-CODE-UOC-4.3

    }

    // ****************************************************

    fun ReloadViewModelSeminar(item:Seminary){
        val currentList = seminarsLiveData.value
        if (currentList == null) {
            seminarsLiveData.postValue(mutableListOf(item))
        } else {
            val updatedList = currentList.toMutableList()
           // updatedList.add(0, item)
            updatedList.add(item)

            seminarsLiveData.postValue(updatedList)
        }
    }

    fun ReloadViewModel(item:Item)
    {
        val currentList = ItemsLiveData.value
        if (currentList == null) {
            ItemsLiveData.postValue(mutableListOf(item))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, item)
            ItemsLiveData.postValue(updatedList)
        }

    }

    fun getNewRequestId(listener: ListenerData)
    {






    }

    fun getNewSeminarId(listener: ListenerData)
    {

        //BEGIN-CODE-UOC_5.1
        val db = FirebaseFirestore.getInstance()

        db.collection("seminar")
            .orderBy("sem_id", Query.Direction.DESCENDING)
            .limit(1)
            .get(Source.SERVER)
            .addOnSuccessListener { querySnapshot ->
                var new_id = 1
                if (!querySnapshot.isEmpty) {
                    val doc = querySnapshot.documents[0]
                    val last_id = (doc.data?.get("sem_id") as Long).toInt()
                    new_id = last_id + 1
                }
                listener.onNewSeminarId(new_id)
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting last seminar id: ", exception)
                listener.onNewSeminarId(1)
            }
        //END-CODE-UOC_5.1




    }

    override fun addSeminarAsync(title:String, url:String, sem_duration: Int, sem_level:String, listener:ListenerData) {

        listener.onNewSeminarId = { new_id ->

            val hashMap = hashMapOf<String, Any>(
                "sem_name" to title,
                "sem_id" to new_id,
                "sem_duration" to sem_duration,
                "sem_level" to sem_level,
                "sem_image_url" to url
            )

        //BEGIN-CODE-UOC-5.2
            val db = FirebaseFirestore.getInstance()

            db.collection("seminar")
                .document(new_id.toString())
                .set(hashMap)
                .addOnSuccessListener {
                    val db2 = FirebaseFirestore.getInstance()

                    val hashMap2 = hashMapOf<String, Any>(
                        "user_id" to _user_id,
                        "sem_id" to new_id
                    )

                    db2.collection("user_seminar")
                        .add(hashMap2)
                        .addOnSuccessListener {
                            val seminar = Seminary(
                                id = new_id,
                                name = title,
                                duration = sem_duration,
                                level = sem_level,
                                image_path = url
                            )

                            ReloadViewModelSeminar(seminar)
                            listener.onNewSeminar()
                        }
                        .addOnFailureListener { exception ->
                            Log.w("Firestore", "Error adding user seminar: ", exception)
                        }
                }
                .addOnFailureListener { exception ->
                    Log.w("Firestore", "Error adding seminar: ", exception)
                }
            //BEGIN-CODE-UOC-5.2

        }

        getNewSeminarId(listener)

    }


    override fun addItemAsync(title:String, description:String, uri: Uri?,listener:ListenerData) {

        listener.onNewRequestId = { new_id ->

            lateinit var inputStream:InputStream

            try {
                inputStream = this.context.getContentResolver().openInputStream(uri!!)!!

            }
            catch(e: Exception)
            {
                Log.d("error",e.message!!)

            }

            val bitmap = BitmapFactory.decodeStream(inputStream)



        }

        getNewRequestId(listener)



    }


}