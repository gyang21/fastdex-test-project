/*
 * Copyright (C) 2016 The Android Open Source Project
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

package com.android.apkzlib.zfile;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Creates or updates APKs based on provided entries.
 */
public interface ApkCreator extends Closeable {

    /**
     * Copies the content of a Jar/Zip archive into the receiver archive.
     *
     * <p>An optional predicate allows to selectively choose which files to copy over and an
     * option function allows renaming the files as they are copied.
     *
     * @param zip the zip to copy data from
     * @param transform an optional transform to apply to file names before copying them
     * @param isIgnored an optional filter or {@code null} to mark which out files should not be
     * added, even through they are on the zip; if {@code transform} is specified, then this
     * predicate applies after transformation
     * @throws IOException I/O error
     */
    void writeZip(
            @Nonnull File zip,
            @Nullable Function<String, String> transform,
            @Nullable Predicate<String> isIgnored)
            throws IOException;

    /**
     * Writes a new {@link File} into the archive. If a file already existed with the given
     * path, it should be replaced.
     *
     * @param inputFile the {@link File} to write.
     * @param apkPath the filepath inside the archive.
     * @throws IOException I/O error
     */
    void writeFile(@Nonnull File inputFile, @Nonnull String apkPath) throws IOException;

    /**
     * Deletes a file in a given path.
     *
     * @param apkPath the path to remove
     * @throws IOException failed to remove the entry
     */
    void deleteFile(@Nonnull String apkPath) throws IOException;

    /** Returns true if the APK will be rewritten on close. */
    boolean hasPendingChangesWithWait() throws IOException;
}
