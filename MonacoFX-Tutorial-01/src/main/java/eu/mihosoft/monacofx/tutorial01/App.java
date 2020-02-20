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
package eu.mihosoft.monacofx.tutorial01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import eu.mihosoft.monacofx.MonacoFX;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // create a new monaco editor node
        MonacoFX monacoFX = new MonacoFX();
        StackPane root = new StackPane(monacoFX);

        // set initial text
        monacoFX.getEditor().getDocument().setText(
                "#include <stdio.h>\n" +
                "int main() {\n" +
                "   // printf() displays the string inside quotation\n" +
                "   printf(\"Hello, World!\");\n" +
                "   return 0;\n" +
                "}");

        // use a predefined language like 'c'
        monacoFX.getEditor().setCurrentLanguage("c");
        monacoFX.getEditor().setCurrentTheme("vs-dark");

        // the usual scene & stage setup
        Scene scene = new Scene(root, 800,600);
        primaryStage.setTitle(
            "MonacoFX Demo (running on JDK " + System.getProperty("java.version")
            +" and JFX " + System.getProperty("javafx.version") 
         + ")");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}