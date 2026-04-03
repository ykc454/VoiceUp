package com.example.voiceup.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.voiceup.data.local.IssueDao
import com.example.voiceup.data.local.IssueDataRepository
import com.example.voiceup.data.local.IssueDatabase
import com.example.voiceup.data.remote.FirebaseRepository
import com.example.voiceup.domain.IssueRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Room database still provided (optional, can be used later)
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): IssueDatabase {
        return Room.databaseBuilder(
            context,
            IssueDatabase::class.java,
            "issue_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(database: IssueDatabase): IssueDao {
        return database.issueDao()
    }

    // ✅ MAIN REPOSITORY = Firebase
    @Provides
    @Singleton
    fun provideRepository(): IssueRepository {
        return FirebaseRepository
    }
}