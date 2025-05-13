package com.uoc.fragments1.data

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.Source
import com.uoc.fragments1.data.model.Seminary
import com.uoc.fragments1.data.model.Item
import com.uoc.fragments1.data.model.ItemType
import com.uoc.fragments1.data.model.User
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

        /*
        val settings = FirebaseFirestoreSettings.Builder()

            .setReadTimeout(60 * 1000)    // 60 seconds
            .setWriteTimeout(60 * 1000)   // 60 seconds
            .build()

        // Apply settings to Firestore instance
        db.firestoreSettings = settings
*/

        /*
        db.collection("user").get(Source.SERVER)
        .addOnSuccessListener { documents ->
            for (document in documents) {
                println("Document ID: ${document.id} => Data: ${document.data}")
            }
        }
        .addOnFailureListener { e ->
            Log.w("Firestore", "Error getting documents $e")
        }
*/


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
            .whereEqualTo("usersem_user_id", _user_id)
            .get()
            .addOnSuccessListener { querySnapshot ->

                querySnapshot.forEach { document ->
                    val dis_id = document.data?.get("usersem_seminar_id") as Long
                    result.add(dis_id)
                }

                listener.onSeminarsUserIds(result)

            }
            .addOnFailureListener { exception ->
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
                    .orderBy("sem_id")
                    .get()
                    .addOnSuccessListener { querySnapshot ->

                        querySnapshot.forEach { document ->
                            val sem_id = document.data?.get("sem_id") as Long

                            val sem_name = document.data?.get("sem_name") as String

                            val sem_duration = document.data?.get("sem_duration") as Long

                            val sem_level = document.data?.get("sem_level") as String

                            var image_path:String = document.data?.get("sem_image_url") as String

                            userSeminaryList.add(Seminary(sem_id.toInt(),sem_name,sem_duration.toInt(),sem_level,image_path))

                        }




                        listener.onSeminarsUser()

                    }
                    .addOnFailureListener { exception ->
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
            .whereEqualTo("item_sem_id",id )
            .orderBy("item_id")
            .get()
            .addOnSuccessListener { querySnapshot ->

                querySnapshot.forEach { document ->

                    val item_type = document.data?.get("item_type") as Long
                    val item_id = document.data?.get("item_id") as Long
                    val item_question = document.data?.get("item_question") as String

                    val item_link = document.data?.get("item_link") as String

                    val map = ItemType.values().associateBy(ItemType::v1)
                    var type: ItemType? = map[item_type.toInt()]

                    val item_correct_answer = document.data?.get("item_correct_answer") as Long

                    val item_answer1 = document.data?.get("item_answer1") as String
                    val item_answer2 = document.data?.get("item_answer2") as String
                    val item_answer3 = document.data?.get("item_answer3") as String
                    val item_answer4 = document.data?.get("item_answer4") as String



                    seminarItemList.add(Item(type!!,item_id.toInt(),item_question,item_link,item_correct_answer,item_answer1,item_answer2,item_answer3,item_answer4))

                }

                listener.onItemsSeminar()

            }
            .addOnFailureListener { exception ->
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

        //BEGIN-CODE-UOC_7.1
        val db = FirebaseFirestore.getInstance()
        db.collection("request")
            .orderBy("request_id", Query.Direction.DESCENDING).limit(1)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val request = querySnapshot.documents.get(0)
                var request_id = request.data?.get("request_id") as Long
                request_id++
                listener.onNewRequestId(request_id.toInt())

            }
            .addOnFailureListener { exception ->
                listener.onNewRequestId(-1)
            }



        //END-CODE-UOC_7.1




    }

    fun getNewSeminarId(listener: ListenerData)
    {

        //BEGIN-CODE-UOC_7.1
        val db = FirebaseFirestore.getInstance()
        db.collection("seminar")
            .orderBy("sem_id", Query.Direction.DESCENDING).limit(1)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val seminar = querySnapshot.documents.get(0)
                var sem_id = seminar.data?.get("sem_id") as Long
                sem_id++
                listener.onNewSeminarId(sem_id.toInt())

            }
            .addOnFailureListener { exception ->
                listener.onNewSeminarId(-1)
            }



        //END-CODE-UOC_7.1




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

            val db = FirebaseFirestore.getInstance()
            db.collection("seminar")
                .add(hashMap)
                .addOnSuccessListener {
                    Log.d("Firestore", "Added story with ID ${it.id}")


                    val item:Seminary = Seminary(new_id,title,sem_duration,sem_level,url)


                    val hashMap2 = hashMapOf<String, Any>(
                        "usersem_user_id" to _user_id,
                        "usersem_seminar_id" to new_id,
                    )
                    db.collection("user_seminar")
                        .add(hashMap2)
                        .addOnSuccessListener {

                            ReloadViewModelSeminar(item)
                            listener.onNewSeminar();

                        } .addOnFailureListener { exception ->
                            Log.w("Firestore", "Error adding story $exception")

                        }
                }
                .addOnFailureListener { exception ->
                    Log.w("Firestore", "Error adding story $exception")

                }
        }

        getNewSeminarId(listener)

    }

    // new image https://www.revolumedia.com/uoc/img/questions1.jpg
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

            /*
            val storage = FirebaseStorage.getInstance()
            val storageRef = storage.reference

            val newImageRef = storageRef.child(new_id.toString() + ".jpg")


            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            val uploadTask = newImageRef.putBytes(data)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->

                val durl = newImageRef.toString()
                // BEGIN-CODE-UOC-7.3

                val hashMap = hashMapOf<String, Any>(
                    "request_title" to title,
                    "request_description" to description,
                    "request_user_id" to _user_id,
                    "request_id" to new_id,
                    "request_type" to 2,
                    "request_imageref" to durl
                )

                val db = FirebaseFirestore.getInstance()
                db.collection("request")
                    .add(hashMap)
                    .addOnSuccessListener {
                        Log.d("Firestore", "Added story with ID ${it.id}")


                        var _type: ItemType = ItemType.BASIC
                        if(uri!=null){
                            _type = ItemType.IMAGE
                        }

                        val item:Item = Item(_type,new_id,title,description,durl)

                        ReloadViewModel(item)
                    }
                    .addOnFailureListener { exception ->
                        Log.w("Firestore", "Error adding story $exception")

                    }




                // END-CODE-UOC-7.3



            }

             */

        }

        getNewRequestId(listener)



    }


}