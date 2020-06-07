package investree.learnkotlin

import javax.persistence.*

@Entity
@Table(name = "hotels")
data class Hotel(

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name")
    var name: String?,

    @Column(name = "classification")
    var classification: Int = 0,

    @Column(name = "rooms")
    var rooms: Int = 0,

    @Column(name = "free_rooms")
    var freeRooms: Int = 0
)


