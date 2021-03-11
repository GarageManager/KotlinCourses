package filters

import SystemParameters
import pushes.interfaces.IGenderPush
import pushes.interfaces.IPush

class GenderFilter : IFilter {
    override fun shouldFilter(push: IPush, parameters: SystemParameters): Boolean =
            push is IGenderPush && push.gender != parameters.userGender
}