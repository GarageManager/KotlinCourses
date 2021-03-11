package pushes.interfaces

import SystemParameters
import kotlin.math.pow
import kotlin.math.sqrt

interface ILocationPush : IPush{
    val x : Float

    val y : Float

    val radius : Int

    fun getDistance(parameters : SystemParameters) : Float =
            sqrt((x - parameters.x).pow(2) + (y - parameters.y).pow(2))
}