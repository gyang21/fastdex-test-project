package com.dx168.fastdex.build.snapshoot.api;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by tong on 17/3/30.
 */
public interface STSerializable<T> {
    void serializeTo(OutputStream outputStream) throws IOException;
}
