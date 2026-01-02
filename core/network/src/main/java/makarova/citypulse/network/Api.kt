package makarova.citypulse.network

import makarova.citypulse.network.response.CoursesResponse
import retrofit2.http.GET

interface Api {

    @GET("/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q")
    suspend fun load(): CoursesResponse

}