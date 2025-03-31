package com.uoc.data.localstorage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.uoc.pr1.R
import java.io.File
import java.io.FileOutputStream


class DbHelper(context: Context) : SQLiteOpenHelper(context, "cat2", null, 1) {

    val _context = context

    override fun onCreate(db: SQLiteDatabase) {

        try {

            //BEGIN-CODE-UOC-1.1

            // 1.1.1. Create the user table
            db.execSQL(SQL_CREATE_user)

            // 1.1.2. Create the seminar table
            db.execSQL(SQL_CREATE_seminar)

            // 1.1.3. Modify the SQL_CREATE_item
            val SQL_CREATE_item_modified = """
                 CREATE TABLE "items" (
                    "item_sem_id" INTEGER,
                    "item_id"	INTEGER,
                    "item_type"	INTEGER,
                    "item_question"	TEXT,
                    "item_link" TEXT,
                    "item_correct_answer" INTEGER,
                    "item_answer1" TEXT,
                    "item_answer2" TEXT,
                    "item_answer3" TEXT,
                    "item_answer4" TEXT,
                    PRIMARY KEY("item_id")
                );
                 """

            // 1.1.4. Create the Item table using the previously modified SQL_CREATE_item
            db.execSQL(SQL_CREATE_item_modified)

            // 1.1.5. Create the user-seminar mapping table
            db.execSQL(SQL_CREATE_user_seminar)

            //END-CODE-UOC-1.1


            val command_list = listOf(

                "INSERT INTO 'main'.'user' ('user_id', 'user_username', 'user_pwd', 'user_display_name') VALUES ('1', 'user1@uoc.com', '123456', 'Jane Doe')",
                "INSERT INTO 'main'.'user' ('user_id', 'user_username', 'user_pwd', 'user_display_name') VALUES ('2', 'user2@uoc.com', '123456', 'John Doe')",
                "INSERT INTO 'main'.'seminar' ('sem_id', 'sem_name', 'sem_duration', 'sem_level') VALUES ('1', 'Introduction to mobile devices','60','beginner')",
                "INSERT INTO 'main'.'seminar' ('sem_id', 'sem_name', 'sem_duration', 'sem_level') VALUES ('2', 'Mobile programming environments','40','intermediate')",


                "INSERT INTO 'main'.'user_seminar' ('usersem_user_id', 'usersem_seminar_id') VALUES ('1', '1')",
                "INSERT INTO 'main'.'user_seminar' ('usersem_user_id', 'usersem_seminar_id') VALUES ('1', '2')",
                "INSERT INTO 'main'.'user_seminar' ('usersem_user_id', 'usersem_seminar_id') VALUES ('2', '1')",
                "INSERT INTO 'main'.'user_seminar' ('usersem_user_id', 'usersem_seminar_id') VALUES ('2', '2')",

                "INSERT INTO 'main'.'items' ('item_sem_id','item_id', 'item_type', 'item_question', 'item_link', 'item_correct_answer' , 'item_answer1', 'item_answer2', 'item_answer3', 'item_answer4') " +
                        "VALUES (1, 1, 1, 'Select the incorrect feature of mobile devices', '', 1, 'They are bulky devices', 'They have permanent or intermittent network connectivity','They have processing capabilities','They offer high levels of interaction through screen, keyboard, vibrations, or sounds')",

                "INSERT INTO 'main'.'items' ('item_sem_id','item_id', 'item_type', 'item_question', 'item_link', 'item_correct_answer' , 'item_answer1', 'item_answer2', 'item_answer3', 'item_answer4') " +
                        "VALUES (1, 2, 1, 'A wireless device is one that can communicate...', '', 2, 'via fiber optic cable', 'via a wireless network','via ethernet cables','None of the above is correct')",

                "INSERT INTO 'main'.'items' ('item_sem_id','item_id', 'item_type', 'item_question', 'item_link', 'item_correct_answer' , 'item_answer1', 'item_answer2', 'item_answer3', 'item_answer4') " +
                        "VALUES (1, 3, 1, 'Which category of device is not considered mobile?','https://mobile-app-dev-uoc.github.io', 4, 'smartphone', 'tablet','wearable device','laptop computer')",


                "INSERT INTO 'main'.'items' ('item_sem_id','item_id', 'item_type', 'item_question', 'item_link', 'item_correct_answer' , 'item_answer1', 'item_answer2', 'item_answer3', 'item_answer4') " +
                        "VALUES (2, 4, 1, 'Which operating system does Samsung use for some of its wearable devices?', 'https://mobile-app-dev-uoc.github.io', 3, 'MacOS', 'Windows','Tizen','Linux')",

                "INSERT INTO 'main'.'items' ('item_sem_id','item_id', 'item_type', 'item_question', 'item_link', 'item_correct_answer' , 'item_answer1', 'item_answer2', 'item_answer3', 'item_answer4') " +
                        "VALUES (2, 5, 1, 'What is the latest available version of the Android operating system?', '', 2, '10', '16','13','15')",

                "INSERT INTO 'main'.'items' ('item_sem_id','item_id', 'item_type', 'item_question', 'item_link', 'item_correct_answer' , 'item_answer1', 'item_answer2', 'item_answer3', 'item_answer4') " +
                        "VALUES (2, 6, 1, 'A web application is...', '', 1, 'a website specifically optimized for a mobile device', 'an application installed on a mobile device with access to hardware','Not used for anything in communications','The transmission of data via Ethernet')",

                )

            //BEGIN-CODE-UOC-1.2

            // 1. Upload the initial data from the tables.
            for (command in command_list) {
                db.execSQL(command)
            }

            //END-CODE-UOC-1.2


            //BEGIN-CODE-UOC-2
            /*
             a) This snippet is creating a directory structure in the internal storage of the app
                and then moving resources into it. It first creates the directory "media" and then a
                subfolder "seminar" within media. It then continues with the MoveResources function
                that copy 2 images from the resources R.drawable to this created folder saved as
                1.jpg and 2.jpg. This allows the images to be persistent and accessible by file path.

             b) As we already explain in exercise a), the folder structure created is:
                [app files internal storage directory]/media/seminar/1.jpg
                [app files internal storage directory]/media/seminar/2.jpg

             c) Checking the MoveResources function, we can see that the images ar stored in JPEG format
                with a quality setting of 90, keeping the quality of the image while reducing a bit the size.
            */

            var directory_path:String = _context.filesDir.path + "/media/"
            var f = File(directory_path,"")
            f.mkdir()

            directory_path = _context.filesDir.path + "/media/seminar/"
            f = File(directory_path,"")
            f.mkdir()

            MoveResources(directory_path+"1.jpg",R.drawable.introduction_mobile)
            MoveResources(directory_path+"2.jpg",R.drawable.mobile_environment)

            //END-CODE-UOC-2


        }
        catch(err:Exception){
            Log.d("error",err.message!!)
        }
    }

