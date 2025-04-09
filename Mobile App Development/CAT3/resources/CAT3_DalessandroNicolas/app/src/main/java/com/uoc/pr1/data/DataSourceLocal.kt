package com.uoc.pr1.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.uoc.data.localstorage.DbHelper
import com.uoc.pr1.R
import com.uoc.pr1.data.model.*
import java.io.FileOutputStream
import java.io.InputStream


class DataSourceLocal:  DataSource {

    private lateinit var dbHelper: DbHelper
    private lateinit  var db: SQLiteDatabase
    private lateinit  var db_read: SQLiteDatabase

    private val seminarItemList = mutableListOf<Item>()

    private val userSeminarList = mutableListOf<Seminary>()

    private lateinit  var context: Context

    private var _user_id = 0

    constructor(context: Context?) : super() {
        seminarsLiveData = MutableLiveData(userSeminarList)
        ItemsLiveData = MutableLiveData(seminarItemList)

        dbHelper = DbHelper(context!!)
        db = dbHelper.writableDatabase
        db_read = dbHelper.readableDatabase

        this.context =  context
    }

    override fun login(username: String, password: String): Result<User>? {

        lateinit var result:Result<User>


        //BEGIN-CODE-UOC-3.1

        // 3.1.1. Comment Code
        // var user_harcoded:User  = User(1, "Jane Doe")
        // result =  Result.Success(user_harcoded)

        // 3.1.2. Use instead the following SQL string
        val str_sql = "SELECT * FROM user WHERE user_username = '$username' and  user_pwd='$password'"

        // 3.1.3. Verify the login
        val cursor = db_read.rawQuery(str_sql, null)
        if (cursor.moveToFirst()) {

            // If we find a user with this credentials
            val userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"))
            val displayName = cursor.getString(cursor.getColumnIndexOrThrow("user_display_name"))

            // We save the id of the user for the future
            _user_id = userId

            // We create an object User with the user data
            val user = User(userId, displayName)
            result = Result.Success(user)

        } else {
            // If the user is not in the database, we throw an error message
            result = Result.Error(Exception("User or password invalid."))
        }
        cursor.close()

        // END-CODE-UOC-3.1

        return result

    }

    override fun logout() {
        _user_id = 0
    }

    // ****************************************************************
    // Seminars
    override open fun selectSeminarssUser(user_id: Int) {
        userSeminarList.clear()
        // Load user's seminars
        //BEGIN-CODE-UOC-3.2
        val str_sql = "SELECT * FROM seminar,user_seminar WHERE  usersem_user_id= $user_id and usersem_seminar_id = sem_id"

        // 3.2.1
        val cursor = db_read.rawQuery(str_sql, null)
        with(cursor) {
            while (moveToNext()) {
                val sem_id = getInt(getColumnIndexOrThrow("sem_id"))
                val sem_name = getString(getColumnIndexOrThrow("sem_name"))
                val sem_duration = getInt(getColumnIndexOrThrow("sem_duration"))
                val sem_level = getString(getColumnIndexOrThrow("sem_level"))

                // We build the route of the image
                val image_path = context.filesDir.path + "/media/seminar/" + sem_id + ".jpg"

                // We create the object Seminary and we add it to the list
                val seminary = Seminary(sem_id, sem_name, sem_duration, sem_level, image_path, null, null)
                userSeminarList.add(seminary)
            }
        }
        cursor.close()

        // We update the seminar live data
        seminarsLiveData.postValue(userSeminarList)

        // END-CODE-UOC-3.2
    }

    // ****************************************************************
    // Items
    override fun selectItemsSeminary(id:Int){

        seminarItemList.clear()


        // Load seminar items
        //BEGIN-CODE-UOC-3.3

        val str_sql = "SELECT * FROM items WHERE item_sem_id= $id"

        //BEGIN-CODE-UOC-3.3

        // 3.3.1. Read database
        val cursor = db_read.rawQuery(str_sql, null)

        // 3.3.2 Process Results
        with(cursor) {
            while (moveToNext()) {

                // We create item object
                val item = Item(
                    id = getInt(getColumnIndexOrThrow("item_id")),
                    type = ItemType.entries.first { it.v1 == getInt(getColumnIndexOrThrow("item_type")) },
                    question = getString(getColumnIndexOrThrow("item_question")),
                    link = getString(getColumnIndexOrThrow("item_link")),
                    correct_answer = getInt(getColumnIndexOrThrow("item_correct_answer")),
                    answer1 = getString(getColumnIndexOrThrow("item_answer1")),
                    answer2 = getString(getColumnIndexOrThrow("item_answer2")),
                    answer3 = getString(getColumnIndexOrThrow("item_answer3")),
                    answer4 = getString(getColumnIndexOrThrow("item_answer4"))
                )
                // We add each question to the seminar list
                seminarItemList.add(item)
            }
        }
        cursor.close()

        // We Update the seminar live data
        ItemsLiveData.postValue(seminarItemList)

        // END-CODE-UOC-3.3

    }


    fun getNewId():Int
    {

        val str_sql = "SELECT max(sem_id)+1 as 'new_sem_id' FROM seminar"

        val cursor2 = db_read.rawQuery(
            str_sql
            , null)

        var new_sem_id = -1

        with(cursor2) {
            while (moveToNext()) {

                new_sem_id = getInt(getColumnIndexOrThrow("new_sem_id"))

            }

        }
        cursor2.close()

        return new_sem_id
    }

    // The method must force the seminar duration to be 60 and the sem level to be a 'beginner.'
    override fun addSeminary(name:String, uri:Uri?) {

        val new_id:Int = getNewId()

        var name_sql = name.replace("'","''");

        var str_sql = "INSERT INTO 'main'.'seminar' ('sem_id', 'sem_name', 'sem_duration', 'sem_level') VALUES ($new_id, '$name_sql', 60, '')"


        //BEGIN-CODE-UOC-6.6.1

        // 6.6.1 We add the new seminar to the table
        db.execSQL(str_sql)

        //END-CODE-UOC-6.6.1

        str_sql = "INSERT INTO 'main'.'user_seminar' ('usersem_user_id', 'usersem_seminar_id') VALUES ($_user_id, $new_id)"

        db.execSQL(str_sql)


        var image_path:String = ""

        //BEGIN-CODE-UOC-6.6.2

        // 6.6.2. If the URI is not null, we save the image selected by the user in the local storage
        if (uri != null) {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)
            if (bitmap != null) {
                val directory_path = this.context.filesDir.path + "/media/seminar/"
                image_path = directory_path + new_id + ".jpg"
                val outputStream = FileOutputStream(image_path)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                outputStream.close()
            }
        }

        //END-CODE-UOC-6.6.2

        val seminary:Seminary = Seminary(new_id,name_sql,60,"beginner" ,image_path, null, null)


        //BEGIN-CODE-UOC-6.6.3

        val currentList = seminarsLiveData.value
        if (currentList == null) {
            seminarsLiveData.postValue(mutableListOf(seminary))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, seminary)
            seminarsLiveData.postValue(updatedList)
        }

        /*
        This fragment is refreshing the user interface after adding a new seminary.
        It is first obtaining the current seminary list from the LiveData, and if the list is null
        (no list) it creates one with the seminary currently added. If exists, it first creates a
        mutable copy of it, then add the new seminar to this list at the beginning (position 0)
        and finally update the LiveData with this new list.
        When we tested commenting this code, we could see that even the new seminar is added to the
        database, the seminar is not being updated in the user interface until the user reload the
        view.
         */

        //END-CODE-UOC-6.6.3
    }

}