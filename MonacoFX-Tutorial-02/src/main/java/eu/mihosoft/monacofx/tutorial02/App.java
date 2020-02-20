/*
 * MIT License
 *
 * Copyright (c) 2020 Michael Hoffer <info@michaelhoffer.de>. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eu.mihosoft.monacofx.tutorial02;

import eu.mihosoft.monacofx.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // create a new monaco editor node
        MonacoFX monacoFX = new MonacoFX();
        StackPane root = new StackPane(monacoFX);

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
        
        monacoFX.getEditor().registerLanguage(language);

        // register custom theme for the custom language
        monacoFX.getEditor().registerTheme(new EditorTheme("mylang-theme", "vs-dark", true,
                new Rule("custom-info", "808080"),
                new Rule("custom-error", "ff0000", null, null,null, "bold"),
                new Rule("custom-notice", "ffa500"),
                new Rule("custom-date", "008800")
        ));

        // set initial text
        monacoFX.getEditor().getDocument().setText("[Sun Mar 7 16:02:00 2004] [notice] Apache/1.3.29 (Unix) configured -- resuming normal operations\n" +
                "[Sun Mar 7 16:02:00 2004] [info] Server built: Feb 27 2004 13:56:37\n" +
                "[Sun Mar 7 16:02:00 2004] [notice] Accept mutex: sysvsem (Default: sysvsem)\n" +
                "[Sun Mar 7 16:05:49 2004] [info] [client xx.xx.xx.xx] (104)Connection reset by peer: client stopped connection before send body completed\n" +
                "[Sun Mar 7 17:21:44 2004] [info] [client xx.xx.xx.xx] (104)Connection reset by peer: client stopped connection before send body completed\n" +
                "[Sun Mar 7 17:23:53 2004] statistics: Use of uninitialized value in concatenation (.) or string at /home/httpd/twiki/lib/TWiki.pm line 528.\n" +
                "[Sun Mar 7 17:23:53 2004] statistics: Can't create file /home/httpd/twiki/data/Main/WebStatistics.txt - Permission denied\n" +
                "[Sun Mar 7 17:27:37 2004] [info] [client xx.xx.xx.xx] (104)Connection reset by peer: client stopped connection before send body completed\n" +
                " completed\n" +
                "[Sun Mar 7 21:16:17 2004] [error] [client xx.xx.xx.xx] File does not exist: /home/httpd/twiki/view/Main/WebHome\n" +
                "[Sun Mar 7 21:20:14 2004] [info] [client xx.xx.xx.xx] (104)Connection reset by peer: client stopped connection before send body completed\n");

        // use the custom language
        monacoFX.getEditor().setCurrentLanguage("mylang");
        // and theme
        monacoFX.getEditor().setCurrentTheme("mylang-theme");

        // the usual scene & stage setup
        Scene scene = new Scene(root, 1400,600);
        primaryStage.setTitle(
            "MonacoFX Demo (running on JDK " + System.getProperty("java.version")
            +" and JFX " + System.getProperty("javafx.version") 
         + ")");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}