package com.fundtastic.mymovie.database

import com.fundtastic.mymovie.models.entity.Person
import com.fundtastic.mymovie.utils.LiveDataTestUtil
import com.fundtastic.mymovie.utils.MockTestUtil.Companion.mockPerson
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class PeopleDaoTest : LocalDatabase() {

  @Test
  fun insertAndRead() {
    val people = ArrayList<Person>()
    val mockPerson = mockPerson()
    people.add(mockPerson)

    db.peopleDao().insertPeople(people)
    val loadFromDB = LiveDataTestUtil.getValue(db.peopleDao().getPeople(1))[0]
    assertThat(loadFromDB.page, `is`(1))
    assertThat(loadFromDB.id, `is`(123))
  }

  @Test
  fun updateAndRead() {
    val people = ArrayList<Person>()
    val mockPerson = mockPerson()
    people.add(mockPerson)
    db.peopleDao().insertPeople(people)

    val loadFromDB = db.peopleDao().getPerson(mockPerson.id)
    assertThat(loadFromDB.page, `is`(1))

    mockPerson.page = 10
    db.peopleDao().updatePerson(mockPerson)

    val updated = db.peopleDao().getPerson(mockPerson.id)
    assertThat(updated.page, `is`(10))
  }
}
