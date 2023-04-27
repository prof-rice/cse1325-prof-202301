package store;

import java.io.BufferedWriter;
import java.io.IOException;

interface Saveable {
    void save(BufferedWriter bw) throws IOException;
}
