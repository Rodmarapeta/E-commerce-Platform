package ecommerce.ui;

import ecommerce.model.Cart;
import ecommerce.model.Order;
import ecommerce.model.ShippingInfo;
import ecommerce.model.User;
import ecommerce.service.CheckoutService;
import ecommerce.service.PaymentService;
import ecommerce.util.Util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CheckoutDialog extends JDialog {
    private final User user;
    private final Cart cart;
    private final CheckoutService checkout;
    private final CheckoutService.Quote quote;

    private final CardLayout steps = new CardLayout();
    private final JPanel stepPanel = new JPanel(steps);
    private final JLabel[] stepChips = new JLabel[4];
    private final JLabel errorLabel = new JLabel(" ");
    private final JButton backButton = UiTheme.secondary("Back");
    private final JButton nextButton = UiTheme.primary("Continue to shipping");

    private final JTextField nameField;
    private final JTextField emailField;
    private final JTextField phoneField;
    private final JTextField addressField;
    private final JTextField cityField;
    private final JTextField postalField;

    private final JComboBox<String> methodBox = new JComboBox<>(
            new String[]{"Credit / Debit Card", "Cash on Delivery"});
    private final JPanel cardForm = new JPanel();
    private final JTextField holderField;
    private final JTextField numberField;
    private final JTextField expiryField;
    private final JTextField cvvField;

    private final JLabel successOrder = new JLabel(" ");
    private final JLabel successSummary = new JLabel(" ");
    private final JLabel successRef = new JLabel(" ");

    private int step = 0;
    private ShippingInfo shipping;
    private Order placedOrder;

    public CheckoutDialog(Window owner, User user, Cart cart, CheckoutService checkout) {
        this(owner, user, cart, checkout, 0, null);
    }

    public CheckoutDialog(Window owner, User user, Cart cart, CheckoutService checkout,
                          int startStep, ShippingInfo shipping) {
        super(owner, "Checkout", ModalityType.APPLICATION_MODAL);
        this.user = user;
        this.cart = cart;
        this.checkout = checkout;
        this.quote = checkout.quote(cart);

        nameField = UiTheme.field(user.getName());
        emailField = UiTheme.field(user.getEmail());
        phoneField = UiTheme.field("");
        addressField = UiTheme.field("");
        cityField = UiTheme.field("");
        postalField = UiTheme.field("");
        holderField = UiTheme.field(user.getName());
        numberField = UiTheme.field(PaymentService.TEST_CARD);
        expiryField = UiTheme.field("12/29");
        cvvField = UiTheme.field("123");

        if (shipping != null) {
            nameField.setText(shipping.fullName());
            emailField.setText(shipping.email());
            phoneField.setText(shipping.phone());
            addressField.setText(shipping.address());
            cityField.setText(shipping.city());
            postalField.setText(shipping.postalCode());
        }
        this.shipping = shipping;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 680);
        setMinimumSize(new Dimension(840, 620));
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());
        getContentPane().setBackground(UiTheme.BG);

        add(buildHeader(), BorderLayout.NORTH);

        stepPanel.setOpaque(false);
        stepPanel.setBorder(new EmptyBorder(18, 24, 0, 24));
        stepPanel.add(buildCartStep(), "0");
        stepPanel.add(buildShippingStep(), "1");
        stepPanel.add(buildPaymentStep(), "2");
        stepPanel.add(buildSuccessStep(), "3");
        add(stepPanel, BorderLayout.CENTER);

        add(buildFooter(), BorderLayout.SOUTH);
        showStep(shipping != null && startStep >= 1 ? startStep : 0);
    }

    public Order getPlacedOrder() {
        return placedOrder;
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UiTheme.SURFACE);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, UiTheme.BORDER),
                new EmptyBorder(16, 24, 16, 24)));

        JPanel titles = new JPanel();
        titles.setOpaque(false);
        titles.setLayout(new BoxLayout(titles, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("Checkout");
        title.setFont(UiTheme.H1);
        title.setForeground(UiTheme.TEXT);
        titles.add(title);
        titles.add(UiTheme.muted(user.getName() + " \u00B7 " + user.getEmail()));
        header.add(titles, BorderLayout.WEST);

        JPanel chips = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        chips.setOpaque(false);
        String[] labels = {"1  Cart", "2  Shipping", "3  Payment", "4  Done"};
        for (int i = 0; i < stepChips.length; i++) {
            JLabel chip = new JLabel(labels[i]);
            chip.setFont(UiTheme.SMALL.deriveFont(Font.BOLD));
            chip.setBorder(new EmptyBorder(7, 12, 7, 12));
            chip.setOpaque(true);
            stepChips[i] = chip;
            chips.add(chip);
        }
        header.add(chips, BorderLayout.EAST);
        return header;
    }

    private JPanel buildCartStep() {
        JPanel panel = new JPanel(new BorderLayout(0, 14));
        panel.setOpaque(false);

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.add(UiTheme.heading("Review your items"));
        heading.add(UiTheme.muted("Confirm quantities, then continue to shipping."));
        panel.add(heading, BorderLayout.NORTH);

        DefaultTableModel itemModel = new DefaultTableModel(
                new String[]{"Item", "Qty", "Unit price", "Amount"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Cart.Line line : cart.getLines()) {
            itemModel.addRow(new Object[]{
                    line.getProduct().getName(),
                    line.getQuantity(),
                    Util.money(line.getProduct().getPrice()),
                    Util.money(line.lineTotal())
            });
        }
        JTable table = new JTable(itemModel);
        UiTheme.styleTable(table);
        table.setRowSelectionAllowed(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setCellRenderer(UiTheme.cellRenderer(SwingConstants.CENTER));
        table.getColumnModel().getColumn(2).setCellRenderer(UiTheme.cellRenderer(SwingConstants.RIGHT));
        table.getColumnModel().getColumn(3).setCellRenderer(UiTheme.cellRenderer(SwingConstants.RIGHT));

        JPanel tableCard = new UiTheme.RoundedPanel(UiTheme.SURFACE, 12);
        tableCard.setLayout(new BorderLayout());
        tableCard.setBorder(new EmptyBorder(6, 6, 6, 6));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(UiTheme.SURFACE);
        tableCard.add(scroll, BorderLayout.CENTER);
        panel.add(tableCard, BorderLayout.CENTER);
        panel.add(buildTotalsCard(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel buildTotalsCard() {
        JPanel card = new UiTheme.RoundedPanel(UiTheme.SURFACE, 12);
        card.setLayout(new GridBagLayout());
        card.setBorder(new EmptyBorder(12, 18, 12, 18));

        addMoneyRow(card, 0, "Subtotal", Util.money(quote.subtotal()), false);
        addMoneyRow(card, 1, "VAT (12%)", Util.money(quote.vat()), false);
        addMoneyRow(card, 2, "Shipping", quote.shipping() == 0 ? "Free" : Util.money(quote.shipping()), false);
        addMoneyRow(card, 3, "Total", Util.money(quote.total()), true);
        return card;
    }

    private void addMoneyRow(JPanel panel, int row, String labelText, String valueText, boolean strong) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = row;
        gc.gridx = 0;
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(2, 0, 2, 28);
        JLabel label = new JLabel(labelText);
        label.setFont(strong ? UiTheme.BODY_BOLD.deriveFont(15f) : UiTheme.BODY);
        label.setForeground(strong ? UiTheme.TEXT : UiTheme.MUTED);
        panel.add(label, gc);

        gc.gridx = 1;
        gc.weightx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = new Insets(2, 0, 2, 0);
        JLabel value = new JLabel(valueText);
        value.setFont(strong ? UiTheme.BODY_BOLD.deriveFont(15f) : UiTheme.BODY_BOLD);
        value.setForeground(strong ? UiTheme.PRIMARY : UiTheme.TEXT);
        panel.add(value, gc);
    }

    private JPanel buildShippingStep() {
        JPanel panel = new JPanel(new BorderLayout(0, 14));
        panel.setOpaque(false);

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.add(UiTheme.heading("Shipping details"));
        heading.add(UiTheme.muted("Where should we send this order?"));
        panel.add(heading, BorderLayout.NORTH);

        JPanel form = new UiTheme.RoundedPanel(UiTheme.SURFACE, 12);
        form.setLayout(new GridBagLayout());
        form.setBorder(new EmptyBorder(20, 22, 20, 22));

        JPanel pair1 = new JPanel(new GridLayout(1, 2, 16, 0));
        pair1.setOpaque(false);
        pair1.add(formField("Full name", nameField));
        pair1.add(formField("Email", emailField));

        JPanel pair2 = new JPanel(new GridLayout(1, 2, 16, 0));
        pair2.setOpaque(false);
        pair2.add(formField("Phone", phoneField));
        pair2.add(formField("City", cityField));

        JPanel lower = new JPanel(new GridLayout(1, 2, 16, 0));
        lower.setOpaque(false);
        lower.add(formField("Postal code", postalField));
        JPanel notePanel = new JPanel(new BorderLayout());
        notePanel.setOpaque(false);
        JLabel note = UiTheme.muted("Demo build \u2014 no parcel is shipped.");
        note.setBorder(new EmptyBorder(20, 0, 0, 0));
        notePanel.add(note, BorderLayout.NORTH);
        lower.add(notePanel);

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.weightx = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.WEST;

        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 12, 0);
        form.add(pair1, gc);
        gc.gridy = 1;
        form.add(pair2, gc);
        gc.gridy = 2;
        form.add(formField("Street address", addressField), gc);
        gc.gridy = 3;
        gc.insets = new Insets(0, 0, 0, 0);
        form.add(lower, gc);

        JPanel holder = new JPanel(new BorderLayout());
        holder.setOpaque(false);
        holder.add(form, BorderLayout.NORTH);
        panel.add(holder, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildPaymentStep() {
        JPanel panel = new JPanel(new BorderLayout(0, 14));
        panel.setOpaque(false);

        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        heading.add(UiTheme.heading("Payment"));
        heading.add(UiTheme.muted("Card payments are simulated \u2014 no real money moves."));
        panel.add(heading, BorderLayout.NORTH);

        JPanel card = new UiTheme.RoundedPanel(UiTheme.SURFACE, 12);
        card.setLayout(new BorderLayout(0, 16));
        card.setBorder(new EmptyBorder(20, 22, 20, 22));

        JPanel methodRow = new JPanel(new BorderLayout(12, 0));
        methodRow.setOpaque(false);
        methodRow.add(UiTheme.label("Payment method"), BorderLayout.WEST);
        methodBox.setFont(UiTheme.BODY);
        methodBox.setBackground(Color.WHITE);
        methodBox.setPreferredSize(new Dimension(220, 34));
        methodRow.add(methodBox, BorderLayout.EAST);
        card.add(methodRow, BorderLayout.NORTH);

        setupCardForm();
        card.add(cardForm, BorderLayout.CENTER);

        JPanel notes = new JPanel();
        notes.setOpaque(false);
        notes.setLayout(new BoxLayout(notes, BoxLayout.Y_AXIS));
        JLabel testCards = UiTheme.muted("Test cards: 4242 4242 4242 4242 (approved) \u00B7 4000 0000 0000 0002 (declined)");
        testCards.setFont(UiTheme.SMALL);
        notes.add(testCards);
        notes.add(Box.createVerticalStrut(2));
        JLabel codNote = UiTheme.muted("Cash on delivery: the rider collects the total when the order arrives.");
        codNote.setFont(UiTheme.SMALL);
        notes.add(codNote);
        card.add(notes, BorderLayout.SOUTH);

        JPanel holder = new JPanel(new BorderLayout());
        holder.setOpaque(false);
        holder.add(card, BorderLayout.NORTH);
        panel.add(holder, BorderLayout.CENTER);

        methodBox.addActionListener(e -> {
            cardForm.setVisible(methodBox.getSelectedIndex() == 0);
            card.revalidate();
            card.repaint();
        });
        return panel;
    }

    private void setupCardForm() {
        cardForm.setOpaque(false);
        cardForm.setLayout(new GridBagLayout());

        JPanel row = new JPanel(new GridLayout(1, 2, 16, 0));
        row.setOpaque(false);
        row.add(formField("Expiry (MM/YY)", expiryField));
        row.add(formField("CVV", cvvField));

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.weightx = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;

        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 12, 0);
        cardForm.add(formField("Cardholder name", holderField), gc);
        gc.gridy = 1;
        cardForm.add(formField("Card number", numberField), gc);
        gc.gridy = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        cardForm.add(row, gc);
    }

    private JPanel formField(String labelText, JTextField field) {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        JLabel label = new JLabel(labelText);
        label.setFont(UiTheme.SMALL.deriveFont(Font.BOLD));
        label.setForeground(UiTheme.MUTED);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

        wrapper.add(label);
        wrapper.add(Box.createVerticalStrut(4));
        wrapper.add(field);
        return wrapper;
    }

    private JPanel buildSuccessStep() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));

        JPanel badge = new UiTheme.RoundedPanel(UiTheme.SUCCESS, 40);
        badge.setLayout(new BorderLayout());
        badge.setPreferredSize(new Dimension(80, 80));
        badge.setMaximumSize(new Dimension(80, 80));
        badge.setAlignmentX(Component.CENTER_ALIGNMENT);
        JComponent check = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                int w = getWidth();
                int h = getHeight();
                g2.drawLine(Math.round(w * 0.28f), Math.round(h * 0.52f),
                        Math.round(w * 0.44f), Math.round(h * 0.68f));
                g2.drawLine(Math.round(w * 0.44f), Math.round(h * 0.68f),
                        Math.round(w * 0.73f), Math.round(h * 0.35f));
                g2.dispose();
            }
        };
        badge.add(check, BorderLayout.CENTER);

        JLabel title = new JLabel("Order confirmed");
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(UiTheme.TEXT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = UiTheme.muted("Your receipt has been saved to order history.");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        successOrder.setFont(UiTheme.BODY_BOLD.deriveFont(16f));
        successOrder.setForeground(UiTheme.PRIMARY);
        successOrder.setAlignmentX(Component.CENTER_ALIGNMENT);

        successSummary.setFont(UiTheme.BODY);
        successSummary.setForeground(UiTheme.TEXT);
        successSummary.setAlignmentX(Component.CENTER_ALIGNMENT);

        successRef.setFont(UiTheme.SMALL);
        successRef.setForeground(UiTheme.MUTED);
        successRef.setAlignmentX(Component.CENTER_ALIGNMENT);

        inner.add(badge);
        inner.add(Box.createVerticalStrut(18));
        inner.add(title);
        inner.add(Box.createVerticalStrut(6));
        inner.add(subtitle);
        inner.add(Box.createVerticalStrut(22));
        inner.add(successOrder);
        inner.add(Box.createVerticalStrut(6));
        inner.add(successSummary);
        inner.add(Box.createVerticalStrut(2));
        inner.add(successRef);

        panel.add(inner);
        return panel;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout(16, 0));
        footer.setBackground(UiTheme.SURFACE);
        footer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, UiTheme.BORDER),
                new EmptyBorder(14, 24, 14, 24)));

        errorLabel.setFont(UiTheme.SMALL);
        errorLabel.setForeground(UiTheme.DANGER);
        footer.add(errorLabel, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actions.setOpaque(false);
        backButton.addActionListener(e -> goBack());
        nextButton.addActionListener(e -> goNext());
        actions.add(backButton);
        actions.add(nextButton);
        footer.add(actions, BorderLayout.EAST);
        return footer;
    }

    private void showStep(int index) {
        step = index;
        steps.show(stepPanel, String.valueOf(index));
        for (int i = 0; i < stepChips.length; i++) {
            boolean current = i == index;
            boolean done = i < index;
            stepChips[i].setBackground(current ? UiTheme.PRIMARY
                    : (done ? UiTheme.PRIMARY_SOFT : new Color(0xF1F4FA)));
            stepChips[i].setForeground(current ? Color.WHITE
                    : (done ? UiTheme.PRIMARY : UiTheme.MUTED));
        }
        errorLabel.setText(" ");
        if (index == 0) {
            backButton.setVisible(true);
            backButton.setText("Cancel");
            nextButton.setText("Continue to shipping");
        } else if (index == 1) {
            backButton.setText("Back");
            nextButton.setText("Continue to payment");
        } else if (index == 2) {
            backButton.setText("Back");
            nextButton.setText("Place order");
        } else {
            backButton.setText("View receipt");
            nextButton.setText("Done");
        }
        nextButton.setEnabled(true);
        getRootPane().setDefaultButton(nextButton);
    }

    private void goBack() {
        if (step == 0) {
            dispose();
        } else if (step == 1) {
            showStep(0);
        } else if (step == 2) {
            showStep(1);
        } else if (placedOrder != null) {
            new ReceiptDialog(this, placedOrder).setVisible(true);
        }
    }

    private void goNext() {
        switch (step) {
            case 0:
                showStep(1);
                break;
            case 1: {
                String error = validateShipping();
                if (error != null) {
                    showError(error);
                    break;
                }
                shipping = readShipping();
                showStep(2);
                break;
            }
            case 2:
                submitOrder();
                break;
            default:
                dispose();
        }
    }

    private String validateShipping() {
        if (Util.isBlank(nameField.getText())) {
            return "Please enter the recipient's full name.";
        }
        if (!Util.isValidEmail(emailField.getText())) {
            return "Please enter a valid email address.";
        }
        if (!Util.isValidPhone(phoneField.getText())) {
            return "Please enter a valid phone number.";
        }
        if (Util.isBlank(addressField.getText())) {
            return "Please enter the street address.";
        }
        if (Util.isBlank(cityField.getText())) {
            return "Please enter the city.";
        }
        if (!Util.digitsOnly(postalField.getText()).matches("\\d{3,6}")) {
            return "Postal code must be 3 to 6 digits.";
        }
        return null;
    }

    private ShippingInfo readShipping() {
        return new ShippingInfo(
                nameField.getText().trim(),
                emailField.getText().trim(),
                phoneField.getText().trim(),
                addressField.getText().trim(),
                cityField.getText().trim(),
                postalField.getText().trim());
    }

    private String validateCard() {
        String digits = Util.digitsOnly(numberField.getText());
        if (digits.length() < 13) {
            return "Enter a valid card number.";
        }
        if (!Util.passesLuhn(digits)) {
            return "Card number looks invalid \u2014 check the digits.";
        }
        if (!expiryField.getText().trim().matches("(0[1-9]|1[0-2])/\\d{2}")) {
            return "Expiry must be in MM/YY format.";
        }
        if (!Util.digitsOnly(cvvField.getText()).matches("\\d{3,4}")) {
            return "CVV must be 3 or 4 digits.";
        }
        if (Util.isBlank(holderField.getText())) {
            return "Enter the cardholder name.";
        }
        return null;
    }

    private void submitOrder() {
        final CheckoutService.PaymentMethod method = methodBox.getSelectedIndex() == 0
                ? CheckoutService.PaymentMethod.CARD
                : CheckoutService.PaymentMethod.COD;
        PaymentService.CardDetails cardDetails = null;
        if (method == CheckoutService.PaymentMethod.CARD) {
            String error = validateCard();
            if (error != null) {
                showError(error);
                return;
            }
            cardDetails = new PaymentService.CardDetails(
                    holderField.getText().trim(),
                    numberField.getText().trim(),
                    expiryField.getText().trim(),
                    cvvField.getText().trim());
        }
        final PaymentService.CardDetails card = cardDetails;
        setBusy(true);

        new SwingWorker<Order, Void>() {
            private Exception failure;

            @Override
            protected Order doInBackground() {
                try {
                    Thread.sleep(700);
                    return checkout.placeOrder(user, cart, shipping, method, card);
                } catch (Exception e) {
                    failure = e;
                    return null;
                }
            }

            @Override
            protected void done() {
                setBusy(false);
                if (failure != null) {
                    showError(failure.getMessage() == null ? "Payment could not be processed." : failure.getMessage());
                    return;
                }
                try {
                    placedOrder = get();
                } catch (Exception e) {
                    showError("Payment could not be processed.");
                    return;
                }
                renderSuccess(placedOrder);
                showStep(3);
            }
        }.execute();
    }

    private void setBusy(boolean busy) {
        nextButton.setEnabled(!busy);
        backButton.setEnabled(!busy);
        nextButton.setText(busy ? "Processing\u2026" : "Place order");
        methodBox.setEnabled(!busy);
        holderField.setEnabled(!busy);
        numberField.setEnabled(!busy);
        expiryField.setEnabled(!busy);
        cvvField.setEnabled(!busy);
    }

    private void showError(String message) {
        errorLabel.setText(message);
    }

    private void renderSuccess(Order order) {
        successOrder.setText(order.getOrderId());
        successSummary.setText(order.getPaymentMethod() + " \u00B7 " + Util.money(order.getTotalPrice()));
        successRef.setText("Payment reference " + order.getPaymentReference());
    }
}
