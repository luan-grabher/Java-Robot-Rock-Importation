package rock_importacao;

import Entity.Executavel;
import LctoTemplate.CfgBancoTemplate;
import Robo.AppRobo;
import TemplateContabil.Control.ControleTemplates;
import TemplateContabil.Model.Entity.CfgTipoLctosBancoModel;
import java.util.ArrayList;
import java.util.List;


public class Rock_Importacao {
    private static String nome;

    public static void main(String[] args) {
        nome = "Rock Informatica Importacao";
        AppRobo robo = new AppRobo(nome);
        
        robo.definirParametros();
        
        int mes = robo.getParametro("mes").getMes();
        int ano = robo.getParametro("ano").getInteger();
        
        nome += " " + mes + "/" + ano;
        robo.setNome(nome);
        
        robo.executar(
                principal(mes, ano)
        );
    }
    
    public static String principal(int mes, int ano){
        try {
            int empresa = 14;
            String pathNameEmpresa= "Rock Internet e Processamento de Dados S.A";
            
            ControleTemplates controle = new ControleTemplates(mes, ano, empresa, pathNameEmpresa);
            controle.definirFilesAndPaths();
            
            CfgTipoLctosBancoModel cfgLctosFinanceiro = new CfgTipoLctosBancoModel();
            
            cfgLctosFinanceiro.setTIPO(CfgTipoLctosBancoModel.TIPO_EXCEL);
            cfgLctosFinanceiro.setExcel_colunaData("N");
            cfgLctosFinanceiro.setExcel_colunaDoc("");
            cfgLctosFinanceiro.setExcel_colunaPreTexto("");
            cfgLctosFinanceiro.setExcel_colunaComplementoHistorico("E;C;D;I;O");
            cfgLctosFinanceiro.setExcel_colunaEntrada("F");
            cfgLctosFinanceiro.setExcel_colunaSaida("-G");
            
            CfgBancoTemplate cfgBancoSicredi = new CfgBancoTemplate();
            cfgBancoSicredi.setNomeBanco("Sicredi");
            cfgBancoSicredi.setContaBanco(716);
            cfgBancoSicredi.setEmpresa(empresa);
            cfgBancoSicredi.setFiltroNomeArquivoOriginal("Extrato;Financeiro;.xlsx");
            cfgBancoSicredi.setFiltrarMesAno(false);
            
            List<Executavel> executaveis = new ArrayList<>();
            executaveis.add(controle.new definirFileTemplatePadrao());
            executaveis.add(controle.new importacaoPadraoBanco(cfgBancoSicredi, cfgLctosFinanceiro));
            
            return AppRobo.rodarExecutaveis(nome, executaveis);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro inesperado no Java: " + e;
        }
    }
}
