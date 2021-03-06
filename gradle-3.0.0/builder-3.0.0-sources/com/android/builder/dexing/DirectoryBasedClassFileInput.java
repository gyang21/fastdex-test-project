/*
 * Copyright (C) 2017 The Android Open Source Project
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

package com.android.builder.dexing;

import com.android.annotations.NonNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.stream.Stream;

final class DirectoryBasedClassFileInput implements ClassFileInput {

    @NonNull private final Path rootPath;

    public DirectoryBasedClassFileInput(@NonNull Path rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public void close() throws IOException {
        // nothing to do for folders.
    }

    @Override
    @NonNull
    public Stream<ClassFileEntry> entries(Predicate<String> filter) throws IOException {
        Predicate<String> predicate = CLASS_MATCHER.and(filter);
        return Files.walk(rootPath)
                .filter(p -> predicate.test(p.toString()))
                .map(this::createEntryFromPath);
    }

    @NonNull
    private ClassFileEntry createEntryFromPath(@NonNull Path path) {
        return new FileBasedClassFileEntry(rootPath, path);
    }
}
