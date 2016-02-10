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

package expressions;

import static annotations.documentation.CGCategory.NATIVE_CALLBACK;
import annotations.documentation.CGNote;

/**
 * A array-based stack implementation.
 *
 * <!--
 * <b>NOTE</b><br>
 * This class is not meant to be (automatically) recompiled; it just serves documentation
 * purposes.
 *
 *
 *
 *
 *
 *
 * INTENTIONALLY LEFT EMPTY TO MAKE SURE THAT THE SPECIFIED LINE NUMBERS ARE STABLE IF THE
 * CODE (E.G. IMPORTS) CHANGE.
 *
 *
 *
 *
 *
 *
 *
 * -->
 *
 * @author Michael Eichberg
 * @author Micahel Reif
 */
public class Stack<V> {

    private V[] data = (V[]) new Object[100];
    private int entries = 0;

    public Stack() {}

    @CGNote(value = NATIVE_CALLBACK,
            description = "potential callback because an object type is passed to a native method;" +
                    "methods of this object could be called from native code (I.e. toString, clone etc.)")
    public void push(V v){
        if(data.length == entries) {
            V[]  newData = (V[]) new Object[entries*2+1];
            System.arraycopy(data,0,newData,0,data.length);
            data = newData;
        }
        data[entries] = v;
        entries += 1;
    }

    public int size() {
        return entries;
    }

    public V peek(){
        return data[entries-1];
    }

    public V pop(){
        V v = data[entries-1];
                entries -= 1;
                        return  v;
    }

    public boolean isEmpty(){ return entries == 0; }

    public Iterator<V> iterator(){
        return new StackIterator(data, entries);
    }

    class StackIterator implements Iterator<V>{

        private V[] data;
        private int cur = 0;


        public StackIterator(V[] data, int top){
            this.data = data;
            cur = top-1;
        }

        @Override
        public boolean hasNext() {
            return cur >= 0;
        }

        @Override
        public V next() {
            return data[cur--];
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }
}
