package org.example;

import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class MethodReferenceFinder extends ASTVisitor {

    private String methodName;
    private String returnType;
    private List<String> argTypes;

    public  MethodReferenceFinder(String methodName, String returnType, List<String> argTypes) {
        this.methodName = methodName;
        this.returnType = returnType;
        this.argTypes = argTypes;
    }

    @Override
    public boolean visit(MethodInvocation node) {
        IMethodBinding methodBinding = node.resolveMethodBinding();
        System.out.println("method binding: " + methodBinding);
        if (methodBinding != null) {
            ITypeBinding declaringClass = methodBinding.getDeclaringClass();
            String methodSignature = declaringClass.getQualifiedName() + "." + methodBinding.getName();
            System.out.println("Visited: " + declaringClass);
            if (methodSignature.equals(methodName) && returnType.equals(methodBinding.getReturnType().toString())) {
                System.out.println("Found reference: " + methodSignature);
            }
        } else {
            String method = node.getName().toString();
            List<Expression> arguments = node.arguments();
            //System.out.println("method binding (fallback): " + method);
            if (method.equals(methodName) && matchArgumentTypes(argTypes, arguments)) {
                System.out.println("Found reference: " + method);
            }
        }
        return super.visit(node);
    }

    private boolean matchArgumentTypes(List<String> expectedArgTypes, List<Expression> actualArguments) {
        if (expectedArgTypes.size() != actualArguments.size()) {
            return false;
        }

        for (int i = 0; i < expectedArgTypes.size(); i++) {
            String expectedType = expectedArgTypes.get(i);
            Expression expression = actualArguments.get(i);
            System.out.println("expression: " + expression.getClass());

            ITypeBinding typeBinding = expression.resolveTypeBinding();
            if (typeBinding != null) {
                String actualType = typeBinding.getQualifiedName();
                if (!expectedType.equals(actualType)) {
                    return false;
                }
            } else {
                if (expression instanceof ClassInstanceCreation) {
                    Type type = ((ClassInstanceCreation) expression).getType();
                    if (type.isSimpleType())
                }
            }
        }

        return true;
    }
}
