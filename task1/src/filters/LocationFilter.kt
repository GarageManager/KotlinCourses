package filters

import SystemParameters
import pushes.interfaces.ILocationPush
import pushes.interfaces.IPush

class LocationFilter : IFilter {
    override fun shouldFilter(push: IPush, parameters: SystemParameters): Boolean =
            push is ILocationPush && push.getDistance(parameters) > push.radius
}