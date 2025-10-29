package lab5;

import java.io.*;

public class EncryptWriter extends FilterWriter {
    private int keyCode;

    public EncryptWriter(Writer out, char keyChar) {
        super(out);
        this.keyCode = (int) keyChar;
    }

    @Override
    public void write(int b) throws IOException {
        int encrypted  = b + keyCode;
        super.write(encrypted);
    }
    @Override
    public void write(char[] buffer, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(buffer[off + i]);
        }
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(str.charAt(off + i));
        }
    }
}
