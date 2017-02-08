package jsf.sandbox.service.cnab240;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;
import org.jrimum.utilix.Objects;

public class ArquivoFlatFile {

    private final FlatFile<Record> flatFile;

    public ArquivoFlatFile(File templateFile) {
        Objects.checkNotNull(templateFile, "Arquivo Template a ser lido nulo!");
        flatFile = Texgit.createFlatFile(templateFile);
    }

    public void read(final File file) {
        Objects.checkNotNull(file, "Arquivo Texto a ser lido nulo!");
        read(file, "UTF-8");
    }

    @SuppressWarnings("unchecked")
    public void read(final File file, String encoding) {
        Objects.checkNotNull(file, "Arquivo Texto a ser lido nulo!");

        try {
            getFlatFile().read(FileUtils.readLines(file, encoding));

        } catch (IOException e) {
            throw new IllegalStateException("Arquivo Texto não lido!");
        }
    }

    public FlatFile<Record> getFlatFile() {
        return flatFile;
    }
}
