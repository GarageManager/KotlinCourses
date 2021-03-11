package pushes.implementations

import pushes.PushData
import pushes.interfaces.IAgePush
import pushes.interfaces.ILocationPush

class LocationAgePush(pushData: PushData) : ILocationPush, IAgePush {
    override val text = pushData.getText()
    override val x = pushData.getX()
    override val y = pushData.getY()
    override val radius = pushData.getRadius()
    override val age = pushData.getAge()
}