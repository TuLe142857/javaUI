import java.awt.*;
import javax.swing.*;

import idea.*;
import idea.*;
public class Main {
	public static void main(String []args){
		// JFrame frame = new JFrame();

		// StarRating rating = new StarRating();
		// frame.add(rating);
		// frame.setLayout(new GridLayout(3, 2));
		// for(int i = 0; i < 1 ; i++){
		// 	// StarRating rating = new StarRating();
		// 	// frame.add(rating);
		// 	frame.add(new ProductCatalog());
		// }

		// JPanel mainPanel = new JPanel(new GridLayout(10, 3, 10, 10));
		// mainPanel.setBackground(Color.CYAN);
		// for(int i = 0; i < 30; i ++)
		// 	mainPanel.add(new ProductCatalog());

		// JScrollPane scrollPanel = new JScrollPane(mainPanel);
        // scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// frame.add(scrollPanel);
		// frame.setSize((Toolkit.getDefaultToolkit().getScreenSize().width)*2/3, (Toolkit.getDefaultToolkit().getScreenSize().height)*2/3 );
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setTitle("Main");
		// frame.setVisible(true);

		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
}

class MainFrame extends JFrame{
	private static int rows = 10;
	private static int cols = 3;
	private JScrollPane mainPanel;
	private JPanel fillterPanel;
	private JPanel sidebarPanel;


	public MainFrame(){
		initFrame();
		initSidebarPanel();
		initMainPanel();


		this.add(mainPanel);
	}

	private void initFrame(){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(d.width*2/3, d.height*2/3);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setTitle("Xin chao");
	}

	private void initFilterPanel(){

	}
	private void initSidebarPanel(){
		sidebarPanel = new JPanel();


	}

	private void initMainPanel(){
		JPanel panel = new JPanel(new GridLayout(rows, cols, 10, 10));
		panel.setBackground(Color.CYAN);
		for(int i = 0; i < rows*cols; i ++)
			panel.add(new ProductCatalog());

		mainPanel = new JScrollPane(panel);
        mainPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		
	}
}

class ProductCatalog extends JPanel{
	// private ImageIcon image;


	// private String name;
	// private String strPrice;
	// private StarRating rating;

	public ProductCatalog(){
		this("cake1.jpg", "Product name", 10000, 3);
	}


	public ProductCatalog(String imagePath, String name, int price, double ratingPoint){
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.gridheight = 5;
		gbc.insets = new Insets(10, 10, 0, 10);
		this.add(new JLabel(new ImageIcon(imagePath)), gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 10, 0, 10);
		this.add(new JLabel("<html><span style='color: black;font-size: 20px'>" + name + "</span> "), gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 5;
		gbc.gridheight = 1;
		gbc.insets = new Insets(0, 10, 0, 10);
		this.add(new JLabel(
			String.format(
				"<html><span style= 'color:#FFCCCB;font-size: 15px' ><strike>9999999</strike></span>   <span style='color:red;font-size: 15px'>%d</span></html>", price)
		), gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0; // Không giãn ra theo chiều ngang
        gbc.weighty = 0; // Không giãn ra theo chiều dọc
		gbc.insets = new Insets(0, 10, 16, 16);
		StarRating  r = new StarRating(ratingPoint, 32, 32);
		r.setBackground(Color.WHITE);

		r.adjustRatingValueWhenClick(false);
		this.add(r, gbc);
	}
}
