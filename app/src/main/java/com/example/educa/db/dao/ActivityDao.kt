package com.example.educa.db.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.widget.Toast
import com.example.educa.db.entities.Activity
import com.example.educa.db.DatabaseHelper

class ActivityDao(private val context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertActivity(activity: Activity) {
        if (activity.name.isBlank() || activity.descriptionShort.isBlank() || activity.age == 0) {
            Toast.makeText(context, "Nome, descrizione ed età sono obbligatori", Toast.LENGTH_SHORT).show()
            return
        }

        if (isActivityNameExists(activity.name)) {
            Toast.makeText(context, "Un'attività con questo nome esiste già", Toast.LENGTH_SHORT).show()
            return
        }

        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", activity.name)
            put("age", activity.age)
            put("is_favorite", activity.isFavorite)
            put("is_for_group", activity.isForGroup)
            put("is_for_single", activity.isForSingle)
            put("description_short", activity.descriptionShort)
            put("description_long", activity.descriptionLong)
            put("duration", activity.duration)
            put("variants", activity.variants)
            put("how_to_do_it", activity.howToDoIt)
        }
        db.insert("Activities", null, values)
        db.close()
    }

    fun getActivitiesByQuery(query: String): List<String> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery(query, null)
        val activityNames = mutableListOf<String>()
        while (cursor.moveToNext()) {
            activityNames.add(cursor.getString(cursor.getColumnIndexOrThrow("name")))
        }
        cursor.close()
        db.close()
        return activityNames
    }
    fun getAllActivities(): List<Activity> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Activities", null, null, null, null, null, null)
        val activities = mutableListOf<Activity>()
        while (cursor.moveToNext()) {
            activities.add(createActivityFromCursor(cursor))
        }
        cursor.close()
        db.close()
        return activities
    }

    fun getActivityByName(name: String): MutableList<Activity> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Activities",
            null,
            "name = ?",
            arrayOf(name),
            null,
            null,
            null
        )
        val activities = mutableListOf<Activity>()
        while (cursor.moveToNext()) {
            activities.add(createActivityFromCursor(cursor))
        }
        cursor.close()
        db.close()
        return activities
    }
    fun isFavorite(name: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Activities",
            arrayOf("is_favorite"),
            "name = ?",
            arrayOf(name),
            null,
            null,
            null
        )
        var isFavorite = false
        if (cursor.moveToFirst()) {
            isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow("is_favorite")) == 1
        }
        cursor.close()
        db.close()
        return isFavorite
    }

    fun setFavorite(name: String, isFavorite: Boolean) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("is_favorite", if (isFavorite) 1 else 0)
        }
        db.update("Activities", values, "name = ?", arrayOf(name))
        db.close()
    }

    fun searchActivities(query: String): List<Activity> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Activities",
            null,
            "name LIKE ? OR description_short LIKE ?",
            arrayOf("%$query%", "%$query%"),
            null,
            null,
            null
        )
        val activities = mutableListOf<Activity>()
        while (cursor.moveToNext()) {
            activities.add(createActivityFromCursor(cursor))
        }
        cursor.close()
        db.close()
        return activities
    }

    fun getFavoriteActivities(): List<Activity> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Activities",
            null,
            "is_favorite = 1",
            null,
            null,
            null,
            null
        )
        val activities = mutableListOf<Activity>()
        while (cursor.moveToNext()) {
            activities.add(createActivityFromCursor(cursor))
        }
        cursor.close()
        db.close()
        return activities
    }
    fun getActivitiesByAge(age: Int): List<Activity> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Activities",
            null,
            "age = ?",
            arrayOf(age.toString()),
            null,
            null,
            null
        )
        val activities = mutableListOf<Activity>()
        while (cursor.moveToNext()) {
            activities.add(createActivityFromCursor(cursor))
        }
        cursor.close()
        db.close()
        return activities
    }

    fun getActivitiesForGroup(): List<Activity> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Activities",
            null,
            "is_for_group = 1",
            null,
            null,
            null,
            null
        )
        val activities = mutableListOf<Activity>()
        while (cursor.moveToNext()) {
            activities.add(createActivityFromCursor(cursor))
        }
        cursor.close()
        db.close()
        return activities
    }

    fun getActivitiesForSingle(): List<Activity> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            "Activities",
            null,
            "is_for_single = 1",
            null,
            null,
            null,
            null
        )
        val activities = mutableListOf<Activity>()
        while (cursor.moveToNext()) {
            activities.add(createActivityFromCursor(cursor))
        }
        cursor.close()
        db.close()
        return activities
    }

    fun updateActivity(activity: Activity) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("age", activity.age)
            put("is_favorite", activity.isFavorite)
            put("is_for_group", activity.isForGroup)
            put("is_for_single", activity.isForSingle)
            put("description_short", activity.descriptionShort)
            put("description_long", activity.descriptionLong)
            put("duration", activity.duration)
            put("variants", activity.variants)
            put("how_to_do_it", activity.howToDoIt)
        }
        db.update("Activities", values, "name = ?", arrayOf(activity.name))
        db.close()
    }

    fun deleteActivity(name: String) {
        val db = dbHelper.writableDatabase
        db.delete("Activities", "name = ?", arrayOf(name))
        db.close()
    }

    fun getActivityCount(): Int {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM Activities", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        return count
    }

    fun getFavoriteActivityCount(): Int {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM Activities WHERE is_favorite = 1", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        db.close()
        return count
    }

// I metodi getActivitiesWithTools e getActivitiesWithObjectives richiedono
// di definire le classi Tool e Objective e di implementare le relative DAO
// prima di poter essere implementati correttamente.
private fun createActivityFromCursor(cursor: Cursor): Activity {
    return Activity(
        name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
        age = cursor.getInt(cursor.getColumnIndexOrThrow("age")),
        isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow("is_favorite")) == 1,
        isForGroup = cursor.getInt(cursor.getColumnIndexOrThrow("is_for_group")) == 1,
        isForSingle = cursor.getInt(cursor.getColumnIndexOrThrow("is_for_single")) == 1,
        descriptionShort = cursor.getString(cursor.getColumnIndexOrThrow("description_short")),
        descriptionLong = cursor.getString(cursor.getColumnIndexOrThrow("description_long")),
        duration = cursor.getInt(cursor.getColumnIndexOrThrow("duration")),
        variants = cursor.getString(cursor.getColumnIndexOrThrow("variants")),
        howToDoIt = cursor.getString(cursor.getColumnIndexOrThrow("how_to_do_it"))
    )
}

    private fun isActivityNameExists(name: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT 1 FROM Activities WHERE name = ?", arrayOf(name))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }
    fun getDistinctAges(): List<Int> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT DISTINCT age FROM Activities", null)
        val ages = mutableListOf<Int>()
        while (cursor.moveToNext()) {
            ages.add(cursor.getInt(0))
        }
        cursor.close()
        db.close()
        return ages
    }
}