package lab5;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class DecryptReader extends FilterReader {
    private int keyCode;

    public DecryptReader(Reader in, char keyChar) {
        super(in);
        this.keyCode = (int) keyChar;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int charsRead = super.read(cbuf, off, len);
        if (charsRead == -1) {
            return -1;
        }
        for (int i = 0; i < charsRead; i++) {
            cbuf[off + i] = (char) (cbuf[off + i] - keyCode);
        }
        return charsRead;
    }

    @Override
    public int read() throws IOException {
        int encrypted = super.read();
        if (encrypted == -1) {
            return -1;
        }
        int decrypted = encrypted - keyCode;
        return decrypted;
    }
}
