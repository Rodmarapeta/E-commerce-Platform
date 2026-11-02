package ecommerce.ui;

import ecommerce.model.Order;
import ecommerce.model.User;
import ecommerce.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderHistoryDialog extends JDialog {
    private final User user;
    private final List<Order> orders = new ArrayList<>();
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"Order #", "Placed", "Items", "Total", "Status"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(model);
    private final JButton viewButton = UiTheme.primary("View receipt");

    public OrderHistoryDialog(Window owner, User user) {
        super(owner, "Order history", ModalityType.APPLICATION_MODAL);
        this.user = user;

        setSize(800, 520);
        setMinimumSize(new Dimension(720, 440));
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        getContentPane().setBackground(UiTheme.BG);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UiTheme.SURFACE);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, UiTheme.BORDER),
                new EmptyBorder(16, 20, 16, 20)));
        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));
        titles.add(UiTheme.heading("Order history"));
        titles.add(UiTheme.muted(user.getName() + " \u00B7 " + user.getEmail()));
        header.add(titles, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        UiTheme.styleTable(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(170);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setCellRenderer(UiTheme.cellRenderer(SwingConstants.CENTER));
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setCellRenderer(UiTheme.cellRenderer(SwingConstants.RIGHT));

        JPanel card = new UiTheme.RoundedPanel(UiTheme.SURFACE, 12);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(8, 8, 8, 8));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(UiTheme.SURFACE);
        card.add(scroll, BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.setOpaque(false);
        center.setBorder(new EmptyBorder(16, 16, 16, 16));
        center.add(card, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(UiTheme.BG);
        footer.setBorder(new EmptyBorder(0, 16, 16, 16));
        footer.add(UiTheme.muted("Double-click an order to open its receipt."), BorderLayout.WEST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actions.setOpaque(false);
        JButton close = UiTheme.secondary("Close");
        close.addActionListener(e -> dispose());
        actions.add(close);
        actions.add(viewButton);
        footer.add(actions, BorderLayout.EAST);
        add(footer, BorderLayout.SOUTH);

        viewButton.setEnabled(false);
        viewButton.addActionListener(e -> openSelected());
        table.getSelectionModel().addListSelectionListener(
                e -> viewButton.setEnabled(table.getSelectedRow() >= 0));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    openSelected();
                }
            }
        });

        load();
    }

    private void load() {
        orders.clear();
        List<Order> history = new ArrayList<>(user.getOrderHistory());
        Collections.reverse(history);
        orders.addAll(history);

        model.setRowCount(0);
        for (Order order : orders) {
            model.addRow(new Object[]{
                    order.getOrderId(),
                    Util.dateTime(order.getPlacedAt()),
                    order.itemCount(),
                    Util.money(order.getTotalPrice()),
                    order.getStatus()
            });
        }
        if (orders.isEmpty()) {
            model.addRow(new Object[]{"No orders yet", "", "", "", ""});
            table.setEnabled(false);
        }
        viewButton.setEnabled(false);
    }

    private void openSelected() {
        int row = table.getSelectedRow();
        if (row < 0 || row >= orders.size()) {
            return;
        }
        new ReceiptDialog(this, orders.get(row)).setVisible(true);
    }
}
