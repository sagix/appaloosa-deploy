package com.octo.nicolasmouchel

import org.gradle.api.Plugin
import org.gradle.api.Project
import com.appaloosastore.client.AppaloosaClient;

class AppaloosaDeployPlugin implements Plugin<Project> {
    void apply(Project project) {

        def apks = project.container(AppaloosaDeployApks)
        AppaloosaDeployPluginExtension extension = project.extensions.create(
                "appaloosaDeploy", AppaloosaDeployPluginExtension)
        extension.apks = apks;

        extension.apks.all { apk ->
            project.task("appaloosaDeploy${name.capitalize()}", group: 'Deploy') << {
                println "--Start deploying--"
                def client = new AppaloosaClient(extension.storeToken)
                client.deployFile(
                        project.buildDir.absolutePath + "/outputs/apk/${apk.getApkName(project.name)}",
                        description,
                        groupsName)
                println "--End deploying--"
            }
        }
    }
}
