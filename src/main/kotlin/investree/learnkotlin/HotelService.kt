package investree.learnkotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class HotelService @Autowired constructor(val hotelRepository: HotelRepository) {

    fun findByName(name: String): List<Hotel> = hotelRepository.findByName(name)

    fun findById(id: Long): Optional<Hotel> = hotelRepository.findById(id)

    fun findAllHotel(): List<Hotel> = hotelRepository.findAll()

    fun saveOrUpdate(hotel: Hotel): Hotel = hotelRepository.save(hotel)

}