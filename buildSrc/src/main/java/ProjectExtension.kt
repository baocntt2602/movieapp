import org.gradle.api.Project
import java.util.*

private const val LOCAL_PROPERTIES_FILE_NAME = "local.properties"

fun Project.getLocalProperty(propertyName: String): String {
    val localProperties = Properties().apply {
        val localPropertiesFile = project.rootProject.file(LOCAL_PROPERTIES_FILE_NAME)
        if (localPropertiesFile.exists()) {
            load(localPropertiesFile.inputStream())
        }
    }

    return localProperties.getProperty(propertyName)?.let {
        it
    } ?: run {
        throw NoSuchFieldException("Not defined property: $propertyName")
    }
}