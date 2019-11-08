include(":app", ":shared")
rootProject.name = "NiceHub"

include(":mvvm")
project(":mvvm").projectDir=(File("../mvvm","app"))

include(":libs")
project(":libs").projectDir=(File("../libs","app"))

include(":widget")
project(":widget").projectDir=(File("../widget","app"))