object Log {
    const val fixSize = 30
    fun i(flag: String, message: Any?) {
        println("=====flag->".plus(flag).plus("||").plus(message.toString()))
    }
}