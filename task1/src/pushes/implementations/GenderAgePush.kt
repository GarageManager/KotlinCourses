package pushes.implementations

import pushes.PushData
import pushes.interfaces.IAgePush
import pushes.interfaces.IGenderPush

class GenderAgePush(pushData: PushData) : IGenderPush, IAgePush {
    override val text = pushData.getText()
    override val gender = pushData.getGender()
    override val age = pushData.getAge()
}