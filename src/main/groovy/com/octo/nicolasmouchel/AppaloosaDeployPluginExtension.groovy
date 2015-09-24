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
    String falvorName
    String apkName
    String description
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
        if (falvorName != null) {
            buildedApkName += falvorName + '/'
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

    void falvorName(final String falvorName) {
        this.falvorName = falvorName
    }

    void apkName(final String apkName) {
        this.apkName = apkName
    }

    void description(final String description) {
        this.description = description
    }

    void groupsName(final String groupsName) {
        this.groupsName = groupsName
    }
}