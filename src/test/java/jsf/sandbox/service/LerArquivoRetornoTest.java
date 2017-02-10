package jsf.sandbox.service;


import java.io.File;
import java.util.List;
import jsf.sandbox.service.cnab240.DetalheLoteSegmentoT;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LerArquivoRetornoTest {

    private File retorno;
    private LerArquivoRetornoBB leitorArquivo;

    @Before
    public void setup() {
        //'getResource' on a ClassLoader, a relative path is resolved based on the root folder.
        ClassLoader classLoader = getClass().getClassLoader();
        retorno = new File(classLoader.getResource("RET/IEDCBR1965102201713542.ret").getFile());        
        leitorArquivo = new LerArquivoRetornoBB(retorno);
    }
    
    @Test
    public void deveLerArquivoRetornoComDoisSegmentos() throws Exception {
        List<DetalheLoteSegmentoT> segmentos = leitorArquivo.getSegmentos();
        System.out.println("segmentos T: " + segmentos);
        Assert.assertEquals(2, segmentos.size());
    }
}
