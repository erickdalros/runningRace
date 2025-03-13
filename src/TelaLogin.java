/*import javax.swing.*;

public class TelaLogin extends JFrame {

    public TelaLogin() {
        setTitle("Autenticação");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Campo de senha
        JLabel lblSenha = new JLabel("Código:");
        lblSenha.setBounds(20, 20, 80, 25);
        add(lblSenha);

        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(100, 20, 150, 25);
        add(campoSenha);

        // Botão para verificar o código
        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBounds(100, 60, 100, 25);
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            String senhaCorreta = "12345"; // Código correto
            String senhaDigitada = new String(campoSenha.getPassword());

            if (senhaDigitada.equals(senhaCorreta)) {
                JOptionPane.showMessageDialog(this, "Acesso Permitido!");
                new InterfaceCompleta(senhaDigitada); // Passa o código para abrir a tela principal
                dispose(); // Fecha a tela de login
            } else {
                JOptionPane.showMessageDialog(this, "Código incorreto!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}

 */
