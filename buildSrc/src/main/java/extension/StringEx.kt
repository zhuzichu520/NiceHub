package extension

fun String.plusQuotes(): String {
    return """"$this""""
}

fun String.toInt2(): Int {
    return try {
        this.toInt()
    } catch (e: Exception) {
        throw Exception("请输入合法的数字")
    }
}