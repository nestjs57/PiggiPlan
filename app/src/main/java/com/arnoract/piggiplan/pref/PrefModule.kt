package com.arnoract.piggiplan.pref

import com.arnoract.piggiplan.core.KoinConst
import com.arnoract.piggiplan.pref.tutorial.SharedPreferencesTutorialPreferenceStorage
import com.arnoract.piggiplan.pref.tutorial.TutorialPreferenceStorage
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val prefModule = module {
    single<TutorialPreferenceStorage> {
        SharedPreferencesTutorialPreferenceStorage(get(named(KoinConst.SharedPreference.DEFAULT)))
    }
}