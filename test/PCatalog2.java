import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Lớp Product ở bên ngoài class PCatalog2
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

public class PCatalog2 extends JFrame {
    
    private JPanel mainPanel;
    private JPanel productPanel;
    private CardLayout cardLayout;
    private int currentPage = 0;
    private static final int PRODUCTS_PER_PAGE = 15; // 3 cột x 5 dòng
    private Product[] products;

    public PCatalog2(Product[] products) {
        this.products = products;
        setTitle("Product Catalog");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        productPanel = new JPanel(new GridLayout(5, 3, 10, 10)); // 5 dòng, 3 cột

        // Tạo các khối sản phẩm và thêm vào productPanel
        updateProductPanel();

        // Tạo JScrollPane để có thể cuộn qua danh sách sản phẩm
        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Cho phép cuộn ngang

        // Tạo panel cho nút Previous và Next
        JPanel navigationPanel = new JPanel();
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        // Thêm ActionListener cho nút Previous
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentPage > 0) {
                    currentPage--;
                    updateProductPanel();
                    cardLayout.show(nextButton, getName());
                }
            }
        });

        // Thêm ActionListener cho nút Next
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((currentPage + 1) * PRODUCTS_PER_PAGE < products.length) {
                    currentPage++;
                    updateProductPanel();
                }
            }
        });

        // Thêm nút vào navigationPanel
        navigationPanel.add(prevButton);
        navigationPanel.add(nextButton);

        // Thêm scrollPane và navigationPanel vào mainPanel
        mainPanel.add(scrollPane, "ProductList");
        mainPanel.add(navigationPanel, "Navigation");

        // Thêm mainPanel vào frame
        add(mainPanel);
    }

    // Phương thức cập nhật danh sách sản phẩm dựa trên trang hiện tại
    private void updateProductPanel() {
        productPanel.removeAll(); // Xóa sản phẩm cũ
        int start = currentPage * PRODUCTS_PER_PAGE;
        int end = Math.min(start + PRODUCTS_PER_PAGE, products.length);

        // Tạo các khối sản phẩm mới cho trang hiện tại
        for (int i = start; i < end; i++) {
            Product product = products[i];
            JPanel productItem = new JPanel(new BorderLayout());
            productItem.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Thêm đường viền cho mỗi khối sản phẩm
            
            // Tạo JLabel để hiển thị hình ảnh
            JLabel productImageLabel = new JLabel(product.getImage());
            productImageLabel.setPreferredSize(new Dimension(100, 100)); // Kích thước hình ảnh
            
            // Tạo JLabel để hiển thị tóm tắt mô tả sản phẩm
            JLabel summaryLabel = new JLabel("<html>" + product.getDescription() + "</html>");
            summaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            // Thêm MouseListener cho productItem để hiển thị thông tin chi tiết sản phẩm
            productItem.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showProductInfo(product);
                }
            });

            // Thêm thành phần vào productItem
            productItem.add(productImageLabel, BorderLayout.CENTER);
            productItem.add(summaryLabel, BorderLayout.SOUTH);
            productPanel.add(productItem);
        }

        productPanel.revalidate(); // Cập nhật lại giao diện
        productPanel.repaint(); // Vẽ lại giao diện
    }

    // Phương thức hiển thị thông tin sản phẩm khi nhấn chuột
    private void showProductInfo(Product product) {
        JPanel productInfoPanel = new JPanel(new BorderLayout());

        JLabel imageLabel = new JLabel(product.getImage());
        JTextArea descriptionArea = new JTextArea(product.getDescription());
        descriptionArea.setEditable(false);

        // Nút "Back" để quay lại danh sách sản phẩm
        JButton backButton = new JButton("Back to Products");
        backButton.addActionListener(e -> updateProductPanel());

        productInfoPanel.add(imageLabel, BorderLayout.NORTH);
        productInfoPanel.add(new JScrollPane(descriptionArea), BorderLayout.CENTER);
        productInfoPanel.add(backButton, BorderLayout.SOUTH);

        // Thêm productInfoPanel vào mainPanel và hiển thị nó
        mainPanel.add(productInfoPanel, product.getName());
        cardLayout.show(mainPanel, product.getName());
    }

    public static void main(String[] args) {
        // Dữ liệu sản phẩm mẫu
        Product[] products = new Product[30]; // Tạo 30 sản phẩm mẫu
        for (int i = 0; i < products.length; i++) {
            products[i] = new Product("Product " + (i + 1), "Tóm tắt về sản phẩm " + (i + 1), new ImageIcon("E:/img/cake" + (i + 1) + ".jpg"));
        }

        // Tạo giao diện sản phẩm
        PCatalog2 catalog = new PCatalog2(products);
        catalog.setVisible(true);
    }
}
