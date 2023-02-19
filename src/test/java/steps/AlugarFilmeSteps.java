package steps;

import br.com.raffathamires.entidades.Filme;
import br.com.raffathamires.entidades.NotaAluguel;
import br.com.raffathamires.entidades.TipoAluguel;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import servicos.AluguelService;
import utils.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlugarFilmeSteps {

    private Filme filme;
    private AluguelService aluguel = new AluguelService();
    private NotaAluguel notaAluguel;
    private String erro;
    private TipoAluguel tipoAluguel = TipoAluguel.COMUM;

    @Dado("um filme com estoque de {int} unidades")
    public void umFilmeComEstoqueDeUnidades(Integer int1) {
            filme = new Filme();
            filme.setEstoque(int1);
    }
    @Dado("que o preço do aluguel seja R$ {int}")
    public void queOPreçoDoAluguelSejaR$(Integer int1) {
        filme.setAluguel(int1);

    }
    @Quando("alugar")
    public void alugar() {
        try{
            notaAluguel = aluguel.alugar(filme, tipoAluguel);
        } catch (RuntimeException e) {
            erro = e.getMessage();
        }
    }
    @Então("o preço do aluguel será R$ {int}")
    public void oPreçoDoAluguelSeráR$(int int1) throws Throwable{
        Assert.assertEquals(int1, notaAluguel.getPreco());

    }
    @Então("o estoque do filme será {int} unidade")
    public void oEstoqueDoFilmeSeráUnidade(int int1) {
        Assert.assertEquals(int1, filme.getEstoque());
    }

    @Então("não será possível por falta de estoque")
    public void não_será_possível_por_falta_de_estoque() {
        Assert.assertEquals("Filme sem estoque", erro);
    }

    @Dado("^que o tipo do aluguel seja (.*)$")
    public void queOTipoDoAluguelSejaExtendido(String tipo) {
        tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL: tipo.equals("extendido")? TipoAluguel.EXTENDIDO: TipoAluguel.COMUM;
    }
    @Então("a data de entrega será em {int} dias")
    public void aDataDeEntregaSeráEmDias(Integer int1) {
        Date dataEsperada = DateUtils.obterDataDiferencaDias(int1);
        Date dataReal = notaAluguel.getDataEntrega();

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Assert.assertEquals(format.format(dataEsperada), format.format(dataReal));

    }
    @Então("a pontuação recebida será de {int} pontos")
    public void aPontuaçãoRecebidaSeráDePontos(int int1) {
        Assert.assertEquals(int1, notaAluguel.getPontuacao());
    }
}
