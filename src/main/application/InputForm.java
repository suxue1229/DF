package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import engine.EIon;
import engine.MSystem;
import java.awt.Label;
import java.awt.Font;

public class InputForm extends JFrame {

	private JTextField textFieldProName;
	private JTextField textFieldDesigner;
	private JTextField textFieldProcess;
	private JTable tableIon;
	private JTextField TextFieldOsmoticPressure;
	private JTextField TextFieldIon;
	private JTextField TextFieldconductivity;
	private JTextField textFieldTemperature;
	private JTextField textFieldpH;
	private JTextField textFieldTDS;
	private JTextField textFieldalkalinity;

	private JTable tablesystem;
	JScrollPane scrollPane;
	JPanel panelWater, jp1;
	private JTextField textFieldsections;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	java.text.DecimalFormat df2 = new java.text.DecimalFormat("0.00");
	MSystem msystem;
	private JTextField textFieldDate, TextField_CaCO3, TextField_BaSO4, TextField_CaSO4, TextField_CaF2, TextField_Ca3,
			TextField_SrSO4, textFieldCOD, textFieldsection, TextFieldpariJ, TextFieldpariYD, textFieldpariY,
			textFieldparEi, textFieldparPpi, textFieldparNVi, textFieldparDpi, textFieldparPLi, TextFieldpariQf,
			textFieldpariQp, textFieldpariQr, TextFieldpariQc;
	private JLabel lblMpa_MPa2, lblMpa_2, lblMpa_MPa0;
	private JPanel panelIon;
	private JLabel jrbCOD;
	private JLabel jrbOther;
	private JLabel jrbTOC;
	private JTextField textFieldCOD_ROD;
	private JTextField textFieldTOC_ROD;
	private JTextField textFieldTOC;
	private JTextField textFieldother_ROD;
	private JTextField textFieldother;
	private GridBagConstraints gbc_labelTOC;
	private GridBagConstraints gbc_textFieldTOC;
	private GridBagConstraints gbc_label_TOC_mgl;
	private GridBagConstraints gbc_textFieldother_ROD;
	private GridBagConstraints gbc_labelother;
	private GridBagConstraints gbc_label_other_mgl;
	private GridBagConstraints gbc_label4_1;
	private JTextField textField4_1_1;
	private JTextField textField4_1_2;
	private JTextField textField4_1_3;
	private JTextField textField4_2_1;
	private JTextField textField4_2_2;
	private JTextField textField4_2_3;
	private JTextField textField4_3_1;
	private JTextField textField4_3_2;
	private JTextField textField4_3_3;
	private JTextField textField4_4_1;
	private JTextField textField4_4_2;
	private JTextField textField4_4_3;
	private JTextField textField4_5_1;
	private JTextField textField4_6_1;

