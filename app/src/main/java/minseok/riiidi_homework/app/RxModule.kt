package minseok.riiidi_homework.app

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
@InstallIn(ApplicationComponent::class)
object RxModule {
    @Provides
    fun createSchedulerProvider(): SchedulerProvider {
        return AndroidSchedulerProvider()
    }
}


interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
}

class AndroidSchedulerProvider : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.io()
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
}