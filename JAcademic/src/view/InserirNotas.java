package view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import models.AcademicDAO;
import models.Aluno;

public class InserirNotas extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldAv1_1;
	private JTextField textFieldAv2_1;
	private JTextField textFieldRecuperacao1;
	private JTextField textFieldSituacao1;
	private JLabel lbInformacoes;
	private JButton btnSalvar;
	private JTextField textFieldMatricula1;
	private JTextField textFieldAluno1;	
	private Aluno alunoEmExibicao;
	private int _matriculaDoALuno;
	private JButton btnVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try 
				{					
					InserirNotas frame = new InserirNotas(5);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	*/
	
	ImageIcon ifpe = new ImageIcon(getClass().getResource("jacademicMenor.png"));
	private JLabel lblLogo;
	
	public InserirNotas(int matriculaDoALuno) {		
		
		super("JAcademic");
		
		_matriculaDoALuno = matriculaDoALuno;
		
		alunoEmExibicao = AcademicDAO.getInstance().findByMatricula(_matriculaDoALuno);
		
		setBackground(new Color(0, 255, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(900, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#010024"));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);	
		
		
		JLabel lblNewLabel = new JLabel("Notas e Conceitos Acadêmicos");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 42));
		lblNewLabel.setBounds(195, 46, 551, 73);
		contentPane.add(lblNewLabel);
		
		lbInformacoes = new JLabel("   Matrícula                     Aluno                             UNID1        UNID2        REC.        Situação");
		lbInformacoes.setForeground(new Color(255, 255, 255));
		lbInformacoes.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		lbInformacoes.setBounds(55, 252, 781, 31);
		lbInformacoes.setOpaque(true);
		lbInformacoes.setBackground(Color.decode("#0A6FC7"));
		contentPane.add(lbInformacoes);
		
		btnSalvar = new JButton("Salvar");			
		btnSalvar.setBackground(Color.decode("#0A6FC7"));
		btnSalvar.setForeground(new Color(255, 255, 255));
		btnSalvar.setFont(new Font("Calibri", Font.BOLD, 16));		
		btnSalvar.setBounds(318, 585, 122, 45);
		btnSalvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
										
				alunoEmExibicao.getMatricula();
				alunoEmExibicao.setPrimeira_nota(Double.parseDouble(textFieldAv1_1.getText()));
				alunoEmExibicao.setSegunda_nota(Double.parseDouble(textFieldAv2_1.getText()));
				alunoEmExibicao.setNota_recuperacao(Double.parseDouble(textFieldRecuperacao1.getText()));
				alunoEmExibicao.setSituacao(textFieldSituacao1.getText());
				
				AcademicDAO.getInstance().InserirNota(alunoEmExibicao);
				
				new ListaDeAlunos().setVisible(true);
				dispose();
			}
		});		
		
		contentPane.add(btnSalvar);						
		
		CreateViewModelAluno();
	}
	
	private void CreateViewModelAluno() 
	{				
		
		textFieldMatricula1 = new JTextField();
		textFieldMatricula1.setEditable(false);
		textFieldMatricula1.setBounds(55, 285, 115, 31);
		textFieldMatricula1.setText(""+ alunoEmExibicao.getMatricula());		
		textFieldMatricula1.setColumns(10);
		contentPane.add(textFieldMatricula1);
		
		textFieldAluno1 = new JTextField();
		textFieldAluno1.setEditable(false);
		textFieldAluno1.setBounds(171, 285, 268, 31);
		textFieldAluno1.setText(alunoEmExibicao.getNome());
		textFieldAluno1.setColumns(10);
		
		contentPane.add(textFieldAluno1);		
		
		textFieldAv1_1 = new JTextField();
		textFieldAv1_1.setForeground(new Color(0, 0, 0));
		textFieldAv1_1.setBounds(439, 285, 93, 31);
		textFieldAv1_1.setText(""+ alunoEmExibicao.getPrimeira_nota());	
		textFieldAv1_1.setColumns(10);
		contentPane.add(textFieldAv1_1);
		
		textFieldAv2_1 = new JTextField();
		textFieldAv2_1.setForeground(Color.BLACK);
		textFieldAv2_1.setColumns(10);
		textFieldAv2_1.setBounds(530, 285, 86, 31);
		textFieldAv2_1.setText(""+ alunoEmExibicao.getSegunda_nota());
		contentPane.add(textFieldAv2_1);
		
		textFieldRecuperacao1 = new JTextField();
		textFieldRecuperacao1.setForeground(Color.BLACK);
		textFieldRecuperacao1.setColumns(10);
		textFieldRecuperacao1.setBounds(616, 285, 80, 31);
		textFieldRecuperacao1.setText(""+ alunoEmExibicao.getNota_recuperacao());
		contentPane.add(textFieldRecuperacao1);
		
		textFieldSituacao1 = new JTextField();
		textFieldSituacao1.setEditable(false);
		textFieldSituacao1.setForeground(Color.BLACK);
		textFieldSituacao1.setColumns(10);
		textFieldSituacao1.setBounds(695, 285, 141, 31);
		textFieldSituacao1.setText(""+ alunoEmExibicao.getSituacao());
		contentPane.add(textFieldSituacao1);
		
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new ListaDeAlunos().setVisible(true);
				dispose();
			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Calibri", Font.BOLD, 16));
		btnVoltar.setBackground(Color.decode("#0A6FC7"));
		btnVoltar.setBounds(471, 585, 122, 45);
		contentPane.add(btnVoltar);
		
		lblLogo = new JLabel(ifpe);
		lblLogo.setBounds(78, 0, 104, 120);
		contentPane.add(lblLogo);
	}	

}
