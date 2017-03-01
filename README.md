#Appaloosa gradle plugin

##INSTALLATION

Add repositories to your project build.gradle

    repositories {
            jcenter()
            // for appaloosa API
            maven {
                url 'https://github.com/joel1di1/joel1di1-mvn-repo/raw/master/releases/'
            }
        }
        dependencies {
            classpath 'com.octo.nicolasmouchel:appaloosa-deploy:1.3'
        }
    }

And apply plugin in your app build.gradle

    apply plugin: 'appaloosa-deploy'
    
##CONFIGURATION

###BASIC
    appaloosaDeploy{
            storeToken 'your-store-token'
            apks {
                release {
                    descriptionVersion 'description'
                    groupsName 'everybody'
                }
            }
    }

###COMPLETE

    appaloosaDeploy{
        storeToken 'your-store-token'
        apks {
            feature {
                buildName 'release'
                flavorName 'full'
                descriptionVersion 'appaloosa try'
                groupsName 'test-only'
            }
    
            demo {
                apkName 'myFlavor/my-app.apk'
                descriptionVersion ''
                groupsName 'everybody'
            }
        }
        proxy {
            host 'proxy-hostname'
            port proxy-portnumber
            user 'proxy-username'
            pass 'proxy-password'
        }
    }
