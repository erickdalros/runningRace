import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class JanelaComMenus extends JFrame {

    private String tokenGerado = null; // Armazena o token gerado

    public JanelaComMenus() {
        setTitle("BinaByte - Softwares");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criando a barra de menu
        JMenuBar menuBar = new JMenuBar();

        // Menu "Arquivo"
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem menuAbrir = new JMenuItem("Abrir");
        JMenuItem menuSalvar = new JMenuItem("Salvar");
        JMenuItem menuSair = new JMenuItem("Sair");

        // Ação para "Abrir"
        menuAbrir.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this, "Arquivo selecionado: " + fileChooser.getSelectedFile().getName());
            }
        });

        // Ação para "Salvar"
        menuSalvar.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int resultado = fileChooser.showSaveDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                JOptionPane.showMessageDialog(this, "Arquivo salvo em: " + fileChooser.getSelectedFile().getName());
            }
        });

        // Ação para "Sair"
        menuSair.addActionListener(e -> System.exit(0));

        menuArquivo.add(menuAbrir);
        menuArquivo.add(menuSalvar);
        menuArquivo.addSeparator();
        menuArquivo.add(menuSair);

        // Menu "Ferramenta"
        JMenu menuFerramenta = new JMenu("Ferramenta");
        JMenuItem menuOp1 = new JMenuItem("Opção 1");
        JMenuItem menuOp2 = new JMenuItem("Opção 2");

        // Ação para "Opção 1"
        menuOp1.addActionListener(e -> JOptionPane.showMessageDialog(this, "Você clicou em Opção 1!"));

        // Ação para "Opção 2"
        menuOp2.addActionListener(e -> JOptionPane.showMessageDialog(this, "Você clicou em Opção 2!"));

        menuFerramenta.add(menuOp1);
        menuFerramenta.add(menuOp2);

        // Menu "Configuração"
        JMenu menuConfiguracao = new JMenu("Configuração");
        JMenuItem menuPreferencias = new JMenuItem("Preferências");
        JMenuItem menuAjustes = new JMenuItem("Ajustes");

        // Ação para "Preferências"
        menuPreferencias.addActionListener(e -> JOptionPane.showMessageDialog(this, "Configurações de preferências!"));

        // Ação para "Ajustes"
        menuAjustes.addActionListener(e -> JOptionPane.showMessageDialog(this, "Ajustes realizados!"));

        menuConfiguracao.add(menuPreferencias);
        menuConfiguracao.add(menuAjustes);

        // Menu "Token"
        JMenu menuToken = new JMenu("Token");
        JMenuItem menuGerarToken = new JMenuItem("Gerar Token");
        JMenuItem menuValidarToken = new JMenuItem("Validar Token");

        // Ação para "Gerar Token"
        menuGerarToken.addActionListener(e -> {
            tokenGerado = gerarTokenAleatorio();
            JOptionPane.showMessageDialog(this, "Token gerado: " + tokenGerado);
        });

        // Ação para "Validar Token"
        menuValidarToken.addActionListener(e -> {
            if (tokenGerado == null) {
                JOptionPane.showMessageDialog(this, "Nenhum token foi gerado ainda!");
            } else {
                String tokenDigitado = JOptionPane.showInputDialog(this, "Digite o token para validação:");
                if (tokenDigitado != null && tokenDigitado.equals(tokenGerado)) {
                    JOptionPane.showMessageDialog(this, "Token válido!");
                } else {
                    JOptionPane.showMessageDialog(this, "Token inválido!");
                }
            }
        });

        menuToken.add(menuGerarToken);
        menuToken.add(menuValidarToken);

        // Adicionando os menus à barra de menu
        menuBar.add(menuArquivo);
        menuBar.add(menuFerramenta);
        menuBar.add(menuConfiguracao);
        menuBar.add(menuToken);

        // Definindo a barra de menu na janela
        setJMenuBar(menuBar);

        setVisible(true);
    }

    // Método para gerar um token aleatório
    private String gerarTokenAleatorio() {
        Random random = new Random();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            token.append(random.nextInt(10)); // Gera um número de 0 a 9
        }
        return token.toString();
    }

    public static void main(String[] args) {
        new JanelaComMenus();
    }
}