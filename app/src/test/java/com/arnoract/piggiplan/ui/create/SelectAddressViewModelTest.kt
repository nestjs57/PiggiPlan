package com.arnoract.piggiplan.ui.create

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arnoract.piggiplan.test.BaseViewModelTest
import com.google.android.gms.maps.model.LatLng
import com.jraska.livedata.TestObserver
import com.jraska.livedata.test
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SelectAddressViewModelTest : BaseViewModelTest() {

    private lateinit var latLngSelectedObserver: TestObserver<LatLng?>
    private lateinit var addressNameSelectedObserver: TestObserver<String?>
    private lateinit var underTest: SelectAddressViewModel

    override fun setUpTest() {
        underTest = SelectAddressViewModel(mockk(relaxUnitFun = true))
        latLngSelectedObserver = underTest.latLngSelected.test()
        addressNameSelectedObserver = underTest.addressNameSelected.test()
    }

    @Test
    fun testSetAddressSelected() {
        underTest.setAddressSelected("id", "name", LatLng(100.0, 110.0))

        addressNameSelectedObserver.assertValue("name")
        latLngSelectedObserver.assertValue(LatLng(100.0, 110.0))
    }


    @Test
    fun testSetAddressName_LatLng_Is_Null() {
        underTest.setAddressSelected("id", "name", null)

        addressNameSelectedObserver.assertValue("name")
        latLngSelectedObserver.assertValue(null)
    }
}