# MonacoFX-Tutorial 02


[HOME](https://github.com/miho/MonacoFX-Tutorials/blob/master/README.md)

In this tutorial you will learn how to register custom languages and themes.

<img src="resources/img/screenshot.png">

## Registering a custom language

The class `LanguageSupport` allows the definition of custom languages. A language support needs a unique name.
Optionally, a folding provider and a syntax highlighter can be provided. In the code sample below, we create custom
fold objects and a monarch-syntax highlighter.

```java
        // custom language based on
        // https://microsoft.github.io/monaco-editor/playground.html#extending-language-services-custom-languages
        LanguageSupport language = new LanguageSupport() {
            @Override
            public String getName() {
                return "mylang";
            }

            @Override
            public FoldingProvider getFoldingProvider() {
                // register custom code folds:
                //
                // it's possible to use a custom lexer that generates
                // the folds
                return editor -> new Folding[]{new Folding(1,5),new Folding(7,10)};
            }

            @Override
            public MonarchSyntaxHighlighter getMonarchSyntaxHighlighter() {
                // custom syntax highlighter:
                //
                // currently, only monarch is supported.
                // if you'd like to see full support for custom lexers (e.g. based on antlr)
                // consider PRs or at least let us know
                return () -> "tokenizer: {\n" +
                             "    root: [\n" +
                             "      [/\\[error.*/, \"custom-error\"],\n" +
                             "      [/\\[notice.*/, \"custom-notice\"],\n" +
                             "      [/\\[info.*/, \"custom-info\"],\n" +
                             "      [/\\[[a-zA-Z 0-9:]+\\]/, \"custom-date\"],\n" +
                             "    ]\n" +
                             "}";
            }
        };
```

To make use of the previously defined syntax highlighter, a custom theme needs to be registered.
        
```java
        monacoFX.getEditor().registerLanguage(language);

        // register custom theme for the custom language
        monacoFX.getEditor().registerTheme(new EditorTheme("mylang-theme", "vs-dark", true,
                new Rule("custom-info", "808080"),
                new Rule("custom-error", "ff0000", null, null,null, "bold"),
                new Rule("custom-notice", "ffa500"),
                new Rule("custom-date", "008800")
        ));
```

Finally, set the registered language and theme:
```java
        // use the custom language
        monacoFX.getEditor().setCurrentLanguage("mylang");
        // and theme
        monacoFX.getEditor().setCurrentTheme("mylang-theme");
```


## How to build and run the project

### 1. Dependencies

- JDK >= 11 (tested with JDK 13)
- Internet Connection (other dependencies will be downloaded automatically)
- Optional: IDE with [Gradle](http://www.gradle.org/) support

### 2. Building the project

#### IDE

To build the project from an IDE do the following:

- open the  [Gradle](http://www.gradle.org/) project
- call the `assemble` Gradle task to build the project

#### Command Line

Building the project from the command line is also possible.

Navigate to the project folder and call the `assemble` [Gradle](http://www.gradle.org/)
task to build the project.

##### Bash (Linux/OS X/Cygwin/other Unix-like OS)

    cd Path/To/MonacoFX-Tutorial-02
    ./gradlew assemble
    
##### Windows (CMD)

    cd Path\To\MonacoFX-Tutorial-02
    gradlew assemble

### 3. Running the project

#### IDE

To run the project from an IDE do the following:

- open the  [Gradle](http://www.gradle.org/) project
- call the `run` Gradle task to run the project

#### Command Line

Running the project from the command line is also possible.

Navigate to the project folder and call the `run` [Gradle](http://www.gradle.org/)
task to run the project.

##### Bash (Linux/OS X/Cygwin/other Unix-like OS)

    cd Path/To/MonacoFX-Tutorial-02
    ./gradlew run
    
##### Windows (CMD)

    cd Path\To\MonacoFX-Tutorial-02
    gradlew run




