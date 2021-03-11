package filters

import SystemParameters
import pushes.interfaces.IAgePush
import pushes.interfaces.IPush

class AgeFilter : IFilter {
    override fun shouldFilter(push: IPush, parameters: SystemParameters): Boolean =
            push is IAgePush && push.age > parameters.age
}