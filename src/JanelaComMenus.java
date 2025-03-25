import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.*;
import java.util.Random;


public class JanelaComMenus extends JFrame {
    private DefaultListModel<String> listaModel;
    private JList<String> listaCorredores;
    private JTextField campoNome, campoNumero, campoAno, campoEquipe, campoTelefone;
    private JRadioButton radioMasculino, radioFeminino;
    private ButtonGroup grupoSexo;
    private String tokenGerado = null;
    private static final String ARQUIVO_SQL = "parque.sql";

    public JanelaComMenus() {
        setTitle("BinaByte - Softwares");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

//------------- AQUI FICA O MENU SUPERIOR COM AS OPÇÕES -------------
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem menuAbrir = new JMenuItem("Abrir");
        JMenuItem menuSalvar = new JMenuItem("Salvar");
        JMenuItem menuSair = new JMenuItem("Sair");

        menuAbrir.addActionListener(e -> JOptionPane.showMessageDialog(this, "Abrindo arquivo..."));
        menuSalvar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Salvando arquivo..."));
        menuSair.addActionListener(e -> System.exit(0));

        menuArquivo.add(menuAbrir);
        menuArquivo.add(menuSalvar);
        menuArquivo.addSeparator();
        menuArquivo.add(menuSair);

        JMenu menuFerramenta = new JMenu("Ferramenta");
        JMenuItem menuOp1 = new JMenuItem("Opção 1");
        JMenuItem menuOp2 = new JMenuItem("Opção 2");

        menuOp1.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opção 1 selecionada!"));
        menuOp2.addActionListener(e -> JOptionPane.showMessageDialog(this, "Opção 2 selecionada!"));

        menuFerramenta.add(menuOp1);
        menuFerramenta.add(menuOp2);

        JMenu menuConfiguracao = new JMenu("Configuração");
        JMenuItem menuPreferencias = new JMenuItem("Preferências");
        JMenuItem menuAjustes = new JMenuItem("Ajustes");

        menuPreferencias.addActionListener(e -> JOptionPane.showMessageDialog(this, "Preferências abertas!"));
        menuAjustes.addActionListener(e -> JOptionPane.showMessageDialog(this, "Ajustes realizados!"));

        menuConfiguracao.add(menuPreferencias);
        menuConfiguracao.add(menuAjustes);

        JMenu menuToken = new JMenu("Token");
        JMenuItem menuGerarToken = new JMenuItem("Gerar Token");
        JMenuItem menuValidarToken = new JMenuItem("Validar Token");

        menuGerarToken.addActionListener(e -> {
            tokenGerado = gerarTokenAleatorio();
            JOptionPane.showMessageDialog(this, "Token gerado: " + tokenGerado);
        });

        menuValidarToken.addActionListener(e -> {
            if (tokenGerado == null) {
                JOptionPane.showMessageDialog(this, "Nenhum token foi gerado ainda!");
            } else {
                String tokenDigitado = JOptionPane.showInputDialog(this, "Digite o token:");
                if (tokenDigitado != null && tokenDigitado.equals(tokenGerado)) {
                    JOptionPane.showMessageDialog(this, "Token válido!");
                } else {
                    JOptionPane.showMessageDialog(this, "Token inválido!");
                }
            }
        });

        menuToken.add(menuGerarToken);
        menuToken.add(menuValidarToken);

        menuBar.add(menuArquivo);
        menuBar.add(menuFerramenta);
        menuBar.add(menuConfiguracao);
        menuBar.add(menuToken);

        setJMenuBar(menuBar);


//------------- AQUI COMEÇA O FORM QUE VAI SER PREENCHIDO --------------------        
        JPanel painel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        campoNome = new JTextField(10);
        campoNumero = new JTextField(10);
        campoAno = new JTextField(10);
        campoEquipe = new JTextField(10);
        campoTelefone = new JTextField(10);

        radioMasculino = new JRadioButton("Masculino");
        radioFeminino = new JRadioButton("Feminino");
        grupoSexo = new ButtonGroup();
        grupoSexo.add(radioMasculino);
        grupoSexo.add(radioFeminino);

        formPanel.add(new JLabel("Nome:"));
        formPanel.add(campoNome);
        formPanel.add(new JLabel("Número:"));
        formPanel.add(campoNumero);
        formPanel.add(new JLabel("Ano:"));
        formPanel.add(campoAno);
        formPanel.add(new JLabel("Sexo:"));

        JPanel sexoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sexoPanel.add(radioMasculino);
        sexoPanel.add(radioFeminino);
        formPanel.add(sexoPanel);

        formPanel.add(new JLabel("Equipe:"));
        formPanel.add(campoEquipe);
        formPanel.add(new JLabel("Telefone:"));
        formPanel.add(campoTelefone);

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.addActionListener(this::adicionarCorredor);

        JPanel botaoPanel = new JPanel();
        botaoPanel.add(botaoAdicionar);

        listaModel = new DefaultListModel<>();
        listaCorredores = new JList<>(listaModel);
        JScrollPane scrollPane = new JScrollPane(listaCorredores);

        painel.add(formPanel, BorderLayout.NORTH);
        painel.add(botaoPanel, BorderLayout.CENTER);
        painel.add(scrollPane, BorderLayout.SOUTH);

        carregarCorredores();
        add(painel);
        setVisible(true);
    }

    private void adicionarCorredor(ActionEvent e) {
        String nome = campoNome.getText();
        String numero = campoNumero.getText();
        String ano = campoAno.getText();
        String sexo = radioMasculino.isSelected() ? "M" : (radioFeminino.isSelected() ? "F" : "N/A");
        String equipe = campoEquipe.getText();
        String telefone = campoTelefone.getText();

        if (nome.isEmpty() || numero.isEmpty() || ano.isEmpty() || equipe.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = String.format("INSERT INTO corredores (nome, numero, ano, sexo, equipe, telefone) VALUES ('%s', '%s', '%s', '%s', '%s', '%s');\n",
                nome, numero, ano, sexo, equipe, telefone);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_SQL, true))) {
            writer.write(sql);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar no arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        limparCampos();
        carregarCorredores();
    }

    private void carregarCorredores() {
        listaModel.clear();
        if (!Files.exists(Paths.get(ARQUIVO_SQL))) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_SQL))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("INSERT INTO corredores")) {
                    String[] partes = linha.split("VALUES")[1].replaceAll("[()';]", "").split(",");
                    listaModel.addElement(String.join(", ", partes).trim());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoNome.setText("");
        campoNumero.setText("");
        campoAno.setText("");
        grupoSexo.clearSelection();
        campoEquipe.setText("");
        campoTelefone.setText("");
    }

    private String gerarTokenAleatorio() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    public static void main(String[] args) {
        new JanelaComMenus();
    }
}
