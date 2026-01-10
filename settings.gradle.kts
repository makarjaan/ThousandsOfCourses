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

rootProject.name = "CityPulse"
include(":app")
include(":feature")
include(":feature:auth")
include(":feature:auth:api")
include(":feature:auth:impl")
include(":core")
include(":core:design-system")
include(":core:network")
include(":core:navigation")
include(":core:utils")
include(":core:database")
include(":feature:main")
include(":feature:main:api")
include(":feature:main:impl")
include(":feature:profile")
include(":feature:profile:api")
include(":feature:profile:impl")
include(":feature:detail")
include(":feature:detail:impl")
include(":feature:detail:api")
include(":feature:favorite")
include(":feature:favorite:api")
include(":feature:favorite:impl")
include(":core:analytics")
