package investree.learnkotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/hotels")

class HotelController @Autowired constructor(val hotelService: HotelService) {

    @GetMapping()
    fun findAllOrByName(@RequestParam(value = "name", required = true, defaultValue = "") name: String) = when(name) {
        "" -> hotelService.findAllHotel()
        else -> hotelService.findByName(name)
    }

    @PostMapping("/check-in")
    fun checkIn(@RequestBody checkInRequest: CheckInRequest) {
        val hotel = hotelService.findById(checkInRequest.hotelId)
        checkIn(hotel,checkInRequest.guests)
        hotelService.saveOrUpdate(hotel)
    }

    @PostMapping("/check-out")
    fun checkOut(@RequestBody checkInRequest: CheckInRequest) {
        val hotel = hotelService.findById(checkInRequest.hotelId)
        checkOut(hotel, checkInRequest.guests)
        hotelService.saveOrUpdate(hotel)
    }

    @PostMapping
    fun saveOrUpdateHotel(@RequestBody @Valid hotelRequest: HotelRequest ): Hotel {
        val hotel: Hotel

        when(hotelRequest.id){
            null -> {
                hotel = Hotel(
                        name = hotelRequest.name,
                        classification = hotelRequest.classification,
                        rooms = hotelRequest.rooms,
                        freeRooms = hotelRequest.freeRooms
                )
            }
            else -> {
                hotel = hotelRequest.id.let { hotelService.findById(it) }
                hotel.name = hotelRequest.name
                hotel.classification = hotelRequest.classification
                hotel.rooms = hotelRequest.rooms
                hotel.freeRooms = hotelRequest.freeRooms
            }
        }

        return hotelService.saveOrUpdate(hotel)

    }

    fun checkIn(hotel: Hotel,guests: Int) {
        when {
            hotel.freeRooms >= guests -> hotel.freeRooms -= guests
        }
    }

    fun checkOut(hotel: Hotel,guests: Int) {
        when {
            hotel.freeRooms >= guests -> hotel.freeRooms += guests
        }
    }
}
