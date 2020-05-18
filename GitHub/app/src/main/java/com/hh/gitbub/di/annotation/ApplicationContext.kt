package com.hh.gitbub.di.annotation

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext {
}