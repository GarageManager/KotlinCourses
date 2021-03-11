import filters.*
import pushes.PushData
import pushes.makePush

fun main() {
    val system = initializeSystem()
    val pushCount = readLine()!!.toInt()
    for (i in 1..pushCount)
    {
        val pushLength = readLine()!!.toInt()
        val pushRawInfo = mutableMapOf<String, String>()
        for (j in 1..pushLength)
        {
            val input = readLine()!!.split(' ')
            pushRawInfo[input[0]] = input[1]
        }

        system.addPush(makePush(PushData(pushRawInfo)))
    }
    system.printPushes();
}

fun initializeSystem() : System {
    val systemInfo = getSystemInfo()
    val systemParameters = SystemParameters(
            systemInfo["time"]!!.toLong(),
            systemInfo["age"]!!.toInt(),
            Gender.valueOf(systemInfo["gender"]!!.toUpperCase()),
            systemInfo["os_version"]!!.toInt(),
            systemInfo["x_coord"]!!.toFloat(),
            systemInfo["y_coord"]!!.toFloat()
    )
    val system = System(systemParameters)
    addFilters(system)
    return system
}

fun getSystemInfo() : Map<String, String>{
    val systemInfo = mutableMapOf<String, String>()
    for (i in 1..6)
    {
        val input = readLine()!!.split(" ")
        systemInfo[input[0]] = input[1];
    }
    return systemInfo
}

fun addFilters(system : System) {
    system.addFilter(AgeFilter())
    system.addFilter(ExpiryFilter())
    system.addFilter(GenderFilter())
    system.addFilter(LocationFilter())
    system.addFilter(SystemVersionFilter())
}