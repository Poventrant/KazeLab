package nio;

import java.nio.ByteBuffer;

public class NioBufferHandler {

    private ByteBuffer readbuf = null;
    private ByteBuffer writebuf = null;

    public NioBufferHandler(int readsize, int writesize, boolean direct) {
        if (direct) {
            readbuf = ByteBuffer.allocateDirect(readsize);
            writebuf = ByteBuffer.allocateDirect(writesize);
        } else {
            readbuf = ByteBuffer.allocate(readsize);
            writebuf = ByteBuffer.allocate(writesize);
        }
    }

    public ByteBuffer expand(ByteBuffer buffer, int remaining) {
        return buffer;
    }

    public ByteBuffer getReadBuffer() {
        return readbuf;
    }

    public ByteBuffer getWriteBuffer() {
        return writebuf;
    }

}