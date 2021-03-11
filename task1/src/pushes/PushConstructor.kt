package pushes

import pushes.implementations.*
import pushes.interfaces.IPush

fun makePush(pushData: PushData) : IPush {
    return when (pushData.getType())
    {
        PushType.LocationPush -> LocationPush(pushData)
        PushType.AgeSpecificPush -> AgeSpecificPush(pushData)
        PushType.TechPush -> TechPush(pushData)
        PushType.LocationAgePush -> LocationAgePush(pushData)
        PushType.GenderAgePush -> GenderAgePush(pushData)
        PushType.GenderPush -> GenderPush(pushData)
    }
}