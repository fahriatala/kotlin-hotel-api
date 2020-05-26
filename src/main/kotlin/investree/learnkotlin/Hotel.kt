package investree.learnkotlin

import javax.persistence.*

@Entity
@Table(name = "hotels")
data class Hotel(var name: String,
                 var classification: Int,
                 var rooms: Int,
                 var freeRooms: Int) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    constructor(): this("",0,0,0)

}
