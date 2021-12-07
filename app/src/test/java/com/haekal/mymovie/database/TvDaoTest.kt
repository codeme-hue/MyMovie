package com.haekal.mymovie.database

import com.haekal.mymovie.models.entity.Tv
import com.haekal.mymovie.utils.LiveDataTestUtil
import com.haekal.mymovie.utils.MockTestUtil.Companion.mockTv
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class TvDaoTest : LocalDatabase() {

  @Test
  fun insertAndRead() {
    val tvList = ArrayList<Tv>()
    val tv = mockTv()
    tvList.add(tv)

    db.tvDao().insertTv(tvList)
    val loadFromDB = LiveDataTestUtil.getValue(db.tvDao().getTvList(tv.page))[0]
    assertThat(loadFromDB.page, `is`(1))
    assertThat(loadFromDB.id, `is`(123))
  }

  @Test
  fun updateAndReadTest() {
    val tvList = ArrayList<Tv>()
    val tv = mockTv()
    tvList.add(tv)
    db.tvDao().insertTv(tvList)

    val loadFromDB = db.tvDao().getTv(tv.id)
    assertThat(loadFromDB.page, `is`(1))

    tv.page = 10
    db.tvDao().updateTv(tv)

    val updated = db.tvDao().getTv(tv.id)
    assertThat(updated.page, `is`(10))
  }
}
