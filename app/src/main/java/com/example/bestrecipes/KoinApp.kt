package com.example.bestrecipes

import android.app.Application
import androidx.room.Room
import com.example.bestrecipes.data.RecipeDB
import com.example.bestrecipes.data.RecipeDao
import com.example.bestrecipes.data.RecipeRepository
import com.example.bestrecipes.ui.RecipeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class KoinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(
                recipeDB,
                recipeViewModel
            )
        }
    }

    private val recipeDB = module {
        fun provideDataBase(application: Application): RecipeDB {
            return Room.databaseBuilder(application, RecipeDB::class.java, "RECIPEDB")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun provideDao(dataBase: RecipeDB): RecipeDao {
            return dataBase.recipeDao
        }
        single { provideDataBase(androidApplication()) }
        single { provideDao(get()) }

    }

    private val recipeViewModel = module{

        single{RecipeRepository(get())}
        viewModel{RecipeViewModel(get())}
    }

}
