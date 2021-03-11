package pushes

import Gender

class PushData(private val pushData : MutableMap<String, String>) {
    fun getAge() : Int = pushData["age"]!!.toInt()

    fun getX() : Float = pushData["x_coord"]!!.toFloat()

    fun getY() : Float = pushData["y_coord"]!!.toFloat()

    fun getType() : PushType = PushType.valueOf(pushData["type"]!!)

    fun getText() : String = pushData["text"]!!

    fun getRadius() : Int = pushData["radius"]!!.toInt()

    fun getExpiryDate() : Long = pushData["expiry_date"]!!.toLong()

    fun getOsVersion() : Int = pushData["os_version"]!!.toInt()

    fun getGender() : Gender = Gender.valueOf(pushData["gender"]!!.toUpperCase())
}