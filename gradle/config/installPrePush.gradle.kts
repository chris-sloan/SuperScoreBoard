delete(
    fileTree(".git/hooks").matching {
        include("**/pre-push*")
    },
)

copy {
    from(rootProject.file("gradle/config/pre-push"))
    into(rootProject.file(".git/hooks"))
}
