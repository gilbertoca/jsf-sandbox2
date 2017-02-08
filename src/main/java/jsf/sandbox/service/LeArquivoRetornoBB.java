package jsf.sandbox.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import jsf.sandbox.service.cnab240.ArquivoFlatFile;
import jsf.sandbox.service.cnab240.DetalheLoteSegmentoT;
import jsf.sandbox.service.cnab240.DetalheLoteSegmentoU;
import org.jrimum.texgit.Record;

public class LeArquivoRetornoBB {

    private final ArquivoFlatFile templateFile;

    public LeArquivoRetornoBB() {
        //carrega o template/modelo de remessa do BB
        templateFile = new ArquivoFlatFile(getFileFromResource("leiaute-retorno-banco-brasil240.xml"));
    }

    private File getFileFromResource(String templatePath) {
        return new File(ArquivoFlatFile.class.getResource(templatePath).getFile());
    }

    public Collection<DetalheLoteSegmentoT> leArquivoRetorno(File arquivoRetorno) throws Exception {
        //busca as linhas do arquivo
        templateFile.read(arquivoRetorno);
        //instancia os objetos
        Collection<Record> recordsT = templateFile.getFlatFile().getRecords("DetalheLoteSegmentoT");

        Collection<DetalheLoteSegmentoT> listaSegmentoT = new ArrayList<DetalheLoteSegmentoT>();

        DetalheLoteSegmentoT segmentoT;
        DetalheLoteSegmentoU segmentoU;
        for (Iterator<Record> i = recordsT.iterator(); i.hasNext();) {
            segmentoT = new DetalheLoteSegmentoT(i.next());

            List<Record> listaSegmentoU = segmentoT.getRecord().getInnerRecords();
            segmentoU = new DetalheLoteSegmentoU(listaSegmentoU.get(0));

            segmentoT.setSegmentoU(segmentoU);

            listaSegmentoT.add(segmentoT);
        }

        return listaSegmentoT;
    }
}
