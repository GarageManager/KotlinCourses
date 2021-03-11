import filters.IFilter
import pushes.interfaces.IPush
import java.util.*

class System(private val systemParameters: SystemParameters) {
    private val filters = mutableListOf<IFilter>()
    private val pushesToPrint: Queue<IPush> = LinkedList();

    fun addFilter(filter : IFilter) = filters.add(filter)

    fun addPush(push : IPush)
    {
        if (!filters.any{filter: IFilter -> filter.shouldFilter(push, systemParameters)})
            pushesToPrint.add(push)
    }

    fun printPushes()
    {
        if (pushesToPrint.count() == 0)
        {
            println(-1);
        } else {
            while (pushesToPrint.count() != 0)
                println(pushesToPrint.poll().text)
        }
    }
}