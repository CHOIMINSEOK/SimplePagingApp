package minseok.riiidi_homework.app

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import minseok.riiidi_homework.data.PostRepositoryImpl
import minseok.riiidi_homework.domain.PostRepository

@Module
@InstallIn(FragmentComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl
    ): PostRepository

}