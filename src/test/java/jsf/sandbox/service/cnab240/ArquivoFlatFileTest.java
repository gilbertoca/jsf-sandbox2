package jsf.sandbox.service.cnab240;

import java.io.File;
import java.util.Collection;

import org.jrimum.texgit.Record;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class ArquivoFlatFileTest {

    private static final String REMESSA_FILE = "REM/REMESSA-01.REM";
    private static final String TEMPLATE_FILE = "leiaute-remessa-banco-brasil240.xml";
    private ArquivoFlatFile flatFile;

    @Before
    public void setup() {
        //'getResource' on a ClassLoader, a relative path is resolved based on the root folder.
        ClassLoader classLoader = getClass().getClassLoader();
        File remessa = new File(classLoader.getResource(REMESSA_FILE).getFile());
        // 'getResource' on a Class, relative path is resolved based on the package the Class is in.
        File template = new File(ArquivoFlatFile.class.getResource(TEMPLATE_FILE).getFile());

        flatFile = new ArquivoFlatFile(template);
        flatFile.read(remessa);
    }

    @Test
    public void deveLerTrailerArquivo() {
        Record record = flatFile.getFlatFile().getRecord("TrailerArquivo");
        TrailerArquivo trailerArquivo = new TrailerArquivo(record);
        assertNotNull("Não deve ser null", trailerArquivo);
        System.out.println("TrailerArquivo: " + trailerArquivo);
    }

    @Test
    public void deveLerHeaderArquivo() {
        Record record = flatFile.getFlatFile().getRecord("HeaderArquivo");
        HeaderArquivo headerArquivo = new HeaderArquivo(record);
        assertNotNull("Não deve ser null", headerArquivo);
        System.out.println("HeaderArquivo: " + headerArquivo);
    }

    @Test
    public void deveLerHeaderLote() {
        Record record = flatFile.getFlatFile().getRecord("HeaderLote");
        HeaderLote headerLote = new HeaderLote(record);
        assertNotNull("Não deve ser null", headerLote);
        System.out.println("HeaderLote: " + headerLote);
    }

    @Test
    public void deveLerTrailerLote() {
        Record record = flatFile.getFlatFile().getRecord("TrailerLote");
        TrailerLote trailerLote = new TrailerLote(record);
        assertNotNull("Não deve ser null", trailerLote);
        System.out.println("TrailerLote: " + trailerLote);
    }

    @Test
    public void deveLerSegmentosP() {
        Collection<Record> recordsP = flatFile.getFlatFile().getRecords("RegistroDetalheSegmentoP");
        assertNotNull("Não deve ser null", recordsP);
        System.out.println("RegistroDetalheSegmentoP n. registros: " + recordsP.size());
    }
}
