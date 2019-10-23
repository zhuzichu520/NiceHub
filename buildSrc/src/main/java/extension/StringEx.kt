package extension

fun String.plusQuotes(): String {
    return """"$this""""
}