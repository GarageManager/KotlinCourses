package filters

import SystemParameters
import pushes.interfaces.IPush
import pushes.interfaces.ITechPush

class SystemVersionFilter : IFilter {
    override fun shouldFilter(push: IPush, parameters: SystemParameters): Boolean =
            push is ITechPush && parameters.osVersion > push.osVersion
}