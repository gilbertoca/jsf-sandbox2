package jsf.sandbox.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import jsf.sandbox.service.cnab240.DetalheLoteSegmentoT;
import jsf.sandbox.service.cnab240.DetalheLoteSegmentoU;
import jsf.sandbox.service.cnab240.HeaderArquivo;
import org.apache.commons.io.FileUtils;
import org.jrimum.texgit.FlatFile;
import org.jrimum.texgit.Record;
import org.jrimum.texgit.Texgit;

public class LerArquivoRetornoBB {
    
    private List<DetalheLoteSegmentoT> segmentos;
    private final FlatFile<Record> flatFile;
    private static final String LEIAUTE_RETORNO_BB_240 = "cnab240/leiaute-retorno-banco-brasil.xml";

    public LerArquivoRetornoBB(File arquivo) {
        // 'getResource' on a Class, relative path is resolved based on the package the Class is in.
        File template = new File(getClass().getResource(LEIAUTE_RETORNO_BB_240).getFile());
        //carrega o template/modelo de remessa do BB
        flatFile = Texgit.createFlatFile(template);
        carregarLinhas(arquivo);
        carregarSegmentos();
    }

    private void carregarLinhas(File arquivo) {
        List<String> linhas;
        try {
            linhas = FileUtils.readLines(arquivo);
        } catch (IOException e) {
            throw new RuntimeException("Erro lendo linhas do arquivo de retorno", e);
        }
        flatFile.read(linhas);
    }

    private void carregarSegmentos() {
        Collection<Record> registros = flatFile.getRecords("RegistroDetalheSegmentoT");
        segmentos = new ArrayList<DetalheLoteSegmentoT>();
        DetalheLoteSegmentoT segmentoT;
        DetalheLoteSegmentoU segmentoU;
        for (Record registro: registros) {
            segmentoT = new DetalheLoteSegmentoT(registro);
            List<Record> listaSegmentoU = segmentoT.getRecord().getInnerRecords();
            segmentoU = new DetalheLoteSegmentoU(listaSegmentoU.get(0));
            segmentoT.setSegmentoU(segmentoU);
            segmentos.add(segmentoT);
        }
    }
    
    public HeaderArquivo getHeaderArquivo() {
        Record record = flatFile.getRecord("HeaderArquivo");
        return new HeaderArquivo(record);        
    }
    
    public List<DetalheLoteSegmentoT> getSegmentos() {
        return segmentos;
    }
}
