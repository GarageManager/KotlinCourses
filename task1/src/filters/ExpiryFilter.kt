package filters

import SystemParameters
import pushes.interfaces.IExpiryPush
import pushes.interfaces.IPush

class ExpiryFilter : IFilter {
    override fun shouldFilter(push: IPush, parameters: SystemParameters): Boolean =
        push is IExpiryPush && push.expiryDate < parameters.time
}