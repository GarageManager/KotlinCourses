package pushes.implementations

import pushes.PushData
import pushes.interfaces.IGenderPush

class GenderPush(pushData: PushData) : IGenderPush {
    override val text = pushData.getText()
    override val gender = pushData.getGender()
}