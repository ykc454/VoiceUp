package com.example.voiceup.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.example.voiceup.data.IssueRepositoryImpl
import com.example.voiceup.data.local.IssueDao
import com.example.voiceup.data.local.IssueDatabase
import com.example.voiceup.data.local.IssueLocalDataSource
import com.example.voiceup.data.remote.FirebaseAuthRepository
import com.example.voiceup.data.remote.IssueRemoteDataSource
import com.example.voiceup.domain.repo.AuthRepository
import com.example.voiceup.domain.repo.IssueRepository
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

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return FirebaseAuthRepository()
    }
    @Provides
    @Singleton
    fun provideLocalDataSource(dao: IssueDao): IssueLocalDataSource {
        return IssueLocalDataSource(dao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(): IssueRemoteDataSource {
        return IssueRemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideRepository(
        local: IssueLocalDataSource,
        remote: IssueRemoteDataSource
    ): IssueRepository {
        return IssueRepositoryImpl(local, remote)
    }
}