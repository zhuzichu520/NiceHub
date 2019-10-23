import com.android.build.gradle.AppExtension
import extension.getPropertyByKey
import extension.plusQuotes
import jdk.nashorn.internal.objects.NativeArray.forEach
import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.*

object Config {

    private enum class GradleKey(val dateType: String, val key: String) {
        IS_JENKINS("boolean", "IS_JENKINS"),
        ENVIRONMENT("String", "ENVIRONMENT"),
        LOCAL_GRADLE_PLUGIN_VERSION("String", "LOCAL_GRADLE_PLUGIN_VERSION"),
        COMPILE_SDK_VERSION("int", "COMPILE_SDK_VERSION"),
        TARGET_SDK_VERSION("int", "TARGET_SDK_VERSION"),
        MIN_SDK_VERSION("int", "MIN_SDK_VERSION"),
        APP_SERVER_URL("String", "APP_SERVER_URL"),
        BUILD_TYPE("String", "BUILD_TYPE"),
        RELEASE_SIGN_CONFIGS_PATH("String", "RELEASE_SIGN_CONFIGS_PATH");

        companion object {
            fun of(key: String) = values().first {
                it.key == key
            }
        }
    }

    private enum class ConfigKey(val dateType: String, val key: String) {
        APP_NAME("String", "APP_NAME"),
        APPLICATION_ID("String", "APPLICATION_ID"),
        APP_VERSION_NAME("String", "APP_VERSION_NAME"),
        APP_VERSION_CODE("int", "APP_VERSION_CODE");

        companion object {
            fun of(key: String) = values().first {
                it.key == key
            }
        }
    }


    private val rootPath: String by lazy { System.getProperty("user.dir") }

    private val gradleProperties by lazy {
        val properties = defaultGradleProperties
        localGradleProperties.mapKeys {
            properties.put(it.key, it.value)
        }
        properties
    }

    private val defaultGradleProperties by lazy {
        loadProperties(
            rootPath
                .plus(File.separator)
                .plus("gradle.properties")
        )
    }

    private val localGradleProperties by lazy {
        loadProperties(
            rootPath
                .plus(File.separator)
                .plus("local.properties")
        )
    }

    private val configProperties by lazy {
        val properties = defaultConfigProperties
        flavorConfigProperties.mapKeys {
            properties.put(it.key, it.value)
        }
        properties
    }

    private val flavorConfigProperties by lazy {
        loadProperties(
            rootPath
                .plus(File.separator)
                .plus("app")
                .plus(File.separator)
                .plus("src")
                .plus(File.separator)
                .plus("main")
                .plus(File.separator)
                .plus("flavor-config.properties")
        )
    }

    private val defaultConfigProperties by lazy {
        loadProperties(
            rootPath
                .plus(File.separator)
                .plus("app")
                .plus(File.separator)
                .plus("default-config.properties")
        )
    }

    @JvmStatic
    fun compileSdkVersion(): Int {
        return try {
            gradleProperties.getPropertyByKey(GradleKey.COMPILE_SDK_VERSION.key).toInt()
        } catch (e: Exception) {
            throw Exception("请输入合法的数字")
        }
    }

    @JvmStatic
    fun applicationId(): String {
        return configProperties.getPropertyByKey(ConfigKey.APPLICATION_ID.key)
    }

    @JvmStatic
    fun minSdkVersion(): String {
        return gradleProperties.getPropertyByKey(GradleKey.MIN_SDK_VERSION.key)
    }

    @JvmStatic
    fun targetSdkVersion(): String {
        return gradleProperties.getPropertyByKey(GradleKey.TARGET_SDK_VERSION.key)
    }

    @JvmStatic
    fun versionCode(): String {
        return configProperties.getPropertyByKey(ConfigKey.APP_VERSION_CODE.key)
    }

    @JvmStatic
    fun versionName(): String {
        return configProperties.getPropertyByKey(ConfigKey.APP_VERSION_NAME.key)
    }

    @JvmStatic
    fun getRunEnvironment(): String {
        return gradleProperties.getPropertyByKey(GradleKey.ENVIRONMENT.key)
    }

    @JvmStatic
    fun initBuildConfig(project: Project) {
        configProperties.mapKeys {
            val configKeyArray = it.key.toString().split("_")
            if (configKeyArray.size < 2)
                return@mapKeys
            if (configKeyArray[0].plus("_").plus(configKeyArray[1]) != "BUILD_CONFIG")
                return@mapKeys
            val dataType = getDataType(configKeyArray)
            val constantName = getConstantName(configKeyArray)

            project.extensions.getByType(AppExtension::class.java).defaultConfig.buildConfigField(
                dataType,
                constantName,
                it.value.toString().plusQuotes()
            )
        }
    }

    private fun getConstantName(configKeyArray: List<String>): String {
        val sb = StringBuilder()
        configKeyArray.forEach {
            sb.append(it).append("_")
        }
        return sb.toString().dropLast(1)
    }

    private fun getDataType(configKeyArray: List<String>): String {
        val dataType = configKeyArray[3]
        return if (dataType.indexOf("STRING") >= 0) "String" else dataType.toLowerCase(Locale.getDefault())
    }

    private fun loadProperties(path: String): Properties {
        return Properties().apply {
            load(FileInputStream(path))
        }
    }

}