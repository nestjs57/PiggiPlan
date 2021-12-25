package com.arnoract.piggiplan.test.model

import com.arnoract.piggiplan.domain.model.branch.Branch

object BranchTestData {

    private val SukiTeenoiBranch1 = Branch(
        branchId = 1L,
        resId = 1L,
        branchName = "สุกี้ตี๋น้อย สาขา เดอะ พาซิโอ พาร์ค กาญจนาภิเษก",
        addressName = "8/9 Kanchanaphisek Rd, เเขวง ศาลาธรรมสพน์ Thawi Watthana, Bangkok 10170",
        latitude = 13.767064015873348,
        longitude = 100.40566238925558,
        tel = "1150",
    )

    private val SukiTeenoiBranch2 = Branch(
        branchId = 2L,
        resId = 1L,
        branchName = "สุกี้ตี๋น้อย เกษตร-นวมินทร์",
        addressName = "2 ซอยประเสริฐมนูกิจ 7 ถนนประเสริฐมนูกิจ, ลาดพร้าว, Bangkok 10230",
        latitude = 13.84207425577887,
        longitude = 100.59443491141242,
        tel = "1150",
    )

    private val SukiTeenoiBranch3 = Branch(
        branchId = 3L,
        resId = 1L,
        branchName = "สุกี้ตี๋น้อย บ้านบางเขน",
        addressName = "17 ถ. พหลโยธิน อนุสาวรีย์, เขตบางเขน กรุงเทพมหานคร 10220",
        latitude = 13.863005798309647,
        longitude = 100.58904250973546,
        tel = "1150",
    )

    private val SukiTeenoiBranch4 = Branch(
        branchId = 4L,
        resId = 1L,
        branchName = "สุกี้ตี๋น้อย เลียบทางด่วน",
        addressName = "61 ซ. ประดิษฐ์มนูธรรม 19 แขวงลาดพร้าว เขตลาดพร้าว กรุงเทพมหานคร 10230",
        latitude = 13.807362289394634,
        longitude = 100.61612269977613,
        tel = "1150",
    )

    private val SukiTeenoiBranch5 = Branch(
        branchId = 5L,
        resId = 1L,
        branchName = "สุกี้ตี๋น้อย พหลฯ-วัชรพล",
        addressName = "700 ถ. เทพรักษ์ (พหลโยธิน, ถนน วัชรพล แขวง ท่าแร้ง กรุงเทพมหานคร 10220",
        latitude = 13.875247758733265,
        longitude = 100.64962079792181,
        tel = "+66968833748",
    )

    private val SukiTeenoiBranch6 = Branch(
        branchId = 6L,
        resId = 1L,
        branchName = "สุกี้ตี๋น้อย แจ้งวัฒนะ",
        addressName = "หมู่ที่ 5 56/115, Thanon Chaeng Watthana, Pak Kret, Pak Kret District, Nonthaburi 11120",
        latitude = 13.907042389877299,
        longitude = 100.51151875559471,
        tel = "+66918866151",
    )

    fun all() = listOf(
        SukiTeenoiBranch1,
        SukiTeenoiBranch2,
        SukiTeenoiBranch3,
        SukiTeenoiBranch4,
        SukiTeenoiBranch5,
        SukiTeenoiBranch6
    )
}