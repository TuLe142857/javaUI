// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class SidebarMenuExample extends JFrame {
//     private JPanel sidebar;
//     private JPanel mainPanel;

//     public SidebarMenuExample() {
//         setTitle("Application with Sidebar Menu");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(800, 600);
//         setLayout(new BorderLayout());

//         // Tạo Sidebar
//         sidebar = new JPanel();
//         sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
//         sidebar.setPreferredSize(new Dimension(200, 600));
//         sidebar.setBackground(Color.LIGHT_GRAY);

//         // Thêm các nút vào Sidebar
//         JButton btn1 = new JButton("Menu 1");
//         JButton btn2 = new JButton("Menu 2");
//         JButton btn3 = new JButton("Menu 3");

//         sidebar.add(btn1);
//         sidebar.add(btn2);
//         sidebar.add(btn3);

//         // Tạo Main Panel
//         mainPanel = new JPanel();
//         mainPanel.setLayout(new CardLayout());

//         // Thêm các panel cho mỗi menu
//         mainPanel.add(createPanel("Content of Menu 1"), "Menu 1");
//         mainPanel.add(createPanel("Content of Menu 2"), "Menu 2");
//         mainPanel.add(createPanel("Content of Menu 3"), "Menu 3");

//         // ActionListener cho các nút trong Sidebar
//         btn1.addActionListener(e -> showPanel("Menu 1"));
//         btn2.addActionListener(e -> showPanel("Menu 2"));
//         btn3.addActionListener(e -> showPanel("Menu 3"));

//         // Thêm Sidebar và Main Panel vào Frame
//         add(sidebar, BorderLayout.WEST);
//         add(mainPanel, BorderLayout.CENTER);

//         setVisible(true);
//     }

//     // Phương thức tạo một panel với nội dung
//     private JPanel createPanel(String text) {
//         JPanel panel = new JPanel();
//         panel.add(new JLabel(text));
//         panel.setBackground(Color.WHITE);
//         return panel;
//     }

//     // Phương thức để hiển thị panel tương ứng với menu đã chọn
//     private void showPanel(String panelName) {
//         CardLayout cl = (CardLayout) (mainPanel.getLayout());
//         cl.show(mainPanel, panelName);
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(SidebarMenuExample::new);
//     }
// }

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidebarMenuExample extends JFrame {
    private JPanel sidebar;
    private JPanel mainPanel;
    private boolean sidebarVisible = true; // Trạng thái của sidebar

    public SidebarMenuExample() {
        setTitle("Application with Sidebar Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Tạo Sidebar
        sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, 600));
        sidebar.setBackground(Color.LIGHT_GRAY);

        // Thêm các nút vào Sidebar
        JButton btn1 = new JButton("Menu 1");
        JButton btn2 = new JButton("Menu 2");
        JButton btn3 = new JButton("Menu 3");

        sidebar.add(btn1);
        sidebar.add(btn2);
        sidebar.add(btn3);

        // Tạo nút ẩn/hiện Sidebar
        JButton toggleButton = new JButton("Toggle Sidebar");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSidebar();
            }
        });

        // Tạo Main Panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());

        // Thêm các panel cho mỗi menu
        mainPanel.add(createPanel("Content of Menu 1"), "Menu 1");
        mainPanel.add(createPanel("Content of Menu 2"), "Menu 2");
        mainPanel.add(createPanel("Content of Menu 3"), "Menu 3");

        // ActionListener cho các nút trong Sidebar
        btn1.addActionListener(e -> showPanel("Menu 1"));
        btn2.addActionListener(e -> showPanel("Menu 2"));
        btn3.addActionListener(e -> showPanel("Menu 3"));

        // Thêm Sidebar, Toggle Button và Main Panel vào Frame
        add(toggleButton, BorderLayout.NORTH); // Nút ở trên cùng
        add(sidebar, BorderLayout.WEST);       // Sidebar bên trái
        add(mainPanel, BorderLayout.CENTER);   // Main panel ở giữa

        setVisible(true);
    }

    // Phương thức tạo một panel với nội dung
    private JPanel createPanel(String text) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(text));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    // Phương thức để hiển thị panel tương ứng với menu đã chọn
    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, panelName);
    }

    // Phương thức ẩn/hiện Sidebar
    private void toggleSidebar() {
        sidebarVisible = !sidebarVisible;
        sidebar.setVisible(sidebarVisible); // Ẩn hoặc hiện Sidebar
        revalidate(); // Cập nhật giao diện
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SidebarMenuExample::new);
    }
}

