import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Product {
    private String name;
    private String description;
    private ImageIcon image;

    public Product(String name, String description, ImageIcon image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getImage() {
        return image;
    }
}

public class ProductCatalog extends JFrame {
    private JPanel productPanel;

    public ProductCatalog(Product[] products) {
        setTitle("Product Catalog");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(0, 3)); // Hiển thị 3 sản phẩm mỗi hàng

        // Tạo các nút sản phẩm
        for (Product product : products) {
            JButton productButton = new JButton(product.getImage());
            productButton.setToolTipText(product.getName());
            productButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    showProductInfo(product);
                }
            });
            productPanel.add(productButton);
        }

        add(new JScrollPane(productPanel), BorderLayout.CENTER);
    }
    private void showProductInfo(Product product) {
        JFrame productInfoFrame = new JFrame(product.getName());
        productInfoFrame.setSize(300, 200);
        productInfoFrame.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(product.getImage());
        JTextArea descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setEditable(false);

        productInfoFrame.add(imageLabel, BorderLayout.NORTH);
        productInfoFrame.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);

        productInfoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Tạo dữ liệu sản phẩm mẫu
        Product[] products = {
            new Product("Product 1", "Description of Product 1", new ImageIcon("E:/img/cake1.jpg")),
            new Product("Product 2", "Description of Product 2", new ImageIcon("E:/img/cake2.jpg")),
            new Product("Product 3", "Description of Product 3", new ImageIcon("E:/img/cake3.jpg")),
            new Product("Product 4", "Description of Product 4", new ImageIcon("E:/img/cake4.jpg")),
            new Product("Product 5", "Description of Product 5", new ImageIcon("E:/img/cake5.jpg"))
        };

        // Tạo giao diện hiển thị sản phẩm
        ProductCatalog catalog = new ProductCatalog(products);
        catalog.setVisible(true);
    }
}
