object Log {
    fun i(flag: String, message: Any?) {
        val tag = String.format("%-25s", flag)
        println("=====flag->".plus(tag).plus("||").plus(message.toString()))
    }
}