package com.octo.nicolasmouchel

import com.appaloosastore.client.AppaloosaClient
import org.gradle.api.Plugin
import org.gradle.api.Project

class AppaloosaDeployPlugin implements Plugin<Project> {

    void apply(Project project) {

        AppaloosaDeployPluginExtension extension = project.extensions.create("appaloosaDeploy", AppaloosaDeployPluginExtension)

        def apks = project.container(AppaloosaDeployApks)
        extension.apks = apks

        extension.apks.all { apk ->
            project.task("appaloosaDeploy${name.capitalize()}", group: 'Deploy') << {
                println "--Start deploying--"
                def client = new AppaloosaClient(extension.storeToken)
                if (extension.proxy != null) {
                    println "--Using proxy : " + extension.proxy
                    if (extension.proxy.host != null) {
                        client.setProxyHost(extension.proxy.host);
                    }
                    if (extension.proxy.port > 0) {
                        client.setProxyPort(extension.proxy.port);
                    }
                    if (extension.proxy.user != null) {
                        client.setProxyUser(extension.proxy.user);
                    }
                    if (extension.proxy.pass != null) {
                        client.setProxyPass(extension.proxy.pass);
                    }
                }
                client.deployFile(
                        project.buildDir.absolutePath + "/outputs/apk/${apk.getApkName(project.name)}",
                        descriptionVersion,
                        groupsName)
                println "--End deploying--"
            }
        }
    }
}
