tasks.register<Exec>("installPrePush") {
    commandLine(
        "sh",
        "-c",
        """
        rm -rf .git/hooks/pre-push* && 
        cp gradle/config/pre-push .git/hooks/pre-push && 
        chmod +x .git/hooks/pre-push
    """
    )
}
