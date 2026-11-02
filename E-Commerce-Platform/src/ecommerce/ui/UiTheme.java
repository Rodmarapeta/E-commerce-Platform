package ecommerce.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public final class UiTheme {
    public static final Color BG = new Color(0xF4F6FB);
    public static final Color SURFACE = Color.WHITE;
    public static final Color PRIMARY = new Color(0x2F6FED);
    public static final Color PRIMARY_DARK = new Color(0x1E52C2);
    public static final Color PRIMARY_SOFT = new Color(0xE8F0FF);
    public static final Color SUCCESS = new Color(0x17925A);
    public static final Color DANGER = new Color(0xD63A3A);
    public static final Color TEXT = new Color(0x1D2333);
    public static final Color MUTED = new Color(0x68708A);
    public static final Color BORDER = new Color(0xDCE2EF);

    public static final Font BODY = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font BODY_BOLD = BODY.deriveFont(Font.BOLD);
    public static final Font SMALL = BODY.deriveFont(11f);
    public static final Font H1 = new Font("Segoe UI", Font.BOLD, 25);
    public static final Font H2 = new Font("Segoe UI", Font.BOLD, 17);
    public static final Font MONO = new Font("Consolas", Font.PLAIN, 12);

    private UiTheme() {
    }

    public static void install() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
            // Default look and feel is fine if the system one is unavailable.
        }
        UIManager.put("Label.font", BODY);
        UIManager.put("Button.font", BODY_BOLD);
        UIManager.put("TextField.font", BODY);
        UIManager.put("ComboBox.font", BODY);
        UIManager.put("Table.font", BODY);
        UIManager.put("TableHeader.font", BODY_BOLD);
        UIManager.put("OptionPane.messageFont", BODY);
        UIManager.put("OptionPane.buttonFont", BODY_BOLD);
        UIManager.put("Button.disabledText", MUTED);
    }

    public static JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BODY);
        label.setForeground(TEXT);
        return label;
    }

    public static JLabel muted(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BODY);
        label.setForeground(MUTED);
        return label;
    }

    public static JLabel heading(String text) {
        JLabel label = new JLabel(text);
        label.setFont(H2);
        label.setForeground(TEXT);
        return label;
    }

    public static JTextField field(String value) {
        JTextField field = new JTextField(value);
        field.setFont(BODY);
        field.setForeground(TEXT);
        field.setBackground(Color.WHITE);
        field.setCaretColor(PRIMARY);
        field.setSelectionColor(PRIMARY_SOFT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER), new EmptyBorder(7, 10, 7, 10)));
        return field;
    }

    public static JButton primary(String text) {
        return new FlatButton(text, PRIMARY, Color.WHITE, PRIMARY_DARK, null);
    }

    public static JButton secondary(String text) {
        return new FlatButton(text, Color.WHITE, TEXT, new Color(0xEEF2FA), BORDER);
    }

    public static JButton link(String text) {
        return new FlatButton(text, new Color(0, 0, 0, 0), PRIMARY, PRIMARY_SOFT, null);
    }

    public static void styleTable(JTable table) {
        table.setFont(BODY);
        table.setForeground(TEXT);
        table.setBackground(SURFACE);
        table.setRowHeight(36);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(0xEFF2F8));
        table.setIntercellSpacing(new Dimension(0, 1));
        table.setSelectionBackground(PRIMARY_SOFT);
        table.setSelectionForeground(TEXT);
        table.setFillsViewportHeight(true);
        JTableHeader header = table.getTableHeader();
        header.setFont(SMALL.deriveFont(Font.BOLD));
        header.setForeground(MUTED);
        header.setBackground(new Color(0xF8FAFE));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 34));
    }

    public static DefaultTableCellRenderer cellRenderer(int alignment) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(new EmptyBorder(0, 10, 0, 10));
                setHorizontalAlignment(alignment);
                if (!isSelected) {
                    setBackground(SURFACE);
                    setForeground(TEXT);
                }
                return this;
            }
        };
    }

    public static class RoundedPanel extends JPanel {
        private final Color fill;
        private final int radius;

        public RoundedPanel(Color fill, int radius) {
            this.fill = fill;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /** Flat, rounded button so it looks the same on every look and feel. */
    public static class FlatButton extends JButton {
        private final Color fill;
        private final Color hoverFill;
        private final Color line;

        public FlatButton(String text, Color fill, Color foreground, Color hoverFill, Color line) {
            super(text);
            this.fill = fill;
            this.hoverFill = hoverFill;
            this.line = line;
            setFont(BODY_BOLD);
            setForeground(foreground);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setRolloverEnabled(true);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(9, 16, 9, 16));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color background;
            if (!isEnabled()) {
                background = new Color(0xE9EDF5);
            } else if (getModel().isPressed()) {
                background = fill.darker();
            } else if (getModel().isRollover()) {
                background = hoverFill;
            } else {
                background = fill;
            }
            g2.setColor(background);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            if (line != null) {
                g2.setColor(line);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
