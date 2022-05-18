# Maven

## Instalação Maven
https://www.appsdeveloperblog.com/how-to-install-maven-on-mac-os/

```shell
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
brew install maven
mvn -v
```

## Criar projeto maven
- https://maven.apache.org/guides/mini/guide-creating-archetypes.html
- https://www.sohamkamani.com/java/cli-app-with-maven/

### Generate
- https://maven.apache.org/archetype/maven-archetype-plugin/generate-mojo.html

```shell
mvn archetype:generate                                  \
  -DinteractiveMode=<true|false>                        \ # mostra ou não mostra o modo iterativo(solicita confirmação dos dados da aplicação) 
  -DarchetypeArtifactId=<archetype-artifactId>          \ # tipo de projeto gerado - https://maven.apache.org/archetypes/
  -DarchetypeVersion=<archetype-version>                \ # versão para criação do tipo de projeto maven - https://stackoverflow.com/questions/55937171/mvn-archetypegenerate-which-version-selected
  -DgroupId=<my.groupid>                                \ # grupo do projeto que será utilizado no package
  -DartifactId=<my-artifactId>                            # nome do projeto
```

```shell
mvn archetype:generate                                  \
  -DinteractiveMode=false                               \
  -DarchetypeArtifactId=maven-archetype-quickstart      \
  -DarchetypeVersion=1.4                                \
  -DgroupId=br.com.maven_build                          \
  -DartifactId=maven_build 
```

## Build da aplicação
```shell
mvn validate # validar se o projeto está correto e todas as informações necessárias estão disponíveis
mvn compile # compila o código fonte da aplicação
mvn package # gera o jar da aplicação, rodando os testes de unidade
mvn clean # limpa a pasta target
mvn clean compile package -Dmaven.test.skip -DskipTests -Dmaven.javadoc.skip=true # compila e gera o pacote sem rodar os testes
mvn install # instala o pacote no repositório local, para uso como dependência em outros projetos localmente
mvn deploy #feito em um ambiente de integração ou lançamento, copia o pacote final para o repositório remoto para compartilhamento com outros desenvolvedores e projetos.
```

## Testes
```shell
mvn test # roda os testes da aplicação
```

## Como rodar a aplicação
```shell
mvn exec:java -Dexec.mainClass="br.com.maven_build.App" # roda a aplicação apontando para a classe principal
```

### Rodar em modo produção
```shell
java -jar target/maven_build-1.0-SNAPSHOT.jar # roda binário do java compilado
java -jar target/maven_build-1.0-SNAPSHOT.war # roda binário do java compilado
```

## Configurar o manifest (indicação para o jar qual é a classe inicial)
- https://www.sohamkamani.com/java/cli-app-with-maven/#running-our-code
```shell
vim pom.xml
```

- colocar o xml abaixo dentro da tag <build>
```xml
<!-- this goes within <build> -->
<plugins>
	<plugin>
		<!-- Build an executable JAR -->
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-jar-plugin</artifactId>
		<version>3.1.0</version>
		<configuration>
			<archive>
				<manifest>
					<addClasspath>true</addClasspath>
					<!-- here we specify that we want to use the main method within the App class -->
					<mainClass>br.com.maven_build.App</mainClass>
				</manifest>
			</archive>
		</configuration>
	</plugin>
</plugins>
<!-- other properties -->
```

### Definindo versão java 17
- dentro da tag <properties> colocar o xml abaixo
  
```shell
vim pom.xml
```

```xml
<properties>
    <java.version>17</java.version>
</properties>
```

## Adicionando dependencia
- https://mvnrepository.com/
```shell
<!-- https://mvnrepository.com/artifact/org.springframework/spring-web -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>5.3.19</version>
</dependency>
```

# Gradle

## Instalação
- https://gradle.org/install/
```shell
sdk install gradle 7.4.2 # instalação via package manager
brew install gradle # instalação via brew MacOs
gradle -v # verifica versão
```
## Iniciar um projeto gradle
```shell
gradle init # inicia projeto
```

## Como fazer build
```shell
gradle build # sempre escolha a versão em sourceCompatibility do java que está instalado na máquina
```

## Como rodar testes
```shell
gradle test # Roda os testes da aplicação
```

