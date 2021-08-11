package datatouch.uikit.core.windowargs.converter

internal object ArgsConverter {

    fun <T> listToArrayList(list: List<T>) = when (list.isEmpty()) {
        true -> ArrayList(0)
        else -> arrayListOf<T>().apply { addAll(list) }
    }

    fun <T> listToArrayListNullable(list: List<T>?): ArrayList<T>? {
        if (list == null) return null
        return listToArrayList(list)
    }
}