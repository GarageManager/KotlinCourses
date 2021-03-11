package pushes.implementations

import pushes.PushData
import pushes.interfaces.IExpiryPush
import pushes.interfaces.ILocationPush

class LocationPush(pushData : PushData) : ILocationPush, IExpiryPush {
    override val text = pushData.getText()
    override val x = pushData.getX()
    override val y = pushData.getY()
    override val radius = pushData.getRadius()
    override val expiryDate = pushData.getExpiryDate()
}