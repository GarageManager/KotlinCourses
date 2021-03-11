package pushes.implementations

import pushes.PushData
import pushes.interfaces.IAgePush
import pushes.interfaces.IExpiryPush

class AgeSpecificPush(pushData: PushData) : IAgePush, IExpiryPush {
    override val text = pushData.getText()
    override val age = pushData.getAge()
    override val expiryDate = pushData.getExpiryDate()
}