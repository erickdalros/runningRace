import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class JanelaComMenus extends JFrame {
    //Criando variáveis do projeto
    private String tokenGerado = null; // Armazena o token gerado
    private JTextField CampoNumero;
    private JButton botaoConfirmar;



    public JanelaComMenus() {
        setTitle("BinaByte - Softwares");
        setSize(1500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

                                        /* CRIANDO O MENU DE OPÇÕES DE CIMA DA JANELA */
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


                                        /* PAINEL CENTRAL DE INFORMAÇÕES */
        //Criação da localização dele
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(null);
        painelCentral.setBorder(BorderFactory.createTitledBorder("Cadastro do Corredor"));
        painelCentral.setBackground(Color.WHITE);
        painelCentral.setLayout(new GridLayout(10, 10, 10, 10));

        //título dele
        JLabel titulo = new JLabel("Cadastro de Corredor");
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);


        // Formulário para cadastro
        JLabel labelNumero = new JLabel("Número do Corredor:");
        labelNumero.setFont(new Font("Arial", Font.BOLD, 20));
        labelNumero.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto do label

        CampoNumero = new JTextField(10);
        CampoNumero.setHorizontalAlignment(JTextField.CENTER); // Centraliza o texto dentro do campo
        CampoNumero.setPreferredSize(new Dimension(100, 20));


        // Botão Confirmar
        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.addActionListener(e -> SalvarDados());



        // Adicionando os componentes ao painel
        painelCentral.add(labelNumero);
        painelCentral.add(CampoNumero);
        painelCentral.add(new JLabel()); // Espaço vazio
        painelCentral.add(botaoConfirmar);

        // Adicionando componentes ao JFrame
        add(titulo, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);




        /* VISIBILIDADE TRUE DA JANELA */
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

    //Cadastro de corredor
    private void SalvarDados(){
        String numero = CampoNumero.getText();

        //Validar se todos os campos foram preenchidos
        if (numero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos para continuar!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Criando o arquivo .txt
        String conteudo = "Número: " + numero;

        //Salvando em um arquivo .txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Parque.txt", true))){
            writer.write(conteudo);
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            limparCampos();
        }catch (IOException ex){
            JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos(){
        CampoNumero.setText("");
    }
    public static void main(String[] args) {
        new JanelaComMenus();
    }
}