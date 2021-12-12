package com.arnoract.piggiplan.ui.create

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arnoract.piggiplan.test.BaseViewModelTest
import com.google.android.gms.maps.model.LatLng
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SelectAddressViewModelTest : BaseViewModelTest() {

    private lateinit var latLngObserver: TestObserver<LatLng?>
    private lateinit var invalidLatLngEventObserver: TestObserver<Unit>
    private lateinit var addressNameObserver: TestObserver<String>
    private lateinit var underTest: SelectAddressViewModel

    override fun setUpTest() {
        underTest = SelectAddressViewModel()
        latLngObserver = underTest.latLng.test()
        addressNameObserver = underTest.addressName.test()
        invalidLatLngEventObserver = underTest.invalidLatLngEvent.test()
    }

    @Test
    fun testSetLayLng() {
        underTest.setLatLng(LatLng(110.0, 100.0))

        latLngObserver.assertValue(LatLng(100.0, 100.0))
        invalidLatLngEventObserver.assertNoValue()
    }

    @Test
    fun testSetLatLng_isNull() {
        underTest.setLatLng(null)

        latLngObserver.assertNoValue()
        invalidLatLngEventObserver.assertHasValue()
    }

    @Test
    fun testSetAddressName() {
        underTest.setAddressName("name")

        addressNameObserver.assertValue("name")
    }
}