package com.haekal.mymovie.api

import com.haekal.mymovie.utils.LiveDataTestUtil
import java.io.IOException
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class PeopleServiceTest : ApiAbstract<PeopleService>() {

  private lateinit var service: PeopleService

  @Before
  fun initService() {
    this.service = createService(PeopleService::class.java)
  }

  @Throws(IOException::class)
  @Test
  fun fetchPersonListTest() {
    enqueueResponse("/tmdb_people.json")
    val response = LiveDataTestUtil.getValue(service.fetchPopularPeople(1))
    assertThat(response.body?.results?.get(0)?.id, `is`(28782))
    assertThat(response.body?.total_pages, `is`(984))
    assertThat(response.body?.total_results, `is`(19671))
  }

  @Throws(IOException::class)
  @Test
  fun fetchPersonDetail() {
    enqueueResponse("tmdb_person.json")
    val response = LiveDataTestUtil.getValue(service.fetchPersonDetail(123))
    assertThat(response.body?.birthday, `is`("1963-12-18"))
    assertThat(response.body?.known_for_department, `is`("Acting"))
    assertThat(response.body?.place_of_birth, `is`("Shawnee, Oklahoma, USA"))
  }
}
