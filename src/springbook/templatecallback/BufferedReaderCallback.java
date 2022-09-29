package springbook.templatecallback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public interface BufferedReaderCallback {
    Integer doSomethingWithReader(BufferedReader br) throws IOException;
}