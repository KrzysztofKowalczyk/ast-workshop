/*
 * Copyright 2003-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gr8conf;

import groovy.transform.CompilationUnitAware
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic;
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.ModuleNode
import org.codehaus.groovy.ast.expr.ConstantExpression;
import org.codehaus.groovy.control.CompilationUnit;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.AbstractASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation

import java.lang.reflect.Modifier

import static java.lang.reflect.Modifier.FINAL
import static java.lang.reflect.Modifier.PUBLIC
import static java.lang.reflect.Modifier.STATIC;

@GroovyASTTransformation(phase= CompilePhase.CANONICALIZATION)
public class AuthorAdderASTTransformation extends AbstractASTTransformation implements CompilationUnitAware {
    CompilationUnit compilationUnit;

    @Override
    public void setCompilationUnit(CompilationUnit compilationUnit) {
        this.compilationUnit = compilationUnit;
    }

    @Override
    public void visit(ASTNode[] nodes, SourceUnit source) {
        nodes.each { ASTNode node ->
            visit(node)
        }
    }


    // visitor on dynamic dispatch

    def visit(ASTNode node) {
        // do nothing
    }

    def visit(ModuleNode node) {
        node.classes.each {
            visit(it)
        }
    }

    def visit(ClassNode node) {
        node.addField("author", PUBLIC | STATIC | FINAL, new ClassNode(String.class), new ConstantExpression("Me"))
    }
}
