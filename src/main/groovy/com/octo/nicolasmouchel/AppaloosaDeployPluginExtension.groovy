package com.octo.nicolasmouchel

import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.tasks.Input

class AppaloosaDeployPluginExtension {
    @Input
    String storeToken
    @Input
    NamedDomainObjectContainer<AppaloosaDeployApks> apks

    void storeToken(String storeToken) {
        this.storeToken = storeToken
    }

    void apks(Closure closure) {
        apks.configure(closure)
    }
}

class AppaloosaDeployApks {
    final String name
    String buildName
    String flavorName
    String apkName
    String descriptionVersion
    String groupsName

    AppaloosaDeployApks(String name) {
        this.name = name
    }
String getApkName(){return ''}
    String getApkName(String moduleName) {
        if (apkName != null) {
            return apkName
        }
        String buildedApkName = ''
        if (flavorName != null) {
            buildedApkName += flavorName + '/'
        }
        buildedApkName += moduleName + '-'
        if (buildName != null) {
            buildedApkName += buildName
        } else {
            buildedApkName += name
        }
        buildedApkName += '.apk'
        return buildedApkName
    }

    void buildName(final String buildName) {
        this.buildName = buildName
    }

    void flavorName(final String flavorName) {
        this.flavorName = flavorName
    }

    void apkName(final String apkName) {
        this.apkName = apkName
    }

    void descriptionVersion(final String descriptionVersion) {
        this.descriptionVersion = descriptionVersion
    }

    void groupsName(final String groupsName) {
        this.groupsName = groupsName
    }
}