package makarova.thousandsofcourses.network

import makarova.thousandsofcourses.network.response.CoursesResponse
import retrofit2.http.GET

interface Api {

    @GET("/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q")
    suspend fun load(): List<CoursesResponse>?

}