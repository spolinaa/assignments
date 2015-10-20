package Flyweight

import java.util.*

public abstract class House {
    protected val floors: Int = 0
    public abstract fun Build (color: String, street: String)
}

public class PanelHouse : House() {
    public fun PanelHouse() {
        val floors = 16
    }
    override fun Build(color: String, street: String ){
        println("House is built of panels; 16 floors; $color color; $street street")
    }
}

public class BrickHouse : House() {
    public fun BrickHouse(){
        val floors = 5
    }
    override fun Build(color: String, street: String ){
        println("House is built of bricks; 5 floors; $color color; $street street")
    }
}

public class HouseFactory() {
    public var houses = HashMap<Int, House>()

    public fun  GetHouse(key: Int): House?{
        if (houses.containsKey(key)) return houses[key]
        else {
            if (key == 5) houses.put(key, BrickHouse())
            if (key == 16) houses.put(key, PanelHouse())
            return houses[key]
        }
    }
}

fun main(args: Array<String>) {
    val houseFactory = HouseFactory()
    houseFactory.GetHouse(5)?.Build("red", "First")
    houseFactory.GetHouse(16)?.Build("green", "Baker")
}