# Scarabei 

Collection of open source reusable Java components

Набор Java-компонентов, чтобы катать всякое говно

[![](https://jitpack.io/v/Scarabei/Scarabei.svg)](https://jitpack.io/#Scarabei/Scarabei)

# Installation
See also: https://jitpack.io/#Scarabei/Scarabei/1.2.3
```
apply plugin: 'java'
apply plugin: 'maven'

sourceCompatibility = 1.8
[compileJava, compileTestJava]*.options*.encoding = 'UTF-8'

repositories {
        	 maven { url 'https://jitpack.io' }
        	 mavenCentral()
}


dependencies {
//format:  compile 'com.github.Scarabei.Scarabei:%project-name%:%version%'

           compile 'com.github.Scarabei.Scarabei:scarabei-api:-SNAPSHOT'
	         compile 'com.github.Scarabei.Scarabei:scarabei-api-desktop:-SNAPSHOT'
	         compile 'com.github.Scarabei.Scarabei:scarabei-red:-SNAPSHOT'
	         compile 'com.github.Scarabei.Scarabei:scarabei-red-desktop:-SNAPSHOT'
	         compile 'com.github.Scarabei.Scarabei:scarabei-gson:-SNAPSHOT'
	         compile 'com.github.Scarabei.Scarabei:scarabei-apache-cmns:-SNAPSHOT'
           
	...
}	
	
```
