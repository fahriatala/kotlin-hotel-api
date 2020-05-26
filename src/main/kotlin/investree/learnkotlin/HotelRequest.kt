package investree.learnkotlin

import com.fasterxml.jackson.annotation.JsonProperty

data class HotelRequest (

        @JsonProperty(value = "id")
        var id: Long? = null,

        @JsonProperty(value = "name")
        val name: String,

        @JsonProperty(value = "classification")
        val classification: Int,

        @JsonProperty(value = "rooms")
        val rooms: Int,

        @JsonProperty(value = "freeRooms")
        var freeRooms: Int
)