/*
 * BSD 2-Clause License:
 * Copyright (c) 2009 - 2016
 * Software Technology Group
 * Department of Computer Science
 * Technische Universität Darmstadt
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  - Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY OF SUCH DAMAGE.
 */

package lib;

import lib.annotations.callgraph.CallSite;
import lib.annotations.callgraph.ResolvedMethod;
import lib.annotations.documentation.CGNote;
import lib.annotations.properties.EntryPoint;
import lib.Expression;
import lib.ExpressionVisitor;
import lib.testutils.CallbackTest;
import lib.testutils.StaticInitializerTest;

import static lib.annotations.callgraph.AnalysisMode.CPA;
import static lib.annotations.callgraph.AnalysisMode.OPA;
import static lib.annotations.documentation.CGCategory.NOTE;

/**
 * An unary expression which represents the identity function. Hence, the encapsulated expression
 * is mapped to itself.
 *
 * <p>
 * <!--
 * <b>NOTE</b><br>
 * This class is not meant to be (automatically) recompiled; it just serves documentation
 * purposes.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * INTENTIONALLY LEFT EMPTY TO MAKE SURE THAT THE SPECIFIED LINE NUMBERS ARE STABLE IF THE
 * CODE (E.G. IMPORTS) CHANGE.
 * <p>
 * <p>
 * <p>
 *
 * <p>
 * <p>
 * <p>
 * <p>
 * -->
 *
 * @author Micahel Reif
 */
public class IdentityExpression extends UnaryExpression {

    public static final String FQN = "lib/IdentityExpression";

    private static /* final */ IUnaryOperator _IDENTITY = IUnaryOperator.identity();

    public IdentityExpression(Expression expr){
        super(expr);
    }

    @EntryPoint(value = {OPA, CPA})
    public IUnaryOperator operator() {
        return _IDENTITY;
    }

    @EntryPoint(value = {OPA, CPA})
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @EntryPoint(value = {OPA, CPA})
    public String toString(){
        return "Id("+expr.toString()+")";
    }

    @EntryPoint
    @CGNote(value = NOTE, description = "This method is called during the garbage collection process if no references to this object are hold. It therefore becomes an entry point")
    @CallSite(name = "garbageCollectorCall", resolvedMethods = @ResolvedMethod(receiverType = CallbackTest.FQN), line = 117)
    @Override public void finalize() throws Throwable{
        CallbackTest.garbageCollectorCall();
        super.finalize();
    }
}