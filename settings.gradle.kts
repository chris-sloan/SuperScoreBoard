pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SuperScoreBoard"
include(":app")

include(":data")
include(":data:feature")
include(":data:feature:fixtures")
include(":data:feature:match")
include(":data:network")

include(":domain")
include(":domain:common")
include(":domain:common:model")
include(":domain:common:useraction")
include(":domain:feature")
include(":domain:feature:fixtures")
include(":domain:feature:match")

include(":ui")
include(":ui:common")
include(":ui:theme")
include(":ui:mvi")
include(":ui:feature")
include(":ui:feature:fixtures")
include(":ui:feature:match")

