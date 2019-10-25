import extension.getPropertyByKey
import extension.plusQuotes
import extension.toInt2
import org.gradle.api.Project
import org.gradle.api.plugins.ExtraPropertiesExtension
import java.io.File
import java.io.FileInputStream
import java.util.*

object Config {

    private enum class GradleKey(val key: String) {
        IS_JENKINS("IS_JENKINS"),
        ENVIRONMENT("ENVIRONMENT"),
        LOCAL_GRADLE_PLUGIN_VERSION("LOCAL_GRADLE_PLUGIN_VERSION"),
        COMPILE_SDK_VERSION("COMPILE_SDK_VERSION"),
        TARGET_SDK_VERSION("TARGET_SDK_VERSION"),
        MIN_SDK_VERSION("MIN_SDK_VERSION"),
        APP_SERVER_URL("APP_SERVER_URL"),
        BUILD_TYPE("BUILD_TYPE"),
        RELEASE_SIGN_CONFIGS_PATH("RELEASE_SIGN_CONFIGS_PATH");
    }

    private enum class ConfigKey(val key: String) {
        APP_NAME("APP_NAME"),
        APPLICATION_ID("APPLICATION_ID"),
        APP_VERSION_NAME("APP_VERSION_NAME"),
        APP_VERSION_CODE("APP_VERSION_CODE");

    }

    private enum class SignKey(val key: String) {
        SIGN_KEY_ALIAS("SIGN_KEY_ALIAS"),
        SIGN_KEY_PASSWORD("SIGN_KEY_PASSWORD"),
        SIGN_STORE_FILE("SIGN_STORE_FILE"),
        SIGN_STORE_PASSWORD("SIGN_STORE_PASSWORD");
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
        try {
            loadProperties(
                rootPath
                    .plus(File.separator)
                    .plus("local.properties")
            )
        } catch (e: Exception) {
            Properties()
        }
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

    private val signatureProperties by lazy {
        try {
            loadProperties(getReleaseSignPath())
        } catch (e: Exception) {
            loadProperties(
                rootPath
                    .plus(File.separator)
                    .plus("app")
                    .plus(File.separator)
                    .plus("signature.properties")
            )
        }
    }

    @JvmStatic
    fun keyAlias(): String {
        return signatureProperties.getPropertyByKey(SignKey.SIGN_KEY_ALIAS.key).apply {
            Log.i("keyAlias", this)
        }
    }

    @JvmStatic
    fun keyPassword(): String {
        return signatureProperties.getPropertyByKey(SignKey.SIGN_KEY_PASSWORD.key).apply {
            Log.i("keyPassword", this)
        }
    }

    @JvmStatic
    fun storeFile(): String {
        return signatureProperties.getPropertyByKey(SignKey.SIGN_STORE_FILE.key).apply {
            Log.i("storeFile", this)
        }
    }

    @JvmStatic
    fun storePassword(): String {
        return signatureProperties.getPropertyByKey(SignKey.SIGN_STORE_PASSWORD.key).apply {
            Log.i("storePassword", this)
        }
    }

    @JvmStatic
    fun appName(): String {
        return configProperties.getPropertyByKey(ConfigKey.APP_NAME.key).apply {
            Log.i("appName", this)
        }
    }

    @JvmStatic
    fun compileSdkVersion(): Int {
        return gradleProperties.getPropertyByKey(GradleKey.COMPILE_SDK_VERSION.key).toInt2().apply {
            Log.i("compileSdkVersion", this)
        }
    }

    @JvmStatic
    fun applicationId(): String {
        return configProperties.getPropertyByKey(ConfigKey.APPLICATION_ID.key).apply {
            Log.i("applicationId", this)
        }
    }

    @JvmStatic
    fun minSdkVersion(): String {
        return gradleProperties.getPropertyByKey(GradleKey.MIN_SDK_VERSION.key).apply {
            Log.i("minSdkVersion", this)
        }
    }

    @JvmStatic
    fun targetSdkVersion(): String {
        return gradleProperties.getPropertyByKey(GradleKey.TARGET_SDK_VERSION.key).apply {
            Log.i("targetSdkVersion", this)
        }
    }

    @JvmStatic
    fun versionCode(): Int {
        return configProperties.getPropertyByKey(ConfigKey.APP_VERSION_CODE.key).toInt2().apply {
            Log.i("versionCode", this)
        }
    }

    @JvmStatic
    fun versionName(): String {
        return configProperties.getPropertyByKey(ConfigKey.APP_VERSION_NAME.key).apply {
            Log.i("versionName", this)
        }
    }

    @JvmStatic
    fun getRunEnvironment(): String {
        return gradleProperties.getPropertyByKey(GradleKey.ENVIRONMENT.key).apply {
            Log.i("getRunEnvironment", this)
        }
    }

    @JvmStatic
    fun initJenkinsProperties(project: Project) {
        Log.i(
            "DefaultExtraPropertiesExtension",
            project.extensions.getByType(ExtraPropertiesExtension::class.java).get("LOCAL_GRADLE_PLUGIN_VERSION")
        )
        Log.i("project.properties", project.properties["LOCAL_GRADLE_PLUGIN_VERSION"])
        gradleProperties.mapKeys {
            gradleProperties.put(it.key, project.properties[it.key])
        }
    }

    private fun getReleaseSignPath(): String {
        return gradleProperties.getPropertyByKey(GradleKey.RELEASE_SIGN_CONFIGS_PATH.key).apply {
            Log.i("getReleaseSignPath", this)
        }
    }

    @JvmStatic
    fun getBuildConfigFields(): List<Array<String>> {
        val list = mutableListOf<Array<String>>()
        configProperties.mapKeys {
            val configKeyArray = it.key.toString().split("_")
            if (configKeyArray.size <= 2)
                return@mapKeys
            if (configKeyArray[0].plus("_").plus(configKeyArray[1]) != "BUILD_CONFIG")
                return@mapKeys
            val environment = getEnvironment(configKeyArray).toLowerCase(Locale.getDefault())
            val dataType = getDataType(configKeyArray)
            val constantName = getConstantName(configKeyArray)
            val runEnvironment = getRunEnvironment().toLowerCase(Locale.getDefault())
            if (runEnvironment == environment) {
                list.add(arrayOf(dataType, constantName, it.value.toString().plusQuotes()))
            }
        }
        return list
    }

    private fun getEnvironment(configKeyArray: List<String>): String {
        return configKeyArray[2]
    }

    private fun getConstantName(configKeyArray: List<String>): String {
        val sb = StringBuilder()
        configKeyArray.drop(4).forEach {
            sb.append(it).append("_")
        }
        return sb.toString().dropLast(1)
    }

    private fun getDataType(configKeyArray: List<String>): String {
        val dataType = configKeyArray[3]
        return if (dataType.indexOf("STRING") >= 0) "String" else dataType.toLowerCase(Locale.getDefault())
    }

    @Throws(Exception::class)
    private fun loadProperties(path: String): Properties {
        return Properties().apply {
            load(FileInputStream(path))
        }
    }

}