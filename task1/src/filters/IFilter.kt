package filters

import SystemParameters
import pushes.interfaces.IExpiryPush
import pushes.interfaces.IPush

interface IFilter {
    fun shouldFilter(push : IPush, parameters: SystemParameters) : Boolean
}