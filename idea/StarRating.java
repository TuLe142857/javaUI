package idea;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class StarRating extends JPanel{
	private ImageIcon []starIcon = new ImageIcon[11];
	private JButton []starButton = new JButton[5];
	private double ratingValue;

	public StarRating(){
		this(0, 16, 16);
	}

	public StarRating(double ratingValue, int starIconWidth, int starIconHeight){
		this.setLayout(new GridLayout(1, 5));
		for(int i = 0; i < 5; i++){
			starButton[i] = new JButton();
			starButton[i].setBackground(Color.WHITE);
			starButton[i].setPreferredSize(new Dimension(starIconWidth+2, starIconHeight+2));
			starButton[i].setBorderPainted(false);
			starButton[i].setOpaque(false);
			this.add(starButton[i]);
		}

		for(int i = 0; i < 11; i++){
			// starIcon[i] = new ImageIcon("E:/GitRepo/javaUI/idea/image/star" + ".png");

			try {
            // Tải hình ảnh từ tệp
            Image image = ImageIO.read(new File("E:/GitRepo/javaUI/idea/image/star" + ".png"));
			image = image.getScaledInstance(starIconWidth, starIconHeight, Image.SCALE_SMOOTH);
			starIcon[i] = new ImageIcon(image);

            // Xử lý hình ảnh (ví dụ: hiển thị, điều chỉnh kích thước, v.v.)
        	} catch (IOException e) {
            	e.printStackTrace();
        	}
		}
		// adjustRatingValueWhenClick(true);
		setRatingValue(ratingValue);
	}

	public void setRatingValue(double ratingValue){
		this.ratingValue = ratingValue;
		System.out.println("setcalled");
		for(int i = 0; i < 5; i++){
			if(ratingValue < 1){
				int value = (int)(ratingValue*10);
				System.out.println(i + " " + value);
				starButton[i].setIcon(starIcon[value]);
				ratingValue = 0;
			}
			else{
				starButton[i].setIcon(starIcon[10]);
				ratingValue -= 1;
			}
		}
	}

	public double getRatingValue(){
		return ratingValue;
		
	}

	@Override
	public void setBackground(Color c){
		super.setBackground(c);
		// for(int i = 0; i <5; i++)
		// 	starButton[i].setBackground(c);
	}

	public void adjustRatingValueWhenClick(boolean b){
		if(b){
			for(int i = 0; i < 5; i++)
				starButton[i].addActionListener(new SetRatingValueWhenClickAction(i+1));
		}else
			for(int i = 0; i < 5; i++)
				starButton[i].addActionListener(e->{
					//Khong lam gi het
				});
	}

	private class SetRatingValueWhenClickAction implements ActionListener{
		private int index;
		public SetRatingValueWhenClickAction(int index){
			this.index = index;
		}
		public void actionPerformed(ActionEvent e){
			System.out.println("start button " + index + " clicked");
			setRatingValue((double)index);
		}
	}
}