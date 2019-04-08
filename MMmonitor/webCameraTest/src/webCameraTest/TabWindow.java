package webCameraTest;

/**
 * @author WSL
 * Date: 01/04/2019
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

public class TabWindow extends JFrame {
	/**
	 * @param N the initialize range of the x-label
	 * @param   N+random=the x-label range
	 */
	private static final int N = 128;
	private static final Random random = new Random();
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		init();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabWindow frame = new TabWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * ����Ƥ��
	 */
	public static void init() {
		try {
//		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
//+1		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
//		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//+3		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
//		          javax.swing.UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
			// handle exception
		}
	}

	/**
	 * ������������ Component_TabbedPane_Create() ��������������ؽ��� Delay_TabbedPane_Create()
	 * ����ʱ�Ӽ�ؽ���
	 */
	public TabWindow() {
		getContentPane().setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(BorderLayout.CENTER, tabbedPane);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		setTitle("������г�ʵʱ���");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(screenSize.width, screenSize.height));
		Component_TabbedPane_Create(tabbedPane, 0, this);
		Delay_TabbedPane_Create(tabbedPane, 1);
	}

	/**
	 * �����JTabbedPane��Ϊ���� ����µ�JTabbedPaneѡ�(6��ʱ�Ӽ�ؽ���) CreatePane_Chart()
	 * ������ͬ����������ʱ�Ӽ��JFreeChart
	 */
	public void Delay_TabbedPane_Create(JTabbedPane jtp, int ct) {
		JTabbedPane tp = new JTabbedPane();
		tp.setTabPlacement(JTabbedPane.LEFT);
		tp.add("����ͷʱ��", CreatePane_Chart("Camera Delay"));
		tp.add("�״�ʱ��", CreatePane_Chart("Radar Delay"));
		tp.addTab("GPSʱ��", CreatePane_Chart("GPS Delay"));
		tp.add("��γ��ʱ��", CreatePane_Chart("Theodolite Delay"));
		tp.add("TSPIʱ��", CreatePane_Chart("TSPI Delay"));
		tp.add("��Դ�ֿ�ʱ��", CreatePane_Chart("SourceRepository Delay"));
		jtp.add(tp, "ʱ�Ӽ��");
	}

	/**
	 * ��������ʱ��JFreeChart
	 */
	private ChartPanel CreatePane_Chart(String componentName) {
		DefaultCategoryDataset series = new DefaultCategoryDataset();
		for (int i = 0; i < 101; i++) {
			series.addValue(Math.abs(random.nextGaussian()), "ʱ��", String.valueOf(i));
		}

		JFreeChart chart = ChartFactory.createBarChart(componentName, "Times", "Delay Time(ms)", series, PlotOrientation.VERTICAL,
				false, false, false);
		chart.setBorderVisible(true);
		Font titleFont = new Font("����", Font.BOLD, 25); // ͼƬ����
		// ͼƬ����
		chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
		// �ײ�
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		categoryplot.setDomainGridlinesVisible(true);
		categoryplot.setRangeCrosshairVisible(true);
		categoryplot.setBackgroundPaint(Color.BLACK);
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
		barrenderer.setBarPainter(new StandardBarPainter());

		for (int i = 0; i < series.getColumnCount(); i++) {
			barrenderer.setSeriesPaint(i, Color.GREEN, true);
		}

		barrenderer.setAutoPopulateSeriesShape(true);
		barrenderer.setBaseItemLabelFont(new Font("����", Font.PLAIN, 12));
		barrenderer.setSeriesItemLabelFont(1, new Font("����", Font.PLAIN, 12));
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		/*------����X�������ϵ�����-----------*/
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 10));
		/*------����x������ת����-----------*/
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90); 
		/*------����X��ı�������------------*/
		domainAxis.setLabelFont(new Font("����", Font.PLAIN, 22));
		/*------����Y�������ϵ�����-----------*/
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		/*------����Y��ı�������------------*/
		numberaxis.setLabelFont(new Font("����", Font.PLAIN, 22));
		/*------���������˵ײ��������������-----------*/

		Counter counter = new Counter();
		counter.setCount(series.getColumnCount());

		new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				series.removeColumn(0);
				series.addValue(Math.abs(random.nextGaussian()), "ʱ��", String.valueOf(counter.getCount()));
				counter.CountAdd1();
			}
		}).start();
		return new ChartPanel(chart) {
			/**
			 * @return
			 * 
			 */

			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {

				return new Dimension(480, 240);
			}

		};
	}

	/**
	 * ����������ؽ��� �Ѽ�ؽ�����ӵ�JPanel Ȼ���ܵ�JPanel��ӵ�ѡ���
	 *
	 * �˶δ������� ���ع�
	 */
	public void Component_TabbedPane_Create(JTabbedPane jtp, int ct, TabWindow tabWindow) {
//		JPanel panel = new JPanel();
//		GridLayout gridlayout = new GridLayout(2, 3, 50, 50);
//		panel.setLayout(gridlayout);
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(0, 0, 1366, 700);
		int wid = panel.getWidth();
		int hei = panel.getHeight();
		int wi = wid / 20;
		int hi = hei / 15;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi,
				wi, wi };
		gridBagLayout.rowHeights = new int[] { hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi };
		tabWindow.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int wid = tabWindow.getWidth();
				int hei = tabWindow.getHeight();
				int wi = wid / 20;
				int hi = hei / 15;
				gridBagLayout.columnWidths = new int[] { wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi, wi,
						wi, wi, wi, wi };
				gridBagLayout.rowHeights = new int[] { hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi, hi };
			}
		});
		panel.setLayout(gridBagLayout);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;

		JLabel label_CamaraTitle = new JLabel("CCD", JLabel.CENTER);
		label_CamaraTitle.setSize(500, 100);
		Font fnt = new Font("Serief", Font.BOLD, 20);
		label_CamaraTitle.setFont(fnt);
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());
		WebcamPanel webpanel = new WebcamPanel(webcam);
		webpanel.setDisplayDebugInfo(true);
		webpanel.setFPSDisplayed(true);
		// webpanel.setDisplayDebugInfo(true);
		webpanel.setImageSizeDisplayed(true);
		webpanel.setMirrored(true);

		AddChart_Tabbed tab2 = new AddChart_Tabbed();
		AddChart_Tabbed tab3 = new AddChart_Tabbed();
		AddChart_Tabbed tab4 = new AddChart_Tabbed();
		AddChart_Tabbed tab5 = new AddChart_Tabbed();
		AddChart_Tabbed tab6 = new AddChart_Tabbed();

		ChartPanel cp2 = tab2.createPane("�״�", "ʱ�� (s)", "�ز�ǿ��");
		ChartPanel cp3 = tab3.createPane("GPS", "γ��", "����");
		ChartPanel cp4 = tab4.createPane("��γ��", "ˮƽ��", "�߶Ƚ�");
		ChartPanel cp5 = tab5.createPane("TSPI", "����(x)", "����(y)");
		ChartPanel cp6 = tab6.createPane("��Դ�ֿ�", "����(x)", "����(y)");

		cp2.setSize(500, 600);
		cp3.setSize(500, 600);
		cp4.setSize(500, 600);
		cp5.setSize(500, 600);
		cp6.setSize(500, 600);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.gridheight = 1;
		gridBagLayout.setConstraints(label_CamaraTitle, gridBagConstraints);

		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 5;
		gridBagConstraints.gridheight = 5;
		gridBagLayout.setConstraints(webpanel, gridBagConstraints);

		gridBagConstraints.gridx = 6;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp2, gridBagConstraints);

		gridBagConstraints.gridx = 13;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp3, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp4, gridBagConstraints);

		gridBagConstraints.gridx = 6;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp5, gridBagConstraints);

		gridBagConstraints.gridx = 13;
		gridBagConstraints.gridy = 8;
		gridBagConstraints.gridwidth = 6;
		gridBagConstraints.gridheight = 7;
		gridBagLayout.setConstraints(cp6, gridBagConstraints);

		panel.add(label_CamaraTitle);
		panel.add(webpanel);
		panel.add(cp2);
		panel.add(cp3);
		panel.add(cp4);
		panel.add(cp5);
		panel.add(cp6);

		addAction(cp2, tab2);
		addAction(cp3, tab3);
		addAction(cp4, tab4);
		addAction(cp5, tab5);
		addAction(cp6, tab6);
		jtp.add(panel, "������");

	}

	public void addAction(ChartPanel cp1, final AddChart_Tabbed tab1) {
		cp1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

}
