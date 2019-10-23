package extension

import java.util.*

fun Properties.getPropertyByKey(key: String): String {
    return try {
        this.getProperty("COMPILE_SDK_VERSION")
    } catch (e: Exception) {
        throw  Exception("Properties配置文件中未找到".plus(key).plus("配置项！"))
    }
}