import Config.project

object Log {

    private const val logFormat = "=====flag->%-25s||%s"

    private val logger by lazy { project.logger }

    fun q(flag: String, message: Any?) {
        logger.quiet(String.format(logFormat, flag, message.toString()))
    }

    fun e(flag: String, message: Any?) {
        logger.error(String.format(logFormat, flag, message.toString()))
    }

    fun w(flag: String, message: Any?) {
        logger.warn(String.format(logFormat, flag, message.toString()))
    }

    fun i(flag: String, message: Any?) {
        logger.info(String.format(logFormat, flag, message.toString()))
    }

    fun d(flag: String, message: Any?) {
        logger.debug(String.format(logFormat, flag, message.toString()))
    }
}