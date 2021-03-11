package pushes.implementations

import pushes.PushData
import pushes.interfaces.ITechPush

class TechPush(pushData: PushData) : ITechPush {
    override val text = pushData.getText()
    override val osVersion = pushData.getOsVersion()
}