## Mostra opções disponíveis
Com esse comando, é possível ver diversos comandos disponíveis para diferentes ações.
```shell
gradle tasks
```

## Configurando manifest
- no arquivo build.gradle adicionar o item abaixo
```shell
vim build.gradle
```
```gradle
tasks.jar {
    manifest.attributes["Main-Class"] = "br.com.java_gradle_app.App"
}
```

## Como rodar a aplicação
```shell
gradle jar # gera o binário da aplicação
java -jar app/build/libs/app.jar # roda o binário gerado
gradle war # gera o binário da aplicação
java -war app/build/libs/app.jar # roda o binário gerado
gradle bootRun # Roda os testes da aplicação para aplicações springboot
./gradlew bootRun # rodar por shell script
```

## Ver estatisticas de build no serviço do gradle
```shell
gradle build --scan # ao aceitar os termos vc passa a ter uma interface na web com estatísticas de build
```

## O arquivo build.gradle
```gradle
plugins {
	id 'org.springframework.boot' version '2.6.4'
	id 'io.spring.dependency-management' version '1.0.11.RELEASE'
	id 'java'
}
group = 'br.com.didox.testgradle'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '8'
repositories {
	mavenCentral()
}
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
tasks.named('test') {
	useJUnitPlatform()
}
```

## Adicionando dependencia
- https://mvnrepository.com/
```shell
// https://mvnrepository.com/artifact/org.springframework/spring-web
implementation group: 'org.springframework', name: 'spring-web', version: '5.3.19'
```

## Ver todas as dependencias do projeto
```shell
gradle buildEnvironment
gradle dependencies
```

## Build Jar ou War
```shell
java -jar build/libs/demo-0.0.1-SNAPSHOT.war
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

#### Fonte
- [Repositório Maven](https://github.com/torneseumprogramador/java-maven-build)
- [Repositório Gradle](https://github.com/torneseumprogramador/java-gradle-build)


# New Relic
- Para fazer a instalação do new relic na máquina linux, devemos executar esses comandos:
```shell
curl -Ls https://download.newrelic.com/install/newrelic-cli/scripts/install.sh | bash && sudo NEW_RELIC_API_KEY=NRAK-S5KL1GNWU9Z4TLKGMNF4DWM87Q9 NEW_RELIC_ACCOUNT_ID=3495412 /usr/local/bin/newrelic install
```
- Na aplicação, precisamos adicionar a dependência e plugin no pow.xml:
```
<dependency>
  <groupId>com.newrelic.agent.java</groupId>
  <artifactId>newrelic-java</artifactId>
  <version>7.7.0</version>
  <scope>provided</scope>
  <type>zip</type>
</dependency>


<!-- Unzip New Relic Java agent into target/ -->
     <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>3.1.1</version>
        <executions>
          <execution>
            <id>unpack-newrelic</id>
            <phase>package</phase>
            <goals>
              <goal>unpack-dependencies</goal>
            </goals>
            <configuration>
              <includeGroupIds>com.newrelic.agent.java</includeGroupIds>
              <includeArtifactIds>newrelic-java</includeArtifactIds>
              <!-- you can optionally exclude files -->
              <!-- <excludes>**/newrelic.yml</excludes> -->
              <overWriteReleases>false</overWriteReleases>
              <overWriteSnapshots>false</overWriteSnapshots>
              <overWriteIfNewer>true</overWriteIfNewer>
              <outputDirectory>${project.build.directory}</outputDirectory>
            </configuration>
          </execution>
        </executions>
      </plugin>
```
- Após isso, executamos o projeto e deverá aparecer um newrelic.jar.
  - nesse diretório, devemos colocar um arquivo newrelic.yml
  - template [aqui](https://docs.newrelic.com/docs/apm/agents/java-agent/configuration/java-agent-config-file-template/)
  - precisa alterar alguns dados do arquivo:
    - license_key
    - app_name
- Para rodar o new relic, utilizamos o comando: 
```shell
java -javagent:<path to your new relic jar>\newrelic.jar -jar <path to your application jar>\<you rapplication jar name>.jar
```
[Referencia](https://stackoverflow.com/questions/26901959/new-relic-for-spring-boot)