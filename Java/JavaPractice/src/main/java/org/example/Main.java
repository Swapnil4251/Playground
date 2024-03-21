package org.example;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.internal.utils.FileUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.jdt.internal.core.ExternalJavaProject;
import org.eclipse.jdt.internal.core.JavaProject;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String methodName = "referencedMethod";
        String returnType = "java.lang.String";
        List<String> argTypes = Arrays.asList("int", "java.lang.String", ReferencedClass.JavaType.class.getCanonicalName());
        try {
            List<File> javaFiles = ProjectScanner.findJavaFiles("./");
            System.out.println("Java files loaded - " + javaFiles.size());
            for (File file: javaFiles) {
                System.out.println("Parsing file - " + file);
                ASTParser parser = ASTParser.newParser(AST.JLS3);
                
                parser.setResolveBindings(true);
                parser.setBindingsRecovery(true);
                //Hashtable<String, String> options = JavaCore.getDefaultOptions();
                //options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);
                //parser.setCompilerOptions(options);
                
                parser.setSource(FileUtils.readFileToString(file).toCharArray());
                
                CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);
                MethodReferenceFinder finder = new MethodReferenceFinder(methodName, returnType, argTypes);
                compilationUnit.accept(finder);
                System.out.println("Parsing file completed - " + file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CompilationUnit getCompilationUnit() throws JavaModelException {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
// Get all projects in the workspace
        IProject[] projects = root.getProjects();

        IPackageFragment[] packages = JavaCore.create(projects[0]).getPackageFragments();
        IPackageFragment mypackage = packages[0];
        ICompilationUnit[] compilationUnits = mypackage.getCompilationUnits();

        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(compilationUnits[0]);
        parser.setResolveBindings(true);
        return (CompilationUnit) parser.createAST(null);
    }
}