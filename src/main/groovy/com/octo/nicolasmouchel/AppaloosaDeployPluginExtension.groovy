package com.octo.nicolasmouchel

import groovy.transform.ToString
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.tasks.Input

class AppaloosaDeployPluginExtension {
    @Input
    String storeToken
    @Input
    NamedDomainObjectContainer<AppaloosaDeployApks> apks
    @Input
    AppaloosaDeployProxy proxy

    void storeToken(String storeToken) {
        this.storeToken = storeToken
    }

    void apks(Closure closure) {
        apks.configure(closure)
    }

    void proxy(Closure closure) {
        proxy = new AppaloosaDeployProxy()
        closure.delegate = proxy
        closure()
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

    String getApkName() { return '' }

    String getApkName(String moduleName) {
        if (apkName != null) {
            return apkName
        }
        String buildedApkName = moduleName + '-'
        if (flavorName != null) {
            buildedApkName += flavorName + '-'
        }
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

class AppaloosaDeployProxy {
    String host
    int port = 0
    String user
    String pass

    AppaloosaDeployProxy() {
        //
    }

    void host(final String host) {
        this.host = host
    }

    void port(final int port) {
        this.port = port
    }

    void user(final String user) {
        this.user = user
    }

    void pass(final String pass) {
        this.pass = pass
    }

    String toString() {
        "(host:${host}, port:{port}, user:${user}, pass:${pass})"
    }
}