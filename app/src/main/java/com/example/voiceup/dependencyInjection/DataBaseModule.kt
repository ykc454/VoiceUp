package com.example.voiceup.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.voiceup.data.IssueDao
import com.example.voiceup.data.IssueDataRepository
import com.example.voiceup.data.IssueDatabase
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): IssueDatabase {
        return Room.databaseBuilder(
            context = context,
            IssueDatabase::class.java,
            "issue_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(database: IssueDatabase): IssueDao {
        return database.issueDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: IssueDao): IssueRepository {
        return IssueDataRepository(dao)
    }
}