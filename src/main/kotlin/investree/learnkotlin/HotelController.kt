package investree.learnkotlin

import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/hotels")

class HotelController(val hotelService: HotelService) {

    @GetMapping()
    fun findAllOrByName(@RequestParam(value = "name", required = true, defaultValue = "") name: String): List<Hotel> {
        return when(name) {
            "" -> hotelService.findAllHotel()
            else -> hotelService.findByName(name)
        }
    }

    @PostMapping("/check-in")
    fun checkIn(@RequestBody checkInRequest: CheckInRequest) {
        val hotel = hotelService.findById(checkInRequest.hotelId).get()
        checkIn(hotel,checkInRequest.guests)
        hotelService.saveOrUpdate(hotel)
    }

    @PostMapping("/check-out")
    fun checkOut(@RequestBody checkInRequest: CheckInRequest) {
        val hotel = hotelService.findById(checkInRequest.hotelId).get()
        checkOut(hotel,checkInRequest.guests)
        hotelService.saveOrUpdate(hotel)
    }

    @PostMapping
    fun saveOrUpdateHotel(@RequestBody @Valid hotelRequest: HotelRequest ): Hotel? {

        val hotel: Hotel?
        when(hotelRequest.id) {
            null -> {
                hotel = Hotel(
                        name = hotelRequest.name,
                        classification = hotelRequest.classification,
                        rooms = hotelRequest.rooms,
                        freeRooms = hotelRequest.freeRooms
                )
            }
            else -> hotel = hotelRequest.id?.let { hotelService.findById(it).get() }
        }

        return hotel?.let { hotelService.saveOrUpdate(it) }

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