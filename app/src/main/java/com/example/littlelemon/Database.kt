package com.example.littlelemon

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Entity
data class MenuItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Dao
interface MenuItemsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(menuItems: List<MenuItem>)

    @Query("SELECT * from MenuItem")
    fun getAll(): LiveData<List<MenuItem>>
}

@Database(entities = [MenuItem::class], version = 1, exportSchema = false)
abstract class MenuDatabase : RoomDatabase(){
        abstract fun menuItemsDao(): MenuItemsDao

        companion object {
            @Volatile
            private var INSTANCE: MenuDatabase? = null

            fun getDatabase(context: Context): MenuDatabase {
                val tempInstance = INSTANCE
                if (tempInstance != null) return tempInstance

                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MenuDatabase::class.java,
                        "menu_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
}