    fun MoveResources(filename:String,resource_id:Int){

        val bmp = BitmapFactory.decodeResource(_context.getResources(), resource_id)
        val out = FileOutputStream(filename)
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, out)

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    private val SQL_CREATE_user = """
CREATE TABLE "user" (
	"user_id"	INTEGER,
	"user_username"	TEXT,
	"user_pwd"	TEXT,
	"user_display_name"	TEXT,
	PRIMARY KEY("user_id")
);
                """

    private val SQL_CREATE_seminar = """
CREATE TABLE "seminar" (
	"sem_id"	INTEGER,
	"sem_name"	TEXT,   
    "sem_duration" INTEGER,
    "sem_level" TEXT, 
	PRIMARY KEY("sem_id")
);
        """

    private val SQL_CREATE_item = """
 CREATE TABLE "items" (
    "item_sem_id" INTEGER,
	"item_id"	INTEGER,
	"item_type"	INTEGER,
	"item_question"	TEXT,
    "item_link" TEXT,
	PRIMARY KEY("item_id")
);
 """

private val SQL_CREATE_user_seminar = """
CREATE TABLE "user_seminar" (
"usersem_user_id"	INTEGER,
"usersem_seminar_id"	INTEGER,
PRIMARY KEY("usersem_user_id","usersem_seminar_id")
)
    """


}