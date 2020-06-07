package investree.learnkotlin

import com.fasterxml.jackson.annotation.JsonProperty

data class CheckInRequest(

        @JsonProperty(value = "hotelId")
        val hotelId: Long,

        @JsonProperty(value = "guests")
        val guests: Int = 0

)