/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.tools.fd.runtime;




/**
 * Boxing/unboxing services for primitive types.
 */
public enum BasicType {

    I(Integer.TYPE),
    J(Long.TYPE),
    C(Character.TYPE),
    Z(Boolean.TYPE),
    F(Float.TYPE),
    D(Double.TYPE),
    V(Void.TYPE);


    private final Class<?> primitiveJavaType;

    BasicType( Class<?> primitiveType) {
        this.primitiveJavaType = primitiveType;
    }


    public Class getJavaType() {
        return primitiveJavaType;
    }


    public static BasicType parse(String name) {
        for (BasicType basicType : BasicType.values()) {
            if (basicType.getJavaType().getName().equals(name)) {
                return basicType;
            }
        }
        return null;
    }

}