	/**
	 * Create the application.
	 * 
	 * @throws Exception
	 */
	public InputForm() {
		try {
			msystem = new MSystem();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws Exception
	 */
	private void initialize() {
		setTitle("DF工艺设计");
		setSize(666, 500);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2;
		setLocation(w, h);
		setResizable(false); // 禁止最大化及缩放功能
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel_1 = new JPanel();
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 500, 40, 30, 25 };
		gbl_panel_1.rowHeights = new int[] { 430, 45, 20 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gbl_panel_1.rowWeights = new double[] { 1.0, 1.0, 1.0 };
		panel_1.setLayout(gbl_panel_1);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane_1 = new GridBagConstraints();
		gbc_tabbedPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane_1.gridwidth = 4;
		gbc_tabbedPane_1.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane_1.gridx = 0;
		gbc_tabbedPane_1.gridy = 0;
		panel_1.add(tabbedPane, gbc_tabbedPane_1);

		JButton button = new JButton("计算");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panel_1.add(button, gbc_btnNewButton);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel_1,
				GroupLayout.PREFERRED_SIZE, 594, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel_1,
				GroupLayout.PREFERRED_SIZE, 467, Short.MAX_VALUE));

		/*
		 * panel面板项目信息，包括项目名称、设计者、项目工艺以及日期
		 */
		JPanel panelProcess = new JPanel();
		panelProcess.setBorder(null);
		tabbedPane.addTab("项目信息", null, panelProcess, null);
		tabbedPane.setBorder(null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 60, 80, 80, 100, 200, 138, 60 };
		gbl_panel.rowHeights = new int[] { 30, 10, 10, 30, 30, 30 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_panel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0 };
		panelProcess.setLayout(gbl_panel);

		JLabel labelProName = new JLabel("项目名称");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 3;
		gbc_label_3.gridy = 1;
		panelProcess.add(labelProName, gbc_label_3);
		textFieldProName = new JTextField(msystem.name);
		textFieldProName.setColumns(10);
		textFieldProName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				msystem.name = textFieldProName.getText();// 项目名称
			}
		});
		textFieldProName.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					msystem.name = textFieldProName.getText();// 项目名称
				}
			}
		});
		GridBagConstraints gbc_textField4_1_1 = new GridBagConstraints();
		gbc_textField4_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField4_1_1.gridx = 4;
		gbc_textField4_1_1.gridy = 1;
		panelProcess.add(textFieldProName, gbc_textField4_1_1);

		JLabel labelDesigner = new JLabel("设计者");
		GridBagConstraints gbc_label4_1_1 = new GridBagConstraints();
		gbc_label4_1_1.anchor = GridBagConstraints.WEST;
		gbc_label4_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_label4_1_1.gridx = 3;
		gbc_label4_1_1.gridy = 2;
		panelProcess.add(labelDesigner, gbc_label4_1_1);
		textFieldDesigner = new JTextField(msystem.designer);
		textFieldDesigner.setColumns(10);
		textFieldDesigner.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				msystem.designer = textFieldDesigner.getText();// 设计者
			}
		});
		textFieldDesigner.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					msystem.designer = textFieldDesigner.getText();// 设计者
				}
			}
		});
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 2;
		panelProcess.add(textFieldDesigner, gbc_textField_1);

		JLabel labelProcess = new JLabel("项目工艺");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 3;
		panelProcess.add(labelProcess, gbc_lblNewLabel);
		textFieldProcess = new JTextField(msystem.process);
		textFieldProcess.setColumns(10);
		textFieldProcess.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				msystem.process = textFieldProcess.getText();// 项目工艺
			}
		});
		textFieldProcess.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					msystem.process = textFieldProcess.getText();// 项目工艺
				}
			}
		});
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 3;
		panelProcess.add(textFieldProcess, gbc_textField_2);

		JLabel labelDate = new JLabel("日期");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 3;
		gbc_label_2.gridy = 4;
		panelProcess.add(labelDate, gbc_label_2);
		textFieldDate = new JTextField(sdf.format(msystem.date));
		textFieldDate.setColumns(10);
		textFieldDate.setEnabled(false);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 4;
		gbc_lblNewLabel_2.gridy = 4;
		panelProcess.add(textFieldDate, gbc_lblNewLabel_2);

		/*
		 * panel面板上放置一个JSplitPane，由左侧list可以切换到包括信息表、进水参数文本框以及饱和度表
		 */
		JPanel panel = new JPanel();
		tabbedPane.addTab("水质指标", null, panel, null);

		// panelWater面板上放置进水参数
		panelWater = new JPanel();
		GridBagLayout gbl_jp = new GridBagLayout();
		gbl_jp.columnWidths = new int[] { 10, 20, 40, 100, 60, 40, 100, 10, 20 };
		gbl_jp.rowHeights = new int[] { 12, 30, 30, 30, 30, 30, 30, 30, 30, 47, 0 };
		gbl_jp.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_jp.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panelWater.setLayout(gbl_jp);
		panelWater.setBackground(new Color(240, 240, 240));
		GridBagConstraints gbc_label_9_1 = new GridBagConstraints();
		gbc_label_9_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_label_9_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_9_1.gridx = 4;
		gbc_label_9_1.gridy = 3;
		GridBagConstraints gbc_label_4_1 = new GridBagConstraints();
		gbc_label_4_1.anchor = GridBagConstraints.WEST;
		gbc_label_4_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_4_1.gridx = 1;
		gbc_label_4_1.gridy = 4;
		GridBagConstraints gbc_textField_4_1 = new GridBagConstraints();
		gbc_textField_4_1.anchor = GridBagConstraints.WEST;
		gbc_textField_4_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4_1.gridx = 2;
		gbc_textField_4_1.gridy = 4;
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 3;
		gbc_lblNewLabel_1_1.gridy = 4;
		GridBagConstraints gbc_lblPh_1 = new GridBagConstraints();
		gbc_lblPh_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPh_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPh_1.gridx = 6;
		gbc_lblPh_1.gridy = 4;
		GridBagConstraints gbc_textField_5_1 = new GridBagConstraints();
		gbc_textField_5_1.anchor = GridBagConstraints.WEST;
		gbc_textField_5_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5_1.gridx = 7;
		gbc_textField_5_1.gridy = 4;
		GridBagConstraints gbc_label_5_1 = new GridBagConstraints();
		gbc_label_5_1.anchor = GridBagConstraints.WEST;
		gbc_label_5_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_5_1.gridx = 1;
		gbc_label_5_1.gridy = 5;
		GridBagConstraints gbc_textField_6_1 = new GridBagConstraints();
		gbc_textField_6_1.anchor = GridBagConstraints.WEST;
		gbc_textField_6_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6_1.gridx = 2;
		gbc_textField_6_1.gridy = 5;
		GridBagConstraints gbc_lblUscm_1 = new GridBagConstraints();
		gbc_lblUscm_1.anchor = GridBagConstraints.WEST;
		gbc_lblUscm_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblUscm_1.gridx = 3;
		gbc_lblUscm_1.gridy = 5;
		GridBagConstraints gbc_lblTds_1 = new GridBagConstraints();
		gbc_lblTds_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblTds_1.gridx = 6;
		gbc_lblTds_1.gridy = 5;
		GridBagConstraints gbc_textField_7_1 = new GridBagConstraints();
		gbc_textField_7_1.anchor = GridBagConstraints.WEST;
		gbc_textField_7_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7_1.gridx = 7;
		gbc_textField_7_1.gridy = 5;
		GridBagConstraints gbc_lblMgl_1_1 = new GridBagConstraints();
		gbc_lblMgl_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblMgl_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblMgl_1_1.gridx = 8;
		gbc_lblMgl_1_1.gridy = 5;
		GridBagConstraints gbc_label_6_1 = new GridBagConstraints();
		gbc_label_6_1.anchor = GridBagConstraints.WEST;
		gbc_label_6_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_6_1.gridx = 1;
		gbc_label_6_1.gridy = 6;
		GridBagConstraints gbc_textField_8_1 = new GridBagConstraints();
		gbc_textField_8_1.anchor = GridBagConstraints.WEST;
		gbc_textField_8_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_8_1.gridx = 2;
		gbc_textField_8_1.gridy = 6;
		GridBagConstraints gbc_lblMoll_1 = new GridBagConstraints();
		gbc_lblMoll_1.anchor = GridBagConstraints.WEST;
		gbc_lblMoll_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMoll_1.gridx = 3;
		gbc_lblMoll_1.gridy = 6;
		GridBagConstraints gbc_label_7_1 = new GridBagConstraints();
		gbc_label_7_1.anchor = GridBagConstraints.WEST;
		gbc_label_7_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_7_1.gridx = 6;
		gbc_label_7_1.gridy = 6;
		GridBagConstraints gbc_textField_9_1 = new GridBagConstraints();
		gbc_textField_9_1.anchor = GridBagConstraints.WEST;
		gbc_textField_9_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9_1.gridx = 7;
		gbc_textField_9_1.gridy = 6;
		GridBagConstraints gbc_lblMgl_2 = new GridBagConstraints();
		gbc_lblMgl_2.anchor = GridBagConstraints.WEST;
		gbc_lblMgl_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblMgl_2.gridx = 8;
		gbc_lblMgl_2.gridy = 6;
		GridBagConstraints gbc_label_8_1 = new GridBagConstraints();
		gbc_label_8_1.anchor = GridBagConstraints.WEST;
		gbc_label_8_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_8_1.gridx = 1;
		gbc_label_8_1.gridy = 7;
		GridBagConstraints gbc_textField_10_1 = new GridBagConstraints();
		gbc_textField_10_1.anchor = GridBagConstraints.WEST;
		gbc_textField_10_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_10_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_10_1.gridx = 2;
		gbc_textField_10_1.gridy = 7;
		GridBagConstraints gbc_lblMpa_1 = new GridBagConstraints();
		gbc_lblMpa_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMpa_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMpa_1.gridx = 3;
		gbc_lblMpa_1.gridy = 7;

		JLabel labelTemperature = new JLabel("温度");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 2;
		gbc_label_4.gridy = 2;
		panelWater.add(labelTemperature, gbc_label_4);
		textFieldTemperature = new JTextField(String.format("%.1f", msystem.streams.parT()));
		textFieldTemperature.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					msystem.streams.parT(Double.parseDouble(textFieldTemperature.getText()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
				}
				textFieldTemperature.setText((String.format("%.1f", msystem.streams.parT())));// 温度
			}
		});
		textFieldTemperature.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					try {
						msystem.streams.parT(Double.parseDouble(textFieldTemperature.getText()));
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "错误！",
								JOptionPane.ERROR_MESSAGE);
					}
					textFieldTemperature.setText((String.format("%.1f", msystem.streams.parT())));// 温度
				}
			}
		});
		textFieldTemperature.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 2;
		panelWater.add(textFieldTemperature, gbc_textField_4);
		JLabel lblNewLabel_1 = new JLabel("℃");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 2;
		panelWater.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel labelconductivity = new JLabel("电导率");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 5;
		gbc_label_5.gridy = 2;
		panelWater.add(labelconductivity, gbc_label_5);
		TextFieldconductivity = new JTextField(String.format("%.2f", msystem.streams.parS()));
		TextFieldconductivity.setEnabled(false);
		GridBagConstraints gbc_TextField_51 = new GridBagConstraints();
		gbc_TextField_51.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_51.insets = new Insets(0, 0, 5, 5);
		gbc_TextField_51.gridx = 6;
		gbc_TextField_51.gridy = 2;
		panelWater.add(TextFieldconductivity, gbc_TextField_51);
		JLabel lblUscm = new JLabel("us/cm");
		GridBagConstraints gbc_lblUscm = new GridBagConstraints();
		gbc_lblUscm.anchor = GridBagConstraints.WEST;
		gbc_lblUscm.insets = new Insets(0, 0, 5, 5);
		gbc_lblUscm.gridx = 7;
		gbc_lblUscm.gridy = 2;
		panelWater.add(lblUscm, gbc_lblUscm);

		JLabel labelpH = new JLabel("pH");
		GridBagConstraints gbc_lblPh = new GridBagConstraints();
		gbc_lblPh.anchor = GridBagConstraints.WEST;
		gbc_lblPh.insets = new Insets(0, 0, 5, 5);
		gbc_lblPh.gridx = 2;
		gbc_lblPh.gridy = 4;
		panelWater.add(labelpH, gbc_lblPh);
		textFieldpH = new JTextField(String.format("%.2f", msystem.streams.parpH()));
		textFieldpH.setColumns(10);
		textFieldpH.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try {
					msystem.streams.parpH(Double.parseDouble(textFieldpH.getText()));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
				}
				textFieldpH.setText((String.format("%.2f", msystem.streams.parpH())));// pH
			}
		});
		textFieldpH.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						msystem.streams.parpH(Double.parseDouble(textFieldpH.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(getContentPane(), e2.getMessage(), "错误！",
								JOptionPane.ERROR_MESSAGE);
					}
					textFieldpH.setText((String.format("%.2f", msystem.streams.parpH())));// pH
				}
			}
		});
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.gridx = 3;
		gbc_textField_5.gridy = 4;
		panelWater.add(textFieldpH, gbc_textField_5);

		JLabel labelIonStrength = new JLabel("离子强度");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.WEST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 5;
		gbc_label_6.gridy = 4;
		panelWater.add(labelIonStrength, gbc_label_6);
		TextFieldIon = new JTextField(String.format("%.3f", msystem.streams.paru()));
		TextFieldIon.setEnabled(false);
		GridBagConstraints gbc_TextField_61 = new GridBagConstraints();
		gbc_TextField_61.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_61.insets = new Insets(0, 0, 5, 5);
		gbc_TextField_61.gridx = 6;
		gbc_TextField_61.gridy = 4;
		panelWater.add(TextFieldIon, gbc_TextField_61);
		JLabel lblMoll = new JLabel("mol/L");
		GridBagConstraints gbc_lblMoll = new GridBagConstraints();
		gbc_lblMoll.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblMoll.gridx = 7;
		gbc_lblMoll.gridy = 4;
		panelWater.add(lblMoll, gbc_lblMoll);

		JLabel labelTDS = new JLabel("TDS");
		GridBagConstraints gbc_lblTds = new GridBagConstraints();
		gbc_lblTds.anchor = GridBagConstraints.WEST;
		gbc_lblTds.insets = new Insets(0, 0, 5, 5);
		gbc_lblTds.gridx = 2;
		gbc_lblTds.gridy = 6;
		panelWater.add(labelTDS, gbc_lblTds);
		textFieldTDS = new JTextField(String.format("%.1f", msystem.streams.tds()));
		textFieldTDS.setColumns(10);
		textFieldTDS.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					msystem.streams.tds(Double.parseDouble(textFieldTDS.getText().toString()));
				} catch (Exception e1) {
					// TODO: handle exception
				}
				textFieldTDS.setText(String.format("%.1f", msystem.streams.tds()));
			}
		});
		textFieldTDS.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						msystem.streams.tds(Double.parseDouble(textFieldTDS.getText().toString()));
					} catch (Exception e1) {
						// TODO: handle exception
					}
					textFieldTDS.setText(String.format("%.1f", msystem.streams.tds()));
				}
			}
		});
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.gridx = 3;
		gbc_textField_7.gridy = 6;
		panelWater.add(textFieldTDS, gbc_textField_7);
		JLabel lblMgl_1 = new JLabel("mg/L");
		GridBagConstraints gbc_lblMgl_1 = new GridBagConstraints();
		gbc_lblMgl_1.anchor = GridBagConstraints.WEST;
		gbc_lblMgl_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMgl_1.gridx = 4;
		gbc_lblMgl_1.gridy = 6;
		panelWater.add(lblMgl_1, gbc_lblMgl_1);
		JLabel labelalkalinity = new JLabel("碱度");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.WEST;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 2;
		gbc_label_7.gridy = 8;
		panelWater.add(labelalkalinity, gbc_label_7);

		textFieldalkalinity = new JTextField(String.format("%.1f", msystem.streams.parcjd()));
		textFieldalkalinity.setColumns(10);
		textFieldalkalinity.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					msystem.streams.parcjd(Double.parseDouble(textFieldalkalinity.getText()));
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textFieldalkalinity.setText(String.format("%.1f", msystem.streams.parcjd()));
			}
		});
		textFieldalkalinity.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					try {
						msystem.streams.parcjd(Double.parseDouble(textFieldalkalinity.getText()));
					} catch (Exception e2) {
						// TODO: handle exception
					}
					textFieldalkalinity.setText(String.format("%.1f", msystem.streams.parcjd()));
				}
			}
		});
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.insets = new Insets(0, 0, 5, 5);
		gbc_textField_9.gridx = 3;
		gbc_textField_9.gridy = 8;
		panelWater.add(textFieldalkalinity, gbc_textField_9);
		JLabel lblMgl = new JLabel("mg/L");
		GridBagConstraints gbc_lblMgl = new GridBagConstraints();
		gbc_lblMgl.anchor = GridBagConstraints.WEST;
		gbc_lblMgl.insets = new Insets(0, 0, 5, 5);
		gbc_lblMgl.gridx = 4;
		gbc_lblMgl.gridy = 8;
		panelWater.add(lblMgl, gbc_lblMgl);
		JLabel labelOsmoticPressure = new JLabel("渗透压");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.WEST;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 5;
		gbc_label_8.gridy = 6;
		panelWater.add(labelOsmoticPressure, gbc_label_8);
		TextFieldOsmoticPressure = new JTextField(String.format("%.2f", msystem.streams.parpif()));
		TextFieldOsmoticPressure.setEnabled(false);
		GridBagConstraints gbc_TextField_81 = new GridBagConstraints();
		gbc_TextField_81.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_81.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_81.insets = new Insets(0, 0, 5, 5);
		gbc_TextField_81.gridx = 6;
		gbc_TextField_81.gridy = 6;
		panelWater.add(TextFieldOsmoticPressure, gbc_TextField_81);
		JLabel lblMpa = new JLabel("MPa");
		GridBagConstraints gbc_lblMpa = new GridBagConstraints();
		gbc_lblMpa.anchor = GridBagConstraints.WEST;
		gbc_lblMpa.insets = new Insets(0, 0, 5, 5);
		gbc_lblMpa.gridx = 7;
		gbc_lblMpa.gridy = 6;
		panelWater.add(lblMpa, gbc_lblMpa);

		// 离子信息表,饱和度
		panelIon = new JPanel();
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[] { 20, 1, 60, 40, 30, 60, 1, 40, 12 };
		gbl_panel1.rowHeights = new int[] { 18, 1, 220, 12, 1, 12, 12, 12, 8 };
		gbl_panel1.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_panel1.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panelIon.setLayout(gbl_panel1);

		String[] name = { "阳离子", "质量浓度(mg/L)", "摩尔浓度(mmol/L)", "当量浓度(meq/L)", "阴离子", "质量浓度(mg/L)", "摩尔浓度(mmol/L)",
				"当量浓度(meq/L)" };
		Object[][] data = new Object[12][8];
		TableModel tablemodel = new DefaultTableModel(data, name) {
			public boolean isCellEditable(int row, int column) {
				if (column == 0 || column == 4 || row == 11 || row > 5 && column > 4) {
					return false;
				} else
					return true;
			}
		};

		tableIon = new JTable(tablemodel);
		JTableHeader heade = tableIon.getTableHeader();
		heade.setPreferredSize(new Dimension(heade.getWidth(), 20));// 表头高度设置
		JScrollPane scrollPane = new JScrollPane(tableIon);
		tableIon.getTableHeader().setReorderingAllowed(false);
		scrollPane.setPreferredSize(new Dimension(520, 10));
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 10;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 2;
		panelIon.add(scrollPane, gbc_scrollPane_1);

		TextField_CaCO3 = new JTextField(String.format("%.2f", msystem.streams.parSCaCO3()));
		TextField_CaCO3.setEnabled(false);
		GridBagConstraints gbc_TextField_CaCO3 = new GridBagConstraints();
		gbc_TextField_CaCO3.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_CaCO3.insets = new Insets(0, 0, 5, 5);
		gbc_TextField_CaCO3.gridx = 2;
		gbc_TextField_CaCO3.gridy = 5;
		panelIon.add(TextField_CaCO3, gbc_TextField_CaCO3);

		TextField_BaSO4 = new JTextField(String.format("%.2f", msystem.streams.parSBaSO4() * 100));
		TextField_BaSO4.setEnabled(false);
		GridBagConstraints gbc_TextField_BaSO4 = new GridBagConstraints();
		gbc_TextField_BaSO4.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_BaSO4.insets = new Insets(0, 0, 5, 5);
		gbc_TextField_BaSO4.gridx = 5;
		gbc_TextField_BaSO4.gridy = 5;
		panelIon.add(TextField_BaSO4, gbc_TextField_BaSO4);

		TextField_CaSO4 = new JTextField(String.format("%.2f", msystem.streams.parSCaSO4() * 100));
		TextField_CaSO4.setEnabled(false);
		GridBagConstraints gbc_TextField_CaSO4 = new GridBagConstraints();
		gbc_TextField_CaSO4.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_CaSO4.insets = new Insets(0, 0, 5, 5);
		gbc_TextField_CaSO4.gridx = 2;
		gbc_TextField_CaSO4.gridy = 6;
		panelIon.add(TextField_CaSO4, gbc_TextField_CaSO4);

		TextField_SrSO4 = new JTextField(String.format("%.2f", msystem.streams.parSSrSO4() * 100));
		TextField_SrSO4.setEnabled(false);
		GridBagConstraints gbc_TextField_SrSO4 = new GridBagConstraints();
		gbc_TextField_SrSO4.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_SrSO4.insets = new Insets(0, 0, 5, 5);
		gbc_TextField_SrSO4.gridx = 5;
		gbc_TextField_SrSO4.gridy = 6;
		panelIon.add(TextField_SrSO4, gbc_TextField_SrSO4);

		TextField_Ca3 = new JTextField(String.format("%.2f", msystem.streams.parSCa3PO42(msystem.temp) * 100));
		TextField_Ca3.setEnabled(false);
		GridBagConstraints gbc_TextField_Ca3 = new GridBagConstraints();
		gbc_TextField_Ca3.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_Ca3.insets = new Insets(0, 0, 0, 5);
		gbc_TextField_Ca3.gridx = 2;
		gbc_TextField_Ca3.gridy = 7;
		panelIon.add(TextField_Ca3, gbc_TextField_Ca3);

		TextField_CaF2 = new JTextField(String.format("%.2f", msystem.streams.parSCaF2() * 100));
		TextField_CaF2.setEnabled(false);
		GridBagConstraints gbc_TextField_CaF2 = new GridBagConstraints();
		gbc_TextField_CaF2.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextField_CaF2.insets = new Insets(0, 0, 0, 5);
		gbc_TextField_CaF2.gridx = 5;
		gbc_TextField_CaF2.gridy = 7;
		panelIon.add(TextField_CaF2, gbc_TextField_CaF2);

		String[] cation = { "K+", "Na+", "NH4(以N计)", "Ca2+", "Mg2+", "Ba2+", "Sr2+", "Fe2+", "Mn2+", "Fe3+", "Al3+",
				"总计" };
		String[] anion = { "NO3(以N计)", "F-", "Cl-", "HCO3-", "(SO4)2-", "总磷P", "", "", "", "", "", "总计" };
		for (int i = 0; i < tableIon.getRowCount(); i++) {
			tablemodel.setValueAt(cation[i], i, 0);
			tablemodel.setValueAt(anion[i], i, 4);
		}
		// 离子表格初始化
		for (int i = 0; i < 11; i++) {
			tableIon.setValueAt(df2.format(msystem.streams.ion(EIon.values()[i]).parcj()), i, 1);
			tableIon.setValueAt(df2.format(msystem.streams.ion(EIon.values()[i]).parmj() * 1000), i, 2);
			tableIon.setValueAt(df2.format(msystem.streams.ion(EIon.values()[i]).parzj()), i, 3);
		}
		for (int i = 11; i < EIon.values().length - 3; i++) {
			tableIon.setValueAt(df2.format(msystem.streams.ion(EIon.values()[i]).parcj()), i - 11, 5);
			tableIon.setValueAt(df2.format(msystem.streams.ion(EIon.values()[i]).parmj() * 1000), i - 11, 6);
			tableIon.setValueAt(df2.format(msystem.streams.ion(EIon.values()[i]).parzj()), i - 11, 7);
		}
		tableIon.setValueAt(df2.format(msystem.streams.parqC()), 11, 3);
		tableIon.setValueAt(df2.format(msystem.streams.parqA()), 11, 7);
		tableIon.setCellSelectionEnabled(true);// 使得表格的选取以cell为单位而不是以列为单位
		tableIon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				int row = tableIon.getSelectedRow();
				int col = tableIon.getSelectedColumn();

				if (row < 11 && col < 4 && col > 0 || row < 6 && col > 4) {
					if (m.getClickCount() == 2) {
						tableIon.setValueAt("", row, col);
					}

					tableIon.getCellEditor(row, col).addCellEditorListener(new CellEditorListener() {
						@Override
						public void editingStopped(ChangeEvent e) {
							try {
								if (Double.parseDouble(
										tableIon.getValueAt(tableIon.getSelectedRow(), tableIon.getSelectedColumn())
												.toString()) < 0) {
									JOptionPane.showMessageDialog(getContentPane(), "请输入不小于0的数值", "错误！",
											JOptionPane.ERROR_MESSAGE);
									tableIon.setValueAt(df2.format(0), tableIon.getSelectedRow(),
											tableIon.getSelectedColumn());

								}
							} catch (Exception e2) {
								tableIon.setValueAt(df2.format(0), tableIon.getSelectedRow(),
										tableIon.getSelectedColumn());
							}
							if (tableIon.getSelectedColumn() == 1) {
								msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()])
										.parcj(Double.parseDouble(tableIon
												.getValueAt(tableIon.getSelectedRow(), tableIon.getSelectedColumn())
												.toString()));
								tableIon.getModel()
										.setValueAt(df2.format(
												msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()]).parcj()),
										tableIon.getSelectedRow(), 1);
								tableIon.getModel().setValueAt(df2.format(
										msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()]).parmj() * 1000),
										tableIon.getSelectedRow(), 2);
								tableIon.getModel()
										.setValueAt(df2.format(
												msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()]).parzj()),
										tableIon.getSelectedRow(), 3);
							} else if (tableIon.getSelectedColumn() == 2) {
								msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()])
										.parmj(Double.parseDouble(tableIon
												.getValueAt(tableIon.getSelectedRow(), tableIon.getSelectedColumn())
												.toString()) / 1000);
								tableIon.getModel()
										.setValueAt(df2.format(
												msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()]).parcj()),
										tableIon.getSelectedRow(), 1);
								tableIon.getModel().setValueAt(df2.format(
										msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()]).parmj() * 1000),
										tableIon.getSelectedRow(), 2);
								tableIon.getModel()
										.setValueAt(df2.format(
												msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()]).parzj()),
										tableIon.getSelectedRow(), 3);
							} else if (tableIon.getSelectedColumn() == 3) {
								msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()])
										.parzj(Double.parseDouble(tableIon
												.getValueAt(tableIon.getSelectedRow(), tableIon.getSelectedColumn())
												.toString()));
								tableIon.getModel()
										.setValueAt(df2.format(
												msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()]).parcj()),
										tableIon.getSelectedRow(), 1);
								tableIon.getModel().setValueAt(df2.format(
										msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()]).parmj() * 1000),
										tableIon.getSelectedRow(), 2);
								tableIon.getModel()
										.setValueAt(df2.format(
												msystem.streams.ion(EIon.values()[tableIon.getSelectedRow()]).parzj()),
										tableIon.getSelectedRow(), 3);
							} else if (tableIon.getSelectedColumn() == 5) {
								msystem.streams.ion(EIon.values()[11 + tableIon.getSelectedRow()])
										.parcj(Double.parseDouble(tableIon
												.getValueAt(tableIon.getSelectedRow(), tableIon.getSelectedColumn())
												.toString()));
								tableIon.getModel()
										.setValueAt(
												df2.format(msystem.streams
														.ion(EIon.values()[11 + tableIon.getSelectedRow()]).parcj()),
										tableIon.getSelectedRow(), 5);
								tableIon.getModel()
										.setValueAt(df2.format(msystem.streams
												.ion(EIon.values()[11 + tableIon.getSelectedRow()]).parmj() * 1000),
										tableIon.getSelectedRow(), 6);
								tableIon.getModel()
										.setValueAt(
												df2.format(msystem.streams
														.ion(EIon.values()[11 + tableIon.getSelectedRow()]).parzj()),
										tableIon.getSelectedRow(), 7);
							} else if (tableIon.getSelectedColumn() == 6) {
								msystem.streams.ion(EIon.values()[11 + tableIon.getSelectedRow()])
										.parmj(Double.parseDouble(tableIon
												.getValueAt(tableIon.getSelectedRow(), tableIon.getSelectedColumn())
												.toString()) / 1000);
								tableIon.getModel()
										.setValueAt(
												df2.format(msystem.streams
														.ion(EIon.values()[11 + tableIon.getSelectedRow()]).parcj()),
										tableIon.getSelectedRow(), 5);
								tableIon.getModel()
										.setValueAt(df2.format(msystem.streams
												.ion(EIon.values()[11 + tableIon.getSelectedRow()]).parmj() * 1000),
										tableIon.getSelectedRow(), 6);
								tableIon.getModel()
										.setValueAt(
												df2.format(msystem.streams
														.ion(EIon.values()[11 + tableIon.getSelectedRow()]).parzj()),
										tableIon.getSelectedRow(), 7);
							} else if (tableIon.getSelectedColumn() == 7) {
								msystem.streams.ion(EIon.values()[11 + tableIon.getSelectedRow()])
										.parzj(Double.parseDouble(tableIon
												.getValueAt(tableIon.getSelectedRow(), tableIon.getSelectedColumn())
												.toString()));
								tableIon.getModel()
										.setValueAt(
												df2.format(msystem.streams
														.ion(EIon.values()[11 + tableIon.getSelectedRow()]).parcj()),
										tableIon.getSelectedRow(), 5);
								tableIon.getModel()
										.setValueAt(df2.format(msystem.streams
												.ion(EIon.values()[11 + tableIon.getSelectedRow()]).parmj() * 1000),
										tableIon.getSelectedRow(), 6);
								tableIon.getModel()
										.setValueAt(
												df2.format(msystem.streams
														.ion(EIon.values()[11 + tableIon.getSelectedRow()]).parzj()),
										tableIon.getSelectedRow(), 7);
							}
							msystem.temp = msystem.streams.ion(EIon.P).parmj();
							tableIon.setValueAt(df2.format(msystem.streams.parqC()), 11, 3);
							tableIon.setValueAt(df2.format(msystem.streams.parqA()), 11, 7);
							// 饱和度实时显示 >100 红色背景
							TextField_CaCO3.setText(String.format("%.2f", msystem.streams.parSCaCO3()));
							TextField_BaSO4.setText(String.format("%.2f", msystem.streams.parSBaSO4() * 100));
							TextField_CaSO4.setText(String.format("%.2f", msystem.streams.parSCaSO4() * 100));
							TextField_SrSO4.setText(String.format("%.2f", msystem.streams.parSSrSO4() * 100));
							TextField_Ca3
									.setText(String.format("%.2f", msystem.streams.parSCa3PO42(msystem.temp) * 100));
							TextField_CaF2.setText(String.format("%.2f", msystem.streams.parSCaF2() * 100));
							if (Double.parseDouble(TextField_BaSO4.getText().toString()) > 100) {
								TextField_BaSO4.setBackground(new Color(176, 23, 31));
							} else {
								TextField_BaSO4.setBackground(Color.white);
							}
							if (Double.parseDouble(TextField_CaSO4.getText().toString()) > 100) {
								TextField_CaSO4.setBackground(new Color(176, 23, 31));
							} else {
								TextField_CaSO4.setBackground(Color.white);
							}
							if (Double.parseDouble(TextField_SrSO4.getText().toString()) > 100) {
								TextField_SrSO4.setBackground(new Color(176, 23, 31));
							} else {
								TextField_SrSO4.setBackground(Color.white);
							}
							if (Double.parseDouble(TextField_Ca3.getText().toString()) > 100) {
								TextField_Ca3.setBackground(new Color(176, 23, 31));
							} else {
								TextField_Ca3.setBackground(Color.white);
							}
							if (Double.parseDouble(TextField_CaF2.getText().toString()) > 100) {
								TextField_CaF2.setBackground(new Color(176, 23, 31));
							} else {
								TextField_CaF2.setBackground(Color.white);
							}
						}

						@Override
						public void editingCanceled(ChangeEvent e) {
						}
					});
				}
			}
		});

		JLabel labelIonInfro = new JLabel("离子信息");
		GridBagConstraints gbc_label_ion = new GridBagConstraints();
		gbc_label_ion.anchor = GridBagConstraints.WEST;
		gbc_label_ion.insets = new Insets(0, 0, 5, 5);
		gbc_label_ion.gridx = 0;
		gbc_label_ion.gridy = 0;
		panelIon.add(labelIonInfro, gbc_label_ion);

		JTextField textFieldIonInfro = new JTextField();
		textFieldIonInfro.setPreferredSize(new Dimension(480, 1));
		GridBagConstraints gbc_textField_ion = new GridBagConstraints();
		gbc_textField_ion.anchor = GridBagConstraints.NORTH;
		gbc_textField_ion.gridwidth = 10;
		gbc_textField_ion.insets = new Insets(0, 0, 5, 5);
		gbc_textField_ion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_ion.gridx = 0;
		gbc_textField_ion.gridy = 1;
		panelIon.add(textFieldIonInfro, gbc_textField_ion);
		textFieldIonInfro.setColumns(10);
		textFieldIonInfro.setOpaque(false);
		textFieldIonInfro.setEditable(false);
		textFieldIonInfro.setBorder(new MatteBorder(1, 0, 0, 0, new Color(192, 192, 192)));

		JLabel labelSaturation = new JLabel("饱和度");
		GridBagConstraints gbc_label23 = new GridBagConstraints();
		gbc_label23.anchor = GridBagConstraints.WEST;
		gbc_label23.insets = new Insets(0, 0, 5, 5);
		gbc_label23.gridx = 0;
		gbc_label23.gridy = 3;
		panelIon.add(labelSaturation, gbc_label23);

		JTextField textField_line = new JTextField();
		textField_line.setPreferredSize(new Dimension(480, 1));
		GridBagConstraints gbc_textField_line = new GridBagConstraints();
		gbc_textField_line.anchor = GridBagConstraints.NORTH;
		gbc_textField_line.gridwidth = 10;
		gbc_textField_line.insets = new Insets(0, 0, 5, 5);
		gbc_textField_line.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_line.gridx = 0;
		gbc_textField_line.gridy = 4;
		panelIon.add(textField_line, gbc_textField_line);
		textField_line.setColumns(10);
		textField_line.setOpaque(false);
		textField_line.setEditable(false);
		textField_line.setBorder(new MatteBorder(1, 0, 0, 0, new Color(192, 192, 192)));

		JLabel lblNewLabelCaCO3 = new JLabel("CaCO3(L.I.)");
		GridBagConstraints gbc_lblNewLabelCaCO3 = new GridBagConstraints();
		gbc_lblNewLabelCaCO3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelCaCO3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelCaCO3.gridx = 1;
		gbc_lblNewLabelCaCO3.gridy = 5;
		panelIon.add(lblNewLabelCaCO3, gbc_lblNewLabelCaCO3);

		JLabel lblNewLabelBaSO4 = new JLabel("BaSO4");
		GridBagConstraints gbc_lblNewLabelBaSO4 = new GridBagConstraints();
		gbc_lblNewLabelBaSO4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelBaSO4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelBaSO4.gridx = 4;
		gbc_lblNewLabelBaSO4.gridy = 5;
		panelIon.add(lblNewLabelBaSO4, gbc_lblNewLabelBaSO4);

		JLabel lblMhBaSO4 = new JLabel("%");
		GridBagConstraints gbc_lblMhBaSO4 = new GridBagConstraints();
		gbc_lblMhBaSO4.anchor = GridBagConstraints.WEST;
		gbc_lblMhBaSO4.insets = new Insets(0, 0, 5, 5);
		gbc_lblMhBaSO4.gridx = 6;
		gbc_lblMhBaSO4.gridy = 5;
		panelIon.add(lblMhBaSO4, gbc_lblMhBaSO4);

		JLabel lblNewLabelCaSO4 = new JLabel("CaSO4");
		GridBagConstraints gbc_lblNewLabelCaSO4 = new GridBagConstraints();
		gbc_lblNewLabelCaSO4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelCaSO4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelCaSO4.gridx = 1;
		gbc_lblNewLabelCaSO4.gridy = 6;
		panelIon.add(lblNewLabelCaSO4, gbc_lblNewLabelCaSO4);

		JLabel lblMhCaSO4 = new JLabel("%");
		GridBagConstraints gbc_lblMhCaSO4 = new GridBagConstraints();
		gbc_lblMhCaSO4.anchor = GridBagConstraints.WEST;
		gbc_lblMhCaSO4.insets = new Insets(0, 0, 5, 5);
		gbc_lblMhCaSO4.gridx = 3;
		gbc_lblMhCaSO4.gridy = 6;
		panelIon.add(lblMhCaSO4, gbc_lblMhCaSO4);

		JLabel lblNewLabelSrSO4 = new JLabel("SrSO4");
		GridBagConstraints gbc_lblNewLabelSrSO4 = new GridBagConstraints();
		gbc_lblNewLabelSrSO4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelSrSO4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelSrSO4.gridx = 4;
		gbc_lblNewLabelSrSO4.gridy = 6;
		panelIon.add(lblNewLabelSrSO4, gbc_lblNewLabelSrSO4);

		JLabel lblMhSrSO4 = new JLabel("%");
		GridBagConstraints gbc_lblMhSrSO4 = new GridBagConstraints();
		gbc_lblMhSrSO4.anchor = GridBagConstraints.WEST;
		gbc_lblMhSrSO4.insets = new Insets(0, 0, 5, 5);
		gbc_lblMhSrSO4.gridx = 6;
		gbc_lblMhSrSO4.gridy = 6;
		panelIon.add(lblMhSrSO4, gbc_lblMhSrSO4);

		JLabel lblNewLabelCa3 = new JLabel("Ca3(PO4)2");
		GridBagConstraints gbc_lblNewLabelCa3 = new GridBagConstraints();
		gbc_lblNewLabelCa3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelCa3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabelCa3.gridx = 1;
		gbc_lblNewLabelCa3.gridy = 7;
		panelIon.add(lblNewLabelCa3, gbc_lblNewLabelCa3);

		JLabel lblMhCa3 = new JLabel("%");
		GridBagConstraints gbc_lblMhCa3 = new GridBagConstraints();
		gbc_lblMhCa3.anchor = GridBagConstraints.WEST;
		gbc_lblMhCa3.insets = new Insets(0, 0, 0, 5);
		gbc_lblMhCa3.gridx = 3;
		gbc_lblMhCa3.gridy = 7;
		panelIon.add(lblMhCa3, gbc_lblMhCa3);

		JLabel lblNewLabelCaF2 = new JLabel("CaF2");
		GridBagConstraints gbc_lblNewLabelCaF2 = new GridBagConstraints();
		gbc_lblNewLabelCaF2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabelCaF2.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabelCaF2.gridx = 4;
		gbc_lblNewLabelCaF2.gridy = 7;
		panelIon.add(lblNewLabelCaF2, gbc_lblNewLabelCaF2);

		JLabel lblMhCaF2 = new JLabel("%");
		GridBagConstraints gbc_lblMhCaF2 = new GridBagConstraints();
		gbc_lblMhCaF2.anchor = GridBagConstraints.WEST;
		gbc_lblMhCaF2.insets = new Insets(0, 0, 0, 5);
		gbc_lblMhCaF2.gridx = 6;
		gbc_lblMhCaF2.gridy = 7;
		panelIon.add(lblMhCaF2, gbc_lblMhCaF2);

		// panel_2有机物信息面板
		JPanel organismpanel = new JPanel();
		jrbCOD = new JLabel("COD总量");
		JPanel panelCOD = new JPanel();
		GridBagConstraints gbc_textField_11_1 = new GridBagConstraints();
		gbc_textField_11_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_textField_11_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_11_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_11_1.gridx = 2;
		gbc_textField_11_1.gridy = 0;
		GridBagConstraints gbc_label_14_1 = new GridBagConstraints();
		gbc_label_14_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_14_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_14_1.gridx = 2;
		gbc_label_14_1.gridy = 3;
		GridBagConstraints gbc_textField_12_1 = new GridBagConstraints();
		gbc_textField_12_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_12_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_12_1.gridx = 3;
		gbc_textField_12_1.gridy = 3;
		GridBagConstraints gbc_label_19_1 = new GridBagConstraints();
		gbc_label_19_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_19_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_19_1.gridx = 6;
		gbc_label_19_1.gridy = 3;
		GridBagConstraints gbc_textField_14_1 = new GridBagConstraints();
		gbc_textField_14_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_14_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_14_1.gridx = 7;
		gbc_textField_14_1.gridy = 3;
		GridBagConstraints gbc_label_13_1 = new GridBagConstraints();
		gbc_label_13_1.anchor = GridBagConstraints.EAST;
		gbc_label_13_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_13_1.gridx = 1;
		gbc_label_13_1.gridy = 4;
		GridBagConstraints gbc_label_18_1 = new GridBagConstraints();
		gbc_label_18_1.anchor = GridBagConstraints.EAST;
		gbc_label_18_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_18_1.gridx = 5;
		gbc_label_18_1.gridy = 4;
		GridBagConstraints gbc_label_15_1 = new GridBagConstraints();
		gbc_label_15_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_15_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_15_1.gridx = 2;
		gbc_label_15_1.gridy = 5;
		GridBagConstraints gbc_textField_13_1 = new GridBagConstraints();
		gbc_textField_13_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_13_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_13_1.gridx = 3;
		gbc_textField_13_1.gridy = 5;
		GridBagConstraints gbc_label_17_1 = new GridBagConstraints();
		gbc_label_17_1.anchor = GridBagConstraints.WEST;
		gbc_label_17_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_17_1.gridx = 4;
		gbc_label_17_1.gridy = 5;
		GridBagConstraints gbc_label_20_1 = new GridBagConstraints();
		gbc_label_20_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_20_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_20_1.gridx = 6;
		gbc_label_20_1.gridy = 5;
		GridBagConstraints gbc_textField_15_1 = new GridBagConstraints();
		gbc_textField_15_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_15_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_15_1.gridx = 7;
		gbc_textField_15_1.gridy = 5;
		GridBagConstraints gbc_label_16_1 = new GridBagConstraints();
		gbc_label_16_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_16_1.anchor = GridBagConstraints.WEST;
		gbc_label_16_1.gridx = 8;
		gbc_label_16_1.gridy = 5;
		jrbOther = new JLabel("其它物质");
		jrbTOC = new JLabel("TOC");

		JPanel panelTOC = new JPanel();
		GridBagLayout gbl_panelTOC = new GridBagLayout();
		gbl_panelTOC.columnWidths = new int[] { 80, 150, 60, 80, 150, 60, 0 };
		gbl_panelTOC.rowHeights = new int[] { 10, 24, 10, 0, 0 };
		gbl_panelTOC.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelTOC.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelTOC.setLayout(gbl_panelTOC);

		JLabel labelTOC_ROD = new JLabel("截留率");
		GridBagConstraints gbc_labelTOC_ROD = new GridBagConstraints();
		gbc_labelTOC_ROD.anchor = GridBagConstraints.EAST;
		gbc_labelTOC_ROD.insets = new Insets(0, 0, 5, 5);
		gbc_labelTOC_ROD.gridx = 0;
		gbc_labelTOC_ROD.gridy = 1;
		panelTOC.add(labelTOC_ROD, gbc_labelTOC_ROD);

		textFieldTOC_ROD = new JTextField(String.format("%.4f", msystem.streams.cods()[1].parRCOD));
		textFieldTOC_ROD.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldROD(textFieldTOC_ROD,1);
			}
		});
		textFieldTOC_ROD.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldROD(textFieldTOC_ROD,1);
				}
			}
		});
		GridBagConstraints gbc_textFieldTOC_ROD = new GridBagConstraints();
		gbc_textFieldTOC_ROD.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTOC_ROD.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTOC_ROD.gridx = 1;
		gbc_textFieldTOC_ROD.gridy = 1;
		panelTOC.add(textFieldTOC_ROD, gbc_textFieldTOC_ROD);

		JLabel labelTOC = new JLabel("TOC浓度");
		gbc_labelTOC = new GridBagConstraints();
		gbc_labelTOC.anchor = GridBagConstraints.EAST;
		gbc_labelTOC.insets = new Insets(0, 0, 5, 5);
		gbc_labelTOC.gridx = 3;
		gbc_labelTOC.gridy = 1;
		panelTOC.add(labelTOC, gbc_labelTOC);

		textFieldTOC = new JTextField(String.format("%.2f", msystem.streams.cods()[1].parcj));
		textFieldTOC.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldparcj(textFieldTOC,1);
			}
		});
		textFieldTOC.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldparcj(textFieldTOC,1);
				}
			}
		});
		gbc_textFieldTOC = new GridBagConstraints();
		gbc_textFieldTOC.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTOC.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTOC.gridx = 4;
		gbc_textFieldTOC.gridy = 1;
		panelTOC.add(textFieldTOC, gbc_textFieldTOC);

		JLabel label_TOC_mgl = new JLabel("mg/L");
		gbc_label_TOC_mgl = new GridBagConstraints();
		gbc_label_TOC_mgl.anchor = GridBagConstraints.WEST;
		gbc_label_TOC_mgl.insets = new Insets(0, 0, 5, 0);
		gbc_label_TOC_mgl.gridx = 5;
		gbc_label_TOC_mgl.gridy = 1;
		panelTOC.add(label_TOC_mgl, gbc_label_TOC_mgl);

		JPanel panelother = new JPanel();
		GridBagLayout gbl_panelother = new GridBagLayout();
		gbl_panelother.columnWidths = new int[] { 80, 150, 60, 80, 150, 60, 0 };
		gbl_panelother.rowHeights = new int[] { 10, 24, 10, 0, 0 };
		gbl_panelother.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panelother.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelother.setLayout(gbl_panelother);

		JLabel labelother_ROD = new JLabel("截留率");
		GridBagConstraints gbc_labelother_ROD = new GridBagConstraints();
		gbc_labelother_ROD.anchor = GridBagConstraints.EAST;
		gbc_labelother_ROD.insets = new Insets(0, 0, 5, 5);
		gbc_labelother_ROD.gridx = 0;
		gbc_labelother_ROD.gridy = 1;
		panelother.add(labelother_ROD, gbc_labelother_ROD);

		textFieldother_ROD = new JTextField(String.format("%.4f", msystem.streams.cods()[2].parRCOD));
		textFieldother_ROD.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldROD(textFieldother_ROD,2);
			}
		});
		textFieldother_ROD.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldROD(textFieldother_ROD,2);
				}
			}
		});
		gbc_textFieldother_ROD = new GridBagConstraints();
		gbc_textFieldother_ROD.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldother_ROD.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldother_ROD.gridx = 1;
		gbc_textFieldother_ROD.gridy = 1;
		panelother.add(textFieldother_ROD, gbc_textFieldother_ROD);

		JLabel labelother = new JLabel("浓度");
		gbc_labelother = new GridBagConstraints();
		gbc_labelother.anchor = GridBagConstraints.EAST;
		gbc_labelother.insets = new Insets(0, 0, 5, 5);
		gbc_labelother.gridx = 3;
		gbc_labelother.gridy = 1;
		panelother.add(labelother, gbc_labelother);

		textFieldother = new JTextField(String.format("%.2f", msystem.streams.cods()[2].parcj));
		textFieldother.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldparcj(textFieldother,2);
			}
		});
		textFieldother.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldparcj(textFieldother,2);
				}
			}
		});
		GridBagConstraints gbc_textFieldother = new GridBagConstraints();
		gbc_textFieldother.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldother.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldother.gridx = 4;
		gbc_textFieldother.gridy = 1;
		panelother.add(textFieldother, gbc_textFieldother);

		JLabel label_other_mgl = new JLabel("mg/L");
		gbc_label_other_mgl = new GridBagConstraints();
		gbc_label_other_mgl.anchor = GridBagConstraints.WEST;
		gbc_label_other_mgl.insets = new Insets(0, 0, 5, 0);
		gbc_label_other_mgl.gridx = 5;
		gbc_label_other_mgl.gridy = 1;
		panelother.add(label_other_mgl, gbc_label_other_mgl);

		GroupLayout gl_panel_2 = new GroupLayout(organismpanel);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(jrbCOD)
										.addComponent(panelCOD, GroupLayout.PREFERRED_SIZE, 456,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(jrbTOC)
										.addComponent(panelTOC, GroupLayout.PREFERRED_SIZE, 456,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(jrbOther).addComponent(panelother, GroupLayout.PREFERRED_SIZE,
												456, GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
		gl_panel_2
				.setVerticalGroup(
						gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup().addGap(24).addComponent(jrbCOD)
										.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
										.addComponent(panelCOD, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE)
										.addGap(24).addComponent(jrbTOC).addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(panelTOC, GroupLayout.PREFERRED_SIZE, 42,
												GroupLayout.PREFERRED_SIZE)
										.addGap(27).addComponent(jrbOther)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(panelother,
												GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
				.addGap(38)));
		GridBagConstraints gbc_label_12_1 = new GridBagConstraints();
		gbc_label_12_1.anchor = GridBagConstraints.WEST;
		gbc_label_12_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_12_1.gridx = 3;
		gbc_label_12_1.gridy = 0;

		GridBagLayout panelcod = new GridBagLayout();
		panelcod.columnWidths = new int[] { 80, 150, 60, 80, 150, 60 };
		panelcod.rowHeights = new int[] { 10, 24, 10, 0 };
		panelcod.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panelcod.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0 };
		panelCOD.setLayout(panelcod);
		organismpanel.setLayout(gl_panel_2);

		JLabel labelCOD_ROD = new JLabel("截留率");
		GridBagConstraints gbc_labelCOD_ROD = new GridBagConstraints();
		gbc_labelCOD_ROD.anchor = GridBagConstraints.EAST;
		gbc_labelCOD_ROD.insets = new Insets(0, 0, 5, 5);
		gbc_labelCOD_ROD.gridx = 0;
		gbc_labelCOD_ROD.gridy = 1;
		panelCOD.add(labelCOD_ROD, gbc_labelCOD_ROD);

		textFieldCOD_ROD = new JTextField(String.format("%.4f", msystem.streams.cods()[0].parRCOD));
		textFieldCOD_ROD.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldROD(textFieldCOD_ROD,0);
			}
		});
		textFieldCOD_ROD.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldROD(textFieldCOD_ROD,0);
				}
			}
		});
		GridBagConstraints gbc_textFieldCOD_ROD = new GridBagConstraints();
		gbc_textFieldCOD_ROD.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCOD_ROD.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCOD_ROD.gridx = 1;
		gbc_textFieldCOD_ROD.gridy = 1;
		panelCOD.add(textFieldCOD_ROD, gbc_textFieldCOD_ROD);
		JLabel labelCOD = new JLabel("COD浓度");
		GridBagConstraints gbc_label_COD = new GridBagConstraints();
		gbc_label_COD.anchor = GridBagConstraints.EAST;
		gbc_label_COD.insets = new Insets(0, 0, 5, 5);
		gbc_label_COD.gridx = 3;
		gbc_label_COD.gridy = 1;
		panelCOD.add(labelCOD, gbc_label_COD);
		textFieldCOD = new JTextField(String.format("%.2f", msystem.streams.cods()[0].parcj));
		textFieldCOD.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldparcj(textFieldCOD,0);
			}
		});
		textFieldCOD.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldparcj(textFieldCOD,0);
				}
			}
		});
		GridBagConstraints gbc_textField_COD = new GridBagConstraints();
		gbc_textField_COD.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_COD.insets = new Insets(0, 0, 5, 5);
		gbc_textField_COD.gridx = 4;
		gbc_textField_COD.gridy = 1;
		panelCOD.add(textFieldCOD, gbc_textField_COD);
		JLabel label_COD_mgl = new JLabel("mg/L");
		GridBagConstraints gbc_label_COD_mgl = new GridBagConstraints();
		gbc_label_COD_mgl.insets = new Insets(0, 0, 5, 0);
		gbc_label_COD_mgl.anchor = GridBagConstraints.WEST;
		gbc_label_COD_mgl.gridx = 5;
		gbc_label_COD_mgl.gridy = 1;
		panelCOD.add(label_COD_mgl, gbc_label_COD_mgl);

		JPanel jsprightpanel = new JPanel();
		String[] str = { "进水参数", "离子信息", "有机物信息" };
		JList jstleftlist = new JList(str);
		jstleftlist.setSelectedIndex(0);
		jstleftlist.setFixedCellWidth(125);
		JSplitPane waterjsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, jstleftlist, jsprightpanel);
		jsprightpanel.add(panelWater);
		waterjsp.add(jsprightpanel, JSplitPane.RIGHT);
		jstleftlist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				msystem.streams.ion(EIon.P).parmj(msystem.temp);
				if (jstleftlist.getSelectedIndex() == 0) {
					waterjsp.remove(waterjsp.getRightComponent());
					jsprightpanel.remove(panelIon);
					jsprightpanel.remove(organismpanel);
					jsprightpanel.add(panelWater);
					waterjsp.add(jsprightpanel, JSplitPane.RIGHT);
				} else if (jstleftlist.getSelectedIndex() == 1) {
					waterjsp.remove(waterjsp.getRightComponent());
					jsprightpanel.remove(panelWater);
					jsprightpanel.remove(organismpanel);
					jsprightpanel.add(panelIon);
					waterjsp.add(jsprightpanel, JSplitPane.RIGHT);
				} else if (jstleftlist.getSelectedIndex() == 2) {
					waterjsp.remove(waterjsp.getRightComponent());
					jsprightpanel.remove(panelWater);
					jsprightpanel.remove(panelIon);
					jsprightpanel.add(organismpanel);
					waterjsp.add(jsprightpanel, JSplitPane.RIGHT);
				}
			}
		});
		waterjsp.setDividerSize(1);
		waterjsp.setEnabled(false);
		GroupLayout gl_panel_1 = new GroupLayout(panel);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(waterjsp,
				GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE));
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1.createSequentialGroup()
						.addComponent(waterjsp, GroupLayout.PREFERRED_SIZE, 387, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel_1);

		/*
		 * panelSystem面板上放置一个JSplitPane，由左侧list可以切换到水量设计基础表、系统设计参数
		 */
		JPanel panelSystem = new JPanel();
		tabbedPane.addTab("系统设计", null, panelSystem, null);

		// panel_30为水量设计基础表
		JPanel panel_30 = new JPanel();
		GridBagLayout gbl_panel_30 = new GridBagLayout();
		gbl_panel_30.columnWidths = new int[] { 25, 50, 80, 30, 20, 50, 80, 30, 20 };
		gbl_panel_30.rowHeights = new int[] { 40, 60, 60, 60, 60, 30, 15, 5 };
		gbl_panel_30.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_panel_30.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panel_30.setLayout(gbl_panel_30);

		JLabel labelQp = new JLabel("产水流量");
		GridBagConstraints gbc_labelQp = new GridBagConstraints();
		gbc_labelQp.anchor = GridBagConstraints.WEST;
		gbc_labelQp.insets = new Insets(0, 0, 5, 5);
		gbc_labelQp.gridx = 1;
		gbc_labelQp.gridy = 1;
		panel_30.add(labelQp, gbc_labelQp);

		textFieldpariQp = new JTextField(String.format("%.2f", msystem.pariQp));
		GridBagConstraints gbc_textFieldpariQp = new GridBagConstraints();
		gbc_textFieldpariQp.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldpariQp.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldpariQp.gridx = 2;
		gbc_textFieldpariQp.gridy = 1;
		panel_30.add(textFieldpariQp, gbc_textFieldpariQp);

		JLabel lblMh311 = new JLabel("m3/h");
		GridBagConstraints gbc_lblMh311 = new GridBagConstraints();
		gbc_lblMh311.anchor = GridBagConstraints.WEST;
		gbc_lblMh311.insets = new Insets(0, 0, 5, 5);
		gbc_lblMh311.gridx = 3;
		gbc_lblMh311.gridy = 1;
		panel_30.add(lblMh311, gbc_lblMh311);

		JLabel labelAvgJP = new JLabel("平均水通量");
		GridBagConstraints gbc_labelAvgJP = new GridBagConstraints();
		gbc_labelAvgJP.anchor = GridBagConstraints.WEST;
		gbc_labelAvgJP.insets = new Insets(0, 0, 5, 5);
		gbc_labelAvgJP.gridx = 5;
		gbc_labelAvgJP.gridy = 1;
		panel_30.add(labelAvgJP, gbc_labelAvgJP);
		try {
			TextFieldpariJ = new JTextField(String.format("%.2f", msystem.pariJ()));
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
		}
		TextFieldpariJ.setEnabled(false);
		GridBagConstraints gbc_TextFieldpariJ = new GridBagConstraints();
		gbc_TextFieldpariJ.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextFieldpariJ.insets = new Insets(0, 0, 5, 5);
		gbc_TextFieldpariJ.gridx = 6;
		gbc_TextFieldpariJ.gridy = 1;
		panel_30.add(TextFieldpariJ, gbc_TextFieldpariJ);

		JLabel lblLmh313 = new JLabel("LMH");
		GridBagConstraints gbc_lblLmh313 = new GridBagConstraints();
		gbc_lblLmh313.anchor = GridBagConstraints.WEST;
		gbc_lblLmh313.insets = new Insets(0, 0, 5, 5);
		gbc_lblLmh313.gridx = 7;
		gbc_lblLmh313.gridy = 1;
		panel_30.add(lblLmh313, gbc_lblLmh313);

		JLabel labelpariY = new JLabel("产水回收率");
		GridBagConstraints gbc_labelpariY = new GridBagConstraints();
		gbc_labelpariY.anchor = GridBagConstraints.WEST;
		gbc_labelpariY.insets = new Insets(0, 0, 5, 5);
		gbc_labelpariY.gridx = 1;
		gbc_labelpariY.gridy = 2;
		panel_30.add(labelpariY, gbc_labelpariY);

		textFieldpariY = new JTextField(String.format("%.2f", msystem.pariY));
		GridBagConstraints gbc_textFieldpariY = new GridBagConstraints();
		gbc_textFieldpariY.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldpariY.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldpariY.gridx = 2;
		gbc_textFieldpariY.gridy = 2;
		panel_30.add(textFieldpariY, gbc_textFieldpariY);

		JLabel lblMh313 = new JLabel("%");
		GridBagConstraints gbc_lblMh313 = new GridBagConstraints();
		gbc_lblMh313.anchor = GridBagConstraints.WEST;
		gbc_lblMh313.insets = new Insets(0, 0, 5, 5);
		gbc_lblMh313.gridx = 3;
		gbc_lblMh313.gridy = 2;
		panel_30.add(lblMh313, gbc_lblMh313);
		JLabel labelpariQf = new JLabel("进水流量");
		GridBagConstraints gbc_labelpariQf = new GridBagConstraints();
		gbc_labelpariQf.anchor = GridBagConstraints.WEST;
		gbc_labelpariQf.insets = new Insets(0, 0, 5, 5);
		gbc_labelpariQf.gridx = 5;
		gbc_labelpariQf.gridy = 2;
		panel_30.add(labelpariQf, gbc_labelpariQf);

		TextFieldpariQf = new JTextField(String.format("%.2f", msystem.pariQf()));
		TextFieldpariQf.setEnabled(false);
		GridBagConstraints gbc_TextFieldpariQf = new GridBagConstraints();
		gbc_TextFieldpariQf.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextFieldpariQf.insets = new Insets(0, 0, 5, 5);
		gbc_TextFieldpariQf.gridx = 6;
		gbc_TextFieldpariQf.gridy = 2;
		panel_30.add(TextFieldpariQf, gbc_TextFieldpariQf);

		JLabel lblMh315 = new JLabel("m3/h");
		GridBagConstraints gbc_lblMh315 = new GridBagConstraints();
		gbc_lblMh315.anchor = GridBagConstraints.WEST;
		gbc_lblMh315.insets = new Insets(0, 0, 5, 5);
		gbc_lblMh315.gridx = 7;
		gbc_lblMh315.gridy = 2;
		panel_30.add(lblMh315, gbc_lblMh315);

		JLabel labelpariQr = new JLabel("循环流量");
		GridBagConstraints gbc_labelpariQr = new GridBagConstraints();
		gbc_labelpariQr.anchor = GridBagConstraints.WEST;
		gbc_labelpariQr.insets = new Insets(0, 0, 5, 5);
		gbc_labelpariQr.gridx = 1;
		gbc_labelpariQr.gridy = 3;
		panel_30.add(labelpariQr, gbc_labelpariQr);
		textFieldpariQr = new JTextField(String.format("%.2f", msystem.pariQr));
		GridBagConstraints gbc_textFieldpariQr = new GridBagConstraints();
		gbc_textFieldpariQr.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldpariQr.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldpariQr.gridx = 2;
		gbc_textFieldpariQr.gridy = 3;
		panel_30.add(textFieldpariQr, gbc_textFieldpariQr);

		JLabel lblMh316 = new JLabel("m3/h");
		GridBagConstraints gbc_lblMh316 = new GridBagConstraints();
		gbc_lblMh316.anchor = GridBagConstraints.WEST;
		gbc_lblMh316.insets = new Insets(0, 0, 5, 5);
		gbc_lblMh316.gridx = 3;
		gbc_lblMh316.gridy = 3;
		panel_30.add(lblMh316, gbc_lblMh316);

		JLabel labelpariQc = new JLabel("浓水流量");
		GridBagConstraints gbc_labelpariQc = new GridBagConstraints();
		gbc_labelpariQc.anchor = GridBagConstraints.WEST;
		gbc_labelpariQc.insets = new Insets(0, 0, 5, 5);
		gbc_labelpariQc.gridx = 5;
		gbc_labelpariQc.gridy = 3;
		panel_30.add(labelpariQc, gbc_labelpariQc);

		TextFieldpariQc = new JTextField(String.format("%.2f", msystem.pariQc()));
		TextFieldpariQc.setEnabled(false);
		GridBagConstraints gbc_TextFieldpariQc = new GridBagConstraints();
		gbc_TextFieldpariQc.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextFieldpariQc.insets = new Insets(0, 0, 5, 5);
		gbc_TextFieldpariQc.gridx = 6;
		gbc_TextFieldpariQc.gridy = 3;
		panel_30.add(TextFieldpariQc, gbc_TextFieldpariQc);

		JLabel lblMh3171 = new JLabel("m3/h");
		GridBagConstraints gbc_lblMh3171 = new GridBagConstraints();
		gbc_lblMh3171.anchor = GridBagConstraints.WEST;
		gbc_lblMh3171.insets = new Insets(0, 0, 5, 5);
		gbc_lblMh3171.gridx = 7;
		gbc_lblMh3171.gridy = 3;
		panel_30.add(lblMh3171, gbc_lblMh3171);

		JLabel labelpariYD = new JLabel("DF膜回收率");
		GridBagConstraints gbc_labelpariYD = new GridBagConstraints();
		gbc_labelpariYD.anchor = GridBagConstraints.WEST;
		gbc_labelpariYD.insets = new Insets(0, 0, 5, 5);
		gbc_labelpariYD.gridx = 5;
		gbc_labelpariYD.gridy = 4;
		panel_30.add(labelpariYD, gbc_labelpariYD);
		TextFieldpariYD = new JTextField(String.format("%.2f", msystem.pariYD()));
		TextFieldpariYD.setEnabled(false);
		GridBagConstraints gbc_TextFieldpariYD = new GridBagConstraints();
		gbc_TextFieldpariYD.fill = GridBagConstraints.HORIZONTAL;
		gbc_TextFieldpariYD.insets = new Insets(0, 0, 5, 5);
		gbc_TextFieldpariYD.gridx = 6;
		gbc_TextFieldpariYD.gridy = 4;
		panel_30.add(TextFieldpariYD, gbc_TextFieldpariYD);

		JLabel lblMhdf = new JLabel("%");
		GridBagConstraints gbc_lblMhdf = new GridBagConstraints();
		gbc_lblMhdf.anchor = GridBagConstraints.WEST;
		gbc_lblMhdf.insets = new Insets(0, 0, 5, 5);
		gbc_lblMhdf.gridx = 7;
		gbc_lblMhdf.gridy = 4;
		panel_30.add(lblMhdf, gbc_lblMhdf);
		textFieldpariQp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					msystem.pariQp = Double.parseDouble(textFieldpariQp.getText());
					TextFieldpariQf.setText(String.format("%.2f", msystem.pariQf()));
					TextFieldpariQc.setText(String.format("%.2f", msystem.pariQc()));
					try {
						TextFieldpariJ.setText(String.format("%.2f", msystem.pariJ()));// 平均水通量
						TextFieldpariYD.setText(String.format("%.2f", msystem.pariYD()));// DF膜回收率
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "错误！",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textFieldpariQp.setText(String.format("%.2f", msystem.pariQp));
			}
		});
		textFieldpariQp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						msystem.pariQp = Double.parseDouble(textFieldpariQp.getText());
						TextFieldpariQf.setText(String.format("%.2f", msystem.pariQf()));
						TextFieldpariQc.setText(String.format("%.2f", msystem.pariQc()));
						try {
							TextFieldpariJ.setText(String.format("%.2f", msystem.pariJ()));// 平均水通量
							TextFieldpariYD.setText(String.format("%.2f", msystem.pariYD()));// DF膜回收率
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "错误！",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					textFieldpariQp.setText(String.format("%.2f", msystem.pariQp));
				}
			}
		});
		textFieldpariY.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					msystem.pariY = Double.parseDouble(textFieldpariY.getText());
					TextFieldpariQf.setText(String.format("%.2f", msystem.pariQf()));
					TextFieldpariQc.setText(String.format("%.2f", msystem.pariQc()));
					try {
						TextFieldpariJ.setText(String.format("%.2f", msystem.pariJ()));// 平均水通量
						TextFieldpariYD.setText(String.format("%.2f", msystem.pariYD()));// DF膜回收率
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "错误！",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textFieldpariY.setText(String.format("%.2f", msystem.pariY));
			}
		});
		textFieldpariY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						msystem.pariY = Double.parseDouble(textFieldpariY.getText());
						TextFieldpariQf.setText(String.format("%.2f", msystem.pariQf()));
						TextFieldpariQc.setText(String.format("%.2f", msystem.pariQc()));
						try {
							TextFieldpariJ.setText(String.format("%.2f", msystem.pariJ()));// 平均水通量
							TextFieldpariYD.setText(String.format("%.2f", msystem.pariYD()));// DF膜回收率
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "错误！",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					textFieldpariY.setText(String.format("%.2f", msystem.pariY));
				}
			}
		});
		textFieldpariQr.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					msystem.pariQr = Double.parseDouble(textFieldpariQr.getText());
					TextFieldpariQf.setText(String.format("%.2f", msystem.pariQf()));
					TextFieldpariQc.setText(String.format("%.2f", msystem.pariQc()));
					try {
						TextFieldpariJ.setText(String.format("%.2f", msystem.pariJ()));// 平均水通量
						TextFieldpariYD.setText(String.format("%.2f", msystem.pariYD()));// DF膜回收率
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "错误！",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textFieldpariQr.setText(String.format("%.2f", msystem.pariQr));
			}
		});
		textFieldpariQr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						msystem.pariQr = Double.parseDouble(textFieldpariQr.getText());
						TextFieldpariQf.setText(String.format("%.2f", msystem.pariQf()));
						TextFieldpariQc.setText(String.format("%.2f", msystem.pariQc()));
						try {
							TextFieldpariJ.setText(String.format("%.2f", msystem.pariJ()));// 平均水通量
							TextFieldpariYD.setText(String.format("%.2f", msystem.pariYD()));// DF膜回收率
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "错误！",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					textFieldpariQr.setText(String.format("%.2f", msystem.pariQr));
				}
			}
		});

		// panel_32为系统设计参数
		JPanel panel_32 = new JPanel();
		panel_32.setBorder(null);
		GridBagLayout gbl_panel_32 = new GridBagLayout();
		gbl_panel_32.columnWidths = new int[] { 5, 30, 40, 30, 30, 120, 30, 5 };
		gbl_panel_32.rowHeights = new int[] { 20, 1, 120, 20, 24, 1, 30, 30, 30, 30, 15 };
		gbl_panel_32.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_panel_32.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panel_32.setLayout(gbl_panel_32);

		JLabel labeldesignpara = new JLabel("设计参数");
		GridBagConstraints gbc_label_designpara = new GridBagConstraints();
		gbc_label_designpara.anchor = GridBagConstraints.SOUTHWEST;
		gbc_label_designpara.insets = new Insets(0, 0, 5, 5);
		gbc_label_designpara.gridx = 1;
		gbc_label_designpara.gridy = 0;
		panel_32.add(labeldesignpara, gbc_label_designpara);

		JTextField textFielddesignline = new JTextField();
		textFielddesignline.setPreferredSize(new Dimension(450, 1));
		textFielddesignline.setOpaque(false);
		textFielddesignline.setEditable(false);
		textFielddesignline.setBorder(new MatteBorder(1, 0, 0, 0, new Color(192, 192, 192)));
		GridBagConstraints gbc_textField_designpara = new GridBagConstraints();
		gbc_textField_designpara.fill = GridBagConstraints.BOTH;
		gbc_textField_designpara.insets = new Insets(0, 0, 5, 5);
		gbc_textField_designpara.gridwidth = 7;
		gbc_textField_designpara.gridx = 1;
		gbc_textField_designpara.gridy = 1;
		panel_32.add(textFielddesignline, gbc_textField_designpara);

		JLabel labelpara = new JLabel("详细设置");
		GridBagConstraints gbc_label_para = new GridBagConstraints();
		gbc_label_para.anchor = GridBagConstraints.SOUTHWEST;
		gbc_label_para.insets = new Insets(0, 0, 5, 5);
		gbc_label_para.gridx = 1;
		gbc_label_para.gridy = 4;
		panel_32.add(labelpara, gbc_label_para);
		JTextField textFieldparaline = new JTextField();
		textFieldparaline.setPreferredSize(new Dimension(450, 1));
		textFieldparaline.setOpaque(false);
		textFieldparaline.setEditable(false);
		textFieldparaline.setBorder(new MatteBorder(1, 0, 0, 0, new Color(192, 192, 192)));
		GridBagConstraints gbc_textField_para = new GridBagConstraints();
		gbc_textField_para.fill = GridBagConstraints.BOTH;
		gbc_textField_para.insets = new Insets(0, 0, 5, 0);
		gbc_textField_para.gridwidth = 7;
		gbc_textField_para.gridx = 1;
		gbc_textField_para.gridy = 5;
		panel_32.add(textFieldparaline, gbc_textField_para);
		JLabel labelsections = new JLabel("总段数");
		GridBagConstraints gbc_label321 = new GridBagConstraints();
		gbc_label321.anchor = GridBagConstraints.WEST;
		gbc_label321.insets = new Insets(0, 0, 5, 5);
		gbc_label321.gridx = 1;
		gbc_label321.gridy = 6;
		panel_32.add(labelsections, gbc_label321);
		String[] firstColumnName_3 = { "段数", "膜元件型号", "膜元件数", "压力容器", "产水背压", "段间增压", "段间压力损失" };
		tablesystem = new JTable(7, msystem.section() + 1) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableModel tableModel_3 = (DefaultTableModel) tablesystem.getModel();
		tablesystem.setColumnSelectionAllowed(true);
		tablesystem.setRowSelectionAllowed(false);
		JTableHeader heade_1 = tablesystem.getTableHeader();
		heade_1.setPreferredSize(new Dimension(heade_1.getWidth(), 0));// 表头高度设置
		tablesystem.getTableHeader().setVisible(false);
		JScrollPane jsptablesystem = new JScrollPane(tablesystem);
		jsptablesystem.setPreferredSize(new Dimension(450, 10));
		jsptablesystem.setViewportBorder(null);
		jsptablesystem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints gbc_jsp = new GridBagConstraints();
		gbc_jsp.gridwidth = 6;
		gbc_jsp.fill = GridBagConstraints.BOTH;
		gbc_jsp.insets = new Insets(0, 0, 5, 0);
		gbc_jsp.gridx = 1;
		gbc_jsp.gridy = 2;
		panel_32.add(jsptablesystem, gbc_jsp);

		textFieldsections = new JTextField(String.format("%d", msystem.section()));
		GridBagConstraints gbc_textField_321 = new GridBagConstraints();
		gbc_textField_321.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_321.insets = new Insets(0, 0, 5, 5);
		gbc_textField_321.gridx = 2;
		gbc_textField_321.gridy = 6;
		panel_32.add(textFieldsections, gbc_textField_321);

		JLabel labelsection = new JLabel("段数");
		GridBagConstraints gbc_label_328 = new GridBagConstraints();
		gbc_label_328.anchor = GridBagConstraints.WEST;
		gbc_label_328.insets = new Insets(0, 0, 5, 5);
		gbc_label_328.gridx = 4;
		gbc_label_328.gridy = 6;
		panel_32.add(labelsection, gbc_label_328);
		textFieldsection = new JTextField("1段");
		textFieldsection.setEnabled(false);
		GridBagConstraints gbc_textField_328_1 = new GridBagConstraints();
		gbc_textField_328_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_328_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_328_1.gridx = 5;
		gbc_textField_328_1.gridy = 6;
		panel_32.add(textFieldsection, gbc_textField_328_1);
		textFieldparEi = new JTextField(String.format("%d", msystem.sections()[0].parEi));
		JComboBox<String> modelcomboBox = new JComboBox<>();
		modelcomboBox.setPreferredSize(new Dimension(150, 30));
		modelcomboBox.addItem("DF304I-8040(400)");
		modelcomboBox.addItem("DF90-8040(400)");
		modelcomboBox.addItem("DF30-8040(400)");
		modelcomboBox.setToolTipText("DF304I-8040(400)  尺寸：8x40   膜面积：37.6m2");

		JLabel labelmodel = new JLabel("膜元件型号");
		GridBagConstraints gbc_label322 = new GridBagConstraints();
		gbc_label322.anchor = GridBagConstraints.WEST;
		gbc_label322.insets = new Insets(0, 0, 5, 5);
		gbc_label322.gridx = 1;
		gbc_label322.gridy = 7;
		panel_32.add(labelmodel, gbc_label322);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 7;
		panel_32.add(modelcomboBox, gbc_comboBox);

		JLabel labelparEi = new JLabel("膜元件数");
		GridBagConstraints gbc_label323 = new GridBagConstraints();
		gbc_label323.anchor = GridBagConstraints.WEST;
		gbc_label323.insets = new Insets(0, 0, 5, 5);
		gbc_label323.gridx = 4;
		gbc_label323.gridy = 7;
		panel_32.add(labelparEi, gbc_label323);
		GridBagConstraints gbc_textField_323 = new GridBagConstraints();
		gbc_textField_323.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_323.insets = new Insets(0, 0, 5, 5);
		gbc_textField_323.gridx = 5;
		gbc_textField_323.gridy = 7;
		panel_32.add(textFieldparEi, gbc_textField_323);

		JLabel labelparNVi = new JLabel("压力容器");
		GridBagConstraints gbc_label324 = new GridBagConstraints();
		gbc_label324.anchor = GridBagConstraints.WEST;
		gbc_label324.insets = new Insets(0, 0, 5, 5);
		gbc_label324.gridx = 1;
		gbc_label324.gridy = 8;
		panel_32.add(labelparNVi, gbc_label324);
		textFieldparNVi = new JTextField(String.format("%d", msystem.sections()[0].parNVi));
		GridBagConstraints gbc_textField_324_1 = new GridBagConstraints();
		gbc_textField_324_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_324_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_324_1.gridx = 2;
		gbc_textField_324_1.gridy = 8;
		panel_32.add(textFieldparNVi, gbc_textField_324_1);

		JLabel labelparPpi = new JLabel("产水背压");
		GridBagConstraints gbc_label_325 = new GridBagConstraints();
		gbc_label_325.anchor = GridBagConstraints.WEST;
		gbc_label_325.insets = new Insets(0, 0, 5, 5);
		gbc_label_325.gridx = 4;
		gbc_label_325.gridy = 8;
		panel_32.add(labelparPpi, gbc_label_325);
		textFieldparPpi = new JTextField(String.format("%.2f", msystem.sections()[0].parPpi));
		GridBagConstraints gbc_textField_325_1 = new GridBagConstraints();
		gbc_textField_325_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_325_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_325_1.gridx = 5;
		gbc_textField_325_1.gridy = 8;
		panel_32.add(textFieldparPpi, gbc_textField_325_1);
		lblMpa_2 = new JLabel("MPa");
		GridBagConstraints gbc_lblMpa_2 = new GridBagConstraints();
		gbc_lblMpa_2.anchor = GridBagConstraints.WEST;
		gbc_lblMpa_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblMpa_2.gridx = 6;
		gbc_lblMpa_2.gridy = 8;
		panel_32.add(lblMpa_2, gbc_lblMpa_2);

		JLabel labelparDpi = new JLabel("段间增压");
		GridBagConstraints gbc_label326 = new GridBagConstraints();
		gbc_label326.anchor = GridBagConstraints.WEST;
		gbc_label326.insets = new Insets(0, 0, 5, 5);
		gbc_label326.gridx = 1;
		gbc_label326.gridy = 9;
		panel_32.add(labelparDpi, gbc_label326);
		textFieldparDpi = new JTextField(String.format("%.2f", msystem.sections()[0].parDpi));
		GridBagConstraints gbc_textField_326_1 = new GridBagConstraints();
		gbc_textField_326_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_326_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_326_1.gridx = 2;
		gbc_textField_326_1.gridy = 9;
		panel_32.add(textFieldparDpi, gbc_textField_326_1);
		lblMpa_MPa0 = new JLabel("MPa");
		GridBagConstraints gbc_lblMpa0 = new GridBagConstraints();
		gbc_lblMpa0.anchor = GridBagConstraints.WEST;
		gbc_lblMpa0.insets = new Insets(0, 0, 5, 5);
		gbc_lblMpa0.gridx = 3;
		gbc_lblMpa0.gridy = 9;
		panel_32.add(lblMpa_MPa0, gbc_lblMpa0);

		JLabel labelparPLi = new JLabel("段间压力损失");
		GridBagConstraints gbc_label327 = new GridBagConstraints();
		gbc_label327.anchor = GridBagConstraints.WEST;
		gbc_label327.insets = new Insets(0, 0, 5, 5);
		gbc_label327.gridx = 4;
		gbc_label327.gridy = 9;
		panel_32.add(labelparPLi, gbc_label327);

		textFieldparPLi = new JTextField(String.format("%.2f", msystem.sections()[0].parPLi));
		GridBagConstraints gbc_textField_327_1 = new GridBagConstraints();
		gbc_textField_327_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_327_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_327_1.gridx = 5;
		gbc_textField_327_1.gridy = 9;
		panel_32.add(textFieldparPLi, gbc_textField_327_1);
		lblMpa_MPa2 = new JLabel("MPa");
		GridBagConstraints gbc_lblMpa2 = new GridBagConstraints();
		gbc_lblMpa2.insets = new Insets(0, 0, 5, 5);
		gbc_lblMpa2.anchor = GridBagConstraints.WEST;
		gbc_lblMpa2.gridx = 6;
		gbc_lblMpa2.gridy = 9;
		panel_32.add(lblMpa_MPa2, gbc_lblMpa2);

		modelcomboBox.setEnabled(false);
		textFieldparEi.setEnabled(false);
		textFieldparNVi.setEnabled(false);
		textFieldparPpi.setEnabled(false);
		textFieldparDpi.setEnabled(false);
		textFieldparPLi.setEnabled(false);

		textFieldsections.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					if (Integer.parseInt(textFieldsections.getText().toString()) > 0) {
						msystem.section(Integer.parseInt(textFieldsections.getText().toString()));// 总段数
						tableModel_3.setColumnCount(msystem.section() + 1);
						for (int i = 0; i < tablesystem.getColumnCount() - 1; i++) {
							tablesystem.setValueAt(msystem.sections()[i].model, 1, (i + 1));
							tablesystem.setValueAt(msystem.sections()[i].parEi, 2, (i + 1));
							tablesystem.setValueAt(msystem.sections()[i].parNVi, 3, (i + 1));
							tablesystem.setValueAt(df2.format(msystem.sections()[i].parPpi) + " " + lblMpa_2.getText(),
									4, (i + 1));
							tablesystem.setValueAt(
									df2.format(msystem.sections()[i].parDpi) + " " + lblMpa_MPa0.getText(), 5, (i + 1));
							tablesystem.setValueAt(
									df2.format(msystem.sections()[i].parPLi) + " " + lblMpa_MPa2.getText(), 6, (i + 1));
							tablesystem.setValueAt(i + 1 + "段", 0, i + 1);
						}
						for (int m = 0; m < tablesystem.getColumnCount(); m++) {
							tablesystem.getColumnModel().getColumn(m).setPreferredWidth(100);
						}
						tablesystem.setColumnSelectionInterval(1, 1);
					} else {
						msystem.section(msystem.section());
					}
				} catch (Exception e2) {
				}
				textFieldsections.setText(String.format("%d", msystem.section()));
			}
		});
		textFieldsections.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					try {
						if (Integer.parseInt(textFieldsections.getText().toString()) > 0) {
							msystem.section(Integer.parseInt(textFieldsections.getText().toString()));// 总段数
							tableModel_3.setColumnCount(msystem.section() + 1);
							for (int i = 0; i < tablesystem.getColumnCount() - 1; i++) {
								tablesystem.setValueAt(msystem.sections()[i].model, 1, (i + 1));
								tablesystem.setValueAt(msystem.sections()[i].parEi, 2, (i + 1));
								tablesystem.setValueAt(msystem.sections()[i].parNVi, 3, (i + 1));
								tablesystem.setValueAt(
										df2.format(msystem.sections()[i].parPpi) + " " + lblMpa_2.getText(), 4,
										(i + 1));
								tablesystem.setValueAt(
										df2.format(msystem.sections()[i].parDpi) + " " + lblMpa_MPa0.getText(), 5,
										(i + 1));
								tablesystem.setValueAt(
										df2.format(msystem.sections()[i].parPLi) + " " + lblMpa_MPa2.getText(), 6,
										(i + 1));
								tablesystem.setValueAt(i + 1 + "段", 0, i + 1);
							}
							for (int m = 0; m < tablesystem.getColumnCount(); m++) {
								tablesystem.getColumnModel().getColumn(m).setPreferredWidth(100);
							}
							tablesystem.setColumnSelectionInterval(1, 1);
						} else {
							msystem.section(msystem.section());
						}
					} catch (Exception e2) {

					}
					textFieldsections.setText(String.format("%d", msystem.section()));
				}
			}
		});
		for (int i = 0; i < tablesystem.getRowCount(); i++) {
			tablesystem.setValueAt(firstColumnName_3[i], i, 0);
		}
		for (int i = 0; i < tablesystem.getColumnCount() - 1; i++) {
			tablesystem.setValueAt(msystem.sections()[i].model, 1, (i + 1));
			tablesystem.setValueAt(msystem.sections()[i].parEi, 2, (i + 1));
			tablesystem.setValueAt(msystem.sections()[i].parNVi, 3, (i + 1));
			tablesystem.setValueAt(df2.format(msystem.sections()[i].parPpi) + " " + lblMpa_2.getText(), 4, (i + 1));
			tablesystem.setValueAt(df2.format(msystem.sections()[i].parDpi) + " " + lblMpa_MPa0.getText(), 5, (i + 1));
			tablesystem.setValueAt(df2.format(msystem.sections()[i].parPLi) + " " + lblMpa_MPa2.getText(), 6, (i + 1));
			tablesystem.setValueAt(i + 1 + "段", 0, i + 1);
		}
		for (int m = 0; m < tablesystem.getColumnCount(); m++) {
			tablesystem.getColumnModel().getColumn(m).setPreferredWidth(100);
		}
		for (int m = 0; m < tablesystem.getColumnCount(); m++) {
			tablesystem.getColumnModel().getColumn(m).setPreferredWidth(100);
		}
		tablesystem.getColumnModel().addColumnModelListener(new TableColumnModelListener() {
			@Override
			public void columnSelectionChanged(ListSelectionEvent e) {
				if (tablesystem.getSelectedColumn() >= 1) {
					modelcomboBox.setEnabled(true);
					textFieldparEi.setEnabled(true);
					textFieldparNVi.setEnabled(true);
					textFieldparPpi.setEnabled(true);
					textFieldparDpi.setEnabled(true);
					textFieldparPLi.setEnabled(true);
					textFieldsection.setText(String.format("%d", tablesystem.getSelectedColumn()));
					modelcomboBox.setSelectedItem(msystem.sections()[tablesystem.getSelectedColumn() - 1].model);
					textFieldparEi.setText(
							String.format("%d", msystem.sections()[tablesystem.getSelectedColumn() - 1].parEi));
					textFieldparNVi.setText(
							String.format("%d", msystem.sections()[tablesystem.getSelectedColumn() - 1].parNVi));
					textFieldparPpi.setText(
							String.format("%.2f", msystem.sections()[tablesystem.getSelectedColumn() - 1].parPpi));
					textFieldparDpi.setText(
							String.format("%.2f", msystem.sections()[tablesystem.getSelectedColumn() - 1].parDpi));
					textFieldparPLi.setText(
							String.format("%.2f", msystem.sections()[tablesystem.getSelectedColumn() - 1].parPLi));

				} else if (tablesystem.getSelectedColumn() == 0) {
					tablesystem.setColumnSelectionInterval(1, 1);
				}
			}

			@Override
			public void columnRemoved(TableColumnModelEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void columnMoved(TableColumnModelEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void columnMarginChanged(ChangeEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void columnAdded(TableColumnModelEvent e) {
				// TODO Auto-generated method stub

			}
		});

		modelcomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(final ItemEvent e) {
				int index = modelcomboBox.getSelectedIndex();
				if (index == 0) {
					modelcomboBox.setToolTipText("DF304I-8040(400)  尺寸：8x40   膜面积：37.6m2");
					tablesystem.setValueAt(msystem.sections()[tablesystem.getSelectedColumn() - 1].model, 1,
							tablesystem.getSelectedColumn());
				} else if (index == 1) {
					modelcomboBox.setToolTipText("DF90-8040(400)   尺寸：8x40   膜面积：37.6m2");
					tablesystem.setValueAt(msystem.sections()[tablesystem.getSelectedColumn() - 1].model, 1,
							tablesystem.getSelectedColumn());
				} else if (index == 2) {
					modelcomboBox.setToolTipText("DF30-8040(400)   尺寸：8x40   膜面积：37m2");
					tablesystem.setValueAt(msystem.sections()[tablesystem.getSelectedColumn() - 1].model, 1,
							tablesystem.getSelectedColumn());
				}
				msystem.sections()[tablesystem.getSelectedColumn() - 1].model = modelcomboBox.getSelectedItem()
						.toString();
			}
		});
		textFieldparEi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					if (Integer.parseInt(textFieldparEi.getText().toString()) > 0) {
						msystem.sections()[tablesystem.getSelectedColumn() - 1].parEi = Integer
								.parseInt(textFieldparEi.getText().toString());
					} else {
						msystem.sections()[tablesystem.getSelectedColumn() - 1].parEi = msystem.sections()[0].parEi;
					}
					tablesystem.setValueAt(msystem.sections()[tablesystem.getSelectedColumn() - 1].parEi, 2,
							tablesystem.getSelectedColumn());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textFieldparEi
						.setText(String.format("%d", msystem.sections()[tablesystem.getSelectedColumn() - 1].parEi));
			}
		});
		textFieldparEi.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					try {
						if (Integer.parseInt(textFieldparEi.getText().toString()) > 0) {
							msystem.sections()[tablesystem.getSelectedColumn() - 1].parEi = Integer
									.parseInt(textFieldparEi.getText().toString());
						} else {
							msystem.sections()[tablesystem.getSelectedColumn() - 1].parEi = msystem.sections()[0].parEi;
						}
						tablesystem.setValueAt(msystem.sections()[tablesystem.getSelectedColumn() - 1].parEi, 2,
								tablesystem.getSelectedColumn());
					} catch (Exception e2) {
						// TODO: handle exception
					}
					textFieldparEi.setText(
							String.format("%d", msystem.sections()[tablesystem.getSelectedColumn() - 1].parEi));
				}
			}
		});
		textFieldparNVi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					if (Integer.parseInt(textFieldparNVi.getText().toString()) > 0) {
						msystem.sections()[tablesystem.getSelectedColumn() - 1].parNVi = Integer
								.parseInt(textFieldparNVi.getText().toString());
					} else {
						msystem.sections()[tablesystem.getSelectedColumn() - 1].parNVi = msystem.sections()[0].parNVi;
					}
					tablesystem.setValueAt(msystem.sections()[tablesystem.getSelectedColumn() - 1].parNVi, 3,
							tablesystem.getSelectedColumn());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textFieldparNVi
						.setText(String.format("%d", msystem.sections()[tablesystem.getSelectedColumn() - 1].parNVi));
			}
		});
		textFieldparNVi.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					try {
						if (Integer.parseInt(textFieldparNVi.getText().toString()) > 0) {
							msystem.sections()[tablesystem.getSelectedColumn() - 1].parNVi = Integer
									.parseInt(textFieldparNVi.getText().toString());
						} else {
							msystem.sections()[tablesystem.getSelectedColumn() - 1].parNVi = msystem
									.sections()[0].parNVi;
						}
						tablesystem.setValueAt(msystem.sections()[tablesystem.getSelectedColumn() - 1].parNVi, 3,
								tablesystem.getSelectedColumn());
					} catch (Exception e2) {
						// TODO: handle exception
					}
					textFieldparNVi.setText(
							String.format("%d", msystem.sections()[tablesystem.getSelectedColumn() - 1].parNVi));
				}
			}
		});
		textFieldparPpi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					for (int i = 0; i < tablesystem.getColumnCount() - 1; i++) {
						msystem.sections()[i].parPpi = Double.parseDouble(textFieldparPpi.getText().toString());
						tablesystem.setValueAt(df2.format(msystem.sections()[i].parPpi) + " " + lblMpa_2.getText(), 4,
								i + 1);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textFieldparPpi
						.setText(String.format("%.2f", msystem.sections()[tablesystem.getSelectedColumn() - 1].parPpi));
			}
		});
		textFieldparPpi.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					try {
						for (int i = 0; i < tablesystem.getColumnCount() - 1; i++) {
							msystem.sections()[i].parPpi = Double.parseDouble(textFieldparPpi.getText().toString());
							tablesystem.setValueAt(df2.format(msystem.sections()[i].parPpi) + " " + lblMpa_2.getText(),
									4, i + 1);
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					textFieldparPpi.setText(
							String.format("%.2f", msystem.sections()[tablesystem.getSelectedColumn() - 1].parPpi));
				}
			}
		});
		textFieldparDpi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					msystem.sections()[tablesystem.getSelectedColumn() - 1].parDpi = Double
							.parseDouble(textFieldparDpi.getText().toString());
					tablesystem.setValueAt(df2.format(msystem.sections()[tablesystem.getSelectedColumn() - 1].parDpi)
							+ " " + lblMpa_MPa0.getText(), 5, tablesystem.getSelectedColumn());

				} catch (Exception e2) {
					// TODO: handle exception
				}
				textFieldparDpi
						.setText(String.format("%.2f", msystem.sections()[tablesystem.getSelectedColumn() - 1].parDpi));
			}
		});
		textFieldparDpi.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					try {
						msystem.sections()[tablesystem.getSelectedColumn() - 1].parDpi = Double
								.parseDouble(textFieldparDpi.getText().toString());
						tablesystem
								.setValueAt(df2.format(msystem.sections()[tablesystem.getSelectedColumn() - 1].parDpi)
										+ " " + lblMpa_MPa0.getText(), 5, tablesystem.getSelectedColumn());
					} catch (Exception e2) {
						// TODO: handle exception
					}
					textFieldparDpi.setText(
							String.format("%.2f", msystem.sections()[tablesystem.getSelectedColumn() - 1].parDpi));
				}
			}
		});
		textFieldparPLi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					msystem.sections()[tablesystem.getSelectedColumn() - 1].parPLi = Double
							.parseDouble(textFieldparPLi.getText().toString());
					tablesystem.setValueAt(df2.format(msystem.sections()[tablesystem.getSelectedColumn() - 1].parPLi)
							+ " " + lblMpa_MPa2.getText(), 6, tablesystem.getSelectedColumn());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				textFieldparPLi
						.setText(String.format("%.2f", msystem.sections()[tablesystem.getSelectedColumn() - 1].parPLi));
			}
		});
		textFieldparPLi.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					try {
						msystem.sections()[tablesystem.getSelectedColumn() - 1].parPLi = Double
								.parseDouble(textFieldparPLi.getText().toString());
						tablesystem
								.setValueAt(df2.format(msystem.sections()[tablesystem.getSelectedColumn() - 1].parPLi)
										+ " " + lblMpa_MPa2.getText(), 6, tablesystem.getSelectedColumn());
					} catch (Exception e2) {
						// TODO: handle exception
					}
					textFieldparPLi.setText(
							String.format("%.2f", msystem.sections()[tablesystem.getSelectedColumn() - 1].parPLi));
				}
			}
		});

		String[] systemList = { "水量设计基础", "系统设计参数" };
		JPanel systempanel = new JPanel();
		JList systemlist = new JList(systemList);
		systemlist.setFixedCellWidth(125);
		systemlist.setSelectedIndex(0);
		JSplitPane systemjsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, systemlist, systempanel);
		systempanel.add(panel_30);
		systemlist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (systemlist.getSelectedIndex() == 0) {
					systemjsp.remove(systemjsp.getRightComponent());
					systempanel.remove(panel_32);
					systempanel.add(panel_30);
					systemjsp.add(systempanel, JSplitPane.RIGHT);
				} else if (systemlist.getSelectedIndex() == 1) {
					systemjsp.remove(systemjsp.getRightComponent());
					systempanel.remove(panel_30);
					systempanel.add(panel_32);
					systemjsp.add(systempanel, JSplitPane.RIGHT);
				}
			}
		});
		systemjsp.setDividerSize(1);
		systemjsp.setEnabled(false);

		GroupLayout gl_panelSystem = new GroupLayout(panelSystem);
		gl_panelSystem.setHorizontalGroup(gl_panelSystem.createParallelGroup(Alignment.LEADING).addComponent(systemjsp,
				GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE));
		gl_panelSystem.setVerticalGroup(
				gl_panelSystem.createParallelGroup(Alignment.LEADING).addGroup(gl_panelSystem.createSequentialGroup()
						.addComponent(systemjsp, GroupLayout.PREFERRED_SIZE, 394, Short.MAX_VALUE).addContainerGap()));
		panelSystem.setLayout(gl_panelSystem);
		
		
		JPanel panelPower = new JPanel();
		tabbedPane.addTab("电耗计算", null, panelPower, null);
		
		JPanel panel_40 = new JPanel();
		GridBagLayout gbl_panel_40 = new GridBagLayout();
		gbl_panel_40.columnWidths = new int[] { 40, 100, 80, 100, 80, 100 };
		gbl_panel_40.rowHeights = new int[] { 40, 50, 40, 50, 40, 50,40,50};
		gbl_panel_40.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0,1.0,1.0 };
		gbl_panel_40.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0,1.0,1.0};
		panel_40.setLayout(gbl_panel_40);
		
		JLabel label4_1 = new JLabel("低压泵");
		label4_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label4_1.setForeground(Color.BLACK);
		GridBagConstraints gbc_label_di;
		gbc_label_di = new GridBagConstraints();
		gbc_label_di.insets = new Insets(0, 0, 5, 5);
		gbc_label_di.gridx = 0;
		gbc_label_di.gridy = 0;
		panel_40.add(label4_1, gbc_label_di);
		
		JLabel label4_1_1 = new JLabel("泵效率");
		GridBagConstraints gbc_label4_1_1_di = new GridBagConstraints();
		gbc_label4_1_1_di.anchor = GridBagConstraints.EAST;
		gbc_label4_1_1_di.insets = new Insets(0, 0, 5, 5);
		gbc_label4_1_1_di.gridx = 0;
		gbc_label4_1_1_di.gridy = 1;
		panel_40.add(label4_1_1, gbc_label4_1_1_di);
		
		textField4_1_1 = new JTextField(String.format("%.2f", msystem.pumps()[0].parη_PJ));
		textField4_1_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldPJ(textField4_1_1,0);
			}
		});
		textField4_1_1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldPJ(textField4_1_1,0);
				}
			}
		});
		GridBagConstraints gbc_textField4_1_1_di = new GridBagConstraints();
		gbc_textField4_1_1_di.insets = new Insets(0, 0, 5, 5);
		gbc_textField4_1_1_di.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_1_1_di.gridx = 1;
		gbc_textField4_1_1_di.gridy = 1;
		panel_40.add(textField4_1_1, gbc_textField4_1_1_di);
		textField4_1_1.setColumns(10);
		
		JLabel label4_1_2 = new JLabel("电机效率");
		GridBagConstraints gbc_label4_1_2 = new GridBagConstraints();
		gbc_label4_1_2.anchor = GridBagConstraints.EAST;
		gbc_label4_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_label4_1_2.gridx = 2;
		gbc_label4_1_2.gridy = 1;
		panel_40.add(label4_1_2, gbc_label4_1_2);
		
		textField4_1_2 = new JTextField(String.format("%.2f", msystem.pumps()[0].parη_MJ));
		textField4_1_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldMJ(textField4_1_2,0);
			}
		});
		textField4_1_2.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldMJ(textField4_1_2,0);
				}
			}
		});
		GridBagConstraints gbc_textField4_1_2 = new GridBagConstraints();
		gbc_textField4_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField4_1_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_1_2.gridx = 3;
		gbc_textField4_1_2.gridy = 1;
		panel_40.add(textField4_1_2, gbc_textField4_1_2);
		textField4_1_2.setColumns(10);
		
		JLabel label4_1_3 = new JLabel("变频效率");
		GridBagConstraints gbc_label4_1_3 = new GridBagConstraints();
		gbc_label4_1_3.anchor = GridBagConstraints.EAST;
		gbc_label4_1_3.insets = new Insets(0, 0, 5, 5);
		gbc_label4_1_3.gridx = 4;
		gbc_label4_1_3.gridy = 1;
		panel_40.add(label4_1_3, gbc_label4_1_3);
		
		textField4_1_3 = new JTextField(String.format("%.2f", msystem.pumps()[0].parη_VFD));
		textField4_1_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldVFD(textField4_1_3,0);
			}
		});
		textField4_1_3.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldVFD(textField4_1_3,0);
				}
			}
		});
		GridBagConstraints gbc_textField4_1_3 = new GridBagConstraints();
		gbc_textField4_1_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField4_1_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_1_3.gridx = 5;
		gbc_textField4_1_3.gridy = 1;
		panel_40.add(textField4_1_3, gbc_textField4_1_3);
		textField4_1_3.setColumns(10);
		
		JLabel label4_2 = new JLabel("高压泵");
		label4_2.setFont(new Font("宋体", Font.PLAIN, 18));
		GridBagConstraints gbc_label4_2 = new GridBagConstraints();
		gbc_label4_2.insets = new Insets(0, 0, 5, 5);
		gbc_label4_2.gridx = 0;
		gbc_label4_2.gridy = 2;
		panel_40.add(label4_2, gbc_label4_2);
		
		JLabel label4_2_1 = new JLabel("泵效率");
		GridBagConstraints gbc_label4_2_1 = new GridBagConstraints();
		gbc_label4_2_1.anchor = GridBagConstraints.EAST;
		gbc_label4_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_label4_2_1.gridx = 0;
		gbc_label4_2_1.gridy = 3;
		panel_40.add(label4_2_1, gbc_label4_2_1);
		
		textField4_2_1 = new JTextField(String.format("%.2f", msystem.pumps()[1].parη_PJ));
		textField4_2_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldPJ(textField4_2_1,1);
			}
		});
		textField4_2_1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldPJ(textField4_2_1,1);
				}
			}
		});
		textField4_2_1.setColumns(10);
		GridBagConstraints gbc_textField4_2_1 = new GridBagConstraints();
		gbc_textField4_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField4_2_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_2_1.gridx = 1;
		gbc_textField4_2_1.gridy = 3;
		panel_40.add(textField4_2_1, gbc_textField4_2_1);
		
		JLabel label4_2_2 = new JLabel("电机效率");
		GridBagConstraints gbc_label4_2_2 = new GridBagConstraints();
		gbc_label4_2_2.anchor = GridBagConstraints.EAST;
		gbc_label4_2_2.insets = new Insets(0, 0, 5, 5);
		gbc_label4_2_2.gridx = 2;
		gbc_label4_2_2.gridy = 3;
		panel_40.add(label4_2_2, gbc_label4_2_2);
		
		textField4_2_2 = new JTextField(String.format("%.2f", msystem.pumps()[1].parη_MJ));
		textField4_2_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldMJ(textField4_2_2,1);
			}
		});
		textField4_2_2.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldMJ(textField4_2_2,1);
				}
			}
		});
		textField4_2_2.setColumns(10);
		GridBagConstraints gbc_textField4_2_2 = new GridBagConstraints();
		gbc_textField4_2_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField4_2_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_2_2.gridx = 3;
		gbc_textField4_2_2.gridy = 3;
		panel_40.add(textField4_2_2, gbc_textField4_2_2);
		
		JLabel label4_2_3 = new JLabel("变频效率");
		GridBagConstraints gbc_label4_2_3 = new GridBagConstraints();
		gbc_label4_2_3.anchor = GridBagConstraints.EAST;
		gbc_label4_2_3.insets = new Insets(0, 0, 5, 5);
		gbc_label4_2_3.gridx = 4;
		gbc_label4_2_3.gridy = 3;
		panel_40.add(label4_2_3, gbc_label4_2_3);
		
		textField4_2_3 = new JTextField(String.format("%.2f", msystem.pumps()[1].parη_VFD));
		textField4_2_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldVFD(textField4_2_3,1);
			}
		});
		textField4_2_3.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldVFD(textField4_2_3,1);
				}
			}
		});
		textField4_2_3.setColumns(10);
		GridBagConstraints gbc_textField4_2_3 = new GridBagConstraints();
		gbc_textField4_2_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField4_2_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_2_3.gridx = 5;
		gbc_textField4_2_3.gridy = 3;
		panel_40.add(textField4_2_3, gbc_textField4_2_3);
		
		JLabel label4_3 = new JLabel("增压泵");
		label4_3.setFont(new Font("宋体", Font.PLAIN, 18));
		GridBagConstraints gbc_label4_3 = new GridBagConstraints();
		gbc_label4_3.insets = new Insets(0, 0, 5, 5);
		gbc_label4_3.gridx = 0;
		gbc_label4_3.gridy = 4;
		panel_40.add(label4_3, gbc_label4_3);
		
		JLabel label4_3_1 = new JLabel("泵效率");
		GridBagConstraints gbc_label4_3_1 = new GridBagConstraints();
		gbc_label4_3_1.anchor = GridBagConstraints.EAST;
		gbc_label4_3_1.insets = new Insets(0, 0, 5, 5);
		gbc_label4_3_1.gridx = 0;
		gbc_label4_3_1.gridy = 5;
		panel_40.add(label4_3_1, gbc_label4_3_1);
		
		textField4_3_1 = new JTextField(String.format("%.2f", msystem.pumps()[2].parη_PJ));
		textField4_3_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldPJ(textField4_3_1,2);
			}
		});
		textField4_3_1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldPJ(textField4_3_1,2);
				}
			}
		});
		textField4_3_1.setColumns(10);
		GridBagConstraints gbc_textField4_3_1 = new GridBagConstraints();
		gbc_textField4_3_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField4_3_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_3_1.gridx = 1;
		gbc_textField4_3_1.gridy = 5;
		panel_40.add(textField4_3_1, gbc_textField4_3_1);
		
		JLabel label4_3_2 = new JLabel("电机效率");
		GridBagConstraints gbc_label4_3_2 = new GridBagConstraints();
		gbc_label4_3_2.anchor = GridBagConstraints.EAST;
		gbc_label4_3_2.insets = new Insets(0, 0, 5, 5);
		gbc_label4_3_2.gridx = 2;
		gbc_label4_3_2.gridy = 5;
		panel_40.add(label4_3_2, gbc_label4_3_2);
		
		textField4_3_2 = new JTextField(String.format("%.2f", msystem.pumps()[2].parη_MJ));
		textField4_3_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldMJ(textField4_3_2,2);
			}
		});
		textField4_3_2.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldMJ(textField4_3_2,2);
				}
			}
		});
		textField4_3_2.setColumns(10);
		GridBagConstraints gbc_textField4_3_2 = new GridBagConstraints();
		gbc_textField4_3_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField4_3_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_3_2.gridx = 3;
		gbc_textField4_3_2.gridy = 5;
		panel_40.add(textField4_3_2, gbc_textField4_3_2);
		
		JLabel label4_3_3 = new JLabel("变频效率");
		GridBagConstraints gbc_label4_3_3 = new GridBagConstraints();
		gbc_label4_3_3.anchor = GridBagConstraints.EAST;
		gbc_label4_3_3.insets = new Insets(0, 0, 5, 5);
		gbc_label4_3_3.gridx = 4;
		gbc_label4_3_3.gridy = 5;
		panel_40.add(label4_3_3, gbc_label4_3_3);
		
		textField4_3_3 = new JTextField(String.format("%.2f", msystem.pumps()[2].parη_VFD));
		textField4_3_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldVFD(textField4_3_3,2);
			}
		});
		textField4_3_3.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldVFD(textField4_3_3,2);
				}
			}
		});
		textField4_3_3.setColumns(10);
		GridBagConstraints gbc_textField4_3_3 = new GridBagConstraints();
		gbc_textField4_3_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField4_3_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_3_3.gridx = 5;
		gbc_textField4_3_3.gridy = 5;
		panel_40.add(textField4_3_3, gbc_textField4_3_3);
		
		JLabel label4_4 = new JLabel("回流泵");
		label4_4.setFont(new Font("宋体", Font.PLAIN, 18));
		GridBagConstraints gbc_label4_4 = new GridBagConstraints();
		gbc_label4_4.insets = new Insets(0, 0, 5, 5);
		gbc_label4_4.gridx = 0;
		gbc_label4_4.gridy = 6;
		panel_40.add(label4_4, gbc_label4_4);
		
		JLabel label4_4_1 = new JLabel("泵效率");
		GridBagConstraints gbc_label4_4_1 = new GridBagConstraints();
		gbc_label4_4_1.anchor = GridBagConstraints.EAST;
		gbc_label4_4_1.insets = new Insets(0, 0, 0, 5);
		gbc_label4_4_1.gridx = 0;
		gbc_label4_4_1.gridy = 7;
		panel_40.add(label4_4_1, gbc_label4_4_1);
		
		textField4_4_1 = new JTextField(String.format("%.2f", msystem.pumps()[3].parη_PJ));
		textField4_4_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldPJ(textField4_4_1,3);
			}
		});
		textField4_4_1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldPJ(textField4_4_1,3);
				}
			}
		});
		textField4_4_1.setColumns(10);
		GridBagConstraints gbc_textField4_4_1 = new GridBagConstraints();
		gbc_textField4_4_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField4_4_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_4_1.gridx = 1;
		gbc_textField4_4_1.gridy = 7;
		panel_40.add(textField4_4_1, gbc_textField4_4_1);
		
		JLabel label4_4_2 = new JLabel("电机效率");
		GridBagConstraints gbc_label4_4_2 = new GridBagConstraints();
		gbc_label4_4_2.anchor = GridBagConstraints.EAST;
		gbc_label4_4_2.insets = new Insets(0, 0, 0, 5);
		gbc_label4_4_2.gridx = 2;
		gbc_label4_4_2.gridy = 7;
		panel_40.add(label4_4_2, gbc_label4_4_2);
		
		textField4_4_2 = new JTextField(String.format("%.2f", msystem.pumps()[3].parη_MJ));
		textField4_4_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldMJ(textField4_4_2,3);
			}
		});
		textField4_4_2.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldMJ(textField4_4_2,3);
				}
			}
		});
		textField4_4_2.setColumns(10);
		GridBagConstraints gbc_textField4_4_2 = new GridBagConstraints();
		gbc_textField4_4_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField4_4_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_4_2.gridx = 3;
		gbc_textField4_4_2.gridy = 7;
		panel_40.add(textField4_4_2, gbc_textField4_4_2);
		
		JLabel label4_4_3 = new JLabel("变频效率");
		GridBagConstraints gbc_label4_4_3 = new GridBagConstraints();
		gbc_label4_4_3.anchor = GridBagConstraints.EAST;
		gbc_label4_4_3.insets = new Insets(0, 0, 0, 5);
		gbc_label4_4_3.gridx = 4;
		gbc_label4_4_3.gridy = 7;
		panel_40.add(label4_4_3, gbc_label4_4_3);
		
		textField4_4_3 = new JTextField(String.format("%.2f", msystem.pumps()[3].parη_VFD));
		textField4_4_3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				textfieldVFD(textField4_4_3,3);
			}
		});
		textField4_4_3.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					textfieldVFD(textField4_4_3,3);
				}
			}
		});
		textField4_4_3.setColumns(10);
		GridBagConstraints gbc_textField4_4_3 = new GridBagConstraints();
		gbc_textField4_4_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_4_3.gridx = 5;
		gbc_textField4_4_3.gridy = 7;
		panel_40.add(textField4_4_3, gbc_textField4_4_3);
		
		JPanel panel_42 = new JPanel();
		panel_42.setForeground(Color.BLACK);
		GridBagLayout gbl_panel_42 = new GridBagLayout();
		gbl_panel_42.columnWidths = new int[] { 40, 98, 150,40,98 };
		gbl_panel_42.rowHeights = new int[] { 40, 60, 60, 60, 40, 50,40,50};
		gbl_panel_42.columnWeights = new double[] { 1.0, 1.0, 1.0,1.0,1.0 };
		gbl_panel_42.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0,1.0,1.0};
		panel_42.setLayout(gbl_panel_42);
		
		
		
		String[] powerList = { "泵效率参数", "电费和压力参数" };
		JPanel powerpanel = new JPanel();
		JList powerlist = new JList(powerList);
		powerlist.setFixedCellWidth(125);
		powerlist.setSelectedIndex(0);
		JSplitPane powerjsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, powerlist, powerpanel);
		powerpanel.add(panel_40);
		
		JLabel label4_5 = new JLabel("电费单位成本");
		GridBagConstraints gbc_label4_5 = new GridBagConstraints();
		gbc_label4_5.insets = new Insets(0, 0, 5, 5);
		gbc_label4_5.gridx = 1;
		gbc_label4_5.gridy = 2;
		panel_42.add(label4_5, gbc_label4_5);
		
		textField4_5_1 = new JTextField(String.format("%.2f", msystem.e));
		textField4_5_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				msystem.e = Double.parseDouble(textField4_5_1.getText());
				textField4_5_1.setText(String.format("%.2f", msystem.e));
			}
		});
		textField4_5_1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					msystem.e = Double.parseDouble(textField4_5_1.getText());
					textField4_5_1.setText(String.format("%.2f", msystem.e));
				}
			}
		});
		GridBagConstraints gbc_textField4_5_1 = new GridBagConstraints();
		gbc_textField4_5_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField4_5_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_5_1.gridx = 2;
		gbc_textField4_5_1.gridy = 2;
		panel_42.add(textField4_5_1, gbc_textField4_5_1);
		textField4_5_1.setColumns(10);
		
		JLabel label4_5_1 = new JLabel("元/kWh");
		GridBagConstraints gbc_label4_5_1 = new GridBagConstraints();
		gbc_label4_5_1.insets = new Insets(0, 0, 5, 5);
		gbc_label4_5_1.gridx = 3;
		gbc_label4_5_1.gridy = 2;
		panel_42.add(label4_5_1, gbc_label4_5_1);
		
		JLabel label4_6 = new JLabel("低压泵压力");
		GridBagConstraints gbc_label4_6 = new GridBagConstraints();
		gbc_label4_6.insets = new Insets(0, 0, 5, 5);
		gbc_label4_6.gridx = 1;
		gbc_label4_6.gridy = 3;
		panel_42.add(label4_6, gbc_label4_6);
		
		textField4_6_1 = new JTextField(String.format("%.2f", msystem.pumps()[0].parP));
		textField4_6_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				msystem.pumps()[0].parP = Double.parseDouble(textField4_6_1.getText());
				textField4_6_1.setText(String.format("%.2f", msystem.pumps()[0].parP));
			}
		});
		textField4_6_1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
				{
					msystem.pumps()[0].parP = Double.parseDouble(textField4_6_1.getText());
					textField4_6_1.setText(String.format("%.2f", msystem.pumps()[0].parP));
				}
			}
		});
		GridBagConstraints gbc_textField4_6_1 = new GridBagConstraints();
		gbc_textField4_6_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField4_6_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField4_6_1.gridx = 2;
		gbc_textField4_6_1.gridy = 3;
		panel_42.add(textField4_6_1, gbc_textField4_6_1);
		textField4_6_1.setColumns(10);
		
		JLabel label4_6_1 = new JLabel("MPa");
		GridBagConstraints gbc_label4_6_1 = new GridBagConstraints();
		gbc_label4_6_1.insets = new Insets(0, 0, 5, 5);
		gbc_label4_6_1.gridx = 3;
		gbc_label4_6_1.gridy = 3;
		panel_42.add(label4_6_1, gbc_label4_6_1);
		powerlist.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (powerlist.getSelectedIndex() == 0) {
					powerjsp.remove(powerjsp.getRightComponent());
					powerpanel.remove(panel_42);
					powerpanel.add(panel_40);
					powerjsp.add(powerpanel, JSplitPane.RIGHT);
				} else if (powerlist.getSelectedIndex() == 1) {
					powerjsp.remove(powerjsp.getRightComponent());
					powerpanel.remove(panel_40);
					powerpanel.add(panel_42);
					powerjsp.add(powerpanel, JSplitPane.RIGHT);
				}
			}
		});
		powerjsp.setDividerSize(1);
		powerjsp.setEnabled(false);

		GroupLayout gl_panelPower = new GroupLayout(panelPower);
		gl_panelPower.setHorizontalGroup(gl_panelPower.createParallelGroup(Alignment.LEADING).addComponent(powerjsp,
				GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE));
		gl_panelPower.setVerticalGroup(
				gl_panelPower.createParallelGroup(Alignment.LEADING).addGroup(gl_panelPower.createSequentialGroup()
						.addComponent(powerjsp, GroupLayout.PREFERRED_SIZE, 394, Short.MAX_VALUE).addContainerGap()));
		panelPower.setLayout(gl_panelPower);
		
		getContentPane().setLayout(groupLayout);
		// 按钮事件
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					msystem.calculate();
					DisplayDiag displaydiag = new DisplayDiag(msystem);
					displaydiag.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "错误！", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public void textfieldparcj(JTextField jtf,int i) {
		try {
			if (Double.parseDouble(jtf.getText()) < 0) {
				JOptionPane.showMessageDialog(getContentPane(), "请输入不小于0的数值", "错误！", JOptionPane.ERROR_MESSAGE);
			} else {
				msystem.streams.cods()[i].parcj = Double.parseDouble(jtf.getText());
			}
			jtf.setText(String.format("%.2f", msystem.streams.cods()[i].parcj));
		} catch (Exception e2) {
			jtf.setText(String.format("%.2f", msystem.streams.cods()[i].parcj));
		}
	}

	public void textfieldROD(JTextField jtf,int i) {
		try {
			if (Double.parseDouble(jtf.getText()) > 1) {
				JOptionPane.showMessageDialog(getContentPane(), "截留率是不大于1的数值", "错误！", JOptionPane.ERROR_MESSAGE);
			} else if (Double.parseDouble(jtf.getText()) < 0) {
				JOptionPane.showMessageDialog(getContentPane(), "截留率是不小于0的数值", "错误！", JOptionPane.ERROR_MESSAGE);
			} else {
				msystem.streams.cods()[i].parRCOD = Double.parseDouble(jtf.getText());
			}
			jtf.setText(String.format("%.4f", msystem.streams.cods()[i].parRCOD));
		} catch (Exception e2) {
			jtf.setText(String.format("%.4f", msystem.streams.cods()[i].parRCOD));
		}
	}
	public void textfieldPJ(JTextField jtf,int i) {
		try {
			if (Double.parseDouble(jtf.getText()) > 1) {
				JOptionPane.showMessageDialog(getContentPane(), "效率是不大于1的数值", "错误！", JOptionPane.ERROR_MESSAGE);
			} else if (Double.parseDouble(jtf.getText()) < 0) {
				JOptionPane.showMessageDialog(getContentPane(), "效率是不小于0的数值", "错误！", JOptionPane.ERROR_MESSAGE);
			} else {
				msystem.pumps()[i].parη_PJ = Double.parseDouble(jtf.getText());
			}
			jtf.setText(String.format("%.2f",msystem.pumps()[i].parη_PJ));
		} catch (Exception e2) {
			jtf.setText(String.format("%.2f", msystem.pumps()[i].parη_PJ));
		}
	}
	public void textfieldMJ(JTextField jtf,int i) {
		try {
			if (Double.parseDouble(jtf.getText()) > 1) {
				JOptionPane.showMessageDialog(getContentPane(), "效率是不大于1的数值", "错误！", JOptionPane.ERROR_MESSAGE);
			} else if (Double.parseDouble(jtf.getText()) < 0) {
				JOptionPane.showMessageDialog(getContentPane(), "效率是不小于0的数值", "错误！", JOptionPane.ERROR_MESSAGE);
			} else {
				msystem.pumps()[i].parη_MJ = Double.parseDouble(jtf.getText());
			}
			jtf.setText(String.format("%.2f",msystem.pumps()[i].parη_MJ));
		} catch (Exception e2) {
			jtf.setText(String.format("%.2f", msystem.pumps()[i].parη_MJ));
		}
	}
	public void textfieldVFD(JTextField jtf,int i) {
		try {
			if (Double.parseDouble(jtf.getText()) > 1) {
				JOptionPane.showMessageDialog(getContentPane(), "效率是不大于1的数值", "错误！", JOptionPane.ERROR_MESSAGE);
			} else if (Double.parseDouble(jtf.getText()) < 0) {
				JOptionPane.showMessageDialog(getContentPane(), "效率是不小于0的数值", "错误！", JOptionPane.ERROR_MESSAGE);
			} else {
				msystem.pumps()[i].parη_VFD = Double.parseDouble(jtf.getText());
			}
			jtf.setText(String.format("%.2f",msystem.pumps()[i].parη_VFD));
		} catch (Exception e2) {
			jtf.setText(String.format("%.2f", msystem.pumps()[i].parη_VFD));
		}
	}
	
	
}
