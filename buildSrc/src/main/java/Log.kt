object Log {
    fun i(flag: String, message: Any?) {
        println("=====flag->".plus(flag).plus("||").plus(message.toString()))
    }
}