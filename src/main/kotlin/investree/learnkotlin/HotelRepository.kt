package investree.learnkotlin

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HotelRepository : JpaRepository<Hotel, Long> {

    fun findByName(name: String): Optional<List<Hotel>>

}